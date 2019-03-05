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
@Mod(modid = HardwareReference.MOD_ID, name = HardwareReference.MOD_NAME, version = HardwareReference.MOD_VERSION, acceptedMinecraftVersions = HardwareReference.MC_VERSION)
public class ModHardware {
	
	@Mod.Instance(HardwareReference.MOD_ID)
	public static ModHardware instance;
	
	@SidedProxy(clientSide = HardwareReference.MOD_PROXY_CLIENT, serverSide = HardwareReference.MOD_PROXY_SERVER)
	public static HardwareCommon proxy;
	
	public static Logger logger;
	
	public ModHardware() {
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
	}

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event.getSuggestedConfigurationFile());
		
		RegistryHandler.preInitRegistries(event);
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init();
		RegistryHandler.initRegistries(event);
	}
	
	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries(event);
		CreativeTabInit.applyCTabsIcons();
	}
	
	@Mod.EventHandler
	public static void serverInit(FMLServerStartingEvent event) {
		RegistryHandler.serverRegistries(event);
	}
}
