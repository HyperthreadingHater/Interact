package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import de.Hero.settings.SettingsManager;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Timer;
import us.interact.events.EventPreMotionUpdate;
import us.interact.events.EventPreMove;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.ingame.TimeHelper;

public class Speed extends Mod {

	public Speed() {
		super("Speed", 0, true, Category.MOVEMENT);
	}

	public float value = 1.2F;
	public static boolean isenabled;
	private int jumps = 0;
	private boolean res = false;
	private TimeHelper laac = new TimeHelper(), aacport = new TimeHelper(), vanilla = new TimeHelper(),
			ncp = new TimeHelper();

	@Override
	public void onUpdate() {
		mc.timer.timerSpeed = 1F;

		if ((this.mc.gameSettings.keyBindForward.pressed) || (this.mc.gameSettings.keyBindBack.pressed)
				|| (this.mc.gameSettings.keyBindLeft.pressed) || (this.mc.gameSettings.keyBindRight.pressed)) {
			if (getMode().equalsIgnoreCase("Vanilla")) {
				if (mc.thePlayer.onGround) {
					mc.thePlayer.motionX *= 1.9;
					mc.thePlayer.motionZ *= 1.9;
				}
			} else if (getMode().equalsIgnoreCase("Motion")) {
				if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed) {
					if (!mc.gameSettings.keyBindJump.pressed && mc.thePlayer.getFoodStats().getFoodLevel() > 3
							&& !mc.thePlayer.isInWater() && mc.thePlayer.onGround) {
						mc.thePlayer.motionX *= 1.3;
						mc.thePlayer.motionZ *= 1.3;
					}
				}
			} else if (getMode().equalsIgnoreCase("AACBhop")) {
				if (mc.thePlayer.onGround) {
					mc.thePlayer.jump();
					mc.thePlayer.landMovementFactor = 9999999999999999999999999F;
					mc.thePlayer.speedOnGround = 9999999999999999999999999999999999f;
					mc.thePlayer.motionX *= 1.001f;
					mc.thePlayer.motionZ *= 1.001f;
					mc.timer.timerSpeed = 1.1F;
				} else {
					mc.thePlayer.speedInAir = 0.0216F;
				}
			} else if (getMode().equalsIgnoreCase("AACYport")) {
				if (this.mc.thePlayer.onGround) {
					this.mc.thePlayer.jump();
				} else {
					this.mc.thePlayer.motionY = -0.21D;
					net.minecraft.util.Timer.timerSpeed = 1.025F;
				}
			} else if (getMode().equalsIgnoreCase("LAAC")) {
				mc.timer.timerSpeed = 1.02F;
				if (mc.thePlayer.onGround) {
					mc.thePlayer.speedOnGround = 1;
					mc.thePlayer.jump();
					laac.reset();
				} else {
					mc.thePlayer.speedInAir = 0.021F;
				}
			} else if (getMode().equalsIgnoreCase("NCP")) {
				if (jumps > 0) {
					if (mc.thePlayer.onGround) {
						mc.thePlayer.jump();
						jumps--;
					}
				} else {
					if (!res) {
						ncp.reset();
						res = true;
					}
					if (ncp.getDelay() < 390) {
						if (mc.thePlayer.onGround) {
							mc.thePlayer.jump();
						} else {
							mc.thePlayer.motionY -= 1.199D;
						}
					} else {
						mc.thePlayer.motionX *= 0.7;
						mc.thePlayer.motionZ *= 0.7;
						res = false;
						jumps = 2;
					}
				}
			} else if (getMode().equalsIgnoreCase("AACStrafe")) {
				if (mc.thePlayer.onGround) {
					mc.thePlayer.jump();
					mc.thePlayer.moveStrafing += 0.4F;

					mc.thePlayer.speedInAir = 0.0210F;
					mc.thePlayer.landMovementFactor = 9999999999999999999999999F;
					mc.thePlayer.speedOnGround = 9999999999999999999999999999999999f;
					mc.thePlayer.motionX *= 1.004f;
					mc.thePlayer.motionZ *= 1.004f;
					mc.timer.timerSpeed = 1.009F;
				}
			} else if (getMode().equalsIgnoreCase("Old")) {
				if ((this.mc.thePlayer == null) || (this.mc.theWorld == null)) {
					return;
				}
				if (this.mc.thePlayer.onGround) {
					isenabled = true;
				}
				if (((this.mc.gameSettings.keyBindForward.pressed) || (this.mc.gameSettings.keyBindBack.pressed)
						|| (this.mc.gameSettings.keyBindLeft.pressed) || (this.mc.gameSettings.keyBindRight.pressed))
						&& (this.mc.thePlayer.onGround) && (!this.mc.thePlayer.isCollidedHorizontally)) {
					this.mc.thePlayer.jump();
					isenabled = true;
				}
				if ((this.mc.thePlayer.isAirBorne) && (!this.mc.gameSettings.keyBindJump.pressed)) {
					this.mc.thePlayer.onGround = true;
					this.mc.thePlayer.motionX *= this.value;
					this.mc.thePlayer.onGround = false;
					this.mc.thePlayer.motionZ *= this.value;
					this.mc.thePlayer.onGround = true;
					isenabled = false;
				}
			} else if (getMode().equalsIgnoreCase("1.9.10")) {
				if (mc.thePlayer.onGround) {
					mc.thePlayer.jump();
				} else {
					mc.thePlayer.motionY -= 10;
				}
			}else if(getMode().equalsIgnoreCase("Cubecraft")) {
				if(mc.thePlayer.onGround) {
					mc.thePlayer.jump();
				}else {
					mc.thePlayer.motionY = -5;
				}
			}
		}

	}

	@Override
	public void onDisable() {
		super.onDisable();
		EventManager.unregister(this);
		mc.thePlayer.speedInAir = 0.02F;
		mc.timer.timerSpeed = 1.0F;
	}

	@Override
	public void onEnable() {
		super.onEnable();
		jumps = 1;
		EventManager.register(this);
	}

	@EventTarget
	public void onMove(EventPreMotionUpdate e) {
		if (getMode().equalsIgnoreCase("AAC 3.3.11")) {
			if (mc.thePlayer.moveForward > 0) {
				double dir = getDirection();
				double speed = 0.01;
				mc.timer.timerSpeed = 12F;

				mc.thePlayer.motionX = -Math.sin(dir) * speed;
				mc.thePlayer.motionZ = Math.cos(dir) * speed;
			}
		}
	}

	private double getDirection() {
		float f = mc.thePlayer.rotationYaw;

		if (mc.thePlayer.moveForward < 0) {
			f += 180;
		}
		return f;
	}

}