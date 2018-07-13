package us.interact.utils.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.StringUtils;
import us.interact.ui.font.Raleway;
import us.interact.ui.font.UnicodeFontRenderer;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class FontUtil {
	private static UnicodeFontRenderer fontRenderer;
	private static UnicodeFontRenderer fontRenderer1;

	public static void setupFontUtils() {
		fontRenderer = Raleway.bar;
		fontRenderer1 = Raleway.small;
	}

	public static int getStringWidth(String text) {
		return fontRenderer.getStringWidth(StringUtils.stripControlCodes(text));
	}

	public static int getFontHeight() {
		return fontRenderer.FONT_HEIGHT;
	}

	public static void drawString(String text, double x, double y, int color) {
		fontRenderer.drawString(text, (int)x, (int)y, color);
		/*
		 * FIX WHITE
		 */
		Gui.drawRect(0, 0, 0, 0, 1);
	}

	public static void drawStringWithShadow(String text, double x, double y, int color) {
		drawString(text, (int) x, (int) y, color);
	}

	public static void drawCenteredString(String text, double x, double y, int color) {
		drawString(text, (int)x - fontRenderer.getStringWidth(text) / 2, (int)y, color);
	}

	public static void drawCenteredStringWithShadow(String text, double x, double y, int color) {
		drawStringWithShadow(text, (int)x - fontRenderer.getStringWidth(text) / 2, (int)y, color);
	}

	public static void drawTotalCenteredString(String text, double x, double y, int color) {
		drawString(text, (int)x - fontRenderer.getStringWidth(text) / 2, (int)y - fontRenderer.FONT_HEIGHT / 2, color);
	}

	public static void drawTotalCenteredStringWithShadow(String text, double x, double y, int color) {
		drawStringWithShadow(text, (int)x - fontRenderer.getStringWidth(text) / 2, (int)y - fontRenderer.FONT_HEIGHT / 2F, color);
	}

	public static void drawStringBIG(String text, double x, double y, int color) {
		fontRenderer1.drawString(text, (int)x, (int)y, color);
	}

	public static void drawStringWithShadowBIG(String text, double x, double y, int color) {
		drawStringBIG(text, (int) x, (int) y, color);
	}

	public static void drawTotalCenteredStringWithShadowBIG(String text, double x, double y, int color) {
		drawStringWithShadowBIG(text, (int)x - fontRenderer1.getStringWidth(text) / 2, (int)y - fontRenderer1.FONT_HEIGHT / 2F, color);
	}
}
