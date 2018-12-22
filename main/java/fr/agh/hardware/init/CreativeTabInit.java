package fr.agh.hardware.init;

import java.util.ArrayList;
import java.util.List;

import fr.agh.hardware.creativetabs.BaseCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabInit {
	
	public static final List<BaseCreativeTab> CREATIVE_TABS = new ArrayList<BaseCreativeTab>();
	
	public static final BaseCreativeTab TAB_MINERALS = new BaseCreativeTab("tab_hardware_minerals", "item_search.png");
	public static final BaseCreativeTab TAB_TOOLS = new BaseCreativeTab("tab_hardware_tools", "item_search.png");
	public static final BaseCreativeTab TAB_MACHINES = new BaseCreativeTab("tab_hardware_machines", "item_search.png");
	public static final BaseCreativeTab TAB_COMPONENTS = new BaseCreativeTab("tab_hardware_components", "item_search.png");
	public static final BaseCreativeTab TAB_MACHINES_UPGRADES = new BaseCreativeTab("tab_hardware_machines_upgrades", "item_search.png");
	
	public static void applyCTabsIcons() {
		TAB_MINERALS.setIcon(new ItemStack(Item.getItemFromBlock(BlockInit.ORE_BAUXITE)));
		TAB_TOOLS.setIcon(new ItemStack(Items.DIAMOND_AXE));
		TAB_MACHINES.setIcon(new ItemStack(Item.getItemFromBlock(BlockInit.MACHINES_ORE_CRUSHER)));
		TAB_COMPONENTS.setIcon(new ItemStack(ItemInit.GEARS_IRON));
		TAB_MACHINES_UPGRADES.setIcon(new ItemStack(ItemInit.ORE_CRUSHER_UPGRADE_FUEL_EFFICIENCY));
	}
}
