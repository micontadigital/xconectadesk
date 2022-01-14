package com.idalytec.cfdidesk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ObtenFolioServlet
 */
@WebServlet("/ObtenFolioServlet")
public class ObtenFolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenFolioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String pass = request.getParameter("pass");
		String correo = request.getParameter("correo");
		
		System.out.println(pass);
		System.out.println(correo);
		
		
		String idUsuario = "";
		
		Connection conexion = MariaDB.getConexion();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement dml_stmt = null;
        String sql = "";
		
		
		try {
			st = conexion.createStatement();
			
			
        		sql = ("select id,nombre_bd,usr_bd,pass_bd,usuario, empresa, local, id_sucursal, id_terminal"
    					+ ", id_usuario_local, tipo, woo_activo from usuarios "
    					+ "where usuario='" + correo + "' and pass='" + pass + "'" );
            	
        		System.out.println(sql);
            	rs = st.executeQuery(sql);
            	
            	while (rs.next()) {
            		idUsuario = rs.getString(1);
            		
            		
            		
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
        		
            	conexionUsr = MariaDBSadpyme.GetConnection("app.xconecta.com", u.getUsrBD(), u.getPassBD(), u.getNombreBD());
            	Statement stUsr = conexionUsr.createStatement();
			
        		sql = "select ifnull(max(folio),0)+1 from pv_facturas_finkok";

        		rs = stUsr.executeQuery(sql);
        		int facturas=0;
        		while (rs.next()) {
        			facturas = rs.getInt(1);
        		}
        		
        		response.getWriter().append(String.valueOf(facturas));
        	

		} catch (SQLException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
            
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}	
				if (st != null) {
					st.close();
				}
				if (dml_stmt != null) {
					dml_stmt.close();
				}
				if (conexion != null) {
					conexion.close();
				}
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
