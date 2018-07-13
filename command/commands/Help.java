package us.interact.command.commands;

import net.minecraft.util.IChatComponent;
import us.interact.Interact;
import us.interact.command.Command;
import us.interact.command.CommandManager;

public class Help extends Command {

	public Help(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		sendMessage("");
		sendMessage("§7§m=========================================");
		sendMessage("");
		for(Command cmd : CommandManager.getCmds()) {
			sendMessage(Interact.chatPrefix + "§c" + cmd.getName() + " §8➤ §7" + cmd.getDescription());
		}
		sendMessage("");
		sendMessage("§7§m=========================================");
		sendMessage("");
	}
	
	@Override
	public void sendMessage(String msg) {
		mc.thePlayer.addChatMessage(IChatComponent.Serializer.jsonToComponent("{\"text\":\"" + "§7" + msg + "\"}"));
	}

}
