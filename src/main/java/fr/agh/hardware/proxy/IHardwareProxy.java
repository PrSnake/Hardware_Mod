package fr.agh.hardware.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IHardwareProxy {
	
	public void preInit(FMLPreInitializationEvent event);
	
	public void init(FMLInitializationEvent event);
	
	public void postInit(FMLPostInitializationEvent event);
	
	/**
	 * Inform you if you are in single player mode or not
	 * @return true if the instance is in single player, false if it is a dedicated server or if client is connected on a dedicated server
	 */
	boolean isSinglePlayer();
	
	/**
	 * inform you if you are a dedicated server
	 * @return true if the instance is a dedicated server
	 */
	boolean isDedicatedServer();
}
