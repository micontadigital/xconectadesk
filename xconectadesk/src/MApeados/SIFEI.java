/**
 * SIFEI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package MApeados;

public interface SIFEI extends java.rmi.Remote {
    public java.lang.String[] getCFDIAndURL(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException;
    public java.lang.String[] getCFDIProcesa(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException;
    public java.lang.String[] getXMLProceso(java.lang.String rfc, java.lang.String pass, long idseguimiento) throws java.rmi.RemoteException, MApeados.SifeiException;
    public byte[] getCFDISign(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException;
    public java.lang.String cancelaCFDISignature(java.lang.String usuarioSIFEI, java.lang.String passUser, byte[] archivoZIP) throws java.rmi.RemoteException, MApeados.SifeiException;
    public java.lang.String getXML(java.lang.String rfc, java.lang.String pass, java.lang.String hash) throws java.rmi.RemoteException, MApeados.SifeiException;
    public byte[] getCFDI(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException;
    public byte[] getTimbreCFDI(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException;
    public boolean cambiaPassword(java.lang.String usuario, java.lang.String password, java.lang.String newPassword) throws java.rmi.RemoteException, MApeados.SifeiException;
    public java.lang.String cancelaCFDI(java.lang.String usuarioSIFEI, java.lang.String passUser, java.lang.String rfc, byte[] pfx, java.lang.String passPFX, java.lang.String[] UUIDS) throws java.rmi.RemoteException, MApeados.SifeiException;
    public java.lang.String cancelaCFDISectorPrimario(java.lang.String usuarioSIFEI, java.lang.String passUser, java.lang.String rfc, java.lang.String[] UUIDS) throws java.rmi.RemoteException, MApeados.SifeiException;
}
