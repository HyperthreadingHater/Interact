package us.interact.mod.mods.misc;

import java.util.Random;

import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.TimeHelper;

public class Spammer extends Mod {

	public Spammer() {
		super("Spammer", 0, true, Category.MISC);
	}
	
	private static String message = "@a Interact Client by FinalCode & ChairPotato";
	
	private TimeHelper delay = new TimeHelper();
	
	@Override
	public void onUpdate() {
		if(delay.isDelayCompleted((long) getDelay())) {
			delay.reset();
			mc.thePlayer.sendChatMessage(message + " | " + new Random().nextInt(1000));
		}
	}
	
	public static void setMessage(String message) {
		Spammer.message = message;
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
