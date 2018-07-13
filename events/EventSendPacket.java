package us.interact.events;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;

import net.minecraft.network.Packet;

public class EventSendPacket implements Event, Cancellable {

	private boolean cancled;
	private Packet packet;
	
	public EventSendPacket(Packet packet) {
		this.packet = packet;
	}
	
	@Override
	public boolean isCancelled() {
		return cancled;
	}

	@Override
	public void setCancelled(boolean cancled) {
		this.cancled = cancled;
	}
	
	public Packet getPacket() {
		return packet;
	}
	
	public void setPacket(Packet packet) {
		this.packet = packet;
	}

}
