package fr.agh.hardware.util.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Fuel {

	private static final Fuel INSTANCE = new Fuel();
	
	private final ArrayList<Item> fuelList = new ArrayList<Item>();
	
	private final Map<Item, Integer> itemBurnTime = new HashMap<Item, Integer>();
	private final Map<Item, FuelType> itemFuelType = new HashMap<Item, FuelType>();
	
	public static Fuel getInstance() {
		return INSTANCE;
	}
	
	private Fuel() {
		// Add all fuel from this page https://minecraft.gamepedia.com/Smelting#Fuel
		// commented ones are for future version of minecraft
		this.addFuel(Items.LAVA_BUCKET, 20000, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.COAL_BLOCK), 16000, FuelType.THERMIC);
		// this.addFuel(Item.getItemFromBlock(Blocks.driedkelp), ticks, type);
		this.addFuel(Items.BLAZE_ROD, ticks, type);
		this.addFuel(Items.COAL, ticks, type); // coal & charcoal
		this.addFuel(Items.BOAT, ticks, type); // all boats types
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
		this.addFuel(item, ticks, type);
	}
	
	public boolean isItemFuel(Item item) {
		for(Item itemIterator : fuelList) {
			if(item.equals(itemIterator)) {
				return true;	
			}
		}
		return false;
	}
	
	public boolean isFuelCompatible(Item item, FuelType type) {
		
		if(this.isItemFuel(item) && type.equals(this.getFuelType(item))) {
			return true;
		}
		
		return false;
	}
	
	public void addFuel(Item item, int ticks, FuelType type) {
		if (!this.isItemFuel(item)) {
			fuelList.add(item);
		}
		this.setItemBurnTime(item, ticks);
		this.setFuelType(item, type);
	}
	
	public int getItemBurnTime(Item item) {
		return this.itemBurnTime.get(item);
	}
	
	public void setItemBurnTime(Item item, int ticks) {
		this.itemBurnTime.put(item, ticks);
	}
	
	
	public FuelType getFuelType(Item item) {
		return this.itemFuelType.get(item);
	}
	
	public void setFuelType(Item item, FuelType type) {
		this.itemFuelType.put(item, type);
	}
	
	
}
