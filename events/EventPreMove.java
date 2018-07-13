package us.interact.events;

import com.darkmagician6.eventapi.events.Event;

import net.minecraft.util.EnumFacing;
import us.interact.utils.other.Location;

public class EventPreMove implements Event {
	
	private EnumFacing direction;
	private Location from, to;
	
	public EventPreMove(EnumFacing direction, Location from, Location to) {
		this.direction = direction;
		this.from = from;
		this.to = to;
	}
	
	public EnumFacing getDirection() {
		return direction;
	}
	
	public Location getFrom() {
		return from;
	}
	
	public Location getTo() {
		return to;
	}
	
	public void setTo(Location to) {
		this.to = to;
	}

}
