package fr.agh.hardware.objects.blocks;

import fr.agh.hardware.ModHardware;
import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.init.ItemInit;
import fr.agh.hardware.util.HardwareReference;
import fr.agh.hardware.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase  extends Block implements IHasModel  {

	public BlockBase(String name, CreativeTabs tab, Material material, float resistance, float hardness) {
		super(material);
		
		setTranslationKey(HardwareReference.MODID + "." + name);
		setRegistryName(HardwareReference.MODID, name);
		
		setResistance(resistance);
		setHardness(hardness);
		setCreativeTab(tab);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		ModHardware.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
