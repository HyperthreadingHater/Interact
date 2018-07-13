package us.interact.ui.font;

import java.awt.Font;
import java.io.InputStream;

import net.minecraft.client.Minecraft;

public class FjallaOne {

	public static UnicodeFontRenderer small;
	public static UnicodeFontRenderer medium;
	public static UnicodeFontRenderer big;
	public static UnicodeFontRenderer tab;
	
	public static void load() {
		
		InputStream is = FjallaOne.class.getResourceAsStream("/us/interact/ui/font/fonts/FjallaOne.TTF");
		
		Font font = null;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);

			tab = new UnicodeFontRenderer(font.deriveFont(15F));
			small = new UnicodeFontRenderer(font.deriveFont(25F));
			medium = new UnicodeFontRenderer(font.deriveFont(45F));
			big = new UnicodeFontRenderer(font.deriveFont(65F));
			
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
		} catch (Exception e) {e.printStackTrace();}
		
	}

}
