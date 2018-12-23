package fr.agh.hardware.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	public static enum EnumType implements IStringSerializable {
		
		OVERWORLD(0, "overworld"),
		NETHER(1, "nether"),
		END(2, "end");
		
		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;
		
		private EnumType(int meta, String name) {
			this(meta, name, name);
		}
		
		private EnumType(int meta, String name, String unlocalizedName) {
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
		
		public static EnumType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for (EnumType enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}
