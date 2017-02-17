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
		View view = new View();
		Presenter presenter = new Presenter();
		//presenter.start();
		
		Location l=model.getLocation("durango");
		Location l2=model.getLocation("denver");
//		System.out.println(l.coord.dd_long);
		double d=l.coord.dd_lat;
		double d1=l.coord.dd_long;
		
		double distance=model.getLegDistance(l,l2);
		System.out.println("Distance between Denver and Durango: " +distance+"km");
		Location l3=model.getLocation("loveland");
		double distance2=model.getLegDistance(l2, l3);
		System.out.println("Distance between Denver and Loveland: "+distance2+"km");
		
	}
	static String init(String input){
		return input;
	}
}

