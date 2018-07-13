package us.interact.mod.mods.movement;

import net.minecraft.init.Blocks;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.BlockUtils;
import us.interact.utils.ingame.PlayerHelper;

public class IceSpeed extends Mod {

	public IceSpeed() {
		super("IceSpeed", 0, true, Category.MOVEMENT);
	}

	@Override
	public void onUpdate() {
		if (getMode().equalsIgnoreCase("Vanilla")) {
			Blocks.ice.slipperiness = 2;
			Blocks.packed_ice.slipperiness = 2;
		} else if (getMode().equalsIgnoreCase("Minijump")) {
			if (BlockUtils.getBlock(PlayerHelper.getPlayerPos().add(0, -1, 0)) == Blocks.ice || BlockUtils.getBlock(PlayerHelper.getPlayerPos().add(0, -1, 0)) == Blocks.packed_ice && mc.gameSettings.keyBindForward.pressed) {
				Blocks.ice.slipperiness = 0.98f;
				Blocks.packed_ice.slipperiness = 0.98f;
				if (mc.thePlayer.onGround) {
					PlayerHelper.motionXZ(1.25);
					mc.thePlayer.motionY = 0.1;
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
		Blocks.ice.slipperiness = 0.98f;
		Blocks.packed_ice.slipperiness = 0.98f;
		super.onDisable();
	}

}
