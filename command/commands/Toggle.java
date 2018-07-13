package us.interact.command.commands;

import us.interact.Interact;
import us.interact.command.Command;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;

public class Toggle extends Command {

	public Toggle(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		if(args.length == 1) {
			System.out.println(args[0]);
			Mod m = ModManager.getMod(args[0]);
			if(m != null) {
				m.toggle();
				sendMessage("Der Mod §c" + m.getName() + " §7wurde §c" + (m.isEnabled() ? "§aaktiviert" : "deaktiviert") + "§7!");
			}else {
				sendMessage("Dieser Mod existiert §cnicht§7!");
			}
		}else {
			sendMessage("Verwende: §c" + Interact.chatPrefix + "toggle §7<§cmod§7>");
		}
	}

}
