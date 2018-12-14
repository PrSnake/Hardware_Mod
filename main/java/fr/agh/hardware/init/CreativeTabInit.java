package fr.agh.hardware.init;

import java.util.ArrayList;
import java.util.List;

import fr.agh.hardware.creativetabs.BaseCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabInit {
	
	public static final List<BaseCreativeTab> CREATIVE_TABS = new ArrayList<BaseCreativeTab>();
	
	public static final BaseCreativeTab MINERALS_TAB = new BaseCreativeTab("tab_hardware_minerals", "item_search.png", BlockInit.ORE_COPPER);
	public static final BaseCreativeTab TOOLS_TAB = new BaseCreativeTab("tab_hardware_tools", "item_search.png", BlockInit.ORE_GALENA);
	public static final BaseCreativeTab MACHINES_TAB = new BaseCreativeTab("tab_hardware_machines", "item_search.png", BlockInit.MACHINES_ORE_CRUSHER);
	public static final BaseCreativeTab COMPONENTS_TAB = new BaseCreativeTab("tab_hardware_components", "item_search.png", BlockInit.ORE_VANADIUM);
}
