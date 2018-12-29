package fr.agh.hardware.util.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.agh.hardware.util.enums.FuelType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

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
		
		// commented ones are for future version of minecraft (1.13)
		// this.addFuel(Item.getItemFromBlock(Blocks.DRIEDKELP), ticks, FuelType.THERMIC);
		// this.addFuel(Item.getItemFromBlock(Blocks.BAMBOO), ticks, FuelType.THERMIC);
		// this.addFuel(Item.getItemFromBlock(Blocks.SCAFFOLDING), ticks, FuelType.THERMIC);
		
		// Items
		this.addFuel(Items.LAVA_BUCKET, 20000, FuelType.THERMIC);
		this.addFuel(Items.BLAZE_ROD, 2400, FuelType.THERMIC);
		this.addFuel(Items.COAL, 1600, FuelType.THERMIC); // coal & charcoal
		this.addFuel(Items.BOAT, 400, FuelType.THERMIC); // all boats types
		this.addFuel(Items.BOW, 300, FuelType.THERMIC);
		this.addFuel(Items.FISHING_ROD, 300, FuelType.THERMIC);
		this.addFuel(Items.WOODEN_AXE, 200, FuelType.THERMIC);
		this.addFuel(Items.WOODEN_HOE, 200, FuelType.THERMIC);
		this.addFuel(Items.WOODEN_PICKAXE, 200, FuelType.THERMIC);
		this.addFuel(Items.WOODEN_SHOVEL, 200, FuelType.THERMIC);
		this.addFuel(Items.WOODEN_SWORD, 200, FuelType.THERMIC);
		this.addFuel(Items.SIGN, 200, FuelType.THERMIC);
		this.addFuel(Items.BOWL, 100, FuelType.THERMIC);
		this.addFuel(Items.STICK, 100, FuelType.THERMIC);
		
		
		// Blocks
		this.addFuel(Item.getItemFromBlock(Blocks.COAL_BLOCK), 16000, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.LOG), 300, FuelType.THERMIC);		//  oak to jungle
		this.addFuel(Item.getItemFromBlock(Blocks.LOG2), 300, FuelType.THERMIC);	// acacia to dark oak
		this.addFuel(Item.getItemFromBlock(Blocks.PLANKS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.ACACIA_FENCE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.ACACIA_FENCE_GATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.BIRCH_FENCE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.BIRCH_FENCE_GATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.DARK_OAK_FENCE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.DARK_OAK_FENCE_GATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.JUNGLE_FENCE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.JUNGLE_FENCE_GATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.OAK_FENCE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.OAK_FENCE_GATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.SPRUCE_FENCE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.SPRUCE_FENCE_GATE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.ACACIA_STAIRS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.BIRCH_STAIRS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.DARK_OAK_STAIRS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.JUNGLE_STAIRS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.OAK_STAIRS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.SPRUCE_STAIRS), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.TRAPDOOR), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.CRAFTING_TABLE), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.BOOKSHELF), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.CHEST), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.TRAPPED_CHEST), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.DAYLIGHT_DETECTOR), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.JUKEBOX), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.NOTEBLOCK), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM_BLOCK), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.RED_MUSHROOM_BLOCK), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.STANDING_BANNER), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.WOODEN_SLAB), 150, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.LADDER), 300, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.ACACIA_DOOR), 200, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.BIRCH_DOOR), 200, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.JUNGLE_DOOR), 200, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.OAK_DOOR), 200, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.SPRUCE_DOOR), 200, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.DARK_OAK_DOOR), 200, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.SAPLING), 100, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.WOODEN_BUTTON), 100, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.WOOL), 100, FuelType.THERMIC);
		this.addFuel(Item.getItemFromBlock(Blocks.CARPET), 67, FuelType.THERMIC);
	}
	
	public boolean isItemFuel(Item item) {
		for(Item itemIterator : fuelList) {
			if(item.equals(itemIterator)) {
				return true;	
			}
		}
		return false;
	}
	
	/**return if the said item has the same fuel type
	 * than the fuel type in parameter (usually the
	 * fuel type of the machine)
	 * @author Lionel
	 * @param item item in the fuel slot
	 * @param type Fuel type of the machine
	 * @return boolean true if fuel is compatible else false
	 */
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
		if (this.isItemFuel(item)) {
			return this.itemBurnTime.get(item);
		}
		
		return 0;
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
