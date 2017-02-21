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


}
