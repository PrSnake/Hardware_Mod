package fr.agh.hardware.util.handlers;

import fr.agh.hardware.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
	
	public static void registerSmelting() {
		
		// Lingots
		GameRegistry.addSmelting(ItemInit.RAW_BAUXITE, new ItemStack(ItemInit.INGOT_ALUMINIUM), 10);
		GameRegistry.addSmelting(ItemInit.RAW_COBALTITE, new ItemStack(ItemInit.INGOT_COBALT), 10);
		GameRegistry.addSmelting(ItemInit.RAW_COPPER, new ItemStack(ItemInit.INGOT_COPPER), 10);
		GameRegistry.addSmelting(ItemInit.RAW_GALENA, new ItemStack(ItemInit.INGOT_LEAD), 10);
		GameRegistry.addSmelting(ItemInit.RAW_VANADIUM, new ItemStack(ItemInit.INGOT_VANADIUM), 10);
	}
}
