package us.interact.events;

import com.darkmagician6.eventapi.events.Event;

import net.minecraft.util.IChatComponent;

public class EventServerDisconnected implements Event {

	private IChatComponent reason;

	public EventServerDisconnected(IChatComponent reason) {
		this.reason = reason;
	}
	
	public IChatComponent getReason() {
		return reason;
	}
	
	public void setReason(IChatComponent reason) {
		this.reason = reason;
	}

}
