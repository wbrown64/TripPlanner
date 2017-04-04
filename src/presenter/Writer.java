package presenter;

import java.util.ArrayList;

import Model.Location;
import Model.Model;

public class Writer {
	Model model;
	String[] selected;
	SQLinterpreter sqli;
	public Writer(String[] selected,Model model,SQLinterpreter sqli){
		this.selected=selected;
		this.model=model;
		this.sqli=sqli;
		//init();
	}
	public ArrayList<Location> createLocations(){
		ArrayList<Location>locations=new ArrayList<Location>();
		for(String S:selected){
			System.out.println(S);
			Location L=sqli.dataQuery(S);
			locations.add(L);
		}
		return locations;
	}
	
	void parse(String line){
		String[] items=line.split(",");
		for(String S:items){
			System.out.println(S);
		}
	}
	
	
}
