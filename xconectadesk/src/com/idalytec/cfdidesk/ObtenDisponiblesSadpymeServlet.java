package com.idalytec.cfdidesk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		Connection conn = MariaDB.getConexion();
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		try{
			
			st = conn.createStatement();
			
			int idUsuario = 0;
			
			sql = ("select id,nombre_bd,usr_bd,pass_bd,usuario, empresa, local, id_sucursal, id_terminal"
					+ ", id_usuario_local, tipo, woo_activo from usuarios "
					+ "where usuario='" + correo + "' and pass='" + pass + "'" );
        	
    		System.out.println(sql);
        	rs = st.executeQuery(sql);
        	
        	while (rs.next()) {
        		idUsuario = rs.getInt(1);
        		
        		
        		
        	}
        	            	
        	rs = st.executeQuery(sql);
			
			int WooActivo = 0;
			
			Usuario u  = null;
			
			while (rs.next()){
				u = new Usuario();
				
				u.setId(rs.getInt(1));
				u.setNombreBD(rs.getString(2));
				u.setUsrBD(rs.getString(3));
				u.setPassBD(rs.getString(4));
				//u.setNombre(name);
				//u.setNick(usuario);
    		
			}
			
			Connection conexionUsr = null;
			
			int cantidad = 0;
			int facturas = 0;
    		
        	conexionUsr = MariaDBSadpyme.GetConnection("app.xconecta.com", u.getUsrBD(), u.getPassBD(), u.getNombreBD());
        	Statement stUsr = conexionUsr.createStatement();
			
			Date fecha = new Date();
			sql = "select cantidad, fecha from conekta_ordenes where concepto='FACTURAS'"
					+ " and usuario=" + u.getId()
					+ " and status in ('paid','active') and ADDDATE(fecha, INTERVAL 1 MONTH)>=now()";
			
			st = conn.createStatement();
			System.out.println(sql);
			rs = st.executeQuery(sql);
			int x = 0;
			while (rs.next()) {
				cantidad += rs.getInt(1);
				if (x==0) {
					fecha = rs.getDate(2);
				}
			}
			
				
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sql = "select count(id) from pv_facturas_finkok where fecha>STR_TO_DATE('" + sdf.format(fecha) + "','DD-MM-YYYY')";
			st = conexionUsr.createStatement();
			System.out.println(sql);
			rs = stUsr.executeQuery(sql);
			while (rs.next()) {
				facturas = rs.getInt(1);
			}
				
			
			
			response.getWriter().append(String.valueOf(cantidad- facturas));
			
			
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
