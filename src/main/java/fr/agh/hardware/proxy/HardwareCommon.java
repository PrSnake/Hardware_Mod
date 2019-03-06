package fr.agh.hardware.proxy;

import java.io.File;

import fr.agh.hardware.util.HardwareReference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * This class is the server side, it is named common
 * because the client proxy class extends this class.
 * Also HardwareCommon implements IHardwareProxy.
 * 
 * @author PrSnake
 */
@EventBusSubscriber(modid = HardwareReference.MOD_ID)
public class HardwareCommon implements IHardwareProxy {
	
	@Override
	public boolean isSinglePlayer() {
		return false;
	}

	@Override
	public boolean isDedicatedServer() {
		return true;
	}
	
	/**
	 * Adds a simple mapping from Item + metadata to the model variant.
	 * @param item specify the Item wich you would like to register it's model
	 * @param meta metadata of the Item wich you would like to register it's model
	 * @param id variant of the ressource
	 */
	public void registerItemRenderer(Item item, int meta, String id) {
		if (meta < 0) meta = 0;
		String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
		if (meta > 0) resourceName += "_m" + String.valueOf(meta);
		
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(resourceName, id));
	}
	
	/**
	 * Register a new variant of a custom model
	 * @param item specify the Item wich you would like to register it's model
	 * @param meta metadata of the Item wich you would like to register it's model
	 * @param filename name of the JSON where you have information about the model
	 * @param id variant of the ressource
	 */
	public void registerVariantItemRenderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(HardwareReference.MOD_ID, filename), id));
	}
	
	/**
	 * Forge server pre-initialization
	 * @param event
	 */
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	/**
	 * Forge server initialization
	 * @param event
	 */
	@Override
	public void init(FMLInitializationEvent event) {
		
	}
	
	/**
	 * Forge server post-initialization
	 * @param event
	 */
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	/**
	 * TESR : Tile Entity Special Renderers
	 */
	public void registerRenderers() {
		
	}
}




















