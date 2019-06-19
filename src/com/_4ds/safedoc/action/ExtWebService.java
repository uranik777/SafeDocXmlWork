package com._4ds.safedoc.action;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.servlet.SafeDocServlet;


public class ExtWebService implements SDLoggable{
	private static final long serialVersionUID = 1L;
	private String m_SessionId;

	public void send(XmlObject x_XmlObject) {
		try{
			m_SessionId=x_XmlObject.getSignature();
			SDLogger.getApplicationLog().add(this,"Send to External WebService begin"+" signature:"+this.getSignature(),"" );
			nffr.contour.ws.printing.NfPrintingService l_NfPrintingService = new nffr.contour.ws.printing.NfPrintingServiceLocator();
			URL l_portAddress = new java.net.URL(SafeDocServlet.get_config().getWebServiceAddress());
			nffr.contour.ws.printing.NfPrintingServicePT l_NfPrintingServicePT = l_NfPrintingService.getNfPrintingServiceSOAP(l_portAddress);
	
			nffr.contour.ws.printing.NfPrintingMessageType l_NfPrintingMessageType = new nffr.contour.ws.printing.NfPrintingMessageType();
			nffr.contour.ws.printing.BatchPrintingMessageType l_BatchPrintingMessageType = new nffr.contour.ws.printing.BatchPrintingMessageType();
			nffr.contour.ws.printing.NfPrintingResponseType l_NfPrintingResponseType = new nffr.contour.ws.printing.NfPrintingResponseType();
	
			String l_data = x_XmlObject.getString();
			l_NfPrintingMessageType.setData(l_data);
			l_BatchPrintingMessageType.setData(l_data);
			l_BatchPrintingMessageType.setEndFlag("false");
	
			String tranCode;
			tranCode = x_XmlObject.get_Delivery_tranCode();
			SDLogger.getApplicationLog().add(this,"tranCode:"+tranCode,"" );
	
			String method = getExtWebServiceMethod(tranCode);
			if(method==null){
				throw new Exception("Cant get tranCode tag.");
			}
			if (method.compareToIgnoreCase("PreviewOp") == 0) {
				l_NfPrintingResponseType = l_NfPrintingServicePT
						.previewOp(l_NfPrintingMessageType);
			}
			if (method.compareToIgnoreCase("PrintNowOp") == 0) {
				l_NfPrintingResponseType = l_NfPrintingServicePT
						.printNowOp(l_NfPrintingMessageType);
			}
			if (method.compareToIgnoreCase("PrintLocallyOp") == 0) {
				l_NfPrintingResponseType = l_NfPrintingServicePT
						.printLocallyOp(l_NfPrintingMessageType);
			}
			if (method.compareToIgnoreCase("BatchPrintingOp") == 0) {
				l_NfPrintingResponseType = l_NfPrintingServicePT
						.batchPrintingOp(l_BatchPrintingMessageType);
			}
			
			SDLogger.getApplicationLog().add(this,"Got reply from External WebService"+" signature:"+this.getSignature(),l_NfPrintingResponseType.getErrorCode().toString() );
			SDLogger.getApplicationLog().add(this,"Got reply from External WebService [ErrorText]"+" signature:"+this.getSignature(),l_NfPrintingResponseType.getErrorText() );
			for(int i=0;i<l_NfPrintingResponseType.getComment().length;i++){
				SDLogger.getApplicationLog().add(this,"Got reply from External WebService [Comment]"+" signature:"+this.getSignature(),l_NfPrintingResponseType.getComment()[i] );
			}
			for(int i=0;i<l_NfPrintingResponseType.getSavedFileName().length;i++){
				SDLogger.getApplicationLog().add(this,"Got reply from External WebService [SavedFileName]"+" signature:"+this.getSignature(),l_NfPrintingResponseType.getSavedFileName()[i] );
			}
			SDLogger.getApplicationLog().add(this,"Send to External WebService end"+" signature:"+this.getSignature(),"" );
		}
		catch (RemoteException e) {
			SDLogger.getApplicationLog().add(this,"Error send to External WebService"+" signature:"+this.getSignature(),e.getMessage() );
			e.printStackTrace();
		}
		catch (Exception e) {
			SDLogger.getApplicationLog().add(this,"Error send to External WebService"+" signature:"+this.getSignature(),e.getMessage() );
			e.printStackTrace();
		}
	}

	private String getExtWebServiceMethod(String x_tranCode) throws Exception {
		XPathFactory  factory=XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		InputStream l_InputStream=getClass().getResourceAsStream("ExtWebServiceConfig.xml");
		InputSource l_inputSource=new InputSource(l_InputStream);
		XPathExpression  xPathExpression= xPath.compile("/Options/tranCode/name");
		NodeList l_NodeList=(NodeList) xPathExpression.evaluate(l_inputSource, XPathConstants.NODESET );
		for(int i=0;i<l_NodeList.getLength();i++){
			if(l_NodeList.item(i).getTextContent().compareToIgnoreCase(x_tranCode)==0 ){
				if(l_NodeList.item(i).getNextSibling().getNodeName().compareToIgnoreCase("method")==0){
					String l_Tag=l_NodeList.item(i).getNextSibling().getTextContent();
					return l_Tag;
				}
			}
		}
		return null;
	}

	@Override
	public String getDocumentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReservationId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSignature() {
		return m_SessionId;
	}

	@Override
	public String getTerminal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
}
