package fr.agh.hardware.init;

import java.util.ArrayList;
import java.util.List;

import fr.agh.hardware.ModHardware;
import fr.agh.hardware.objects.blocks.BlockOreBase;
import fr.agh.hardware.objects.blocks.BlockOreCrusher;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// Minerai
	public static final Block ORE_COBALT = new BlockOreBase("block_ore_cobalt", ItemInit.RAW_COBALT);
	public static final Block ORE_COPPER = new BlockOreBase("block_ore_copper", ItemInit.RAW_COPPER);
	public static final Block ORE_BAUXITE = new BlockOreBase("block_ore_bauxite", ItemInit.RAW_BAUXITE);
	public static final Block ORE_GALENA = new BlockOreBase("block_ore_galena", ItemInit.RAW_GALENA);
	public static final Block ORE_VANADIUM = new BlockOreBase("block_ore_vanadium", ItemInit.RAW_VANADIUM);
	
	// Machines
	public static final Block MACHINES_ORE_CRUSHER = new BlockOreCrusher("block_ore_crusher", CreativeTabInit.TAB_MACHINES);
	
}
