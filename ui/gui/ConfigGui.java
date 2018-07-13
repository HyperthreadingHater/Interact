package us.interact.ui.gui;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ConfigGui extends GuiScreen {

	
	
	int x = 100,y = 10,w = 100, h = 10;
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		Gui.drawRect(sr.getScaledWidth() - 50, 100, 50, sr.getScaledHeight() - 50, Color.BLACK.getRGB());		
		fontRendererObj.drawString("WORK IN PROGRESS(triggerd?)xd", mouseX, mouseY, Color.RED.getRGB());

		super.drawScreen(mouseX, mouseY, partialTicks);
		
		
	}
		
		
		//Gui.drawRect(x, y, w, y - h, Integer.MAX_VALUE);
		
		
		
		
		public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
			
			if((mouseY > y - h && mouseY < y) && (mouseX > x && mouseX < w)) {
				mc.displayGuiScreen(new ConfigGui());
				return;
			}
		
	}
	
}
