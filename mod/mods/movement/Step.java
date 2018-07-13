package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.network.play.client.C03PacketPlayer;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.ingame.TimeHelper;

public class Step extends Mod {

	public Step() {
		super("Step", Keyboard.KEY_X, true, Category.MOVEMENT);
	}

	TimeHelper boost = new TimeHelper();
	TimeHelper jump = new TimeHelper();

	@Override
	public void onUpdate() {
		mc.thePlayer.stepHeight = 0.5F;
		if (!mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater()) {
			if (getMode().equalsIgnoreCase("LowHop")) {
				if (mc.thePlayer.isCollidedHorizontally && mc.gameSettings.keyBindForward.pressed) {
					
					if (boost.isDelayCompleted(300)) {
						mc.thePlayer.motionY = 0.41;
						boost.reset();
					}
					mc.thePlayer.posX *= 1.1;
					mc.thePlayer.posZ *= 1.1;
					mc.thePlayer.motionY *= 0.952155;
				}
			} else if (getMode().equalsIgnoreCase("NewAAC")) {
                if (mc.thePlayer.isCollidedHorizontally && mc.gameSettings.keyBindForward.pressed) {
                    if (mc.thePlayer.onGround) {
                        mc.thePlayer.motionY = 0.39;
                        mc.timer.timerSpeed = 1.1F;
                    }
                    mc.thePlayer.speedInAir = 0.07F;
                }else {
                    mc.thePlayer.speedInAir = 0.02F;
				}
			} else if (getMode().equalsIgnoreCase("Normal")) {
				mc.thePlayer.stepHeight = (float) SettingsManager.getSettingByName("Height", ModManager.getMod("Step")).getValDouble();
			}else if (getMode().equalsIgnoreCase("OldAAC")) {
				if(mc.thePlayer.isCollidedHorizontally) {
					mc.thePlayer.jump();
					mc.thePlayer.motionY = 0.42;
				}
				
			}
		}
		if(!mc.thePlayer.isCollidedHorizontally) {
			mc.timer.timerSpeed = 1.0F;
		}
		
	}

	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		mc.timer.timerSpeed = 1.0F;
		mc.thePlayer.stepHeight = 0.5F;
        mc.thePlayer.speedInAir = 0.02F;
	}

}
