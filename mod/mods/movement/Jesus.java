package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;

public class Jesus extends Mod {

	public Jesus() {
		super("Jesus", Keyboard.KEY_NONE, true, Category.MOVEMENT);
	}

	public void onUpdate() {

		if (getMode().equalsIgnoreCase("Vanilla")) {

			if (mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY + 0.0001, mc.thePlayer.posZ)).getBlock().getMaterial() == Material.water
					|| mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY + 0.0001, mc.thePlayer.posZ)).getBlock().getMaterial() == Material.lava) {
				mc.thePlayer.motionY = 0;
				mc.thePlayer.motionX *= 0.99F;
				mc.thePlayer.motionZ *= 0.99F;
				if (mc.gameSettings.keyBindJump.pressed) {
					mc.thePlayer.motionY += 0.1F;
				}
				if (mc.gameSettings.keyBindSneak.pressed) {
					mc.thePlayer.motionY -= 0.1F;
				}
				if (mc.thePlayer.isCollidedHorizontally) {
					mc.thePlayer.motionY += 0.1F;
				}
			}
		}else if (getMode().equalsIgnoreCase("NCP")) {
			if (mc.thePlayer.isInWater()) {
				mc.thePlayer.motionY = 0.01F;
				mc.thePlayer.motionX *= 1.0;
				mc.thePlayer.motionZ *= 1.0;
				mc.timer.timerSpeed = 1.1F;
			}
		}else if (getMode().equalsIgnoreCase("NCP2")) {
			if (mc.thePlayer.isInWater()) {
				mc.thePlayer.motionY = 0;
				mc.timer.timerSpeed = 1.1F;
			}
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();
	}

	@Override
	public void onDisable() {
		mc.timer.timerSpeed = 1.0F;
		super.onDisable();
	}

}