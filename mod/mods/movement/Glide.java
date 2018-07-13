package us.interact.mod.mods.movement;

import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.ingame.TimeHelper;
import us.interact.utils.other.Location;
import us.interact.utils.render.FontUtil;

public class Glide extends Mod {

	public Glide() {
		super("Glide", 0, true, Category.MOVEMENT);
	}

	TimeHelper time = new TimeHelper();

	@Override
	public void onUpdate() {
		if (getMode().equalsIgnoreCase("AAC")) {
			mc.timer.timerSpeed = 0.1f;
			if (time.isDelayCompleted(3500)) {
				time.reset();
				mc.thePlayer.motionY = 0.0;
			}
		}else if(getMode().equalsIgnoreCase("Vanilla")) {
			mc.thePlayer.motionY *= 0.3;
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();
	}

	@Override
	public void onDisable() {
		mc.timer.timerSpeed = 1f;
		super.onDisable();
	}

}
