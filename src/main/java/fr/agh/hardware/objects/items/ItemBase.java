package fr.agh.hardware.objects.items;

import fr.agh.hardware.init.ItemInit;
import fr.agh.hardware.util.HardwareReference;
import fr.agh.hardware.util.interfaces.IHasModel;
import fr.agh.hardware.ModHardware;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name, CreativeTabs tab) {
		setUnlocalizedName(HardwareReference.MOD_ID + "." + name);
		setRegistryName(HardwareReference.MOD_ID, name);
		setCreativeTab(tab);
		setMaxStackSize(64);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		ModHardware.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
