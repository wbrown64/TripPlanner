package presenter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestPresenter {
	//locations.size should be 91
	
	@Test
	public static void testDriver(String args[]) {
		Model.Model m = null;
		try {
			m = new Model.Model(args[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		View.View v = new View.View();
		Presenter p = new Presenter(m,v);
	}
	

}
