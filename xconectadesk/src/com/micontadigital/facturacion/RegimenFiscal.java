package com.micontadigital.facturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegimenFiscal {
	private String clave;
	private String descripcion;
	public RegimenFiscal() {
		
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
		return clave + " | " + descripcion;
	}
	public String getRegimenSelected(String valor){
        String selected = "";
       
        if (valor.equals(clave)){
            selected = "selected";
        }
       
        return selected;
    }
	public static RegimenFiscal getRegimenByClave(String clave){
		RegimenFiscal rf = new RegimenFiscal();
		//Connection conexion = null;
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			
			sql = "select clave, descripcion from regimenes where clave ='" + clave + "'"; 
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				rf.setClave(rs.getString(1));
				rf.setDescripcion(rs.getString(2));
			}	
			
			/*
			rf.setClave(clave);
			rf.setDescripcion("Personas Físicas con Actividades Empresariales y Profesionales");
			*/
			
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
		
		
		return rf;
	}

	
	public static ArrayList<RegimenFiscal> getRegimenes(){
		ArrayList<RegimenFiscal> regimenes = new ArrayList<RegimenFiscal>();
		RegimenFiscal rf = new RegimenFiscal();
		Connection conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			sql = "select clave, descripcion from regimenes order by 1"; 
			System.out.println(sql);
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				rf = new RegimenFiscal();
				rf.setClave(rs.getString(1));
				rf.setDescripcion(rs.getString(2));
				regimenes.add(rf);
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
		
		
		return regimenes;
	}

}
