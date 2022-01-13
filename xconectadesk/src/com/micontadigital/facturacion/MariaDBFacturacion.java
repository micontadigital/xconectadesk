package com.micontadigital.facturacion;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MariaDBFacturacion {
    static Connection conexion;
    
    static String host;

	static String usuario;

	static String clave;

	static String db;
    
    public MariaDBFacturacion(String host, String usuario, String clave, String db){
    	this.host = host; 
    	this.usuario = usuario;
    	this.clave = clave;
    	this.db = db;
    	
    
    	
    }
    
  
    public static Connection GetConnection(String host, String usuario, String clave, String db){
    	try{
    		
    		//host = "192.168.15.200";
    		
    		//host = "idalytec.com";
    		
    		host = "bd.micontadigital.com";
    		
    		
    		Class.forName("org.mariadb.jdbc.Driver");
	    	String cadena = "jdbc:mysql://" + host + ":3306/" + db;
    		System.out.println(cadena);
    		usuario = "micontadigital";
			clave = "Acceso2014.";
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

