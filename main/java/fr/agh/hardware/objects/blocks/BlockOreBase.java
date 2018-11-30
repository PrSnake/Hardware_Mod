package fr.agh.hardware.objects.blocks;

import java.util.Random;

import fr.agh.hardware.ModHardware;
import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.init.ItemInit;
import fr.agh.hardware.util.HardwareReference;
import fr.agh.hardware.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockOreBase extends Block implements IHasModel {

	private static final Material MATERIAL = Material.ROCK;
	private Item loot = null;
	
	public BlockOreBase(String name, Item item) {
		super(MATERIAL);
		
		loot = item;
		
		setUnlocalizedName(HardwareReference.MODID + "." + name);
		setRegistryName(HardwareReference.MODID, name);
		
		setResistance(5.0F);
		setHardness(3.0F);
		setCreativeTab(ModHardware.MINERALS_TAB);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	/**
     * Get the Item that this Block should drop when harvested.
     */
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return loot;
	}
	
	/**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1 + random.nextInt(2);
    }

	@Override
	public void registerModels() {
		ModHardware.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}
