package Model;

public class Coordinates {
	private double x;
	private double y;
	private double distance_to_next;
	private double[] latitude;
	private double[] longitude;
	char lat_direction;
	char long_direction;
	public double dd_lat;
	public double dd_long;
	
	
	public Coordinates(String lat, String longe){
		latitude=new double[3];
		longitude=new double[3];
		parse_strs(lat,latitude,0);
		parse_strs(longe,longitude,1);
		calculate_dd();

	}
	
	public void calculate_dd(){
		dd_lat=(latitude[0]+(latitude[1]/60)+(latitude[2]/3600));
		dd_long=(longitude[0]+(longitude[1]/60)+(longitude[2]/3600))*-1;
		
		//System.out.println(dd_long);
	}
	
	public void parse_strs(String str, double[] values,int val) {
		str=str.replaceAll("[°\'\"]", " ");
//		str=str.replaceAll(" ", "");
		//System.out.println(str);
		String[] parts=str.split(" ");
		
//		for(int i=0;i<parts.length;i++){
//			System.out.println(parts[i]);
//		}
		values[0]=Double.parseDouble(parts[0]);
		values[1]=Double.parseDouble(parts[1]);
		values[2]=Double.parseDouble(parts[2]);
		
		if(val == 0){
			lat_direction=str.charAt(str.length()-1);
		//	System.out.println(lat_direction);
		}
		else if(val == 1){
			long_direction=str.charAt(str.length()-1);
		//	System.out.println(long_direction);
		}
		
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

	public static void main(String[] args) {
		Coordinates C=new Coordinates("39°40'47.28\" N","104°59'26.8794\" W");

	}

	

}
