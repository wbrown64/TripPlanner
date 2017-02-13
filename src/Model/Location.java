package Model;

public class Location {
	protected String id;
	protected String brewery;
	protected String city;
	protected String latitude;
	protected String longitude;
	protected String altitude;
	
	public Location(String id, String brewery, String city, String latitude, String longitude, String altitude) {
		this.id = id;
		this.brewery = brewery;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	
	public String toString() {
		return "id: " + id + ", " + "Brewery: " + brewery + ", City: " + city + ", latitude: " + latitude
				+ ", longitude: " + longitude + ", altitude " + altitude; 
	}
}
