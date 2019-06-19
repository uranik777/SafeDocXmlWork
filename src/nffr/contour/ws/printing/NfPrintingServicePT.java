/**
 * NfPrintingServicePT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nffr.contour.ws.printing;

public interface NfPrintingServicePT extends java.rmi.Remote {
    public nffr.contour.ws.printing.NfPrintingResponseType printLocallyOp(nffr.contour.ws.printing.NfPrintingMessageType RQ) throws java.rmi.RemoteException;
    public nffr.contour.ws.printing.NfPrintingResponseType previewOp(nffr.contour.ws.printing.NfPrintingMessageType RQ) throws java.rmi.RemoteException;
    public nffr.contour.ws.printing.NfPrintingResponseType printNowOp(nffr.contour.ws.printing.NfPrintingMessageType RQ) throws java.rmi.RemoteException;
    public nffr.contour.ws.printing.NfPrintingResponseType batchPrintingOp(nffr.contour.ws.printing.BatchPrintingMessageType RQ) throws java.rmi.RemoteException;
}
