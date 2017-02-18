package presenter;
import java.util.ArrayList;
import Model.Model;
import View.View;


public class Presenter {
	protected Model m;
	protected View v;
	
	public Presenter() {
		// This should take in a model and a view for arguments
	}

	public void planTrip(String[] args) {
		String filename;
		switch (args.length){
		case 1: {
			filename = args[0];
			break;
		}
		case 2: {
			checkFlags(args[0]);
			filename = args[1];
			break;
		}
		case 3: {
			checkFlags(args[0]);
			checkFlags(args[1]);
			filename = args[2];
			break;
		}
		case 4: {
			checkFlags(args[0]);
			checkFlags(args[1]);
			checkFlags(args[2]);
			filename = args[3];
		}
		
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Problem constructing Model");
				e.printStackTrace();
			}
		v = new View(m.getItinerary());
		
			
		}
		/*try {
			lo =r.readFile();
		} catch (Exception e) {
			System.out.println("Exception caught from readFile");
			System.exit(-1);
			e.printStackTrace();
		}*/
	}
	
	public void checkFlags(String arg) {
		if (arg == "-m")
			v.setShowMileage(true);
		else if (arg == "-i")
			v.setShowID(true);
		else if (arg == "-n")
			v.setShowName(true);
		else {
			System.err.println("Invalid command line input");
			System.exit(-1);
		}
			
	}

}
