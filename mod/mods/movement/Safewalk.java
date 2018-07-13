package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import us.interact.events.EventSafewalk;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Safewalk extends Mod {

	public Safewalk() {
		super("SafeWalk", 0, true, Category.MOVEMENT);
	}
	
	@EventTarget
	public void on(EventSafewalk event) {
		event.setSafe(true);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		EventManager.register(this);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		EventManager.unregister(this);
	}

}
