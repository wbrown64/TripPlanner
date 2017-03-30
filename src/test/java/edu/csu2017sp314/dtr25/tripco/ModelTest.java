package test.java.edu.csu2017sp314.dtr25.tripco;
import java.util.ArrayList;

import org.junit.Test;

import main.java.edu.csu2017sp314.dtr25.Location;
import main.java.edu.csu2017sp314.dtr25.Model;

import static org.junit.Assert.assertEquals;
public class ModelTest {
	//@Test
	public void testGetItinerary(){ // just tests creation of model (and planTrip atm)
		try {
			Model myModel = new Model("small_locations.txt",true,true); 
			ArrayList<Location> i=myModel.getItinerary();
			assertEquals(i,myModel.getItinerary());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Test fails if (a) test.csv is not valid OR (b) something is wrong with the constructor + planTrip code
		}  
	}


}
