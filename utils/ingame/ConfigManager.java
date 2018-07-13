package us.interact.utils.ingame;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.other.FileManager;

public class ConfigManager {
	
	private static ArrayList<String> configs = new ArrayList<>();
	
	public static boolean exists(String name) {
		return configs.contains(name.toLowerCase());
	}
	
	public static void add(String name) {
		configs.add(name.toLowerCase());
	}
	
	public static void save(String name) {
		if(!exists(name))
			configs.add(name.toLowerCase());
		FileManager.saveConfig(name);
	}
	
	public static void load(String name) {
		if(exists(name))
			FileManager.loadConfig(name);
	}
	
	public static ArrayList<String> getConfigs() {
		return configs;
	}

	public static void delete(String name) {
		if(exists(name)) {
			configs.remove(name.toLowerCase());
			FileManager.deleteConfig(name);
		}
	}
	
	public static void loadFromString(String s) {
		try {
			String[] sa = s.split("::");
			for (String s1 : sa) {
				Mod m = ModManager.getMod(s1.split("//")[0]);
				if (m != null) {
					String sn = s1.split("//")[1];
					String o = s1.split("//")[2];
					Setting setting = SettingsManager.getSettingByName(sn, m);
					if(setting != null) {
						if (setting.isCheck()) {
							setting.setValBoolean(Boolean.valueOf(o));
						} else if (setting.isCombo()) {
							setting.setValString(o);
						} else if (setting.isSlider()) {
							setting.setValDouble(Double.valueOf(o));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
