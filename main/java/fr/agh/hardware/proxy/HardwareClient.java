package fr.agh.hardware.proxy;

import java.io.File;

import fr.agh.hardware.util.HardwareReference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;


public class HardwareClient extends HardwareCommon {

	@Override
	public void preInit(File configFile) {
		super.preInit(configFile);
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		if (meta < 0) meta = 0;
		String resourceName = item.getTranslationKey().substring(5).replace('.', ':');
		if (meta > 0) resourceName += "_m" + String.valueOf(meta);
		
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(resourceName, id));
	}
	
	@Override
	public void registerVariantItemRenderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(HardwareReference.MODID, filename), id));
	}
}
