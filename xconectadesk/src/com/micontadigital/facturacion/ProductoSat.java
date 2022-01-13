package com.micontadigital.facturacion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoSat implements Serializable {
	private String clave;
	private String descripcion;
	
	public ProductoSat() {
		
		super();
		// TODO Auto-generated constructor stub
		clave = "";
		descripcion = "";
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
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getClaveDescripcion(){
		if (clave.length()>0&&descripcion.length()>0){
			return clave + " | " + descripcion;
		} else {
			return "";
		}
	}
	public static ProductoSat getProductoByClave(String clave){
		ProductoSat p = new ProductoSat();
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			sql = "select clave, descripcion from productos where clave ='" + clave + "'"; 
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				p.setClave(rs.getString(1));
				p.setDescripcion(rs.getString(2));
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
		
		System.out.println(p.getClave());
		return p;
	}

}
