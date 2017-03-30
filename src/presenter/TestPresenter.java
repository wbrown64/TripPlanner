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
		testList.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		View myView = new View(testList, "placeholder.txt");
		try {
			Model myModel = new Model("test.csv",true,true,true,true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Likely a file not found error
		}
		Presenter presenter = new Presenter(); // new Presenter(myView, myModel);
	}
	@Test
	public void testGuiFlag(){
		Presenter p = new Presenter();
		assertFalse("failure - should be false",p.GUI);
	}
	@Test
	public void testCheckFlags(){
		ArrayList<Location> testList = new ArrayList<Location>(0);
		testList.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		Presenter p = new Presenter();
		View v = new View(testList, "placeholder.txt");
		p.checkViewFlags("-m");
		assertTrue("failure - should be true",v.isShowMileage());
	}

}
