package us.interact.utils.other;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiConsumer;

import de.Hero.clickgui.ClickGUI;
import de.Hero.clickgui.Panel;
import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.mod.mods.player.InvSort;
import us.interact.utils.ingame.ConfigManager;
import us.interact.utils.ingame.FriendManager;
import us.interact.utils.render.Account;

public class FileManager {

	private static File datafolder = new File("Interact/");
	private static File configfolder = new File("Interact/configs");

	public static void init() {
		if (!datafolder.exists())
			datafolder.mkdirs();
		if (!configfolder.exists())
			configfolder.mkdirs();
	}

	public static void saveSettings() {
		File file = new File(datafolder, "settings.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (Setting setting : SettingsManager.getSettings()) {

				if (setting.isCheck()) {
					writer.write(setting.getParentMod().getName() + "//" + setting.getName() + "//"
							+ setting.getValBoolean() + "::");
				} else if (setting.isCombo()) {
					writer.write(setting.getParentMod().getName() + "//" + setting.getName() + "//"
							+ setting.getValString() + "::");
				} else if (setting.isSlider()) {
					writer.write(setting.getParentMod().getName() + "//" + setting.getName() + "//"
							+ setting.getValDouble() + "::");
				}

				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadSettings() {
		File file = new File(datafolder, "settings.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						Mod m = ModManager.getMod(s1.split("//")[0]);
						if (m != null) {
							if(s1.split("//").length == 3) {
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
					}
				}
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveMods() {
		File file = new File(datafolder, "mods.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (Mod m : ModManager.getMods()) {

				writer.write(m.getName() + "//" + m.isEnabled() + "//" + m.getKeyBind() + "::");

				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadMods() {
		File file = new File(datafolder, "mods.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						Mod m = ModManager.getMod(s1.split("//")[0]);
						if (m != null) {
							boolean enabled = Boolean.valueOf(s1.split("//")[1]);
							int keyBind = Integer.valueOf(s1.split("//")[2]);
							if (enabled && !m.isEnabled())
								m.toggle();
							m.setKeyBind(keyBind);
						}
					}
				}
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveFriends() {
		File file = new File(datafolder, "friends.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			FriendManager.getFriends().forEach(new BiConsumer<String, String>() {

				@Override
				public void accept(String name, String alias) {
					try {
						writer.write(name + "//" + alias + "::");

						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadFriends() {
		File file = new File(datafolder, "friends.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						if (s1.split("//").length == 2) {
							String name = s1.split("//")[0];
							String alias = s1.split("//")[1];
							FriendManager.addFriend(name, alias);
						}
					}
				}
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveGui() {
		File file = new File(datafolder, "gui.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (Panel p : ClickGUI.rpanels) {
				writer.write(p.title + "//" + p.extended + "//" + p.x + "//" + p.y + "::");

				writer.flush();
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadGui() {
		File file = new File(datafolder, "gui.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						if (s1.split("//").length == 4) {
							String title = s1.split("//")[0];
							boolean extended = Boolean.valueOf(s1.split("//")[1]);
							double x = Double.valueOf(s1.split("//")[2]);
							double y = Double.valueOf(s1.split("//")[3]);
							for (Panel p : ClickGUI.rpanels) {
								if (p.title.equalsIgnoreCase(title)) {
									p.extended = extended;
									p.x = x;
									p.y = y;
								}
							}
						}
					}
				}
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig(String name) {
		File file = new File(configfolder, name.toLowerCase() + ".txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (Setting setting : SettingsManager.getSettings()) {

				Mod m = setting.getParentMod();
				if (m.isInConfig()) {
					if (setting.isCheck()) {
						writer.write(m.getName() + "//" + setting.getName() + "//" + setting.getValBoolean() + "::");
					} else if (setting.isCombo()) {
						writer.write(m.getName() + "//" + setting.getName() + "//" + setting.getValString() + "::");
					} else if (setting.isSlider()) {
						writer.write(m.getName() + "//" + setting.getName() + "//" + setting.getValDouble() + "::");
					}
				}

				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadConfig(String name) {
		File file = new File(configfolder, name.toLowerCase() + ".txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						Mod m = ModManager.getMod(s1.split("//")[0]);
						if (m != null) {
							String sn = s1.split("//")[1];
							String o = s1.split("//")[2];
							Setting setting = SettingsManager.getSettingByName(sn, m);
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
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveConfigs() {
		File file = new File(datafolder, "configs.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (String name : ConfigManager.getConfigs()) {
				writer.write(name + "::");

				writer.flush();
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadConfigs() {
		File file = new File(datafolder, "configs.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						ConfigManager.add(s1);
					}
				}
				if(ConfigManager.getConfigs().size() -1 > 0)
					ConfigManager.getConfigs().remove(ConfigManager.getConfigs().size()-1);
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteConfig(String name) {
		File file = new File(configfolder, name.toLowerCase() + ".txt");
		if(file.exists())
			file.delete();
	}

	public static void saveSlots() {
		File file = new File(datafolder, "slots.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("sword" + "//" + InvSort.sword + "::");
			writer.flush();
			writer.write("bow" + "//" + InvSort.bow + "::");
			writer.flush();
			writer.write("pickaxe" + "//" + InvSort.pickaxe + "::");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadSlots() {
		File file = new File(datafolder, "slots.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("::");
					for (String s1 : sa) {
						String type = s1.split("//")[0];
						if (type.equalsIgnoreCase("sword")) {
							InvSort.sword = Integer.parseInt(s1.split("//")[1]);
						}else if (type.equalsIgnoreCase("bow")) {
							InvSort.bow = Integer.parseInt(s1.split("//")[1]);
						}else if (type.equalsIgnoreCase("pickaxe")) {
							InvSort.pickaxe = Integer.parseInt(s1.split("//")[1]);
						}
					}
				}
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Account> loadAlts() {
		File file = new File(datafolder, "alts.txt");
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				char[] c = new char[2048];

				int i = 0;

				ArrayList<Account> alts = new ArrayList<>();
				
				while ((i = fr.read(c)) >= 0) {
					String s = String.copyValueOf(c);
					String[] sa = s.split("//");
					for (String alt : sa) {
						if(alt.split(":").length > 1)
							alts.add(Account.fromString(alt));
					}
				}
				fr.close();
				return alts;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static void saveAlts(ArrayList<Account> accounts) {
		File file = new File(datafolder, "alts.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			String s = "";
			for (Account account : accounts) {
				s += account.toAltString() + "//";
			}
			
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
