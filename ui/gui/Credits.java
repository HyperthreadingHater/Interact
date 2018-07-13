package us.interact.ui.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import us.interact.ui.font.Fibel;
import us.interact.utils.other.Credit;

public class Credits extends GuiScreen {

	private String current;
	private GuiScreen oldScreen;
	private ArrayList<Credit> credits = new ArrayList<>();
	
	public Credits(GuiScreen oldScreen) {
		this.oldScreen = oldScreen;
		credits.add(new Credit("HeroCode", "ClickGui\n\nYouTube: HeroCode\nTwitter: @HeroCodeDE"));
		credits.add(new Credit("Vitox", "Particle-System\n\nYouTube: Vitox\nTwitter: @realViTox"));
		credits.add(new Credit("NxtByte", "OutlineUtils\n\nYouTube: nxtByte\nTwitter: @itsBen_YT"));
		credits.add(new Credit("Praisekek", "Tracers\n\nFrom the \"masterof13fps.de\" Forum"));
		
		current = credits.get(0).getName();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution sr = new ScaledResolution(mc);
		this.buttonList.add(new GuiButton(1, 10, sr.getScaledHeight() - 35, "Back"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		drawDefaultBackground();
		
		ScaledResolution sr = new ScaledResolution(mc);

		drawRect(10, 10, 10 + 200, sr.getScaledHeight() - 50, Integer.MIN_VALUE);
		drawRect(10 + 210, 10, 10 + 180 + sr.getScaledWidth() - 200, sr.getScaledHeight() - 10, Integer.MIN_VALUE);

		int yModif = 0;
		int yModif1 = 0;
		
		for(Credit credit : credits) {
			Fibel.bar.drawString(credit.getName(), 10 + 5, 10 + 2 + yModif, 0xffffff);
			if(credit.getName().equalsIgnoreCase(current)) {
				for(String s : credit.getDescription().split("\n")) {
					Fibel.bar.drawString(s, 10 + 210 + 5, 10 + 2 + yModif1, 0xffffff);
					yModif1 += 11;
				}
			}else {
				Fibel.bar.drawString(credit.getName(), 10 + 5, 10 + 2 + yModif, 0xafafaf);
			}
			yModif += 13;
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if(keyCode == Keyboard.KEY_ESCAPE) {
			this.mc.displayGuiScreen(oldScreen);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		
		if(button.id == 1) {
			this.mc.displayGuiScreen(oldScreen);
		}
		
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		int yModif = 0;
		int yModif1 = 0;
		
		for(Credit credit : credits) {
			
			if(mouseY >= 10 + 2 + yModif && mouseY <= 10 + 2 + yModif + Fibel.bar.getStringHeight(credit.getName()) && mouseX >= 10 + 5 - 2 && mouseX <= 10 + 5 + Fibel.bar.getStringWidth(credit.getName()) + 2) {
				current = credit.getName();
			}
			
			yModif += 13;
		}
		
	}
	
	public GuiScreen getOldScreen() {
		return oldScreen;
	}

}
