package us.interact.mod.mods.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.render.RenderHelper;

public class BedESP extends Mod {

	public BedESP() {
		super("BedESP", Keyboard.KEY_NONE, true, Category.RENDER);
	}

	public void onRender() {
		int playerX = (int) this.mc.thePlayer.posX;
		int playerZ = (int) this.mc.thePlayer.posZ;
		int playerY = (int) this.mc.thePlayer.posY;
		int range = (int) SettingsManager.getSettingByName("Radius", this).getValDouble();
		for (int y = playerY - range; y <= playerY + range; y++) {
			for (int x = playerX - range; x <= playerX + range; x++) {
				for (int z = playerZ - range; z <= playerZ + range; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					if ((this.mc.theWorld.getBlockState(pos).getBlock() == Blocks.bed
							|| this.mc.theWorld.getBlockState(pos).getBlock() == Blocks.dragon_egg
							|| this.mc.theWorld.getBlockState(pos).getBlock() == Blocks.cake)) {

						if (pos != null && mc.theWorld.getBlockState(pos).getBlock() != Blocks.air) {
							RenderHelper.blockOutlineESP(pos, Color.RED);
						}
					}
				}
			}
		}
	}

}
