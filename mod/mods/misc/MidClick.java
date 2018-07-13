package us.interact.mod.mods.misc;

import org.lwjgl.input.Mouse;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.FriendManager;
import us.interact.utils.ingame.PlayerHelper;
import us.interact.utils.ingame.TimeHelper;

public class MidClick extends Mod {

	public MidClick() {
		super("MidClick", 0, true, Category.MISC);
	}
	
	private TimeHelper time = new TimeHelper();
	
	@Override
	public void onUpdate() {
		if(Mouse.isButtonDown(2)) {
			if(time.isDelayCompleted(100)) {
				time.reset();
				MovingObjectPosition mop = mc.objectMouseOver;
				Entity e = mop.entityHit;
				if(e != null) {
					if(!FriendManager.isCmdFriend(e.getName())) {
						FriendManager.addFriend(e.getName(), e.getName());
						PlayerHelper.sendMessage("§c" + e.getName() + " §7wurde zu deinen §cFreunden §7hinzugefügt!");
					}else {
						FriendManager.removeFriend(e.getName());
						PlayerHelper.sendMessage("§c" + e.getName() + " §7wurde von deinen §cFreunden §7entfernt!");
					}
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
