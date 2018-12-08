package fr.agh.hardware.objects.blocks.tileentities;

import fr.agh.hardware.objects.blocks.BlockOreCrusher;
import fr.agh.hardware.objects.blocks.recipes.RecipesOreCrusher;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;


public class TileEntityOreCrusher extends TileEntity implements ITickable{

	private ItemStackHandler handler = new ItemStackHandler(3);
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	
	/** The number of ticks that the furnace will keep burning */
	private int burnTime;
	/** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime = 50;
	private boolean dirty = false;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.ore_crusher");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.handler.getStackInSlot(2));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isBurning() 
	{
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityOreCrusher te) 
	{
		return te.getField(1) > 0;
	}
	
	// méthode appelée à chaque tic pour actualiser le bloc
	@Override
	public void update() 
	{
		
		boolean isBurning = this.isBurning();
        boolean dirty = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack fuel = this.handler.getStackInSlot(1);

            if (this.isBurning() || !fuel.isEmpty() && !((ItemStack)this.handler.getStackInSlot(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.burnTime = getItemBurnTime(fuel);
                    this.currentBurnTime = this.burnTime;

                    if (this.isBurning())
                    {
                        dirty = true;

                        if (!fuel.isEmpty())
                        {
                            Item item = fuel.getItem();
                            fuel.shrink(1);

                            if (fuel.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(fuel);
                                this.handler.setStackInSlot(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.handler.getStackInSlot(0));
                        this.smeltItem();
                        dirty = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (isBurning != this.isBurning())
            {
            	dirty = true;
                BlockOreCrusher.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (dirty)
        {
            this.markDirty();
        }
	}
	
	private void smeltItem() {
		// TODO Auto-generated method stub
		ItemStack input = this.handler.getStackInSlot(0);
		ItemStack output = this.handler.getStackInSlot(2);
		ItemStack result = RecipesOreCrusher.getInstance().getOreCrushingResult(input);
		
		if(!result.isEmpty())
		{
			if (output.isEmpty())
			{
				this.handler.insertItem(2, result, false);
				output.setCount(2);
			}
			else if (output == result)
			{
				output.grow(2);
			}
			else if (output != result)
			{
				return;
			}
			input.shrink(1);
		}
		
		return;
	}

	private int getCookTime(ItemStack stackInSlot) {
		return this.totalCookTime;
	}

	private boolean canSmelt() 
	{
		
		if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty())
		{
			return false;
		}
		else 
		{
			ItemStack result = RecipesOreCrusher.getInstance().getOreCrushingResult((ItemStack)this.handler.getStackInSlot(0));
			
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.handler.getStackInSlot(2);
				if(output.isEmpty())
				{
					return true;
				}
				
				if(!output.isItemEqual(result))
				{
					return false;
				}
				
				// Si on change le nombre d'item créées par le bloc remplacer le 2 par ce nombre
				int res = output.getCount() + 2;

				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}
	
	/**
	 * Permet d'obtenir le nombre de tic durant lequel une item peut être utilisée comme carburant
	 * @param fuel Carburant qui est inséré dans le slot 1 de la machine
	 * @return retourne la valeur sous forme d'entier du nombre de tic pendant lequel l'item peut servir de carburant
	 */
	public static int getItemBurnTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) 
			{
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB) return 150;
				if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
				if (block == Blocks.COAL_BLOCK) return 16000;
			}

			if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
			if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
			if (item == Items.STICK) return 100;
			if (item == Items.COAL) return 1600;
			if (item == Items.LAVA_BUCKET) return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
			if (item == Items.BLAZE_ROD) return 2400;

			//deprecated methode
			return GameRegistry.getFuelValue(fuel);
			
			//return ForgeEventFactory.getItemBurnTime(fuel);
		}
	}
	
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

}
