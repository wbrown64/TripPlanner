package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.Location;

public class View {
	protected ArrayList<Location> itinerary;
	protected boolean showMileage = false;
	protected boolean showID = false;
	protected boolean showName = false;
	
	public View(ArrayList<Location> itinerary){
		this.itinerary = itinerary;
	}
	
	public void initializeTrip(){
		createSvg(itinerary,"out.txt");
		createSvg(itinerary,"out.svg");
		createXML(itinerary,"itinerary.xml");
		createXML(itinerary,"itinerary.txt");
	}
	
	private void createSvg(ArrayList<Location> itinerary,String filename){
		try{
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			writer.println("<?xml version=\"1.0\"?>");
			writer.println("<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">");
			createBorders(writer);
			createTitles(writer,itinerary);
			createLegs(writer,itinerary);
			createLocations(writer,itinerary);
			createDistances(writer,itinerary);
			writer.println("</svg>");
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error");
		}
	}
	
	private void createXML(ArrayList<Location> itinerary,String filename){
		try{
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.println("<trip>");
			writer.println("<!--");
			writer.println("sequence - the order of the legs");
			writer.println("start - the name of the starting location for a leg");
			writer.println("finish - the name of the finishing location for a leg");
			writer.println("mileage - the number of miles (to the nearest whole mile) between the starting and finishing locations");
			writer.println("-->");
			createLegForXML(writer,itinerary);
			writer.println("</trip>");
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error");
		}
	}
	
	public boolean isShowMileage() {
		return showMileage;
	}

	public void setShowMileage(boolean showMileage) {
		this.showMileage = showMileage;
	}

	public boolean isShowID() {
		return showID;
	}

	public void setShowID(boolean showID) {
		this.showID = showID;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	
	private void createLegForXML(PrintWriter writer,ArrayList<Location> itinerary){
		for(int i = 0; i < itinerary.size()-1; i++){
			writer.println("<leg>");
			writer.println("<sequence>" + (i+1) +"</sequence>");
			writer.println("<start>" + itinerary.get(i).getCity() +"</start>");
			writer.println("<finish>" + itinerary.get(i+1).getCity() + "</finish>");
			writer.println("<mileage>" + itinerary.get(i).legDistance + "</mileage>");
			writer.println("</leg>");
		}
		writer.println("<leg>");
		writer.println("<sequence>" + (itinerary.size()-1) + "</sequence>");
		writer.println("<start>" + itinerary.get(itinerary.size()-1).getCity() + "</start>");
		writer.println("<finish>" + itinerary.get(0).getCity() + "</finish>");
		writer.println("<mileage>" + itinerary.get(itinerary.size()-1).legDistance + "</mileage>");
		writer.println("</leg>");
	}
	
	private double[] toCartesian(double x,double y){
		double[] vals=new double[2];
		//System.out.println("x is: "+x);
		vals[0]= (Math.abs(x-(-109)) / Math.abs((-102) - (-109)))*(1230-50);
		//System.out.println(t);
		vals[1]= (Math.abs(y-41) / (Math.abs(37-41))*(974-50));
//		for(double i:vals){
//			System.out.println(i);
//		}
		return vals;
	}
	private void createBorders(PrintWriter writer){
		writer.println("<g>");
		writer.println("<title>Borders</title>");
		writer.println("<line id=\"north\" y2=\"50\" x2=\"1230\" y1=\"50\" x1=\"50\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"east\" y2=\"974\" x2=\"1230\" y1=\"50\" x1=\"1230\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"south\" y2=\"974\" x2=\"50\" y1=\"974\" x1=\"1230\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"west\" y2=\"50\" x2=\"50\" y1=\"974\" x1=\"50\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("</g>");
		
	}
	private void createTitles(PrintWriter writer, ArrayList<Location> itinerary){
		int totalDistance = 0;
		double distance = 0;
		for(int i = 0; i < itinerary.size()-1; i++){
			distance += itinerary.get(i).legDistance;
		}
		distance += itinerary.get(itinerary.size()-1).legDistance;
		distance = Math.round(distance);
		totalDistance = (int)distance;
		
		writer.println("<g>");
		writer.println("<title>Titles</title>");
		writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"state\" y=\"40\" x=\"640\">Colorado</text>");
		writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"distance\" y=\"1014\" x=\"640\">" + totalDistance + " miles</text>");
		writer.println("</g>");
	}
	
	private void createLegs(PrintWriter writer, ArrayList<Location> itinerary){
		writer.println("<g>");
		writer.println("<title>Legs</title>");
		double x1;
		double y1;
		double x2;
		double y2;
		for(int i = 0; i < itinerary.size()-1; i++){
			x1 = itinerary.get(i).getLon_dd();
			y1 = itinerary.get(i).getLat_dd();
			x2 = itinerary.get(i+1).getLon_dd();
			y2 = itinerary.get(i+1).getLat_dd();
			double[] vals=toCartesian(x1,y1);
			double[] vals2=toCartesian(x2,y2);
			x1=vals[0];
			y1=vals[1];
			x2=vals2[0];
			y2=vals2[1];
			writer.println("<line id=\"leg" + i +"\" y2=\"" +y2 +"\" x2=\"" + x2 + "\" y1=\"" +y1 +"\" x1=\"" +x1 +"\" stroke-width=\"3\" stroke=\"#999999\"/>");
		}
		x1=itinerary.get(itinerary.size()-1).getLon_dd();
		y1=itinerary.get(itinerary.size()-1).getLat_dd();
		x2=itinerary.get(0).getLon_dd();
		y2=itinerary.get(0).getLat_dd();
		int size=itinerary.size()-1;
		double[]vals=toCartesian(x1,y1);
		double[] vals2=toCartesian(x2,y2);
		x1=vals[0];
		y1=vals[1];
		x2=vals2[0];
		y2=vals2[1];
		writer.println("<line id=\"leg" + size +"\" y2=\"" +y2 +"\" x2=\"" + x2 + "\" y1=\"" +y1 +"\" x1=\"" +x1 +"\" stroke-width=\"3\" stroke=\"#999999\"/>");
		writer.println("</g>");
	}
	
	private void createLocations(PrintWriter writer,ArrayList<Location> itinerary){
		writer.println("<g>");
		writer.println("<title>Locations</title>");
		for(int i = 0; i < itinerary.size(); i++){
			double x1 = itinerary.get(i).getLon_dd();
			double y1 = itinerary.get(i).getLat_dd();
			String city = itinerary.get(i).getCity();
			double[] vals=toCartesian(x1,y1);
			x1 = vals[0];
			y1 = vals[1];
		
			writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"id" + i + "\" y=\"" + y1 +"\" x=\"" + x1 +"\">" + city + "</text>");
		}
		writer.println("</g>");
	}
	
	private void createDistances(PrintWriter writer,ArrayList<Location> itinerary){
		writer.println("<g>");
		writer.println("<title>Distances</title>");
		for(int i = 0; i < itinerary.size()-1; i++){
			double x1 = itinerary.get(i).getLon_dd();
			double y1 = itinerary.get(i).getLat_dd();
			double x2 = itinerary.get(i+1).getLon_dd();
			double y2 = itinerary.get(i+1).getLat_dd();
			int distance = itinerary.get(i).legDistance;
			
			double[] vals=toCartesian(x1,y1);
			double[] vals2=toCartesian(x2,y2);
			double mid = Math.abs((vals[0]-vals2[0]) / 2);
			double mid2 = Math.abs((vals[1]-vals2[1]) / 2);
			double x = 0;
			double y = 0;
			
			if(vals[0] < vals2[0]){
				x = vals[0] + mid;
			}
			else{
				x = vals2[0] + mid;
			}
			if(vals[1] < vals2[1]){
				y = vals[1] + mid2;
			}
			else{
				y = vals2[1] + mid2;
			}
			writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"leg" + i + "\" y=\"" + y +"\" x=\"" + x +"\">" + distance + "</text>");
			
		}
		double x1=itinerary.get(itinerary.size()-1).getLon_dd();
		double y1=itinerary.get(itinerary.size()-1).getLat_dd();
		double x2=itinerary.get(0).getLon_dd();
		double y2=itinerary.get(0).getLat_dd();
		int distance = itinerary.get(itinerary.size()-1).legDistance;
		
		double[]vals=toCartesian(x1,y1);
		double[] vals2=toCartesian(x2,y2);
		x1=vals[0];
		y1=vals[1];
		x2=vals2[0];
		y2=vals2[1];
		
		double mid = Math.abs((vals[0]-vals2[0]) / 2);
		double mid2 = Math.abs((vals[1]-vals2[1]) / 2);
		double x = 0;
		double y = 0;
		
		if(vals[0] < vals2[0]){
			x = vals[0] + mid;
		}
		else{
			x = vals2[0] + mid;
		}
		if(vals[1] < vals2[1]){
			y = vals[1] + mid2;
		}
		else{
			y = vals2[1] + mid2;
		}
		writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"leg" + (itinerary.size()-1) + "\" y=\"" + y +"\" x=\"" + x +"\">" + distance + "</text>");
		writer.println("</g>");
	}
	
	
	public void Driver(){
		initializeTrip();
	}
}
