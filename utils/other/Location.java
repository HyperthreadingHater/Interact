package us.interact.utils.other;

public class Location {
	
	private double x, y, z;
	
	public Location(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public double distanceX(Location to) {
		double x2 = to.getX();
		return x > x2 ? x - x2 : x2 - x;
	}
	
	public double distanceY(Location to) {
		double y2 = to.getY();
		return y > y2 ? y - y2 : y2 - y;
	}
	
	public double distanceZ(Location to) {
		double z2 = to.getZ();
		return z > z2 ? z - z2 : z2 - z;
	}

}
