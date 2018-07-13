package us.interact.mod.mods.misc;

import java.util.ArrayList;

import com.darkmagician6.eventapi.EventManager;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.TimeHelper;

public class AntiBots extends Mod {

	public static ArrayList<String> bots = new ArrayList<>(), hurttime = new ArrayList<>(), nohurttime = new ArrayList<>(), wasGround = new ArrayList<>();

	public AntiBots() {
		super("AntiBots", 0, true, Category.MISC);
	}
	
	private TimeHelper time = new TimeHelper();

	@Override
	public void onUpdate() {

			time.reset();
			bots.clear();
			for (Object o : mc.theWorld.loadedEntityList) {
				Entity e = (Entity) o;

				if(e instanceof EntityLivingBase && e instanceof EntityPlayer) {
					EntityPlayer ep = (EntityPlayer)e;
					if (getSetting("Ground")) {
						if(!wasGround.contains(ep.getEntityId() + "")) {
							if (!ep.onGround) {
								bots.add(e.getEntityId() + "");
								continue;
							}else {
								wasGround.add(ep.getEntityId() + "");
							}
						}
					}
					
					if(getSetting("Tab")) {
						if(!isInTablist(ep)) {
							bots.add(ep.getEntityId() + "");
							continue;
						}
					}
					
					if(getSetting("Hurttime") && mc.thePlayer.getDistanceSqToEntity(ep) < SettingsManager.getSettingByName("Range", ModManager.getMod("KillAura")).getValDouble() && ep != mc.thePlayer) {
						if(nohurttime.contains(ep.getEntityId() + "")) {
							bots.add(ep.getEntityId() + "");
							continue;
						}
						if(!hurttime.contains(ep.getEntityId() + "")) {
							mc.playerController.attackEntity(mc.thePlayer, ep);
							if(ep.hurtTime > 0)
								hurttime.add(ep.getEntityId() + "");
							else {
								nohurttime.add(ep.getEntityId() + "");
								bots.add(ep.getEntityId() + "");
							}
						}
					}
				}
			}
	}

	private boolean isInTablist(EntityPlayer player) {
		if (mc.isSingleplayer()) {
			return false;
		} else {
			java.util.Iterator var3 = mc.getNetHandler().func_175106_d().iterator();

			while (var3.hasNext()) {
				NetworkPlayerInfo playerInfo = (NetworkPlayerInfo) var3.next();
				if (playerInfo.func_178845_a().getName().equalsIgnoreCase(player.getName())) {
					return true;
				}
			}
			return false;
		}
	}

	public static boolean isBot(Entity e) {
		return bots.contains(e.getEntityId() + "");
	}

	@Override
	public void onEnable() {
		hurttime.clear();
		nohurttime.clear();
		bots.clear();
		wasGround.clear();
		super.onEnable();
		EventManager.register(this);
	}

	@Override
	public void onDisable() {
		super.onDisable();
		EventManager.unregister(this);
	}

}
