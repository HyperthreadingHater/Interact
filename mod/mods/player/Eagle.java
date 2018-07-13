package us.interact.mod.mods.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class Eagle extends Mod {

	public Eagle() {
		super("Eagle", Keyboard.KEY_NONE, true, Category.PLAYER);
	}

	public void onUpdate() {
		if ((this.mc.thePlayer != null) && (this.mc.theWorld != null)) {
			BlockPos bp = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 1.0D, this.mc.thePlayer.posZ);
			if (this.mc.thePlayer.fallDistance <= 4.0F) {
				if (this.mc.theWorld.getBlockState(bp).getBlock() != Blocks.air) {
					Minecraft.getMinecraft().gameSettings.keyBindSneak.pressed = false;
				} else {
					Minecraft.getMinecraft().gameSettings.keyBindSneak.pressed = true;
				}
			}
		}
	}

	public void onDisable() {
		super.onDisable();
		this.mc.gameSettings.keyBindSneak.pressed = false;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}

}
