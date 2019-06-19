package com._4ds.safedoc.messageProcessors;

import com._4ds.contour.ws.docreply.TSafeDocReply;
import com._4ds.contour.ws.extprocinfo.TExternalProcessingInfoRQ;
import com._4ds.eclient.businesslogic.logon.EClientLogonBusinessService;
import com._4ds.eclient.businesslogic.logon.impl.EClientSimpleLogonBusinessLogicImpl;
import com._4ds.eclient.safedoc.businesslogic.impl.EClientSafeDocBusinessLogicImpl;
import com._4ds.safedoc.processing.SafeDocError;
import com._4ds.safedoc.processing.impl.SafeDocErrorImpl;
import com._4ds.safedoc.processing.impl.SafeDocMessageProcessorImpl;

public class SafeDocExternalProcessingInfoProcessor extends SafeDocMessageProcessorImpl{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9157441905605336715L;
	protected String m_error = null;
	private TExternalProcessingInfoRQ m_Request;
	private TSafeDocReply m_Response = new TSafeDocReply();
	
	public SafeDocExternalProcessingInfoProcessor(TExternalProcessingInfoRQ Xi_Request) throws Exception {
		super();
		m_Request = Xi_Request;
	}
	
	public void execute() {
		try{
			String l_docId = getDocId();
			String l_user = getUser();
			String l_password = getPassword();
			String l_errorCode = getErrorCode();
			String l_errorText = getErrorText();
			if ( l_docId != null && l_user != null && l_password != null ){
				m_error = processUsingSafeDocBusineeLogic( getLogon( l_user, l_password ),
					Integer.parseInt( l_docId ), l_errorCode, l_errorText );
			}else{
				m_Error= new SafeDocErrorImpl("006",SafeDocError.K_ERROR);
			}
			m_Response.setErrorCode ( (m_error == null ) ? 0 : 999 );
			m_Response.setErrorText( (m_error == null ) ? "All OK" : m_error );
		}catch(Exception exc){
			; // TODO
		}
	}
	
	public SafeDocError getError(){
		return new SafeDocErrorImpl(m_error);
	}

	public String getDocumentId() {
		return getDocId();
	}

	public String getUserId() {
		return getUser();
	}

    private String getDocId(){
    	// TODO
    	//if (m_Request != null && m_Request.m_docReply != null ){
    	//	return m_Request.m_docReply.m_DocId
    	//}
    	return null;
    }
    private String getErrorCode(){
    	if (m_Request != null && m_Request.m_docReply != null ){
    		return String.valueOf(m_Request.m_docReply.m_errorCode);
    	}
    	return null;
    }
    private String getErrorText(){
    	if (m_Request != null && m_Request.m_docReply != null ){
    		return m_Request.m_docReply.m_errorText;
    	}
    	return null;
    }
    private String getUser(){
    	if (m_Request != null && m_Request.m_loginInfo != null ){
    		return m_Request.m_loginInfo.m_userID;
    	}
    	return null;
    }
    private String getPassword(){
    	if (m_Request != null && m_Request.m_loginInfo != null ){
    		return m_Request.m_loginInfo.m_password;
    	}
    	return null;
    }
    private EClientLogonBusinessService getLogon( String Xi_user, String Xi_password ) {
		EClientLogonBusinessService l_logon = new EClientSimpleLogonBusinessLogicImpl();
		l_logon.setUserId( Xi_user );
		l_logon.setPassword( Xi_password );
		l_logon.logon();
		String l_error = l_logon.getError();
		if( l_error != null ){
			System.out.println( "Logon error: "  + l_error );
		}
		return l_logon;
	}
    
	private String processUsingSafeDocBusineeLogic( EClientLogonBusinessService Xi_logon, int Xi_documentNumber, String Xi_errorCode, String Xi_errorText){
		EClientSafeDocBusinessLogicImpl l_logic = new EClientSafeDocBusinessLogicImpl(
				Xi_logon, Xi_documentNumber, Xi_errorCode, Xi_errorText );
		l_logic.updateDocumentStatus();
		return l_logic.getError();
	}

	public Object getReply() {
		return m_Response;
	}
	public String decorateRequestBeforeLog(String Xi_InputStreamContent){
		return Xi_InputStreamContent.replaceAll("<password>+[\\w]+</password>", "<password>xxxxxxxx</password>");
	}
}
