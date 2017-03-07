package presenter;
import java.util.ArrayList;
import Model.Model;
import View.View;


public class Presenter {
	protected Model m;
	protected View v;
	public boolean TwoOpt = false;
	public boolean ThreeOpt = false;
	
	public Presenter() {
		// This should take in a model and a view for arguments
	}

	public void planTrip(String[] args){
		String filename;
		if(args.length == 1){
			filename = args[0];
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
		}
		else{
			filename = args[0];
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
			for(int i = 1; i < args.length; i++){
				checkFlags(args[i]);
			}
		}
		v.initializeTrip();
	}
	
	public void checkFlags(String arg) {
		if (arg.equals("-m"))
			v.setShowMileage(true);
		else if (arg.equals("-i"))
			v.setShowID(true);
		else if (arg.equals("-n"))
			v.setShowName(true);
		else if (arg.equals("-2"))
			TwoOpt = true;
		else if (arg.equals("-3"))
			ThreeOpt = true;
		else {
			System.err.println("Invalid command line input");
			System.exit(-1);
		}
			
	}

}
