/**
 * Cancelacion_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cancelacion.sifei.service;

public class Cancelacion_ServiceLocator extends org.apache.axis.client.Service implements cancelacion.sifei.service.Cancelacion_Service {

    public Cancelacion_ServiceLocator() {
    }


    public Cancelacion_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Cancelacion_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CancelacionPort
    private java.lang.String CancelacionPort_address = "https://sat.sifei.com.mx:9000/CancelacionSIFEI/Cancelacion";

    public java.lang.String getCancelacionPortAddress() {
        return CancelacionPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CancelacionPortWSDDServiceName = "CancelacionPort";

    public java.lang.String getCancelacionPortWSDDServiceName() {
        return CancelacionPortWSDDServiceName;
    }

    public void setCancelacionPortWSDDServiceName(java.lang.String name) {
        CancelacionPortWSDDServiceName = name;
    }

    public cancelacion.sifei.service.Cancelacion_PortType getCancelacionPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CancelacionPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCancelacionPort(endpoint);
    }

    public cancelacion.sifei.service.Cancelacion_PortType getCancelacionPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cancelacion.sifei.service.CancelacionPortBindingStub _stub = new cancelacion.sifei.service.CancelacionPortBindingStub(portAddress, this);
            _stub.setPortName(getCancelacionPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCancelacionPortEndpointAddress(java.lang.String address) {
        CancelacionPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cancelacion.sifei.service.Cancelacion_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                cancelacion.sifei.service.CancelacionPortBindingStub _stub = new cancelacion.sifei.service.CancelacionPortBindingStub(new java.net.URL(CancelacionPort_address), this);
                _stub.setPortName(getCancelacionPortWSDDServiceName());
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
        if ("CancelacionPort".equals(inputPortName)) {
            return getCancelacionPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.sifei.cancelacion/", "Cancelacion");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.sifei.cancelacion/", "CancelacionPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CancelacionPort".equals(portName)) {
            setCancelacionPortEndpointAddress(address);
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
