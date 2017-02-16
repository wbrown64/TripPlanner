package Model;

import java.util.ArrayList;

import presenter.Reader;

public class Model {
		protected ArrayList<Location>itinerary;
	
	public Model(String filename) throws Exception{
		Reader read=new Reader(filename);
		ArrayList<Location> i=read.readFile();
		this.itinerary=i;
		planTrip();
	}
	
	void planTrip(){
//		for(int i=0;i<itinerary.size();i++){
//			System.out.println(itinerary.get(i).brewery);
//		}
		getlegStartLocation(itinerary);
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
	int getLegDistance(Location l1, Location l2){
		
		return 0;
	}
	public String getLocationName(double lat, double lon){
		for(Location l:itinerary){
			if(l.coord.dd_lat==lat&&l.coord.dd_long==lon){
				return l.city;
			}
		}
		return null;
	}
	public Location getLocation(String name){
		for(Location l:itinerary){
			if(l.city.equalsIgnoreCase(name)){
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
}
