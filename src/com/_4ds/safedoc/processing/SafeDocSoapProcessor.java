package com._4ds.safedoc.processing;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;


public interface SafeDocSoapProcessor {
	public String getRequestName() throws SOAPException;
	
	public byte[] getRequestBytes() throws Exception;
	public String getRequestString() throws Exception;
	
	public void createReply(byte[] Xi_ResponseXML) throws Exception;
	public String getReplyString() throws Exception;

	public void createSOAPFaultMessage( Exception excEC );

	public void createSOAPFaultMessage( String Xi_FaultMessageContent );

	public SOAPBody getRequestSOAPBody();
	public SOAPBody getResponseSOAPBody();
}
