package us.interact.ui.gui;

import java.io.IOException;
import java.net.Proxy;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import de.vitox.particle.ParticleGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import us.interact.utils.render.ParticleUtils;

public class AltLogin extends GuiScreen {
	
	private GuiScreen oldScreen;
	private ScaledResolution sr;
	private GuiTextField text;
	private String status;
    
    private ParticleGenerator particles;
	
	public AltLogin(GuiScreen oldScreen) {
		this.oldScreen = oldScreen;
		this.mc = Minecraft.getMinecraft();
		sr = new ScaledResolution(mc);
	}
	
	@Override
	public void initGui() {
		sr = new ScaledResolution(mc);
		this.buttonList.add(new GuiButton(1, sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 2, "Login"));
		this.buttonList.add(new GuiButton(0, sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 2 + 25, "Cancel"));
		text = new GuiTextField(2, fontRendererObj, sr.getScaledWidth() / 2 - 99, sr.getScaledHeight() / 2 - 23, 195, 18);
		text.setMaxStringLength(200);
		text.setFocused(true);
		text.setEnabled(true);
		status = "§7Username: §c" + mc.session.getUsername();
        
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		particles = new ParticleGenerator(450);
		
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		sr = new ScaledResolution(mc);
    	this.drawDefaultBackground();

    	ParticleUtils.update(particles.particles, mouseY, mouseX);
		particles.drawParticles(mouseX, mouseY, partialTicks);
		
		text.updateCursorCounter();
		text.drawTextBox();
		drawCenteredString(fontRendererObj, status, sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 35, 0xFFFFFF);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton b) throws IOException {
		
		if(b.id == 1) {
			status = (text.getText().contains(":") ? login(text.getText().split(":")[0].trim(), text.getText().split(":")[1].trim()) : login(text.getText().trim()));
		}else if(b.id == 0) {
			mc.displayGuiScreen(oldScreen);
		}
		
		super.actionPerformed(b);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		text.textboxKeyTyped(typedChar, keyCode);
		if(keyCode == Keyboard.KEY_ESCAPE)
			actionPerformed((GuiButton) buttonList.get(1));
		if(keyCode == Keyboard.KEY_RETURN)
			actionPerformed((GuiButton) buttonList.get(0));
		super.keyTyped(typedChar, keyCode);
	}
	
	public String login(String username, String password) {
		YggdrasilUserAuthentication ua = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
		ua.setUsername(username.trim());
		ua.setPassword(password.trim());
		
		try {
			
			ua.logIn();
			mc.session = new Session(ua.getSelectedProfile().getName(), ua.getSelectedProfile().getId().toString(), ua.getAuthenticatedToken(), "mojang");
			return "§7Username: §c" + mc.session.getUsername();
			
		} catch (Exception e) {
			return "§cEmail oder Passwort falsch? Vieleicht auch ein mojang Ban";
		}
	}
	
	public String login(String username) {
		if(!username.isEmpty()) {
			mc.session = new Session(username, "", "", "");
			return "§7Username: §c" + mc.session.getUsername();
		}
		return "§7Username: §c" + mc.session.getUsername();
	}

}
