package us.interact.mod.mods.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.ui.font.Fibel;
import us.interact.utils.render.ColorUtil;
import us.interact.utils.render.RenderHelper;

public class BlockOverlay extends Mod {

	public BlockOverlay() {
		super("BlockOverlay", Keyboard.KEY_NONE, true, Category.RENDER);
		setInConfig(false);
	}

	public void onRender() {
		try {
			BlockPos pos = mc.objectMouseOver.func_178782_a();
			Block block = mc.theWorld.getBlockState(pos).getBlock();
			if (block != null && block.getBlockState().getBlock() != Blocks.air && block.getBlockState().getBlock() != Blocks.water && block.getBlockState().getBlock() != Blocks.flowing_water && block.getBlockState().getBlock() != Blocks.lava && block.getBlockState().getBlock() != Blocks.flowing_lava) {
				if(getSetting("Rainbow"))
					RenderHelper.blockESP((pos), ColorUtil.rainbowEffect(0, 1));
				else 
					RenderHelper.blockESP((pos), ColorUtil.getHUDColor());
			}
		} catch (Exception e) {
		}

	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

}
