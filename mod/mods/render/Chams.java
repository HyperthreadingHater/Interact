package us.interact.mod.mods.render;

import org.lwjgl.input.Keyboard;

import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Chams extends Mod {

	public Chams() {
		super("Chams", Keyboard.KEY_NONE, true, Category.RENDER);
		setInConfig(false);
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
