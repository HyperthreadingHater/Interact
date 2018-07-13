package us.interact.mod.mods.movement;

import us.interact.mod.Category;
import us.interact.mod.Mod;

public class FastLadder extends Mod {

	public FastLadder() {
		super("FastLadder", 0, true, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if(getMode().equalsIgnoreCase("AAC")) {
			if(mc.thePlayer.isOnLadder() && mc.gameSettings.keyBindForward.pressed) {
				mc.thePlayer.motionY *= 1.4;
			}
		}else if(getMode().equalsIgnoreCase("Vanilla")) {
			if(mc.thePlayer.isOnLadder() && mc.gameSettings.keyBindForward.pressed) {
				mc.thePlayer.motionY *= 8;
			}
		}else if(getMode().equalsIgnoreCase("NCP")) {
			if(mc.thePlayer.isOnLadder() && mc.gameSettings.keyBindForward.pressed) {
				mc.thePlayer.motionY *= 1.11;
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
