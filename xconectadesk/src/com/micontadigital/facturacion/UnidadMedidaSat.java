package com.micontadigital.facturacion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnidadMedidaSat implements Serializable {
	private String clave;
	private String descripcion;
	
	
	public UnidadMedidaSat() {
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
	public static UnidadMedidaSat getUnidadMedidaByClave(String clave){
		UnidadMedidaSat um = new UnidadMedidaSat();
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			sql = "select clave, descripcion from unidades_medida where clave ='" + clave + "'"; 
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				um.setClave(rs.getString(1));
				um.setDescripcion(rs.getString(2));
				
				System.out.println("Unidad Medida Sat " + um.getClave());
				
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
		
		
		return um;
	}


}
