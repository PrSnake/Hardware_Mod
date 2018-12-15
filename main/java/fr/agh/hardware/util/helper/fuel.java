package fr.agh.hardware.util.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class fuel {

	private int burnTime = 0;
	private boolean isFuel = false;
	
	public fuel(int burn, boolean fuel, int type) {
		this.setBurnTime(burn);
		this.setFuelType(type);
	}
	
	public boolean isItemFuel(ItemStack stack) {
		//TODO
		return true;
	}
	
	public void getBurnTime(Item item) {
		
	}
	
	public void setBurnTime(int ticks) {
		
	}
	
	public void getFuelType(Item item) {
		
	}
	
	public void setFuelType(int type) {
		
	}
	
	
}
