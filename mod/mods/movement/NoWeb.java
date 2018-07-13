package us.interact.mod.mods.movement;

import de.Hero.settings.SettingsManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class NoWeb extends Mod {

	public NoWeb() {
		super("NoWeb", 0, true, Category.MOVEMENT);
	}

	@Override
	public void onUpdate() {
		if (SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("Vanilla")) {
			if (mc.thePlayer.isInWeb) {
				mc.thePlayer.isInWeb = false;
			}
		} else if (SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("AAC")) {
			if (mc.thePlayer.isInWeb) {
				mc.thePlayer.isInWeb = false;
				mc.thePlayer.motionX *= 0.22;
				mc.thePlayer.motionZ *= 0.22;
			}
		} else if (SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("NCP")) {
			if (mc.thePlayer.isInWeb) {
				mc.thePlayer.isInWeb = false;
				if (mc.thePlayer.onGround)
					mc.thePlayer.motionY = 0.075;
				else
					mc.thePlayer.motionY = 0.0D;
				mc.thePlayer.motionX *= 0.5;
				mc.thePlayer.motionZ *= 0.5;
				mc.gameSettings.keyBindSprint.pressed = false;
			}
		} else if (SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("AAC2")) {
			if (this.mc.thePlayer.isInWeb) {
				if (this.mc.thePlayer.onGround) {
					this.mc.thePlayer.motionY = 2.0D;
				} else {
					this.mc.thePlayer.isInWeb = false;
					this.mc.thePlayer.motionY = 0.0D;
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
