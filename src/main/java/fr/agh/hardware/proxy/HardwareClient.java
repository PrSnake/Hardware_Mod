package fr.agh.hardware.proxy;

import java.io.File;

import fr.agh.hardware.util.HardwareReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = HardwareReference.MOD_ID, value = Side.CLIENT)
public class HardwareClient extends HardwareCommon {
	
	/**
	 * Specify if the client is in a single player world
	 * or if he is in a multiplayer world.
	 * @return Returns true if there is only one player playing, 
	 * and the current server is the integrated one.
	 */
	@Override
	public boolean isSinglePlayer() {
		return Minecraft.getMinecraft().isSingleplayer();
	}

	/**
	 * Specify if it is a dedicated server or not.
	 * @return Can only return false
	 */
	@Override
	public boolean isDedicatedServer() {
		return false;
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
	 * Forge client pre-initialization
	 * @param event
	 */
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	/**
	 * Forge client initialization
	 * @param event
	 */
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	/**
	 * Forge client post-initialization
	 * @param event
	 */
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		
	}
	
	/**
	 * TESR : Tile Entity Special Renderers
	 */
	@Override
	public void registerRenderers() {
		
	}
}


























