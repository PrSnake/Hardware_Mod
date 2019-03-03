package fr.agh.hardware.objects.blocks.tileentities;

import fr.agh.hardware.objects.blocks.BlockEnergyGenerator;
import fr.agh.hardware.util.helper.Fuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityEnergyGenerator extends TileEntity implements ITickable, ICapabilityProvider {

	private ItemStackHandler handler = new ItemStackHandler(6);
	private String customName;
	
	/** The number of ticks that the furnace will keep burning */
	private int burnTime;
	/** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
	private int currentBurnTime;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) this.handler;
		}
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
		this.handler.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));
		this.burnTime = compound.getInteger("BurnTime");
		this.currentBurnTime = getItemBurnTime(this.handler.getStackInSlot(1));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setTag("ItemStackHandler", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	/** Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.*/
	public int getInventoryStackSizeLimit() {
		return 64;
	}
	
	public int getUpgradeStackSizeLimit() {
		return 1;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityEnergyGenerator te) {
		return te.getField(1) > 0;
	}
	
	@Override
	public void update() {
		
		boolean flagIsBurning = this.isBurning(); 
		boolean flagIsDirty = false;
		
		if (this.isBurning()) {
			--this.burnTime;
		}
		
		if (!this.world.isRemote) {
			
			ItemStack fuelSlot = this.getStackInFuelSlot();
			
			if (this.isBurning() || !fuelSlot.isEmpty()) {
				
				if (!this.isBurning() && this.canBurn()) {
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
								this.setStackInFuelSlot(itemFuelSlot.getContainerItem(fuelSlot));
							}
						}
					}
					
				}
				
				if (this.isBurning() && this.canBurn()) {
					flagIsDirty = true;
				}
				
			}
			
			if (flagIsBurning != this.isBurning()) {
				flagIsDirty = true;
				BlockEnergyGenerator.setState(this.isBurning(), this.world, this.pos);
			}
		}
		
		if (flagIsDirty) {
			this.markDirty();
		}
	}
	
	private boolean canBurn()  {
		if (this.getItemBurnTime(this.getStackInFuelSlot()) == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Get the number of ticks that a certain ItemStack can last
	 * @param fuel The content of the fuel slot
	 * @return number of ticks that a fresh item of the input can last, 0 if it's empty or if it's not a fuel
	 */
	public static int getItemBurnTime(ItemStack fuel) {
		if(fuel.isEmpty()) {
			return 0;
		}
		
		return Fuel.getInstance().getItemBurnTime(fuel.getItem());
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public ItemStack getStackInFuelSlot() {
		return this.handler.getStackInSlot(1);
	}
	
	public void setStackInFuelSlot (ItemStack stack) {
		this.handler.setStackInSlot(1, stack);
	}
	
	public int getField(int id) {
		switch(id) {
			case 0:
				return this.burnTime;
			case 1:
				return this.currentBurnTime;
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
			default:
				break;
		}
	}
}

































