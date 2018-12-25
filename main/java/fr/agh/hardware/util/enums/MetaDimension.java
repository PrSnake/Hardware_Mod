package fr.agh.hardware.util.enums;

import net.minecraft.util.IStringSerializable;

public enum MetaDimension implements IStringSerializable {
	OVERWORLD(0, "overworld"),
	NETHER(1, "nether"),
	END(2, "end");
	
	private static final MetaDimension[] META_LOOKUP = new MetaDimension[values().length];
	private final int meta;
	private final String name;
	private final String unlocalizedName;
	
	private MetaDimension(int meta, String name) {
		this(meta, name, name);
	}
	
	private MetaDimension(int meta, String name, String unlocalizedName) {
		this.meta = meta;
		this.name = name;
		this.unlocalizedName = unlocalizedName;
	}
	
	public int getMeta() {
		return this.meta;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static MetaDimension byMetadata(int meta) {
		return META_LOOKUP[meta];
	}
	
	static {
		for (MetaDimension metadimension : values()) {
			META_LOOKUP[metadimension.getMeta()] = metadimension;
		}
	}
}
