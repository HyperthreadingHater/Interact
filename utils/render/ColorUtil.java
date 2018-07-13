package us.interact.utils.render;

import java.awt.Color;

import de.Hero.settings.SettingsManager;
import us.interact.mod.ModManager;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class ColorUtil {

	public static Color getHUDColor() {
		return new Color((int) SettingsManager.getSettingByName("Red", ModManager.getMod("HUD")).getValDouble(),
				(int) SettingsManager.getSettingByName("Green", ModManager.getMod("HUD")).getValDouble(),
				(int) SettingsManager.getSettingByName("Blue", ModManager.getMod("HUD")).getValDouble());
	}

	public static Color rainbowEffect(long offset, float fade) {
		float f = (float)(System.nanoTime() + (offset * 4)) / 1.0E10F % 1.0F;
		long l = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(f, 1, 1)).intValue()), 16);
		Color c = new Color((int)l);
		return new Color(c.getRed() / 255.0F * fade, c.getGreen() / 255.0F * fade, c.getBlue() / 255.0F * fade, c.getAlpha() / 255.0F);
	}
	
}
