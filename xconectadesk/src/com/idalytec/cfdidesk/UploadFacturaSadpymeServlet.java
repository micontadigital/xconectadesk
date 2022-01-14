package com.idalytec.cfdidesk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.finkok.facturacion.stamp.Application;
import com.finkok.facturacion.stamp.StampSOAPLocator;


import MApeados.SIFEI;
import MApeados.SIFEIServiceLocator;
import MApeados.SifeiException;
import mx.gob.sat.cfd._3.CFDv33;
import views.core.soap.services.apps.AcuseRecepcionCFDI;
import views.core.soap.services.apps.Incidencia;

/**
 * Servlet implementation class UploadFacturaSadpymeServlet
 */
@WebServlet("/UploadFacturaSadpymeServlet")
public class UploadFacturaSadpymeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFacturaSadpymeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		JSONObject jsonObject = new JSONObject();
		response.setContentType("application/json");

		boolean generada = false;
		String resultado = "Upload ";

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		

		Connection conexion = MariaDB.getConexion();
		
		
		
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement dml_stmt = null;
		String sql = "";

		String motivo="";

		int idFacturaCfdiApp = 0;

		int idUsuario = 0;

		File file = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			generada = false;
			resultado = "Error al enviar el archivo.";	

		} else {
			
			
			List items=null;
			try {
				items = sfu.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String correo = "";
			String pass = "";
			String correoReceptor = "";
			String observaciones = "";
			String metodoPago = "1";
			String tipoComprobante = "1";

			FileItem serieItem = (FileItem) items.get(0);
			String serie = serieItem.getString();

			FileItem folioItem = (FileItem) items.get(1);
			String folio = folioItem.getString();

			FileItem cfdi = null;



			cfdi = (FileItem) items.get(2);

			FileItem correoItem = (FileItem) items.get(3);
			correo = correoItem.getString();

			FileItem passItem = (FileItem) items.get(4);
			pass = passItem.getString().trim();

			FileItem correoReceptorItem = (FileItem) items.get(5);
			correoReceptor = correoReceptorItem.getString().trim();

			correo = URLDecoder.decode(correo, "UTF-8");
			pass = URLDecoder.decode(pass, "UTF-8");
			correoReceptor = URLDecoder.decode(correoReceptor, "UTF-8");

			System.out.println("Servlet Upload " + correo);
			System.out.println("Servlet Upload " + pass + ".");

			if (items.size()>6){
				FileItem observacionesItem = (FileItem) items.get(6);
				observaciones = observacionesItem.getString().trim();
				observaciones = URLDecoder.decode(observaciones, "UTF-8");
			}
			
			if (items.size()>7){
				FileItem metodoPagoItem = (FileItem) items.get(7);
				metodoPago = metodoPagoItem.getString().trim();
				
				if (metodoPago.equals("1")||metodoPago.equals("2")) {
					metodoPago = metodoPago;
				} else {
					metodoPago = "1";
				}
				
				
			}
			
			if (items.size()>8){
				FileItem tipoComprobanteItem = (FileItem) items.get(8);
				tipoComprobante = tipoComprobanteItem.getString().trim();
				
				
			}
			
			System.out.println("metodo de pago " + metodoPago);

			try{

				st = conexion.createStatement();

				
				idUsuario = obtenerDisponibles(correo, pass, 2);

				if (true){

				
					if (obtenerDisponibles(correo, pass, 1) > 0){

						StampSOAPLocator stampSOAP = new StampSOAPLocator();
						Application application = stampSOAP.getApplication();
						

						//String path = "/home/cfdiapp/cfdi" + serie + folio + ".xml";
						//String path = "C:\\idalytec.com\\cfdiapp\\cfdi" + serie + folio + ".xml";

						String path = IP.getDir() + "cfdi" + serie + folio + ".xml";
						//
						
						System.out.println(path);
						
						file = new File(path);

						cfdi.write(file);
							
						System.out.println(file.exists());
						
			
						if (file.exists()) {
							
							
							String xml = readFile(path);
							
							byte[] buffer = new byte[1024];
							
							 FileOutputStream fos = new FileOutputStream(
									 IP.getDir() + "cfdi" + serie + folio + "factura.zip");
							 ZipOutputStream zos = new ZipOutputStream(fos);
							 ZipEntry ze= new ZipEntry("factura.xml");
							 zos.putNextEntry(ze);
							 FileInputStream in = new FileInputStream(path);
						        
							 int len;
							 while ((len = in.read(buffer)) > 0) {
								 zos.write(buffer, 0, len);
							 }	
							 in.close();
							 zos.closeEntry();
							 zos.close();
							 
							 File fileZip = new File(IP.getDir() + "cfdi" + serie + folio + "factura.zip");
							 
							 byte[] bytesArray = new byte[(int) fileZip.length()]; 

							  FileInputStream fis = new FileInputStream(fileZip);
							  fis.read(bytesArray); //read file into bytes[]
							  fis.close();
						          
						     
							SIFEIServiceLocator locator = new SIFEIServiceLocator();
							SIFEI sifei = locator.getSIFEIPort();
							
							boolean correcto = false;
							mx.gob.sat.cfd._3.Comprobante f33 = null;
				            List<mx.gob.sat.cfd._3.Comprobante.Complemento> complemento33;
				            mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital t33 = null;
				            File newFile = null;
							try {
								
								///desarrollo
								byte[] archivoRespuesta = sifei.getCFDISign("GAME791105P87"
											, "8534bc8b", bytesArray, "", "NGM5ODAxZWMtYmFhMy0wNWJkLTUxMjUtNWQyZTNjOTUzNjI5");
											
								
								
								/*///produccion
								byte[] archivoRespuesta = sifei.getCFDISign("GAME791105P87", "Js5&Jk7&" 
										, bytesArray, "", "YzY1ZjVmYTMtYjdiMS1kNjczLTI3NmItNWQ1MmZmOWZjMDc4");
								*/
								
								correcto = true;
								
								OutputStream os = new FileOutputStream(
										IP.getDir() + "cfdi" + serie + folio + "factura2.zip"); 
								os.write(archivoRespuesta); 
					            os.close(); 
					            
					            
					            String fileZipPath = IP.getDir() + "cfdi" + serie + folio + "factura2.zip";
					            File destDir = new File(IP.getDir());
					            buffer = new byte[1024];
					            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZipPath));
					            ZipEntry zipEntry = zis.getNextEntry();
					            
					            
					            while (zipEntry != null) {
					            	newFile = new File (IP.getDir() + zipEntry.getName());
					                FileOutputStream fos1 = new FileOutputStream(newFile);
					                int len1;
					                while ((len1 = zis.read(buffer)) > 0) {
					                    fos1.write(buffer, 0, len1);
					                }
					                fos1.close();
					                zipEntry = zis.getNextEntry();
					            }
					            zis.closeEntry();
					            zis.close();
					            
					            String UUIDResp = "";
					            
					            
					            
					            f33 = CFDv33.newComprobante(new FileInputStream(newFile.getAbsolutePath()));
								
					            complemento33 = f33.getComplemento();

								mx.gob.sat.cfd._3.Comprobante.Complemento comp =  complemento33.get(0);

								
									
								List<Object> timbre =  comp.getAny();
								
								System.out.println(comp);
								System.out.println(timbre.size() );
								System.out.println(timbre.get(0) );


								if (timbre.get(0)!=null){
									System.out.println("no es nulo");
									
									t33	= (mx.gob.sat.timbrefiscaldigital.TimbreFiscalDigital) timbre.get(0);
									
									UUIDResp = t33.getUUID();
									
									System.out.println(t33.getUUID());
									System.out.println(t33.getNoCertificadoSAT());
									
									generada = true;

								}
					            

							
							
							
							
							
							/*
							AcuseRecepcionCFDI acuse = application.stamp(xml.getBytes("UTF-8"),"idalytec@gmail.com", "Acceso2014.");
	
							String xmlDato = "";
							String codEstatus = "";
							String uuid = "";
							String incidencia = "";
	
	
	
							if (acuse != null) {
								xmlDato = acuse.getXml();
							
								if (acuse.getCodEstatus() != null) {
									codEstatus = acuse.getCodEstatus();
									resultado = codEstatus;
									generada = true;
								}
								if (acuse.getUUID() != null) {
									uuid = acuse.getUUID();
								}
		
								if (acuse.getIncidencias() != null) {
									Incidencia[] array = acuse.getIncidencias();
									for (Incidencia in : array) {
										if (in.getCodigoError() != null) {
											in.getCodigoError();
										}
										if (in.getMensajeIncidencia() != null) {
											in.getMensajeIncidencia();
										}
										if (in.getWorkProcessId() != null) {
											in.getWorkProcessId();
										}
										resultado += in.getCodigoError() + " --- " 
												+ in.getMensajeIncidencia();
									}
									generada = false;
								}
	
							}
							*/
							/*
							sql = "select ifnull(max(id),0) + 1 from facturas";
							st = conexion.createStatement();
							
							rs = st.executeQuery(sql);
							
							while (rs.next()){
								idFacturaCfdiApp = rs.getInt(1);
							}
							
							
							sql = "insert into facturas (usuario, uuid, xml, folio, serie, codigo, incidencia, status"
									+ ", correo, observaciones, metodo_pago, tipo_comprobante, id) "
									+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
							dml_stmt = conexion.prepareStatement(sql);
							dml_stmt.setInt(1, idUsuario);
							dml_stmt.setString(2, UUIDResp);
							dml_stmt.setString(4, folio);
							dml_stmt.setString(5, serie);
							dml_stmt.setString(6, "");
							dml_stmt.setString(7, resultado);
							dml_stmt.setString(8, "1");
							dml_stmt.setString(3, readFile(newFile.getAbsolutePath()));
							dml_stmt.setString(9, correoReceptor);
							dml_stmt.setString(10, observaciones);
							
							dml_stmt.setString(11, metodoPago);
							dml_stmt.setString(12, tipoComprobante);
	
							dml_stmt.setInt(13, idFacturaCfdiApp);
	
							dml_stmt.executeUpdate();
							*/
								
							
								
							
							
							Connection conn = MariaDB.getConexion();
						
								
								st = conn.createStatement();
								
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
					    		
					        	conexionUsr = MariaDBSadpyme.GetConnection("app.xconecta.com", u.getUsrBD(), u.getPassBD(), u.getNombreBD());
					        	Statement stUsr = conexionUsr.createStatement();
							
								
								sql = "select ifnull(max(id),0) + 1 from pv_facturas_finkok";
								
								rs = stUsr.executeQuery(sql);
								
								int idFacturaFinkok = 0;	
								while (rs.next()) {
									idFacturaFinkok = rs.getInt(1);
									
								}
				
			
								sql = "insert into pv_facturas_finkok (uuid, xml, cliente, status, serie, folio, cod_status, incidencia, total, id, iva) "
										+ " values (?,?,?,?,?, ?,?,?,?,?, ?)";
			
								dml_stmt = conexionUsr.prepareStatement(sql);
								
								dml_stmt.setString(1, ( (t33!=null)?t33.getUUID():"") );
								dml_stmt.setString(2, readFile(newFile.getAbsolutePath()));
								dml_stmt.setInt(3, 1);
								dml_stmt.setInt(4, 1);
								dml_stmt.setString(5, f33.getSerie());
								dml_stmt.setString(6, f33.getFolio());
								dml_stmt.setString(7, "");
								dml_stmt.setString(8, "");
								dml_stmt.setDouble(9, f33.getTotal().doubleValue());
								dml_stmt.setInt(10, idFacturaFinkok);
								dml_stmt.setDouble(11, 0);
								
								dml_stmt.executeUpdate();
								
								String listaVentas = "";
								
								/*
								for(int k = 0 ; k < ventas.size() ; k++) {
									listaVentas += ventas.get(k) + ",";
								}
								
								if (ventas.size()>0) {
									sql = "update pv_ventas set factura=? where id in (" + listaVentas.substring(0,listaVentas.length()-1) + ")";
											
									dml_stmt = conexion.prepareStatement(sql);
									
									dml_stmt.setInt(1, idFacturaFinkok);
									
									dml_stmt.executeUpdate();
									
								}
								*/
								
								
								resultado = "La factura se generó correctamente.";
								
							} catch (SifeiException e) {
								System.out.println(e.getCodigo());
								System.out.println(e.getMessage1());
								System.out.println(e.getError());
								generada = false;
								resultado = e.getCodigo() + " - " + e.getMessage1() + "\n" + e.getError();
								
							} catch (Exception e) {
								System.out.println(e.getMessage());
								
								generada = false;
								resultado = e.getMessage();
								
							}
								
							
							
							
							
							
							
	
							//file.delete();

						} else {
						
							generada = false;
							resultado = "Error al generar el archivo.";
						
							
						}

					} else {
						generada = false;
						resultado = "No se cuenta con timbres disponibles.";
					}		


				} else {
					generada = false;
					resultado = "Credenciales inválidas.";
				}

				
				if (generada) {
					
					//guardaFactura(idFacturaCfdiApp, obtenerIdMP(idUsuario));
					
				}
				
				
				
			} catch (SQLException e){
				e.printStackTrace();

				e.printStackTrace(pw);
				String sStackTrace = sw.toString();

				resultado = sw.toString();
				
				generada = false;
				//resultado = e.getMessage();	

			} catch (IOException e){
				e.printStackTrace();

				generada = false;
				e.printStackTrace(pw);
				String sStackTrace = sw.toString();

				resultado = sw.toString();
				
			} catch (Exception e){
				e.printStackTrace();

				generada = false;
				//resultado = e.getMessage();
				
				e.printStackTrace(pw);
				String sStackTrace = sw.toString();

				resultado = sw.toString();
				
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
					// 	TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


		}


		//if (file!=null){file.delete();};

		jsonObject.put("generada", generada);
		jsonObject.put("resultado", resultado);

		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();

		System.out.println(resultado);


	}
	
	public void guardaFactura(int idCfdiApp, int idMP) {
		Connection conn = MariaDBSadpyme.GetConnection("localhost","usrusuarios","AccesoUsuarios01","usuarios_sadpyme");
		PreparedStatement dml_stmt = null;
		String sql = "";

		Statement st = null;
		ResultSet rs = null;
		
				
		try{

			sql = "insert into facturas_mp (id_factura_cfdiapp,id_mp) values (?,?)";
			
			dml_stmt = conn.prepareStatement(sql);

			dml_stmt.setInt(1, idCfdiApp);
			dml_stmt.setInt(2, idMP);
			
			dml_stmt.executeUpdate();


		} catch (SQLException e){
			e.printStackTrace();
			
		} finally {
			try {
				if(dml_stmt!=null)dml_stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	public int obtenerDisponibles(String correo, String pass, int valor) {
		
		
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		int disponibles = 0;
		int idUsuario = 0;
		Connection conn = MariaDB.getConexion();
		try{
			
			st = conn.createStatement();
			
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
    		
        	conexionUsr = MariaDBSadpyme.GetConnection("app.xconecta.com", u.getUsrBD(), u.getPassBD(), u.getNombreBD());
        	Statement stUsr = conexionUsr.createStatement();
			
			int cantidad=0;
			int facturas=0;
			
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
				
				disponibles = (cantidad - facturas);
				
				System.out.println("disponibles " + cantidad + " " + facturas);
				
			
			
			
			
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
		
		
		//return 0;
		if (valor==1) {
			return disponibles;
		} else {
			return idUsuario;
		}
		
	}
	
	
	public int obtenerIdMP(int idUsuario) {
		Connection conn = MariaDBSadpyme.GetConnection("localhost","usrusuarios","AccesoUsuarios01","usuarios_sadpyme");
		PreparedStatement dml_stmt = null;
		String sql = "";

		Statement st = null;
		ResultSet rs = null;
		
		int idMP = 0;
		
		try{

			sql = "select mp.id"
					+ ",REPLACE(mp.referencia,'FAC','') compradas" 
					+ ",(select count(fmp.id) from mp fmp where fmp.id_mp=mp.id) usadas" 
					+ " from mp"
					+ " where mp.usuario=" + idUsuario
					+ " and mp.status='approved'"
					+ " and mp.vigencia>=now()"
					+ " and substr(mp.referencia,1,3)='FAC'";
			
		
			st = conn.createStatement();
			
			System.out.println(sql);
			
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				
				if (idMP==0) {
					if (rs.getInt(2) - rs.getInt(3) > 0) {
						idMP = rs.getInt(1);
					};
				}
				
			}
			
			System.out.println("idMP " + idMP);


		} catch (SQLException e){
			e.printStackTrace();
			
		} finally {
			try {
				if(dml_stmt!=null)dml_stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return idMP;
		
	}
	
	
	public String readFile(String ruta) {

		String line;
		String f = ruta;

		//error += Charset.defaultCharset();

		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(f),Charset.forName("UTF-8"));

			int i = 0;
			StringBuilder sB = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sB.append(new String(line.getBytes(),Charset.forName("UTF-8")));
				//sB.append(new String( br.readLine().getBytes(),Charset.forName("UTF-8")));
				i++;

				System.out.println(new String(line.getBytes(),Charset.forName("UTF-8")));

				//error += "linea" + new String(br.readLine().getBytes(),Charset.forName("UTF-8"));
				//error += "read" + br.readLine().getBytes();
			}
			//error += " veces " + i; 
			//error += sB.toString();
			return sB.toString();
		} catch (IOException ex) {
			return null;
		}
	}

}
