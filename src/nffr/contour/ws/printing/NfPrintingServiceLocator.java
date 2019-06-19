/**
 * NfPrintingServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nffr.contour.ws.printing;

public class NfPrintingServiceLocator extends org.apache.axis.client.Service implements nffr.contour.ws.printing.NfPrintingService {

    public NfPrintingServiceLocator() {
    }


    public NfPrintingServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NfPrintingServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NfPrintingServiceSOAP
    private java.lang.String NfPrintingServiceSOAP_address = "http://ws.contour.nffr/";

    public java.lang.String getNfPrintingServiceSOAPAddress() {
        return NfPrintingServiceSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfPrintingServiceSOAPWSDDServiceName = "NfPrintingServiceSOAP";

    public java.lang.String getNfPrintingServiceSOAPWSDDServiceName() {
        return NfPrintingServiceSOAPWSDDServiceName;
    }

    public void setNfPrintingServiceSOAPWSDDServiceName(java.lang.String name) {
        NfPrintingServiceSOAPWSDDServiceName = name;
    }

    public nffr.contour.ws.printing.NfPrintingServicePT getNfPrintingServiceSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfPrintingServiceSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfPrintingServiceSOAP(endpoint);
    }

    public nffr.contour.ws.printing.NfPrintingServicePT getNfPrintingServiceSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            nffr.contour.ws.printing.NfPrintingServiceSOAPHttpStub _stub = new nffr.contour.ws.printing.NfPrintingServiceSOAPHttpStub(portAddress, this);
            _stub.setPortName(getNfPrintingServiceSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfPrintingServiceSOAPEndpointAddress(java.lang.String address) {
        NfPrintingServiceSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (nffr.contour.ws.printing.NfPrintingServicePT.class.isAssignableFrom(serviceEndpointInterface)) {
                nffr.contour.ws.printing.NfPrintingServiceSOAPHttpStub _stub = new nffr.contour.ws.printing.NfPrintingServiceSOAPHttpStub(new java.net.URL(NfPrintingServiceSOAP_address), this);
                _stub.setPortName(getNfPrintingServiceSOAPWSDDServiceName());
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
        if ("NfPrintingServiceSOAP".equals(inputPortName)) {
            return getNfPrintingServiceSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingServiceSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NfPrintingServiceSOAP".equals(portName)) {
            setNfPrintingServiceSOAPEndpointAddress(address);
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
