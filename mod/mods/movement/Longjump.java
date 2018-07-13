package us.interact.mod.mods.movement;

import de.Hero.settings.SettingsManager;
import net.minecraft.util.Timer;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;

public class Longjump extends Mod {

	public Longjump() {
		super("LongJump", 0, true, Category.MOVEMENT);
	}
	
	boolean wasToggled = false, performed = false;

	@Override
	public void onUpdate() {
		if(!performed) {
			if(getMode().equalsIgnoreCase("CubeCraft")) {
				wasToggled = mc.gameSettings.keyBindForward.pressed;
				mc.gameSettings.keyBindForward.pressed = true;
				mc.timer.timerSpeed = 0.3f;
				double value = 1 + (SettingsManager.getSettingByName("Value", ModManager.getMod("LongJump")).getValDouble() / 10);
				PlayerHelper.motionXZ(value);
			}
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();
		if (mc.thePlayer != null && !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround) {
			mc.thePlayer.jump();
		}
	}

	@Override
	public void onDisable() {
		if(getMode().equalsIgnoreCase("CubeCraft")) {
			PlayerHelper.motionXZ(0.1);
			super.onDisable();
			mc.gameSettings.keyBindForward.pressed = wasToggled;
			Timer.timerSpeed = 1;
		}
	}

}
