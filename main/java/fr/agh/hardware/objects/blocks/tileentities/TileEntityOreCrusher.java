package fr.agh.hardware.objects.blocks.tileentities;

import fr.agh.hardware.objects.blocks.BlockOreCrusher;
import fr.agh.hardware.objects.blocks.recipes.RecipesOreCrusher;
import fr.agh.hardware.util.helper.Fuel;
import fr.agh.hardware.util.helper.FuelType;
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
	private FuelType fuelType = FuelType.THERMIC;
	
	/** The number of ticks that the furnace will keep burning */
	private int burnTime;
	/** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime = 200;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.hardware.ore_crusher.name");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.handler.getStackInSlot(1));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	/** Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.*/
	public int getInventoryStackSizeLimit() {
		return 64;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityOreCrusher te) {
		return te.getField(1) > 0;
	}
	
	// méthode appelée à chaque tic pour actualiser le bloc
	@Override
	public void update() {
		
		boolean flagIsBurning = this.isBurning(); 
		boolean flagIsDirty = false;
		
		if (this.isBurning()) {
			--this.burnTime;
		}
		
		if (!this.world.isRemote) {
			
			ItemStack fuelSlot = this.getStackInFuelSlot();
			
			if (this.isBurning() || !fuelSlot.isEmpty() && !(this.getStackInInputSlot().isEmpty())) {
				
				if (!this.isBurning() && this.canSmelt()) {
					this.burnTime = this.getItemBurnTime(fuelSlot);
					this.currentBurnTime = this.burnTime;
					
					if (this.isBurning()) {
						flagIsDirty = true;
						
						if (!fuelSlot.isEmpty()) {
							Item itemFuelSlot = fuelSlot.getItem();
							fuelSlot.shrink(1);
							
							if (fuelSlot.isEmpty()) {
								/**
								 *  If anyone wonders what containerItem is, it's used to keep items after a craft.
								 *  For example, after crafting cake, the milk buckets turn into buckets.
								 *  Forge uses the container item to get the item that an ingredient transforms into.
								 *  Thus, buckets are milk buckets' container item.
								 *  (If there isn't a container item, then Itemstack.empty is returned) 
								 */
								this.setStackInFuelSlot((ItemStack)itemFuelSlot.getContainerItem(fuelSlot));
							}
						}
					}
					
				}
				
				if (this.isBurning() && this.canSmelt()) {
					++this.cookTime;
					
					if (this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime(this.getStackInInputSlot());
						this.smeltItem();
						flagIsDirty = true;
					}
				} else {
					this.cookTime = 0;
				}
				
			} else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}
			
			if (flagIsBurning != this.isBurning()) {
				flagIsDirty = true;
				BlockOreCrusher.setState(this.isBurning(), this.world, this.pos);
			}
		}
		
		if (flagIsDirty) {
			this.markDirty();
		}
	}
	
	private void smeltItem() {
		if (this.canSmelt()) {
			ItemStack inputStack = this.getStackInInputSlot();
			ItemStack outputStack = this.getStackInOutputSlot();
			ItemStack resultStack = RecipesOreCrusher.getInstance().getOreCrushingResult(getStackInInputSlot());
			
			if (outputStack.isEmpty()) {
				this.setStackInOutputSlot(resultStack.copy());
				this.getStackInOutputSlot().grow(1);
			} else if (outputStack.getItem() == resultStack.getItem()) {
				outputStack.grow(2); // TODO use a variable if we want to implement upgrade system
				// outputStack.grow(resultStack.getCount()); // If we deal with results upgrade in RecipesOreCrusher use this instead
			}
			inputStack.shrink(1);
		}
	}

	private int getCookTime(ItemStack stackInSlot) {
		return this.totalCookTime;
	}

	private boolean canSmelt()  {
		if (this.getStackInInputSlot().isEmpty()) {
			return false;
		} else {
			ItemStack resultStack = RecipesOreCrusher.getInstance().getOreCrushingResult(getStackInInputSlot());
			if (resultStack.isEmpty()) {
				return false;
			} else {
				ItemStack outputStack = this.getStackInOutputSlot();
				// TODO set an offset where getCount is used because resultStack is always a stack of 1 Item (it's used to facilitate ore crusher upgrade system where it will only edit offset and multipliers)
				if (outputStack.isEmpty()) {
					return true;
				} else if (outputStack.getItem() != resultStack.getItem()) {
					return false;
				} else if ((outputStack.getCount() + 2) <= this.getInventoryStackSizeLimit() && (outputStack.getCount() + 2) <= outputStack.getMaxStackSize()) {
					return true;
				} else {
					return outputStack.getCount() + 2 <= resultStack.getMaxStackSize();
				}
			}
		}
	}
	
	/**
	 * Get the number of ticks that a certain ItemStack can last
	 * @param fuel The content of the fuel slot
	 * @return number of ticks that a fresh item of the input can last, 0 if it's empty or if it's not a fuel
	 */
	public static int getItemBurnTime(ItemStack fuel) {
		if(fuel.isEmpty()) {
			return 0;
		} else {
			return Fuel.getInstance().getItemBurnTime(fuel.getItem());
		}
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public ItemStack getStackInInputSlot() {
		return this.handler.getStackInSlot(0);
	}
	
	public void setStackInInputSlot(ItemStack stack) {
		this.handler.setStackInSlot(0, stack);
	}
	
	public ItemStack getStackInFuelSlot() {
		return this.handler.getStackInSlot(1);
	}
	
	public void setStackInFuelSlot (ItemStack stack) {
		this.handler.setStackInSlot(1, stack);
	}
	
	public ItemStack getStackInOutputSlot() {
		return this.handler.getStackInSlot(2);
	}
	
	public void setStackInOutputSlot(ItemStack stack) {
		this.handler.setStackInSlot(2, stack);
	}
	
	public int getField(int id) {
		switch(id) {
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
		switch(id) {
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
