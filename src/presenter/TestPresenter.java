package presenter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Model.Coordinates;
import Model.Location;
import Model.Model;
import View.View;

import java.util.ArrayList;

public class TestPresenter {
	//locations.size should be 91
	@Test
	public static void testPlanTrip(String args[]) {
		ArrayList<Location> testList = new ArrayList<Location>(0);
		testList.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3", "4")));
		View myView = new View(testList);
		try {
			Model myModel = new Model("test.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Likely a file not found error
		}
		Presenter presenter = new Presenter(); // new Presenter(myView, myModel);
	}
	

}
