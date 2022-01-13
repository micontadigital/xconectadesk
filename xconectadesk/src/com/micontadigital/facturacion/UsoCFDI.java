package com.micontadigital.facturacion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsoCFDI implements Serializable {

	private String clave,descripcion;

	public UsoCFDI(String clave, String descripcion) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public String getClaveDescripcion() {
		return clave + " | " + descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public static ArrayList<UsoCFDI> getUsosCFDI(){
		ArrayList<UsoCFDI> uCFDI = new ArrayList<UsoCFDI>();
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			sql = "select clave, descripcion from usos_cfdi";
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				uCFDI.add(new UsoCFDI(rs.getString(1), rs.getString(2)));
				
			}	
			
		} catch (SQLException e){
			e.printStackTrace();
			
		} finally {
			
				try {
					if (rs!=null)rs.close();
					if (st!=null)st.close();
					if (conexion!=null)conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		return uCFDI;
	}


	public static UsoCFDI getUsoCFDIByClave(String clave){
		UsoCFDI uCFDI = null;
		//Connection conexion = null;
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			
			sql = "select clave, descripcion from usos_cfdi where clave='" + clave + "'";
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				uCFDI = new UsoCFDI(rs.getString(1), rs.getString(2));
				
			}	
			
			//uCFDI = new UsoCFDI(clave, "GASTOS EN GENERAL");
			
		} catch (SQLException e){
			e.printStackTrace();
			
		} finally {
			
				try {
					if (rs!=null)rs.close();
					if (st!=null)st.close();
					if (conexion!=null)conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		return uCFDI;
	}

}
