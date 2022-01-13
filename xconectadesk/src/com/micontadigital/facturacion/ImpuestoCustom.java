package com.micontadigital.facturacion;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.idalytec.test.MariaDB;



public class ImpuestoCustom {
	private int id;
	private String nombre;
	public ImpuestoCustom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public static ImpuestoCustom getImpuesto(String clave) {
		ImpuestoCustom impuesto = new ImpuestoCustom();
		
		
		StringWriter errors = new StringWriter();
		
		
		
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try{
			
			conexion = MariaDBFacturacion.GetConnection("localhost", "cfdiappuser", "Acceso01.", "cfdiapp");
			
			sql = "select id, descripcion from impuestos where clave='" + clave + "'";
			
			System.out.println(sql);
			
			st = conexion.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				impuesto.setId(rs.getInt(1));
				impuesto.setNombre(rs.getString(2));
			}
			
			/*
			impuesto.setId("002");
			impuesto.setNombre(rs.getString(2));
			*/
		
			
		} catch (SQLException e){
			e.printStackTrace(new PrintWriter(errors));
			JOptionPane.showMessageDialog(null, errors.toString(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
			
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
		
		
		return impuesto;
	}
		

}
