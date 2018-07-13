package us.interact.mod.mods.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.EnumFaceing;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import us.interact.events.EventPreMotionUpdate;
import us.interact.events.EventSendPacket;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.mod.mods.misc.AntiBots;
import us.interact.utils.ingame.FriendManager;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.ingame.TimeHelper;

public class KillAura extends Mod {

	public KillAura() {
		super("KillAura", 0, true, Category.COMBAT);
	}

	TimeHelper delay = new TimeHelper();

	private static int targets = 0;
	private float yaw = 0, pitch = 0;

	private EntityLivingBase hit = null;

	@Override
	public void onUpdate() {
		if (getSetting("NoInvAttack"))
			if (mc.currentScreen instanceof GuiInventory)
				return;
		if (delay.isDelayCompleted(((long) SettingsManager.getSettingByName("Delay", this).getValDouble()
				+ (SettingsManager.getSettingByName("Random", this).getValBoolean() ? new Random().nextInt(50) : 0)))) {
			targets = 0;

			delay.reset();

			boolean inviseble = SettingsManager.getSettingByName("Inviseble", this).getValBoolean();
			boolean villager = SettingsManager.getSettingByName("Villager", this).getValBoolean();
			boolean player = SettingsManager.getSettingByName("Players", this).getValBoolean();
			boolean animal = SettingsManager.getSettingByName("Animals", this).getValBoolean();
			boolean mob = SettingsManager.getSettingByName("Mobs", this).getValBoolean();
			boolean wall = SettingsManager.getSettingByName("ThroughWalls", this).getValBoolean();
			boolean dead = SettingsManager.getSettingByName("Dead", this).getValBoolean();

			if (getMode().equalsIgnoreCase("Single")) {

				EntityLivingBase hit = null;

				double nearst = 100;

				if (!mc.thePlayer.isEating()) {
					for (Object o : mc.theWorld.loadedEntityList) {
						if (o instanceof Entity) {
							Entity en = (Entity) o;
							if (en instanceof EntityLivingBase) {

								EntityLivingBase e = (EntityLivingBase) en;

								if ((e instanceof EntityPlayer && player) || (e instanceof EntityAnimal && animal)
										|| (e instanceof EntityMob && mob)
										|| (e instanceof EntityVillager && villager)) {

									if ((wall ? true : mc.thePlayer.canEntityBeSeen(e))) {
										if (!e.getName().equalsIgnoreCase(mc.thePlayer.getName())) {
											if ((dead ? true : !e.isDead) == true) {
												if (inviseble ? true : !e.isInvisible()) {
													if (!ModManager.getMod("Teams").isEnabled() || !Teams.isInTeam(e)) {
														if (!FriendManager.isFriend(e.getName())) {
															float[] rots = getRotationsNeeded(e);

															float yawDiff = mc.thePlayer.rotationYaw > rots[0]
																	? mc.thePlayer.rotationYaw - rots[0]
																	: rots[0] - mc.thePlayer.rotationYaw;
															if (yawDiff <= SettingsManager.getSettingByName("FOV", this)
																	.getValDouble()) {
																double dist = e.getDistanceToEntity(mc.thePlayer);
																if (dist <= SettingsManager
																		.getSettingByName("Range", this).getValDouble()
																		&& dist < nearst) {
																	if (!AntiBots.isBot(e)) {
																		nearst = dist;
																		hit = e;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				this.hit = hit;
				attack(hit);

				if (hit != null) {
					targets = 1;
				}

			} else if (getMode().equalsIgnoreCase("Multi")) {

				if (!mc.thePlayer.isEating()) {
					for (Object o : mc.theWorld.loadedEntityList) {
						if (o instanceof Entity) {
							Entity en = (Entity) o;
							if (en instanceof EntityLivingBase) {

								EntityLivingBase e = (EntityLivingBase) en;

								if ((e instanceof EntityPlayer && player) || (e instanceof EntityAnimal && animal)
										|| (e instanceof EntityMob && mob)
										|| (e instanceof EntityVillager && villager)) {

									if (!AntiBots.isBot(e)) {
										if ((wall ? true : mc.thePlayer.canEntityBeSeen(e))) {
											if (!e.getName().equalsIgnoreCase(mc.thePlayer.getName())) {
												if (!e.isDead) {
													if (inviseble ? true : !e.isInvisible()) {
														if (!ModManager.getMod("Teams").isEnabled()
																|| !Teams.isInTeam(e)) {
															if (!FriendManager.isFriend(e.getName())) {
																float[] rots = getRotationsNeeded(e);

																float yawDiff = mc.thePlayer.rotationYaw > rots[0]
																		? mc.thePlayer.rotationYaw - rots[0]
																		: rots[0] - mc.thePlayer.rotationYaw;
																if (yawDiff <= SettingsManager
																		.getSettingByName("FOV", this).getValDouble()) {
																	double dist = e.getDistanceToEntity(mc.thePlayer);
																	if (dist <= SettingsManager
																			.getSettingByName("Range", this)
																			.getValDouble()) {
																		this.hit = e;
																		attack(e);
																		targets++;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

			}
		}
	}

	@EventTarget
	public void onPacket(EventPreMotionUpdate event) {

			if (hit != null) {
				float[] rots = getRotationsNeeded(hit);

				yaw = rots[0];
				pitch = rots[1];
				event.setPitch(pitch);
				event.setYaw(yaw);
			}

	}

	private void attack(EntityLivingBase e) {

		if (e != null) {
			if (SettingsManager.getSettingByName("Raycast", this).getValBoolean()) {
				Entity rayEn = PlayerHelper.raycast(e,
						SettingsManager.getSettingByName("Range", this).getValDouble() + 0.5);

				if (rayEn != null)
					e = (EntityLivingBase) rayEn;
			}

			if (mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem())
				mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
						net.minecraft.network.play.client.C07PacketPlayerDigging.Action.RELEASE_USE_ITEM,
						new BlockPos(0, 0, 0), EnumFacing.DOWN));

			if (getSetting("Swing"))
				mc.thePlayer.swingItem();
			else
				mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());

			mc.playerController.attackEntity(mc.thePlayer, e);
		}

	}

	boolean ud = false;

	public float[] getRotationsNeeded(Entity entity) {
		if (entity == null)
			return null;
		double diffX = entity.posX - mc.thePlayer.posX;
		double diffY;
		if ((entity instanceof EntityLivingBase)) {
			EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() * 0.9D
					- (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
		} else {
			diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D
					- (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
		}
		double diffZ = entity.posZ - mc.thePlayer.posZ;
		double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / 3.141592653589793D) - 90.0F;
		float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / 3.141592653589793D);

		ud = !ud;
		
		if (ud) {
			pitch += 10;
		}
		
		if(pitch > 90)
			pitch = 90;

		return new float[] { mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw),
				mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch) };
	}

	public static int getTargets() {
		return targets;
	}

	@Override
	public void onEnable() {
		super.onEnable();
		EventManager.register(this);
	}

	@Override
	public void onDisable() {
		super.onDisable();
		EventManager.unregister(this);
	}

}
