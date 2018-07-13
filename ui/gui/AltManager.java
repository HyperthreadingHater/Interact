package us.interact.ui.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;

import org.lwjgl.Sys;
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
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.util.Session;
import us.interact.ui.font.Raleway;
import us.interact.utils.other.FileManager;
import us.interact.utils.other.SystemUtils;
import us.interact.utils.render.Account;

public class AltManager extends GuiScreen {

	private ArrayList<Account> accounts = new ArrayList<>();

	private Account selectedAccount;

	/*
	 * Fields
	 */
	private GuiTextField username;
	private GuiPasswordField password;

	/*
	 * Buttons
	 */
	private GuiButton clipboardlogin;
	private GuiButton cancle;
	private GuiButton login;
	private GuiButton add;
	private GuiButton del;

	private ParticleGenerator particles;

	private GuiScreen oldScreen;

	private String status;

	public AltManager(GuiScreen oldScreen) {
		this.oldScreen = oldScreen;
		mc = Minecraft.getMinecraft();
		if (mc.session != null)
			status = "§7Username§8: §c" + mc.session.getUsername();
		else
			status = "§7Username§8: §c" + "InteractUser";

	}

	@Override
	public void initGui() {
		super.initGui();

		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

		username = new GuiTextField(0, mc.fontRendererObj, 10, sr.getScaledHeight() - 111, 180, 20);
		username.setFocused(true);
		username.setEnabled(true);
		username.setMaxStringLength(200);

		password = new GuiPasswordField(0, mc.fontRendererObj, 10, sr.getScaledHeight() - 81, 180, 20);
		password.setFocused(false);
		password.setEnabled(true);
		password.setMaxStringLength(200);

		clipboardlogin = new GuiButton(1, 10, sr.getScaledHeight() - 51, 180, 20, "Clipboard Login");
		buttonList.add(clipboardlogin);

		cancle = new GuiButton(2, 10, sr.getScaledHeight() - 26, 180, 20, "Zurück");
		buttonList.add(cancle);

		add = new GuiButton(3, 200, sr.getScaledHeight() - 51, 180, 20, "Add");
		buttonList.add(add);

		del = new GuiButton(4, 390, sr.getScaledHeight() - 51, 180, 20, "Delete");
		buttonList.add(del);

		login = new GuiButton(5, 580, sr.getScaledHeight() - 51, 180, 20, "Login");
		buttonList.add(login);

		accounts = FileManager.loadAlts();

		if (accounts == null)
			accounts = new ArrayList<>();

		if (accounts.size() > 0)
			selectedAccount = accounts.get(0);

		particles = new ParticleGenerator(450);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		drawDefaultBackground();
		particles.drawParticles(mouseX, mouseY, partialTicks);

		drawRect(200, 30, sr.getScaledWidth() - 10, sr.getScaledHeight() - 60, Integer.MIN_VALUE);

		int y = 1;

		Color c = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 185);

		if (selectedAccount != null) {
			for (Account account : accounts) {
				if (selectedAccount == account)
					drawRect(201, 30 + y, sr.getScaledWidth() / 1.5, 30 + 40 + y, c.getRGB());
				else
					drawRect(201, 30 + y, sr.getScaledWidth() / 1.5, 30 + 40 + y, Integer.MAX_VALUE);
				fontRendererObj.drawString(account.getEmail(), 201 + 4, 34 + y, 0xff000000);
				y += 41;
			}
		}

		fontRendererObj.drawString(status, sr.getScaledWidth() - 10 - fontRendererObj.getStringWidth(status), 15,
				0xffffffff);

		password.drawTextBox();
		password.updateCursorCounter();
		username.drawTextBox();
		username.updateCursorCounter();

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);

		if (keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(oldScreen);
			return;
		}

		if (password.isFocused())
			password.textboxKeyTyped(typedChar, keyCode);
		if (username.isFocused())
			username.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);

		if (button.id == 1) {
			String clipboard = SystemUtils.getClipboard();
			if (clipboard.split(":").length == 2) {
				status = login(clipboard.split(":")[0], clipboard.split(":")[1]);
			} else {
				status = "§7In deiner §cZwischenablage §7befindet sich §cKEIN §7Account!";
			}
		} else if (button.id == 2) {
			mc.displayGuiScreen(oldScreen);
		} else if (button.id == 3) {
			if (!username.getText().trim().equalsIgnoreCase("") && !password.getText().trim().equalsIgnoreCase("")) {
				Account acc = new Account(username.getText(), password.getText());
				username.setText("");
				password.setText("");
				if (canLogin(acc.getEmail(), acc.getPassword())) {
					if (accounts.size() < 1)
						selectedAccount = acc;
					accounts.add(acc);
				} else {
					status = "§cEmail oder Passwort falsch? Vieleicht auch ein mojang Ban";
				}
			} else if (!username.getText().trim().equalsIgnoreCase("") && password.getText().trim().equalsIgnoreCase("")) {
				Account acc = new Account(username.getText(), "NONE");
				if (accounts.size() < 1)
					selectedAccount = acc;
				accounts.add(acc);
			} else {
				status = "§7Du §cmusst §7eine Alt eingeben!";
			}
		} else if (button.id == 4) {
			if (accounts.size() > 0) {
				accounts.remove(selectedAccount);
				if (accounts.size() > 0)
					selectedAccount = accounts.get(0);
				else
					selectedAccount = null;
			}
		} else if (button.id == 5) {
			if(username.getText().trim().equalsIgnoreCase("") && password.getText().trim().equalsIgnoreCase("")) {
				if (selectedAccount != null) {
					if (selectedAccount.getPassword().equalsIgnoreCase("NONE")) {
						status = login(selectedAccount.getEmail());
					} else {
						status = login(selectedAccount.getEmail(), selectedAccount.getPassword());
					}
				} else {
					status = "§7Es ist §ckein §7Alt ausgewählt!";
				}
			}else {
				if (!username.getText().trim().equalsIgnoreCase("") && password.getText().trim().equalsIgnoreCase("")) {
					status = login(username.getText());
					username.setText("");
					password.setText("");
				} else if (!username.getText().trim().equalsIgnoreCase("") && !password.getText().trim().equalsIgnoreCase("")) {
					status = login(username.getText(), password.getText());
					username.setText("");
					password.setText("");
				} else {
					status = "§7Du §cmusst §7eine Alt eingeben!";
				}
			}
		}

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		password.mouseClicked(mouseX, mouseY, mouseButton);
		username.mouseClicked(mouseX, mouseY, mouseButton);
		ScaledResolution sr = new ScaledResolution(mc);

		int y = 0;
		for (Account account : accounts) {
			if (mouseX > 201 && mouseX < sr.getScaledWidth() / 1.5) {
				if (mouseY > 30 + y && mouseY < 30 + 40 + y) {
					selectedAccount = account;
				}
			}
			y += 41;
		}

	}

	@Override
	public void onGuiClosed() {
		FileManager.saveAlts(accounts);
		super.onGuiClosed();
	}

	public boolean canLogin(String u, String pw) {
		YggdrasilUserAuthentication ua = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(
				Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
		ua.setUsername(u.trim());
		ua.setPassword(pw.trim());

		return ua.canLogIn();
	}

	public String login(String username, String password) {
		YggdrasilUserAuthentication ua = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(
				Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
		ua.setUsername(username.trim());
		ua.setPassword(password.trim());

		try {
			ua.logIn();
			mc.session = new Session(ua.getSelectedProfile().getName(), ua.getSelectedProfile().getId().toString(),
					ua.getAuthenticatedToken(), "mojang");
			return "§7Username§8: §c" + mc.session.getUsername();

		} catch (Exception e) {
			return "§cEmail oder Passwort falsch? Vieleicht auch ein mojang Ban";
		}
	}

	public String login(String username) {
		if (!username.isEmpty()) {
			mc.session = new Session(username, "", "", "");
			return "§7Username: §c" + mc.session.getUsername();
		}
		return "§7Username: §c" + mc.session.getUsername();
	}

}
