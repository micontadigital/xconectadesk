package com.idalytec.cfdidesk;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBElement;
import javax.xml.rpc.ServiceException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.finkok.facturacion.cancel.Application;
import com.finkok.facturacion.cancel.CancelSOAP;
import com.finkok.facturacion.cancel.CancelSOAPLocator;

import views.core.soap.services.apps.CancelaCFDResult;
import views.core.soap.services.apps.Folio;
import views.core.soap.services.apps.UUIDS;


/**
 * Servlet implementation class CancelarComprobanteServlet
 */
@WebServlet("/CancelarComprobanteServlet2")
public class CancelarComprobanteServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarComprobanteServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		JSONObject jsonObject = new JSONObject();
		response.setContentType("application/json");
		
		
		
		Connection conexion = MariaDB.getConexion();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement dml_stmt = null;
        String sql = "";
		String mensaje = "Test";
		String idUsuario = "";
		boolean cancelada = false;
		

		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			cancelada = false;
			mensaje = "Error al enviar el archivo.";	
		}
		
		List items=null;
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		FileItem uuidItem = (FileItem) items.get(0);
		String uuid = uuidItem.getString();
		
		FileItem cerItem = (FileItem) items.get(1);
		FileItem keyItem = (FileItem) items.get(2);
		
		
		String pathCer = "/home/cfdiapp/" + uuid.substring(0, 5) + "cer.pem";
		String pathKey = "/home/cfdiapp/" + uuid.substring(0, 5) + "key.enc";
		
		
		
		File fileCer = new File(pathCer);
		try {
			cerItem.write(fileCer);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File fileKey = new File(pathKey);
		try {
			keyItem.write(fileKey);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		FileItem correoItem = (FileItem) items.get(3);
		String correo = correoItem.getString();
		
		FileItem passItem = (FileItem) items.get(4);
		String pass = passItem.getString().trim();
		
		correo = URLDecoder.decode(correo, "UTF-8");
	    pass = URLDecoder.decode(pass, "UTF-8");
	    
		FileItem correoReceptorItem = (FileItem) items.get(5);
		String correoReceptor = correoReceptorItem.getString();
		
		correoReceptor = URLDecoder.decode(correoReceptor, "UTF-8");
		
		
		System.out.println("Servlet Upload " + correoReceptor);
		
		try {
			
			st = conexion.createStatement();
			
			sql = "select id from usuarios where correo='" + correo + "' and password='" + pass + "'" ;
        	
			System.out.println(sql);
			
        	rs = st.executeQuery(sql);
        	
        	while (rs.next()) {
        		idUsuario = rs.getString(1);
        	}
        	
        	
        	if (idUsuario.length()>0){
			
        		CancelSOAPLocator cancelSOAP = new CancelSOAPLocator();
    			Application application = cancelSOAP.getApplication();

    			
        		UUIDS uuids = new UUIDS(new String[] {uuid});
        		
        		String idComprobante = "";
        		String serie = "";
        		String folio = "";
        		String rfc = "";
        		String razon = "";
        		
        		
        		byte[] cer = leeArchivo(pathCer).getBytes("UTF-8");
        		byte[] key = leeArchivo(pathKey).getBytes("UTF-8");
        		
        		sql = "select f.id, f.serie, f.folio, u.rfc, u.razon_social from facturas f, usuarios u "
        				+ " where f.usuario=u.id and uuid = '" + uuid + "'";
        		
        		
        		rs = st.executeQuery(sql);
        		while (rs.next()){
        			idComprobante = rs.getString(1);
        			serie = rs.getString(2);
        			folio = rs.getString(3);
        			rfc = rs.getString(4);
        			razon = rs.getString(5); 
        			
        			System.out.println(rfc);
        		}
        		
        		CancelaCFDResult acuse = null;
        		acuse = application.cancel(uuids, "idalytec@gmail.com", "Acceso2014.", rfc, cer, key, true);
        		
        		
        		if (acuse.getAcuse() == null) {
        			if (acuse.getCodEstatus()!=null){
        				mensaje = acuse.getCodEstatus();
        				System.out.println(mensaje);
        			} else {
        				mensaje =  "Código de estatus desconocido.";
        			}
        		}else{
        			
        			if (acuse.getCodEstatus() != null) {
        				mensaje = acuse.getCodEstatus();
        				System.out.println("CodEstatus " + acuse.getCodEstatus());
					
        				if (Integer.parseInt(acuse.getCodEstatus()) == 303) {
        					mensaje += " - Sello no corresponde a emisor.";
        				}
						
        			} else {
        				if (acuse.getFolios() != null) {
        					Folio[] folioArray = acuse.getFolios();

    						for (Folio f : folioArray) {
        						
        						if (f.getUUID() != null) {
        							System.out.println("UUID: "	+ f.getUUID());
        							
        							if (f.getEstatusUUID() != null) {
        								
        								if (Integer.parseInt(f.getEstatusUUID()) == 201) {
        									
        									sql = "UPDATE facturas set status=9 where id=" + idComprobante;
        									dml_stmt = conexion.prepareStatement(sql);
        									dml_stmt.executeUpdate();
															
        									
															
        									sql = "INSERT INTO cancelaciones (factura,xml) values (?,?)";
        									dml_stmt = conexion.prepareStatement(sql);
        									
        									dml_stmt.setString(1, idComprobante);
        									dml_stmt.setString(2, acuse.getAcuse());
															
        									dml_stmt.executeUpdate();
        									
        									
        									/*
        									com.idalytec.sadpyme.facturacion.CorreoFormato.enviarCancelacion(
        											correoReceptor, serie + folio, uuid, rfc, razon);

        									*/
        									
        									cancelada = true;			
        									mensaje = "La factura se canceló correctamente.";
															
        									
														
        								} else if (Integer.parseInt(f.getEstatusUUID()) == 708) {
														
        									
        									
        									mensaje = "No se pudo conectar al SAT";
        									
        									
        								} else if (Integer.parseInt(f.getEstatusUUID()) == 205) {
        									
        									mensaje = "UUID No existe.";
        									
        									
        								} else if (Integer.parseInt(f.getEstatusUUID()) == 303) {
        									
        									mensaje = "Sello no corresponde a emisor.";
        									
        									
        								} else if (Integer.parseInt(f.getEstatusUUID()) == 202) {
												
        									sql = "UPDATE facturas set status=9 where id=" + idComprobante;
        									dml_stmt = conexion.prepareStatement(sql);
        									dml_stmt.executeUpdate();
        									
        									
        									sql = "INSERT INTO cancelaciones (factura,xml) values (?,?)";
        									dml_stmt = conexion.prepareStatement(sql);
        									
        									dml_stmt.setString(1, idComprobante);
        									dml_stmt.setString(2, acuse.getAcuse());
															
        									dml_stmt.executeUpdate();
        									
        									/*
        									com.idalytec.sadpyme.facturacion.CorreoFormato.enviarCancelacion(
        											correoReceptor, serie + folio, uuid, rfc, razon);

        									*/
        									cancelada = true;
        									mensaje = "La factura se canceló correctamente.";
														
        								} else if (Integer.parseInt(f.getEstatusUUID()) == 203) {
    										mensaje = f.getEstatusUUID();

    									} else if (Integer.parseInt(f.getEstatusUUID()) == 300) {
    										mensaje = f.getEstatusUUID();

    									} else if (Integer.parseInt(f.getEstatusUUID()) == 301) {
    										mensaje = f.getEstatusUUID();

    									} else {
    										mensaje = f.getEstatusUUID();
        									
        								}
        								
        							}
        						}
        					}
        					
        				}
        			}
        			
        		}
        		
        		fileCer.delete();
        		fileKey.delete();
        		
        		jsonObject.put("cancelada", cancelada);
        		jsonObject.put("resultado", mensaje);
        		
        		PrintWriter out = response.getWriter();
        		out.print(jsonObject);
        		out.flush();
        		
        	}else{
        		jsonObject.put("cancelada", false);
        		jsonObject.put("resultado", "Credenciales inválidas.");
        		
        		PrintWriter out = response.getWriter();
        		out.print(jsonObject);
        		out.flush();
        		
        		
        	}
		} catch (IOException e){
			e.printStackTrace();
			
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jsonObject.put("cancelada", cancelada);
		jsonObject.put("resultado", mensaje);
		
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
	}
	
	
	public String leeArchivo(String ruta) {
        try {
        	FileInputStream fstream = new FileInputStream(ruta);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            StringBuffer sB = new StringBuffer();
            while ((strLinea = buffer.readLine()) != null) {
                sB.append(strLinea).append("\n");
            }
            entrada.close();
            return sB.toString();
        } catch (FileNotFoundException e){
        	JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
