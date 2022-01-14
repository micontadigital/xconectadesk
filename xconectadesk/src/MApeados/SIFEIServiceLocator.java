/**
 * SIFEIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package MApeados;

public class SIFEIServiceLocator extends org.apache.axis.client.Service implements MApeados.SIFEIService {

    public SIFEIServiceLocator() {
    }


    public SIFEIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SIFEIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SIFEIPort

    //private java.lang.String SIFEIPort_address = "https://sat.sifei.com.mx:8443/SIFEI/SIFEI";
    
    private java.lang.String SIFEIPort_address = "https://devcfdi.sifei.com.mx:8443/SIFEI33/SIFEI";   //desarrollo


    public java.lang.String getSIFEIPortAddress() {
        return SIFEIPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SIFEIPortWSDDServiceName = "SIFEIPort";

    public java.lang.String getSIFEIPortWSDDServiceName() {
        return SIFEIPortWSDDServiceName;
    }

    public void setSIFEIPortWSDDServiceName(java.lang.String name) {
        SIFEIPortWSDDServiceName = name;
    }

    public MApeados.SIFEI getSIFEIPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SIFEIPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSIFEIPort(endpoint);
    }

    public MApeados.SIFEI getSIFEIPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            MApeados.SIFEIPortBindingStub _stub = new MApeados.SIFEIPortBindingStub(portAddress, this);
            _stub.setPortName(getSIFEIPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSIFEIPortEndpointAddress(java.lang.String address) {
        SIFEIPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (MApeados.SIFEI.class.isAssignableFrom(serviceEndpointInterface)) {
                MApeados.SIFEIPortBindingStub _stub = new MApeados.SIFEIPortBindingStub(new java.net.URL(SIFEIPort_address), this);
                _stub.setPortName(getSIFEIPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SIFEIPort".equals(inputPortName)) {
            return getSIFEIPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://MApeados/", "SIFEIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://MApeados/", "SIFEIPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SIFEIPort".equals(portName)) {
            setSIFEIPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
