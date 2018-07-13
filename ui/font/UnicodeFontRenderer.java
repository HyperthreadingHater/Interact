package us.interact.ui.font;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import us.interact.mod.ModManager;
import us.interact.mod.mods.render.Nameprotect;
import us.interact.utils.ingame.FriendManager;

import static org.lwjgl.opengl.GL11.*;

public class UnicodeFontRenderer extends FontRenderer {

	private final UnicodeFont font;

	public UnicodeFontRenderer(Font awtFont) {
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"),
				Minecraft.getMinecraft().getTextureManager(), false);

		font = new UnicodeFont(awtFont);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.WHITE));
		try {
			font.loadGlyphs();
		} catch (SlickException exception) {
			throw new RuntimeException(exception);
		}
		String alphabet = "abcdefghijklmnopgrstruvwxyzABCDEFGHIJKLMNOPGRSTRUVWXYZ";
		FONT_HEIGHT = font.getHeight(alphabet) / 2;
	}

	@Override
	public int drawString(String string, int x, int y, int color) {
		if (string == null)
			return 0;

		glPushMatrix();
		glScaled(0.5, 0.5, 0.5);

		boolean blend = glIsEnabled(GL_BLEND);
		boolean lighting = glIsEnabled(GL_LIGHTING);
		boolean texture = glIsEnabled(GL_TEXTURE_2D);
		if (!blend)
			glEnable(GL_BLEND);
		if (lighting)
			glDisable(GL_LIGHTING);
		if (texture)
			glDisable(GL_TEXTURE_2D);
		x *= 2;
		y *= 2;

//		int lastColor = color;
//
//		for (int i = 0; i < string.length(); i++) {
//			char var4 = string.charAt(i);
//			int var5;
//			int var6 = lastColor;
//			int tmp = i + 1;
//			if (tmp > string.length() - 1)
//				tmp = i;
//			var5 = "0123456789abcdefklmnor".indexOf(string.toLowerCase().charAt(tmp));
//			boolean render = true;
//
//			if (var4 == 167 && i + 1 < string.length()) {
//				i++;
//				if (var5 < 16) {
//					this.randomStyle = false;
//					this.boldStyle = false;
//					this.strikethroughStyle = false;
//					this.underlineStyle = false;
//					this.italicStyle = false;
//
//					if (var5 < 0 || var5 > 15) {
//						var5 = 15;
//					}
//
//					var6 = this.colorCode[var5];
//					this.textColor = var6;
//					render = false;
//				} else if (var5 == 16) {
//					this.randomStyle = true;
//					render = false;
//				} else if (var5 == 17) {
//					this.boldStyle = true;
//					render = false;
//				} else if (var5 == 18) {
//					this.strikethroughStyle = true;
//					render = false;
//				} else if (var5 == 19) {
//					this.underlineStyle = true;
//					render = false;
//				} else if (var5 == 20) {
//					this.italicStyle = true;
//					render = false;
//				} else if (var5 == 21) {
//					this.randomStyle = false;
//					this.boldStyle = false;
//					this.strikethroughStyle = false;
//					this.underlineStyle = false;
//					this.italicStyle = false;
//					var6 = new Color(red, blue, green, alpha).getRGB();
//					render = false;
//				}
//			}
//
//			lastColor = var6;
//
//			if (render)
//				x += font.drawString(x, y, String.valueOf(var4), new org.newdawn.slick.Color(var6));
//		}
		
		font.drawString(x, y, string, new org.newdawn.slick.Color(color));

		if (texture)
			glEnable(GL_TEXTURE_2D);
		if (lighting)
			glEnable(GL_LIGHTING);
		if (!blend)
			glDisable(GL_BLEND);
		glPopMatrix();
		return (x / 2) + getStringWidth(string);
	}

	@Override
	public int getCharWidth(char c) {
		return getStringWidth(Character.toString(c));
	}

	@Override
	public int getStringWidth(String string) {
//		String codes = "0123456789abcdefklmnor";
//		for (int i = 0; i < string.length(); i++) {
//			char c = string.charAt(i);
//			String sc = String.valueOf(c);
//			if (sc.equalsIgnoreCase("§")) {
//				int tmp = i + 1;
//				if (tmp < string.length()) {
//					char bc = string.charAt(i + 1);
//					if (codes.contains(String.valueOf(bc).toLowerCase())) {
//						string = string.replace(String.valueOf(c + "" + bc), "");
//					}
//				}
//			}
//		}

		return font.getWidth(string) / 2;
	}

	public int getStringHeight(String string) {
		return font.getHeight(string) / 2;
	}

}
