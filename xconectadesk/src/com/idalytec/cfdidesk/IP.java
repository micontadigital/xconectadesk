package com.idalytec.cfdidesk;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class IP {
    static Connection conexion;
    
    static String host;

	static String usuario;

	static String clave;

	static String db;
    
    public IP(){
    	
    	
    }
    
  
    public static String GetIP(String empresa, String local){
    	
    	String ip = "localhost";
    	
    	try{
    		
    		
        	host = "idalytec.com"; 
        	usuario = "dnsusr";
        	clave = "Acceso2014.";
        	db = "dns";
    		
    		Class.forName("org.mariadb.jdbc.Driver");
	    	String cadena = "jdbc:mysql://" + host + ":3306/" + db;
    		System.out.println(cadena);
    		conexion = DriverManager.getConnection(cadena , usuario, clave);
    		
    		String sql = "select ip from direcciones where empresa='" + empresa + "' and local='" + local + "'";
    		
    		System.out.println(sql);
    		
    		Statement st = conexion.createStatement();
    		ResultSet rs = st.executeQuery(sql);
    		
    		
    		
    		while (rs.next()) {
    			ip = rs.getString(1);
    		}
    		
    	
    		System.out.println(ip);
    		
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
        return ip;
        
    }
    
    public static Connection GetConnectionGlobal(String host, String usuario, String clave){
    	try{
    		
    		//host = "192.168.15.200";
    		
    		//host = "idalytec.com";
    		
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
    public static String getDir() {
    	//return "d:\\cfdiapp\\";
    	//return "/home/cfdiapp/";
    	return "/Users/erickidaly/micontadigital/";
    }
}

;