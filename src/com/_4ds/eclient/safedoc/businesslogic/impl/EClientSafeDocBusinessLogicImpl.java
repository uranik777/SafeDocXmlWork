package com._4ds.eclient.safedoc.businesslogic.impl;

import cm_sdoc902.COPY_MEMBER_SDOC902_BODY_DOC_HEADER_LINE_Type;

import com._4ds.eclient.businesslogic.common.impl.EClientBusinessServiceImpl;
import com._4ds.eclient.businesslogic.logon.EClientLogonBusinessService;
import com._4ds.eclient.safedoc.businesslogic.EClientSafeDocBusinessLogic;
import com._4ds.eclient.safedoc.businesslogic.function.EClientSafeDocFunction;

import dochdmk.MESSAGE_DOCHDMKS;

public class EClientSafeDocBusinessLogicImpl extends EClientBusinessServiceImpl
		implements EClientSafeDocBusinessLogic{

	private static final long serialVersionUID = -2978629246154180753L;
	
	protected EClientSafeDocFunction m_SafeDocFunction = new EClientSafeDocFunction();
	protected MESSAGE_DOCHDMKS m_DochdmkBody = new MESSAGE_DOCHDMKS();
	
    public EClientSafeDocBusinessLogicImpl(
    		EClientLogonBusinessService Xi_logon,
    		int Xi_documentNumber,
    		String Xi_errorCode,
    		String Xi_errorText){
    	
    	super(Xi_logon);
    	m_DochdmkBody.Init();
    	setMsgBody(m_DochdmkBody);
    	m_DochdmkBody.RESV001_BODY.Init();
    	
//    	m_DochdmkBody.RESV001_BODY.PICK_PART.PICK_RESERVATION_BODY.COMP_PART.RESERVATION_NUMBER = 20080001454926L;
    	
		COPY_MEMBER_SDOC902_BODY_DOC_HEADER_LINE_Type l_sdoc902Line = 
			new COPY_MEMBER_SDOC902_BODY_DOC_HEADER_LINE_Type();
		l_sdoc902Line.Init();
		l_sdoc902Line.LINE_HEADER_BODY.COMP_PART.DOCUMENT_NUMBER = Xi_documentNumber;
		l_sdoc902Line.LINE_HEADER_BODY.COMP_PART.HEADER_RECORD_NUMBER = Xi_documentNumber;
		l_sdoc902Line.LINE_STATUS_DESCRIPTION = Xi_errorCode + " " + Xi_errorText;
		
		m_DochdmkBody.SDOC902_BODY.DOC_HEADER_PAGE_SIZE = 1;
		m_DochdmkBody.SDOC902_BODY.DOC_HEADER_PAGE.DOC_HEADER_LINE = new COPY_MEMBER_SDOC902_BODY_DOC_HEADER_LINE_Type[1];
		m_DochdmkBody.SDOC902_BODY.DOC_HEADER_PAGE.DOC_HEADER_LINE[0] = l_sdoc902Line; 
    }
    
	public void updateDocumentStatus() {
		m_SafeDocFunction.setServerMessage( m_DochdmkBody );
		m_SafeDocFunction.docHeaderUpdateStatus();
	}
    
	public String getError() {
		return m_SafeDocFunction.getlastError();	
	}
}
