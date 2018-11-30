package fr.agh.hardware.world.gen.ores;

import java.util.Random;

import fr.agh.hardware.init.BlockInit;
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
	private WorldGenerator 	ore_overworld_copper,
							ore_overworld_cobalt,
							ore_overworld_vanadium,
							ore_overworld_bauxite,
							ore_overworld_galena;
	
	public WorldGenCustomOres() {
		ore_overworld_copper = new WorldGenMinable(BlockInit.ORE_COPPER.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_cobalt = new WorldGenMinable(BlockInit.ORE_COBALT.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_vanadium = new WorldGenMinable(BlockInit.ORE_VANADIUM.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_bauxite = new WorldGenMinable(BlockInit.ORE_BAUXITE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_galena = new WorldGenMinable(BlockInit.ORE_GALENA.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
			case -1:
				//runGenerator();
				break;
			case 0:
				runGenerator(ore_overworld_copper, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_cobalt, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_vanadium, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_bauxite, world, random, chunkX, chunkZ, 50, 0, 128);
				runGenerator(ore_overworld_galena, world, random, chunkX, chunkZ, 50, 0, 128);
				
				break;
			case 1:
				break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds");
		
		int heightDiff = maxHeight - minHeight +1;
		for(int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
}
