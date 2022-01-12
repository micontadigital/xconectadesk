package MApeados;

public class SIFEIProxy implements MApeados.SIFEI {
  private String _endpoint = null;
  private MApeados.SIFEI sIFEI = null;
  
  public SIFEIProxy() {
    _initSIFEIProxy();
  }
  
  public SIFEIProxy(String endpoint) {
    _endpoint = endpoint;
    _initSIFEIProxy();
  }
  
  private void _initSIFEIProxy() {
    try {
      sIFEI = (new MApeados.SIFEIServiceLocator()).getSIFEIPort();
      if (sIFEI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sIFEI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sIFEI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sIFEI != null)
      ((javax.xml.rpc.Stub)sIFEI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public MApeados.SIFEI getSIFEI() {
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI;
  }
  
  public java.lang.String[] getCFDIAndURL(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getCFDIAndURL(usuario, password, archivoXMLZip, serie, idEquipo);
  }
  
  public java.lang.String[] getCFDIProcesa(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getCFDIProcesa(usuario, password, archivoXMLZip, serie, idEquipo);
  }
  
  public java.lang.String[] getXMLProceso(java.lang.String rfc, java.lang.String pass, long idseguimiento) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getXMLProceso(rfc, pass, idseguimiento);
  }
  
  public byte[] getCFDISign(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getCFDISign(usuario, password, archivoXMLZip, serie, idEquipo);
  }
  
  public java.lang.String cancelaCFDISignature(java.lang.String usuarioSIFEI, java.lang.String passUser, byte[] archivoZIP) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.cancelaCFDISignature(usuarioSIFEI, passUser, archivoZIP);
  }
  
  public java.lang.String getXML(java.lang.String rfc, java.lang.String pass, java.lang.String hash) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getXML(rfc, pass, hash);
  }
  
  public byte[] getCFDI(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getCFDI(usuario, password, archivoXMLZip, serie, idEquipo);
  }
  
  public byte[] getTimbreCFDI(java.lang.String usuario, java.lang.String password, byte[] archivoXMLZip, java.lang.String serie, java.lang.String idEquipo) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.getTimbreCFDI(usuario, password, archivoXMLZip, serie, idEquipo);
  }
  
  public boolean cambiaPassword(java.lang.String usuario, java.lang.String password, java.lang.String newPassword) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.cambiaPassword(usuario, password, newPassword);
  }
  
  public java.lang.String cancelaCFDI(java.lang.String usuarioSIFEI, java.lang.String passUser, java.lang.String rfc, byte[] pfx, java.lang.String passPFX, java.lang.String[] UUIDS) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.cancelaCFDI(usuarioSIFEI, passUser, rfc, pfx, passPFX, UUIDS);
  }
  
  public java.lang.String cancelaCFDISectorPrimario(java.lang.String usuarioSIFEI, java.lang.String passUser, java.lang.String rfc, java.lang.String[] UUIDS) throws java.rmi.RemoteException, MApeados.SifeiException{
    if (sIFEI == null)
      _initSIFEIProxy();
    return sIFEI.cancelaCFDISectorPrimario(usuarioSIFEI, passUser, rfc, UUIDS);
  }
  
  
}