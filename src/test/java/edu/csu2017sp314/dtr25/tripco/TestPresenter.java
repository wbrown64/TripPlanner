package test.java.edu.csu2017sp314.dtr25.tripco;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.java.edu.csu2017sp314.dtr25.Coordinates;
import main.java.edu.csu2017sp314.dtr25.Location;
import main.java.edu.csu2017sp314.dtr25.Model;
import main.java.edu.csu2017sp314.dtr25.Presenter;
import main.java.edu.csu2017sp314.dtr25.View;

import java.util.ArrayList;

public class TestPresenter {
	//locations.size should be 91
//	@Test
//	public static void testPlanTrip(String args[]) {
//		ArrayList<Location> testList = new ArrayList<Location>(0);
//		testList.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
//		View myView = new View(testList, "placeholder.txt");
//		try {
//			Model myModel = new Model("test.csv",true,true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			// Likely a file not found error
//		}
//		Presenter presenter = new Presenter(); // new Presenter(myView, myModel);
//	}
//	@Test
//	public void testGuiFlag(){
//		Presenter p = new Presenter();
//		assertFalse("failure - should be false",p.GUI);
//	}
//	@Test
//	public void testCheckFlags(){
//		ArrayList<Location> testList = new ArrayList<Location>(0);
//		testList.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
//		Presenter p = new Presenter();
//		View v = new View(testList, "placeholder.txt");
//		p.checkFlags("-m");
//		assertTrue("failure - should be true",v.isShowMileage());
//	}
	@Test
	public void testBasic(){
		assertTrue("presenter",true);
	}

}
