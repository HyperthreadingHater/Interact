package us.interact.mod.mods.misc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import de.Hero.settings.SettingsManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.JavaSoundAudioDeviceFactory;
import javazoom.jl.player.Player;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Radio extends Mod {

	private static Player player;
	private BufferedInputStream is;
	private String url = "http://stream01.iloveradio.de/iloveradio1.mp3";

	public Radio() {
		super("Radio", 0, true, Category.MISC);
		setInConfig(false);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		url = "http://stream01.iloveradio.de/iloveradio1.mp3";
		new Thread(() -> playMusic(url)).start();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	@Override
	public void onTick() {
		super.onTick();
		setVolume((int) SettingsManager.getSettingByName("Volume", this).getValDouble());
	}

	@Override
	public void onDisable() {
		super.onDisable();
		stopMusic();
	}

	public static void setVolume(int vol) {
		if (player != null && player.getAudio() instanceof JavaSoundAudioDevice) {
			JavaSoundAudioDevice jsad = (JavaSoundAudioDevice) player.getAudio();
			jsad.setLineGain(vol - 100);
		}
	}

	private void stopMusic() {
		if (player != null) {
			player.close();
			player = null;
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void playMusic(String url) {
		try {
			is = new BufferedInputStream(new URL(url).openStream());
			player = new Player(is);
			player.play();
		} catch (Exception e) {
			e.printStackTrace();
			if (is != null)
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
	}

}
