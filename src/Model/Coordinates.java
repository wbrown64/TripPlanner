package Model;

import java.util.ArrayList;

public class Coordinates {
	private double x;
	private double y;
	private double distance_to_next;
	ArrayList<Double> longitude;
	ArrayList<Double> latitude;
	char lat_direction;
	char long_direction;
	public double dd_lat;
	public double dd_long;
	

	public Coordinates(String lat, String longe){
		latitude=new ArrayList<Double>();
		longitude=new ArrayList<Double>();
		if(lat.contains("'")||lat.contains("\"")||lat.contains("°")){
		parse_strs(lat,latitude,0);
		parse_strs(longe,longitude,1);
		calculate_dd();
		}
		else{
			dd_lat=Double.parseDouble(lat);
			dd_long=Double.parseDouble(longe);
		}

	}
	
	public void calculate_dd(){
		if(latitude.size()==1){
			dd_lat=latitude.get(0);
//			System.out.println(dd_lat);
		}
		if(longitude.size()==1){
			dd_long=longitude.get(0)*-1;
//			System.out.println(dd_long);
		}
		else{
		dd_lat=(latitude.get(0)+(latitude.get(1)/60)+(latitude.get(2)/3600));
		dd_long=(longitude.get(0)+(longitude.get(1)/60)+(longitude.get(2)/3600))*-1;
		}
		//System.out.println(dd_long);
	}
	
	public void parse_strs(String str, ArrayList<Double> values,int val) {
		str=str.replaceAll("[°\'\"]", " ");
//		str=str.replaceAll(" ", "");
		//System.out.println(str);
		String[] parts=str.split(" ");
		
		for(int i=0;i<parts.length;i++){
			//System.out.println(parts[i]);
			if(!parts[i].isEmpty()&&!parts[i].equalsIgnoreCase("w")&&!parts[i].equalsIgnoreCase("n")){
				values.add(Double.parseDouble(parts[i]));
			}
		}
//		values[0]=Double.parseDouble(parts[0]);
//		values[1]=Double.parseDouble(parts[1]);
//		values[2]=Double.parseDouble(parts[2]);
		
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

	//public static void main(String[] args) {
	//	Coordinates C=new Coordinates("39°40'47.28\" N","104°59'26.8794\" W");

	//}

	

}
