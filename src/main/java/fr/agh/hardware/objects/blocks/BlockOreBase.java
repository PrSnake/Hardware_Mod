package fr.agh.hardware.objects.blocks;

import java.util.Random;

import fr.agh.hardware.ModHardware;
import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.init.CreativeTabInit;
import fr.agh.hardware.init.ItemInit;
import fr.agh.hardware.objects.blocks.item.ItemBlockVariants;
import fr.agh.hardware.util.HardwareReference;
import fr.agh.hardware.util.enums.MetaDimension;
import fr.agh.hardware.util.interfaces.IHasModel;
import fr.agh.hardware.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockOreBase extends Block implements IHasModel, IMetaName {
	
	public static final PropertyEnum<MetaDimension> VARIANT = PropertyEnum.<MetaDimension>create("variant", MetaDimension.class);
	
	private String name;
	private Item loot = null;
	
	public BlockOreBase(String name, Item item, float resistance, float hardness) {
		super(Material.ROCK);
		
		this.setUnlocalizedName(HardwareReference.MODID + "." + name);
		this.setRegistryName(HardwareReference.MODID, name);
		
		this.setSoundType(blockSoundType.STONE);
		
		this.setResistance(resistance);
		this.setHardness(hardness);
		
		this.setCreativeTab(CreativeTabInit.TAB_MINERALS);
		
		this.name = name;
		this.loot = item;
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.loot;
	}
	
	/**
	 * Get the quantity dropped based on the given fortune level
	 */
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random random)
	{
		return 1 + random.nextInt(2);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return (state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, MetaDimension.byMetadata(meta));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (MetaDimension variant : MetaDimension.values()) {
			items.add(new ItemStack(this, 1, variant.getMeta()));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}

	@Override
	public void registerModels() {
		
		for (int i = 0; i < MetaDimension.values().length; i++) {
			ModHardware.proxy.registerVariantItemRenderer(Item.getItemFromBlock(this), i, this.name + "_" + MetaDimension.values()[i].getName(), "inventory");
		}
		// ModHardware.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); // Without variants
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return MetaDimension.values()[stack.getItemDamage()].getName();
	}

}
