/**
 * Cancelacion_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cancelacion.sifei.service;

public interface Cancelacion_PortType extends java.rmi.Remote {
    public java.lang.String peticionesPendientes(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String rfcReceptor) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault;
    public java.lang.String procesarRespuesta(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String rfcReceptor, cancelacion.sifei.service.Folios[] folios, byte[] pfx, java.lang.String passwordPfx) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault;
    public java.lang.String cfdiRelacionado(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String rfcReceptor, java.lang.String rfcEmisor, java.lang.String uuid, byte[] pfx, java.lang.String passwordPfx) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault;
    public java.lang.String consultaSATCFDI(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String id, java.lang.String re, java.lang.String rr, java.lang.String tt, java.lang.String fe) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault;
    public java.lang.String cancelaCFDI(java.lang.String usuarioSIFEI, java.lang.String passwordSifei, java.lang.String rfcEmisor, byte[] pfx, java.lang.String passwordPfx, java.lang.String[] uuids) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault;
}
