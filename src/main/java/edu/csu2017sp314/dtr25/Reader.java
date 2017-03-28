package main.java.edu.csu2017sp314.dtr25;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.edu.csu2017sp314.dtr25.Coordinates;
public class Reader {
	
	protected String filename;
	protected ArrayList<Boolean>  isCart;
	
	public Reader(String filename) {
		this.filename = filename;
		isCart = new ArrayList<Boolean>();
	}
	
	public ArrayList<main.java.edu.csu2017sp314.dtr25.Location> readFile() throws Exception {
		ArrayList<main.java.edu.csu2017sp314.dtr25.Location> locations = new ArrayList<>(500);
		String[] fields;
		int idIndex = 0;
		int breweryIndex = 0;
		int cityIndex = 0;
		int latitudeIndex = 0;
		int longitudeIndex = 0;
		int altitudeIndex = 0;
		
		try {
			Scanner scnr = new Scanner(new File(filename));
			//while (scnr.hasNextLine()) {
				//String[] field = scnr.nextLine().split(",");
				//for (int i = 0; i < field.length; ++i) 
					//System.out.println(i + ": " + field[i]);
			//}
			//int index = 0;
			if (scnr.hasNextLine()) {
				fields = scnr.nextLine().split(",");
				for (int i = 0; i < fields.length; ++i) {
					fields[i] = fields[i].toLowerCase().trim();
					//System.out.println(i + ": " + fields[i]);
					switch (fields[i]) {
					case "name":
						breweryIndex = i;
						break;
					case "id":
						idIndex = i;
						break;
					case "city":
						cityIndex = i;
						break;
					case "latitude":
						latitudeIndex = i;
						break;
					case "longitude":
						longitudeIndex = i;
						break;
					case "altitude":
						altitudeIndex = i;
						break;
				}
				if (cityIndex == 0)
					cityIndex = breweryIndex;
			}
			while (scnr.hasNextLine()) {
				fields = scnr.nextLine().split(",");
				/*if (!(fields.length == 6)) {
					scnr.close();
					throw new Exception("Not enough input fields on a value");
				} */
				for (int i = 0; i < fields.length; ++i) {
					fields[i] = fields[i].trim();
				}
				Coordinates c=new Coordinates(fields[latitudeIndex],fields[longitudeIndex]);
				//System.out.println(fields[breweryIndex]);
				locations.add(new main.java.edu.csu2017sp314.dtr25.Location(fields[idIndex],fields[breweryIndex],fields[cityIndex],fields[latitudeIndex],fields[longitudeIndex],fields[altitudeIndex],c));
				//System.out.println(locations.get(index++));
			}
			scnr.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return locations;
		
	}
	
	public void testCoordType(String latitude) {
		if (latitude.contains("'") || latitude.contains("\""))
			isCart.add(false);
		else
			isCart.add(true);
	}
	
	public ArrayList<Boolean> getIsCart() {
		return isCart;
	}
	

}
