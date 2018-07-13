package us.interact.mod.mods.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.FriendManager;
import us.interact.utils.render.ColorUtil;
import us.interact.utils.render.RenderHelper;

public class ChestESP extends Mod {

	public ChestESP() {
		super("ChestESP", Keyboard.KEY_NONE, true, Category.RENDER);
		setInConfig(false);
	}

	public void onRender() {
		for (Object o : this.mc.theWorld.loadedTileEntityList) {
			if ((o instanceof TileEntityChest)) {
					TileEntityChest ec = (TileEntityChest) o;
					if(!getSetting("Rainbow")) {
						if(getMode().equalsIgnoreCase("Box")) {
							RenderHelper.blockBoxESP(ec.getPos(), ColorUtil.getHUDColor());
						}else if(getMode().equalsIgnoreCase("Outline")) {
							RenderHelper.blockOutlineESP(ec.getPos(), ColorUtil.getHUDColor());
						}else if(getMode().equalsIgnoreCase("Block")) {
							RenderHelper.blockESP(ec.getPos(), ColorUtil.getHUDColor());
						}
					}else {
						if(getMode().equalsIgnoreCase("Box")) {
							RenderHelper.blockBoxESP(ec.getPos(), ColorUtil.rainbowEffect(0, 1));
						}else if(getMode().equalsIgnoreCase("Outline")) {
							RenderHelper.blockOutlineESP(ec.getPos(), ColorUtil.rainbowEffect(0, 1));
						}else if(getMode().equalsIgnoreCase("Block")) {
							RenderHelper.blockESP(ec.getPos(), ColorUtil.rainbowEffect(0, 1));
						}
					}
				}
			}
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
