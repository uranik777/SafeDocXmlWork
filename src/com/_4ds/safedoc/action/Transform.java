package com._4ds.safedoc.action;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;

public class Transform implements SDLoggable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream m_xsltStream;
	private InputStream m_xmlStream;
	
	private String m_SessionId;
	private String m_Terminal;
	private String m_UserId;
	
	
	
	byte[] xml2html() throws TransformerException, IOException{
		ByteArrayOutputStream l_ByteArrayOutputStream = null;
		try{
		SDLogger.getApplicationLog().add(this,"Transform to HTML: "+this.getSignature(),"begin");
	    TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer =
            tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(getM_xsltStream()));
        StreamSource source = new StreamSource(getM_xmlStream());
        l_ByteArrayOutputStream=new ByteArrayOutputStream();
        transformer.transform (source,  new javax.xml.transform.stream.StreamResult( l_ByteArrayOutputStream ));
        l_ByteArrayOutputStream.close();
        SDLogger.getApplicationLog().add(this,"Transform to HTML: "+this.getSignature(),"end");
		}
		finally{
			l_ByteArrayOutputStream.close();
		}
        return(l_ByteArrayOutputStream.toByteArray());
	}

	byte[] xml2pdf(){
    	byte[] ba = null;
        try {
        	SDLogger.getApplicationLog().add(this,"Transform to PDF begin: "+this.getSignature(),"");
            
            FopFactory fopFactory = FopFactory.newInstance();

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

            ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();

            
            try {
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
    
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(getM_xsltStream()));
                

                transformer.setParameter("versionParam", "2.0");
            

                Source src = new StreamSource(getM_xmlStream());
            

                Result res = new SAXResult(fop.getDefaultHandler());
    

                transformer.transform(src, res);
            	ba=out.toByteArray();
            } finally {
                out.close();
            }
        	SDLogger.getApplicationLog().add(this,"Transform to PDF end: "+this.getSignature(),"");
        } catch (Exception e) {
        	SDLogger.getApplicationLog().add(this,"Transform to PDF error: "+this.getSignature(),e.getMessage() );
        }
		return ba;		
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
	public void setSignature(String x_SessionId) {
		m_SessionId=x_SessionId;
	}
	@Override
	public String getTerminal() {
		return m_Terminal;
	}
	
	public void setTerminal(String x_Terminal) {
		m_Terminal=x_Terminal;
	}
	@Override
	public String getUserId() {
		return m_UserId;
	}
	
	public void setUserId(String x_UserId) {
		m_UserId=x_UserId;
	}
	

	public void setM_xsltStream(InputStream m_xsltStream) {
		this.m_xsltStream = m_xsltStream;
	}

	public InputStream getM_xsltStream() {
		return m_xsltStream;
	}

	public void setM_xmlStream(InputStream m_xmlStream) {
		this.m_xmlStream = m_xmlStream;
	}

	public InputStream getM_xmlStream() {
		return m_xmlStream;
	}
}
