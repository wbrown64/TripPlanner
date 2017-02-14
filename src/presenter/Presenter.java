package presenter;
import java.util.ArrayList;

public class Presenter {
	//protected Model m;
	//protected View v;
	
	public Presenter(/*Model m, View v*/) {
		//this.m = m;
		//this.v = v;
	}

	public ArrayList<Model.Location> Driver(String[] args) {
		Reader r = new Reader(args[0]);
		ArrayList<Model.Location> lo = new ArrayList<>();
		try {
			lo =r.readFile();
		} catch (Exception e) {
			System.out.println("Exception caught from readFile");
			System.exit(-1);
			e.printStackTrace();
		}
		return lo;
	}

}
