package us.interact.ui.gui;

import org.lwjgl.Sys;

import net.minecraft.client.gui.Gui;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.ui.font.Blade;
import us.interact.ui.font.Fibel;
import us.interact.utils.render.ColorUtil;

public class TabGui extends Gui {

	public static double scale = 0.9D;
	public static int currentMod = 0, currentTab = 0, allTabs = 0, startY = 80, startX = 3;
	public static boolean extended = false;

	public static void init() {
		for (Category category : Category.values()) {
			allTabs++;
		}
	}

	public void draw() {
		int y = 0;
		int i = 0;
		for (Category category : Category.values()) {
			if (i == currentTab) {
				drawGradientRect(3 + startX, 25 + y + startY, (95 + startX) * scale, 40 + y + startY, -65536, -65536 + 30);
			} else {
				drawGradientRect(3 + startX, 25 + y + startY, (95 + startX) * scale, 40 + y + startY, 0xFF262626, 0xFF252525);
			}
			Fibel.tab.drawString(category.name(), 5 + startX, 22 + y + 2 + startY, 0xFFFFFF);
			i++;
			y += 15;
		}
		if(extended) {
			Category cc = getCurrentCategory();
			int y1 = 25 + startY;
			int y2 = 40 + startY;
			for(Category category : Category.values()) {
				if(category != cc) {
					y1 += 15;
					y2 += 15;
				}else 
					break;
			}
			int y3 = 0;
			int i1 = 0;
			for(Mod m : ModManager.getMods()) {
				if(m.getCategory() == cc && m.isVisible() && !m.getName().equalsIgnoreCase("GUI")) {

					if(i1 == currentMod) {
						drawGradientRect((95 + startX) * scale, y1 + y3, (187 + startX) * scale, y2 + y3, -65536, -65536 + 30);
						Fibel.tab.drawString(m.getName(),(int) ((97 + startX) * scale), y1 + y3, 0xFFFFFF);
					}else {
						drawGradientRect((95 + startX) * scale, y1 + y3, (187 + startX) * scale, y2 + y3, 0xFF262626, 0xFF252525);
						if(!m.isEnabled()) 
							Fibel.tab.drawString(m.getName(), (int) ((97 + startX) * scale), y1 + y3, 0xFFFFFF);
						else
							Fibel.tab.drawString(m.getName(), (int) ((97 + startX) * scale), y1 + y3, ColorUtil.getHUDColor().getRGB());
					}
					
					
					i1++;
					y3 += 15;
				}
			}
		}
	}

	public Category getCurrentCategory() {
		int i = 0;
		for(Category category : Category.values()) 
			if(i == currentTab)
				return category;
			else
				i++;
		return null;
	}
	
	public Mod getCurrentMod() {
		int i = 0;
		Category c = getCurrentCategory();
		for(Mod m : ModManager.getMods()) {
			if(m.isVisible() && m.getCategory() == c && !m.getName().equalsIgnoreCase("GUI")) {
				if(i == currentMod)
					return m;
				i++;
			}
		}
		return null;
	}
	
}
