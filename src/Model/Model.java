package Model;

import java.util.ArrayList;

import presenter.Presenter;
import presenter.Reader;

public class Model {
		protected ArrayList<Location>itinerary;
		private ArrayList<Edge> edges = new ArrayList<Edge>(500);

		public boolean twoOpt = false;
		public boolean threeOpt = false;
		public boolean Miles = false;
		public boolean Kilometers = false;
		public String filename;
	
	public Model(String filename, boolean twoOpt,boolean threeOpt,boolean Miles,boolean Kilometers) throws Exception{
		this.twoOpt=twoOpt;
		this.threeOpt=threeOpt;
		this.Miles=Miles;
		this.Kilometers=Kilometers;
		this.filename=filename;
		//Reader read=new Reader(filename);
		//ArrayList<Location> i=read.readFile();
		//this.setItinerary(i);
		//initializeModel();
	}
	 public void initializeModel() throws Exception{
		itinerary = bestStandardTrip();
		setLegDistance(itinerary);
		//threeOpt();
		
	}
	 
	 private ArrayList<Location> bestStandardTrip() {
		 double bestDistance = Double.MAX_VALUE;
		 ArrayList<Location> newItinerary = new ArrayList<Location>(500);
		 
		 for (int i = 0; i < itinerary.size(); ++i) {
			 ArrayList<Location> tempItinerary = standardTrip(itinerary.get(i));
			 double tempDistance = getTotalDistance(tempItinerary);
			 
			 if (tempDistance < bestDistance) {
				 bestDistance = tempDistance;
				 newItinerary = tempItinerary;
			 }
		 }
		 return newItinerary;
	 }
	 
	 private ArrayList<Location> standardTrip(Location start) {
		 Location currentLoc = start;
		 ArrayList<Location> itineraryCopy= new ArrayList<Location>(itinerary);
		 ArrayList<Location> newItinerary = new ArrayList<Location>(500);
		 
		 itineraryCopy.remove(start);
		 newItinerary.add(start);
		 
		 double minDistance = Double.MAX_VALUE;
		 int index = 0;
		 
		 while (itineraryCopy.size()>0) {
			 for (int i = 0; i < itineraryCopy.size(); ++i) {
				 double dist = getLegDistance(currentLoc,itineraryCopy.get(i));
				 
				 if (dist < minDistance) {
					 minDistance = dist;
					 index = i;
				 }
			 }
			 Location oldCurrent = currentLoc;
				currentLoc=itineraryCopy.get(index);
				newItinerary.add(currentLoc);
				itineraryCopy.remove(currentLoc);
				if(itineraryCopy.size() > 0)
				//min_distance=getLegDistance(current,getItinerary().get(0));
				minDistance = Double.MAX_VALUE;
		 }
		 return newItinerary;
	 }

	 public void newItinerary() throws Exception{
		 Reader read=new Reader(filename);
		ArrayList<Location> i=read.readFile();
		this.setItinerary(i);
	 }

	
	/*private ArrayList<Location> standard_trip(){ // should this be private?
		ArrayList<Location> itinerary_copy=new ArrayList<Location>();
		Location current=getItinerary().get(0);
		//double min_distance=getLegDistance(current,getItinerary().get(1));
		double min_distance=Double.MAX_VALUE;
		itinerary_copy.add(current);
		getItinerary().remove(current);
		int index = 0;
		//int index=1;
		while(getItinerary().size()!=0){
			for(int i=0;i<getItinerary().size();i++){
				double distance=getLegDistance(current,getItinerary().get(i));
				//System.out.println("getLegDistance(): " + getLegDistance(current,getItinerary().get(i)));
				if(distance<min_distance){
					min_distance=distance;
					index=i;
				}
			}
			
			Location oldCurrent = current;
			current=getItinerary().get(index);
			itinerary_copy.add(current);
			//Edge addingEdge = new Edge(oldCurrent,current);
			//addingEdge.setDistance(min_distance);
			//edges.add(addingEdge);
			getItinerary().remove(current);
			if(getItinerary().size()!=0)
			//min_distance=getLegDistance(current,getItinerary().get(0));
			min_distance = Double.MAX_VALUE;
		}
//		for(Location L:itinerary_copy){
//			System.out.println(L.city);
//		}
		return itinerary_copy;
		//setItinerary(itinerary_copy);
	}*/
	
	private void setLegDistance(ArrayList<Location> itinerary){
		if(Miles){
		for(int i = 0; i < itinerary.size()-1; i++){
			double temp = getLegDistance(itinerary.get(i),itinerary.get(i+1));
			temp = temp * 0.621371;
			temp = Math.round(temp);
			int d = (int) temp;
			itinerary.get(i).legDistance = d;
		}
		double temp = getLegDistance(itinerary.get(itinerary.size()-1),itinerary.get(0));
		temp = temp * 0.621371;
		temp = Math.round(temp);
		int d = (int) temp;
		itinerary.get(itinerary.size()-1).legDistance = d;
		}
		else if(Kilometers){
			for(int i = 0; i < itinerary.size()-1; i++){
				double temp = getLegDistance(itinerary.get(i),itinerary.get(i+1));
				//temp = temp * 0.621371;
				temp = Math.round(temp);
				int d = (int) temp;
				itinerary.get(i).legDistance = d;
			}
			double temp = getLegDistance(itinerary.get(itinerary.size()-1),itinerary.get(0));
			//temp = temp * 0.621371;
			temp = Math.round(temp);
			int d = (int) temp;
			itinerary.get(itinerary.size()-1).legDistance = d;	
		}
	}
	
	private Location getlegStartLocation(ArrayList<Location> itinerary){
		for(Location l:itinerary){
			if(l.isStart)
				return l;
		}
		return null;
	}
	private Location getlegFinishLocation(ArrayList<Location> itinerary){
		for(Location l:itinerary){
			if(l.isEnd)
				return l;
		}
		return null;
	}
	private double getLegDistance(Location l1, Location l2){  // this could be a static method
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
	private double getTotalDistance(ArrayList<Location> list){
		setLegDistance(list);
		double totalDistance = 0;
		double distance = 0;
		for(int i = 0; i < list.size()-1; i++){
			distance += list.get(i).legDistance;
		}
		distance += list.get(list.size()-1).legDistance;
		distance = Math.round(distance);
		totalDistance = distance;
		return totalDistance;
	}
	

	public void twoOpt() {// you guys suck <--wtf bro.. 
	    double bestDistance=getTotalDistance(itinerary);
	    double newDistance=bestDistance;
	    int c=0;
	    	while(c!=10){
	    	ArrayList<Location> old_route=itinerary;
			ArrayList<Location> new_route;
			for(int i=0;i<old_route.size()-1;i++){
				for(int j=i+1;j<old_route.size();j++){
					new_route=twoOptSwap(old_route,i,j);
					newDistance=getTotalDistance(new_route);
					if(newDistance<bestDistance){
						itinerary=new_route;
						bestDistance=newDistance;
						c=0;
					}
				}
			}
			c++;
		}
	}

	ArrayList<Location> twoOptSwap(ArrayList<Location> old_route,int a, int b){
		ArrayList<Location>new_route=new ArrayList<Location>();
		for(int i=0;i<a;i++){
			new_route.add(old_route.get(i));
		}
		for(int i=b;i>=a;i--){
			new_route.add(old_route.get(i));
		}
		for(int i=b+1;i<old_route.size();i++){
			new_route.add(old_route.get(i));
		}
		return new_route;
	}
	
	private double[] toCartesian(double x,double y){
		double[] vals=new double[2];
		//System.out.println("x is: "+x);
		vals[0]= (Math.abs(x-(-109)) / Math.abs((-102) - (-109)))*(1063.0085-50);
		//vals[0] = ((1180/7) * (x-109))+50;
		//System.out.println(t);
		vals[1]= (Math.abs(y-41) / (Math.abs(37-41))*(779.5144-50));
		//vals[1] = ((1180/7)*(41-y)) + ((1024-(4 * 1180/7))/2);
//		for(double i:vals){
//			System.out.println(i);
//		}
		return vals;
	}
	
	//creates new nodes for 2Opt Evaluation, calls Swap Edge if distance is better
	private void evaluateEdges2Opt(Edge e1, Edge e2) {
		Edge newE1 = new Edge(e1.getfrom(),e2.getfrom());
		Edge newE2 = new Edge(e1.getTo(),e2.getTo());
		
		double newE2Dist = getLegDistance(e1.getTo(),e2.getTo());
		double newE1Dist = getLegDistance(e1.getfrom(),e2.getfrom());
		
		newE1.setDistance(newE1Dist);
		newE2.setDistance(newE2Dist);
		
		if ((newE1Dist + newE2Dist) < (e1.getDistance() + e2.getDistance())){
			swapEdge2Opt(e1,e2,newE1,newE2);
		}
	}
	
	// if the new edges are better, swaps them out for the old ones using the .equals methods defined in Location and Edge
	private void swapEdge2Opt(Edge oldE1, Edge oldE2, Edge newE1, Edge newE2) {
		int e1Index = edges.indexOf(oldE1);
		int e2Index = edges.indexOf(oldE2);
		
		edges.set(e1Index, newE1);
		edges.set(e2Index, newE2);
	}
	
	public void threeOpt() {
		twoOpt();
		double bestDistance=getTotalDistance(itinerary);
	    double newDistance=bestDistance;
	    int c=0;
	    	while(c!=10){
	    	ArrayList<Location> old_route=itinerary;
			ArrayList<Location> new_route;
			for(int i=0;i<old_route.size()-2;i++){
				for(int j=i+1;j<old_route.size()-1;j++){
					for (int k = j+1; k<old_route.size();++k) {
						new_route=threeOptSwap(old_route,i,j,k);
						newDistance=getTotalDistance(new_route);
						if(newDistance<bestDistance){
							itinerary=new_route;
							bestDistance=newDistance;
							c=0;
						}
					}
				}
			}
			c++;
		}
	}
	
	private ArrayList<Location> threeOptSwap(ArrayList<Location> oldRoute, int a, int b, int c) {
		
		ArrayList<Location> newRoute = new ArrayList<>();
		for(int i = 0; i < a ; ++i){
			newRoute.add(oldRoute.get(i));
		}
		for(int i = b; i >= a; --i){
			newRoute.add(oldRoute.get(i));
		}
		for(int i = b + 1; i < c; ++i){
			newRoute.add(oldRoute.get(i));
		}
		for (int i = oldRoute.size()-1; i >= c; --i) {
			newRoute.add(oldRoute.get(i));
		}
		
		return newRoute;
	}
	
	private String getLocationName(double lat, double lon){
		for(Location l:getItinerary()){
			if(l.coord.dd_lat==lat&&l.coord.dd_long==lon){
				return l.city;
			}
		}
		return null;
	}
	private Location getLocation(String brewery_name){
		for(Location l:getItinerary()){
			if(l.brewery.equalsIgnoreCase(brewery_name)){
				return l;
			}
		}
		return null;
	}
	private double getLat(Location l){
		return l.coord.dd_lat;
	}
	private double getLon(Location l){
		return l.coord.dd_long;
	}
	public static void main(String[] args) throws Exception{
		//Model m=new Model("small_locations.txt");

		
	}
	
	public void setTwoOpt(boolean t) {
		twoOpt = t;
	}

	public void setThreeOpt(boolean t) {
		threeOpt = t;
	}
	
	public ArrayList<Location> getItinerary() {
		return itinerary;
	}

	public void setItinerary(ArrayList<Location> itinerary) {
		this.itinerary = itinerary;
	}
	public void setMiles(boolean t){
		Miles = t;
	}
	public void setKilometers(boolean t){
		Kilometers = t;
	}
}
