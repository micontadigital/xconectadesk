package com.micontadigital.facturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FormaPago {
	private String clave,descripcion;

	public FormaPago(String clave, String descripcion) {
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
	public static ArrayList<FormaPago> getFormasPago(){
		ArrayList<FormaPago> fp = new ArrayList<FormaPago>();
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			sql = "select clave, descripcion from formas_pago";
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				fp.add(new FormaPago(rs.getString(1), rs.getString(2)));
				
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
		
		
		return fp;
	}
	
	public static FormaPago getFormaPagoByClave(String clave){
		FormaPago fp = null;
		//Connection conexion = null;
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			
			sql = "select clave, descripcion from formas_pago where clave = '" + clave + "'";
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				fp = new FormaPago(rs.getString(1), rs.getString(2));
				
			}	
			
			
			//fp = new FormaPago(clave, "TRANSFERENCIA ELECTRÒNICA DE FONDOS");
			
		} catch (Exception e){
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
		
		
		return fp;
	}


}
