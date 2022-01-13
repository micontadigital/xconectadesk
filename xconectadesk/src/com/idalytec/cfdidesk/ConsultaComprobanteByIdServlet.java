package com.idalytec.cfdidesk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.InputSource;

//import mx.bigdata.sat.cfdi.CFDv32;
import mx.gob.sat.cfd._3.CFDv33;
import mx.gob.sat.cfd._3.Comprobante;

/**
 * Servlet implementation class ConsultaComprobanteByIdServlet
 */
@WebServlet("/ConsultaComprobanteByIdServlet")
public class ConsultaComprobanteByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaComprobanteByIdServlet() {
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
		
		
		String idComprobante = request.getParameter("idComprobante");
		
		
		String idUsuario = "";
		
		Connection conexion = MariaDB.getConexion();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement dml_stmt = null;
        String sql = "";

        SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd'T'hh:mm:ss");
        
        JSONArray jsonArray = new JSONArray();
		
        try {
        	st = conexion.createStatement();
        	
        	sql = "select id from usuarios where correo='" + correo + "' and password='" + pass + "'" ;
        	
        	rs = st.executeQuery(sql);
        	
        	while (rs.next()) {
        		idUsuario = rs.getString(1);
        	}
        	
        	
        	if (idUsuario.length()>0){
        		
        		String filtroStatus = "";
        		
        		sql = "select f.id,f.serie,f.folio,f.xml,'',f.status,f.uuid,f.registro "
        				+ " ,(select ifnull(sum(monto),0) from pagos_facturas where factura_relacionada=f.id) saldo"
        				+ " ,(select ifnull(max(numero_pago),0) + 1 from pagos_facturas where factura_relacionada=f.id) parcialidad"
        				+ " from facturas f, metodos_pago mp "
        				+ " where f.metodo_pago=mp.id and ifnull(f.receptor,0)=0 and f.usuario=" + idUsuario 
        				+ " and f.id=" + idComprobante
        				+ " order by f.id desc";
        		
        		System.out.println(sql);
        		
        		rs = st.executeQuery(sql);
        		
        		
        		
        		while (rs.next()) {
        			
        			if (rs.getString(4).length()>100){
        				
        				Comprobante f = new mx.gob.sat.cfd._3.Comprobante();
        				
        				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        				InputSource is = new InputSource();
        				is.setCharacterStream(new StringReader(rs.getString(4)));
        				is.setEncoding("utf-8");
        				
        				
        				org.w3c.dom.Document doc = db.parse(is);
        				
        				File file = new File("/home/cfdiapp/factura" + rs.getString(1) + ".xml");
        				//File file = new File("C:\\idalytec.com\\cfdiapp\\factura" + rs.getString(1) + ".xml");
        				
        				javax.xml.transform.Source origen = new javax.xml.transform.dom.DOMSource(doc);
        		//		File file = new File(xmlFileName);
        				javax.xml.transform.Result resultado = new javax.xml.transform.stream.StreamResult(file);
        				javax.xml.transform.Result consola = new javax.xml.transform.stream.StreamResult(System.out);
        				javax.xml.transform.Transformer transformar = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        				transformar.transform(origen, resultado);
        				transformar.transform(origen, consola);
        				
        				
        				f = CFDv33.newComprobante(new FileInputStream(file));
        				
        				
        				//file.delete();
        				
        				System.out.println("\n");
        				
        				String version = f.getVersion();
        				
        				if (f.getReceptor().getRfc()==null) {
        					version = "3.2";
        				}
        				
        				System.out.println("Nombre Receptor: " + f.getReceptor().getRfc());
        				
        				boolean agrega = true;
        				
        				
        				if (agrega&&version.equals("3.3")){
        					
        					JSONObject jsonObject = new JSONObject();
        					jsonObject.put("id_factura", rs.getInt(1));
        					jsonObject.put("serie", rs.getString(2));
        					jsonObject.put("folio", rs.getString(3));
        					
        					jsonObject.put("folio_comprobante", f.getFolio());
        					jsonObject.put("serie_comprobante", f.getSerie());
        					
        					jsonObject.put("total" , (f.getTotal()!=null) ?  f.getTotal().setScale(2, BigDecimal.ROUND_HALF_UP) : 0);
        					jsonObject.put("rfc_receptor", f.getReceptor().getRfc());
        					jsonObject.put("nombre_receptor", f.getReceptor().getNombre());
        					
        					jsonObject.put("fecha", f.getFecha());
        					jsonObject.put("correo_receptor", rs.getString(5));
        					jsonObject.put("status", rs.getString(6));
        					jsonObject.put("parcialidad", rs.getInt(10));
        					
        					jsonObject.put("tipo_comprobante",  f.getTipoDeComprobante().value() );
        					
        					jsonObject.put("metodo_pago",  (f.getMetodoPago()!=null)?f.getMetodoPago().value():"" );
        					
        					
        					BigDecimal tot = (f.getTotal()!=null) ?  f.getTotal().setScale(2, BigDecimal.ROUND_HALF_UP) : new BigDecimal(0);
        					
        					jsonObject.put("saldo", tot.doubleValue() - rs.getDouble(9));
        					
        					
        					
        					List<mx.gob.sat.cfd._3.Comprobante.Complemento> complemento33 = f.getComplemento();
        					
        					if (!complemento33.isEmpty()) {
        						
        						jsonObject.put("uuid", "");

								mx.gob.sat.cfd._3.Comprobante.Complemento comp =  complemento33.get(0);
	
								mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital t33 = null;
	
								List<Object> complementos =  comp.getAny();
								
								System.out.println(complementos.size() );
								
								for (int i = 0 ; i < complementos.size() ; i++) {
									
									if (complementos.get(i)!=null){
										
										 if (complementos.get(i) instanceof mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital) {
											
											 t33 = (mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital) complementos.get(i);
										
											 System.out.println("complemento numero " + i);
												
											 System.out.println(t33.getUUID());
											 System.out.println(t33.getNoCertificadoSAT());
											 jsonObject.put("uuid", t33.getUUID());
										 }
																		
									}
								
								}
								
								
        					
        					} else {
        						jsonObject.put("uuid", "");
        					}
        					
        					jsonArray.add(jsonObject);
        					
        					        				
        				} else {
        					/*
        					mx.bigdata.sat.cfdi.v32.schema.Comprobante	f22 = CFDv32.newComprobante(new FileInputStream(file));
        					
        					
        					System.out.println("22" + f22.getFolio());
        					
        					
        					JSONObject jsonObject = new JSONObject();
        					jsonObject.put("id_factura", rs.getInt(1));
        					jsonObject.put("serie", rs.getString(2));
        					jsonObject.put("folio", rs.getString(3));
        					
        					jsonObject.put("folio_comprobante", f22.getFolio());
        					jsonObject.put("serie_comprobante", f22.getSerie());
        					
        					jsonObject.put("total", f22.getTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
        					jsonObject.put("rfc_receptor", f22.getReceptor().getRfc());
        					jsonObject.put("nombre_receptor", f22.getReceptor().getNombre());
        					
        					jsonObject.put("fecha", sdf.format(f22.getFecha()));
        					jsonObject.put("correo_receptor", rs.getString(5));
        					jsonObject.put("status", rs.getString(6));
        					jsonObject.put("parcialidad", rs.getInt(10));
        					
        					mx.bigdata.sat.cfdi.v32.schema.Comprobante.Complemento t = f22.getComplemento();

							
							List<Object> l = t.getAny();

							if (!l.isEmpty()) {
								mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital timbre = (mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital) l.get(0) ;
								System.out.println("CertificadoSAT" + timbre.getNoCertificadoSAT());
        						jsonObject.put("uuid", timbre.getUUID());
        					} else {
        						jsonObject.put("uuid", "");
        					}
        					
        					jsonArray.add(jsonObject);
        					
        					*/
        				}
        				
        				file.delete();
        				
        			} else {
        				JSONObject jsonObject = new JSONObject();
    					jsonObject.put("id_factura", rs.getInt(1));
    					jsonObject.put("serie", rs.getString(2));
    					jsonObject.put("folio", rs.getString(3));
    					
    					jsonObject.put("folio_comprobante", "");
    					jsonObject.put("serie_comprobante", "");
    					
    					jsonObject.put("total", 0);
    					jsonObject.put("rfc_receptor", "");
    					jsonObject.put("nombre_receptor", "");
    					
    					jsonObject.put("fecha", sdf.format(rs.getDate(8)));
    					jsonObject.put("correo_receptor", rs.getString(5));
    					jsonObject.put("status", rs.getString(6));
    				
    					
    					jsonObject.put("uuid", rs.getString(7));
    					
    					jsonArray.add(jsonObject);
        				
        				
        			}
        			
        			
        			
        		}
        	
        		
        		
        	} else {
        		response.setContentType("application/json");
        		JSONObject o = new JSONObject();
        		o.put("mensaje", "No se encuentran comprobantes.");
        		jsonArray.add(o);
        		
        		
        	}	
        		response.setContentType("application/json");
        		
        		PrintWriter out = response.getWriter();
        		out.print(jsonArray);
        		out.flush();
    		             	
        		
        } catch (javax.xml.transform.TransformerException e){
            e.printStackTrace();
            response.getWriter().append("javax.xml.transform.TransformerException " + e.getMessage());
            
        } catch (org.xml.sax.SAXException e){
            e.printStackTrace();
            response.getWriter().append("org.xml.sax.SAXException " + e.getMessage());

        } catch (ParserConfigurationException e){
            e.printStackTrace();
            response.getWriter().append("ParserConfigurationException " + e.getMessage());

        } catch (SQLException e) {
             // TODO Auto-generated catch block
        	e.printStackTrace();
        	response.getWriter().append("SQLException " + e.getMessage());
        	
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	e.printStackTrace();
        	StringWriter errors = new StringWriter();
        	e.printStackTrace(new PrintWriter(errors));
			response.getWriter().append("Exception " + e.getMessage() + " " + errors.toString());
        	
             
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
