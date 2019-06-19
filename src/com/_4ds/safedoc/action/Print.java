package com._4ds.safedoc.action;

import java.util.ArrayList;
import java.util.List;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;


public class Print implements SDLoggable{
	
	private static final long serialVersionUID = 1L;
	XmlObject m_XmlObject;
	String m_XslName;
	String m_FileName;	
	List<Object> m_PrintObjectList;

	private String m_SessionId;
	private String m_Terminal;
	private String m_UserId;
	
	private void prepareToPrint() throws Exception {
		loadDocs();
		PrintAction();
	}
	
	private void loadDocs() {
		for(int i=0;i<m_XmlObject.get_DocsList().size();i++ ) {
			DocObject l_doc=(DocObject) m_XmlObject.get_DocsList().get(i);
			m_PrintObjectList.add( l_doc.get_byte() );
		}
	}	
	

		

	
	public  void PrintAction() throws Exception{
		for(int i=0;i<m_PrintObjectList.size();i++) {
			byte[] l_byte=(byte[]) m_PrintObjectList.get(i);
	        PrintHTML lPrintHTML=new PrintHTML();
	        lPrintHTML.print(l_byte);
	        
		}		
	}
	
	

    
	public void execute(XmlObject x_XmlObject) throws Exception {
		m_XmlObject=x_XmlObject;
		m_PrintObjectList=new ArrayList<Object>();
		
		m_SessionId=x_XmlObject.getSignature();
		this.setTerminal(x_XmlObject.getTerminal());
		this.setUserId(x_XmlObject.getUserId());
		SDLogger.getApplicationLog().add(this,"Print begin, signature:"+this.getSignature(),"" );		
		prepareToPrint();
		SDLogger.getApplicationLog().add(this,"Print end, signature:"+this.getSignature(),"" );		
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
    

}
