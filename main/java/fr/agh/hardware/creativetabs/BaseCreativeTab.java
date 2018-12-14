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
	private Block icon = null;
	
	public BaseCreativeTab(String label, String image, Block block) {
		super(HardwareReference.MODID + "." + label);
		setBackgroundImageName(image);
		NAME = label;
		icon = block;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack createIcon() {
		return new ItemStack(Item.getItemFromBlock(icon));
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
