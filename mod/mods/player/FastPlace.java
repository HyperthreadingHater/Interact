package us.interact.mod.mods.player;

import org.lwjgl.input.Keyboard;

import us.interact.mod.Category;
import us.interact.mod.Mod;

public class FastPlace extends Mod {

	public FastPlace() {
		super("FastPlace", Keyboard.KEY_NONE, true, Category.PLAYER);
	}

	public void onUpdate() {
		mc.rightClickDelayTimer = 0;
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
