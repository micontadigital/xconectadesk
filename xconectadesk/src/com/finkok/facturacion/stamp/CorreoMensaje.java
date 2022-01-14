package com.finkok.facturacion.stamp;


import java.io.UnsupportedEncodingException;
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

public class CorreoMensaje {
	
	private static String PRIVACIDAD = "AVISO DE CONFIDENCIALIDAD. Este correo y la informaci&oacute;n contenida o adjunta al mismo es privada y confidencial"
			+ " y va dirigida exclusivamente a su destinatario. Xconecta informa a quien pueda haber recibido"
			+ " este correo por error que, contiene informaci&oacute;n confidencial cuyo uso, copia, reproducci&oacute;n o distribuci&oacute;n est&aacute; expresamente prohibida. "
			+ "Si no eres el destinatario del mismo y recibes este correo por error, te pedimos pongas en conocimiento al emisor y procedas a la eliminaci&oacute;n "
			+ "sin copiarlo, imprimirlo o utilizarlo de ning&uacute;n modo.";
	
	
	

	public static void enviar(String destino, String mensaje, String asunto, String origen){
		String correo= origen;
		String contrasenia="Acceso2021,";
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
			message.setFrom(new InternetAddress(correo, "Xconecta"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			
			
			message.setSubject(asunto);
			//message.setText(mensaje,"text/html");
			
			BodyPart cuerpoMensaje = new MimeBodyPart();
			cuerpoMensaje.setContent(mensaje, "text/html");
			
			Multipart multiparte = new MimeMultipart();
			multiparte.addBodyPart(cuerpoMensaje);
			
			message.setContent(multiparte);
								
			
			Transport.send(message);
		
			
		} catch (MessagingException e) {
			e.printStackTrace();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String getMensajeBienvenida(String correo, String nombre, String idUsuario) {
		String mensaje= "";
		
		mensaje = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"<!--[if !mso]><!-->\r\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
				"<!--<![endif]-->\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
				"<title></title>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"* {\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				"body {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding: 0;\r\n" + 
				"	min-width: 100%;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"	mso-line-height-rule: exactly;\r\n" + 
				"}\r\n" + 
				"table {\r\n" + 
				"	border-spacing: 0;\r\n" + 
				"	color: #333333;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"}\r\n" + 
				"img {\r\n" + 
				"	border: 0;\r\n" + 
				"}\r\n" + 
				".wrapper {\r\n" + 
				"	width: 100%;\r\n" + 
				"	table-layout: fixed;\r\n" + 
				"	-webkit-text-size-adjust: 100%;\r\n" + 
				"	-ms-text-size-adjust: 100%;\r\n" + 
				"}\r\n" + 
				".webkit {\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".outer {\r\n" + 
				"	Margin: 0 auto;\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".full-width-image img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".inner {\r\n" + 
				"	padding: 10px;\r\n" + 
				"}\r\n" + 
				"p {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".h1 {\r\n" + 
				"	font-size: 21px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 15px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".h2 {\r\n" + 
				"	font-size: 18px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 10px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column .contents {\r\n" + 
				"	text-align: left;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column p {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	Margin-bottom: 10px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".two-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"}\r\n" + 
				".two-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 300px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".contents {\r\n" + 
				"	width: 100%;\r\n" + 
				"}\r\n" + 
				".two-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: left;\r\n" + 
				"}\r\n" + 
				".two-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 280px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".two-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".three-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".three-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 200px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".three-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				".three-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 180px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".three-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".img-align-vertical img {\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: middle;\r\n" + 
				"}\r\n" + 
				"@media only screen and (max-device-width: 480px) {\r\n" + 
				"table[class=hide], img[class=hide], td[class=hide] {\r\n" + 
				"	display: none !important;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"<!--[if (gte mso 9)|(IE)]>\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		table {border-collapse: collapse !important;}\r\n" + 
				"	</style>\r\n" + 
				"	<![endif]-->\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#ececec;\">\r\n" + 
				"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#ececec;\">\r\n" + 
				"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#ececec;\" bgcolor=\"#ececec;\">\r\n" + 
				"    <tr>\r\n" + 
				"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \r\n" + 
				"          \r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"\r\n" + 
				"						<table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0\" >\r\n" + 
				"							<tr>\r\n" + 
				"								<td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"								<![endif]--> \r\n" + 
				"          \r\n" + 
				"          <!-- ======= start main body ======= -->\r\n" + 
				"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\r\n" + 
				"            <tr>\r\n" + 
				"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\r\n" + 
				"                \r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
			//	"                                            <td align=\"center\"><font style=\"font-size:11px; text-decoration:none; color:#474b53; font-family: Verdana, Geneva, sans-serif; text-align:left\"><a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">View in browser</a> | <a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">Send to a friend </a></font></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td align=\"center\">&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-top-left-radius:10px; border-top-right-radius:10px\"></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\r\n" + 
				"                                        \r\n" + 
				"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:center;font-size:0;\"><!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													<table width=\"100%\" style=\"border-spacing:0\" >\r\n" + 
				"													<tr>\r\n" + 
				"													<td width=\"20%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				"                                              <div class=\"column\" style=\"width:100%;max-width:410px;display:inline-block;vertical-align:top;\">\r\n" + 
				"                                                <table class=\"contents\" style=\"border-spacing:0; width:100%\"  bgcolor=\"#ffffff\" >\r\n" + 
				"                                                  <tr>\r\n" + 
				"                                                    <td style=\"padding-top:30;padding-bottom:0;padding-right:0;padding-left:0;\" align=\"left\"><a href=\"#\" target=\"_blank\">"
				+ "<img src=\"" 
				+ "https://app.xconecta.com/images/logo_xconecta_black.png" + "\" width=\"400\" alt=\"\" style=\"border-width:0; max-width:400px;height:auto; display:block\" /></a></td>\r\n" + 
				"                                                  </tr>\r\n" + 
				"                                                </table>\r\n" + 
				"                                              </div>\r\n" + 
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td><td width=\"80%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				                                              
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td>\r\n" + 
				"													</tr>\r\n" + 
				"													</table>\r\n" + 
				"													<![endif]--></td>\r\n" + 
				"                                          </tr>\r\n" + 
				                                           
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end header ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero image ======= --><!-- ======= end hero image ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero article ======= -->\r\n" + 
				"                \r\n" + 
				"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\" bgcolor=\"#FFFFFF\">\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td align=\"left\" style=\"padding:0px 40px 40px 40px\"><p style=\"color:#5b5f65; font-size:28px; text-align:left; font-family: Verdana, Geneva, sans-serif\">"
				+ "Hola " + nombre
				+ " </p>\r\n" + 
				"                      <p style=\"color:#5b5f65; font-size:16px; text-align:left; font-family: Verdana, Geneva, sans-serif\">Te damos las gracias por registrarte en <strong>Xconecta</strong> para nosotros es un compromiso el brindarte un servicio de calidad y estamos seguros que nuestra plataforma digital te ser&aacute; de gran utilidad para controlar tu negocio. <br />\r\n" + 
				"                        <br />\r\n" + 
				"                        A partir de este momento tendr&aacute;s 30 d&iacute;as para probar nuestra plataforma digital totalmente gratis, cuentas con 30 facturas que podr&aacute;s usar sin ning&uacute;n costo y si al terminar este peri&oacute;do deseas continuar usando nuestro servicio podr&aacute;s elegir cualquiera de nuestros planes pagando de forma mensual o aprovechando el descuento por pago anual. <br />\r\n" + 
				"                        <br />\r\n" + 
				"                        Da clic en el siguiente enlace para activar tu cuenta. <br />\r\n" + 
				"                        <br />\r\n" + 
				"                         </p>\r\n" + 
				"                      \r\n" + 
				"                      <!-- START BUTTON -->\r\n" + 
				"                      \r\n" + 
				"                      <center>\r\n" + 
				"                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
				"                                <tr>\r\n" + 
				"                                  <td height=\"20\" width=\"100%\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td>\r\n" + 
				"                                </tr>\r\n" + 
				"                              </table>\r\n" + 
				"                              <table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin:0 auto;\">\r\n" + 
				"                                <tbody>\r\n" + 
				"                                  <tr>\r\n" + 
				"                                    <td align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin:0 auto;\">\r\n" + 
				"                                        <tr>\r\n" + 
				"                                          <td width=\"250\" height=\"60\" align=\"center\" bgcolor=\"#2f9780\"><a href=\"https://app.xconecta.com/ValidaCorreoServlet?id=" + idUsuario + "\" style=\"width:250; display:block; text-decoration:none; border:0; text-align:center; font-weight:bold;font-size:18px; font-family: Arial, sans-serif; color: #ffffff; background:#2f9780\" class=\"button_link\">Verificar Correo</a></td>\r\n" + 
				"                                        </tr>\r\n" + 
				"                                      </table></td>\r\n" + 
				"                                  </tr>\r\n" + 
				"                                </tbody>\r\n" + 
				"                              </table></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </table>\r\n" + 
				"                      </center>\r\n" + 
				"                      \r\n" + 
				"                      <!-- END BUTTON --></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end hero article ======= -->  \r\n" + 
				"                \r\n" + 
				"                 <!-- ======= start footer ======= -->\r\n" + 
				"                \r\n" + 
				"               <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"  bgcolor=\"#2f9780\">\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"40\" align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Xconecta M&eacute;xico</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table width=\"150\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"          <tr>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/237854a9-0a06-4f88-a9b8-c36e57e31083.png\" alt=\"facebook\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"34\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/2fb3f578-f70a-41b6-9bbc-f99a174d6456.png\" alt=\"twitter\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/17c02388-c25e-4eb5-a7cc-8f34458a50ad.png\" alt=\"linkedin\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"          </tr>\r\n" + 
				"        </table></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:10px;padding-left:10px;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Todos los derechos reservados.</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents1\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px; padding:13px; text-align:justify; \"><font style=\"font-size:11px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">" + PRIVACIDAD  + "</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> \r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px\">"
								
				//+ PRIVACIDAD
				
				+ "</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td>&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"    </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"               <!-- ======= end footer ======= --></td>\r\n" + 
				"            </tr>\r\n" + 
				"          </table>\r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"			<![endif]--> \r\n" + 
				"        </div></td>\r\n" + 
				"    </tr>\r\n" + 
				"  </table>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return mensaje;
	}
	
	
	public static String getMensajeFacturacion(String empresa) {
		String mensaje= "";
		
		mensaje = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"<!--[if !mso]><!-->\r\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
				"<!--<![endif]-->\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
				"<title></title>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"* {\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				"body {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding: 0;\r\n" + 
				"	min-width: 100%;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"	mso-line-height-rule: exactly;\r\n" + 
				"}\r\n" + 
				"table {\r\n" + 
				"	border-spacing: 0;\r\n" + 
				"	color: #333333;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"}\r\n" + 
				"img {\r\n" + 
				"	border: 0;\r\n" + 
				"}\r\n" + 
				".wrapper {\r\n" + 
				"	width: 100%;\r\n" + 
				"	table-layout: fixed;\r\n" + 
				"	-webkit-text-size-adjust: 100%;\r\n" + 
				"	-ms-text-size-adjust: 100%;\r\n" + 
				"}\r\n" + 
				".webkit {\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".outer {\r\n" + 
				"	Margin: 0 auto;\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".full-width-image img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".inner {\r\n" + 
				"	padding: 10px;\r\n" + 
				"}\r\n" + 
				"p {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".h1 {\r\n" + 
				"	font-size: 21px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 15px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".h2 {\r\n" + 
				"	font-size: 18px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 10px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column .contents {\r\n" + 
				"	text-align: left;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column p {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	Margin-bottom: 10px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".two-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"}\r\n" + 
				".two-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 300px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".contents {\r\n" + 
				"	width: 100%;\r\n" + 
				"}\r\n" + 
				".two-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: left;\r\n" + 
				"}\r\n" + 
				".two-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 280px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".two-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".three-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".three-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 200px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".three-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				".three-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 180px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".three-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".img-align-vertical img {\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: middle;\r\n" + 
				"}\r\n" + 
				"@media only screen and (max-device-width: 480px) {\r\n" + 
				"table[class=hide], img[class=hide], td[class=hide] {\r\n" + 
				"	display: none !important;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"<!--[if (gte mso 9)|(IE)]>\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		table {border-collapse: collapse !important;}\r\n" + 
				"	</style>\r\n" + 
				"	<![endif]-->\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#ececec;\">\r\n" + 
				"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#ececec;\">\r\n" + 
				"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#ececec;\" bgcolor=\"#ececec;\">\r\n" + 
				"    <tr>\r\n" + 
				"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \r\n" + 
				"          \r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"\r\n" + 
				"						<table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0\" >\r\n" + 
				"							<tr>\r\n" + 
				"								<td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"								<![endif]--> \r\n" + 
				"          \r\n" + 
				"          <!-- ======= start main body ======= -->\r\n" + 
				"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\r\n" + 
				"            <tr>\r\n" + 
				"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\r\n" + 
				"                \r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
			//	"                                            <td align=\"center\"><font style=\"font-size:11px; text-decoration:none; color:#474b53; font-family: Verdana, Geneva, sans-serif; text-align:left\"><a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">View in browser</a> | <a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">Send to a friend </a></font></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td align=\"center\">&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-top-left-radius:10px; border-top-right-radius:10px\"></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\r\n" + 
				"                                        \r\n" + 
				"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:center;font-size:0;\"><!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													<table width=\"100%\" style=\"border-spacing:0\" >\r\n" + 
				"													<tr>\r\n" + 
				"													<td width=\"20%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				"                                              <div class=\"column\" style=\"width:100%;max-width:410px;display:inline-block;vertical-align:top;\">\r\n" + 
				"                                                <table class=\"contents\" style=\"border-spacing:0; width:100%\"  bgcolor=\"#ffffff\" >\r\n" + 
				"                                                  <tr>\r\n" + 
				"                                                    <td style=\"padding-top:30;padding-bottom:0;padding-right:0;padding-left:0;\" align=\"left\"><a href=\"#\" target=\"_blank\">"
				+ "<img src=\"" 
				+ "https://app.xconecta.com/images/logo_xconecta_black.png" + "\" width=\"400\" alt=\"\" style=\"border-width:0; max-width:400px;height:auto; display:block\" /></a></td>\r\n" + 
				"                                                  </tr>\r\n" + 
				"                                                </table>\r\n" + 
				"                                              </div>\r\n" + 
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td><td width=\"80%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				                                              
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td>\r\n" + 
				"													</tr>\r\n" + 
				"													</table>\r\n" + 
				"													<![endif]--></td>\r\n" + 
				"                                          </tr>\r\n" + 
				                                           
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end header ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero image ======= --><!-- ======= end hero image ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero article ======= -->\r\n" + 
				"                \r\n" + 
				"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\" bgcolor=\"#FFFFFF\">\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td align=\"left\" style=\"padding:0px 40px 40px 40px\"><p style=\"color:#5b5f65; font-size:28px; text-align:left; font-family: Verdana, Geneva, sans-serif\">"
				+ empresa
				+ " </p>\r\n" + 
				"                      <p style=\"color:#5b5f65; font-size:16px; text-align:left; font-family: Verdana, Geneva, sans-serif\"><br />\r\n" + 
				"                        " +
				" Agradece su preferencia y le hace llegar su Factura Electr&oacute;nica."+
				"                        <br />\r\n" + 
				"                         </p>\r\n" + 
				"                      \r\n" + 
				                      
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end hero article ======= -->  \r\n" + 
				"                \r\n" + 
				"                 <!-- ======= start footer ======= -->\r\n" + 
				"                \r\n" + 
				"               <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"  bgcolor=\"#2f9780\">\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"40\" align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Xconecta M&eacute;xico</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table width=\"150\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"          <tr>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/237854a9-0a06-4f88-a9b8-c36e57e31083.png\" alt=\"facebook\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"34\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/2fb3f578-f70a-41b6-9bbc-f99a174d6456.png\" alt=\"twitter\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/17c02388-c25e-4eb5-a7cc-8f34458a50ad.png\" alt=\"linkedin\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"          </tr>\r\n" + 
				"        </table></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:10px;padding-left:10px;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Todos los derechos reservados.</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents1\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px; padding:13px; text-align:justify; \"><font style=\"font-size:11px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">" + PRIVACIDAD  + "</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> \r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px\"></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td>&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"    </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"               <!-- ======= end footer ======= --></td>\r\n" + 
				"            </tr>\r\n" + 
				"          </table>\r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"			<![endif]--> \r\n" + 
				"        </div></td>\r\n" + 
				"    </tr>\r\n" + 
				"  </table>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return mensaje;
	}
	
	public static String getMensajeCancelacion(String mensajeRecibido) {
		
		
		String mensaje = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"<!--[if !mso]><!-->\r\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
				"<!--<![endif]-->\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
				"<title></title>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"* {\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				"body {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding: 0;\r\n" + 
				"	min-width: 100%;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"	mso-line-height-rule: exactly;\r\n" + 
				"}\r\n" + 
				"table {\r\n" + 
				"	border-spacing: 0;\r\n" + 
				"	color: #333333;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"}\r\n" + 
				"img {\r\n" + 
				"	border: 0;\r\n" + 
				"}\r\n" + 
				".wrapper {\r\n" + 
				"	width: 100%;\r\n" + 
				"	table-layout: fixed;\r\n" + 
				"	-webkit-text-size-adjust: 100%;\r\n" + 
				"	-ms-text-size-adjust: 100%;\r\n" + 
				"}\r\n" + 
				".webkit {\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".outer {\r\n" + 
				"	Margin: 0 auto;\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".full-width-image img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".inner {\r\n" + 
				"	padding: 10px;\r\n" + 
				"}\r\n" + 
				"p {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".h1 {\r\n" + 
				"	font-size: 21px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 15px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".h2 {\r\n" + 
				"	font-size: 18px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 10px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column .contents {\r\n" + 
				"	text-align: left;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column p {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	Margin-bottom: 10px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".two-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"}\r\n" + 
				".two-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 300px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".contents {\r\n" + 
				"	width: 100%;\r\n" + 
				"}\r\n" + 
				".two-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: left;\r\n" + 
				"}\r\n" + 
				".two-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 280px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".two-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".three-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".three-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 200px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".three-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				".three-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 180px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".three-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".img-align-vertical img {\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: middle;\r\n" + 
				"}\r\n" + 
				"@media only screen and (max-device-width: 480px) {\r\n" + 
				"table[class=hide], img[class=hide], td[class=hide] {\r\n" + 
				"	display: none !important;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"<!--[if (gte mso 9)|(IE)]>\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		table {border-collapse: collapse !important;}\r\n" + 
				"	</style>\r\n" + 
				"	<![endif]-->\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#ececec;\">\r\n" + 
				"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#ececec;\">\r\n" + 
				"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#ececec;\" bgcolor=\"#ececec;\">\r\n" + 
				"    <tr>\r\n" + 
				"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \r\n" + 
				"          \r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"\r\n" + 
				"						<table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0\" >\r\n" + 
				"							<tr>\r\n" + 
				"								<td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"								<![endif]--> \r\n" + 
				"          \r\n" + 
				"          <!-- ======= start main body ======= -->\r\n" + 
				"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\r\n" + 
				"            <tr>\r\n" + 
				"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\r\n" + 
				"                \r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
			//	"                                            <td align=\"center\"><font style=\"font-size:11px; text-decoration:none; color:#474b53; font-family: Verdana, Geneva, sans-serif; text-align:left\"><a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">View in browser</a> | <a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">Send to a friend </a></font></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td align=\"center\">&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-top-left-radius:10px; border-top-right-radius:10px\"></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\r\n" + 
				"                                        \r\n" + 
				"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:center;font-size:0;\"><!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													<table width=\"100%\" style=\"border-spacing:0\" >\r\n" + 
				"													<tr>\r\n" + 
				"													<td width=\"20%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				"                                              <div class=\"column\" style=\"width:100%;max-width:410px;display:inline-block;vertical-align:top;\">\r\n" + 
				"                                                <table class=\"contents\" style=\"border-spacing:0; width:100%\"  bgcolor=\"#ffffff\" >\r\n" + 
				"                                                  <tr>\r\n" + 
				"                                                    <td style=\"padding-top:30;padding-bottom:0;padding-right:0;padding-left:0;\" align=\"left\"><a href=\"#\" target=\"_blank\">"
				+ "<img src=\"" 
				+ "https://app.xconecta.com/images/logo_xconecta_black.png" + "\" width=\"400\" alt=\"\" style=\"border-width:0; max-width:400px;height:auto; display:block\" /></a></td>\r\n" + 
				"                                                  </tr>\r\n" + 
				"                                                </table>\r\n" + 
				"                                              </div>\r\n" + 
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td><td width=\"80%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				                                             
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td>\r\n" + 
				"													</tr>\r\n" + 
				"													</table>\r\n" + 
				"													<![endif]--></td>\r\n" + 
				"                                          </tr>\r\n" + 
				                                           
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end header ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero image ======= --><!-- ======= end hero image ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero article ======= -->\r\n" + 
				"                \r\n" + 
				"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\" bgcolor=\"#FFFFFF\">\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td align=\"left\" style=\"padding:0px 40px 40px 40px\"><p style=\"color:#5b5f65; font-size:28px; text-align:left; font-family: Verdana, Geneva, sans-serif\">"
				  
				+   " </p>\r\n" + 
				"                      <p style=\"color:#5b5f65; font-size:16px; text-align:left; font-family: Verdana, Geneva, sans-serif\">" + mensajeRecibido + "<br />\r\n" + 
				"                        <br />\r\n" + 
				" "
				+ ""+
				"                        <br />\r\n" + 
				 
				"                        <br />\r\n" + 
				"                         </p>\r\n" + 
				"                      \r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end hero article ======= -->  \r\n" + 
				"                \r\n" + 
				"                 <!-- ======= start footer ======= -->\r\n" + 
				"                \r\n" + 
				"               <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"  bgcolor=\"#2f9780\">\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"40\" align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Xconecta M&eacute;xico</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table width=\"150\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"          <tr>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/237854a9-0a06-4f88-a9b8-c36e57e31083.png\" alt=\"facebook\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"34\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/2fb3f578-f70a-41b6-9bbc-f99a174d6456.png\" alt=\"twitter\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/17c02388-c25e-4eb5-a7cc-8f34458a50ad.png\" alt=\"linkedin\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"          </tr>\r\n" + 
				"        </table></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:10px;padding-left:10px;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Todos los derechos reservados.</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents1\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px; padding:13px; text-align:justify; \"><font style=\"font-size:11px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">" + PRIVACIDAD  + "</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> \r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px\"></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td>&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"    </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"               <!-- ======= end footer ======= --></td>\r\n" + 
				"            </tr>\r\n" + 
				"          </table>\r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"			<![endif]--> \r\n" + 
				"        </div></td>\r\n" + 
				"    </tr>\r\n" + 
				"  </table>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return mensaje;
	}
	
public static String getMensajePago(String mensajeRecibido) {
		
		
		String mensaje = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"<!--[if !mso]><!-->\r\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
				"<!--<![endif]-->\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
				"<title></title>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"* {\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				"body {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding: 0;\r\n" + 
				"	min-width: 100%;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"	mso-line-height-rule: exactly;\r\n" + 
				"}\r\n" + 
				"table {\r\n" + 
				"	border-spacing: 0;\r\n" + 
				"	color: #333333;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"}\r\n" + 
				"img {\r\n" + 
				"	border: 0;\r\n" + 
				"}\r\n" + 
				".wrapper {\r\n" + 
				"	width: 100%;\r\n" + 
				"	table-layout: fixed;\r\n" + 
				"	-webkit-text-size-adjust: 100%;\r\n" + 
				"	-ms-text-size-adjust: 100%;\r\n" + 
				"}\r\n" + 
				".webkit {\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".outer {\r\n" + 
				"	Margin: 0 auto;\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"}\r\n" + 
				".full-width-image img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 600px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".inner {\r\n" + 
				"	padding: 10px;\r\n" + 
				"}\r\n" + 
				"p {\r\n" + 
				"	Margin: 0;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".h1 {\r\n" + 
				"	font-size: 21px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 15px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".h2 {\r\n" + 
				"	font-size: 18px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	Margin-top: 10px;\r\n" + 
				"	Margin-bottom: 5px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column .contents {\r\n" + 
				"	text-align: left;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".one-column p {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	Margin-bottom: 10px;\r\n" + 
				"	font-family: Arial, sans-serif;\r\n" + 
				"	-webkit-font-smoothing: antialiased;\r\n" + 
				"}\r\n" + 
				".two-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"}\r\n" + 
				".two-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 300px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".contents {\r\n" + 
				"	width: 100%;\r\n" + 
				"}\r\n" + 
				".two-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: left;\r\n" + 
				"}\r\n" + 
				".two-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 280px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".two-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".three-column {\r\n" + 
				"	text-align: center;\r\n" + 
				"	font-size: 0;\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"}\r\n" + 
				".three-column .column {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 200px;\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: top;\r\n" + 
				"}\r\n" + 
				".three-column .contents {\r\n" + 
				"	font-size: 14px;\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				".three-column img {\r\n" + 
				"	width: 100%;\r\n" + 
				"	max-width: 180px;\r\n" + 
				"	height: auto;\r\n" + 
				"}\r\n" + 
				".three-column .text {\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"}\r\n" + 
				".img-align-vertical img {\r\n" + 
				"	display: inline-block;\r\n" + 
				"	vertical-align: middle;\r\n" + 
				"}\r\n" + 
				"@media only screen and (max-device-width: 480px) {\r\n" + 
				"table[class=hide], img[class=hide], td[class=hide] {\r\n" + 
				"	display: none !important;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"<!--[if (gte mso 9)|(IE)]>\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		table {border-collapse: collapse !important;}\r\n" + 
				"	</style>\r\n" + 
				"	<![endif]-->\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#ececec;\">\r\n" + 
				"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#ececec;\">\r\n" + 
				"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#ececec;\" bgcolor=\"#ececec;\">\r\n" + 
				"    <tr>\r\n" + 
				"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \r\n" + 
				"          \r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"\r\n" + 
				"						<table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0\" >\r\n" + 
				"							<tr>\r\n" + 
				"								<td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"								<![endif]--> \r\n" + 
				"          \r\n" + 
				"          <!-- ======= start main body ======= -->\r\n" + 
				"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\r\n" + 
				"            <tr>\r\n" + 
				"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\r\n" + 
				"                \r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
			//	"                                            <td align=\"center\"><font style=\"font-size:11px; text-decoration:none; color:#474b53; font-family: Verdana, Geneva, sans-serif; text-align:left\"><a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">View in browser</a> | <a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">Send to a friend </a></font></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td align=\"center\">&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-top-left-radius:10px; border-top-right-radius:10px\"></td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody>\r\n" + 
				"                          <tr>\r\n" + 
				"                            <td align=\"center\"><center>\r\n" + 
				"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
				"                                  <tbody>\r\n" + 
				"                                    <tr>\r\n" + 
				"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\r\n" + 
				"                                        \r\n" + 
				"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
				"                                          <tr>\r\n" + 
				"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:center;font-size:0;\"><!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													<table width=\"100%\" style=\"border-spacing:0\" >\r\n" + 
				"													<tr>\r\n" + 
				"													<td width=\"20%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				"                                              <div class=\"column\" style=\"width:100%;max-width:410px;display:inline-block;vertical-align:top;\">\r\n" + 
				"                                                <table class=\"contents\" style=\"border-spacing:0; width:100%\"  bgcolor=\"#ffffff\" >\r\n" + 
				"                                                  <tr>\r\n" + 
				"                                                    <td style=\"padding-top:30;padding-bottom:0;padding-right:0;padding-left:0;\" align=\"left\"><a href=\"#\" target=\"_blank\">"
				+ "<img src=\"" 
				+ "https://app.xconecta.com/images/logo_xconecta_black.png" + "\" width=\"400\" alt=\"\" style=\"border-width:0; max-width:400px;height:auto; display:block\" /></a></td>\r\n" + 
				"                                                  </tr>\r\n" + 
				"                                                </table>\r\n" + 
				"                                              </div>\r\n" + 
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td><td width=\"80%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
				"													<![endif]-->\r\n" + 
				"                                              \r\n" + 
				                                              
				"                                              \r\n" + 
				"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"													</td>\r\n" + 
				"													</tr>\r\n" + 
				"													</table>\r\n" + 
				"													<![endif]--></td>\r\n" + 
				"                                          </tr>\r\n" + 
				                                           
				"                                          <tr>\r\n" + 
				"                                            <td>&nbsp;</td>\r\n" + 
				"                                          </tr>\r\n" + 
				"                                        </table></td>\r\n" + 
				"                                    </tr>\r\n" + 
				"                                  </tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                              </center></td>\r\n" + 
				"                          </tr>\r\n" + 
				"                        </tbody>\r\n" + 
				"                      </table></td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end header ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero image ======= --><!-- ======= end hero image ======= --> \r\n" + 
				"                \r\n" + 
				"                <!-- ======= start hero article ======= -->\r\n" + 
				"                \r\n" + 
				"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\" bgcolor=\"#FFFFFF\">\r\n" + 
				"                  <tr>\r\n" + 
				"                    <td align=\"left\" style=\"padding:0px 40px 40px 40px\"><p style=\"color:#5b5f65; font-size:28px; text-align:left; font-family: Verdana, Geneva, sans-serif\">"
				  
				+ " </p>\r\n" + 
				"                      <p style=\"color:#5b5f65; font-size:16px; text-align:left; font-family: Verdana, Geneva, sans-serif\">" + mensajeRecibido + "<br />\r\n" + 
				"                        <br />\r\n" + 
				 
				"                        <br />\r\n" + 
				 
				"                        <br />\r\n" + 
				"                         </p>\r\n" + 
				"                      \r\n" + 
				"                  </tr>\r\n" + 
				"                </table>\r\n" + 
				"                \r\n" + 
				"                <!-- ======= end hero article ======= -->  \r\n" + 
				"                \r\n" + 
				"                 <!-- ======= start footer ======= -->\r\n" + 
				"                \r\n" + 
				"               <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"  bgcolor=\"#2f9780\">\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"40\" align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Xconecta M&eacute;xico</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table width=\"150\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"          <tr>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/237854a9-0a06-4f88-a9b8-c36e57e31083.png\" alt=\"facebook\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"34\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/2fb3f578-f70a-41b6-9bbc-f99a174d6456.png\" alt=\"twitter\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/17c02388-c25e-4eb5-a7cc-8f34458a50ad.png\" alt=\"linkedin\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
				"          </tr>\r\n" + 
				"        </table></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:10px;padding-left:10px;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Todos los derechos reservados.</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents1\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px; padding:13px; text-align:justify; \"><font style=\"font-size:11px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">" + PRIVACIDAD  + "</font></td>\r\n" + 
				"      </tr>\r\n" + 
				"      </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> \r\n" + 
				"      <tr>\r\n" + 
				"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px\"></td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td>&nbsp;</td>\r\n" + 
				"      </tr>\r\n" + 
				"    </table></td>\r\n" + 
				"  </tr>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"               <!-- ======= end footer ======= --></td>\r\n" + 
				"            </tr>\r\n" + 
				"          </table>\r\n" + 
				"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"			<![endif]--> \r\n" + 
				"        </div></td>\r\n" + 
				"    </tr>\r\n" + 
				"  </table>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return mensaje;
	}



public static String getRecuperaPassword(String correo, String nombre, String idUsuario) {
	String mensaje= "";
	
	mensaje = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
			"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
			"<head>\r\n" + 
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
			"<!--[if !mso]><!-->\r\n" + 
			"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
			"<title></title>\r\n" + 
			"<style type=\"text/css\">\r\n" + 
			"* {\r\n" + 
			"	-webkit-font-smoothing: antialiased;\r\n" + 
			"}\r\n" + 
			"body {\r\n" + 
			"	Margin: 0;\r\n" + 
			"	padding: 0;\r\n" + 
			"	min-width: 100%;\r\n" + 
			"	font-family: Arial, sans-serif;\r\n" + 
			"	-webkit-font-smoothing: antialiased;\r\n" + 
			"	mso-line-height-rule: exactly;\r\n" + 
			"}\r\n" + 
			"table {\r\n" + 
			"	border-spacing: 0;\r\n" + 
			"	color: #333333;\r\n" + 
			"	font-family: Arial, sans-serif;\r\n" + 
			"}\r\n" + 
			"img {\r\n" + 
			"	border: 0;\r\n" + 
			"}\r\n" + 
			".wrapper {\r\n" + 
			"	width: 100%;\r\n" + 
			"	table-layout: fixed;\r\n" + 
			"	-webkit-text-size-adjust: 100%;\r\n" + 
			"	-ms-text-size-adjust: 100%;\r\n" + 
			"}\r\n" + 
			".webkit {\r\n" + 
			"	max-width: 600px;\r\n" + 
			"}\r\n" + 
			".outer {\r\n" + 
			"	Margin: 0 auto;\r\n" + 
			"	width: 100%;\r\n" + 
			"	max-width: 600px;\r\n" + 
			"}\r\n" + 
			".full-width-image img {\r\n" + 
			"	width: 100%;\r\n" + 
			"	max-width: 600px;\r\n" + 
			"	height: auto;\r\n" + 
			"}\r\n" + 
			".inner {\r\n" + 
			"	padding: 10px;\r\n" + 
			"}\r\n" + 
			"p {\r\n" + 
			"	Margin: 0;\r\n" + 
			"	padding-bottom: 10px;\r\n" + 
			"}\r\n" + 
			".h1 {\r\n" + 
			"	font-size: 21px;\r\n" + 
			"	font-weight: bold;\r\n" + 
			"	Margin-top: 15px;\r\n" + 
			"	Margin-bottom: 5px;\r\n" + 
			"	font-family: Arial, sans-serif;\r\n" + 
			"	-webkit-font-smoothing: antialiased;\r\n" + 
			"}\r\n" + 
			".h2 {\r\n" + 
			"	font-size: 18px;\r\n" + 
			"	font-weight: bold;\r\n" + 
			"	Margin-top: 10px;\r\n" + 
			"	Margin-bottom: 5px;\r\n" + 
			"	font-family: Arial, sans-serif;\r\n" + 
			"	-webkit-font-smoothing: antialiased;\r\n" + 
			"}\r\n" + 
			".one-column .contents {\r\n" + 
			"	text-align: left;\r\n" + 
			"	font-family: Arial, sans-serif;\r\n" + 
			"	-webkit-font-smoothing: antialiased;\r\n" + 
			"}\r\n" + 
			".one-column p {\r\n" + 
			"	font-size: 14px;\r\n" + 
			"	Margin-bottom: 10px;\r\n" + 
			"	font-family: Arial, sans-serif;\r\n" + 
			"	-webkit-font-smoothing: antialiased;\r\n" + 
			"}\r\n" + 
			".two-column {\r\n" + 
			"	text-align: center;\r\n" + 
			"	font-size: 0;\r\n" + 
			"}\r\n" + 
			".two-column .column {\r\n" + 
			"	width: 100%;\r\n" + 
			"	max-width: 300px;\r\n" + 
			"	display: inline-block;\r\n" + 
			"	vertical-align: top;\r\n" + 
			"}\r\n" + 
			".contents {\r\n" + 
			"	width: 100%;\r\n" + 
			"}\r\n" + 
			".two-column .contents {\r\n" + 
			"	font-size: 14px;\r\n" + 
			"	text-align: left;\r\n" + 
			"}\r\n" + 
			".two-column img {\r\n" + 
			"	width: 100%;\r\n" + 
			"	max-width: 280px;\r\n" + 
			"	height: auto;\r\n" + 
			"}\r\n" + 
			".two-column .text {\r\n" + 
			"	padding-top: 10px;\r\n" + 
			"}\r\n" + 
			".three-column {\r\n" + 
			"	text-align: center;\r\n" + 
			"	font-size: 0;\r\n" + 
			"	padding-top: 10px;\r\n" + 
			"	padding-bottom: 10px;\r\n" + 
			"}\r\n" + 
			".three-column .column {\r\n" + 
			"	width: 100%;\r\n" + 
			"	max-width: 200px;\r\n" + 
			"	display: inline-block;\r\n" + 
			"	vertical-align: top;\r\n" + 
			"}\r\n" + 
			".three-column .contents {\r\n" + 
			"	font-size: 14px;\r\n" + 
			"	text-align: center;\r\n" + 
			"}\r\n" + 
			".three-column img {\r\n" + 
			"	width: 100%;\r\n" + 
			"	max-width: 180px;\r\n" + 
			"	height: auto;\r\n" + 
			"}\r\n" + 
			".three-column .text {\r\n" + 
			"	padding-top: 10px;\r\n" + 
			"}\r\n" + 
			".img-align-vertical img {\r\n" + 
			"	display: inline-block;\r\n" + 
			"	vertical-align: middle;\r\n" + 
			"}\r\n" + 
			"@media only screen and (max-device-width: 480px) {\r\n" + 
			"table[class=hide], img[class=hide], td[class=hide] {\r\n" + 
			"	display: none !important;\r\n" + 
			"}\r\n" + 
			"</style>\r\n" + 
			"<!--[if (gte mso 9)|(IE)]>\r\n" + 
			"	<style type=\"text/css\">\r\n" + 
			"		table {border-collapse: collapse !important;}\r\n" + 
			"	</style>\r\n" + 
			"	<![endif]-->\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#ececec;\">\r\n" + 
			"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#ececec;\">\r\n" + 
			"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#ececec;\" bgcolor=\"#ececec;\">\r\n" + 
			"    <tr>\r\n" + 
			"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \r\n" + 
			"          \r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"\r\n" + 
			"						<table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0\" >\r\n" + 
			"							<tr>\r\n" + 
			"								<td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
			"								<![endif]--> \r\n" + 
			"          \r\n" + 
			"          <!-- ======= start main body ======= -->\r\n" + 
			"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\r\n" + 
			"            <tr>\r\n" + 
			"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\r\n" + 
			"                \r\n" + 
			"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"                        <tbody>\r\n" + 
			"                          <tr>\r\n" + 
			"                            <td align=\"center\"><center>\r\n" + 
			"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
			"                                  <tbody>\r\n" + 
			"                                    <tr>\r\n" + 
			"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\">\r\n" + 
			"                                          <tr>\r\n" + 
			"                                            <td>&nbsp;</td>\r\n" + 
			"                                          </tr>\r\n" + 
			"                                          <tr>\r\n" + 
		//	"                                            <td align=\"center\"><font style=\"font-size:11px; text-decoration:none; color:#474b53; font-family: Verdana, Geneva, sans-serif; text-align:left\"><a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">View in browser</a> | <a href=\"#\" target=\"_blank\" style=\"color:#474b53; text-decoration:none\">Send to a friend </a></font></td>\r\n" + 
			"                                          </tr>\r\n" + 
			"                                          <tr>\r\n" + 
			"                                            <td align=\"center\">&nbsp;</td>\r\n" + 
			"                                          </tr>\r\n" + 
			"                                          <tr>\r\n" + 
			"                                            <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-top-left-radius:10px; border-top-right-radius:10px\"></td>\r\n" + 
			"                                          </tr>\r\n" + 
			"                                        </table></td>\r\n" + 
			"                                    </tr>\r\n" + 
			"                                  </tbody>\r\n" + 
			"                                </table>\r\n" + 
			"                              </center></td>\r\n" + 
			"                          </tr>\r\n" + 
			"                        </tbody>\r\n" + 
			"                      </table></td>\r\n" + 
			"                  </tr>\r\n" + 
			"                </table>\r\n" + 
			"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"                        <tbody>\r\n" + 
			"                          <tr>\r\n" + 
			"                            <td align=\"center\"><center>\r\n" + 
			"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\r\n" + 
			"                                  <tbody>\r\n" + 
			"                                    <tr>\r\n" + 
			"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\r\n" + 
			"                                        \r\n" + 
			"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
			"                                          <tr>\r\n" + 
			"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:center;font-size:0;\"><!--[if (gte mso 9)|(IE)]>\r\n" + 
			"													<table width=\"100%\" style=\"border-spacing:0\" >\r\n" + 
			"													<tr>\r\n" + 
			"													<td width=\"20%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
			"													<![endif]-->\r\n" + 
			"                                              \r\n" + 
			"                                              <div class=\"column\" style=\"width:100%;max-width:410px;display:inline-block;vertical-align:top;\">\r\n" + 
			"                                                <table class=\"contents\" style=\"border-spacing:0; width:100%\"  bgcolor=\"#ffffff\" >\r\n" + 
			"                                                  <tr>\r\n" + 
			"                                                    <td style=\"padding-top:30;padding-bottom:0;padding-right:0;padding-left:0;\" align=\"left\"><a href=\"#\" target=\"_blank\">"
			+ "<img src=\"" 
			+ "https://app.xconecta.com/images/logo_xconecta_black.png" + "\" width=\"400\" alt=\"\" style=\"border-width:0; max-width:400px;height:auto; display:block\" /></a></td>\r\n" + 
			"                                                  </tr>\r\n" + 
			"                                                </table>\r\n" + 
			"                                              </div>\r\n" + 
			"                                              \r\n" + 
			"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"													</td><td width=\"80%\" valign=\"top\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" >\r\n" + 
			"													<![endif]-->\r\n" + 
			"                                              \r\n" + 
			                                              
			"                                              \r\n" + 
			"                                              <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"													</td>\r\n" + 
			"													</tr>\r\n" + 
			"													</table>\r\n" + 
			"													<![endif]--></td>\r\n" + 
			"                                          </tr>\r\n" + 
			                                           
			"                                          <tr>\r\n" + 
			"                                            <td>&nbsp;</td>\r\n" + 
			"                                          </tr>\r\n" + 
			"                                        </table></td>\r\n" + 
			"                                    </tr>\r\n" + 
			"                                  </tbody>\r\n" + 
			"                                </table>\r\n" + 
			"                              </center></td>\r\n" + 
			"                          </tr>\r\n" + 
			"                        </tbody>\r\n" + 
			"                      </table></td>\r\n" + 
			"                  </tr>\r\n" + 
			"                </table>\r\n" + 
			"                \r\n" + 
			"                <!-- ======= end header ======= --> \r\n" + 
			"                \r\n" + 
			"                <!-- ======= start hero image ======= --><!-- ======= end hero image ======= --> \r\n" + 
			"                \r\n" + 
			"                <!-- ======= start hero article ======= -->\r\n" + 
			"                \r\n" + 
			"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0\" bgcolor=\"#FFFFFF\">\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td align=\"left\" style=\"padding:0px 40px 40px 40px\"><p style=\"color:#5b5f65; font-size:28px; text-align:left; font-family: Verdana, Geneva, sans-serif\">"
			+ "Hola " + nombre
			+ " </p>\r\n" + 
			//"                      <p style=\"color:#5b5f65; font-size:16px; text-align:left; font-family: Verdana, Geneva, sans-serif\">Te damos las gracias por registrarte en <strong>MiContaDigital</strong> para nosotros es un compromiso el brindarte un servicio de calidad y estamos seguros que nuestra plataforma digital te ser&aacute; de gran utilidad para controlar tu negocio. <br />\r\n" + 
			"                        <br />\r\n" + 
			//+ aqui va el mensaje
			"                         <br />\r\n" + 
			"                        <br />\r\n" + 
			"                        Da clic en el siguiente enlace para recuperar tu contrase&ntilde;a. <br />\r\n" + 
			"                        <br />\r\n" + 
			"                         </p>\r\n" + 
			"                      \r\n" + 
			"                      <!-- START BUTTON -->\r\n" + 
			"                      \r\n" + 
			"                      <center>\r\n" + 
			"                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\r\n" + 
			"                          <tr>\r\n" + 
			"                            <td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
			"                                <tr>\r\n" + 
			"                                  <td height=\"20\" width=\"100%\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td>\r\n" + 
			"                                </tr>\r\n" + 
			"                              </table>\r\n" + 
			"                              <table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin:0 auto;\">\r\n" + 
			"                                <tbody>\r\n" + 
			"                                  <tr>\r\n" + 
			"                                    <td align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin:0 auto;\">\r\n" + 
			"                                        <tr>\r\n" + 
			"                                          <td width=\"250\" height=\"60\" align=\"center\" bgcolor=\"#2f9780\"><a href=\"https://app.xconecta.com/recupera_password.jsp?id=" + idUsuario + "\" style=\"width:250; display:block; text-decoration:none; border:0; text-align:center; font-weight:bold;font-size:18px; font-family: Arial, sans-serif; color: #ffffff; background:#2f9780\" class=\"button_link\">Recuperar Contrase&ntilde;a</a></td>\r\n" + 
			"                                        </tr>\r\n" + 
			"                                      </table></td>\r\n" + 
			"                                  </tr>\r\n" + 
			"                                </tbody>\r\n" + 
			"                              </table></td>\r\n" + 
			"                          </tr>\r\n" + 
			"                        </table>\r\n" + 
			"                      </center>\r\n" + 
			"                      \r\n" + 
			"                      <!-- END BUTTON --></td>\r\n" + 
			"                  </tr>\r\n" + 
			"                </table>\r\n" + 
			"                \r\n" + 
			"                <!-- ======= end hero article ======= -->  \r\n" + 
			"                \r\n" + 
			"                 <!-- ======= start footer ======= -->\r\n" + 
			"                \r\n" + 
			"               <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"  <tr>\r\n" + 
			"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"  bgcolor=\"#2f9780\">\r\n" + 
			"      <tr>\r\n" + 
			"        <td height=\"40\" align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\">&nbsp;</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Xconecta M&eacute;xico</font></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><table width=\"150\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"          <tr>\r\n" + 
			"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/237854a9-0a06-4f88-a9b8-c36e57e31083.png\" alt=\"facebook\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
			"            <td width=\"34\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/2fb3f578-f70a-41b6-9bbc-f99a174d6456.png\" alt=\"twitter\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
			"            <td width=\"33\" align=\"center\"><a href=\"#\" target=\"_blank\"><img src=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/17c02388-c25e-4eb5-a7cc-8f34458a50ad.png\" alt=\"linkedin\" width=\"32\" height=\"32\" border=\"0\"/></a></td>\r\n" + 
			"          </tr>\r\n" + 
			"        </table></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:10px;padding-left:10px;\"><font style=\"font-size:13px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">Todos los derechos reservados.</font></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td align=\"center\" bgcolor=\"#2f9780\" class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">&nbsp;</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents1\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px; padding:13px; text-align:justify; \"><font style=\"font-size:11px; text-decoration:none; color:#ffffff; font-family: Verdana, Geneva, sans-serif; text-align:center\">" + PRIVACIDAD  + "</font></td>\r\n" + 
			"      </tr>\r\n" + 
			"      </table></td>\r\n" + 
			"  </tr>\r\n" + 
			"  <tr>\r\n" + 
			"    <td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> \r\n" + 
			"      <tr>\r\n" + 
			"        <td height=\"6\" bgcolor=\"#2f9780\" class=\"contents\" style=\"width:100%; border-bottom-left-radius:10px; border-bottom-right-radius:10px\">"
							
			//+ PRIVACIDAD
			
			+ "</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td>&nbsp;</td>\r\n" + 
			"      </tr>\r\n" + 
			"    </table></td>\r\n" + 
			"  </tr>\r\n" + 
			"</table>\r\n" + 
			"\r\n" + 
			"               <!-- ======= end footer ======= --></td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"					</td>\r\n" + 
			"				</tr>\r\n" + 
			"			</table>\r\n" + 
			"			<![endif]--> \r\n" + 
			"        </div></td>\r\n" + 
			"    </tr>\r\n" + 
			"  </table>\r\n" + 
			"</center>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	return mensaje;
}


	
}
