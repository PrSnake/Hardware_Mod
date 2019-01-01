package fr.agh.hardware.util.handlers;

import fr.agh.hardware.objects.blocks.containers.ContainerOreCrusher;
import fr.agh.hardware.objects.blocks.gui.GuiOreCrusher;
import fr.agh.hardware.objects.blocks.tileentities.TileEntityOreCrusher;
import fr.agh.hardware.util.HardwareReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID == HardwareReference.GUI_ORE_CRUSHER) return new ContainerOreCrusher(player.inventory, (TileEntityOreCrusher)world.getTileEntity(new BlockPos(x,y,z)));
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID == HardwareReference.GUI_ORE_CRUSHER) return new GuiOreCrusher(player.inventory, (TileEntityOreCrusher)world.getTileEntity(new BlockPos(x,y,z)));
		
		return null;
	}

}
