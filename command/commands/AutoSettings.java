package us.interact.command.commands;

import us.interact.command.Command;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.ConfigManager;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.other.OnlineSettings;

public class AutoSettings extends Command {

	public AutoSettings(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		String list = OnlineSettings.getAvailableSettings().replaceAll("&", "§").replace("(ue)", "ü");
		if(args.length == 1) {
			String setting = OnlineSettings.getSettings(args[0]);
			if(!setting.equalsIgnoreCase("NONE")) {
				ConfigManager.loadFromString(setting);
				PlayerHelper.sendMessage("§7Die §c" + args[0] + " §7Config wurde geladen!");
			}else {
				PlayerHelper.sendMessage(list);
			}
		}else {
			PlayerHelper.sendMessage(list);
		}
	}

}
