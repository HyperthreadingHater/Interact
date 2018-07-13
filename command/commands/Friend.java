package us.interact.command.commands;

import us.interact.Interact;
import us.interact.command.Command;
import us.interact.utils.ingame.FriendManager;

public class Friend extends Command {

	public Friend(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		if(args.length >= 2) {
			String operation = args[0];
			String name = args[1];
			if(operation.equalsIgnoreCase("add") && args.length == 3) {
				if(!FriendManager.isCmdFriend(name)) {
					String alias = args[2];
					FriendManager.addFriend(name, alias);
					sendMessage("§c" + name + " §7wurde zu deinen §cFreunden §7hinzugefügt!");
				}else {
					sendMessage("§c" + name + " §7ist bereits dein §cFreund§7!");
				}
			}else if(operation.equalsIgnoreCase("rm") || operation.equalsIgnoreCase("remove") && args.length == 2) {
				if(name.equalsIgnoreCase("all")) {
					FriendManager.clear();
					sendMessage("Alle deine §cFreunde §7wurden §cgelöscht§7!");
				}else {
					if(FriendManager.isCmdFriend(name)) {
						FriendManager.removeFriend(name);
						sendMessage("§c" + name + " §7wurde von deinen §cFreunden §7entfernt!");
					}else {
						sendMessage("§c" + name + " §7ist §cnicht §7dein §cFreund§7!");
					}
				}
			}else {
				sendMessage("Verwende: §c" + Interact.chatPrefix + "friend §7<§cadd§7/§crm§7> <§cname§7> [§calias§7]");
			}
		}else {
			sendMessage("Verwende: §c" + Interact.chatPrefix + "friend §7<§cadd§7/§crm§7> <§cname§7> [§calias§7]");
		}
	}

}
