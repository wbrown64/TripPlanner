import Model.Model;
import View.View;
import presenter.Presenter;

public class TripCo {
// This is essentially main, this file will do stuff, but not the same stuff
	static String filename;
	public static void main(String[] args) throws Exception {
		// argument handling?
		int argCount = args.length;
		// Test the number of arguments, for now should be one, for input filename
		if (argCount!=1) {
			// Error, incorrect number of arguments!
			throw new Exception("Incorrect number of arguments!");
		}
		else {
			filename = args[0];
			// check if file with name filename exists
			System.out.println("Filename: "+filename);
		}
		
		Model model = new Model(filename);
		View view = new View();
		Presenter presenter = new Presenter();
		//presenter.start();
	}
	static String init(String input){
		return input;
	}
}

