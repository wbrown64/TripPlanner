import Model.Location;
import Model.Model;
import View.View;
import presenter.Presenter;

public class TripCo {
	static String filename;
	public static void main(String[] args) throws Exception {
		int argCount = args.length;
		if (argCount!=1) {
			throw new Exception("Incorrect number of arguments!");
		}
		else {
			filename = args[0];
		}
		
		// These objects only exist in this class, Presenter creates its own
		// I changed this back to no parameters, solves problems with command line flags
		Presenter presenter = new Presenter();
		
		// I'm trying to do almost all work in here
		presenter.planTrip(args);
		

//		for(Location L:model.getItinerary()){
//			System.out.println(L.getCity());
//		}

	}
	static String init(String input){
		return input;
	}
}

