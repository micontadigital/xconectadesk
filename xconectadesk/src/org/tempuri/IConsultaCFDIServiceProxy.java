package org.tempuri;

public class IConsultaCFDIServiceProxy implements org.tempuri.IConsultaCFDIService {
  private String _endpoint = null;
  private org.tempuri.IConsultaCFDIService iConsultaCFDIService = null;
  
  public IConsultaCFDIServiceProxy() {
    _initIConsultaCFDIServiceProxy();
  }
  
  public IConsultaCFDIServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIConsultaCFDIServiceProxy();
  }
  
  private void _initIConsultaCFDIServiceProxy() {
    try {
      iConsultaCFDIService = (new org.tempuri.ConsultaCFDIServiceLocator()).getBasicHttpBinding_IConsultaCFDIService();
      if (iConsultaCFDIService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iConsultaCFDIService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iConsultaCFDIService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iConsultaCFDIService != null)
      ((javax.xml.rpc.Stub)iConsultaCFDIService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IConsultaCFDIService getIConsultaCFDIService() {
    if (iConsultaCFDIService == null)
      _initIConsultaCFDIServiceProxy();
    return iConsultaCFDIService;
  }
  
  public org.datacontract.schemas._2004._07.Sat_Cfdi_Negocio_ConsultaCfdi_Servicio.Acuse consulta(java.lang.String expresionImpresa) throws java.rmi.RemoteException{
    if (iConsultaCFDIService == null)
      _initIConsultaCFDIServiceProxy();
    return iConsultaCFDIService.consulta(expresionImpresa);
  }
  
  
}