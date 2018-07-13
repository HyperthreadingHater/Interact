package us.interact.command.commands;

import org.lwjgl.input.Keyboard;

import us.interact.Interact;
import us.interact.command.Command;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;

public class Bind extends Command {

	public Bind(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		if(args.length == 2) {
			Mod m = ModManager.getMod(args[0]);
			if(m != null) {
				int keyBind = Keyboard.getKeyIndex(args[1].toUpperCase());
				m.setKeyBind(keyBind);
				if(keyBind == 0)
					sendMessage("Der §cKeyBind §7von §c" + m.getName() + " §7wurde gelöscht!");
				else
					sendMessage("Der §cKeyBind §7von §c" + m.getName() + " §7wurde zu §c" + args[1].toUpperCase() + " §7geändert!");
			}else {
				sendMessage("Dieser Mod existiert §cnicht§7!");
			}
		}else {
			sendMessage("Verwende: §c" + Interact.chatPrefix + "bind §7<§cMod§7> <§cKey§7>");
		}
	}

}
