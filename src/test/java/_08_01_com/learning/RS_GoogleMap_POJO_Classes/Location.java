package _08_01_com.learning.RS_GoogleMap_POJO_Classes;

public class Location {

	private double lat;
	private double lng;
	
	public Location(double d, double e) {
		super();
		this.lat = d;
		this.lng = e;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
}
