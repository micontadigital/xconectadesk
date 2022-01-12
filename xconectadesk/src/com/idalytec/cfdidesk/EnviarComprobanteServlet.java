package com.idalytec.cfdidesk;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idalytec.sadpyme.facturacion.CorreoFormato;
import com.idalytec.sadpyme.formatos.GenerarComprobante1;
import com.idalytec.sadpyme.formatos.GenerarComprobante3;
import com.idalytec.test.Emisor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;

import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.cfd._3.Comprobante.Receptor;

/**
 * Servlet implementation class EnviarComprobanteServlet
 */
@WebServlet("/EnviarComprobanteServlet")
public class EnviarComprobanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarComprobanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pdfFileName = "";
		String xmlFileName = "";
		String pdf = "";
		String xml = "";
		
		String error = "";
		String mensaje = "";

		String pass = request.getParameter("pass");
		String correo = request.getParameter("correo");
		String idComprobante = request.getParameter("idComprobante");
		String destino = request.getParameter("correoReceptor");
		
		
		
		
		String calle = URLDecoder.decode(request.getParameter("calle"), "UTF-8");
		String numeroExterior = URLDecoder.decode(request.getParameter("numeroExterior"), "UTF-8");;
		String numeroInterior = URLDecoder.decode(request.getParameter("numeroInterior"), "UTF-8");
		String colonia = URLDecoder.decode(request.getParameter("colonia"), "UTF-8");
		String municipio = URLDecoder.decode(request.getParameter("municipio"), "UTF-8");
		String estado = URLDecoder.decode(request.getParameter("estado"), "UTF-8");
		String codigoPostal = URLDecoder.decode(request.getParameter("codigoPostal"), "UTF-8");
		
		
		
		correo = URLDecoder.decode(correo, "UTF-8");
		pass = URLDecoder.decode(pass, "UTF-8");
		
		String idUsuario = "";

		Connection conexion = MariaDB.getConexion();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement dml_stmt = null;
        String sql = "";

		
		
		BaseColor color = new BaseColor(240, 240, 240);
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd'T'hh:mm:ss");
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		request.setCharacterEncoding("UTF-8");
		String uuid = request.getParameter("uuid");

			
			// String contextPath =
			// getServletContext().getRealPath(File.separator);
			// File pdfFile = new File(contextPath + pdfFileName);

		pdfFileName = IP.getDir() + "factura" + idComprobante + ".pdf";
		xmlFileName = IP.getDir() + "factura" + idComprobante + ".xml";

		
		String xmlRecords = "";
		String observaciones = "";
		int formato = 1;

		File pdfFile = new File(pdfFileName);

		/*
		File folder = new File("c:\\idalytec.com\\facturacionWEB");
		folder.mkdirs();
		*/
		
		
		try {
			st = conexion.createStatement();
			
			
			System.out.println(destino);
			
			sql = "select id, formato from usuarios where correo='" + correo + "' and password='" + pass + "'" ;
        	
        	rs = st.executeQuery(sql);
        	
        	while (rs.next()) {
        		idUsuario = rs.getString(1);
        		formato = rs.getInt(2);
        	}
        	
        	
        	if (idUsuario.length()>0){
        		
    			if (destino.length()>0){
    				
    				destino = URLDecoder.decode(destino, "UTF-8");
    			
    			} else {
    				
    				sql = "select correo from facturas where id=" + idComprobante ;
    	        	
    	        	rs = st.executeQuery(sql);
    	        	
    	        	while (rs.next()) {
    	        		destino = rs.getString(1);
    	        	}
    				
    				
    			}
        		
        		sql = "select f.xml, f.observaciones from facturas f where f.id = " + idComprobante;
        		
        		rs = st.executeQuery(sql);
        		
        		while (rs.next()) {
        			xmlRecords = rs.getString(1);
        			observaciones = rs.getString(2);
        			
        		}
        	
        		Comprobante comprobante = null;
        		com.idalytec.test.Emisor emisor = new com.idalytec.test.Emisor();
    			com.idalytec.test.Receptor receptor = new com.idalytec.test.Receptor();
    		
            	receptor.setCalle(calle);
            	receptor.setNumeroExterior(numeroExterior);
            	receptor.setNumeroInterior(numeroInterior);
            	receptor.setColonia(colonia);
            	receptor.setMunicipio(municipio);
            	receptor.setEstado(estado);
            	receptor.setCodigoPostal(Integer.parseInt(codigoPostal));
            	
        	
            	conexion = MariaDBSadpyme.GetConnection("localhost","usrusuarios","AccesoUsuarios01","usuarios_sadpyme");
				st = conexion.createStatement();
            	
				sql = "select id,nombre_bd,usr_bd,pass_bd,usuario, empresa, local from usuarios where id=" + idUsuario;
				rs = st.executeQuery(sql);
				
				String nombre_bd = "";
				String usr_bd = "";
				String pass_bd = "";
				
				
				while (rs.next()){
					nombre_bd = rs.getString(2);
					usr_bd = rs.getString(3);
					pass_bd = rs.getString(4);
					
					
				}
				
				conexion = MariaDBSadpyme.GetConnection("localhost",usr_bd,pass_bd,nombre_bd);
				st = conexion.createStatement();
				
				sql = "select rfc, razon_social, calle, numero_exterior, numero_interior, colonia, municipio, estado, codigo_postal"
        				+ ", regimen "
            			+ " from pv_empresas where id=1";
            
        		System.out.println(sql);
            	rs = st.executeQuery(sql);
            	while (rs.next()){
            		emisor.setRfc(rs.getString(1));
                	emisor.setNombre(rs.getString(2));
                	emisor.setCalle(rs.getString(3));
                	emisor.setNumeroExterior(rs.getString(4));
                	emisor.setNumeroInterior(rs.getString(5));
                	emisor.setColonia(rs.getString(6));
                	emisor.setMunicipio(rs.getString(7));
                	emisor.setEstado(rs.getString(8));
                	emisor.setCodigoPostal(rs.getInt(9));
                	
                	System.out.println("Municipio " + emisor.getMunicipio());
                	
            	}
				
				
				sql = "select logo, extension, formato from pv_empresas where id=1";
    			rs = st.executeQuery(sql);
    		          
    			Image imagen = null;
    		          
    			while (rs.next()){
    		        	   		
    				Blob blob = rs.getBlob(1);
    				
    				formato = rs.getInt(3);
    						
    				if (blob!=null) {
    						 
    						 byte[] data = blob.getBytes(1, (int)blob.length());
    						 BufferedImage img = null;
    						 try {
    						 img = ImageIO.read(new ByteArrayInputStream(data));
    						 } catch (IOException ex) {
    						
    						 }
    						 


    						File outputfile = new File("image" + nombre_bd + ".jpg");
    						ImageIO.write(img, "jpg", outputfile);


    						 
    						imagen = com.itextpdf.text.Image.getInstance(outputfile.getName());
            	
    				}
    			}
				
				
				
    			
        		System.out.println(xmlRecords);
        		System.out.println(formato);
        		
        		switch (formato){
    			case 1:
    				
    				
    				comprobante = GenerarComprobante1.GenerarComprobante1
					(xmlRecords, pdfFileName, xmlFileName, String.valueOf(idComprobante), conexion, emisor, receptor, imagen);

    				break;
    				
    			case 2:
    				comprobante = GenerarComprobante1.GenerarComprobante1
					(xmlRecords, pdfFileName, xmlFileName, String.valueOf(idComprobante), conexion, emisor, receptor, imagen);

    				break;
    				
    				
    			case 3:
    				comprobante = GenerarComprobante3.GenerarComprobante1
					(xmlRecords, pdfFileName, xmlFileName, String.valueOf(idComprobante), conexion, emisor, receptor, imagen);

    				break;
    		
        		}
        					
        		
        		String resultado = "";
        		
        		/*
        		pdf = "factura_" + comprobante.getEmisor().getRfc() + "_" + comprobante.getSerie() + "_" + comprobante.getFolio() + ".pdf";
        		xml = "factura_" + comprobante.getEmisor().getRfc() + "_" + comprobante.getSerie() + "_" + comprobante.getFolio() + ".xml";
        		*/
        		
        		if (destino.length()>0){
        			
        			resultado = CorreoFormato.enviarComprobante(xmlFileName, pdfFileName, destino
        					, comprobante.getEmisor().getRfc(), comprobante.getFolio()
        					, comprobante.getSerie(), comprobante.getEmisor().getNombre());
            		
            		if (resultado.equals("El comprobante se envió correctamente")) {
            			
            			conexion = MariaDB.getConexion();
            			
            			sql = "update facturas set status=? where id=" + idComprobante;
            			dml_stmt = conexion.prepareStatement(sql);
            			dml_stmt.setInt(1, 2);
            			
            			dml_stmt.executeUpdate();
            			
            			
            			response.getWriter().append("Correo enviado correctamente.");
            			
            			
            		} else {
            			response.getWriter().append("Error al enviar el correo.");
            			
            		}
        			
        			
        			
        			
        		} else {
        			
        			response.getWriter().append("Error al buscar el correo destino " + destino + ".");
        			
        		}
        		
        		
        		
        		
        	} else {
        		response.getWriter().append("Credenciales inválidas.");
        	}
			
			
		} catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            response.getWriter().append(ex.getMessage());
           
        } catch (BadElementException e) {
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
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
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
