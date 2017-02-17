package Model;

import java.util.ArrayList;

import presenter.Reader;

public class Model {
		private ArrayList<Location>itinerary;
	
	public Model(String filename) throws Exception{
		Reader read=new Reader(filename);
		ArrayList<Location> i=read.readFile();
		this.setItinerary(i);
		planTrip();
	}
	
	public void planTrip(){
		ArrayList<Location> itinerary_copy=new ArrayList<Location>();
		Location current=getItinerary().get(0);
		double min_distance=getLegDistance(current,getItinerary().get(1));
		itinerary_copy.add(current);
		getItinerary().remove(current);
		int index=1;
		while(getItinerary().size()!=0){
			for(int i=0;i<getItinerary().size();i++){
				double distance=getLegDistance(current,getItinerary().get(i));
				if(distance<=min_distance){
					min_distance=distance;
					index=i;
				}
			}
			current=getItinerary().get(index);
			itinerary_copy.add(current);
			getItinerary().remove(current);
			if(getItinerary().size()!=0)
			min_distance=getLegDistance(current,getItinerary().get(0));
		}
//		for(Location L:itinerary_copy){
//			System.out.println(L.city);
//		}
		setItinerary(itinerary_copy);
	}
	Location getlegStartLocation(ArrayList<Location> itinerary){
		for(Location l:itinerary){
			if(l.isStart)
				return l;
		}
		return null;
	}
	Location getlegFinishLocation(ArrayList<Location> itinerary){
		for(Location l:itinerary){
			if(l.isEnd)
				return l;
		}
		return null;
	}
	public double getLegDistance(Location l1, Location l2){
		double lat1=l1.coord.dd_lat;
		double lon1=l1.coord.dd_long;
		double lat2=l2.coord.dd_lat;
		double lon2=l2.coord.dd_long;
		
		double dlat=Math.toRadians(lat2-lat1);
		double dlon=Math.toRadians(lon2-lon1);
		lat1=Math.toRadians(lat1);
		lat2=Math.toRadians(lat2);
		
		double a = Math.pow(Math.sin(dlat / 2),2) + Math.pow(Math.sin(dlon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return 6372.8*c;
		
	}
	public String getLocationName(double lat, double lon){
		for(Location l:getItinerary()){
			if(l.coord.dd_lat==lat&&l.coord.dd_long==lon){
				return l.city;
			}
		}
		return null;
	}
	public Location getLocation(String brewery_name){
		for(Location l:getItinerary()){
			if(l.brewery.equalsIgnoreCase(brewery_name)){
				return l;
			}
		}
		return null;
	}
	double getLat(Location l){
		return l.coord.dd_lat;
	}
	double getLon(Location l){
		return l.coord.dd_long;
	}
	public static void main(String[] args) throws Exception{
		Model m=new Model("small_locations.txt");
		
		
	}

	public ArrayList<Location> getItinerary() {
		return itinerary;
	}

	public void setItinerary(ArrayList<Location> itinerary) {
		this.itinerary = itinerary;
	}
}
