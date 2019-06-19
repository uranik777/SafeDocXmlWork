package com._4ds.safedoc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com._4ds.eclient.config.EClientBaseConfigImpl;
import com._4ds.safedoc.action.Database;
import com._4ds.safedoc.action.EmailThread;
import com._4ds.safedoc.action.FileThread;
import com._4ds.safedoc.action.PrintThread;
import com._4ds.safedoc.action.SafeDocConfig;
import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;
import com._4ds.safedoc.processing.SafeDocProcessorFactory;
import com._4ds.safedoc.processing.SafeDocSoapProcessor;
import com._4ds.safedoc.processing.impl.SafeDocProcessorFactoryImpl;
import com._4ds.safedoc.processing.impl.SafeDocSoapProcessorImpl;

/**
 * SafeDoc is a servlet class that get SOAP message, process it and 
 * send a reply back to the sender.
 * Includes public methods on Message that perform main processing and init
 * for initializing servlet base on config file 
 */
public class SafeDocServlet extends HttpServlet implements SDLoggable{ 
  
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
	public static String s_applicationId = "SafeDoc";
    private static final String m_LOG_DIR_KEY = "log.directory";
	protected static String s_ConfigPathFromServletRoot = "webapps\\SafeDoc.CFG\\";
    
    private String m_logConfig = "";
    private String m_RequestTypeName = null;

	private String m_RemoteHost;

	private String m_MessagId;
	public static EmailThread m_EmailThread;
	public static FileThread m_FileThread;
	public static PrintThread m_PrintThread;
	private static SafeDocConfig m_Config;

    /** Initializes the servlet 
	 * @param servletConfig servlet configurator
	 */ 
    public void init( ServletConfig servletConfig ) throws ServletException {
        super.init(servletConfig);
    	EClientBaseConfigImpl.setConfigPathFromServletRoot(s_ConfigPathFromServletRoot);
        m_logConfig = servletConfig.getServletContext().getInitParameter( m_LOG_DIR_KEY );
        SDLogger.getLog( m_logConfig, s_applicationId );
        try {
			set_config(new SafeDocConfig());
		} catch (IOException e) {
			SDLogger.getApplicationLog().add(this,"Error read SafeDoc config"+e.getMessage(),"Error");
			e.printStackTrace();
		}
        createEmailThread();
        createFileThread();
        createPrintThread();
        //test database
        Database db=new Database();
        try {
			db.makedb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }

	private void createEmailThread() {
		try {
			m_EmailThread=new EmailThread();
		} catch (IOException e) {
			SDLogger.getApplicationLog().add(this,"Error create EmailThread "+e.getMessage(),"Error");
			e.printStackTrace();
		}
		m_EmailThread.start();
	}
	private void createPrintThread() {
		try {
			m_PrintThread=new PrintThread();
		} catch (IOException e) {
			SDLogger.getApplicationLog().add(this,"Error create PrintThread "+e.getMessage(),"Error");
			e.printStackTrace();
		}
		m_PrintThread.start();
	}
	private void createFileThread() {
		try {
			m_FileThread=new FileThread();
		} catch (IOException e) {
			SDLogger.getApplicationLog().add(this,"Error create FileThread "+e.getMessage(),"Error");
			e.printStackTrace();
		}
		m_FileThread.start();
	}
	public void doPost( HttpServletRequest Xi_request, HttpServletResponse Xo_reply ) 
			throws ServletException, IOException {
		
		process( Xi_request, Xo_reply );
    }
/*	
	public void doGet( HttpServletRequest Xi_request, HttpServletResponse Xo_reply ) 
			throws ServletException, IOException {
		doGet is not supported by this webservice	
		
    }
*/    
	
	public void doGet( HttpServletRequest Xi_request, HttpServletResponse Xo_reply ) 
	throws ServletException, IOException {
		 Xo_reply.setContentType("text/html");
	     PrintWriter out = Xo_reply.getWriter();
	     out.println("Please use Post method for call servlet..");
	     out.close();
	}
	
	private void process( HttpServletRequest Xi_request, HttpServletResponse Xo_reply )
			throws ServletException, IOException {
		SafeDocSoapProcessor l_soapProcessor = null;
		SafeDocMessageProcessor l_ImportMessageProcessor = null;
		String l_InputStreamContent = null;
		try {
			l_soapProcessor = new SafeDocSoapProcessorImpl( Xi_request, Xo_reply );
			String l_rawRequestTypeName = l_soapProcessor.getRequestName();
			int l_indx = l_rawRequestTypeName.indexOf(":");
			if ( l_indx >= 0 ){
				m_RequestTypeName = l_rawRequestTypeName.substring(l_indx +1 );
			}else{
				m_RequestTypeName = l_rawRequestTypeName;
			}
			m_RemoteHost = Xi_request.getRemoteHost();
			SafeDocProcessorFactory l_ProcessorFactory = new SafeDocProcessorFactoryImpl();
			
			String l_SessionId = null;
			l_SessionId = getSessionId(Xi_request);
		    m_MessagId=l_SessionId + System.currentTimeMillis();
			l_InputStreamContent = l_soapProcessor.getRequestString();
			l_ImportMessageProcessor = l_ProcessorFactory.create( m_RequestTypeName, l_SessionId, l_soapProcessor.getRequestBytes());
			l_ImportMessageProcessor.setSessionId(m_MessagId);
			l_ImportMessageProcessor.setTerminal(this.getTerminal());
			l_ImportMessageProcessor.setUserId(this.getUserId());

			SDLogger.getApplicationLog().begin(l_ImportMessageProcessor);
			String l_InputStreamContentToLog = l_ImportMessageProcessor.decorateRequestBeforeLog(l_InputStreamContent);
			SDLogger.getApplicationLog().add(l_ImportMessageProcessor,l_InputStreamContentToLog,"Input");
			if (l_ImportMessageProcessor.getError() == null){
				HttpSession l_Session = Xi_request.getSession(false);
				if (l_Session==null)
					l_Session = Xi_request.getSession(true);
				l_ImportMessageProcessor.execute();
			}
			l_soapProcessor.createReply(l_ProcessorFactory.createReply(l_soapProcessor.getRequestName()));
			if (l_ImportMessageProcessor.getError() != null){
				SDLogger.getApplicationLog().addError(l_ImportMessageProcessor,l_soapProcessor.getReplyString(),"Output");
			}else{
				SDLogger.getApplicationLog().add(l_ImportMessageProcessor,l_soapProcessor.getReplyString(),"Output");
			}
    	}catch(Exception exc){
    		System.out.println("SafeDoc Exception");
    		exc.printStackTrace();
    		if ( l_soapProcessor != null ){
    			l_soapProcessor.createSOAPFaultMessage( exc );
    			if (l_ImportMessageProcessor != null ){
    				SDLogger.getApplicationLog().add(l_ImportMessageProcessor,exc);
    			}else{
    				SDLogger.getApplicationLog().begin(this);
    				SDLogger.getApplicationLog().add(this,l_InputStreamContent,"Input");
    				SDLogger.getApplicationLog().add(this,exc);
    				SDLogger.getApplicationLog().end(this);
    			}
    		}
    	}finally{
    		if (l_ImportMessageProcessor != null){
    			SDLogger.getApplicationLog().end(l_ImportMessageProcessor);
    		}
    	}
    	Xo_reply.getOutputStream().close();
	}
	private String getSessionId(
		HttpServletRequest Xi_request
	){
		HttpSession l_Session = Xi_request.getSession(false);
		if (l_Session == null ){
			l_Session = Xi_request.getSession(true);
		}
		return l_Session.getId();
	}

	public String getLogId() {
		return s_applicationId;
	}

	public String getRequestType() {
		return m_RequestTypeName;
	}

	public String getSignature() {
		return m_MessagId;
	}

	public String getTerminal() {
		return m_RemoteHost;
	}

	public String getOperation() {
		return null;
	}

	public String getDocumentId() {
		return null;
	}

	public String getReservationId() {
		return null;
	}

	public String getUserId() {
		return "Servlet";
	}

	public static void set_config(SafeDocConfig m_Config) {
		SafeDocServlet.m_Config = m_Config;
	}

	public static SafeDocConfig get_config() {
		return m_Config;
	}
}
