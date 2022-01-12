package cancelacion.sifei.service;

public class CancelacionProxy implements cancelacion.sifei.service.Cancelacion_PortType {
  private String _endpoint = null;
  private cancelacion.sifei.service.Cancelacion_PortType cancelacion_PortType = null;
  
  public CancelacionProxy() {
    _initCancelacionProxy();
  }
  
  public CancelacionProxy(String endpoint) {
    _endpoint = endpoint;
    _initCancelacionProxy();
  }
  
  private void _initCancelacionProxy() {
    try {
      cancelacion_PortType = (new cancelacion.sifei.service.Cancelacion_ServiceLocator()).getCancelacionPort();
      if (cancelacion_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cancelacion_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cancelacion_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cancelacion_PortType != null)
      ((javax.xml.rpc.Stub)cancelacion_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cancelacion.sifei.service.Cancelacion_PortType getCancelacion_PortType() {
    if (cancelacion_PortType == null)
      _initCancelacionProxy();
    return cancelacion_PortType;
  }
  
  public java.lang.String peticionesPendientes(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String rfcReceptor) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault{
    if (cancelacion_PortType == null)
      _initCancelacionProxy();
    return cancelacion_PortType.peticionesPendientes(usuarioSIFEI, passwordSIFEI, rfcReceptor);
  }
  
  public java.lang.String procesarRespuesta(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String rfcReceptor, cancelacion.sifei.service.Folios[] folios, byte[] pfx, java.lang.String passwordPfx) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault{
    if (cancelacion_PortType == null)
      _initCancelacionProxy();
    return cancelacion_PortType.procesarRespuesta(usuarioSIFEI, passwordSIFEI, rfcReceptor, folios, pfx, passwordPfx);
  }
  
  public java.lang.String cfdiRelacionado(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String rfcReceptor, java.lang.String rfcEmisor, java.lang.String uuid, byte[] pfx, java.lang.String passwordPfx) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault{
    if (cancelacion_PortType == null)
      _initCancelacionProxy();
    return cancelacion_PortType.cfdiRelacionado(usuarioSIFEI, passwordSIFEI, rfcReceptor, rfcEmisor, uuid, pfx, passwordPfx);
  }
  
  public java.lang.String consultaSATCFDI(java.lang.String usuarioSIFEI, java.lang.String passwordSIFEI, java.lang.String id, java.lang.String re, java.lang.String rr, java.lang.String tt, java.lang.String fe) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault{
    if (cancelacion_PortType == null)
      _initCancelacionProxy();
    return cancelacion_PortType.consultaSATCFDI(usuarioSIFEI, passwordSIFEI, id, re, rr, tt, fe);
  }
  
  public java.lang.String cancelaCFDI(java.lang.String usuarioSIFEI, java.lang.String passwordSifei, java.lang.String rfcEmisor, byte[] pfx, java.lang.String passwordPfx, java.lang.String[] uuids) throws java.rmi.RemoteException, cancelacion.sifei.service.SifeiServiceFault{
    if (cancelacion_PortType == null)
      _initCancelacionProxy();
    return cancelacion_PortType.cancelaCFDI(usuarioSIFEI, passwordSifei, rfcEmisor, pfx, passwordPfx, uuids);
  }
  
  
}