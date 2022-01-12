package com.idalytec.cfdidesk;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.xml.sax.InputSource;

import com.idalytec.sadpyme.formatos.GenerarComprobante1;
import com.idalytec.sadpyme.formatos.GenerarComprobante2;
import com.idalytec.sadpyme.formatos.GenerarComprobante3;
import com.itextpdf.text.Image;

import mx.bigdata.sat.cfdi.CFDv32;
import mx.gob.sat.cfd._3.CFDv33;
import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.cfd._3.Comprobante.Complemento;
import mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital;



/**
 * Servlet implementation class ComprobantePdfServlet
 */
@WebServlet("/ComprobantePdfServlet")
public class ComprobantePdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobantePdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error="";
		String mensaje="";
		request.setCharacterEncoding("UTF-8");
		
		String pass = request.getParameter("pass");
		String correo = request.getParameter("correo");
		String idComprobante = request.getParameter("idComprobante");
		
		
		String calle = URLDecoder.decode(request.getParameter("calle"), "UTF-8");
		String numeroExterior = URLDecoder.decode(request.getParameter("numeroExterior"), "UTF-8");;
		String numeroInterior = URLDecoder.decode(request.getParameter("numeroInterior"), "UTF-8");
		String colonia = URLDecoder.decode(request.getParameter("colonia"), "UTF-8");
		String municipio = URLDecoder.decode(request.getParameter("municipio"), "UTF-8");
		String estado = URLDecoder.decode(request.getParameter("estado"), "UTF-8");
		String codigoPostal = URLDecoder.decode(request.getParameter("codigoPostal"), "UTF-8");
		
		
		
		System.out.println(pass);
		System.out.println(correo);
		
		
		String idUsuario = "";
		int formato = 1;
		
		String pdfFileName = "";
		String xmlFileName = "";
		
		File pdfFile = null;
		
		Connection conexion = MariaDB.getConexion();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement dml_stmt = null;
        String sql = "";
       
        try {
        	st = conexion.createStatement();
        
        	sql = "select id, formato from usuarios where correo='" + correo + "' and password='" + pass + "'" ;
        	
        	rs = st.executeQuery(sql);
        	
        	while (rs.next()) {
        		idUsuario = rs.getString(1);
        		formato = rs.getInt(2);
        	}
        	
        	
        	if (idUsuario.length()>0){
        	
        
        		JSONArray jsonArray = new JSONArray();
        		
        		pdfFileName = IP.getDir() + "factura" + idComprobante + ".pdf";
        		xmlFileName = IP.getDir() + "factura" + idComprobante + ".xml";
        		
        		/*
        		pdfFileName = "C:\\idalytec.com\\cfdiapp\\factura" + idComprobante + ".pdf";
        		xmlFileName = "C:\\idalytec.com\\cfdiapp\\factura" + idComprobante + ".xml";
        		*/
        		
        		pdfFile = new File(pdfFileName);
        		
        		String xmlRecords = "";
        		String observaciones = "";
        		
        		sql = "select xml, observaciones from facturas where id=" + idComprobante + " and usuario=" + idUsuario;
        		
        		rs = st.executeQuery(sql);
        		
        		while (rs.next()) {
        			xmlRecords = rs.getString(1);
        			observaciones = rs.getString(2);
        		}
        		
        		
        		com.idalytec.test.Emisor emisor = new com.idalytec.test.Emisor();
    			com.idalytec.test.Receptor receptor = new com.idalytec.test.Receptor();
        		
        		
            	
            	receptor.setCalle(calle);
            	receptor.setNumeroExterior(numeroExterior);
            	receptor.setNumeroInterior(numeroInterior);
            	receptor.setColonia(colonia);
            	receptor.setMunicipio(municipio);
            	receptor.setEstado(estado);
            	receptor.setCodigoPostal(Integer.parseInt(codigoPostal));
            	
        		
            	DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(xmlRecords));
				is.setEncoding("utf-8");
				
				
				org.w3c.dom.Document doc = db.parse(is);
				
				File file = new File(IP.getDir() + "factura" + idComprobante + ".xml");
				//File file = new File("C:\\idalytec.com\\cfdiapp\\factura" + rs.getString(1) + ".xml");
				
				javax.xml.transform.Source origen = new javax.xml.transform.dom.DOMSource(doc);
		//		File file = new File(xmlFileName);
				javax.xml.transform.Result resultado = new javax.xml.transform.stream.StreamResult(file);
				javax.xml.transform.Result consola = new javax.xml.transform.stream.StreamResult(System.out);
				javax.xml.transform.Transformer transformar = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
				transformar.transform(origen, resultado);
				transformar.transform(origen, consola);
				
				
				mx.bigdata.sat.cfdi.v32.schema.Comprobante f = CFDv32.newComprobante(new FileInputStream(file));
            	
				System.out.println("Emisor " + f.getEmisor().getRfc());
				
				
				
				
				
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
				
				
				
				
				
				
            	if (f.getEmisor().getRfc()!=null) {
            		mx.bigdata.sat.cfdi.v32.schema.Comprobante comprobante;
            		
            		System.out.println("Combrobante2 ");
            		
            		switch (formato){
        			case 1:
        				comprobante = GenerarComprobante2.GenerarComprobante1
    					(xmlRecords, pdfFileName, xmlFileName, String.valueOf(idComprobante), conexion, emisor, receptor);
        				break;
        				
        			case 2:
        				comprobante = GenerarComprobante2.GenerarComprobante1
    					(xmlRecords, pdfFileName, xmlFileName, String.valueOf(idComprobante), conexion, emisor, receptor);
        				break;
        		
            		}
            		
            	} else {
            		
            		System.out.println("Combrobante1 ");
            		
            		
            		
            		
            		Comprobante comprobante;
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
            	}
        		
        		
        		
        		file.delete();
        		
        		
        	}
        		
		} catch (SQLException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
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
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=" + pdfFileName);
		response.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream = new FileInputStream(pdfFile);
		OutputStream responseOutputStream = response.getOutputStream();
		int bytes;
		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
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
