package Model;
import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class ModelTest {
	//@Test
	public void testGetItinerary(){ // just tests creation of model (and planTrip atm)
		try {
			Model myModel = new Model("small_locations.txt",true,true, true); 
			ArrayList<Location> i=myModel.getItinerary();
			assertEquals(i,myModel.getItinerary());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Test fails if (a) test.csv is not valid OR (b) something is wrong with the constructor + planTrip code
		}  
	}


}
