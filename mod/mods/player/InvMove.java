package us.interact.mod.mods.player;

import org.lwjgl.input.Keyboard;

import de.Hero.clickgui.ClickGUI;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class InvMove extends Mod {

	public InvMove() {
		super("InvMove", 0, true, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		try {
			if((mc.inGameHasFocus) || (mc.currentScreen instanceof GuiChat))
				return;
			
			KeyBinding[] keys = new KeyBinding[] {
					mc.gameSettings.keyBindSprint,
					mc.gameSettings.keyBindForward,
					mc.gameSettings.keyBindBack,
					mc.gameSettings.keyBindLeft,
					mc.gameSettings.keyBindRight,
					mc.gameSettings.keyBindJump
			};
			
			for(KeyBinding b : keys) {
				b.setKeyBindState(b.getKeyCode(), Keyboard.isKeyDown(b.getKeyCode()));
			}
		} catch (Exception e) {}
		
	}
	
	private boolean isKeyDown(int key) {
		return Keyboard.isKeyDown(key);
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
