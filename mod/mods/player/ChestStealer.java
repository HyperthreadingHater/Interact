package us.interact.mod.mods.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;
import net.minecraft.inventory.ContainerChest;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.TimeHelper;

public class ChestStealer extends Mod {

	public ChestStealer() {
		super("ChestStealer", Keyboard.KEY_H, true, Category.PLAYER);
	}
	
	TimeHelper delay = new TimeHelper();
	
	@Override
	public void onUpdate() {
		if(mc.thePlayer.openContainer != null && mc.thePlayer.openContainer instanceof ContainerChest) {
			ContainerChest c = (ContainerChest)mc.thePlayer.openContainer;
			ArrayList<Integer> integers = new ArrayList<>();
			boolean empty = true;
			for(int i = 0; i < c.getLowerChestInventory().getSizeInventory(); i++) {
				if(c.getLowerChestInventory().getStackInSlot(i) != null) {
					integers.add(i);
					empty = false;
				}
			}
			for(int i : integers) {
				if(delay.isDelayCompleted((long) SettingsManager.getSettingByName("Delay", this).getValDouble())) {
					mc.playerController.windowClick(c.windowId, i, 0, 1, mc.thePlayer);
					delay.reset();
					if(c.getInventory().isEmpty()) {
						mc.displayGuiScreen(null);
					}
				}
			}
			if(empty) {
				mc.thePlayer.closeScreen();
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
