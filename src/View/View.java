package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import presenter.Presenter;
import Model.Location;
import Model.Model;

public class View {
	protected ArrayList<Location> itinerary;
	protected boolean showMileage = false;
	protected boolean showID = false;
	protected boolean showName = false;
	protected String filename;
	protected String SVG;
	protected String XML;
	
	public View(ArrayList<Location> itinerary, String filename){
		this.itinerary = itinerary;
		this.filename = filename;
		
	}
	
	
	public void initializeTrip(Presenter presenter,Model model){
		filename=filename.substring(0,filename.length()-4);
		this.SVG=presenter.SVG;
		this.XML=presenter.XML;
		if(presenter.GUI){
			GUI gui=new GUI(this,model);
			GUI.main(null);
		}
		else{
			createSvg(itinerary,filename+".svg",presenter.SVG);
			createXML(itinerary,filename+".xml");
		}

	}
	
	void createSvg(ArrayList<Location> itinerary,String filename, String SVG){
		try{
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			writer.println("<?xml version=\"1.0\"?>");
			writer.println("<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">");
			writeBackgroundSVG(filename,SVG,writer);		//this write's the given svg before appending our code 
			createBorders(writer);
			createTitles(writer,itinerary);
			createLegs(writer,itinerary);
			if(showName == true){
			createLocations(writer,itinerary,true);
			}
			if(showID == true){
			createLocations(writer,itinerary,false);

			}
			if(showMileage == true){
			createDistances(writer,itinerary);
			}
			writer.println("</svg>");
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error");
		}
	}
	
	private void writeBackgroundSVG(String filename, String SVG, PrintWriter printer) {
		 try {
		        File input = new File(SVG);
		        File output = new File(filename);
		        Scanner sc = new Scanner(input);
		        sc.nextLine();					//skip the already in place xml tags 
		        sc.nextLine();					//skip the already in place xml tags 
		        while(sc.hasNextLine()) {
		            String s = sc.nextLine();
		           // System.out.println(s);
		            printer.write(s+"\n");
		        }
		    }
		    catch(FileNotFoundException e) {
		        System.err.println("File not found. Please scan in new file.");
		    }
		
	}


	void createXML(ArrayList<Location> itinerary,String filename){
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
		vals[0]= (Math.abs(x-(-109)) / Math.abs((-102) - (-109)))*(1063.0085-50);
		//vals[0] = ((1180/7) * (x-109))+50;
		//System.out.println(t);
		vals[1]= (Math.abs(y-41) / (Math.abs(37-41))*(779.5144-50));
		//vals[1] = ((1180/7)*(41-y)) + ((1024-(4 * 1180/7))/2);
//		for(double i:vals){
//			System.out.println(i);
//		}
		return vals;
	}
	private void createBorders(PrintWriter writer){
		writer.println("<g>");
		writer.println("<title>Borders</title>");
		writer.println("<line id=\"north\" y2=\"34.76269\" x2=\"1027.66\" y1=\"34.76269\" x1=\"34.72952\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"east\" y2=\"744.70214\" x2=\"1027.6634\" y1=\"34.76269\" x1=\"1027\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"south\" y2=\"744.70214\" x2=\"34\" y1=\"744.70214\" x1=\"1027.6634\" stroke-width=\"5\" stroke=\"#666666\"/>");
		writer.println("<line id=\"west\" y2=\"34.76269\" x2=\"34.72952\" y1=\"744.70214\" x1=\"34\" stroke-width=\"5\" stroke=\"#666666\"/>");
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
		writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"state\" y=\"30\" x=\"531\">Colorado</text>");
		writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"distance\" y=\"775\" x=\"531\">" + totalDistance + " miles</text>");
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
	
	private void createLocations(PrintWriter writer,ArrayList<Location> itinerary,boolean name){
		writer.println("<g>");
		writer.println("<title>Locations</title>");
		for(int i = 0; i < itinerary.size(); i++){
			double x1 = itinerary.get(i).getLon_dd();
			double y1 = itinerary.get(i).getLat_dd();
			String var;
			if(name){
				var = itinerary.get(i).getCity();
			}
			else{
				var = itinerary.get(i).getId();
			}
			double[] vals=toCartesian(x1,y1);
			x1 = vals[0];
			y1 = vals[1];
		
			writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"id" + i + "\" y=\"" + y1 +"\" x=\"" + x1 +"\">" + var + "</text>");
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
}
