package us.interact.command.commands;

import us.interact.Interact;
import us.interact.command.Command;
import us.interact.mod.mods.player.InvSort;
import us.interact.utils.ingame.PlayerHelper;

public class InvSorter extends Command {

	public InvSorter(String name, String description, String[] aliases) {
		super(name, description, aliases);
	}

	@Override
	public void execute(String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("sword")) {
				PlayerHelper.sendMessage("Dein §cSchwert §7wird momentan auf den Slot §c" + InvSort.sword + " §7sortiert!");
			}else if(args[0].equalsIgnoreCase("bow")) {
				PlayerHelper.sendMessage("Dein §cBogen §7wird momentan auf den Slot §c" + InvSort.bow + " §7sortiert!");
			}else if(args[0].equalsIgnoreCase("pickaxe")) {
				PlayerHelper.sendMessage("Dein §cSpitzhacke §7wird momentan auf den Slot §c" + InvSort.pickaxe + " §7sortiert!");
			}else {
				PlayerHelper.sendMessage("Dieses §cItem §7wird nicht sortiert!");
			}
		}else if(args.length == 2) {
			int newSlot = -1;
			try {
				newSlot = Integer.parseInt(args[1]);
				if(args[0].equalsIgnoreCase("sword")) {
					InvSort.sword = newSlot;
					PlayerHelper.sendMessage("Dein §cSchwert §7wird nun auf den Slot §c" + InvSort.sword + " §7sortiert!");
				}else if(args[0].equalsIgnoreCase("bow")) {
					InvSort.bow = newSlot;
					PlayerHelper.sendMessage("Dein §cBogen §7wird nun auf den Slot §c" + InvSort.bow + " §7sortiert!");
				}else if(args[0].equalsIgnoreCase("pickaxe")) {
					InvSort.pickaxe = newSlot;
					PlayerHelper.sendMessage("Dein §cSpitzhacke §7wird nun auf den Slot §c" + InvSort.pickaxe + " §7sortiert!");
				}else {
					PlayerHelper.sendMessage("Dieses §cItem §7wird nicht sortiert!");
				}
			} catch (Exception e) {
				PlayerHelper.sendMessage("Bitte geben einen gültigen §cSlot §7an!");
			}
		}else {
			PlayerHelper.sendMessage("Verwende: §c" + Interact.chatPrefix + "invsorter §7<§csword§7/§cbow§7/§cpickaxe§7> [§cslot§7]");
		}
	}

}
