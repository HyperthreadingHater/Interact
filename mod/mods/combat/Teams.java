package us.interact.mod.mods.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Teams extends Mod {

	public Teams() {
		super("Teams", Keyboard.KEY_NONE, true, Category.COMBAT);
	}

	public static boolean isInTeam(EntityLivingBase entityplayer) {
		if ((entityplayer instanceof EntityPlayer)) {
			int entityColor = getTeamColor((EntityPlayer) entityplayer);
			if (entityColor == getTeamColor(Minecraft.getMinecraft().thePlayer)) {
				return true;
			}
		}
		return false;
	}

	public static int getTeamColor(EntityPlayer player) {
		int color = 16777215;
		ScorePlayerTeam scoreplayerteam = (ScorePlayerTeam) player.getTeam();
		if (scoreplayerteam != null) {
			String s = FontRenderer.getFormatFromString(scoreplayerteam.getColorPrefix());
			if (s.length() >= 2) {
				color = Minecraft.getMinecraft().fontRendererObj.func_175064_b(s.charAt(1));
			}
		}
		return color;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}
