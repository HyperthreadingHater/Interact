package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.network.play.client.C03PacketPlayer;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;

public class NoFall extends Mod {

	public NoFall() {
		super("NoFall", 0, true, Category.MOVEMENT);
	}

	public void onUpdate() {
		if (SettingsManager.getSettingByName("Mode", ModManager.getMod("NoFall")).getValString().equalsIgnoreCase("OldAAC")) {
			if (!mc.thePlayer.onGround || mc.thePlayer.isInWater() || ModManager.getMod("SafeWalk").isEnabled()
					|| ModManager.getMod("Speed").isEnabled())
				return;

			if (!mc.thePlayer.isInWeb || !mc.thePlayer.isAirBorne || mc.thePlayer.onGround) {
				mc.thePlayer.motionY -= 6;
			}
		}
			if (SettingsManager.getSettingByName("Mode", ModManager.getMod("NoFall")).getValString().equalsIgnoreCase("NewAAC")) {
				if(mc.thePlayer.fallDistance > 3) {
				PlayerHelper.sendMessage("HAHA AAC IST GAY NIX DA NOFALL");
					//mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 0.021F, mc.thePlayer.posZ, mc.thePlayer.onGround));
				}
				
					
				
		}else if (SettingsManager.getSettingByName("Mode", ModManager.getMod("NoFall")).getValString().equalsIgnoreCase("Vanilla")) {
            if(mc.thePlayer.fallDistance > 3) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
		}else if (SettingsManager.getSettingByName("Mode", ModManager.getMod("NoFall")).getValString().equalsIgnoreCase("AAC")) {
			if(mc.thePlayer.fallDistance > 3) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 0.01, mc.thePlayer.posZ, mc.thePlayer.onGround));
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			}
		}
		
	
		
	
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

}
