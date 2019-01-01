package fr.agh.hardware.util.enums;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum FuelType {
	// Type(Name==Translation key)
	THERMIC("fueltype.hardware.thermic.name"),
	ELECTRIC("fueltype.hardware.electric.name"),
	NUCLEAR("fueltype.hardware.nuclear.name");
	
	private String translationKey = "";
	
	FuelType (String translationKey) {
		this.translationKey = translationKey;
	}
	
	public ITextComponent getFuelTypeDisplayName() {
		return new TextComponentTranslation(this.translationKey);
	}
}
