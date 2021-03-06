package presenter;
import java.util.ArrayList;
import Model.Model;
import View.View;


public class Presenter {
	protected Model m;
	protected View v;
	public boolean GUI = false;
	public boolean TwoOpt = false;
	public boolean ThreeOpt = false;
	public boolean Miles = false;
	public boolean Kilometers = false;
	public String filename = "";
	public String XML = "";
	public String SVG = "";
	public Presenter() {
		// This should take in a model and a view for arguments
	}

	public void planTrip(String[] args){
			SQLinterpreter sqli=new SQLinterpreter("wbrown64","830285807");
			
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
			
			for(int i = 0; i < args.length;i++){
				checkModelFlags(args[i]);
			}
			if(!GUI){
			try {
				m = new Model(filename,TwoOpt,ThreeOpt,Miles,Kilometers);
				if(TwoOpt)
					m.twoOpt();
				//FIXME
				/*if (true)
					m.threeOpt();*/
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v = new View(m.getItinerary(),filename);			
			for(int i = 0; i < args.length;i++){
				checkViewFlags(args[i]);
			}
			
			v.initializeTrip(this,m);
			}
			else{
				try {
					m = new Model(filename,TwoOpt,ThreeOpt,Miles,Kilometers);
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				v = new View(m.getItinerary(),filename);			
				for(int i = 0; i < args.length;i++){
					checkViewFlags(args[i]);
				}
				
				v.initializeTrip(this,m);
				}
			}
	
	
	public void checkModelFlags(String arg) {
	
		if(arg.equals("-2")){
			TwoOpt = true;
		}
		else if(arg.equals("-3")){
			ThreeOpt = true;
		}
		else if(arg.equals("-g")){
			GUI = true;
		}
		else if(arg.equals("-m")){
			Miles = true;
		}
		else if(arg.equals("-k")){
			Kilometers = true;
		}
		
			
	}
	public void checkViewFlags(String arg){
		if(arg.equals("-d")){
			v.setShowMileage(true);
		}
		else if(arg.equals("-i")){
			v.setShowID(true);
		}
		else if(arg.equals("-n")){
			v.setShowName(true);
		}
		else if(arg.equals("-m")){
			v.setMiles(true);
		}
		else if(arg.equals("-k")){
			v.setKilometers(true);
		}
	}

}
