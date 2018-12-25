package fr.agh.hardware.util.enums;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum UpgradeType {
	// Type(Name==Translation key)
	SPEED("upgradetype.hardware.speed.name"),
	PRODUCTION("upgradetype.hardware.production.name"),
	FUEL("upgradetype.hardware.fuel.name");
	
	private String translationKey = "";
	
	UpgradeType (String translationKey) {
		this.translationKey = translationKey;
	}
	
	public ITextComponent getFuelTypeDisplayName(){
		return new TextComponentTranslation(this.translationKey);
	}
}
