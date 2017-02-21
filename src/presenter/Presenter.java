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
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
			break;
		}
		case 2: {
			filename = args[1];
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
			checkFlags(args[0]);
			break;
		}
		case 3: {
			filename = args[2];
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
			checkFlags(args[0]);
			checkFlags(args[1]);
			break;
		}
		case 4: {
			filename = args[3];
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
			checkFlags(args[0]);
			checkFlags(args[1]);
			checkFlags(args[2]);
			break;
		}
		
			/*try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Problem constructing Model");
				e.printStackTrace();
			}
		v = new View(m.getItinerary()); */
		default: {
			System.err.println("Usage: Executable <optional flags> filename");
			System.err.println("where optional flags are -m, -i, or -n and filename is a csv");
			System.err.println("with at least the fields name, id, latitude, city, and longitude");
			System.exit(-1);
		}
		
			
		}
		/*try {
			lo =r.readFile();
		} catch (Exception e) {
			System.out.println("Exception caught from readFile");
			System.exit(-1);
			e.printStackTrace();
		}*/
		v.initializeTrip();
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
