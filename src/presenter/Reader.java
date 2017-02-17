package presenter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Coordinates;
public class Reader {
	
	protected String filename;
	
	public Reader(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Model.Location> readFile() throws Exception {
		ArrayList<Model.Location> locations = new ArrayList<>(500);
		
		try {
			Scanner scnr = new Scanner(new File(filename));
			//int index = 0;
			while (scnr.hasNextLine()) {
				String[] fields = scnr.nextLine().split(",");
				if (!(fields.length == 6)) {
					scnr.close();
					throw new Exception("Not enough input fields on a value");
				}
				for (int i = 0; i < fields.length; ++i) {
					fields[i] = fields[i].trim();
				}
				Coordinates c=new Coordinates(fields[3],fields[4],fields[5]);
				locations.add(new Model.Location(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],c));
				//System.out.println(locations.get(index++));
			}
			scnr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return locations;
		
	}

}
