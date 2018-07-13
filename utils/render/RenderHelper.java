package us.interact.utils.render;

import java.awt.Color;
import java.nio.FloatBuffer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import us.interact.utils.ingame.BlockUtils;

import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public final class RenderHelper {
	private static final Vec3 field_82884_b = new Vec3(0.20000000298023224D, 1.0D, -0.699999988079071D).normalize();
	private static final Vec3 field_82885_c = new Vec3(-0.20000000298023224D, 1.0D, 0.699999988079071D).normalize();
	private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);

	public static void color(int color, float alpha) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		GL11.glColor4f(red, green, blue, alpha);
	}

	public static void blockBoxESP(BlockPos blockPos, Color color) {
		Minecraft.getMinecraft().getRenderManager();
		double x = blockPos.getX() - RenderManager.renderPosX;
		Minecraft.getMinecraft().getRenderManager();
		double y = blockPos.getY() - RenderManager.renderPosY;
		Minecraft.getMinecraft().getRenderManager();
		double z = blockPos.getZ() - RenderManager.renderPosZ;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(3042);
		GL11.glLineWidth(2.0F);
		GL11.glColor4d(0.0D, 1.0D, 0.0D, 0.15000000596046448D);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		color(color.getRGB(), 0.3F);
		Block b = BlockUtils.getBlock(blockPos);
		if(b == Blocks.stone_brick_stairs || b == Blocks.stone_stairs || b == Blocks.acacia_stairs || b == Blocks.birch_stairs || b == Blocks.dark_oak_stairs || b == Blocks.jungle_stairs || b == Blocks.nether_brick_stairs || b == Blocks.oak_stairs || b == Blocks.quartz_stairs || b == Blocks.red_sandstone_stairs || b == Blocks.sandstone_stairs || b == Blocks.spruce_stairs || b == Blocks.spruce_stairs)
			b = Blocks.stone;
		AxisAlignedBB axis = new AxisAlignedBB(x + b.getBlockBoundsMinX(), y + b.getBlockBoundsMinY(), z + b.getBlockBoundsMinZ(), x + b.getBlockBoundsMaxX(), y + b.getBlockBoundsMaxY(), z + b.getBlockBoundsMaxZ());
		drawColorBox(axis);

		RenderGlobal.drawOutlinedBoundingBox(axis, color.getRGB());
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
	}

	public static void blockOutlineESP(BlockPos blockPos, Color color) {
		Minecraft.getMinecraft().getRenderManager();
		double x = blockPos.getX() - RenderManager.renderPosX;
		Minecraft.getMinecraft().getRenderManager();
		double y = blockPos.getY() - RenderManager.renderPosY;
		Minecraft.getMinecraft().getRenderManager();
		double z = blockPos.getZ() - RenderManager.renderPosZ;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(3042);
		GL11.glLineWidth(2.0F);
		GL11.glColor4d(0.0D, 1.0D, 0.0D, 0.15000000596046448D);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		color(color.getRGB(), 0.3F);
		Block b = BlockUtils.getBlock(blockPos);
		if(b == Blocks.stone_brick_stairs || b == Blocks.stone_stairs || b == Blocks.acacia_stairs || b == Blocks.birch_stairs || b == Blocks.dark_oak_stairs || b == Blocks.jungle_stairs || b == Blocks.nether_brick_stairs || b == Blocks.oak_stairs || b == Blocks.quartz_stairs || b == Blocks.red_sandstone_stairs || b == Blocks.sandstone_stairs || b == Blocks.spruce_stairs || b == Blocks.spruce_stairs)
			b = Blocks.stone;
		AxisAlignedBB axis = new AxisAlignedBB(x + b.getBlockBoundsMinX(), y + b.getBlockBoundsMinY(), z + b.getBlockBoundsMinZ(), x + b.getBlockBoundsMaxX(), y + b.getBlockBoundsMaxY(), z + b.getBlockBoundsMaxZ());
		
		RenderGlobal.drawOutlinedBoundingBox(axis, color.getRGB());
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
	}

	public static void blockESP(BlockPos blockPos, Color color) {
		Minecraft.getMinecraft().getRenderManager();
		double x = blockPos.getX() - RenderManager.renderPosX;
		Minecraft.getMinecraft().getRenderManager();
		double y = blockPos.getY() - RenderManager.renderPosY;
		Minecraft.getMinecraft().getRenderManager();
		double z = blockPos.getZ() - RenderManager.renderPosZ;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(3042);
		GL11.glLineWidth(2.0F);
		GL11.glColor4d(0.0D, 1.0D, 0.0D, 0.15000000596046448D);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		color(color.getRGB(), 0.3F);
		Block b = BlockUtils.getBlock(blockPos);
		if(b == Blocks.stone_brick_stairs || b == Blocks.stone_stairs || b == Blocks.acacia_stairs || b == Blocks.birch_stairs || b == Blocks.dark_oak_stairs || b == Blocks.jungle_stairs || b == Blocks.nether_brick_stairs || b == Blocks.oak_stairs || b == Blocks.quartz_stairs || b == Blocks.red_sandstone_stairs || b == Blocks.sandstone_stairs || b == Blocks.spruce_stairs || b == Blocks.spruce_stairs)
			b = Blocks.stone;
		AxisAlignedBB axis = new AxisAlignedBB(x + b.getBlockBoundsMinX(), y + b.getBlockBoundsMinY(), z + b.getBlockBoundsMinZ(), x + b.getBlockBoundsMaxX(), y + b.getBlockBoundsMaxY(), z + b.getBlockBoundsMaxZ());
		drawColorBox(axis);
		
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
	}

	public static void drawColorBox(AxisAlignedBB axisalignedbb) {
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer wr = ts.getWorldRenderer();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		ts.draw();
	}

	public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, Color color) {
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);

		GL11.glDisable(3553);
		GL11.glEnable(2848);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		color(color.getRGB(), 1);
		drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
		color(color.getRGB(), 0.2f);
		drawFilledBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
		GL11.glDisable(2848);
		GL11.glEnable(3553);

		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public static void drawOutlinedClearEntityESP(double x, double y, double z, double width, double height, Color color) {
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);

		GL11.glDisable(3553);
		GL11.glEnable(2848);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		color(color.getRGB(), 1);
		drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
		color(color.getRGB(), 0.2f);
		GL11.glDisable(2848);
		GL11.glEnable(3553);

		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public static void drawCoolLines(AxisAlignedBB mask) {
		GL11.glPushMatrix();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public static void drawBorderedRect(float x, float y, float x2, float y2, float l1, int col1, int col2) {
		drawRect(x, y, x2, y2, col2);

		float f = (col1 >> 24 & 0xFF) / 255.0F;
		float f1 = (col1 >> 16 & 0xFF) / 255.0F;
		float f2 = (col1 >> 8 & 0xFF) / 255.0F;
		float f3 = (col1 & 0xFF) / 255.0F;

		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848);

		GL11.glPushMatrix();
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glLineWidth(l1);
		GL11.glBegin(1);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glEnd();
		GL11.glPopMatrix();

		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glDisable(2848);
	}

	public static void drawFilledBox(AxisAlignedBB mask) {
		WorldRenderer worldRenderer = Tessellator.instance.getWorldRenderer();
		Tessellator tessellator = Tessellator.instance;
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(mask.minX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.maxZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.maxZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(mask.minX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(mask.minX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.maxZ);
		worldRenderer.addVertex(mask.minX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.minX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.minZ);
		worldRenderer.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		worldRenderer.addVertex(mask.maxX, mask.minY, mask.maxZ);
		tessellator.draw();
	}

	public static void glColor(Color color) {
		GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F,
				color.getAlpha() / 255.0F);
	}

	public static void glColor(int hex) {
		float alpha = (hex >> 24 & 0xFF) / 255.0F;
		float red = (hex >> 16 & 0xFF) / 255.0F;
		float green = (hex >> 8 & 0xFF) / 255.0F;
		float blue = (hex & 0xFF) / 255.0F;
		GL11.glColor4f(red, green, blue, alpha);
	}

	public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
		GL11.glEnable(1536);
		GL11.glShadeModel(7425);
		GL11.glBegin(7);
		glColor(topColor);
		GL11.glVertex2f(x, y1);
		GL11.glVertex2f(x1, y1);
		glColor(bottomColor);
		GL11.glVertex2f(x1, y);
		GL11.glVertex2f(x, y);
		GL11.glEnd();
		GL11.glShadeModel(7424);
		GL11.glDisable(1536);
	}

	public static void drawLines(AxisAlignedBB mask) {
		GL11.glPushMatrix();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
		GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
		GL11.glEnd();
		GL11.glBegin(2);
		GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
		GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public static void drawOutlinedBoundingBox(AxisAlignedBB mask) {
		WorldRenderer var2 = Tessellator.instance.getWorldRenderer();
		Tessellator var1 = Tessellator.instance;
		var2.startDrawing(3);
		var2.addVertex(mask.minX, mask.minY, mask.minZ);
		var2.addVertex(mask.maxX, mask.minY, mask.minZ);
		var2.addVertex(mask.maxX, mask.minY, mask.maxZ);
		var2.addVertex(mask.minX, mask.minY, mask.maxZ);
		var2.addVertex(mask.minX, mask.minY, mask.minZ);
		var1.draw();
		var2.startDrawing(3);
		var2.addVertex(mask.minX, mask.maxY, mask.minZ);
		var2.addVertex(mask.maxX, mask.maxY, mask.minZ);
		var2.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		var2.addVertex(mask.minX, mask.maxY, mask.maxZ);
		var2.addVertex(mask.minX, mask.maxY, mask.minZ);
		var1.draw();
		var2.startDrawing(1);
		var2.addVertex(mask.minX, mask.minY, mask.minZ);
		var2.addVertex(mask.minX, mask.maxY, mask.minZ);
		var2.addVertex(mask.maxX, mask.minY, mask.minZ);
		var2.addVertex(mask.maxX, mask.maxY, mask.minZ);
		var2.addVertex(mask.maxX, mask.minY, mask.maxZ);
		var2.addVertex(mask.maxX, mask.maxY, mask.maxZ);
		var2.addVertex(mask.minX, mask.minY, mask.maxZ);
		var2.addVertex(mask.minX, mask.maxY, mask.maxZ);
		var1.draw();
	}

	public static void drawRect(float g, float h, float i, float j, int col1) {
		float f = (col1 >> 24 & 0xFF) / 255.0F;
		float f1 = (col1 >> 16 & 0xFF) / 255.0F;
		float f2 = (col1 >> 8 & 0xFF) / 255.0F;
		float f3 = (col1 & 0xFF) / 255.0F;

		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848);

		GL11.glPushMatrix();
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glBegin(7);
		GL11.glVertex2d(i, h);
		GL11.glVertex2d(g, h);
		GL11.glVertex2d(g, j);
		GL11.glVertex2d(i, j);
		GL11.glEnd();
		GL11.glPopMatrix();

		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glDisable(2848);
	}

	public static void enableGUIStandardItemLighting() {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(165.0F, 1.0F, 0.0F, 0.0F);
		enableStandardItemLighting();
		GlStateManager.popMatrix();
	}

	public static void enableStandardItemLighting() {
		GlStateManager.enableLighting();
		GlStateManager.enableBooleanStateAt(0);
		GlStateManager.enableBooleanStateAt(1);
		GlStateManager.enableColorMaterial();
		GlStateManager.colorMaterial(1032, 5634);
		float var0 = 0.4F;
		float var1 = 0.6F;
		float var2 = 0.0F;
		GL11.glLight(16384, 4611,
				setColorBuffer(field_82884_b.xCoord, field_82884_b.yCoord, field_82884_b.zCoord, 0.0D));
		GL11.glLight(16384, 4609, setColorBuffer(var1, var1, var1, 1.0D));
		GL11.glLight(16384, 4608, setColorBuffer(0.0D, 0.0D, 0.0D, 1.0D));
		GL11.glLight(16384, 4610, setColorBuffer(var2, var2, var2, 1.0D));
		GL11.glLight(16385, 4611,
				setColorBuffer(field_82885_c.xCoord, field_82885_c.yCoord, field_82885_c.zCoord, 0.0D));
		GL11.glLight(16385, 4609, setColorBuffer(var1, var1, var1, 1.0D));
		GL11.glLight(16385, 4608, setColorBuffer(0.0D, 0.0D, 0.0D, 1.0D));
		GL11.glLight(16385, 4610, setColorBuffer(var2, var2, var2, 1.0D));
		GlStateManager.shadeModel(7424);
		GL11.glLightModel(2899, setColorBuffer(var0, var0, var0, 1.0D));
	}

	private static FloatBuffer setColorBuffer(double p_74517_0_, double p_74517_2_, double p_74517_4_,
			double p_74517_6_) {
		return setColorBuffer((float) p_74517_0_, (float) p_74517_2_, (float) p_74517_4_, (float) p_74517_6_);
	}

	public static void disableStandardItemLighting() {
		GlStateManager.disableLighting();
		GlStateManager.disableBooleanStateAt(0);
		GlStateManager.disableBooleanStateAt(1);
		GlStateManager.disableColorMaterial();
	}

	public static void BlockESPBox(BlockPos blockPos, Color color) {
		double x = blockPos.getX() - RenderManager.renderPosX;
		double y = blockPos.getY() - RenderManager.renderPosY;
		double z = blockPos.getZ() - RenderManager.renderPosZ;
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.color(0.0F, 0.0F, 0.0F, 0.4F);
		GL11.glLineWidth(1.0F);
		GlStateManager.func_179090_x();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		color(color.getRGB(), 0.125F);
		drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
		RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D),
				Color.black.hashCode());
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
		GlStateManager.func_179098_w();
		GlStateManager.disableBlend();
	}
	
	public static void renderOne() {
		checkSetupFBO();
		GL11.glPushAttrib(1048575);
		GL11.glDisable(3008);
		GL11.glDisable(3553);
		GL11.glDisable(2896);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(3.0F);
		GL11.glEnable(2848);
		GL11.glEnable(2960);
		GL11.glClear(1024);
		GL11.glClearStencil(15);
		GL11.glStencilFunc(512, 1, 15);
		GL11.glStencilOp(7681, 7681, 7681);
		GL11.glPolygonMode(1032, 6913);
	}

	public static void renderTwo() {
		GL11.glStencilFunc(512, 0, 15);
		GL11.glStencilOp(7681, 7681, 7681);
		GL11.glPolygonMode(1032, 6914);
	}

	public static void renderThree() {
		GL11.glStencilFunc(514, 1, 15);
		GL11.glStencilOp(7680, 7680, 7680);
		GL11.glPolygonMode(1032, 6913);
	}

	public static void renderFour() {
		GL11.glDepthMask(false);
		GL11.glDisable(2929);
		GL11.glEnable(10754);
		GL11.glPolygonOffset(1.0F, -2000000.0F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
	}

	public static void renderFive() {
		GL11.glPolygonOffset(1.0F, 2000000.0F);
		GL11.glDisable(10754);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(2960);
		GL11.glDisable(2848);
		GL11.glHint(3154, 4352);
		GL11.glEnable(3042);
		GL11.glEnable(2896);
		GL11.glEnable(3553);
		GL11.glEnable(3008);
		GL11.glPopAttrib();
	}

	public static void setColor(Color c) {
		GL11.glColor4d(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
	}

	public static void checkSetupFBO() {
		Framebuffer fbo = Minecraft.getMinecraft().getFramebuffer();
		if (fbo != null) {
			if (fbo.depthBuffer > -1) {
				setupFBO(fbo);

				fbo.depthBuffer = -1;
			}
		}
	}

	public static void setupFBO(Framebuffer fbo) {
		EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.depthBuffer);

		int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();

		EXTFramebufferObject.glBindRenderbufferEXT(36161, stencil_depth_buffer_ID);

		EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);

		EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencil_depth_buffer_ID);

		EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencil_depth_buffer_ID);
	}

}
