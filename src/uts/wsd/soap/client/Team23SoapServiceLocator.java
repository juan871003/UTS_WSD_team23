/**
 * Team23SoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uts.wsd.soap.client;

public class Team23SoapServiceLocator extends org.apache.axis.client.Service implements uts.wsd.soap.client.Team23SoapService {

    public Team23SoapServiceLocator() {
    }


    public Team23SoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Team23SoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Team23SoapPort
    private java.lang.String Team23SoapPort_address = "http://localhost:8080/Team23/soap/team23soap";

    public java.lang.String getTeam23SoapPortAddress() {
        return Team23SoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Team23SoapPortWSDDServiceName = "Team23SoapPort";

    public java.lang.String getTeam23SoapPortWSDDServiceName() {
        return Team23SoapPortWSDDServiceName;
    }

    public void setTeam23SoapPortWSDDServiceName(java.lang.String name) {
        Team23SoapPortWSDDServiceName = name;
    }

    public uts.wsd.soap.client.Team23Soap getTeam23SoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Team23SoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTeam23SoapPort(endpoint);
    }

    public uts.wsd.soap.client.Team23Soap getTeam23SoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uts.wsd.soap.client.Team23SoapPortBindingStub _stub = new uts.wsd.soap.client.Team23SoapPortBindingStub(portAddress, this);
            _stub.setPortName(getTeam23SoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTeam23SoapPortEndpointAddress(java.lang.String address) {
        Team23SoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uts.wsd.soap.client.Team23Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                uts.wsd.soap.client.Team23SoapPortBindingStub _stub = new uts.wsd.soap.client.Team23SoapPortBindingStub(new java.net.URL(Team23SoapPort_address), this);
                _stub.setPortName(getTeam23SoapPortWSDDServiceName());
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
        if ("Team23SoapPort".equals(inputPortName)) {
            return getTeam23SoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.wsd.uts/", "Team23SoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.wsd.uts/", "Team23SoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Team23SoapPort".equals(portName)) {
            setTeam23SoapPortEndpointAddress(address);
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
