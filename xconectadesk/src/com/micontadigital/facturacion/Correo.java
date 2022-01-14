package com.micontadigital.facturacion;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo {
	
	public static String enviarComprobante(String xmlPath, String pdfPath, String destino, String rfcEmisor, String folio, String serie, String empresa) {
		String respuesta = "";
		String correo="facturacion@micontadigital.com.mx";
		String contrasenia="Acceso2014.";
		String servidor="smtp.ionos.mx";
		String puerto="587";
		
		final String correo2 = correo;
		final String contrasenia2 = contrasenia;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", servidor);
		props.put("mail.smtp.port", puerto);
		
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(correo2, contrasenia2);
			}
		});
		
		
		
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correo, "MiContaDigital"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			
			message.setSubject("Factura No. " + serie + folio + " " + empresa);

			BodyPart cuerpoMensaje = new MimeBodyPart();
			
			cuerpoMensaje.setContent(com.idalytec.cfdidesk.Correo1.getMensajeFacturacion(empresa), "text/html");
				//cuerpoMensaje.setText(com.micontadigital.controller.Correo.getMensajeFacturacion(empresa));
			
			String pdfFileName = rfcEmisor + "_" + serie + folio + ".pdf";
			String xmlFileName = rfcEmisor + "_" + serie + folio + ".xml";
			
			
			Multipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(cuerpoMensaje);

			BodyPart archivoPDF = new MimeBodyPart();
			String pdf1 = pdfFileName;
			DataSource fuentePDF = new FileDataSource(pdfPath);
			archivoPDF.setDataHandler(new DataHandler(fuentePDF));
			archivoPDF.setFileName(pdfFileName);
			multiparte.addBodyPart(archivoPDF);

			BodyPart archivoXML = new MimeBodyPart();
			String xml1 = xmlFileName;
			DataSource fuenteXML = new FileDataSource(xmlPath);
			archivoXML.setDataHandler(new DataHandler(fuenteXML));
			archivoXML.setFileName(xmlFileName);
			multiparte.addBodyPart(archivoXML);

			// Asignamos al mensaje todas las partes que creamos anteriormente
			message.setContent(multiparte);
			
			
			
			
			
			
										
			Transport.send(message);
			
			respuesta = "El comprobante se envió correctamente";
		
			
		} catch (MessagingException e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		}
		
		return respuesta;
		
		
	}
	
	public static String enviarComprobante(String xmlPath, String pdfPath, String destino, String rfcEmisor, String folio, String serie
			, String empresa, String pdfPath2) {
		String respuesta = "";
		String correo="facturacion@micontadigital.com.mx";
		String contrasenia="Acceso2014.";
		String servidor="smtp.ionos.mx";
		String puerto="587";
		
		final String correo2 = correo;
		final String contrasenia2 = contrasenia;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", servidor);
		props.put("mail.smtp.port", puerto);
		
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(correo2, contrasenia2);
			}
		});
		
		
		
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correo, "MiContaDigital"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			
			message.setSubject("Factura No. " + serie + folio + " " + empresa);

			BodyPart cuerpoMensaje = new MimeBodyPart();
			String cuerpo = "<strong>" + empresa + "</strong>"
					+ " agradece su preferencia y le hace llegar su Factura Electrónica."
					+ "\n\n";
					
			
			cuerpoMensaje.setContent(com.idalytec.cfdidesk.Correo1.getMensajeFacturacion(empresa), "text/html");
				
						
			String pdfFileName = rfcEmisor + "_" + serie + folio + ".pdf";
			String pdfFileName2 = rfcEmisor + "_" + serie + folio + "2.pdf";
			
			String xmlFileName = rfcEmisor + "_" + serie + folio + ".xml";
			
			
			Multipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(cuerpoMensaje);

			BodyPart archivoPDF = new MimeBodyPart();
			String pdf1 = pdfFileName;
			DataSource fuentePDF = new FileDataSource(pdfPath);
			archivoPDF.setDataHandler(new DataHandler(fuentePDF));
			archivoPDF.setFileName(pdfFileName);
			multiparte.addBodyPart(archivoPDF);
			
			/*
			if ((new File(pdfPath2)).exists()) {
			
				BodyPart archivoPDF2 = new MimeBodyPart();
				String pdf2 = pdfFileName2;
				DataSource fuentePDF2 = new FileDataSource(pdfPath2);
				archivoPDF2.setDataHandler(new DataHandler(fuentePDF2));
				archivoPDF2.setFileName(pdfFileName2);
				multiparte.addBodyPart(archivoPDF2);
				
			}
			*/
			
			BodyPart archivoXML = new MimeBodyPart();
			String xml1 = xmlFileName;
			DataSource fuenteXML = new FileDataSource(xmlPath);
			archivoXML.setDataHandler(new DataHandler(fuenteXML));
			archivoXML.setFileName(xmlFileName);
			multiparte.addBodyPart(archivoXML);

			// Asignamos al mensaje todas las partes que creamos anteriormente
			message.setContent(multiparte);
			
			
			
			
			
			
										
			Transport.send(message);
			
			respuesta = "El comprobante se envió correctamente";
		
			
		} catch (MessagingException e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		}
		
		return respuesta;
		
		
	}
	
	
	public static void enviarCancelacion(String correoReceptor, String factura, String uuid, String rfcEmisor, String nombreEmisor){
		try {
			
			final String correo="facturacion@micontadigital.com.mx";
			final String contrasenia="Acceso2014.";
			String servidor="smtp.ionos.mx";
			String puerto="587";
			Properties props = new Properties();
			Session session = null;
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", servidor);
			props.put("mail.smtp.port", puerto);
			
			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					
							return new PasswordAuthentication(correo, contrasenia);
						}
					});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correo, "MiContaDigital"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(correoReceptor));
			
			
			message.setSubject("Informe de cancelación de CFDI.");
			
			BodyPart cuerpoMensaje = new MimeBodyPart();
			
			String mensaje ="Se notifica que el CFDI con folio " + factura + " emitido por "
					+ rfcEmisor + " - " + nombreEmisor
					+ " ha sido cancelado.\n\n"
					+ "UUID SAT: " + uuid + "\n\n\n";
			
			cuerpoMensaje.setContent(com.idalytec.cfdidesk.Correo1.getMensajeCancelacion(mensaje), "text/html");
	
			
			Multipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(cuerpoMensaje);
			message.setContent(multiparte);
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
