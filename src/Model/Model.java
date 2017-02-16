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
		for(int i=0;i<itinerary.size();i++){
			System.out.println(itinerary.get(i).brewery);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Model m=new Model("location.txt");
	}
}
