/*
 *  Copyright 2011 BigData.mx
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package mx.gob.sat.cfd._3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.codec.binary.Base64;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import mx.gob.sat.cfd._3.Comprobante.Complemento;
import mx.gob.sat.implocal.ImpuestosLocales;




public final class CFDv33 implements CFDI {

  private static final String XSLT = "/xslt/cadenaoriginal_3_3.xslt";
	
  private static final String[] XSD = new String[] {
		  "/xsd/v33/cfdv33.xsd",
		  "/xsd/v33/TimbreFiscalDigitalv11.xsd",
		  "/xsd/v33/tdCFDI.xsd",
		  "/xsd/v33/catCFDI.xsd",
		  "/xsd/v33/Pagos10.xsd",
		  "/xsd/v33/catPagos.xsd",
		  "/xsd/v33/implocal.xsd"
  		};
  
  private static final String XML_HEADER = 
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

  private static final String BASE_CONTEXT = "mx.gob.sat.cfd._3";
  
  private final static Joiner JOINER = Joiner.on(':');
      
  private final JAXBContext context;

  public static final ImmutableMap<String, String> PREFIXES = 
    ImmutableMap.of("http://www.w3.org/2001/XMLSchema-instance","xsi", 
                    "http://www.sat.gob.mx/cfd/3", "cfdi", 
                    "http://www.sat.gob.mx/TimbreFiscalDigital", "tfd",
                    "http://www.sat.gob.mx/Pagos", "pago10",
                    "http://www.sat.gob.mx/implocal", "implocal");

  private final Map<String, String> localPrefixes = Maps.newHashMap(PREFIXES);
  
  private TransformerFactory tf;

  final Comprobante document;

  public CFDv33(InputStream in, String... contexts) throws Exception {
    this.context = getContext(contexts);
    this.document = load(in);
  }

  public CFDv33(Comprobante comprobante, String... contexts) throws Exception {
    this.context = getContext(contexts);
    this.document = copy(comprobante);
       
  }

  public void addNamespace(String uri, String prefix) {
      localPrefixes.put(uri, prefix);
  }

  public void setTransformerFactory(TransformerFactory tf) {
      this.tf = tf;
      tf.setURIResolver(new URIResolverImpl());
  }

  public void sellar(PrivateKey key, X509Certificate cert) throws Exception {
      cert.checkValidity();
      byte[] bytes = cert.getEncoded();
      Base64 b64 = new Base64(-1);
      String certStr = b64.encodeToString(bytes);
      document.setCertificado(certStr);
      BigInteger bi = cert.getSerialNumber();
      document.setNoCertificado(new String(bi.toByteArray()));
      
      String signature = getSignature(key,cert);
      
      document.setSello(signature);
      
      
  }

  public Comprobante sellarComprobante(PrivateKey key, X509Certificate cert) throws Exception {
      sellar(key, cert);
      return doGetComprobante();
  }

  public void validar() throws Exception {
      validar(null);
  }

  public void validar(ErrorHandler handler) throws Exception {
      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Source[] schemas = new Source[XSD.length];
      for (int i = 0; i < XSD.length; i++) {
          schemas[i] = new StreamSource(getClass().getResourceAsStream(XSD[i]));
      }
      Schema schema = sf.newSchema(schemas);
      Validator validator = schema.newValidator();
      if (handler != null) {
          validator.setErrorHandler(handler);
      }
      validator.validate(new JAXBSource(context, document));
  }

  public void verificar() throws Exception {
      String certStr = document.getCertificado();
      Base64 b64 = new Base64();
      byte[] cbs = b64.decode(certStr);

      X509Certificate cert = KeyLoaderFactory.createInstance(
              KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
              new ByteArrayInputStream(cbs)
      ).getKey();

      String sigStr = document.getSello();
      
      System.out.println("verificar sello " + sigStr);
      
      byte[] signature = b64.decode(sigStr);
      byte[] bytes = getOriginalBytes();
      
      for (byte b:bytes)System.out.print((char)b);
	  System.out.println("\n");
      
      Signature sig = Signature.getInstance("SHA256withRSA");
      sig.initVerify(cert);
      sig.update(bytes);
      boolean bool = sig.verify(signature);
      if (!bool) {
          throw new Exception("Invalid signature");
      }
  }

  //Verifica textualmente el XML con el XSD (Funciona cuando queremos validar un XML que NO fue creado con esta librer�a
  public void verificar(InputStream in) throws Exception {
      String certStr = document.getCertificado();
      Base64 b64 = new Base64();
      byte[] cbs = b64.decode(certStr);
      X509Certificate cert = KeyLoaderFactory.createInstance(
              KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
              new ByteArrayInputStream(cbs)
      ).getKey();
      System.out.println(cert.getSerialNumber());
       String sigStr = document.getSello();
      byte[] signature = b64.decode(sigStr);
      byte[] bytes = getOriginalBytes(in);
      Signature sig = Signature.getInstance("SHA256WithRSA");
      sig.initVerify(cert);
      sig.update(bytes);
      boolean bool = sig.verify(signature);
      if (!bool) {
          throw new Exception("Invalid signature.");
      }
  }

  public void guardar(OutputStream out) throws Exception {
      Marshaller m = context.createMarshaller();
      m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapperImpl(localPrefixes));
      m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, getSchemaLocation());
      byte[] xmlHeaderBytes = XML_HEADER.getBytes("UTF8");

      out.write(xmlHeaderBytes);

      m.marshal(document, out);
  }

  //Se implement� este m�todo para que agregue los esquemas y los namespace's de manera autom�tica (solo hay que enviar los contexts en el constructor)
  //Se deben agregar todos los complementos en todas sus versiones (tambien a todas las versiones de CFDi seg�n sus complementos)
  private String getSchemaLocation() throws Exception {
      List<String> contexts = new ArrayList<>();
      String schema = "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd";
      
      System.out.println("SchemaLocation");
      
      if (document != null && document.getComplemento() != null && !document.getComplemento().isEmpty()) {
    	  
    	  System.out.println("SchemaLocation complemento no nulo");
    	  
          for (int i = 0 ; i < document.getComplemento().get(0).getAny().size() ; i++) {
        	  
        	  System.out.println("SchemaLocation complemento dentro for");
        	  
              if ((Object)document.getComplemento().get(0).getAny().get(i) instanceof mx.gob.sat.pagos.Pagos) {
            	  
            	  System.out.println("SchemaLocation complemento pagos");
            	  
                  schema += " http://www.sat.gob.mx/Pagos http://www.sat.gob.mx/sitio_internet/cfd/Pagos/Pagos10.xsd";
                  addNamespace("http://www.sat.gob.mx/Pagos", "pago10");
                  
              
              } else if ((Object)document.getComplemento().get(0).getAny().get(i) instanceof ImpuestosLocales) {
            	  
            	  System.out.println("SchemaLocation complemento impuesto local");
            	  
                  schema += " http://www.sat.gob.mx/implocal http://www.sat.gob.mx/sitio_internet/cfd/implocal/implocal.xsd";
                  addNamespace("http://www.sat.gob.mx/implocal", "implocal");
             
                  
             
              } else {
                  System.out.println("El complemento " + (Object)document.getComplemento().get(i) + " aún no ha sido declarado.");
              }
          }
          if (!contexts.isEmpty()) {
              getContext(contexts.toArray(new String[contexts.size()]));
          }
      }
      
      return schema;
  }

  public String getCadenaOriginal() throws Exception {
      byte[] bytes = getOriginalBytes();
      return new String(bytes, "UTF8");
  }

  public static Comprobante newComprobante(InputStream in) throws Exception {
      return load(in);
  }

  byte[] getOriginalBytes() throws Exception {
      JAXBSource in = new JAXBSource(context, document);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Result out = new StreamResult(baos);
      TransformerFactory factory = tf;
      if (factory == null) {
          factory = TransformerFactory.newInstance();
          factory.setURIResolver(new URIResolverImpl());
      }
      Transformer transformer = factory
              .newTransformer(new StreamSource(getClass().getResourceAsStream(XSLT)));
      transformer.transform(in, out);
      return baos.toByteArray();
  }

  //Funciona en conjunto con: verificar(InputStream in)
  byte[] getOriginalBytes(InputStream in) throws Exception {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Source source = new StreamSource(in);
      Result out = new StreamResult(baos);
      TransformerFactory factory = tf;
      if (factory == null) {
          factory = TransformerFactory.newInstance();
          factory.setURIResolver(new URIResolverImpl());
      }
      Transformer transformer = factory
              .newTransformer(new StreamSource(getClass().getResourceAsStream(XSLT)));
      transformer.transform(source, out);
      in.close();
      return baos.toByteArray();
  }

  String getSignature(PrivateKey key, X509Certificate cert) throws Exception {
	  byte[] bytes = getOriginalBytes();
	  
	  for (byte b:bytes)System.out.print((char)b);
	  
      Signature sig = Signature.getInstance("SHA256withRSA");
      sig.initSign(key);
      sig.update(bytes);
      byte[] signed = sig.sign();
  
      Base64 b64 = new Base64(-1);
      String signature = b64.encodeToString(signed);
      
     
      //System.out.println("BnMfu/MIofwlji2Tfi413LH4od7JM456mjbBK/52nDHXrfSH9vF492ehsXO2opDjuAhHgWVlq/kUlDgZR3pdIMSziCm7JWAQYMCeW6zRwMvwKu4l8Uqj0hdEJHaextluzBQ/yM+rfHBCWyj5sFVjKAdkXMgC4P3wQkmSlagy/7VZPb/WGATSpLXOCGrS4kqt0IDY2ATpLi1ogxkkNb3JLf651ll9aDGVIE8UodHjwayNp9cVqrNUowIWZ3nkZDV1Pf2S3zDrj+vQpmXrDTwxum+0vGimjDtRBdbkTEWq/KCO3qFHvIbaxq2+ZA35GO+6XZBoawg0073OPGJvGK+1RQ==");
      
      System.out.println(signature);

	  //String test = "";
	  /*
	  Openssl.cerPem();
	  Openssl.cerDer();
	  Openssl.keyPem();
	  Openssl.cadena(getOriginalBytes());
	  test = Openssl.sello();
      */
      return signature;
  }

  public ComprobanteBase getComprobante() throws Exception {
      return new CFDv32ComprobanteBase(doGetComprobante());
  }

  Comprobante doGetComprobante() throws Exception {
      return copy(document);
  }

  // Defensive deep-copy
  private Comprobante copy(Comprobante comprobante) throws Exception {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.newDocument();
      Marshaller m = context.createMarshaller();
      m.marshal(comprobante, doc);
      Unmarshaller u = context.createUnmarshaller();
      return (Comprobante) u.unmarshal(doc);

  }

  public static final class CFDv32ComprobanteBase implements ComprobanteBase {

      private final Comprobante document;

      public CFDv32ComprobanteBase(Comprobante document) {
          this.document = document;
      }

      @Override
      public boolean hasComplemento() {
          return document.getComplemento() != null;
      }

      @Override
      public List<Object> getComplementoGetAny() {
    	  return ((Complemento) document.getComplemento()).getAny();
      }

      @Override
      public String getSello() {
          return document.getSello();
      }

      @Override
      public void setComplemento(Element element) {
    	  ObjectFactory of = new ObjectFactory();
          Comprobante.Complemento comp = of.createComprobanteComplemento();
          List<Object> list = comp.getAny(); 
          list.add(element);
          document.setComplemento(comp);
      }

      @Override
      public Object getComprobante() {
          return document;
      }
  }

  private static JAXBContext getContext(String[] contexts) throws Exception {
      List<String> ctx = Lists.asList(BASE_CONTEXT, contexts);
      return JAXBContext.newInstance(JOINER.join(ctx));
  }

  private static Comprobante load(InputStream source, String... contexts) throws Exception {
      JAXBContext context = getContext(contexts);
      try {
          Unmarshaller u = context.createUnmarshaller();
          return (Comprobante) u.unmarshal(source);
      } finally {
          source.close();
      }
  }

  static void dump(String title, byte[] bytes, PrintStream out) {
      out.printf("%s: ", title);
      for (byte b : bytes) {
          out.printf("%02x ", b & 0xff);
      }
      out.println();
  }

}
