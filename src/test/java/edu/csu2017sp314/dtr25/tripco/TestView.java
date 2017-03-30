package test.java.edu.csu2017sp314.dtr25.tripco;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.java.edu.csu2017sp314.dtr25.Coordinates;
import main.java.edu.csu2017sp314.dtr25.Location;
import main.java.edu.csu2017sp314.dtr25.View;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class TestView {

	//@Test
	/*public void testDisplay(){
		ArrayList<Location> testList = new ArrayList<Location>(0);
		testList.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		View myView = new View(testList);
		assertEquals("text",myView.display("text"));
		//testing
		//testing
	}*/
	@Test
	public void testIsShowMileage(){
		ArrayList<Location> test = new ArrayList<Location>(0);
		test.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		View v = new View(test,"testFile");
		assertFalse("failure - should be false",v.isShowMileage());
	}
	@Test
	public void testIsShowID(){
		ArrayList<Location> test = new ArrayList<Location>(0);
		test.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		View v = new View(test,"testFile");
		assertFalse("failure - should be false",v.isShowID());
	}
	@Test
	public void testIsShowName(){
		ArrayList<Location> test = new ArrayList<Location>(0);
		test.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		View v = new View(test,"testFile");
		assertFalse("failure - should be false",v.isShowName());
	}
	@Test
	public void testViewObjects(){
		ArrayList<Location> test = new ArrayList<Location>(0);
		test.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		ArrayList<Location> test2 = new ArrayList<Location>(0);
		test2.add(new Location("1", "Brewery", "City", "2", "3", "4", new Coordinates("2", "3")));
		View v = new View(test,"testFile");
		View v2 = new View(test2,"testFile");
		assertNotSame("should not be same Object",v,v2);
	}
	
}