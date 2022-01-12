package com.idalytec.cfdidesk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ObtenDisponiblesSadpymeServlet
 */
@WebServlet("/ObtenDisponiblesSadpymeServlet")
public class ObtenDisponiblesSadpymeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenDisponiblesSadpymeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pass = request.getParameter("pass");
		String correo = request.getParameter("correo");
		
		Connection conn = MariaDBSadpyme.GetConnection("localhost","usrusuarios","AccesoUsuarios01","usuarios_sadpyme");
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		try{
			
			st = conn.createStatement();
			
			sql = "select id,nombre_bd,usr_bd,pass_bd,usuario, empresa, local from usuarios where usuario='" + correo + "' and pass='" + pass + "'" ;
			
			System.out.println(sql);
			
			rs = st.executeQuery(sql);
			
			int idUsuario = 0;
			
			while (rs.next()){
				
				idUsuario = rs.getInt(1);
				
			}
			
			sql = "select id, description, referencia, vigencia from mp where status='approved'"
					+ " and usuario=" + idUsuario + " and vigencia>=now() order by vigencia,id" ;
					
			System.out.println(sql);
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			int disponibles = 0;
			
			while (rs.next()){
				int idServicio = rs.getInt(1);
				String referencia =  rs.getString(3);
				int cant = 0;
				if (referencia.indexOf("FAC")!=-1) {
					cant = Integer.parseInt(referencia.replace("FAC", ""));
					
				}
				
				sql = "select count(id) from facturas_mp where id_mp=" + idServicio;
				
				System.out.println(sql);
				
				rs1 = st.executeQuery(sql);
				int facturas = 0;
				while (rs1.next()) {
					facturas = rs1.getInt(1);
				}
				
				disponibles += (cant - facturas);
				
				System.out.println("disponibles " + cant + " " + facturas);
				
			}
			
			
			response.getWriter().append(String.valueOf(disponibles));
			
			
		} catch (SQLException e){
			e.printStackTrace();
			
		} finally {
			
				try {
					if (rs!=null)rs.close();
					if (rs1!=null)rs1.close();
					if (st!=null)st.close();
					if (conn!=null)conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
