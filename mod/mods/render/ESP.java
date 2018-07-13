package us.interact.mod.mods.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import de.Hero.settings.SettingsManager;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.mod.mods.misc.AntiBots;
import us.interact.utils.ingame.FriendManager;
import us.interact.utils.render.ColorUtil;
import us.interact.utils.render.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Timer;

public class ESP extends Mod {

	public ESP() {
		super("ESP", Keyboard.KEY_NONE, true, Category.RENDER);
		setInConfig(false);
	}

	public void onRender() {
		if (SettingsManager.getSettingByName("Mode", ModManager.getMod("ESP")).getValString().equalsIgnoreCase("Box")) {
			for (Object o : this.mc.theWorld.loadedEntityList) {
				if (o instanceof EntityPlayer) {
					EntityPlayer e = (EntityPlayer) o;
					if (!AntiBots.isBot(e))
						if (e != mc.thePlayer)
							passive(e);
				}
			}
		} else if (SettingsManager.getSettingByName("Mode", ModManager.getMod("ESP")).getValString()
				.equalsIgnoreCase("ClearBox")) {
			for (Object o : this.mc.theWorld.loadedEntityList) {
				if (o instanceof EntityPlayer) {
					EntityPlayer e = (EntityPlayer) o;
					if (!AntiBots.isBot(e))
						if (e != mc.thePlayer)
							passiveClear(e);
				}
			}
		} else if (getMode().equalsIgnoreCase("2D")) {
			for (Object o : this.mc.theWorld.loadedEntityList) {
				if (o instanceof EntityPlayer) {
					EntityPlayer e = (EntityPlayer) o;
					if (e != mc.thePlayer) {
						GlStateManager.pushMatrix();
						GlStateManager.translate(
								(float) e.lastTickPosX + (e.posX - e.lastTickPosX) * mc.timer.renderPartialTicks
										- RenderManager.renderPosX,
								e.lastTickPosY + (e.posY - e.lastTickPosY) * mc.timer.renderPartialTicks
										- RenderManager.renderPosY + 0.5,
								e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * mc.timer.renderPartialTicks
										- RenderManager.renderPosZ);
						GlStateManager.rotate(-mc.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
						GlStateManager.rotate(mc.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
						GlStateManager.disableDepth();
						GlStateManager.disableLighting();

						if (e.hurtTime == 0) {
							Gui.drawRect(0.9D, 1.7D, -1.0D, 1.3D, Color.BLACK.getRGB());
							Gui.drawRect(0.9D, 1.6D, -0.9D, 1.4D, ColorUtil.getHUDColor());
							// Unten
							Gui.drawRect(0, 0, 0, 0, -1);

							Gui.drawRect(0.9D, -1.0D, -1.0D, -0.6D, Color.BLACK.getRGB());
							Gui.drawRect(0.9D, -0.7D, -0.9D, -0.9D, ColorUtil.getHUDColor());
							// Oben
							Gui.drawRect(0, 0, 0, 0, -1);

							Gui.drawRect(0.6D, 1.7D, 1.0D, -1.0D, Color.BLACK.getRGB());
							Gui.drawRect(0.7D, 1.6D, 0.9D, -0.9D, ColorUtil.getHUDColor());
							// Links
							Gui.drawRect(0, 0, 0, 0, -1);
							Gui.drawRect(-0.6D, 1.6D, -1.0D, -0.9D, Color.BLACK.getRGB());
							Gui.drawRect(-0.7D, 1.6D, -0.9D, -0.9D, ColorUtil.getHUDColor());

							// Rechts
							Gui.drawRect(0, 0, 0, 0, -1);
						} else {
							Gui.drawRect(0.9D, 1.7D, -1.0D, 1.3D, Color.BLACK.getRGB());
							Gui.drawRect(0.9D, 1.6D, -0.9D, 1.4D, Color.RED.getRGB());
							// Unten
							Gui.drawRect(0, 0, 0, 0, -1);

							Gui.drawRect(0.9D, -1.0D, -1.0D, -0.6D, Color.BLACK.getRGB());
							Gui.drawRect(0.9D, -0.7D, -0.9D, -0.9D, Color.RED.getRGB());
							// Oben
							Gui.drawRect(0, 0, 0, 0, -1);

							Gui.drawRect(0.6D, 1.7D, 1.0D, -1.0D, Color.BLACK.getRGB());
							Gui.drawRect(0.7D, 1.6D, 0.9D, -0.9D, Color.RED.getRGB());
							// Links
							Gui.drawRect(0, 0, 0, 0, -1);
							Gui.drawRect(-0.6D, 1.6D, -1.0D, -0.9D, Color.BLACK.getRGB());
							Gui.drawRect(-0.7D, 1.6D, -0.9D, -0.9D, Color.RED.getRGB());

							// Rechts
							Gui.drawRect(0, 0, 0, 0, -1);
						}

						GlStateManager.enableDepth();
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						GlStateManager.popMatrix();
					}
				}
			}
		}
	}

	public void passive(EntityPlayer entityItem) {
		Color color = getSetting("Rainbow") ? ColorUtil.rainbowEffect(0, 1) : ColorUtil.getHUDColor();
		if (FriendManager.isFriend(entityItem.getName())) {
			color = Color.WHITE;
		}
		this.mc.getRenderManager();
		double x = entityItem.lastTickPosX
				+ (entityItem.posX - entityItem.lastTickPosX) * this.mc.timer.renderPartialTicks
				- RenderManager.renderPosX;
		this.mc.getRenderManager();
		double y = entityItem.lastTickPosY
				+ (entityItem.posY - entityItem.lastTickPosY) * this.mc.timer.renderPartialTicks
				- RenderManager.renderPosY;
		this.mc.getRenderManager();
		double z = entityItem.lastTickPosZ
				+ (entityItem.posZ - entityItem.lastTickPosZ) * this.mc.timer.renderPartialTicks
				- RenderManager.renderPosZ;
		render(color, x, y, z, entityItem.width - 1);
		if (entityItem.hurtTime != 0) {
			this.mc.getRenderManager();
			double x1 = entityItem.lastTickPosX
					+ (entityItem.posX - entityItem.lastTickPosX) * this.mc.timer.renderPartialTicks
					- RenderManager.renderPosX;
			this.mc.getRenderManager();
			double y1 = entityItem.lastTickPosY
					+ (entityItem.posY - entityItem.lastTickPosY) * this.mc.timer.renderPartialTicks
					- RenderManager.renderPosY;
			this.mc.getRenderManager();
			double z1 = entityItem.lastTickPosZ
					+ (entityItem.posZ - entityItem.lastTickPosZ) * this.mc.timer.renderPartialTicks
					- RenderManager.renderPosZ;
			render1(color, x1, y1, z1, entityItem.width - 1);
		}
	}

	public void passiveClear(EntityPlayer entityItem) {
		Color color = getSetting("Rainbow") ? ColorUtil.rainbowEffect(0, 1) : ColorUtil.getHUDColor();
		if (FriendManager.isFriend(entityItem.getName())) {
			color = Color.WHITE;
		}
		this.mc.getRenderManager();
		double x = entityItem.lastTickPosX
				+ (entityItem.posX - entityItem.lastTickPosX) * this.mc.timer.renderPartialTicks
				- RenderManager.renderPosX;
		this.mc.getRenderManager();
		double y = entityItem.lastTickPosY
				+ (entityItem.posY - entityItem.lastTickPosY) * this.mc.timer.renderPartialTicks
				- RenderManager.renderPosY;
		this.mc.getRenderManager();
		double z = entityItem.lastTickPosZ
				+ (entityItem.posZ - entityItem.lastTickPosZ) * this.mc.timer.renderPartialTicks
				- RenderManager.renderPosZ;
		renderClear(color, x, y, z, entityItem.width - 1);
		if (entityItem.hurtTime != 0) {
			this.mc.getRenderManager();
			double x1 = entityItem.lastTickPosX
					+ (entityItem.posX - entityItem.lastTickPosX) * this.mc.timer.renderPartialTicks
					- RenderManager.renderPosX;
			this.mc.getRenderManager();
			double y1 = entityItem.lastTickPosY
					+ (entityItem.posY - entityItem.lastTickPosY) * this.mc.timer.renderPartialTicks
					- RenderManager.renderPosY;
			this.mc.getRenderManager();
			double z1 = entityItem.lastTickPosZ
					+ (entityItem.posZ - entityItem.lastTickPosZ) * this.mc.timer.renderPartialTicks
					- RenderManager.renderPosZ;
			render1Clear(color, x1, y1, z1, entityItem.width - 1);
		}
	}

	public void render1(Color color1, double x1, double y1, double z1, float width) {
		RenderHelper.drawOutlinedEntityESP(x1, y1, z1, width, 2.0D, color1);
	}

	public void render(Color color, double x, double y, double z, float width) {
		RenderHelper.drawOutlinedEntityESP(x, y, z, width, 2.0D, color);
	}

	public void render1Clear(Color color1, double x1, double y1, double z1, float width) {
		RenderHelper.drawOutlinedClearEntityESP(x1, y1, z1, width, 2.0D, color1);
	}

	public void renderClear(Color color, double x, double y, double z, float width) {
		RenderHelper.drawOutlinedClearEntityESP(x, y, z, width, 2.0D, color);
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
