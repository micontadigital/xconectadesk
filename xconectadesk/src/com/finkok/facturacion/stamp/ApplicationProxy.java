package com.finkok.facturacion.stamp;

public class ApplicationProxy implements com.finkok.facturacion.stamp.Application {
  private String _endpoint = null;
  private com.finkok.facturacion.stamp.Application application = null;
  
  public ApplicationProxy() {
    _initApplicationProxy();
  }
  
  public ApplicationProxy(String endpoint) {
    _endpoint = endpoint;
    _initApplicationProxy();
  }
  
  private void _initApplicationProxy() {
    try {
      application = (new com.finkok.facturacion.stamp.StampSOAPLocator()).getApplication();
      if (application != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)application)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)application)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (application != null)
      ((javax.xml.rpc.Stub)application)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.finkok.facturacion.stamp.Application getApplication() {
    if (application == null)
      _initApplicationProxy();
    return application;
  }
  
  public views.core.soap.services.apps.AcuseRecepcionCFDI stamp(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.stamp(xml, username, password);
  }
  
  public views.core.soap.services.apps.AcuseRecepcionCFDI sign_stamp(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.sign_stamp(xml, username, password);
  }
  
  public views.core.soap.services.apps.AcuseRecepcionCFDI stamped(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.stamped(xml, username, password);
  }
  
  public views.core.soap.services.apps.AcuseRecepcionCFDI quick_stamp(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.quick_stamp(xml, username, password);
  }
  
  public views.core.soap.services.apps.QueryPendingResult query_pending(java.lang.String username, java.lang.String password, java.lang.String uuid) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.query_pending(username, password, uuid);
  }
  
  
}