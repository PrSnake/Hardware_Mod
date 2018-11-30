package fr.agh.hardware.creativetabs;

import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.util.HardwareReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ComponentsTab extends CreativeTabs{
public static final String NAME = "tab_hardware_components";
	
	public ComponentsTab() {
		super(HardwareReference.MODID + "." + NAME);
		setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(BlockInit.ORE_BAUXITE));
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
