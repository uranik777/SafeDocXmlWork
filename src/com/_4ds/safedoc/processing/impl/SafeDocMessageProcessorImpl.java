package com._4ds.safedoc.processing.impl;

import javax.xml.soap.SOAPBody;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com._4ds.safedoc.processing.SafeDocError;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;
import com._4ds.safedoc.servlet.SafeDocServlet;

public abstract class SafeDocMessageProcessorImpl implements
		SafeDocMessageProcessor {

	protected SafeDocError m_Error;
	
	protected String m_SessionId;
	protected String m_Terminal;
	protected String m_UserId;
	
    protected SafeDocMessageProcessorImpl (){
    	
    }
	
	public void setSessionId (String Xi_sessionId ){
		m_SessionId = Xi_sessionId;
	}
	public void setTerminal (String xi ){
		m_Terminal = xi;
	}
	public void setUserId (String xi ){
		m_UserId = xi;
	}

    protected void beforeExecuteLogic(){
		// SDLogger.getApplicationLog().add(this, getSignature(),"Identification"); 
    	// processing is context free - we need no keep any context from request to request
    }
/*	
    protected String getValue( String Xi_tag ) throws Exception{
    	NodeList l_nodeList = m_request.getElementsByTagName( Xi_tag );
    	if( l_nodeList != null && l_nodeList.getLength() > 0 ){
    		Node l_node = l_nodeList.item(0).getFirstChild();
    		if( l_node != null ){
    			return l_node.getNodeValue();
    		}
    	}
    	return "";
    }
*/
	public SafeDocError getError() {
		return m_Error;
	}
	public String getUserId(){
		return m_UserId;
	}

	public String getTerminal(){
		return m_Terminal;
	}

	public String getLogId() {
		return SafeDocServlet.s_applicationId;
	}

	public String getRequestType() {
		return getClass().getSimpleName();
	}

	public String getSignature() {
		return m_SessionId;
	}


	public String getDocumentId() {
		return null;
	}
	public String getReservationId() {
		return null;
	}
	public String getOperation() {
		// TODO
		return null;
	}
	public String decorateRequestBeforeLog(String Xi_InputStreamContent){
		return Xi_InputStreamContent;
	}

}
