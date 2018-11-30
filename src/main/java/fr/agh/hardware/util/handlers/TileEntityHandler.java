package fr.agh.hardware.util.handlers;

import fr.agh.hardware.objects.blocks.tileentities.TileEntityOreCrusher;
import fr.agh.hardware.util.HardwareReference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityOreCrusher.class, new ResourceLocation(HardwareReference.MODID + ":block_ore_crusher"));
	}
}
