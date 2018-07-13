package us.interact.mod.mods.movement;

import de.Hero.settings.SettingsManager;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Webwalk extends Mod {

	public Webwalk() {
		super("WebWalk", 0, true, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if(SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("NCP")) {
			if(mc.thePlayer.isInWeb) {
				mc.thePlayer.motionY = 0.0D;
				mc.thePlayer.isInWeb = false;
				mc.thePlayer.motionZ *= 0.6;
				mc.thePlayer.motionX *= 0.6;
				mc.gameSettings.keyBindSprint.pressed = false;
			}
		}else if(SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("AAC")) {
			if(mc.thePlayer.isInWeb) {
				mc.thePlayer.motionY = 0.0D;
				mc.thePlayer.isInWeb = false;
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
