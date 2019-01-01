package fr.agh.hardware;

import org.apache.logging.log4j.Logger;

import fr.agh.hardware.util.handlers.RegistryHandler;
import fr.agh.hardware.init.CreativeTabInit;
import fr.agh.hardware.proxy.HardwareCommon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import fr.agh.hardware.util.HardwareReference;

// TODO check the version in mods menu in game
@Mod(modid = HardwareReference.MODID, useMetadata = true, name = HardwareReference.NAME, version = HardwareReference.VERSION, acceptedMinecraftVersions = HardwareReference.MCVERSION)

public class ModHardware {
	
	@Instance(HardwareReference.MODID)
	public static ModHardware instance;
	
	@SidedProxy(clientSide = HardwareReference.CLIENT, serverSide = HardwareReference.SERVER)
	public static HardwareCommon proxy;
	
	public static Logger logger;
	
	public ModHardware() {
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event.getSuggestedConfigurationFile());
		
		RegistryHandler.preInitRegistries(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init();
		RegistryHandler.initRegistries(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries(event);
		CreativeTabInit.applyCTabsIcons();
	}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event) {
		RegistryHandler.serverRegistries(event);
	}
}
