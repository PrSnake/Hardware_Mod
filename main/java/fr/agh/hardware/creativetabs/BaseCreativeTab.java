package fr.agh.hardware.creativetabs;

import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.util.HardwareReference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseCreativeTab extends CreativeTabs {

	public final String NAME;
	private ItemStack icon = null;
	
	public BaseCreativeTab(String label, String image) {
		super(HardwareReference.MODID + "." + label);
		setBackgroundImageName(image);
		NAME = label;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack createIcon() {
		return icon.copy();
	}
	
	public void setIcon(ItemStack stack) {
		icon = stack;
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
