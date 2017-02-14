import Model.Model;

public class TripCo {
// This is essentially main, this file will do stuff, but not the same stuff
	public static void main(String[] args) {
		// argument handling?
		Model model = new Model();
		View view = new View();
		Presenter presenter = new Presenter(view, model);
		presenter.start();
	}
}

