package com._4ds.safedoc.processing.impl;

import com._4ds.contour.ws.docreply.TSafeDocReply;
import com._4ds.contour.ws.safedoc.TMessage;
import com._4ds.safedoc.messageProcessors.SafeDocAdvisingProcessor;
import com._4ds.safedoc.messageProcessors.SafeDocExternalProcessingInfoProcessor;
import com._4ds.safedoc.messageProcessors.SafeDocResvExportProcessor;
import com._4ds.safedoc.messageProcessors.SafeDocTravelDocProcessor;
import com._4ds.safedoc.messageProcessors.SafeDocUnknownProcessor;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;
import com._4ds.safedoc.processing.SafeDocProcessorFactory;

public class SafeDocProcessorFactoryImpl implements
		SafeDocProcessorFactory {
	public static final String K_SAFEDOC_REQUEST = "SafeDocMessage";
	public static final String K_SAFEADV_REQUEST = "SafeAdvMessage";
	public static final String K_RESVEXP_REQUEST = "ResvExportMessage";
	public static final String K_EXTPROCINFO_REQUEST = "ExtProcInfoMessage";

    private com._4ds.contour.ws.safedoc.Unmarshaller m_UnmarshallerSafeDoc = new com._4ds.contour.ws.safedoc.Unmarshaller();
    private com._4ds.contour.ws.advising.Unmarshaller m_UnmarshallerSafeAdv = new com._4ds.contour.ws.advising.Unmarshaller();
    private com._4ds.contour.ws.resvexport.Unmarshaller m_UnmarshallerResvExport = new com._4ds.contour.ws.resvexport.Unmarshaller();
    private com._4ds.contour.ws.extprocinfo.Unmarshaller m_UnmarshallerExtProcInfo = new com._4ds.contour.ws.extprocinfo.Unmarshaller();
    private com._4ds.contour.ws.docreply.Marshaller m_MarshallerSafeDoc = new com._4ds.contour.ws.docreply.Marshaller();
    private SafeDocMessageProcessor m_ImportProcessor;

	public SafeDocMessageProcessor create(String Xi_RequestName, Object Xi_SessionContext, byte[] Xi_Request ) throws Exception {
		if(Xi_RequestName.compareToIgnoreCase(K_SAFEDOC_REQUEST)==0){
            TMessage oRequest = (TMessage) m_UnmarshallerSafeDoc.unpackMessage(Xi_Request);
            return m_ImportProcessor=new SafeDocTravelDocProcessor(oRequest);
		}else if(Xi_RequestName.compareToIgnoreCase(K_SAFEADV_REQUEST)==0){
			com._4ds.contour.ws.advising.TMessage oRequest = (com._4ds.contour.ws.advising.TMessage) m_UnmarshallerSafeAdv.unpackMessage(Xi_Request);
            return m_ImportProcessor=new SafeDocAdvisingProcessor(oRequest);
		}else if(Xi_RequestName.compareToIgnoreCase(K_RESVEXP_REQUEST)==0){
			com._4ds.contour.ws.resvexport.TMessage oRequest = (com._4ds.contour.ws.resvexport.TMessage) m_UnmarshallerResvExport.unpackMessage(Xi_Request);
            return m_ImportProcessor=new SafeDocResvExportProcessor(oRequest);
		}else if(Xi_RequestName.compareToIgnoreCase(K_RESVEXP_REQUEST)==0){
			com._4ds.contour.ws.extprocinfo.TExternalProcessingInfoRQ oRequest = (com._4ds.contour.ws.extprocinfo.TExternalProcessingInfoRQ) m_UnmarshallerExtProcInfo.unpackMessage(Xi_Request);
            return m_ImportProcessor=new SafeDocExternalProcessingInfoProcessor(oRequest);
		}
		return m_ImportProcessor=new SafeDocUnknownProcessor();
	}

	public byte[] createReply( String Xi_RequestName) {
		if(Xi_RequestName.compareToIgnoreCase(K_SAFEDOC_REQUEST)==0){
			return m_MarshallerSafeDoc.packToXml((TSafeDocReply) m_ImportProcessor.getReply());
		}else if(Xi_RequestName.compareToIgnoreCase(K_SAFEADV_REQUEST)==0){
			return m_MarshallerSafeDoc.packToXml((TSafeDocReply) m_ImportProcessor.getReply());
		}else if(Xi_RequestName.compareToIgnoreCase(K_RESVEXP_REQUEST)==0){
			return m_MarshallerSafeDoc.packToXml((TSafeDocReply) m_ImportProcessor.getReply());
		}
		return ((String)m_ImportProcessor.getReply()).getBytes();
	}
}
