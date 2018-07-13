package us.interact.command.commands;

import us.interact.Interact;
import us.interact.command.Command;

public class Spammer extends Command {

	public Spammer(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		if(args.length > 0) {
			String msg = "";
			for(int i = 0; i < args.length; i++) {
				if(msg == "") {
					msg = args[i];
				}else {
					msg += " " + args[i];
				}
			}
			us.interact.mod.mods.misc.Spammer.setMessage(msg);
			sendMessage("Die Nachricht lautet nun: §c" + msg);
		}else {
			sendMessage("Verwende: §c" + Interact.chatPrefix + "spammer §7<§cmessage§7>");
		}
	}

}
