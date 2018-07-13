package us.interact.mod.mods.movement;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;

public class StairSpeed extends Mod {

	public StairSpeed() {
		super("StairSpeed", 0, true, Category.MOVEMENT);
	}

	@Override
	public void onUpdate() {
		if (SettingsManager.getSettingByName("Mode", ModManager.getMod("StairSpeed")).getValString()
				.equalsIgnoreCase("AAC")) {
			Block b = mc.theWorld
					.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ))
					.getBlock();
			if (b == Blocks.stone_brick_stairs || b == Blocks.stone_stairs || b == Blocks.acacia_stairs
					|| b == Blocks.birch_stairs || b == Blocks.dark_oak_stairs || b == Blocks.jungle_stairs
					|| b == Blocks.nether_brick_stairs || b == Blocks.oak_stairs || b == Blocks.quartz_stairs
					|| b == Blocks.red_sandstone_stairs || b == Blocks.red_sandstone_stairs
					|| b == Blocks.sandstone_stairs || b == Blocks.spruce_stairs && mc.thePlayer.onGround
							&& mc.thePlayer.getFoodStats().getFoodLevel() > 3) {
				mc.thePlayer.motionX *= 1.85;
				mc.thePlayer.motionZ *= 1.85;
			}
			Block b1 = mc.theWorld
					.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.1, mc.thePlayer.posZ))
					.getBlock();
			if (b1 == Blocks.stone_slab || b1 == Blocks.stone_slab2 || b1 == Blocks.wooden_slab && mc.thePlayer.onGround
					&& mc.thePlayer.getFoodStats().getFoodLevel() > 3) {
				mc.thePlayer.motionX *= 1.7;
				mc.thePlayer.motionZ *= 1.7;
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
