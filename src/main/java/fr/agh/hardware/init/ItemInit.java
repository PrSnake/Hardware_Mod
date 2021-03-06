package fr.agh.hardware.init;

import java.util.List;
import java.util.ArrayList;

import net.minecraft.item.Item;

import fr.agh.hardware.objects.items.ItemBase;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// Minerai brut
	// public static final Item RAW_ = new ItemBase("item_raw_", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_COPPER = new ItemBase("item_raw_copper", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_COBALTITE = new ItemBase("item_raw_cobaltite", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_BAUXITE = new ItemBase("item_raw_bauxite", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_GALENA = new ItemBase("item_raw_galena", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_VANADIUM = new ItemBase("item_raw_vanadium", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_RUTILE = new ItemBase("item_raw_rutile", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_WOLFRAMITE = new ItemBase("item_raw_wolframite", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_SCHEELITE = new ItemBase("item_raw_scheelite", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_BISMUTHINITE = new ItemBase("item_raw_bismuthinite", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_URANIUM = new ItemBase("item_raw_uranium", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_CASSITERITE = new ItemBase("item_raw_cassiterite", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_SILVER = new ItemBase("item_raw_silver", CreativeTabInit.TAB_MINERALS);
	public static final Item RAW_GARNIERITE = new ItemBase("item_raw_garnierite", CreativeTabInit.TAB_MINERALS);

	// Lingots
	// public static final Item INGOT_ = new ItemBase("item_ingot_", CreativeTabInit.TAB_MINERALS);
	public static final Item INGOT_COPPER = new ItemBase("item_ingot_copper", CreativeTabInit.TAB_MINERALS);
	public static final Item INGOT_COBALT = new ItemBase("item_ingot_cobalt", CreativeTabInit.TAB_MINERALS);
	public static final Item INGOT_ALUMINIUM = new ItemBase("item_ingot_aluminium", CreativeTabInit.TAB_MINERALS);
	public static final Item INGOT_LEAD = new ItemBase("item_ingot_lead", CreativeTabInit.TAB_MINERALS);
	public static final Item INGOT_VANADIUM = new ItemBase("item_ingot_vanadium", CreativeTabInit.TAB_MINERALS);
	
	// Composant de machines
	// public static final Item  = new ItemBase("item_", CreativeTabInit.TAB_COMPONENTS);
	public static final Item PLATE_IRON = new ItemBase("item_plate_iron", CreativeTabInit.TAB_COMPONENTS);
	public static final Item RODE_IRON = new ItemBase("item_rode_iron", CreativeTabInit.TAB_COMPONENTS);
	public static final Item GEARS_IRON = new ItemBase("item_gears_iron", CreativeTabInit.TAB_COMPONENTS);
	public static final Item GRINDER_IRON = new ItemBase("item_grinder_iron", CreativeTabInit.TAB_COMPONENTS);
	
	// Upgrades
	// public static final Item  = new ItemBase("item_", ModHardware.TAB_MACHINES_UPGRADES);
	public static final Item ORE_CRUSHER_UPGRADE_FUEL_EFFICIENCY = new ItemBase("item_ore_crusher_upgrade_fuel_efficiency", CreativeTabInit.TAB_MACHINES_UPGRADES);
	public static final Item ORE_CRUSHER_UPGRADE_PRODUCTION = new ItemBase("item_ore_crusher_upgrade_production", CreativeTabInit.TAB_MACHINES_UPGRADES);
	public static final Item ORE_CRUSHER_UPGRADE_SPEED = new ItemBase("item_ore_crusher_upgrade_speed", CreativeTabInit.TAB_MACHINES_UPGRADES);

	
}
