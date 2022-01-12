package com.idalytec.cfdidesk;

import java.util.Properties;

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

public class EnviarCorreo {
	
	public EnviarCorreo(){
		
	}
	
	
	public static void enviarBienvenida(String destino, String pass){
		try {
			final String correoOrigen = "idalytec@gmail.com";
			final String contrasenia = "Acceso2014.";
			String servidor = "smtp.gmail.com";
			String puerto = "587";
			Properties props = new Properties();
			Session session = null;
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", servidor);
			props.put("mail.smtp.port", puerto);
			
			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					
							return new PasswordAuthentication(correoOrigen, contrasenia);
						}
					});
			
			final String correo2 = correoOrigen;
			final String contrasenia2 = contrasenia;
			
			
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoOrigen));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			
			
			message.setSubject("Bienvenido a CFDIApp");
			
			BodyPart cuerpoMensaje = new MimeBodyPart();
			Multipart multiparte = new MimeMultipart();
			cuerpoMensaje
					.setText("Bienvenido."
							+ "\n\n"
							+ "Te damos la m�s cordial bienvenida a la Aplicaci�n para Android CFDIApp."
							+ "\nEsperamos que te sea de gran utilidad para generar tus CFDI's en cualquier momento y lugar."
							+ "\nPara acceder a la app solo digita tu correo y el password que te enviamos al final de este correo."
							+ "\nUna vez dentro de la aplicaci�n dirigete al men� EMISOR para cambiar tu contrase�a."
							+ "\nA partir de este momento cuentas con 5 timbres totalmente gratuitos, podr�s usarlos para"
							+ " probar nuestra plataforma y posteriormente podr�s adquirir m�s."
							+ "\n\nCualquier duda o comentario no dudes en contact�rnos, estamos comprometidos a ofrecerte un excelente servicio y todos tus"
							+ " comentarios son de gran utilidad para mejorar la calidad de nuestros productos y servicios."
							+ "\n\nPassword: " + pass
							
							+ "\n\nPuedes usar tambi�n CFDIApp, desc�rgala gratis en https://play.google.com/store/apps/details?id=idalytec.com.cfdiapp"
							
							+ "\n\n\nAtentamente.\n"
							+ "idalytec.com\n"
							+ "contacto@idalytec.com"
							);
			
			
			multiparte.addBodyPart(cuerpoMensaje);
			message.setContent(multiparte);
		
			Transport.send(message);
		
		
		} catch (MessagingException e) {
			
		}
	
		
		
	}
	
	public static void enviarAviso(String usuario){
		try {
			
			final String correoOrigen = "idalytec@gmail.com";
			final String contrasenia = "Acceso2014.";
			String servidor = "smtp.gmail.com";
			String puerto = "587";
			Properties props = new Properties();
			Session session = null;
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", servidor);
			props.put("mail.smtp.port", puerto);
			
			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					
							return new PasswordAuthentication(correoOrigen, contrasenia);
						}
					});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoOrigen));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(correoOrigen));
			
			
			message.setSubject("Nuevo Usuario CFDIApp");
			
			BodyPart cuerpoMensaje = new MimeBodyPart();
			cuerpoMensaje
					.setText("Nuevo Usuario CFDIApp " + usuario);
			
			Multipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(cuerpoMensaje);
			message.setContent(multiparte);
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			
		}
	}
	

	
	public static void enviarCancelacion(String correoReceptor, String factura, String uuid, String rfcEmisor, String nombreEmisor){
		try {
			
			final String correoOrigen = "idalytec@gmail.com";
			final String contrasenia = "Acceso2014.";
			String servidor = "smtp.gmail.com";
			String puerto = "587";
			Properties props = new Properties();
			Session session = null;
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", servidor);
			props.put("mail.smtp.port", puerto);
			
			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					
							return new PasswordAuthentication(correoOrigen, contrasenia);
						}
					});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoOrigen));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(correoReceptor));
			
			
			message.setSubject("Informe de cancelaci�n de CFDI.");
			
			BodyPart cuerpoMensaje = new MimeBodyPart();
			cuerpoMensaje
					.setText("Se notifica que el CFDI con folio " + factura + " emitida por "
							+ rfcEmisor + " - " + nombreEmisor
							+ " ha sido cancelada.\n\n"
							+ "UUID SAT: " + uuid + "\n\n\n"
							+ "Cancelaci�n realizada desde CFDIApp Desk"
							+ "\nPuedes usar tambi�n CFDIApp, desc�rgala gratis en https://play.google.com/store/apps/details?id=idalytec.com.cfdiapp"
							+ "\n\n\n"
							+ "idalytec.com\n"
							+ "contacto@idalytec.com");
			
			Multipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(cuerpoMensaje);
			message.setContent(multiparte);
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			
		}
	}
	
}
