package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.settings.GameSettings;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;

public class NoSlow extends Mod {

	public NoSlow() {
		super("NoSlow", Keyboard.KEY_NONE, true, Category.MOVEMENT);
	}

	public void onUpdate() {

		if (getMode().equalsIgnoreCase("AAC")) {

			if (!mc.thePlayer.onGround || mc.thePlayer.isInWater() || ModManager.getMod("SafeWalk").isEnabled() || ModManager.getMod("Speed").isEnabled())
				return;

			if (mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem()) {
				if(mc.gameSettings.keyBindForward.pressed) {
					mc.thePlayer.motionX *= 1.6;
					mc.thePlayer.motionZ *= 1.6;
				}else {
					mc.thePlayer.motionX *= 0;
					mc.thePlayer.motionZ *= 0;
				}
			}
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
