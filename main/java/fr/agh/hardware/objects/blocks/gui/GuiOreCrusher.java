package fr.agh.hardware.objects.blocks.gui;

import fr.agh.hardware.objects.blocks.containers.ContainerOreCrusher;
import fr.agh.hardware.objects.blocks.tileentities.TileEntityOreCrusher;
import fr.agh.hardware.util.HardwareReference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiOreCrusher extends GuiContainer{

	private static final ResourceLocation TEXTURES = new ResourceLocation(HardwareReference.MODID + ":textures/gui/container/block_ore_crusher.png");
	private final InventoryPlayer player;
	private final TileEntityOreCrusher tileentity;
	
	public GuiOreCrusher(InventoryPlayer player, TileEntityOreCrusher tileentity) {
		super(new ContainerOreCrusher(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		// display tile name at the center of the GUI, correct the offset if needed
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 6, 4210752);
		// display "inventory" at the left of the GUI
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// R-G-B-Alpha (alpha 0 -> transparent) (alpha 1 -> opaque) 
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityOreCrusher.isBurning(tileentity)) {
			int k = this.getBurnLeftScaled(16);
			this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 35 + 16 - k, 176, 16 - k, 16, k + 1);
		}
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 35, 176, 16, l + 1, 16);
	}
	
	// Flame animation progression
	private int getBurnLeftScaled(int pixels) {
		int i = this.tileentity.getField(1);
		if(i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	// Arrow animation progression
	private int getCookProgressScaled(int pixels) {
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

}
