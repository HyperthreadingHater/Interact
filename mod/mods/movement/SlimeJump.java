package us.interact.mod.mods.movement;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.PlayerHelper;

public class SlimeJump extends Mod {

	public SlimeJump() {
		super("SlimeJump", 0, true, Category.MOVEMENT);
	}

	@Override
	public void onUpdate() {
		if (mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ)).getBlock() == Blocks.slime_block && mc.thePlayer.onGround) {
			mc.thePlayer.motionY = 1.5;
			mc.timer.timerSpeed = 1.000005f;
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		mc.timer.timerSpeed = 1f;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}

}
