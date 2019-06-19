package com._4ds.safedoc.messageProcessors;

import com._4ds.contour.ws.docreply.TSafeDocReply;
import com._4ds.contour.ws.resvexport.TMessage;
import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.processing.SafeDocError;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;
import com._4ds.safedoc.processing.impl.SafeDocErrorImpl;
import com._4ds.safedoc.processing.impl.SafeDocMessageProcessorImpl;

public class SafeDocResvExportProcessor extends SafeDocMessageProcessorImpl implements SafeDocMessageProcessor {
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private TMessage m_Request;
   private TSafeDocReply m_Response = new TSafeDocReply();

	public SafeDocResvExportProcessor(TMessage Xi_Request) throws Exception {
		super();
		m_Request=Xi_Request;
	}

    protected void logon(){
//    	 do nothing		
    }
    
	public void execute() {
		if( m_Request!=null ){
			m_Error = null;
			m_Response.setErrorCode(0);
			m_Response.setErrorText("Request " + m_Request.getHeader().getId()+" passed OK"); // TODO
			SDLogger.getApplicationLog().add(this,"Simulate reservation export processing, signature: " + getSignature(),"Processing");
		}else{
			m_Error= new SafeDocErrorImpl("002",SafeDocError.K_ERROR);
		}
	}

	public Object getReply() {
		//TProcessingInfo l_ProcessingInfo = createReply();
		return m_Response;
	}
	public String getOperation() {
		// TODO
		return null;
	}

	public String getDocumentId() {
		// TODO
		return null;
	}

	public String getReservationId() {
		// TODO
		return null;
	}

}
