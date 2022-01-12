package com.idalytec.cfdidesk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class MariaDB {
	static Connection conexion;
	static String error = "";
	  
    public static Connection getConexion(){
    	try{
    		Class.forName("org.mariadb.jdbc.Driver"); 
    		
    		String host = "localhost";
    		
    		host = "idalytec.com";
    		
    		System.out.println("jdbc:mariadb://" + host + ":3306/cfdiapp");
    		
        	conexion = DriverManager.getConnection("jdbc:mariadb://" + host + ":3306/cfdiapp", "cfdiappuser", "Acceso01.");
        	//conexion = DriverManager.getConnection("jdbc:mariadb://idalytec.com:3306/cfdiapp", "cfdiappuser", "Acceso01.");
        }
        catch(SQLException ex)
        {   
            conexion=null;
            error = ex.getMessage();
            ex.printStackTrace();
        }
    	catch(ClassNotFoundException ex)
    	{
    		conexion=null;
            error = ex.getMessage();
            ex.printStackTrace();
    	}
    	catch(Exception ex)
        {
            conexion=null;
            error = ex.getMessage();
            ex.printStackTrace();
        }
        return conexion;
        
   	}
    
    public static String getError(){
    	return error;
    }
	
}
