package com.finkok.facturacion.cancel;

public class ApplicationProxy implements com.finkok.facturacion.cancel.Application {
  private String _endpoint = null;
  private com.finkok.facturacion.cancel.Application application = null;
  
  public ApplicationProxy() {
    _initApplicationProxy();
  }
  
  public ApplicationProxy(String endpoint) {
    _endpoint = endpoint;
    _initApplicationProxy();
  }
  
  private void _initApplicationProxy() {
    try {
      application = (new com.finkok.facturacion.cancel.CancelSOAPLocator()).getApplication();
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
  
  public com.finkok.facturacion.cancel.Application getApplication() {
    if (application == null)
      _initApplicationProxy();
    return application;
  }
  
  public views.core.soap.services.apps.CancelaCFDResult cancel_signature(byte[] xml, java.lang.String username, java.lang.String password, java.lang.Boolean store_pending) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.cancel_signature(xml, username, password, store_pending);
  }
  
  public views.core.soap.services.apps.QueryPendingResult query_pending_cancellation(java.lang.String username, java.lang.String password, java.lang.String uuid) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.query_pending_cancellation(username, password, uuid);
  }
  
  public views.core.soap.services.apps.CancelaCFDResult sign_cancel(views.core.soap.services.apps.UUIDS UUIDS, java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, java.lang.String serial, java.lang.Boolean store_pending) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.sign_cancel(UUIDS, username, password, taxpayer_id, serial, store_pending);
  }
  
  public views.core.soap.services.apps.ReceiptResult get_receipt(java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, java.lang.String uuid, java.lang.String type) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.get_receipt(username, password, taxpayer_id, uuid, type);
  }
  
  public views.core.soap.services.apps.CancelaCFDResult cancel(views.core.soap.services.apps.UUIDS UUIDS, java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, byte[] cer, byte[] key, java.lang.Boolean store_pending) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.cancel(UUIDS, username, password, taxpayer_id, cer, key, store_pending);
  }
  
  public views.core.soap.services.apps.CancelaCFDResult out_cancel(byte[] xml, java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, byte[] cer, byte[] key, java.lang.Boolean store_pending) throws java.rmi.RemoteException{
    if (application == null)
      _initApplicationProxy();
    return application.out_cancel(xml, username, password, taxpayer_id, cer, key, store_pending);
  }
  
  
}