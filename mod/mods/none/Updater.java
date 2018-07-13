package us.interact.mod.mods.none;

import java.net.UnknownHostException;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.network.OldServerPinger;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.mod.mods.movement.SlimeJump;
import us.interact.ui.font.Bebas;
import us.interact.ui.font.Verdana;
import us.interact.utils.ingame.TimeHelper;

public class Updater extends Mod {
	
	private final OldServerPinger oldServerPinger = new OldServerPinger();

	public Updater() {
		super("Updater", Keyboard.KEY_NONE, false, Category.RENDER);
		setToggleable(false);
	}
	
	TimeHelper time = new TimeHelper();
	
	@Override
	public void onUpdate() {
		mc.gameSettings.ofFastRender = false;
	}
	
	@Override
	public void onTick() {
		for(Mod m : ModManager.getMods()) {
			if(m.isEnabled()) {
				m.setFade(m.getFade() + 6);
        		if(m.getFade() > 100)
        			m.setFade(100);
			}else {
				String font = SettingsManager.getSettingByName("FontMode", ModManager.getMod("ArrayList")).getValString();
				String s = getName() + (SettingsManager.getSettingByName("Info", ModManager.getMod("ArrayList")).getValBoolean() && (SettingsManager.getSettingByName("Mode", this) != null && SettingsManager.getSettingByName("Mode", this).isCombo()) ? " [" + SettingsManager.getSettingByName("Mode", this).getValString().toUpperCase() + "]" : "");
				if(getName().equals("KillAura"))
					s += " (2)";
				
				m.setFade(m.getFade() - 6);
        		if(m.getFade() < (font.equalsIgnoreCase("Mc") ? mc.fontRendererObj.getStringWidth(s) : font.equalsIgnoreCase("Bebas") ? Bebas.small.getStringWidth(s) : Verdana.small.getStringWidth(s)))
        			m.setFade(0);
			}
		}
	}

}
