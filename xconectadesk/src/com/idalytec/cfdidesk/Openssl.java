package com.idalytec.cfdidesk;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Session;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

import com.sun.mail.handlers.image_gif;

/**
 *
 * @author AKMH 
 * anakaren.monroyh@gmail.com
 * 
 * Instalar openssl y configurar las variables de entorno / ambiente segun el sistema operativo
 * Cambiar rutas 
 * 
 */

public class Openssl {

    private static String pass = "12345678a";
    private static String key = "";
    private static String cer = "";
    private static String rutaDestino = IP.getDir();
    private static String c = "";
    private static String rfcUsuario = "";
    
    //private static String path = "C:\\idalytec.com\\OpenSSL-Win64\\bin\\openssl.exe";
    
    private static String path = "openssl";
    
    
    
    static Connection conexion;
    
    
    public Openssl(Connection conexion) {
        super();
       
        //path = "C:\\idalytec.com\\OpenSSL-Win64\\bin\\";
    }

    public static String getRutaDestino() {
        return rutaDestino;
    }

    public static void creaCerPem() {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        try {
        	String comando = path + " x509 -inform DER -outform PEM -in " + cer + " -pubkey -out " + rutaDestino
                    + "cer" + rfcUsuario + ".pem";
        	
        	System.out.println(comando);
            exec = runtime.exec(comando);
            
            
            
            exec.waitFor();
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }
    
    public static void creaCerPem2() {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        try {
        	String comando = path + " x509 -inform DER -in " + cer + " -out " + rutaDestino
                    + "cer" + rfcUsuario + ".pem";
        	
        	System.out.println(comando);
            exec = runtime.exec(comando);
            
            
            
            exec.waitFor();
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }

    public static void creaKeyPem() {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        
        String comando = path + " pkcs8 -inform DER -in " + key + " -passin pass:" + pass + " -out "
                + rutaDestino + "key" + rfcUsuario + ".pem";
        
        System.out.println(comando);
        
        try {
            exec = runtime.exec(comando);
            
            exec.waitFor();
            
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }

    }

    public static void creaKeyEncriptado() {
    	//im = new Imprimir();
    	c = "Acceso2014.";
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        
        String comando = path + " rsa -in " + rutaDestino + "key" + rfcUsuario + ".pem -des3 -out " + rutaDestino
                + "key" + rfcUsuario + ".enc -passout pass:" + getPass();
        
        System.out.println(comando);
        
        try {
            exec = runtime.exec(comando);
            
            exec.waitFor();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }
    
    
    public static void creaPFX(String rfc,  String ph) {
    	//im = new Imprimir();
    	//c = "Acceso2014.";
    	//c = "12345678a";
    	
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        
        rfcUsuario = rfc;
        pass = ph;
        
        String comando = path + " pkcs12 -export -in " + rutaDestino + "cer" + rfcUsuario + ".pem -inkey " + rutaDestino + "key" + rfcUsuario + ".pem   -out " + rutaDestino
                + "pfx" + rfcUsuario + ".pfx -passout pass:" + getPass();
        
        System.out.println(comando);
        
        try {
            exec = runtime.exec(comando);
            
            exec.waitFor();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }

    public static String leeArchivo(String ruta) {
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
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    public static void deleteFiles() {
        File cerPem = new File(rutaDestino + "cer" + rfcUsuario + ".pem");
        cerPem.delete();
        File keyPem = new File(rutaDestino + "key" + rfcUsuario + ".pem");
        keyPem.delete();
        File keyEnc = new File(rutaDestino + "key" + rfcUsuario + ".enc");
        keyEnc.delete();
        
        File keyF = new File(key);
        keyF.delete();
        File cerF = new File(cer);
        cerF.delete();
    }
    
    public static void datos(String rfc, String serie, Connection conexion){
    	Statement st =null;
		ResultSet rs =null;
		
		rfcUsuario = rfc;
		
		System.out.println("Comienza datos");
		
		InputStream isKey = null;
		InputStream isCer = null;
		String password="";
		OutputStream outputStreamKey = null;
		OutputStream outputStreamCer = null;
		String sql;
		String idSucursal="";
		try {
			st = conexion.createStatement();
			
			sql = "select llave,certificado,password FROM pv_sat";
			rs = st.executeQuery(sql);

			

			Blob bKey = null;
			Blob bCer = null;
			while (rs.next()) {
				bKey = rs.getBlob(1);
				bCer = rs.getBlob(2);
				password = rs.getString(3);

			}
			
			System.out.println("password-" + password);
			
			String secretKey = ".cfdiAPP*";
			String base64EncryptedString = "";

			byte[] message = Base64.decodeBase64(password.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey sctKey = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, sctKey);

			byte[] plainText = decipher.doFinal(message);
			base64EncryptedString = new String(plainText, "UTF-8");
			
			pass = base64EncryptedString;
			
			System.out.println("pass-" + base64EncryptedString);
			
	        isKey = bKey.getBinaryStream();
	        isCer = bCer.getBinaryStream();
	       

	        File keyFile = new File(IP.getDir() + "key" + rfcUsuario + ".key");
			File cerFile = new File(IP.getDir() + "cer" + rfcUsuario + ".cer");

			OutputStream outStreamKey = new FileOutputStream(keyFile);
			byte[] bufKey = new byte[1024];// Actualizado me olvide del 1024
			int lenKey;
			while ((lenKey = isKey.read(bufKey)) > 0) {
				outStreamKey.write(bufKey, 0, lenKey);
			}
			outStreamKey.close();
			isKey.close();

			OutputStream outStreamCer = new FileOutputStream(cerFile);
			byte[] bufCer = new byte[1024];// Actualizado me olvide del 1024
			int lenCer;
			while ((lenCer = isCer.read(bufCer)) > 0) {
				outStreamCer.write(bufCer, 0, lenCer);
			}
			outStreamCer.close();
			isCer.close();


	    	key = keyFile.getPath();
	    	cer = cerFile.getPath();
	    	
	    	
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			
		} catch (BadPaddingException e) {
			e.printStackTrace();
	    	
		} catch (InvalidKeyException e) {
			e.printStackTrace();
	    	
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
	    	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
	        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			if (outputStreamKey != null) {
				try {
					// outputStream.flush();
					outputStreamKey.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (outputStreamCer != null) {
				try {
					// outputStream.flush();
					outputStreamCer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			try {
				if (rs!=null)rs.close();
				if (st!=null)st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
    }
    public static String getPass() {
    	return pass;
    }
}
