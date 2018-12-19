package fr.agh.hardware.objects.blocks.containers;

import fr.agh.hardware.objects.blocks.recipes.RecipesOreCrusher;
import fr.agh.hardware.objects.blocks.tileentities.TileEntityOreCrusher;
import fr.agh.hardware.util.helper.Fuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerOreCrusher extends Container {

	private final TileEntityOreCrusher tileentity;
	private int cookTime;
	private int totalCookTime;
	private int burnTime;
	private int currentBurnTime;
	
	public ContainerOreCrusher(InventoryPlayer player, TileEntityOreCrusher tileentity) {
		this.tileentity = tileentity;
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		// entity, index, Y, X
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 56, 17));	// input
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 56, 53));	// fuel
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 112, 31));	// output
		
		// player inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
			}
		}
		
		// player toolbar
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	/** Looks for changes made in the container, sends them to every listener. */
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tileentity.getField(2)) {
				listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			}
			if(this.burnTime != this.tileentity.getField(0)) {
				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			}
			if(this.currentBurnTime != this.tileentity.getField(1)) {
				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			}
			if(this.totalCookTime != this.tileentity.getField(3)) {
				listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			}
		}
		
		this.cookTime = this.tileentity.getField(2);
		this.burnTime = this.tileentity.getField(0);
		this.currentBurnTime = this.tileentity.getField(1);
		this.totalCookTime = this.tileentity.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		
		ItemStack stackBuffer0 = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			
			ItemStack stackBuffer1 = slot.getStack();
			stackBuffer0 = stackBuffer1.copy();
			
			if (index == 2) {
				
				if (!this.mergeItemStack(stackBuffer1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(stackBuffer1, stackBuffer0);
				
			} else if (index != 1 && index != 0) {
				if (!RecipesOreCrusher.getInstance().getOreCrushingResult(stackBuffer1).isEmpty()) {
					if (!this.mergeItemStack(stackBuffer1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (Fuel.getInstance().isItemFuel(stackBuffer1.getItem())) {
					if (!this.mergeItemStack(stackBuffer1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(stackBuffer1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.mergeItemStack(stackBuffer1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stackBuffer1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}
			
			if (stackBuffer1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			
			if (stackBuffer1.getCount() == stackBuffer0.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(playerIn, stackBuffer1);
		}
		
		return stackBuffer0;
	}
}







































































