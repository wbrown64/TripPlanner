package Model;

public class Coordinates {
	private double x;
	private double y;
	private int elevation;
	private double distance_to_next;
	private double[] latitude;
	private double[] longitude;
	private char lat_direction;
	private char lon_direction;
	
	public Coordinates(String lat, String longe,String elevation){
		latitude=new double[20];
		longitude=new double[20];
		this.elevation=Integer.parseInt(elevation);
		parse_coordinates(lat,longe);
	}
	public void parse_coordinates(String lat, String longe) {
		int counter=0;
	
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDistance() {
		return distance_to_next;
	}

	public void setDistance(double distance_to_next) {
		this.distance_to_next = distance_to_next;
	}

	
	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public static void main(String[] args) {
		Coordinates C=new Coordinates("39°40'47.28\" N","104°59'26.8794\" W","5280");

	}

	

}
