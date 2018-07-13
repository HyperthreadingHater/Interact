package us.interact.ui.font;

import java.awt.Font;
import java.io.InputStream;

import net.minecraft.client.Minecraft;

public class Verdana {

	public static UnicodeFontRenderer small;
	public static UnicodeFontRenderer medium;
	public static UnicodeFontRenderer big;
	public static UnicodeFontRenderer tab;
	
	public static void load() {
		
		InputStream is = Verdana.class.getResourceAsStream("/us/interact/ui/font/fonts/Verdana.TTF");
		
		Font font = null;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);

			tab = new UnicodeFontRenderer(font.deriveFont(40F));
			small = new UnicodeFontRenderer(font.deriveFont(18F));
			medium = new UnicodeFontRenderer(font.deriveFont(70F));
			big = new UnicodeFontRenderer(font.deriveFont(90F));
			
			if(Minecraft.getMinecraft().gameSettings.language != null) {
				small.setUnicodeFlag(true);
				small.setBidiFlag(Minecraft.getMinecraft().mcLanguageManager.isCurrentLanguageBidirectional());
				medium.setUnicodeFlag(true);
				medium.setBidiFlag(Minecraft.getMinecraft().mcLanguageManager.isCurrentLanguageBidirectional());
				big.setUnicodeFlag(true);
				big.setBidiFlag(Minecraft.getMinecraft().mcLanguageManager.isCurrentLanguageBidirectional());
				tab.setUnicodeFlag(true);
				tab.setBidiFlag(Minecraft.getMinecraft().mcLanguageManager.isCurrentLanguageBidirectional());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
