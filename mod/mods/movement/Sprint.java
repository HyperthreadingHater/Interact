package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Sprint extends Mod {

	public Sprint() {
		super("Sprint", Keyboard.KEY_NONE, true, Category.MOVEMENT);
	}

	public void onUpdate() {
		if ((!this.mc.thePlayer.isCollidedHorizontally) && (this.mc.thePlayer.moveForward > 0.0F)
				&& (!this.mc.thePlayer.isSneaking())) {
			this.mc.thePlayer.setSprinting(true);
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
