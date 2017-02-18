package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.Location;

public class View {
	protected ArrayList<Leg> legArray = new ArrayList<Leg>();
	protected ArrayList<Location> itinerary;
	protected boolean showMileage = false;
	protected boolean showID = false;
	protected boolean showName = false;
	
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

	public View(ArrayList<Location> itinerary){//ArrayList<Location> itinerary
		this.itinerary = itinerary;
	}
	
	public void initializeTrip(){
		createSvg(itinerary);
	}
	
	public String display(String text){
		//System.out.println(text);
		return text;
	}
	public void addLeg(int seq,String start,String end, String mileage){	
		Leg leg = new Leg();
		leg.setSeq(seq);
		leg.setStart(start);
		leg.setEnd(end);
		leg.setMileage(mileage);
		legArray.add(leg);
	}
	
	public void addLine(double x1, double y1, double x2, double y2){
		//implement
	}
	
	//public void addLabel(double)
	
	public void addHeader(String title){
		//implement
	}
	
	public void addFooter(String foot){
		//implement
	}
	
	public void finalizeTrip(){
		//implement
	}
	
	public void createBorders(PrintWriter writer){
		writer.println("<g>");
		writer.println("<title>Borders</title>");
		writer.println("<line id=\"north\" y2=\"50\" x2=\"1230\" y1=\"50\" x1=\"50\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"east\" y2=\"974\" x2=\"1230\" y1=\"50\" x1=\"1230\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"south\" y2=\"974\" x2=\"50\" y1=\"974\" x1=\"1230\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"west\" y2=\"50\" x2=\"50\" y1=\"974\" x1=\"50\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("</g>");
		
	}
	public void createTitles(PrintWriter writer){
		writer.println("<g>");
		writer.println("<title>Titles</title>");
		writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"state\" y=\"40\" x=\"640\">Colorado</text>");
		writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"distance\" y=\"1014\" x=\"640\">" + 9999 + " miles</text>");
		writer.println("</g>");
	}
	
	public void createLegs(PrintWriter writer, ArrayList<Location> itinerary){
		writer.println("<g>");
		writer.println("<title>Legs</title>");
		for(int i = 0; i < itinerary.size()-1; i++){
			int x1 = itinerary.get(i).getLon_dd();
			int y1 = itinerary.get(i).getLat_dd();
			int x2 = itinerary.get(i+1).getLon_dd();
			int y2 = itinerary.get(i+1).getLat_dd();
			writer.println("<line id=\"leg" + i +"\" y2=\"" +y2 +"\" x2=\"" + x2 + "\" y1=\"" +y1 +"\" x1=\"" +x1 +"\" stroke-width=\"3\" stroke=\"#999999\"/>");
		}
		writer.println("</g>");
	}
	public void createSvg(ArrayList<Location> itinerary){
		try{
			PrintWriter writer = new PrintWriter("out.svg", "UTF-8");
			writer.println("<?xml version=\"1.0\"?>");
			writer.println("<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">");
			createBorders(writer);
			createTitles(writer);
			writer.println("</svg>");
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error");
		}
	}
	
	
	public void Driver(String args[]){
		initializeTrip();
	}
}
