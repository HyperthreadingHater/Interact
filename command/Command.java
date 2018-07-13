package us.interact.command;

import net.minecraft.client.Minecraft;
import us.interact.utils.ingame.PlayerHelper;

public abstract class Command {

	private String name;
	private String[] aliases;
	private String description;
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Command(String name, String description, String[] aliases) {
		this.name = name;
		this.description = description;
		this.aliases = aliases;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}
	
	public abstract void execute(String[] args);
	
	public void sendMessage(String msg) {
		PlayerHelper.sendMessage(msg);
	}

}
