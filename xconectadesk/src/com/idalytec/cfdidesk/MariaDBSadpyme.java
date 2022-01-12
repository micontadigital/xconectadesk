package com.idalytec.cfdidesk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBSadpyme {
	 static Connection conexion;
	    
	    static String host;

		static String usuario;

		static String clave;

		static String db;
	    
	    public MariaDBSadpyme(String host, String usuario, String clave, String db){
	    	this.host = host; 
	    	this.usuario = usuario;
	    	this.clave = clave;
	    	this.db = db;
	    	
	    
	    	
	    }
	    
	  
	    public static Connection GetConnection(String host, String usuario, String clave, String db){
	    	try{
	    		
	    		//host = "192.168.15.200";
	    		
	    		
	    		System.out.println(db);
	    		
	    		host = "idalytec.com";

	    		
	    		System.out.println("host " + host);
	    		
	    		Class.forName("org.mariadb.jdbc.Driver");
		    	String cadena = "jdbc:mysql://" + host + ":3306/" + db;
	    		System.out.println(cadena);
	    		conexion = DriverManager.getConnection(cadena , usuario, clave);
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	            conexion=null;
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            conexion=null;
	        }
	        return conexion;
	        
	    }
	    
	    public static Connection GetConnectionGlobal(String host, String usuario, String clave){
	    	try{
	    		
	    		//host = "192.168.15.200";
	    		
	    		host = "idalytec.com";
	    		
	    		Class.forName("org.mariadb.jdbc.Driver");
	    		String cadena = "jdbc:mysql://" + host + ":3306";
	    		System.out.println(cadena);
	    		conexion = DriverManager.getConnection(cadena , usuario, clave);
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	            conexion=null;
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            conexion=null;
	        }
	        return conexion;
	        
	    }
	}

