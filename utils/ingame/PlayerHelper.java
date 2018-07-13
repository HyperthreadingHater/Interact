package us.interact.utils.ingame;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldSettings.GameType;
import us.interact.Interact;

public class PlayerHelper {

	private static Minecraft mc = Minecraft.getMinecraft();

	public static void sendMessage(String msg) {
		mc.thePlayer.addChatMessage(
				IChatComponent.Serializer.jsonToComponent("{\"text\":\"" + Interact.prefix + msg + "\"}"));
	}

	public static BlockPos getPlayerPos() {
		return new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
	}

	public static void motionXZ(double motion) {
		mc.thePlayer.motionZ *= motion;
		mc.thePlayer.motionX *= motion;
	}

	public static Entity raycast(Entity e, double range) {
		Vec3 vec = e.getPositionVector().add(new Vec3(0, e.getEyeHeight(), 0));
		Vec3 vec1 = mc.thePlayer.getPositionVector().add(new Vec3(0, mc.thePlayer.getEyeHeight(), 0));
		AxisAlignedBB axis = mc.thePlayer.getEntityBoundingBox()
				.addCoord(vec.xCoord - vec1.xCoord, vec.yCoord - vec1.yCoord, vec.zCoord - vec1.zCoord).expand(1, 1, 1);

		Entity nearst = null;
		Vec3 vec2;

		for (Object obj : mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.thePlayer, axis)) {
			Entity en = (Entity) obj;

			if (en.canBeCollidedWith() && en instanceof EntityLivingBase) {
				float size = en.getCollisionBorderSize();
				AxisAlignedBB axis1 = en.getEntityBoundingBox().expand(size, size, size);
				MovingObjectPosition mop = axis1.calculateIntercept(vec1, vec);

				if (axis1.isVecInside(vec1)) {
					if (range >= 0) {
						nearst = en;
						vec2 = mop == null ? vec1 : mop.hitVec;
						range = 0;
					}
				} else if (mop != null) {
					double dist = vec1.distanceTo(mop.hitVec);

					if (range == 0 || dist < range) {
						nearst = en;
						vec2 = mop.hitVec;
						range = dist;
					}
				}
			}
		}

		return nearst;

	}

}
