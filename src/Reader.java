import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Coordinates;
import Model.Location;
public class Reader {
	
	protected String filename;
	
	public Reader(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Location> readFile() throws Exception {
		ArrayList<Location> locations = new ArrayList<>(500);
		
		try {
			Scanner scnr = new Scanner(new File(filename));
			
			while (scnr.hasNextLine()) {
				String[] fields = scnr.nextLine().split(",");
				if (fields.length != 6) {
					throw new Exception("Not enough input fields on a value");
				}
				for (int i = 0; i < fields.length; ++i) {
					fields[i] = fields[i].trim();
				}
				Coordinates c=new Coordinates(fields[3],fields[4],fields[5]);
				locations.add(new Location(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5]));
				System.out.println(locations.get(0));
			}
			scnr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locations;
		
	}
	
	public static void main(String[] args) {
		Reader r = new Reader(args[0]);
		try {
			r.readFile();
		} catch (Exception e) {
			System.out.println("Caught exception");
			System.exit(-1);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
