package us.interact.mod.mods.player;

import org.lwjgl.input.Keyboard;

import us.interact.mod.Category;
import us.interact.mod.Mod;

public class AntiCactus extends Mod{

	public AntiCactus() {
		super("AntiCactus", Keyboard.KEY_NONE, true, Category.PLAYER);
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
