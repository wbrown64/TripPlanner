package Model;

public class Edge {
	private Location from;
	private Location to;
	private double distance;
	
	public Edge(Location n1, Location n2) {
		from = n1;
		to = n2;
		distance = -1;
	}
	
	

	public double getDistance() {
		return distance;
	}



	public void setDistance(double distance) {
		this.distance = distance;
	}



	public Location getfrom() {
		return from;
	}

	public void setfrom(Location from) {
		this.from = from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}
	
	public String toString() {
		return from.toString() + " ||||||| " + to.toString();
	}
	
	public boolean equals(Edge e) {
		return (this.getfrom().equals(e.getfrom()) && this.getTo().equals(e.getTo()));
	}
	
	public boolean compareAnyLocations(Edge e) {
		return (this.getfrom().equals(e.getfrom()) || this.getfrom().equals(e.getTo()) || this.getTo().equals(e.getfrom())
				|| this.getTo().equals(e.getTo())); 
	}
	
	public static void main(String[] args) {
		Edge e1 = new Edge(new Location("1","2","3","4","5","6", new Coordinates("1","1")),new Location("1","2","3","4","5","7", new Coordinates("1","1")));
		Edge e2 = new Edge(new Location("1","2","3","4","5","8", new Coordinates("1","1")),new Location("1","2","3","4","5","7", new Coordinates("1","1")));
		System.out.println(e1.equals(e2));
	}
	
}
