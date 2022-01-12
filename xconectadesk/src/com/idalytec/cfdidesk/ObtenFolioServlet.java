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
			
			sql = "select id from usuarios where correo='" + correo + "' and password='" + pass + "'" ;
        	
        	rs = st.executeQuery(sql);
        	
        	while (rs.next()) {
        		idUsuario = rs.getString(1);
        	}
        	
        	
        	if (idUsuario.length()>0){
			
			
			
        		sql = "select ifnull(max(folio),0)+1 from facturas where usuario=" + idUsuario;

        		rs = st.executeQuery(sql);
        		int facturas=0;
        		while (rs.next()) {
        			facturas = rs.getInt(1);
        		}
        		
        		response.getWriter().append(String.valueOf(facturas));
        	} else {
        		
        		response.getWriter().append(String.valueOf(1));
        		
        	}

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
