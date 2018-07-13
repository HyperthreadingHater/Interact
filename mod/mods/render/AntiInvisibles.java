package us.interact.mod.mods.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiKeyBindingList.CategoryEntry;
import net.minecraft.entity.Entity;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class AntiInvisibles extends Mod {

	public AntiInvisibles() {
		super("AntiInvisibles", Keyboard.KEY_NONE, true, Category.RENDER);
	}
	
	ArrayList<Entity> entities = new ArrayList<>();

	public void onRender() {
		for (Object o : this.mc.theWorld.loadedEntityList) {
			Entity e = (Entity) o;
			if (e.isInvisible()) {
				e.setInvisible(false);
				if(!entities.contains(e))
					entities.add(e);
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
		for(Entity e : entities) {
			e.setInvisible(true);
		}
		entities.clear();
	}
	
}