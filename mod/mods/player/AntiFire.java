package us.interact.mod.mods.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import us.interact.mod.Category;
import us.interact.mod.Mod;

public class AntiFire extends Mod{

	public AntiFire() {
		super("AntiFire", Keyboard.KEY_NONE, true, Category.PLAYER);
	}
	public void onUpdate() {
		 if (this.mc.thePlayer.isBurning()) {
		      for (int i = 0; i < 15; i++) {
		        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		      }
		    }
		  }
		
	}

