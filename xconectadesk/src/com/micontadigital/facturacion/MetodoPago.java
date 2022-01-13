package com.micontadigital.facturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MetodoPago {
	private String clave,descripcion;

	public MetodoPago(String clave, String descripcion) {
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
	public static ArrayList<MetodoPago> getMetodosPago(){
		ArrayList<MetodoPago> mp = new ArrayList<MetodoPago>();
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			sql = "select clave, descripcion from metodos_pago";
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				mp.add(new MetodoPago(rs.getString(1), rs.getString(2)));
				
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
		
		
		return mp;
	}


	public static MetodoPago getMetodoPagoByClave(String clave){
		MetodoPago mp = null;
		//Connection conexion = null;
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			
			sql = "select clave, descripcion from metodos_pago where clave = '" + clave + "'";
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				mp = new MetodoPago(rs.getString(1), rs.getString(2));
				
			}
			
			
			//mp = new MetodoPago(clave, "PAGO EN UNA SOLA EXIBICION");
			
			
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
		
		
		return mp;
	}
}
