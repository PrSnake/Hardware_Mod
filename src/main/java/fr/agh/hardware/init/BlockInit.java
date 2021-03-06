package fr.agh.hardware.init;

import java.util.ArrayList;
import java.util.List;

import fr.agh.hardware.objects.blocks.BlockEnergyGenerator;
import fr.agh.hardware.objects.blocks.BlockOreBase;
import fr.agh.hardware.objects.blocks.BlockOreCrusher;
import net.minecraft.block.Block;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	/**
	 * Resistance :
	 *	- Ores : 15
	 *	- Obsidian : 6000
	 *	- Dirt : 2.5
	 *	- Stone/Cobblestone = 30
	 * Hardness :
	 *	- Ores : 3
	 *	- Obsidian : 50
	 *	- Dirt : 0.5
	 *	- Stone/Cobblestone = 1.5/2
	 */
	
	// Minerai
	/* TODO Add more of them !
	 * Silver
	 * Sulfure
	 * Lithium
	 * Nickel
	 */
	
	public static final Block ORE_COBALTITE = new BlockOreBase("block_ore_cobaltite", ItemInit.RAW_COBALTITE, 15.0F, 3.0F);
	public static final Block ORE_COPPER = new BlockOreBase("block_ore_copper", ItemInit.RAW_COPPER, 15.0F, 3.0F);
	public static final Block ORE_BAUXITE = new BlockOreBase("block_ore_bauxite", ItemInit.RAW_BAUXITE, 15.0F, 3.0F);
	public static final Block ORE_GALENA = new BlockOreBase("block_ore_galena", ItemInit.RAW_GALENA, 15.0F, 3.0F);
	public static final Block ORE_VANADIUM = new BlockOreBase("block_ore_vanadium", ItemInit.RAW_VANADIUM, 15.0F, 3.0F);
	public static final Block ORE_RUTILE = new BlockOreBase("block_ore_rutile", ItemInit.RAW_RUTILE, 15.0F, 3.0F);
	public static final Block ORE_WOLFRAMITE = new BlockOreBase("block_ore_wolframite", ItemInit.RAW_WOLFRAMITE, 15.0F, 3.0F);
	public static final Block ORE_SCHEELITE = new BlockOreBase("block_ore_scheelite", ItemInit.RAW_SCHEELITE, 15.0F, 3.0F);
	public static final Block ORE_BISMUTHINITE = new BlockOreBase("block_ore_bismuthinite", ItemInit.RAW_BISMUTHINITE, 15.0F, 3.0F);
	public static final Block ORE_URANIUM = new BlockOreBase("block_ore_uranium", ItemInit.RAW_URANIUM, 15.0F, 3.0F);
	public static final Block ORE_CASSITERITE = new BlockOreBase("block_ore_cassiterite", ItemInit.RAW_CASSITERITE, 15.0F, 3.0F);
	public static final Block ORE_SILVER = new BlockOreBase("block_ore_silver", ItemInit.RAW_SILVER, 15.0F, 3.0F);
	public static final Block ORE_GARNIERITE = new BlockOreBase("block_ore_garnierite", ItemInit.RAW_GARNIERITE, 15.0F, 3.0F);

	// Machines
	public static final Block MACHINES_ORE_CRUSHER = new BlockOreCrusher("block_ore_crusher", CreativeTabInit.TAB_MACHINES);
	public static final Block MACHINES_ENERGY_GENERATOR = new BlockEnergyGenerator("block_energy_generator", CreativeTabInit.TAB_MACHINES);
	
}
