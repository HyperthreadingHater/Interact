package us.interact.mod;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import us.interact.mod.mods.combat.KillAura;
import us.interact.ui.font.Bebas;
import us.interact.ui.font.Verdana;

public abstract class Mod {

	private String name;
	private int keyBind;
	private double fade;
	private boolean visible, inArrayList, enabled, toggleable, inConfig;
	private Category category;

	public Minecraft mc = Minecraft.getMinecraft();

	public Mod(String name, int keyBind, boolean visible, Category category) {
		this.name = name;
		this.keyBind = keyBind;
		this.visible = visible;
		this.category = category;
		this.enabled = false;
		this.inArrayList = true;
		this.toggleable = true;
		this.inConfig = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKeyBind() {
		return keyBind;
	}

	public void setKeyBind(int keyBind) {
		this.keyBind = keyBind;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isInArrayList() {
		return inArrayList;
	}

	public void setInArrayList(boolean inArrayList) {
		this.inArrayList = inArrayList;
	}

	public boolean isToggleable() {
		return toggleable;
	}

	public void setToggleable(boolean toggleable) {
		this.toggleable = toggleable;
	}
	
	public boolean isInConfig() {
		return inConfig;
	}
	
	public void setInConfig(boolean inConfig) {
		this.inConfig = inConfig;
	}
	
	public double getFade() {
		return fade;
	}
	
	public void setFade(double fade) {
		this.fade = fade;
	}
	
	public String getMode() {
		return SettingsManager.getSettingByName("Mode", this).getValString();
	}
	
	public long getDelay() {
		return (long)SettingsManager.getSettingByName("Delay", this).getValDouble();
	}
	
	public boolean getSetting(String name) {
		return SettingsManager.getSettingByName(name, this).getValBoolean();
	}

	public void toggle() {
		if (isEnabled()) {
			setEnabled(false);
			onDisable();
		} else {
			setEnabled(true);
			onEnable();
		}
	}

	public void onEnable() {
		try {
			String font = SettingsManager.getSettingByName("FontMode", ModManager.getMod("ArrayList")).getValString();
			String s = getName() + (SettingsManager.getSettingByName("Info", ModManager.getMod("ArrayList")).getValBoolean() && (SettingsManager.getSettingByName("Mode", this) != null && SettingsManager.getSettingByName("Mode", this).isCombo()) ? " [" + SettingsManager.getSettingByName("Mode", this).getValString().toUpperCase() + "]" : "");
			if(getName().equals("KillAura"))
				s += " (2)";
			
			if(fade <= 0)
				fade = 100 - (font.equalsIgnoreCase("Mc") ? mc.fontRendererObj.getStringWidth(s) : font.equalsIgnoreCase("Bebas") ? Bebas.small.getStringWidth(s) : Verdana.small.getStringWidth(s));
		} catch (Exception e) {
			fade = 0;
		}
	}

	public void onDisable() {
		if(fade <= 0)
			fade = 100;
	}

	public void onUpdate() {}

	public void onTick() {}

	public void onRender() {}

	public void onRenderScreen() {}

}
