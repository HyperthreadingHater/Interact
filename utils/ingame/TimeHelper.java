package us.interact.utils.ingame;

public class TimeHelper {
	
	private long currentMS;
	
	public TimeHelper() {
		currentMS = System.currentTimeMillis();
	}
	
	public boolean isDelayCompleted(long ms) {
		return System.currentTimeMillis() - currentMS >= ms;
	}
	
	public void reset() {
		currentMS = System.currentTimeMillis();
	}
	
	public long getDelay() {
		return System.currentTimeMillis() - currentMS;
	}

}
