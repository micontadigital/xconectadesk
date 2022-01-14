package com.idalytec.cfdidesk;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

import com.idalytec.sadpyme.facturacion.CorreoFormato;
import com.idalytec.sadpyme.formatos.GenerarComprobante1;
import com.idalytec.sadpyme.formatos.GenerarComprobante3;
//import com.idalytec.test.Emisor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;

import mx.gob.sat.cfd._3.CFDv33;
import mx.gob.sat.cfd._3.Comprobante;
//import mx.gob.sat.cfd._3.Comprobante.Receptor;
import mx.gob.sat.cfd._3.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado;
import mx.gob.sat.cfd._3.Comprobante.Impuestos.Retenciones.Retencion;
import mx.gob.sat.implocal.ImpuestosLocales;
import mx.gob.sat.pagos.Pagos;
import mx.gob.sat.pagos.Pagos.Pago;
import mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

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
		
		
		String imageQR = null;
		
		 Map<String, Object> parametros = new HashMap<String, Object>();
	        
	    Connection conexionUsr = null;
		
		System.out.println(pass);
		System.out.println(correo);
		Usuario u = null;
		
		
		
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
		File file = null;

		/*
		File folder = new File("c:\\idalytec.com\\facturacionWEB");
		folder.mkdirs();
		*/
		
		
		try {
			st = conexion.createStatement();
			
			
			System.out.println(destino);
			
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
			
			
			
			while (rs.next()){
				u = new Usuario();
				
				u.setId(rs.getInt(1));
				u.setNombreBD(rs.getString(2));
				u.setUsrBD(rs.getString(3));
				u.setPassBD(rs.getString(4));
				//u.setNombre(name);
				//u.setNick(usuario);
    		
			}
    		
        	conexionUsr = MariaDBSadpyme.GetConnection("app.xconecta.com", u.getUsrBD(), u.getPassBD(), u.getNombreBD());
        	Statement stUsr = conexionUsr.createStatement();
        	
        	
        	
        	if (idUsuario.length()>0){
        		
    			if (destino.length()>0){
    				
    				destino = URLDecoder.decode(destino, "UTF-8");
    			
    			} else {
    				
    				sql = "select correo from pv_clientes c, pv_facturas_finkok f where f.cliente=c.id and f.id=" + idComprobante ;
    	        	
    	        	rs = stUsr.executeQuery(sql);
    	        	
    	        	while (rs.next()) {
    	        		destino = rs.getString(1);
    	        	}
    				
    				
    			}
        		
        		sql = "select f.xml, '' observaciones from pv_facturas_finkok f where f.id = " + idComprobante;
        		
        		rs = stUsr.executeQuery(sql);
        		
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
            	
            	
            	
            	DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(xmlRecords));
				is.setEncoding("utf-8");
				
				
				org.w3c.dom.Document doc = db.parse(is);
				
				file = new File(IP.getDir() + "factura" + u.getNombreBD() + "_" + idComprobante + ".xml");
				//File file = new File("C:\\idalytec.com\\cfdiapp\\factura" + rs.getString(1) + ".xml");
				
				javax.xml.transform.Source origen = new javax.xml.transform.dom.DOMSource(doc);
		//		File file = new File(xmlFileName);
				javax.xml.transform.Result resultado = new javax.xml.transform.stream.StreamResult(file);
				javax.xml.transform.Result consola = new javax.xml.transform.stream.StreamResult(System.out);
				javax.xml.transform.Transformer transformar = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
				transformar.transform(origen, resultado);
				transformar.transform(origen, consola);
				
				
            	
				
				sql = "select rfc, razon_social, calle, numero_exterior, numero_interior, colonia, municipio, estado, codigo_postal"
        				+ ", regimen "
            			+ " from pv_empresas where id=1";
            
        		System.out.println(sql);
            	rs = stUsr.executeQuery(sql);
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
            	System.out.println(sql);
        		rs = stUsr.executeQuery(sql);
        	          
        		
        		File outputfile = null;  
        		while (rs.next()){
        	        	   		
        			Blob blob = rs.getBlob(1);
        			formato = rs.getInt(3);
        			
        			
        			
        			String contenType = rs.getString(2);
                      
                    	InputStream input = new ByteArrayInputStream(rs.getBytes(1));
                    	BufferedInputStream buf = new BufferedInputStream(input);
        			
        					
        			if (blob!=null) {
        			//if (false) { 
        					 byte[] data = blob.getBytes(1, (int)blob.length());
        					 BufferedImage img = null;
        					 
        					 try {
        					 //img = ImageIO.read(buf);
        					 img =  javax.imageio.ImageIO.read(blob.getBinaryStream());
        					 
        					 } catch (IOException ex) {
        						 ex.printStackTrace();
        					 }
        					 
        					

        					 
        					 if (rs.getString(2).equals("image/png")){
        						 outputfile = new File(IP.getDir() + "image" + u.getNombreBD() + ".png");
        						 
        						 System.out.println(outputfile.getAbsolutePath());
        						 
        						 ImageIO.write(img, "png", outputfile);	
        						 
        					 } else {
        					
        						 outputfile = new File(IP.getDir() + "image" + u.getNombreBD() + ".jpg");
        						 
        						 System.out.println(outputfile.getAbsolutePath());
        						 
        						 ImageIO.write(img, "jpg", outputfile);

        					 }

            	
        			} else {
        				  
        				input = getServletContext().getResourceAsStream("images/logo_xconecta_gray_v.png");
        			 	BufferedImage img = ImageIO.read(input);
        				outputfile = new File(IP.getDir() + "image" + u.getNombreBD() + ".png");
        					ImageIO.write(img, "png", outputfile);
        				
        			}
        			
        		}
        		
        		int idFactura = guardaFactura(file,conexionUsr,conexionUsr, u ,new Receptor(), new Emisor());
        		imageQR = IP.getDir() + "qr" + idFactura + u.getNombreBD() + ".jpg";
        		
        		System.out.println(idFactura);
        		System.out.println(outputfile.getPath());
        		System.out.println(imageQR);
        		
        		
        		
        		 parametros.put("idFactura", idFactura);
        		    parametros.put("logo", outputfile.getPath());
        		    parametros.put("qr", imageQR);
        		    
        		    System.out.println("idFactura " + idFactura);
        		    System.out.println("logo " + outputfile.getPath());
        		    System.out.println("qr " + imageQR);
        		
        	
        		
        		/*
        		pdf = "factura_" + comprobante.getEmisor().getRfc() + "_" + comprobante.getSerie() + "_" + comprobante.getFolio() + ".pdf";
        		xml = "factura_" + comprobante.getEmisor().getRfc() + "_" + comprobante.getSerie() + "_" + comprobante.getFolio() + ".xml";
        		*/
        		
        		if (destino.length()>0){
        			//comprobante = new mx.gob.sat.cfd._3.Comprobante();
        			
        			comprobante = CFDv33.newComprobante(new FileInputStream(file));
        			
        			
        			JasperReport reporte = null;
        			try {
        				reporte = (JasperReport) JRLoader.loadObject(getServletContext().getResource("reports/FacturaPago.jasper"));
        			} catch (MalformedURLException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (JRException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        			
        		    
        		    
        		    JasperPrint jasperPrint;
        		
        		    
        			
        			File pdfFileDownload = new File(IP.getDir() + "server" +  u.getNombreBD() + "_" + idComprobante + ".pdf");
        			
        		

        			JasperPrint jasperPrint1;
        			
        			try {
        				jasperPrint1 = JasperFillManager.fillReport(reporte, parametros, conexionUsr);
        				JasperExportManager.exportReportToPdfFile(jasperPrint1, pdfFileDownload.getAbsolutePath());
        			} catch (JRException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        			
        			
        			
        			String respuesta = Correo.enviarComprobante(file.getAbsolutePath(), pdfFileDownload.getAbsolutePath(), destino
        					, comprobante.getEmisor().getRfc(), comprobante.getFolio()
        					, comprobante.getSerie(), comprobante.getEmisor().getNombre());
            		
            		if (respuesta.equals("El comprobante se envió correctamente")) {
            			
            			/*
            			conexion = MariaDB.getConexion();
            			
            			sql = "update facturas set status=? where id=" + idComprobante;
            			dml_stmt = conexion.prepareStatement(sql);
            			dml_stmt.setInt(1, 2);
            			
            			dml_stmt.executeUpdate();
            			*/
            			
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
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }

        }

		
	
	}
	
	
	
	
	public int guardaFactura(File file,Connection dataSourceTpv,Connection dataSourceCfdi, Usuario u, Receptor receptor, Emisor emisor) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement dml_stmt = null;
		String sql = "";
		
		String regresa = "";
		int idFactura = 0;
		
		try {

			mx.gob.sat.cfd._3.Comprobante f33;
			
			List<mx.gob.sat.cfd._3.Comprobante.Complemento> complemento33;
			mx.gob.sat.cfd._3.Comprobante.Emisor e33;
			mx.gob.sat.cfd._3.Comprobante.Receptor r33;

			conexion = dataSourceTpv;

			f33 = CFDv33.newComprobante(new FileInputStream(file.getAbsolutePath()));

			e33 = f33.getEmisor();

			r33 = f33.getReceptor();


			System.out.println(r33.getRfc());

			complemento33 = f33.getComplemento();

			mx.gob.sat.cfd._3.Comprobante.Complemento comp =  complemento33.get(0);

			mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital t33 = null;

			List<Object> listaComplementos =  comp.getAny();

			ImpuestosLocales impuestosLocales = null;
			Pagos pagos = null;

			if (complemento33!=null){

				for (int i = 0; i < comp.getAny().size() ; i++) {
					Object o = comp.getAny().get(i);

					if (o instanceof TimbreFiscalDigital) {
						t33 = (TimbreFiscalDigital) comp.getAny().get(i);

						System.out.println("Timbre " + t33.getNoCertificadoSAT() );
					}

					if (o instanceof ImpuestosLocales) {
						impuestosLocales = (ImpuestosLocales)	comp.getAny().get(i);

						System.out.println("Impuestos Locales");
					}

					if (o instanceof Pagos) {
						pagos = (Pagos)comp.getAny().get(i);

						System.out.println("Pagos");
					}

				}
			}

			st = conexion.createStatement();
			//Empresa empresa = Empresa.getEmpresa(u, dataSourceTpv, dataSourceCfdi);
			
			//System.out.println(empresa.getRfc());
			System.out.println(r33.getRfc());

			//if (r33.getRfc().equals(empresa.getRfc())) {

			if (true) {
				/*
				sql = "select id from pv_proveedores where rfc = '" + e33.getRfc() + "'";
				System.out.println(sql);
				
				rs = st.executeQuery(sql);

				boolean existeProveedor = false;
				int idProveedor = 0;

				while (rs.next()) {
					existeProveedor = true;
					idProveedor = rs.getInt(1);
				}

				if (!existeProveedor) {
					sql = "select ifnull(max(id),0) + 1 from pv_proveedores";
					rs = st.executeQuery(sql);

					while (rs.next()) {
						idProveedor = rs.getInt(1);
					}

					sql = "INSERT INTO pv_proveedores (razon_social,rfc,telefono_1,telefono_2,contacto,email,direccion,status,id) values "
							+ "(?,?,?,?,?,?,?,?,?)";

					dml_stmt = conexion.prepareStatement(sql);

					dml_stmt.setString(1,(e33.getNombre()!=null)?e33.getNombre():e33.getRfc());
					dml_stmt.setString(2,e33.getRfc());
					dml_stmt.setString(3,"");
					dml_stmt.setString(4,"");
					dml_stmt.setString(5,"");
					dml_stmt.setString(6,"");
					dml_stmt.setString(7,"");
					dml_stmt.setInt(8,1);
					dml_stmt.setInt(9,idProveedor);

					dml_stmt.executeUpdate();
				}

				*/


				Date fechaTimbrado = t33.getFechaTimbrado().toGregorianCalendar().getTime();


				mx.gob.sat.cfd._3.Comprobante.Conceptos conceptos = f33.getConceptos();
				ArrayList<mx.gob.sat.cfd._3.Comprobante.Conceptos.Concepto> c 
				= (ArrayList<mx.gob.sat.cfd._3.Comprobante.Conceptos.Concepto>) conceptos.getConcepto();
				double ivaRetenido = 0;
				double isrRetenido = 0;
				double ivaTrasladado = 0;
				double iepsTrasladado= 0;
				mx.gob.sat.cfd._3.Comprobante.Impuestos impuestosComprobante = f33.getImpuestos();
				if (impuestosComprobante!=null) {
					mx.gob.sat.cfd._3.Comprobante.Impuestos.Retenciones retencionesComprobante = impuestosComprobante.getRetenciones();
					
					
					if (retencionesComprobante!=null) {
						List<Retencion> listaRetenciones = retencionesComprobante.getRetencion();
						for(int i = 0 ; i < listaRetenciones.size() ; i++) {
							if (listaRetenciones.get(i).getImpuesto().equals("002")) {
								ivaRetenido += listaRetenciones.get(i).getImporte().doubleValue();
								
							} else if (listaRetenciones.get(i).getImpuesto().equals("001")){
								isrRetenido += listaRetenciones.get(i).getImporte().doubleValue();
							}
						}
					}
					
					mx.gob.sat.cfd._3.Comprobante.Impuestos.Traslados trasladosComprobante = impuestosComprobante.getTraslados();
					
					if (trasladosComprobante!=null) {
					
						List<mx.gob.sat.cfd._3.Comprobante.Impuestos.Traslados.Traslado> listaTrasladosComp = trasladosComprobante.getTraslado();
						for(int i = 0 ; i < listaTrasladosComp.size() ; i++) {
							if (listaTrasladosComp.get(i).getImpuesto().equals("002")) {
								ivaTrasladado += listaTrasladosComp.get(i).getImporte().doubleValue();
								
							} else if (listaTrasladosComp.get(i).getImpuesto().equals("003")){
								iepsTrasladado += listaTrasladosComp.get(i).getImporte().doubleValue();
							}
						}
					}
				}
			

				boolean existe = false;

				/*
				sql = "delete from pv_conceptos_facturas where factura_gasto=" + idComprobante;
				dml_stmt = conexion.prepareStatement(sql);
				dml_stmt.executeUpdate();
				
				sql = "delete from pv_impuestos_facturas where factura_gasto=" + idComprobante;
				dml_stmt = conexion.prepareStatement(sql); 
				dml_stmt.executeUpdate();

				sql = "delete from pv_facturas_gastos where id=" + idComprobante;
				dml_stmt = conexion.prepareStatement(sql); 
				dml_stmt.executeUpdate();
				
				System.out.println(sql);
				*/
				
				sql="select id from pv_facturas_gastos where uuid='" + t33.getUUID() + "'";
				System.out.println(sql);
				rs = st.executeQuery(sql);
				while (rs.next()){
					idFactura = rs.getInt(1);
					existe = true;
				}

				boolean tipo = true;
				
				if (f33.getTipoDeComprobante().toString().equals("N")) {
					tipo = false;
				}
				
				if (!existe) {
					System.out.println("No existe");
					
					
					int compra = 0;

					sql="select ifnull(max(id),0) + 1 from pv_facturas_gastos";
					rs = st.executeQuery(sql);
					while (rs.next()){
						idFactura =  rs.getInt(1);
					}
				

					sql = "INSERT INTO pv_facturas_gastos (id,uuid,subtotal,total,fecha_emision"
							+ ",fecha_certificacion,impuestos_retenidos,impuestos_trasladados"
							+ ",emisor,serie,folio,condicion_pago,num_cta_pago,metodo_pago"
							+ ",forma_pago,tipo_cambio,estado_sat,version,tipo_comprobante"
							+ ",rfc_emisor,nombre_emisor,lugar_expedicion,uso_cfdi,descuento"
							+ ",total_ieps,total_iva,retenido_iva,retenido_isr,ish,local_trasladado"
							+ ",local_retenido"
							+ ",serie_sello,serie_sello_sat,sello,sello_sat,cadena_original,nombre_metodo_pago"
							+ ",nombre_forma_pago,nombre_uso_cfdi,nombre_tipo_comprobante,qr,nombre_regimen_fiscal"
							+ ",tipo_factura,rfc_receptor, nombre_receptor, regimen_fiscal, importe_letra, direccion_receptor) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?, ?,?,?,?,?  ,?,?,?,?,?  ,?,?,?,?,?   ,?,?)";

					dml_stmt = conexion.prepareStatement(sql);

					dml_stmt.setInt(1, idFactura);
					dml_stmt.setString(2,t33.getUUID());
					dml_stmt.setDouble(3, f33.getSubTotal().doubleValue());
					dml_stmt.setDouble(4, f33.getTotal().doubleValue());
					dml_stmt.setString(5, f33.getFecha());
					dml_stmt.setString(6, t33.getFechaTimbrado().toString());
					if (f33.getImpuestos()!=null) {
						if (f33.getImpuestos().getTotalImpuestosRetenidos()!=null) {
							dml_stmt.setDouble(7, f33.getImpuestos().getTotalImpuestosRetenidos().doubleValue());
						} else {
							dml_stmt.setDouble(7,0);
						}
						if (f33.getImpuestos().getTotalImpuestosTrasladados()!=null) {
							dml_stmt.setDouble(8, f33.getImpuestos().getTotalImpuestosTrasladados().doubleValue());
						} else {
							dml_stmt.setDouble(8, 0);
						}
					} else {
						dml_stmt.setDouble(7,0);
						dml_stmt.setDouble(8, 0);
					}
					
					dml_stmt.setInt(9, 0);
					dml_stmt.setString(10, f33.getSerie());
					dml_stmt.setString(11, f33.getFolio());
					dml_stmt.setString(12, f33.getCondicionesDePago());
					dml_stmt.setInt(13, 0);
					dml_stmt.setString(14, (f33.getMetodoPago()!=null)?f33.getMetodoPago().value():"");
					dml_stmt.setString(15, (f33.getFormaPago()!=null)?f33.getFormaPago():"");
					dml_stmt.setDouble(16, (f33.getTipoCambio()!=null)?f33.getTipoCambio().doubleValue():1);
					dml_stmt.setString(17, "");
					dml_stmt.setString(18, f33.getVersion());
					dml_stmt.setString(19, f33.getTipoDeComprobante().toString());
					dml_stmt.setString(20, f33.getEmisor().getRfc());
					dml_stmt.setString(21, f33.getEmisor().getNombre());
					dml_stmt.setString(22, f33.getLugarExpedicion());
					dml_stmt.setString(23, f33.getReceptor().getUsoCFDI().name());
					
					dml_stmt.setDouble(24, (f33.getDescuento()!=null)?f33.getDescuento().doubleValue():0);
					dml_stmt.setDouble(25, iepsTrasladado);
					dml_stmt.setDouble(26, ivaTrasladado);
					dml_stmt.setDouble(27, ivaRetenido);
					dml_stmt.setDouble(28, isrRetenido);
					dml_stmt.setDouble(29, 0);
					dml_stmt.setDouble(30, 0);
					dml_stmt.setDouble(31, 0);
					
					dml_stmt.setString(32, f33.getNoCertificado());
					dml_stmt.setString(33, t33.getNoCertificadoSAT());
					dml_stmt.setString(34, t33.getSelloCFD());
					dml_stmt.setString(35, t33.getSelloSAT());
					
					//SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
										
					
					
					XMLGregorianCalendar gDateFormatted = null;
					        //DatatypeFactory.newInstance().newXMLGregorianCalendar(formatFecha.format(t33.getFechaTimbrado()));
										
					dml_stmt.setString(36, "||" + t33.getVersion() + "|" + t33.getUUID() + "|" + t33.getFechaTimbrado().toXMLFormat() + 
							"|" + t33.getSelloCFD() + "|" + t33.getNoCertificadoSAT() + "||");
					
					String nombreTipo = "";
					
					switch (f33.getTipoDeComprobante().value()) {
						case "I":
							nombreTipo = "Ingreso";
							break;
						case "E":
							nombreTipo = "Egreso";
							break;
						case "P":
							nombreTipo = "Pago";
							break;
						case "N":
							nombreTipo = "Nomina";
							break;
					}
					
					
					dml_stmt.setString(37, ((f33.getMetodoPago()!=null)?com.micontadigital.facturacion.MetodoPago.getMetodoPagoByClave(f33.getMetodoPago().value()).getDescripcion():""));
					dml_stmt.setString(38, ((f33.getFormaPago()!=null)?com.micontadigital.facturacion.FormaPago.getFormaPagoByClave(f33.getFormaPago()).getDescripcion():""));
					dml_stmt.setString(39, (com.micontadigital.facturacion.UsoCFDI.getUsoCFDIByClave(f33.getReceptor().getUsoCFDI().name()).getDescripcion()));
					dml_stmt.setString(40, nombreTipo);
					dml_stmt.setString(41, "");
					
					dml_stmt.setString(42, (com.micontadigital.facturacion.RegimenFiscal.getRegimenByClave(f33.getEmisor().getRegimenFiscal()).getDescripcion()));
					dml_stmt.setInt(43, 2);
					
					dml_stmt.setString(44, f33.getReceptor().getRfc());
					dml_stmt.setString(45, f33.getReceptor().getNombre());
					dml_stmt.setString(46, f33.getEmisor().getRegimenFiscal());
					
					dml_stmt.setString(47, NumberToLetterConverter.convertNumberToLetter(String.valueOf(f33.getTotal())));
					dml_stmt.setString(48, receptor.getCalle() + " " + receptor.getNumeroExterior() + " " + receptor.getNumeroInterior()
											+ receptor.getColonia() + " " + receptor.getMunicipio() + " " + receptor.getEstado() + " C.P. " + receptor.getCodigoPostal());
					
					dml_stmt.executeUpdate();
					

					System.out.println("Guardada");
					
					
					DecimalFormat df = new DecimalFormat("0000000000.000000");
					
					String expImpresa = "https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx" + 
							"?re=" + f33.getEmisor().getRfc() + "&rr=" + f33.getReceptor().getRfc() + "&tt=" + df.format(f33.getTotal()) + "&id=" + t33.getUUID() +
							"&fe=" + f33.getSello().substring(f33.getSello().length()-8);
			        
			        BarcodeQRCode qrcode = new BarcodeQRCode(expImpresa.trim(), 10, 10, null);
			        Image qrcodeImage = qrcode.getImage(); 
			        qrcodeImage.scalePercent(210);
				    
			        
			        java.awt.Image awtImage = qrcode.createAwtImage(Color.BLACK, Color.WHITE);
			        
			            
			        BufferedImage img = null;
					try {
						
						BufferedImage bImage= new BufferedImage(awtImage.getWidth(null), awtImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
						Graphics2D g = bImage.createGraphics();
						g.drawImage(awtImage, 0, 0, null);
						g.dispose();
						
						
						File outputfileQR = null;  
		    			outputfileQR = new File(IP.getDir() + "qr" + idFactura + u.getNombreBD() + ".jpg");
		    			ImageIO.write(bImage, "jpg", outputfileQR);
		    			
					} catch (IOException ex) {
						ex.printStackTrace();
					}
	    				
					for (int j = 0; j < c.size() ; j++){
						
						
						mx.gob.sat.cfd._3.Comprobante.Conceptos.Concepto ci = c.get(j);
						System.out.println("Identificacion " + ci.getNoIdentificacion());
						System.out.println("Clave " + ci.getClaveProdServ());
						System.out.println("Descripcion " + ci.getDescripcion());
						System.out.println("Valor Unitario " + ci.getValorUnitario().doubleValue());

						/*
						int idProducto = 0;

						String codigoBusqueda = (ci.getNoIdentificacion()!=null)?ci.getNoIdentificacion():ci.getClaveProdServ();

						sql = "select id from pv_productos where lower(codigo)='" + codigoBusqueda.toLowerCase() + "'";


						rs = st.executeQuery(sql);

						boolean existeProducto = false;

						while (rs.next()) {
							existeProducto = true;
							idProducto = rs.getInt(1);
						}

						String descripcionCorta = "";
						String costo = "0";
						String costoInicial = "0";
						String precio = "0";
						String minimo = "0";
						String utilidad = "0";
						String unidad = "1";
						String linea = "1";
						String departamento = "1";
						String precioMayoreo = "0";
						String normal = "0";
						String status = "1";
						String descripcion = "";
						String receta = "0";
						String controlLote = "0";

						String precio2 = "0";
						String precio3 = "0";
						String precio4 = "0";
						String precio5 = "0";
						String precio6 = "0";
						String precio7 = "0";
						String precio8 = "0";
						String precio9 = "0";

						String claveSat = "";


						if (!existeProducto) {

							sql = "select id from pv_unidades_medida where clave_sat='" + ci.getClaveUnidad() + "'";
							rs = st.executeQuery(sql);
							boolean existeUnidad = false;
							int idUnidadMedida = 0;
							while (rs.next()) {
								existeUnidad = true;
								idUnidadMedida = rs.getInt(1);
							}


							if (!existeUnidad) {
								Connection connCfdi = dataSourceCfdi.getConnection();
								Statement stCfdi = null;
								ResultSet rsCfdi = null;

								stCfdi = connCfdi.createStatement();

								sql = "select descripcion from unidades_medida where lower(clave)='" + ci.getClaveUnidad().toLowerCase() + "'"; 

								String descUnidad = "";

								rsCfdi = stCfdi.executeQuery(sql);
								while (rsCfdi.next()) {
									descUnidad = rsCfdi.getString(1);

								}


								if (rsCfdi!=null)rsCfdi.close();
								if (stCfdi!=null)stCfdi.close();
								if (connCfdi!=null)connCfdi.close();


								sql = "select ifnull(max(id),0) + 1 from pv_unidades_medida";
								rs = st.executeQuery(sql);



								while (rs.next()) {
									idUnidadMedida = rs.getInt(1);
								}

								sql = "insert into pv_unidades_medida (descripcion,nombre,clave_sat,id) values (?,?,?,?)";
								dml_stmt = conexion.prepareStatement(sql);

								dml_stmt.setString(1, descUnidad);
								dml_stmt.setString(2, descUnidad);
								dml_stmt.setString(3, ci.getClaveUnidad());
								dml_stmt.setInt(4, idUnidadMedida);

								dml_stmt.executeUpdate();
							}


							sql = "select ifnull(max(id),0) + 1 from pv_productos";
							rs = st.executeQuery(sql);

							while (rs.next()) {
								idProducto = rs.getInt(1);
							}


							sql = "insert into pv_productos (codigo,descripcion_corta,costo,precio,minimo,utilidad,unidad_medida"
									+ ",departamento,linea,precio_mayoreo,normal,status,descripcion,receta,control_lote,costo_inicial"
									+ ",precio2,precio3,precio4,precio5,precio6,precio7,precio8,precio9,clave_sat,id) values "
									+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?)";

							dml_stmt = conexion.prepareStatement(sql);

							dml_stmt.setString(1, (ci.getNoIdentificacion()!=null)?ci.getNoIdentificacion():ci.getClaveProdServ());
							dml_stmt.setString(2, ci.getDescripcion());
							dml_stmt.setDouble(3, ci.getValorUnitario().doubleValue());
							dml_stmt.setDouble(4, ci.getValorUnitario().doubleValue());
							dml_stmt.setDouble(5, Double.parseDouble(minimo));
							dml_stmt.setDouble(6, Double.parseDouble(utilidad));
							dml_stmt.setInt(7, idUnidadMedida);
							dml_stmt.setInt(8, Integer.parseInt(departamento));
							dml_stmt.setInt(9, Integer.parseInt(linea));
							dml_stmt.setDouble(10, ci.getValorUnitario().doubleValue());
							dml_stmt.setDouble(11, Double.parseDouble(normal));
							dml_stmt.setInt(12, Integer.parseInt(status));
							dml_stmt.setString(13, descripcion);
							dml_stmt.setInt(14, Integer.parseInt(receta));
							dml_stmt.setInt(15, Integer.parseInt(controlLote));
							dml_stmt.setDouble(16, Double.parseDouble(costoInicial));

							dml_stmt.setDouble(17, Double.parseDouble(precio2));
							dml_stmt.setDouble(18, Double.parseDouble(precio3));
							dml_stmt.setDouble(19, Double.parseDouble(precio4));
							dml_stmt.setDouble(20, Double.parseDouble(precio5));
							dml_stmt.setDouble(21, Double.parseDouble(precio6));
							dml_stmt.setDouble(22, Double.parseDouble(precio7));
							dml_stmt.setDouble(23, Double.parseDouble(precio8));
							dml_stmt.setDouble(24, Double.parseDouble(precio9));

							dml_stmt.setString(25, ci.getClaveProdServ());

							dml_stmt.setInt(26, idProducto);

							dml_stmt.executeUpdate();


							int lista = 0;
							List<Traslado> listaTraslados = null;
							if (ci.getImpuestos()!= null) {
								listaTraslados = ci.getImpuestos().getTraslados().getTraslado();
								lista = listaTraslados.size();
							}

							boolean conIva = false;
							double tasa = 0;



							for(int i = 0 ; i < lista ; i++) {

								Traslado impuesto = listaTraslados.get(i);

								sql = "select id from pv_impuestos where tipo=1 and clave='" + impuesto.getImpuesto() + "' and porcentaje/100=" + impuesto.getTasaOCuota();

								rs = st.executeQuery(sql);

								int idImpuesto = 0;
								boolean existeImpuesto = false;
								while(rs.next()) {
									idImpuesto = rs.getInt(1);
									existeImpuesto = true;
								}

								if (!existeImpuesto) {

									sql = "select ifnull(max(id),0) + 1 from pv_impuestos";
									rs = st.executeQuery(sql);

									while(rs.next()) {
										idImpuesto = rs.getInt(1);
									}

									sql = "insert into pv_impuestos (id,nombre,descripcion,porcentaje,tipo,nomenclatura,aplicar_todo,en_precio,clave) values (?,?,?,?,?,?,?,?,?)";

									System.out.println(sql);
									dml_stmt = conexion.prepareStatement(sql);

									dml_stmt.setInt(1, idImpuesto);
									dml_stmt.setString(2, Impuesto.getNombre(impuesto.getImpuesto()));
									dml_stmt.setString(3, Impuesto.getDescripcion(impuesto.getImpuesto()));
									dml_stmt.setDouble(4, impuesto.getTasaOCuota().doubleValue()*100);
									dml_stmt.setInt(5, 1);
									dml_stmt.setString(6, Impuesto.getNomenclatura(impuesto.getImpuesto()));
									dml_stmt.setInt(7, 0);
									dml_stmt.setInt(8, 0);
									dml_stmt.setString(9, impuesto.getImpuesto());

									dml_stmt.executeUpdate();

								}

								sql = "select id from pv_impuestos_producto where impuesto=" + idImpuesto + " and producto=" + idProducto;

								rs = st.executeQuery(sql);

								boolean existeImpuestoProd = false;
								while (rs.next()) {
									existeImpuestoProd = true;
								}


								if (!existeImpuestoProd) {
									sql = "insert into pv_impuestos_producto (producto,impuesto,en_precio) values (?,?,?)";

									System.out.println(sql);
									dml_stmt = conexion.prepareStatement(sql);

									dml_stmt.setInt(1, idProducto);
									dml_stmt.setInt(2, idImpuesto);
									dml_stmt.setInt(3, 0);

									dml_stmt.executeUpdate();
								}

							}

						} else {
							//******************************** Si existe producto, revisar impuestos
							int lista = 0;
							List<Traslado> listaTraslados = null;
							if (ci.getImpuestos()!= null) {
								listaTraslados = ci.getImpuestos().getTraslados().getTraslado();
								lista = listaTraslados.size();
							}
							boolean conIva = false;
							double tasa = 0;




							for(int i = 0 ; i < lista ; i++) {

								Traslado impuesto = listaTraslados.get(i);

								sql = "select id from pv_impuestos where tipo=1 and clave='" + impuesto.getImpuesto() + "' and porcentaje/100=" + impuesto.getTasaOCuota();

								rs = st.executeQuery(sql);

								int idImpuesto = 0;
								boolean existeImpuesto = false;
								while(rs.next()) {
									idImpuesto = rs.getInt(1);
									existeImpuesto = true;
								}

								if (!existeImpuesto) {

									sql = "select ifnull(max(id),0) + 1 from pv_impuestos";
									rs = st.executeQuery(sql);

									while(rs.next()) {
										idImpuesto = rs.getInt(1);
									}

									sql = "insert into pv_impuestos (id,nombre,descripcion,porcentaje,tipo,nomenclatura,aplicar_todo,en_precio,clave) values (?,?,?,?,?,?,?,?,?)";

									System.out.println(sql);
									dml_stmt = conexion.prepareStatement(sql);

									dml_stmt.setInt(1, idImpuesto);
									dml_stmt.setString(2, Impuesto.getNombre(impuesto.getImpuesto()));
									dml_stmt.setString(3, Impuesto.getDescripcion(impuesto.getImpuesto()));
									if (impuesto.getTipoFactor().value().toUpperCase().equals("EXENTO")) {
										dml_stmt.setDouble(4, 0);
									} else {
										dml_stmt.setDouble(4, impuesto.getTasaOCuota().doubleValue()*100);
									}
									dml_stmt.setInt(5, 1);
									dml_stmt.setString(6, Impuesto.getNomenclatura(impuesto.getImpuesto()));
									dml_stmt.setInt(7, 0);
									dml_stmt.setInt(8, 0);
									dml_stmt.setString(9, impuesto.getImpuesto());

									dml_stmt.executeUpdate();

								}

								sql = "select id from pv_impuestos_producto where impuesto=" + idImpuesto + " and producto=" + idProducto;

								rs = st.executeQuery(sql);

								boolean existeImpuestoProd = false;
								while (rs.next()) {
									existeImpuestoProd = true;
								}


								if (!existeImpuestoProd) {
									sql = "insert into pv_impuestos_producto (producto,impuesto,en_precio) values (?,?,?)";

									System.out.println(sql);
									dml_stmt = conexion.prepareStatement(sql);

									dml_stmt.setInt(1, idProducto);
									dml_stmt.setInt(2, idImpuesto);
									dml_stmt.setInt(3, 0);

									dml_stmt.executeUpdate();
								}

							}



						}



						double cantidad = ci.getCantidad().doubleValue();

						

						dml_stmt = conexion.prepareStatement(sql);

						
						
						*/
						
						double iva = 0;
						double otros = 0;
						int lista = 0;
						List<Traslado> listaTraslados = null;
						if (ci.getImpuestos()!= null && ci.getImpuestos().getTraslados()!=null) {
							listaTraslados = ci.getImpuestos().getTraslados().getTraslado();
							lista = listaTraslados.size();
						}
						
						boolean conIva = false;
						double tasa = 0;
						
						for(int i = 0 ; i < lista ; i++) {
							if (!listaTraslados.get(i).getTipoFactor().value().toUpperCase().equals("EXENTO")) {
								if (listaTraslados.get(i).getImpuesto().equals("002")) {
									iva = listaTraslados.get(i).getImporte().doubleValue();
									conIva = true;
								} else {
									otros += listaTraslados.get(i).getImporte().doubleValue();
								}
							}
							sql = "INSERT INTO pv_impuestos_facturas (impuesto,tasa,importe,tipo,factura_gasto, nombre_impuesto, tipo_movimiento) VALUES (?,?,?,?,?,?,?)";
						
							dml_stmt = conexion.prepareStatement(sql);
							
							dml_stmt.setString(1, listaTraslados.get(i).getImpuesto());
							dml_stmt.setDouble(2, (listaTraslados.get(i).getTasaOCuota()!=null)?
									listaTraslados.get(i).getTasaOCuota().doubleValue():0);
							dml_stmt.setDouble(3, (listaTraslados.get(i).getImporte()!=null)?
									listaTraslados.get(i).getImporte().doubleValue():0);
							dml_stmt.setString(4, listaTraslados.get(i).getTipoFactor().value());
							dml_stmt.setInt(5, idFactura);
							//dml_stmt.setString(6, com.micontadigital.facturacion.ImpuestoCustom.getImpuesto(listaTraslados.get(i).getImpuesto()).getNombre());
							dml_stmt.setString(6,"IVA");
							dml_stmt.setString(7, "T");
							
							dml_stmt.executeUpdate();
							
							
						}
						
						lista = 0;
						List<mx.gob.sat.cfd._3.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion> listaRetenciones = null;
						if (ci.getImpuestos()!= null && ci.getImpuestos().getRetenciones()!=null) {
							listaRetenciones = ci.getImpuestos().getRetenciones().getRetencion();
							lista = listaRetenciones.size();
						}
						
						for(int i = 0 ; i < lista ; i++) {
							if (!listaRetenciones.get(i).getTipoFactor().value().toUpperCase().equals("EXENTO")) {
								if (listaRetenciones.get(i).getImpuesto().equals("002")) {
									iva = listaRetenciones.get(i).getImporte().doubleValue();
									conIva = true;
								} else {
									otros += listaRetenciones.get(i).getImporte().doubleValue();
								}
							}
							sql = "INSERT INTO pv_impuestos_facturas (impuesto,tasa,importe,tipo,factura_gasto, nombre_impuesto, tipo_movimiento) VALUES (?,?,?,?,?,?,?)";
						
							dml_stmt = conexion.prepareStatement(sql);
							
							dml_stmt.setString(1, listaRetenciones.get(i).getImpuesto());
							dml_stmt.setDouble(2, (listaRetenciones.get(i).getTasaOCuota()!=null)?
									listaRetenciones.get(i).getTasaOCuota().doubleValue():0);
							dml_stmt.setDouble(3, (listaRetenciones.get(i).getImporte()!=null)?
									listaRetenciones.get(i).getImporte().doubleValue():0);
							dml_stmt.setString(4, listaRetenciones.get(i).getTipoFactor().value());
							dml_stmt.setInt(5, idFactura);
							dml_stmt.setString(6, com.micontadigital.facturacion.ImpuestoCustom.getImpuesto(listaRetenciones.get(i).getImpuesto()).getNombre());
							dml_stmt.setString(7, "R");
							
							dml_stmt.executeUpdate();
							
							
						}
						
						

						sql = "INSERT INTO pv_conceptos_facturas (descripcion,valor_unitario,descuento,cantidad,importe,"
								+ "identificacion,unidad,factura_gasto,clave_sat,nombre_unidad) VALUES (?,?,?,?,?,?,?,?,?,?)";
					
						dml_stmt = conexion.prepareStatement(sql);
						
						dml_stmt.setString(1, ci.getDescripcion());
						dml_stmt.setDouble(2, ci.getValorUnitario().doubleValue());
						dml_stmt.setDouble(3,   ( ci.getDescuento()!=null)?ci.getDescuento().doubleValue():0  );
						dml_stmt.setDouble(4, ci.getCantidad().doubleValue());
						dml_stmt.setDouble(5, ci.getImporte().doubleValue());
						dml_stmt.setString(6, ci.getNoIdentificacion());
						dml_stmt.setString(7, ci.getClaveUnidad());
						dml_stmt.setInt(8, idFactura);
						dml_stmt.setString(9, ci.getClaveProdServ());
						dml_stmt.setString(10, com.micontadigital.facturacion.UnidadMedidaSat.getUnidadMedidaByClave(ci.getClaveUnidad()).getDescripcion());
						
						dml_stmt.executeUpdate();

					}
					
					if (pagos!=null) {
						
					List<Pago> listaPagos =	pagos.getPago();
					for (int i = 0 ; i < listaPagos.size() ; i++) {
						
						Pago pago = listaPagos.get(i);
						
						
						
						sql = "INSERT INTO pv_complemento_pago (factura_gasto,uuid_documento,parcialidad,moneda,saldo_anterior,importe,saldo_insoluto,forma_pago"
								+ ",nombre_forma_pago,fecha_pago) values (?,?,?,?,?  ,?,?,?,?,?)";
					
						dml_stmt = conexion.prepareStatement(sql);
						
						dml_stmt.setInt(1, idFactura);
						dml_stmt.setString(2, pago.getDoctoRelacionado().get(0).getIdDocumento());
						dml_stmt.setInt(3, pago.getDoctoRelacionado().get(0).getNumParcialidad().intValue());
						dml_stmt.setString(4, pago.getDoctoRelacionado().get(0).getMonedaDR().name());
						dml_stmt.setDouble(5, pago.getDoctoRelacionado().get(0).getImpSaldoAnt().doubleValue());
						dml_stmt.setDouble(6, pago.getDoctoRelacionado().get(0).getImpPagado().doubleValue());
						dml_stmt.setDouble(7, pago.getDoctoRelacionado().get(0).getImpSaldoInsoluto().doubleValue());
						dml_stmt.setString(8, pago.getFormaDePagoP());
						dml_stmt.setString(9, com.micontadigital.facturacion.FormaPago.getFormaPagoByClave(pago.getFormaDePagoP()).getDescripcion());
						dml_stmt.setString(10, pago.getFechaPago());
						
						
						dml_stmt.executeUpdate();
						
					}
						
						
					}
					
					
					
				} else {
					
					DecimalFormat df = new DecimalFormat("0000000000.000000");
					
					String expImpresa = "https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx" + 
							"?re=" + f33.getEmisor().getRfc() + "&rr=" + f33.getReceptor().getRfc() + "&tt=" + df.format(f33.getTotal()) + "&id=" + t33.getUUID() +
							"&fe=" + f33.getSello().substring(f33.getSello().length()-8);
			        
			        BarcodeQRCode qrcode = new BarcodeQRCode(expImpresa.trim(), 10, 10, null);
			        Image qrcodeImage = qrcode.getImage(); 
			        qrcodeImage.scalePercent(210);
				    
			        
			        java.awt.Image awtImage = qrcode.createAwtImage(Color.BLACK, Color.WHITE);
			        
			            
			        BufferedImage img = null;
					try {
						
						BufferedImage bImage= new BufferedImage(awtImage.getWidth(null), awtImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
						Graphics2D g = bImage.createGraphics();
						g.drawImage(awtImage, 0, 0, null);
						g.dispose();
						
						
						File outputfileQR = null;  
		    			outputfileQR = new File(IP.getDir() + "qr" + idFactura + u.getNombreBD() + ".jpg");
		    			ImageIO.write(bImage, "jpg", outputfileQR);
		    			
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					
					
				}
				
				
				/*
 			sql = "INSERT INTO pv_movimientos_inventario (cantidad,referencia,producto,concepto,vendedor,terminal,turno,almacen) "
                         		+ "VALUES (?,?,?,?,?,?,?,?)";
 			System.out.println(sql);

 			dml_stmt = conn.prepareStatement(sql);

 			dml_stmt.setDouble(1, cantidad);
 			dml_stmt.setInt(2, idCompra);
 			dml_stmt.setInt(3, idProducto);
 			dml_stmt.setInt(4, 1);
 			dml_stmt.setInt(5, u.getUsuarioLocal());
 			dml_stmt.setInt(6, u.getIdTerminal());
 			dml_stmt.setInt(7, 1);
 			dml_stmt.setInt(8, u.getIdSucursal());


 			dml_stmt.executeUpdate();
				 */		
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			e.printStackTrace(pw);
			regresa = sw.toString(); // stack trace as a st

		} catch (Exception e){
			e.printStackTrace();
			e.printStackTrace(pw);
			regresa = sw.toString(); // stack trace as a st

		} finally {
			
			return idFactura;

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
