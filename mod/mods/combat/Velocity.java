package us.interact.mod.mods.combat;

import de.Hero.settings.SettingsManager;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;

public class Velocity extends Mod {

	public Velocity() {
		super("Velocity", 0, true, Category.COMBAT);
	}
	
	public void onUpdate() {
		if(SettingsManager.getSettingByName("Mode", ModManager.getMod("Velocity")).getValString().equalsIgnoreCase("OldAAC")) {
			if(mc.thePlayer.hurtTime > 0) {
				if(mc.thePlayer.onGround)
					mc.thePlayer.jump();
				mc.thePlayer.motionX *= 0.3;
				mc.thePlayer.motionZ *= 0.3;
			}
		}else if(SettingsManager.getSettingByName("Mode", ModManager.getMod("Velocity")).getValString().equalsIgnoreCase("Reverse")) {
			if(mc.thePlayer.hurtTime > 0) {
				mc.thePlayer.speedInAir = 0.05F;
			}else {
				mc.thePlayer.speedInAir = 0.02F;
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
