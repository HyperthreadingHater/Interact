package us.interact.command;

import java.util.ArrayList;

import us.interact.command.commands.*;

public class CommandManager {
	
	private static ArrayList<Command> cmds = new ArrayList<>();
	
	public static void init() {
		cmds.add(new Toggle("toggle", "Schaltet ein Mod ein/aus", new String[] {"t"}));
		cmds.add(new Bind("bind", "Binded ein Mod", new String[] {}));
		cmds.add(new Friend("friend", "Verwaltet deine Freunde", new String[] {}));
		cmds.add(new Config("config", "Verwaltet deine Configs", new String[] {"configs"}));
		cmds.add(new Help("help", "Zeigt dir diese Hilfe", new String[] {"hilfe"}));
		cmds.add(new Spammer("Spammer", "Setzt die Spammer Nachricht", new String[] {"spam"}));
		cmds.add(new AutoSettings("AutoSettings", "Lädt vorgegebene Settings", new String[] {"settings", "setting"}));
		cmds.add(new InvSorter("InvSorter", "Ändert die Sortierung", new String[] {"sort", "sorter", "invsort"}));
	}
	
	public static boolean execute(String[] args) {

		Command cmd = getCommand(args[0]);
		
		if(cmd != null) {
			String s = "";
			for(int i = 1; i < args.length; i++) {
				s += args[i] + " ";
			}
			String[] rs = s.trim().split(" ");
			if(rs[0].equalsIgnoreCase(""))
				cmd.execute(new String[] {});
			else
				cmd.execute(rs);
			return true;
		}
		
		return false;
	}
	
	public static Command getCommand(String name) {
		for(Command c : getCmds()) {
			String cname = c.getName();
			String[] aliases = c.getAliases();
			if(name.equalsIgnoreCase(cname)) {
				return c;
			}else {
				for(String a : aliases) {
					if(name.equalsIgnoreCase(a)) {
						return c;
					}
				}
			}
		}
		return null;
	}
	
	public static ArrayList<Command> getCmds() {
		return cmds;
	}

}
