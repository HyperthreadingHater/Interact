package us.interact.mod.mods.movement;

import de.Hero.settings.SettingsManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class TerrainSpeed extends Mod {

	public TerrainSpeed() {
		super("TerrainSpeed", 0, true, Category.MOVEMENT);
	}
	
	long lastUnder = 0;
	double lastMultiplier = 0;
	
	@Override
	public void onUpdate() {
		if (SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("AAC") || SettingsManager.getSettingByName("Mode", this).getValString().equalsIgnoreCase("AAC")) {
			if(!mc.thePlayer.isInWater() && mc.thePlayer.onGround && !mc.thePlayer.isInWeb) {
				if(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY + 2, mc.thePlayer.posZ)).getBlock() != Blocks.air) {
					lastMultiplier = 1.85;
					mc.thePlayer.motionX *= lastMultiplier;
					mc.thePlayer.motionZ *= lastMultiplier;
					lastUnder = System.currentTimeMillis();
				}else {
					if(lastMultiplier > 1) {
						double oldXZ = lastMultiplier - 0.1;
						lastMultiplier = oldXZ;
						System.out.println(oldXZ);
						if(oldXZ > 1) {
							mc.thePlayer.motionX *= oldXZ;
							mc.thePlayer.motionZ *= oldXZ;
						}
					}
				}
			}
			
			if(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ)).getBlock() == Blocks.slime_block) {
				mc.thePlayer.motionX *= 1.1;
				mc.thePlayer.motionZ *= 1.1;
				if(mc.thePlayer.onGround)
					mc.thePlayer.motionY = 0.4;
				else
					mc.thePlayer.motionY = -0.4;
			}
			
			if(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.waterlily) {
				if(mc.gameSettings.keyBindForward.pressed) {
					mc.thePlayer.motionX *= 1.22;
					mc.thePlayer.motionZ *= 1.22;
					if(mc.thePlayer.onGround)
						mc.thePlayer.motionY = 0.4;
					else
						mc.thePlayer.motionY = -0.4;
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
