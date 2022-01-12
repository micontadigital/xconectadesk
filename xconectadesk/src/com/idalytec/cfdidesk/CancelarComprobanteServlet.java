package com.idalytec.cfdidesk;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.datacontract.schemas._2004._07.Sat_Cfdi_Negocio_ConsultaCfdi_Servicio.Acuse;
import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.finkok.facturacion.cancel.Application;
import com.finkok.facturacion.cancel.CancelSOAP;
import com.finkok.facturacion.cancel.CancelSOAPLocator;

import MApeados.SifeiException;
import cancelacion.sifei.service.Cancelacion_PortType;
import cancelacion.sifei.service.Cancelacion_ServiceLocator;
import mx.gob.sat.cfd._3.CFDv33;
import mx.gob.sat.cfd._3.Comprobante;
import views.core.soap.services.apps.CancelaCFDResult;
import views.core.soap.services.apps.Folio;
import views.core.soap.services.apps.UUIDS;


/**
 * Servlet implementation class CancelarComprobanteServlet
 */
@WebServlet("/CancelarComprobanteServlet")
public class CancelarComprobanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarComprobanteServlet() {
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
		String mensaje = "No fue posible cancelar la factura.";
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
		
		FileItem correoItem = (FileItem) items.get(3);
		String correo = correoItem.getString();
		
		FileItem passItem = (FileItem) items.get(4);
		String pass = passItem.getString().trim();
		
		
		correo = URLDecoder.decode(correo, "UTF-8");
	    pass = URLDecoder.decode(pass, "UTF-8");
	    
		FileItem correoReceptorItem = (FileItem) items.get(5);
		String correoReceptor = correoReceptorItem.getString();
		correoReceptor = URLDecoder.decode(correoReceptor, "UTF-8");
		
		FileItem passpfxItem = (FileItem) items.get(8);
		String passpfx = passpfxItem.getString();
		passpfx = URLDecoder.decode(passpfx, "UTF-8");
		
		
		System.out.println("Servlet Upload " + correoReceptor);
		
		try {
			
			st = conexion.createStatement();
			
			sql = "select id, ph from usuarios where correo='" + correo + "' and password='" + pass + "'" ;
        	
			System.out.println(sql);
			
        	rs = st.executeQuery(sql);
        	String ph = "";
        	
        	while (rs.next()) {
        		idUsuario = rs.getString(1);
        		ph = rs.getString(2);
        	}
        	
        	
        	if (idUsuario.length()>0){
			/*
        		CancelSOAPLocator cancelSOAP = new CancelSOAPLocator();
    			Application application = cancelSOAP.getApplication();
*/
    			
        		UUIDS uuids = new UUIDS(new String[] {uuid});
        		
        		
        		String idComprobante = "";
        		String serie = "";
        		String folio = "";
        		String rfc = "";
        		String razon = "";
        		
        		
        		
        		
        		sql = "select f.id, f.serie, f.folio, u.rfc, u.razon_social, f.xml from facturas f, usuarios u "
        				+ " where f.usuario=u.id and uuid = '" + uuid + "'";
        		
        		String xml = "";
        		rs = st.executeQuery(sql);
        		while (rs.next()){
        			idComprobante = rs.getString(1);
        			serie = rs.getString(2);
        			folio = rs.getString(3);
        			rfc = rs.getString(4);
        			razon = rs.getString(5); 
        			xml = rs.getString(6);
        			
        			System.out.println(rfc);
        		}
        		
        		//rfc="AAA010101AAA";
        		
        		//Openssl.datos(rfc, "", conexion);
        		
        		
        		String pathCer = IP.getDir() + "pfx" + rfc + ".pfx";
        		String pathKey = IP.getDir() + "key" + rfc + ".pem";
        		
        		
        		/*
        		String pathCer = IP.getDir() + "cerAAA010101AAA.pem";
        		String pathKey = IP.getDir() + "keyAAA010101AAA.pem";
        		*/
        		
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
        		
        		
        		
        		byte[] cer = leeArchivo(pathCer).getBytes("UTF-8");
        		byte[] key = leeArchivo(pathKey).getBytes("UTF-8");
        		
	        	
	
	        	Comprobante f = new mx.gob.sat.cfd._3.Comprobante();
				
				DocumentBuilder db1 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is1 = new InputSource();
				is1.setCharacterStream(new StringReader(xml));
				is1.setEncoding("utf-8");
				
				
				org.w3c.dom.Document doc1 = db1.parse(is1);
				
				File file = new File(IP.getDir() + "factura" + rfc + ".xml");
				//File file = new File("C:\\idalytec.com\\cfdiapp\\factura" + rs.getString(1) + ".xml");
				
				System.out.println(file.getAbsolutePath());
				
				javax.xml.transform.Source origen = new javax.xml.transform.dom.DOMSource(doc1);
		//		File file = new File(xmlFileName);
				javax.xml.transform.Result resultado = new javax.xml.transform.stream.StreamResult(file);
				javax.xml.transform.Result consola = new javax.xml.transform.stream.StreamResult(System.out);
				javax.xml.transform.Transformer transformar = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
				transformar.transform(origen, resultado);
				transformar.transform(origen, consola);
				
				
				f = CFDv33.newComprobante(new FileInputStream(file));
				
				
				
				
				//Openssl.creaPFX(rfc, ph);
					
				//String pfx = Openssl.leeArchivo(Openssl.getRutaDestino() + "pfx" + rfc + ".pfx");
				
				
				File filepfx = new File(Openssl.getRutaDestino() + "pfx" + rfc + ".pfx");
				 
				byte[] bytesArray = new byte[(int) filepfx.length()]; 

				FileInputStream fis = new FileInputStream(filepfx);
				fis.read(bytesArray); //read file into bytes[]
				fis.close();
				
				
				
				//Openssl.deleteFiles();
        		
				/*
        		sql = "select f.id, f.serie, f.folio, u.rfc, u.razon_social "
        				+ " from facturas f, usuarios u "
        				+ " where f.usuario=u.id and uuid = '" + uuid + "'";
        		
        		
        		rs = st.executeQuery(sql);
        		while (rs.next()){
        			//idComprobante = rs.getString(1);
        			serie = rs.getString(2);
        			folio = rs.getString(3);
        			//rfc = rs.getString(4);
        			razon = rs.getString(5); 
        			
        			System.out.println(rfcUsuario);
        		}
        		*/
				String[] uuidss = new String[]{uuid}; 
				
				System.out.println(rfc);
				System.out.println(uuidss[0]);
				System.out.println(passpfx);
				
				String resp = "";
				
        		try {
        			
        			
        			
					Cancelacion_ServiceLocator locator = new Cancelacion_ServiceLocator();
					Cancelacion_PortType app = locator.getCancelacionPort();
					resp = app.cancelaCFDI("GAME791105P87", "Js5&Jk7&", rfc
							, bytesArray, passpfx, uuidss);
					
					DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    			InputSource is = new InputSource();
	    			is.setCharacterStream(new StringReader(resp));
	    			
	    			System.out.println(resp);
	    			
	    			org.w3c.dom.Document doc = db.parse(is);
	    			
	    			doc.getDocumentElement().normalize();
	    			
	    			NodeList nList = doc.getElementsByTagName("Acuse");
	    			System.out.println("Nodo: " + nList.getLength());
					
	    			
	    			Node nNode = nList.item(0);

	    			  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	    			    Element eElement = (Element) nNode;

	    			    System.out.println("Fecha: " + eElement.getAttribute("Fecha"));
	    			    System.out.println("RFC: " + eElement.getAttribute("RfcEmisor"));
	    			    System.out.println("Code: " + eElement.getAttribute("CodEstatus"));
	    			    
	    			    NodeList nList1 = eElement.getChildNodes();
	    			    
	    			    System.out.println("Nodo: " + nList1.getLength());
	    			    
	    			    Node nNode1 = nList1.item(0);
	    			    
	    			    if(nNode1.getNodeType() == Node.ELEMENT_NODE) {
		    			    Element eElement1 = (Element) nNode1;
		    			    
		    			    System.out.println("Folio: " + eElement1.getElementsByTagName("UUID").item(0).getTextContent());
		    			    System.out.println("Status: " + eElement1.getElementsByTagName("EstatusUUID").item(0).getTextContent());
	    			    }
	    			   
	    			  }
	    			
	    			
					

					
					cancelada = true;
					mensaje = "La factura se canceló correctamente.";
					

        		} catch (SifeiException e) {
        			e.printStackTrace();
        			
        			System.out.println(e.getCodigo());
     	            System.out.println(e.getError());
     	            System.out.println(e.getMessage1());
        			
        			
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        			
        		} finally {
        			/*
        			String re = request.getParameter("re");
        			String rr = request.getParameter("rr");
        			String tt = request.getParameter("tt");
        			String id = request.getParameter("id");
        			*/
        			
        			DecimalFormat df = new DecimalFormat("0000000000.000000");
        			
        			String re = rfc;
        			String rr = f.getReceptor().getRfc();
        			String tt = df.format(f.getTotal());
        			String id = uuid;
        			
        			//String idUsuario = "1";
        			String parametro = "?re=" + re + "&rr=" + rr + "&tt=" + tt +"&id=" + id;
        			
        			System.out.println(parametro);
        			
        			conexion = MariaDB.getConexion();
        			
        			
        			try {
	        			org.tempuri.ConsultaCFDIServiceLocator port = new org.tempuri.ConsultaCFDIServiceLocator();
	        			org.tempuri.IConsultaCFDIService service = port.getBasicHttpBinding_IConsultaCFDIService();
	        			Acuse acuse = service.consulta(parametro);
	     	            System.out.println(acuse.getEstado());
	     	            System.out.println(acuse.getCodigoEstatus());
	     	            
	     	            mensaje = acuse.getCodigoEstatus();
	     	            
	     	            
						if (acuse.getEstado().equals("Cancelado")){
							
							sql = "UPDATE facturas set status=9 where id=" + idComprobante;
							dml_stmt = conexion.prepareStatement(sql);
							dml_stmt.executeUpdate();
											
							
											
							sql = "INSERT INTO cancelaciones (factura,xml) values (?,?)";
							dml_stmt = conexion.prepareStatement(sql);
							
							dml_stmt.setString(1, idComprobante);
							dml_stmt.setString(2, acuse.getCodigoEstatus());
											
							dml_stmt.executeUpdate();
							
							EnviarCorreo.enviarCancelacion(
									correoReceptor, serie + folio, uuid, rfc, razon);

							
							
							cancelada = true;			
							//mensaje = "La factura se canceló correctamente.";
							
							
						}
						
	     	         
	     	            
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
	     	            
        		}
        		
        		
        		
        		
        		
        		
        		/*
        		
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
        									
        									com.idalytec.sadpyme.facturacion.Correo.enviarCancelacion(
        											correoReceptor, serie + folio, uuid, rfc, razon);

        									
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
        		*/
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
		
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
