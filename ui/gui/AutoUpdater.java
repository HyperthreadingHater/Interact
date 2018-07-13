package us.interact.ui.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import us.interact.Interact;
import us.interact.utils.ingame.TimeHelper;

public class AutoUpdater extends GuiScreen {

	TimeHelper time = new TimeHelper();

	us.interact.utils.other.AutoUpdater autoUpdater = null;

	public AutoUpdater(us.interact.utils.other.AutoUpdater autoUpdater) {
		this.autoUpdater = autoUpdater;
	}

	@Override
	public void initGui() {
		Interact.update = true;
		super.initGui();
		ScaledResolution sr = new ScaledResolution(mc);
		time.reset();
	}

	boolean finished = false;
	String state = "Downloading...";

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		drawDefaultBackground();

		ScaledResolution sr = new ScaledResolution(mc);

		super.drawScreen(mouseX, mouseY, partialTicks);

		if (time.isDelayCompleted(1000) && !finished) {
			autoUpdater.download();
			time.reset();
			finished = true;
			state = "Bitte starte den Client erneut!";
		}
		if (time.isDelayCompleted(1000) && finished) {
			mc.shutdownMinecraftApplet();
		}

		mc.fontRendererObj.drawString(state, (sr.getScaledWidth() / 2) - (mc.fontRendererObj.getStringWidth(state) / 2),
				sr.getScaledHeight() / 2, 0xffffff);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}
