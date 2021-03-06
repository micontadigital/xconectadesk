package com.idalytec.cfdidesk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.json.simple.JSONObject;

import com.finkok.facturacion.stamp.Application;
import com.finkok.facturacion.stamp.StampSOAPLocator;

import views.core.soap.services.apps.AcuseRecepcionCFDI;
import views.core.soap.services.apps.Incidencia;



/**
 * Servlet implementation class UploadFacturaServlet
 */
@WebServlet("/UploadFacturaServlet")
public class UploadFacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFacturaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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


		String idUsuario = "";

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

				sql = "select id from usuarios where correo='" + correo + "' and password='" + pass + "'" ;

				System.out.println(sql);

				rs = st.executeQuery(sql);

				while (rs.next()) {
					idUsuario = rs.getString(1);
				}


				if (idUsuario.length()>0){


					sql = "select sum(cantidad) - (select count(id) from facturas where status!=0 and usuario=" 
							+ idUsuario + ") from pedidos where status=1 and usuario=" + idUsuario;	

					rs = st.executeQuery(sql);
					int facturas=0;
					while (rs.next()) {
						facturas = rs.getInt(1);
					}
					
				
					if (facturas>0){

						StampSOAPLocator stampSOAP = new StampSOAPLocator();
						Application application = stampSOAP.getApplication();
						

						String path = "/home/cfdiapp/cfdi" + serie + folio + ".xml";
						//String path = "C:\\idalytec.com\\cfdiapp\\cfdi" + serie + folio + ".xml";

						System.out.println(path);
						
						file = new File(path);

						cfdi.write(file);
							
						System.out.println(file.exists());
						
			
						if (file.exists()) {
							
							
							String xml = readFile(path);
							
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
							
							sql = "insert into facturas (usuario, uuid, xml, folio, serie, codigo, incidencia, status"
									+ ", correo, observaciones, metodo_pago, tipo_comprobante) "
									+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
	
							dml_stmt = conexion.prepareStatement(sql);
							dml_stmt.setString(1, idUsuario);
							dml_stmt.setString(2, uuid);
							dml_stmt.setString(4, folio);
							dml_stmt.setString(5, serie);
							dml_stmt.setString(6, codEstatus);
							dml_stmt.setString(7, resultado);
							if (incidencia.length()>0 || uuid.length() < 10) {
								dml_stmt.setString(8, "0");
								dml_stmt.setString(3, xml);
								//		resultado = incidencia;
								generada = false;
							} else {
								dml_stmt.setString(8, "1");
								dml_stmt.setString(3, xmlDato);
								generada = true;
							}
	
							dml_stmt.setString(9, correoReceptor);
							dml_stmt.setString(10, observaciones);
							
							dml_stmt.setString(11, metodoPago);
							dml_stmt.setString(12, tipoComprobante);
	
	
							dml_stmt.executeUpdate();
	
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
					resultado = "Credenciales inv??lidas.";
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
