package com._4ds.safedoc.processing.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.w3c.dom.Node;

import com._4ds.safedoc.processing.SafeDocSoapProcessor;


public class SafeDocSoapProcessorImpl implements SafeDocSoapProcessor {
	protected SOAPMessage m_request = null;
	protected SOAPMessage m_response = null;
	protected ServletOutputStream m_responseStream = null;
	private byte[] m_RequestBytes = null;
	private String m_ResponseXMLString = null;
	
	public SafeDocSoapProcessorImpl( HttpServletRequest Xi_request, HttpServletResponse Xo_reply ) throws IOException, SOAPException{
		MessageFactory l_msgFactory = null;
		ServletInputStream l_requestStream = Xi_request.getInputStream();
		l_msgFactory = MessageFactory.newInstance();
		MimeHeaders l_mh = new MimeHeaders();
		l_mh.addHeader( "Content-Type", "text/xml; charset=iso-8859-1" );
		m_request = l_msgFactory.createMessage( l_mh, l_requestStream );
		
		
		Xo_reply.setContentType("text/xml;charset=\"iso-8859-1\"");
	    Xo_reply.setHeader("SOAPAction","\"\"");
		m_responseStream = Xo_reply.getOutputStream();
		m_response = l_msgFactory.createMessage();
		copySoapHeader();
	}
	
	public String getRequestName() throws SOAPException{
		Node l_root = getXmlMessage();
		if( l_root != null ){
			return l_root.getNodeName();
		}
		return "";
	}
	
	public String createReply(String Xi_ResponseXML) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getRequestBytes() throws Exception {
		ByteArrayOutputStream l_buffer = new ByteArrayOutputStream();
		m_request.writeTo(l_buffer);
		m_RequestBytes = l_buffer.toByteArray();
		return m_RequestBytes;
	}
	public String getRequestString() throws Exception {
		if (m_RequestBytes != null)
			return new String(m_RequestBytes);
		return new String(getRequestBytes());
	}
	public String getSOAPBodyAsString() throws Exception {
	/*	try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION , "yes");
			ByteArrayOutputStream baContent = new ByteArrayOutputStream();
			DOMSource source = new DOMSource(m_request.getSOAPBody());
			StreamResult result = new StreamResult(baContent);
			transformer.transform(source, result);
			return baContent;
		}catch(Exception e){
			//throw new ECShellException("009",e,null);
		}*/
		return null;
	}
	
	private Node getXmlMessage() throws SOAPException {
		SOAPBody l_body = m_request.getSOAPBody();
		if( l_body != null ){
			return l_body.getFirstChild();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void copySoapHeader() throws SOAPException {
		SOAPHeader oSoapHeder = m_request.getSOAPHeader();
		if( oSoapHeder != null ){
			Iterator l_he = oSoapHeder.getChildElements();
			if( l_he != null ){
				while( l_he.hasNext() ){
					Object l_element = l_he.next();
					if( l_element instanceof SOAPElement ){
						m_response.getSOAPHeader().addChildElement( 
								(SOAPElement) l_element );
						}
				}
			}
		}
	}
	public String getReplyString() throws Exception {
		return new String(m_ResponseXMLString);
	}

	public void createReply(byte[] Xi_ResponseXML) throws Exception {
		//m_response.getMimeHeaders().setHeader("Content-Type", "text/xml; charset=iso-8859-1");
		//m_response.getSOAPPart().setMimeHeader("Content-Type", "text/xml; charset=iso-8859-1");
		if ( isValidXml( Xi_ResponseXML )  ){
			setSoapBodyContent( Xi_ResponseXML );
			m_response.saveChanges();
			m_response.writeTo( m_responseStream );
		}else{
			createSOAPFaultMessage(Xi_ResponseXML);
		}
		m_ResponseXMLString = new String(Xi_ResponseXML);
	}
	/**
	  * Set content into this message
	  *
	  * @param String Xi_Content to be added into the message
	  * @return SOAPMessage
	  */
	public void setSoapBodyContent( 
		byte[] Xi_sContent) 
	throws Exception
	{
		javax.xml.parsers.DocumentBuilderFactory oDocumentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
	    javax.xml.parsers.DocumentBuilder oDocumentBuilder = oDocumentBuilderFactory.newDocumentBuilder();
	    ByteArrayInputStream oInput = new ByteArrayInputStream(Xi_sContent);
	    org.w3c.dom.Document oDocument = oDocumentBuilder.parse(oInput);
	    m_response.getSOAPBody().addDocument(oDocument);
	}
	private boolean isValidXml(byte[] Xi_ResponseXML){
		if (Xi_ResponseXML == null && Xi_ResponseXML.length < 8 ){
			return false;
		}
		byte[] l_prefix = new byte[8];
		for ( int ii = 0; ii < 8; ii++ ){
			l_prefix[ii] =  Xi_ResponseXML[ii];
		}
		String l_sPrefix = new String(l_prefix);
		if ( !l_sPrefix.startsWith("<?xml")){
			return false;
		}
		return true;
	}
	public void createSOAPFaultMessage( Exception excEC )
	{
	  String l_Message = ""; 	
      if (excEC.getLocalizedMessage()==null){
    	  if ( excEC.toString() == null ){
    		  l_Message = "Unknown error";
    	  }else{
    		  l_Message = excEC.toString();
    	  }
	  }else{
		  l_Message = excEC.getLocalizedMessage();
	  }
      createSOAPFaultMessage(l_Message);
	}
	private void createSOAPFaultMessage( byte[] Xi_FaultMessageContent )
	{
		createSOAPFaultMessage(new String(Xi_FaultMessageContent));
	}
	public SOAPBody getRequestSOAPBody(){
		try{
		SOAPPart oSOAPPart = m_request.getSOAPPart();
	    SOAPEnvelope oSOAPEnvelope = oSOAPPart.getEnvelope();       
	    return oSOAPEnvelope.getBody();
		} catch(Exception exc){
			return null;
		}
	}
	public SOAPBody getResponseSOAPBody(){
		try{
		SOAPPart oSOAPPart = m_response.getSOAPPart();
	    SOAPEnvelope oSOAPEnvelope = oSOAPPart.getEnvelope();       
	    return oSOAPEnvelope.getBody();
		} catch(Exception exc){
			return null;
		}
	}
	public void createSOAPFaultMessage( String Xi_FaultMessageContent )
	{
	   try{
		SOAPPart oSOAPPart = m_response.getSOAPPart();
	    SOAPEnvelope oSOAPEnvelope = oSOAPPart.getEnvelope();       
	    SOAPBody oSOAPBody = oSOAPEnvelope.getBody();
	    Name oSOAPFaultCodeName = oSOAPEnvelope.createName("Server", 
	    								oSOAPEnvelope.getPrefix(), 
	                                    oSOAPEnvelope.getNamespaceURI());
	    oSOAPBody.addFault(oSOAPFaultCodeName, Xi_FaultMessageContent);
		m_response.saveChanges();
		m_response.writeTo( m_responseStream );
	   }catch (Exception exc){
		   ;// TODO log exception - it is all we can do if even send the fault message is failed
	   }
	}

}
