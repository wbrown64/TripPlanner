package presenter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestPresenter {
	//locations.size should be 91
	
	@Test
	public static void main(String args[]) {
		Presenter p = new Presenter();
		ArrayList<Location> locations = p.Driver(args);
		for (Location lo : locations) {
			assertNotNull(lo);
		}
	}
	

}