package fr.agh.hardware.init;

import java.util.List;

import fr.agh.hardware.objects.items.ItemBase;
import fr.agh.hardware.ModHardware;

import java.util.ArrayList;
import net.minecraft.item.Item;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// Minerai brut
	// public static final Item RAW_ = new ItemBase("item_raw_", ModHardware.MINERALS_TAB);
	public static final Item RAW_COPPER = new ItemBase("item_raw_copper", CreativeTabInit.MINERALS_TAB);
	public static final Item RAW_COBALT = new ItemBase("item_raw_cobalt", CreativeTabInit.MINERALS_TAB);
	public static final Item RAW_BAUXITE = new ItemBase("item_raw_bauxite", CreativeTabInit.MINERALS_TAB);
	public static final Item RAW_GALENA = new ItemBase("item_raw_galena", CreativeTabInit.MINERALS_TAB);
	public static final Item RAW_VANADIUM = new ItemBase("item_raw_vanadium", CreativeTabInit.MINERALS_TAB);
	
	// Lingots
	// public static final Item INGOT_ = new ItemBase("item_ingot_", ModHardware.MINERALS_TAB);
	public static final Item INGOT_COPPER = new ItemBase("item_ingot_copper", CreativeTabInit.MINERALS_TAB);
	public static final Item INGOT_COBALT = new ItemBase("item_ingot_cobalt", CreativeTabInit.MINERALS_TAB);
	public static final Item INGOT_ALUMINIUM = new ItemBase("item_ingot_aluminium", CreativeTabInit.MINERALS_TAB);
	public static final Item INGOT_LEAD = new ItemBase("item_ingot_lead", CreativeTabInit.MINERALS_TAB);
	public static final Item INGOT_VANADIUM = new ItemBase("item_ingot_vanadium", CreativeTabInit.MINERALS_TAB);
	
	// Composant de machines
	// public static final Item  = new ItemBase("item_", ModHardware.COMPONENTS_TAB);
	public static final Item PLATE_IRON = new ItemBase("item_plate_iron", CreativeTabInit.COMPONENTS_TAB);
	public static final Item RODE_IRON = new ItemBase("item_rode_iron", CreativeTabInit.COMPONENTS_TAB);
	public static final Item GEARS_IRON = new ItemBase("item_gears_iron", CreativeTabInit.COMPONENTS_TAB);
	public static final Item GRINDER_IRON = new ItemBase("item_grinder_iron", CreativeTabInit.COMPONENTS_TAB);
}
