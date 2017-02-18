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
		
		Model model = new Model(filename);
		View view = new View(model.getItinerary());
		

		for(Location L:model.getItinerary()){
			System.out.println(L.getCity());
		}

	}
	static String init(String input){
		return input;
	}
}

