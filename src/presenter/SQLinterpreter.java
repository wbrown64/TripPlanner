package presenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Model.Coordinates;
import Model.Location;

public class SQLinterpreter {
	public String myDriver = "com.mysql.jdbc.Driver";
    public String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    String username;
    String password;
    
    public SQLinterpreter(String username, String password){
    	this.username=username;
    	this.password=password;
    	//querey();
    }
   
	
    String[] query(String field,String table,String type){
    	ArrayList<String>results=new ArrayList<String>();
    	String query="Select "+field+" from "+table+" where "+type;
    	//System.out.println(query);
        try	{ // connect to the database 
            Class.forName(myDriver); 
            Connection conn = DriverManager.getConnection(myUrl, username, password);	

			try { // create a statement
				Statement st = conn.createStatement();
				
				try { // submit a query
					//String query = "SELECT name from airports where type='large_airport' limit 15";
					ResultSet rs = st.executeQuery(query);
					
					try { // iterate through the query results and print
						while (rs.next())
						{
//							String code = rs.getString("code");
//							String name = rs.getString("name");
//							System.out.printf("%s,%s\n", name,code);
							String line=specify(query,rs);
							//System.out.println(line);
							results.add(line);
						}
					} finally { rs.close(); }
				} finally { st.close(); }
			} finally { conn.close(); }
		} catch (Exception e) {
			System.err.printf("Exception: ");
			System.err.println(e.getMessage());
		}
        Set<String> hs = new HashSet<>();
        hs.addAll(results);
        results.clear();
        results.addAll(hs);
        results.sort(String::compareToIgnoreCase);
        return results.toArray(new String[0]);
    }
   public String[] selectContinents(String[] continents){
    	String type="name='"+continents[0]+"'";
    	for(int i=1;i<continents.length;i++){
    		type+=" or name='"+continents[i]+"'";
    	}
    	//System.out.println(type);
    	String[] results=query("name, id","continents",type);
    	return results;
    }
   public String[] selectCountries(String[] continents){
    	String type="continent= '"+continents[0]+"'";
    	for(int i=1;i<continents.length;i++){
    		type+=" or continent='"+continents[i]+"'";
    	}
    	//System.out.println(type);
    	String[] results=query("name","countries",type);
    	return results;
    }
   public String[] selectCountryCodes(String[]countries){
   	String type="name= '"+countries[0]+"'";
	for(int i=1;i<countries.length;i++){
		type+=" or name='"+countries[i]+"'";
	}
	String[] results=query("name,code","countries",type);
	return results;
	
   }
   public String[] selectReigons(String[] countryCodes){
    	String type="iso_country='"+countryCodes[0]+"'";
    	for(int i=1;i<countryCodes.length;i++){
    		type+=" or iso_country='"+countryCodes[i]+"'";
    	}
    	//System.out.println(type);
    	String[] results=query("name, code","regions",type);
    	return results;
    }
   public String[] selectRegionCode(String[] regions){
	   String type="name='"+regions[0]+"'";
	   for(int i=1;i<regions.length;i++){
   		type+=" or name='"+regions[i]+"'";
   		}
   	//System.out.println(type);
   		String[] results=query("name, code","regions",type);
   		return results;
   }
   public String[] selectMunicipality(String[] regionCodes){
	   String type="iso_region='"+regionCodes[0]+"'";
	   for(int i=1;i<regionCodes.length;i++){
   		type+=" or iso_region='"+regionCodes[i]+"'";
   		}
	   String[] results=query("name,municipality","airports",type);
	   return results;
   }
  public  String[] selectAirports(String[] municipality){
    	String type="municipality='"+municipality[0]+"'";
    	for(int i=1;i<municipality.length;i++){
    		type+=" or municipality='"+municipality[i]+"'";
    	}
    	String[] results=query("name, id","airports",type);
    	return results;
    }
    String specify(String query,ResultSet rs) throws SQLException{
    	String var=null;
		//String name=rs.getString("name");
    	if(query.contains("continents"))
    		var = rs.getString("id");
    	else if(query.contains("municipality")&&query.contains("airports")&&!query.contains("iso_region"))
    		var=rs.getString("name");
    	else if(query.contains("municipality")&&query.contains("iso_region"))
    		var=rs.getString("municipality");
    	else if(query.contains("regions")&&!query.contains("iso_country"))
    		var=rs.getString("code");
    	else if(query.contains("countries")&&query.contains("code"))
    		var=rs.getString("code");
    	else if(query.contains("countries")||query.contains("regions")||query.contains("airports"))
    		var=rs.getString("name");
    	
    	
		//System.out.printf("%s,%s\n", name,var);
		return var;

    }
   public Location dataQuery(String name){
	     String myDriver = "com.mysql.jdbc.Driver";
	        String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
	        String result = null;
	        Location L = null;
	        try	{ // connect to the database 
	            Class.forName(myDriver); 
	            Connection conn = DriverManager.getConnection(myUrl, "wbrown64","830285807");	

				try { // create a statement
					Statement st = conn.createStatement();
					
					try { // submit a query
						String query = "SELECT id,name,municipality,latitude,longitude,elevation_ft FROM airports where name='"+name+"'";
						ResultSet rs = st.executeQuery(query);
						//System.out.println(query);
						try { // iterate through the query results and print
							while (rs.next())
							{
								String id = rs.getString("Id");
								name = rs.getString("name");
								String munic=rs.getString("municipality");
								String latitude=rs.getString("latitude");
								String longitude=rs.getString("longitude");
								String elevation=rs.getString("elevation_ft");
								Coordinates c=new Coordinates(latitude,longitude);
								L=new Location(id,name,munic,latitude,longitude,elevation,c);
								//System.out.printf("%s,%s,%s,%s,%s,%s\n", id, name,munic,latitude,longitude,elevation);
								result=id+","+name+","+munic+","+latitude+","+longitude+","+elevation;
							}
						} finally { rs.close(); }
					} finally { st.close(); }
				} finally { conn.close(); }
			} catch (Exception e) {
				System.err.printf("Exception: ");
				System.err.println(e.getMessage());
			}
	       
	        return L;
   }
    
    public static void main(String[] args){
    	SQLinterpreter sqli=new SQLinterpreter("wbrown64","830285807");
    	//String[] c={"Europe","Asia"};
    	//sqli.selectContinents(c);
    	//String[] s={"AS","NA"};
    	//String[] s={"EU"};
    	//sqli.selectCountries(s);
    	//String[] d={"XK"};
    	//sqli.selectReigons(d);
    	//String[] e={"US-CO"};
    	//sqli.selectAirports(e);
    	//String[] f={"France"};
    	//sqli.selectCountryCodes(f);
    	//String[] g={"Colorado"};
    	//sqli.selectRegionCode(g);
    	sqli.dataQuery("Denver International Airport");
    }
   
    
  
}