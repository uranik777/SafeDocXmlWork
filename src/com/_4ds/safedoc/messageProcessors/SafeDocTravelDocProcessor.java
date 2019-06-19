package com._4ds.safedoc.messageProcessors; 

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com._4ds.contour.ws.docreply.TSafeDocReply;
import com._4ds.contour.ws.safedoc.TMessage;
import com._4ds.safedoc.action.ExtWebService;
import com._4ds.safedoc.action.MessageCollector;
import com._4ds.safedoc.action.SDException;
import com._4ds.safedoc.action.XmlObject;
import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.processing.SafeDocError;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;
import com._4ds.safedoc.processing.impl.SafeDocErrorImpl;
import com._4ds.safedoc.processing.impl.SafeDocMessageProcessorImpl;
import com._4ds.safedoc.servlet.SafeDocServlet;

public class SafeDocTravelDocProcessor extends SafeDocMessageProcessorImpl implements SafeDocMessageProcessor {
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private TMessage m_Request;
   private TSafeDocReply m_Response = new TSafeDocReply();

	public SafeDocTravelDocProcessor(TMessage Xi_Request) throws Exception {
		super();
		m_Request=Xi_Request;
	}

    protected void logon(){
//    	 do nothing		
    }
    
	public void execute() {
		//begin changes by Yury
		try {
			if( m_Request!=null ){
				XmlObject l_XmlObject=new XmlObject();
				Document l_XmlDocument=buldDocument(m_Request);
				l_XmlObject.setM_XmlDocument( l_XmlDocument );
				l_XmlObject.setM_msgType("SafeDocMessage");
				l_XmlObject.setSignature( getSignature() );
				
				XmlObject l_CollectedXml;
				if( l_XmlObject.isMultiPart() ) {
					MessageCollector l_MessageCollector=new MessageCollector();
					l_MessageCollector.setSignature(getSignature());
					l_CollectedXml=l_MessageCollector.execute( l_XmlObject );
				}
				else {
					l_CollectedXml = l_XmlObject ;
				}
				
				l_CollectedXml.divideIntoDocs();
				
				if(l_CollectedXml.needExtWebService()==true){
					ExtWebService l_ExtWebService=new ExtWebService();
					l_ExtWebService.send(l_CollectedXml);
				}
				else{
					if( l_CollectedXml.isFileAction() ){
						SafeDocServlet.m_FileThread.addToQueue(l_CollectedXml);
						synchronized (SafeDocServlet.m_FileThread) {
							SafeDocServlet.m_FileThread.notifyAll();
						}					    
					}
					if( l_CollectedXml.isEmailAction() ){
						SafeDocServlet.m_EmailThread.addToQueue(l_CollectedXml);
						synchronized (SafeDocServlet.m_EmailThread) {
							SafeDocServlet.m_EmailThread.notifyAll();
						}
					}
					if( l_CollectedXml.isPrintAction() ){
						SafeDocServlet.m_PrintThread.addToQueue(l_CollectedXml);
						synchronized (SafeDocServlet.m_PrintThread) {
							SafeDocServlet.m_PrintThread.notifyAll();
						}
					}
				}
				
				
				m_Error = null;
				m_Response.setErrorCode(0);
				m_Response.setErrorText("Request " + m_Request.getMsgId()+" passed OK"); // TODO
				SDLogger.getApplicationLog().add(this,"Simulate travel documents processing, signature: " + getSignature(),"Processing");
			}else{
				m_Error= new SafeDocErrorImpl("002",SafeDocError.K_ERROR);
			}
		} catch (Exception e) {
			SDException se;
			try{
				se=(SDException) e;
			}
			catch(Exception e2){
				se=new SDException(11,e.toString());
			}
			SDLogger.getApplicationLog().add(this,"Simulate travel documents processing, signature: " + getSignature(),e.toString());
			m_Response.setErrorCode( se.m_ErrorCode );
			m_Response.setErrorText( e.toString() );
			m_Error= new SafeDocErrorImpl("002",SafeDocError.K_ERROR);			
		}
		//end changes by Yury
	}



	private Document buldDocument(TMessage request) throws ParserConfigurationException, SAXException, IOException {
		com._4ds.contour.ws.safedoc.Marshaller l_MarshallerSafeDoc=new com._4ds.contour.ws.safedoc.Marshaller();
		byte[] l_Xml=l_MarshallerSafeDoc.packToXml(m_Request);
		DocumentBuilderFactory l_dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder l_docBuilder = l_dbfac.newDocumentBuilder();
		ByteArrayInputStream l_ByteArrayInputStream=new ByteArrayInputStream(l_Xml);
		Document l_Document=l_docBuilder.parse(l_ByteArrayInputStream);
		return l_Document;
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
