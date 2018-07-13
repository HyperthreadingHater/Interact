package us.interact.mod.mods.render;

import org.lwjgl.input.Keyboard;

import us.interact.mod.Category;
import us.interact.mod.Mod;

public class ArrayList extends Mod{

	public ArrayList() {
		super("ArrayList", Keyboard.KEY_NONE, true, Category.RENDER);
		setInArrayList(false);
		setToggleable(false);
		setInConfig(false);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}

}
