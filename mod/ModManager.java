package us.interact.mod;

import java.util.ArrayList;

import us.interact.mod.mods.combat.*;
import us.interact.mod.mods.exploits.*;
import us.interact.mod.mods.misc.*;
import us.interact.mod.mods.movement.*;
import us.interact.mod.mods.none.*;
import us.interact.mod.mods.player.*;
import us.interact.mod.mods.render.*;
import us.interact.mod.mods.world.*; 
import us.interact.utils.*;

public class ModManager {
	
	private static ArrayList<Mod> mods;
	
	public static void init() {
		mods = new ArrayList<>();
		addMod(new InvCleaner());
		addMod(new Updater());
		addMod(new ESP());
		addMod(new GUI());
		addMod(new Speed());
		addMod(new Chams());
		addMod(new HUD());
		addMod(new StairSpeed());
		addMod(new SlimeJump());
		addMod(new Fly());
		addMod(new us.interact.mod.mods.render.ArrayList());
		addMod(new NoBob());
		addMod(new Safewalk());
		addMod(new Step());
		addMod(new NoWeb());
		addMod(new Webwalk());
		addMod(new TerrainSpeed());
		addMod(new KillAura());
		addMod(new Velocity());
		addMod(new ChestStealer());
		addMod(new NoFall());
		addMod(new FastPlace());
		addMod(new Eagle());
		addMod(new NoFriends());
		addMod(new ChestESP());
		addMod(new ItemESP());
		addMod(new AutoArmor());
		addMod(new Fucker());
		addMod(new Sprint());
		addMod(new Teams());
		addMod(new InvMove());
		addMod(new NoFov());
		addMod(new ScaffoldWalk());
		addMod(new Tower());
		addMod(new Fullbright());
		addMod(new NoHurtcam());
		addMod(new FastLadder());
		addMod(new Jesus());
		addMod(new NoSlow());
		addMod(new Spammer());
		addMod(new InvSort());
		addMod(new Nameprotect());
		addMod(new AntiCactus());
		addMod(new ColorSign());
		addMod(new Glide());
		addMod(new AntiInvisibles());
		addMod(new MidClick());
		addMod(new Tracers());
		addMod(new AirStuck());
		addMod(new BlockOverlay());
		addMod(new AntiBots());
		addMod(new AntiFire());
		addMod(new NoScoreboard());
		addMod(new Longjump());
		addMod(new IceSpeed());
		addMod(new Stream());
		addMod(new CustomSpeed());
		addMod(new HitAnimation());
		addMod(new BedESP());
		addMod(new Radio());
		addMod(new Nametags());
		
		if(!getMod("Updater").isEnabled())
			getMod("Updater").toggle();
		if(!getMod("HUD").isEnabled())
			getMod("HUD").toggle();
	}
	
	public static void addMod(Mod m) {
		if(getMod(m.getName()) == null)
			mods.add(m);
	}
	
	public static void removeMod(Mod m) {
		if(getMod(m.getName()) != null)
			mods.remove(m);
	}
	
	public static Mod getMod(String name) {
		for(Mod m : mods) {
			if(m.getName().equalsIgnoreCase(name))
				return m;
		}
		return null;
	}

	public static ArrayList<Mod> getMods() {
		return mods;
	}
	
}
