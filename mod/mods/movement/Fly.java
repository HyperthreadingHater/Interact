package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.ingame.TimeHelper;

public class Fly extends Mod {

	public Fly() {
		super("Fly", 0, true, Category.MOVEMENT);
	}

	double startY = 0;
	boolean b = false;
	TimeHelper delay = new TimeHelper();

	@Override
	public void onUpdate() {
		mc.thePlayer.capabilities.isFlying = false;
		if (getMode().equalsIgnoreCase("AACSlime")) {
			BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ);
			if (mc.theWorld.getBlockState(bp).getBlock() == Blocks.slime_block) {
				mc.thePlayer.motionY = 0.6D;
				delay.reset();
			} else {
				if (delay.isDelayCompleted(500)) {
					boolean overSlime = false;
					for (int i = 1; i < 8; i++) {
						BlockPos slime = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - i, mc.thePlayer.posZ);
						if (mc.theWorld.getBlockState(slime).getBlock() == Blocks.slime_block) {
							overSlime = true;
							break;
						}
					}
					if (overSlime) {
						b = !b;
						if (b)
							mc.thePlayer.motionY = 0.4;
						else
							mc.thePlayer.motionY = -0.4;
					}
				}
			}
		} else if (getMode().equalsIgnoreCase("Timolia")) {
			if (!mc.thePlayer.onGround) {
				b = !b;
				if (b)
					mc.thePlayer.motionY = 0.4;
				else
					mc.thePlayer.motionY = -0.4;
				mc.thePlayer.motionX *= 1.01;
				mc.thePlayer.motionZ *= 1.01;
			}
		} else if (getMode().equalsIgnoreCase("Vanilla")) {
			mc.thePlayer.capabilities.isFlying = true;
		} else if (getMode().equalsIgnoreCase("OldAAC")) {
			if (startY > mc.thePlayer.posY) {
				mc.thePlayer.motionY = 1D;
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			} else {
				mc.thePlayer.motionZ *= 1.05D;
				mc.thePlayer.motionX *= 1.05D;
			}
		}else if(getMode().equalsIgnoreCase("Gomme")) {
			if(mc.thePlayer.hurtTime > 0 && mc.thePlayer.posY <= 1.0D ) {
                mc.thePlayer.motionY = 8D;
            }
		}else if(getMode().equalsIgnoreCase("CubeCraft")) {
			mc.timer.timerSpeed = 0.2F;
			mc.thePlayer.capabilities.isFlying = true;
			mc.gameSettings.keyBindJump.pressed = false;
			mc.gameSettings.keyBindSneak.pressed = false;
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();
		if(mc.thePlayer != null)
			startY = mc.thePlayer.posY - 4;
	}

	@Override
	public void onDisable() {
		mc.thePlayer.speedInAir = 0.02F;
		mc.timer.timerSpeed = 1F;
		super.onDisable();
		mc.thePlayer.capabilities.isFlying = false;
		mc.thePlayer.fallDistance = 0.0F;
	}

}
