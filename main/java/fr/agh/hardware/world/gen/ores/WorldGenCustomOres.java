package fr.agh.hardware.world.gen.ores;

import java.util.Random;

import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.objects.blocks.BlockOreBase;
import fr.agh.hardware.util.enums.MetaDimension;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator {
	private WorldGenerator ore_overworld_copper;
	private WorldGenerator ore_nether_copper;
	private WorldGenerator ore_end_copper;
	
	private WorldGenerator ore_overworld_cobalt;
	private WorldGenerator ore_nether_cobalt;
	private WorldGenerator ore_end_cobalt;
	
	private WorldGenerator ore_overworld_vanadium;
	private WorldGenerator ore_nether_vanadium;
	private WorldGenerator ore_end_vanadium;
	
	private WorldGenerator ore_overworld_bauxite;
	private WorldGenerator ore_nether_bauxite;
	private WorldGenerator ore_end_bauxite;
	
	private WorldGenerator ore_overworld_galena;
	private WorldGenerator ore_nether_galena;
	private WorldGenerator ore_end_galena;
	
	private WorldGenerator ore_overworld_rutile;
	private WorldGenerator ore_nether_rutile;
	private WorldGenerator ore_end_rutile;
	
	public WorldGenCustomOres() {
		ore_overworld_copper = new WorldGenMinable(BlockInit.ORE_COPPER.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.OVERWORLD), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_nether_copper = new WorldGenMinable(BlockInit.ORE_COPPER.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.NETHER), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_end_copper = new WorldGenMinable(BlockInit.ORE_COPPER.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.END), 8, BlockMatcher.forBlock(Blocks.END_STONE));
		
		ore_overworld_cobalt = new WorldGenMinable(BlockInit.ORE_COBALT.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.OVERWORLD), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_nether_cobalt = new WorldGenMinable(BlockInit.ORE_COBALT.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.NETHER), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_end_cobalt = new WorldGenMinable(BlockInit.ORE_COBALT.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.END), 8, BlockMatcher.forBlock(Blocks.END_STONE));
		
		ore_overworld_vanadium = new WorldGenMinable(BlockInit.ORE_VANADIUM.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.OVERWORLD), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_nether_vanadium = new WorldGenMinable(BlockInit.ORE_VANADIUM.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.NETHER), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_end_vanadium = new WorldGenMinable(BlockInit.ORE_VANADIUM.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.END), 8, BlockMatcher.forBlock(Blocks.END_STONE));
		
		ore_overworld_bauxite = new WorldGenMinable(BlockInit.ORE_BAUXITE.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.OVERWORLD), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_nether_bauxite = new WorldGenMinable(BlockInit.ORE_BAUXITE.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.NETHER), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_end_bauxite = new WorldGenMinable(BlockInit.ORE_BAUXITE.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.END), 8, BlockMatcher.forBlock(Blocks.END_STONE));
		
		ore_overworld_galena = new WorldGenMinable(BlockInit.ORE_GALENA.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.OVERWORLD), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_nether_galena = new WorldGenMinable(BlockInit.ORE_GALENA.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.NETHER), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_end_galena = new WorldGenMinable(BlockInit.ORE_GALENA.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.END), 8, BlockMatcher.forBlock(Blocks.END_STONE));
		
		ore_overworld_rutile = new WorldGenMinable(BlockInit.ORE_RUTILE.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.OVERWORLD), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_nether_rutile = new WorldGenMinable(BlockInit.ORE_RUTILE.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.NETHER), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_end_rutile = new WorldGenMinable(BlockInit.ORE_RUTILE.getDefaultState().withProperty(BlockOreBase.VARIANT, MetaDimension.END), 8, BlockMatcher.forBlock(Blocks.END_STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
			case -1: // Nether
				runGenerator(ore_nether_copper, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_nether_cobalt, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_nether_vanadium, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_nether_bauxite, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_nether_galena, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_nether_rutile, world, random, chunkX, chunkZ, 50, 0, 256);
				break;
			case 0: // Overworld
				runGenerator(ore_overworld_copper, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_cobalt, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_vanadium, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_bauxite, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_galena, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_rutile, world, random, chunkX, chunkZ, 50, 0, 128);
				break;
			case 1: // End
				runGenerator(ore_end_copper, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_end_cobalt, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_end_vanadium, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_end_bauxite, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_end_galena, world, random, chunkX, chunkZ, 50, 0, 256);
				runGenerator(ore_end_rutile, world, random, chunkX, chunkZ, 50, 0, 256);
				break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) {
			throw new IllegalArgumentException("Ore generated out of bounds");
		}
		
		int heightDiff = maxHeight - minHeight +1;
		
		for (int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
}










































