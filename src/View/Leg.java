package View;

public class Leg {

	protected int seq;
	protected String start;
	protected String end;
	protected String mileage;
	
	public Leg(){
		
	}
	
	public void setSeq(int temp){
		seq = temp;
	}
	
	public int getSeq(){
		return seq;
	}
	
	public void setStart(String temp){
		start = temp;
	}
	
	public String getStart(){
		return start;
	}
	
	public void setEnd(String temp){
		end = temp;
	}
	
	public String getEnd(){
		return end;
	}
	
	public void setMileage(String temp){
		mileage = temp;
	}
	
	public String getMileage(){
		return mileage;
	}
}
