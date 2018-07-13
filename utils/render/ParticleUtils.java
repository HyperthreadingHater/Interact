package us.interact.utils.render;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import de.vitox.particle.Particle;
import net.minecraft.util.MathHelper;

public class ParticleUtils {
	
	public static void update(ArrayList<Particle> particles, int mouseY, int mouseX) {
    	for (Particle p : particles) {
			for (Particle p2 : particles) {
				int xx = (int) (MathHelper.cos(0.1F * (p.getX())) * 10.0F);
				int xx2 = (int) (MathHelper.cos(0.1F * (p2.getX())) * 10.0F);

				if (p.isHovering(p2.getX(), p2.getY())) {
					if (mouseY >= p.getY() - 100 && mouseX >= p2.getX() - 100 && mouseY >= p2.getY()
							&& mouseY <= p2.getY() + 70 && mouseX <= p2.getX()) {

						int maxDistance = 100;

						final int xDif = p.getX() - mouseX;
						final int yDif = p.getY() - mouseY;
						final int distance = (int) Math.sqrt(xDif * xDif + yDif + yDif);

						final int xDif1 = p2.getX() - mouseX;
						final int yDif1 = p2.getY() - mouseY;
						final int distance2 = (int) Math.sqrt(xDif1 * xDif1 + yDif1 + yDif1);

						if (distance < maxDistance && distance2 < maxDistance) {

							GL11.glPushMatrix();
							GL11.glEnable(GL11.GL_LINE_SMOOTH);
							GL11.glDisable(GL11.GL_DEPTH_TEST);
							GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
							GL11.glDisable(GL11.GL_TEXTURE_2D);
							GL11.glDepthMask(false);
							GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
							GL11.glEnable(GL11.GL_BLEND);
							GL11.glLineWidth(0.5F);
							GL11.glBegin(GL11.GL_LINES);

							GL11.glVertex2i(p.getX() + xx, p.getY());

							GL11.glVertex2i(p2.getX() + xx2, p2.getY());

							GL11.glEnd();
							GL11.glPopMatrix();
						}
					}
				}
			}
		}
	}

}
