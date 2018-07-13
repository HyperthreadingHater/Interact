package us.interact.command.commands;

import us.interact.Interact;
import us.interact.command.Command;
import us.interact.utils.ingame.ConfigManager;

public class Config extends Command {

	public Config(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		if (args.length >= 1 && args.length < 3) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					if (ConfigManager.getConfigs().isEmpty()) {
						sendMessage("Es existieren §ckeine §7Configs!");
					} else {
						String msg = "Configs: §c";
						for (String s : ConfigManager.getConfigs()) {
							if (msg.equalsIgnoreCase("Configs: §c")) {
								String s1, s2;
								s1 = s.substring(0, 1).toUpperCase().replace(" ", "");
								s2 = s.substring(1).replace(" ", "");
								msg += s1 + s2;
							} else {
								String s1, s2;
								s1 = s.substring(0, 1).toUpperCase().replace(" ", "");
								s2 = s.substring(1).replace("", "");
								msg += "§7, §c" + s1 + s2;
							}
						}
						sendMessage(msg);
					}
				} else {
					sendMessage("Verwende: §c" + Interact.chatPrefix + "config §7<§csave§7/§cload§7/§clist§7> [§cname§7]");
				}
			} else if (args.length == 2) {
				String name = args[1].toUpperCase();
				if (args[0].equalsIgnoreCase("save") || args[0].equalsIgnoreCase("add")) {
					if (ConfigManager.exists(name)) {
						sendMessage("Die §cConfig §7wurde §cüberschrieben§7!");
					} else {
						sendMessage("Die §cConfig §7wurde §cerstellt§7!");
					}
					ConfigManager.save(name);
				} else if (args[0].equalsIgnoreCase("load")) {
					ConfigManager.load(name);
					if (ConfigManager.exists(name)) {
						sendMessage("Die §cConfig §7wurde §cgeladen§7!");
					} else {
						sendMessage("Die §cConfig §7existiert §cnicht§7!");
					}
				} else if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("rm")
						|| args[0].equalsIgnoreCase("remove")) {
					if (ConfigManager.exists(name)) {
						sendMessage("Die §cConfig §7wurde §cgelöscht§7!");
					} else {
						sendMessage("Die §cConfig §7existiert §cnicht§7!");
					}
					ConfigManager.delete(name);
				} else {
					sendMessage(
							"Verwende: §c" + Interact.chatPrefix + "config §7<§csave§7/§cload§7/§clist§7> [§cname§7]");
				}
			}
		} else {
			sendMessage("Verwende: §c" + Interact.chatPrefix + "config §7<§csave§7/§cload§7/§clist§7> [§cname§7]");
		}
	}

}
