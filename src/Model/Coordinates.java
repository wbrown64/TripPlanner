package Model;

public class Coordinates {
	private double x;
	private double y;
	private int elevation;
	private double distance_to_next;
	private double[] latitude;
	private double[] longitude;
	char lat_direction;
	char long_direction;
	double dd_lat;
	double dd_long;
	
	
	public Coordinates(String lat, String longe,String elevation){
		latitude=new double[3];
		longitude=new double[3];
		this.elevation=Integer.parseInt(elevation);
		parse_strs(lat,latitude,0);
		parse_strs(longe,longitude,1);
		calculate_dd();

	}
	
	public void calculate_dd(){
		dd_lat=latitude[0]+(latitude[1]/60)+(latitude[2]/3600);
		dd_long=longitude[0]+(longitude[1]/60)+(longitude[2]/3600);
		//System.out.println(dd_long);
	}
	
	public void parse_strs(String str, double[] values,int val) {
		String[] split=str.split("°");
		String days=split[0];
		String hours=split[1].substring(0,2);
		String mins=split[1].substring(3,8);
		values[0]=Double.parseDouble(days);
		values[1]=Double.parseDouble(hours);
		values[2]=Double.parseDouble(mins);
		if(val==0)
			lat_direction=str.charAt(str.length()-1);
		else if(val==1)
			long_direction=str.charAt(str.length()-1);

//		for(int i=0;i<3;i++){
//			System.out.println(values[i]);
//		}
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
