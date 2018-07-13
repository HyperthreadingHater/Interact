package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;

public class CustomSpeed extends Mod {

	public CustomSpeed() {
		super("CustomSpeed", Keyboard.KEY_NONE, true, Category.MOVEMENT);
	}

	public void onUpdate() {

		if (getSetting("Sprint")) {
			mc.thePlayer.setSprinting(true);
		}
		mc.timer.timerSpeed = 1F;
		mc.thePlayer.speedInAir = 0.02F;

		if (getSetting("Move") && !mc.gameSettings.keyBindForward.pressed && !mc.gameSettings.keyBindBack.pressed && !mc.gameSettings.keyBindLeft.pressed && !mc.gameSettings.keyBindRight.pressed)
			return;

		mc.timer.timerSpeed = (float) SettingsManager.getSettingByName("Timer", ModManager.getMod("CustomSpeed"))
				.getValDouble();
		mc.thePlayer.speedInAir = (float) SettingsManager
				.getSettingByName("SpeedInAir", ModManager.getMod("CustomSpeed")).getValDouble();
		PlayerHelper.motionXZ(SettingsManager.getSettingByName("XZ", ModManager.getMod("CustomSpeed")).getValDouble());

		if (mc.thePlayer.onGround) {
			mc.thePlayer.motionY += SettingsManager.getSettingByName("Y+", ModManager.getMod("CustomSpeed"))
					.getValDouble();
		} else {
			mc.thePlayer.motionY -= SettingsManager.getSettingByName("Y-", ModManager.getMod("CustomSpeed"))
					.getValDouble();
		}

	}

	public void onDisable() {
		mc.timer.timerSpeed = 1.0F;
		mc.thePlayer.speedInAir = 0.02F;
		super.onDisable();
	}

}
