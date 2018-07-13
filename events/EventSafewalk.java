package us.interact.events;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;

public class EventSafewalk implements Event {

	boolean safe;

	public EventSafewalk(boolean safe) {
		this.safe = safe;
	}

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

}
