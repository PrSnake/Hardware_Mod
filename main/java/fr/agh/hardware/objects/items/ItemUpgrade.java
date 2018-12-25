package fr.agh.hardware.objects.items;

import fr.agh.hardware.ModHardware;
import fr.agh.hardware.init.CreativeTabInit;
import fr.agh.hardware.init.ItemInit;
import fr.agh.hardware.util.HardwareReference;
import fr.agh.hardware.util.enums.UpgradeType;
import fr.agh.hardware.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemUpgrade extends Item implements IHasModel {
	private final Block target;
	private final UpgradeType upType;
	private final int tier;
	
	public ItemUpgrade(String name, UpgradeType upType, int tier, Block target) {
		setUnlocalizedName(HardwareReference.MODID + "." + name);
		setRegistryName(HardwareReference.MODID, name);
		setCreativeTab(CreativeTabInit.TAB_COMPONENTS);
		setMaxStackSize(64);
		
		this.target = target;
		this.upType = upType;
		this.tier = tier;
		
		ItemInit.ITEMS.add(this);
	}
	
	public int getUpgradeTier() {
		return this.tier;
	}
	
	public UpgradeType getUpgradeType() {
		return this.upType;
	}
	
	public Block getTarget() {
		return this.target;
	}
	
	public boolean isUpgradeCompatibleWith(Block block) {
		return block == this.target;
	}
	
	@Override
	public void registerModels() {
		ModHardware.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
