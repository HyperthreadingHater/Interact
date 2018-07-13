package us.interact.utils.ingame;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class BlockUtils {
	
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static Block getBlock(BlockPos pos) {
		return getState(pos).getBlock();
	}
	
	public static IBlockState getState(BlockPos pos) {
		return mc.theWorld.getBlockState(pos);
	}

}
