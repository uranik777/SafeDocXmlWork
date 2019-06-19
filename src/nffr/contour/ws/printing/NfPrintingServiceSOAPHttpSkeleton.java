/**
 * NfPrintingServiceSOAPHttpSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nffr.contour.ws.printing;

public class NfPrintingServiceSOAPHttpSkeleton implements nffr.contour.ws.printing.NfPrintingServicePT, org.apache.axis.wsdl.Skeleton {
    private nffr.contour.ws.printing.NfPrintingServicePT impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessageType"), nffr.contour.ws.printing.NfPrintingMessageType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("printLocallyOp", _params, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "PrintLocallyOp"));
        _oper.setSoapAction("urn:nfprinting/PrintLocally");
        _myOperationsList.add(_oper);
        if (_myOperations.get("printLocallyOp") == null) {
            _myOperations.put("printLocallyOp", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("printLocallyOp")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessageType"), nffr.contour.ws.printing.NfPrintingMessageType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("previewOp", _params, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "PreviewOp"));
        _oper.setSoapAction("urn:nfprinting/Preview");
        _myOperationsList.add(_oper);
        if (_myOperations.get("previewOp") == null) {
            _myOperations.put("previewOp", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("previewOp")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessageType"), nffr.contour.ws.printing.NfPrintingMessageType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("printNowOp", _params, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "PrintNowOp"));
        _oper.setSoapAction("urn:nfprinting/PrintNow");
        _myOperationsList.add(_oper);
        if (_myOperations.get("printNowOp") == null) {
            _myOperations.put("printNowOp", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("printNowOp")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "BatchPrintingMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "BatchPrintingMessageType"), nffr.contour.ws.printing.BatchPrintingMessageType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("batchPrintingOp", _params, new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "BatchPrintingResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "BatchPrintingOp"));
        _oper.setSoapAction("urn:nfprinting/BatchPrinting");
        _myOperationsList.add(_oper);
        if (_myOperations.get("batchPrintingOp") == null) {
            _myOperations.put("batchPrintingOp", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("batchPrintingOp")).add(_oper);
    }

    public NfPrintingServiceSOAPHttpSkeleton() {
        this.impl = new nffr.contour.ws.printing.NfPrintingServiceSOAPHttpImpl();
    }

    public NfPrintingServiceSOAPHttpSkeleton(nffr.contour.ws.printing.NfPrintingServicePT impl) {
        this.impl = impl;
    }
    public nffr.contour.ws.printing.NfPrintingResponseType printLocallyOp(nffr.contour.ws.printing.NfPrintingMessageType RQ) throws java.rmi.RemoteException
    {
        nffr.contour.ws.printing.NfPrintingResponseType ret = impl.printLocallyOp(RQ);
        return ret;
    }

    public nffr.contour.ws.printing.NfPrintingResponseType previewOp(nffr.contour.ws.printing.NfPrintingMessageType RQ) throws java.rmi.RemoteException
    {
        nffr.contour.ws.printing.NfPrintingResponseType ret = impl.previewOp(RQ);
        return ret;
    }

    public nffr.contour.ws.printing.NfPrintingResponseType printNowOp(nffr.contour.ws.printing.NfPrintingMessageType RQ) throws java.rmi.RemoteException
    {
        nffr.contour.ws.printing.NfPrintingResponseType ret = impl.printNowOp(RQ);
        return ret;
    }

    public nffr.contour.ws.printing.NfPrintingResponseType batchPrintingOp(nffr.contour.ws.printing.BatchPrintingMessageType RQ) throws java.rmi.RemoteException
    {
        nffr.contour.ws.printing.NfPrintingResponseType ret = impl.batchPrintingOp(RQ);
        return ret;
    }

}
