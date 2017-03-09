package presenter;
import java.util.ArrayList;
import Model.Model;
import View.View;


public class Presenter {
	protected Model m;
	protected View v;
	public boolean TwoOpt = false;
	public boolean ThreeOpt = false;
	public boolean GUI = false;
	
	public Presenter() {
		// This should take in a model and a view for arguments
	}

	public void planTrip(String[] args){
		String filename = "";
		String XML = "";
		String SVG = "";
		
			for(int i = 0;i < args.length;i++){
				//System.out.println(args[i].substring(args[i].length()-4, args[i].length()));
				
				if(args[i].length() > 4 && args[i].substring(args[i].length()-4, args[i].length()).equals(".csv")){
					filename = args[i];
				}
				else if(args[i].length() > 4 && args[i].substring(args[i].length()-4, args[i].length()).equals(".xml")){
					XML = args[i];
				}
				else if(args[i].length() > 4 && args[i].substring(args[i].length()-4, args[i].length()).equals(".svg")){
					SVG = args[i];
				}
			}
			try {
				m = new Model(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);
			for(int i = 0; i < args.length;i++){
				checkFlags(args[i]);
			}
			
		v.initializeTrip(this);
	}
	
	public void checkFlags(String arg) {
		if(arg.equals("-m")){
			v.setShowMileage(true);
		}
		else if(arg.equals("-i")){
			v.setShowID(true);
		}
		else if(arg.equals("-n")){
			v.setShowName(true);
		}
		else if(arg.equals("-2")){
			TwoOpt = true;
		}
		else if(arg.equals("-3")){
			ThreeOpt = true;
		}
		else if(arg.equals("-g")){
			GUI = true;
		}
			
	}

}
