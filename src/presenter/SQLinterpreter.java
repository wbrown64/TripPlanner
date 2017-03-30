package presenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLinterpreter {
	public String myDriver = "com.mysql.jdbc.Driver";
    public String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    String username;
    String password;
    
    SQLinterpreter(String username, String password){
    	this.username=username;
    	this.password=password;
    	//querey();
    }
   
	
    ArrayList<String> query(String field,String table,String type){
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
							System.out.println(line);
						}
					} finally { rs.close(); }
				} finally { st.close(); }
			} finally { conn.close(); }
		} catch (Exception e) {
			System.err.printf("Exception: ");
			System.err.println(e.getMessage());
		}
        return results;
    }
    void selectContinents(String[] continents){
    	String type="name='"+continents[0]+"'";
    	for(int i=1;i<continents.length;i++){
    		type+=" or name='"+continents[i]+"'";
    	}
    	//System.out.println(type);
    	query("name, id","continents",type);
    }
    void selectCountries(String[] continents){
    	ArrayList<String>countries=new ArrayList<String>();
    	String type="continent= '"+continents[0]+"'";
    	for(int i=1;i<continents.length;i++){
    		type+=" or continent='"+continents[i]+"'";
    	}
    	//System.out.println(type);
    	query("name, code","countries",type);
    }
    void selectReigons(String[] countryCodes){
    	String type="iso_country='"+countryCodes[0]+"'";
    	for(int i=1;i<countryCodes.length;i++){
    		type+=" or iso_country='"+countryCodes[i]+"'";
    	}
    	//System.out.println(type);
    	query("name, code","regions",type);
    }
    void selectAirports(String[] regionCodes){
    	String type="iso_region='"+regionCodes[0]+"'";
    	for(int i=1;i<regionCodes.length;i++){
    		type+=" or iso_region='"+regionCodes[i]+"'";
    	}
    	query("name, id","airports",type);
    }
    String specify(String query,ResultSet rs) throws SQLException{
    	String var=null;
		String name=rs.getString("name");
    	if(query.contains("continents")||query.contains("airports"))
    		var = rs.getString("id");
    	else if(query.contains("countries")||query.contains("regions"))
    		var=rs.getString("code");
    	
		//System.out.printf("%s,%s\n", name,var);
		return name+", "+var;

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
    	String[] e={"US-CO"};
    	sqli.selectAirports(e);
    	
    }
   
    
  
}