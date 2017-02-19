package Model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class ModelTest {
	//@Test
	public void testConstructor(){ // just tests creation of model (and planTrip atm)
		try {
			Model myModel = new Model("test.csv"); // requires test dummy file, or this fails regardless of later code
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Test fails if (a) test.csv is not valid OR (b) something is wrong with the constructor + planTrip code
		}  
	}
	public void testLegDistance(){ // creates a model to use for testing getLegDistance (static?)
		try {
			Model myModel = new Model("test.csv");
			Location l1 = new Location("1", "Brewery", "City", "0", "0", "0", new Coordinates("0", "0", "0"));
			Location l2 = new Location("1", "Brewery", "City", "0", "0", "0", new Coordinates("0", "0", "0"));
			assertEquals(0,myModel.getLegDistance(l1, l2));  
			// planning on replacing this test with something a bit more involved
			//for now just making sure it doesn't crash
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
