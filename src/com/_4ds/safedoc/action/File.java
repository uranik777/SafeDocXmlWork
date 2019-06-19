package com._4ds.safedoc.action; 

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;

public class File implements SDLoggable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String m_XslName;
	String m_FileName;
	XmlObject m_XmlObject;
	List<Object> m_Attachments;
	List<String> m_AttachmentsName;

	private String m_SessionId;
	private String m_Terminal;
	private String m_UserId;
	

	
	
	public void execute(XmlObject x_XmlObject) throws Exception {
		m_XmlObject=x_XmlObject;
		
		m_SessionId=x_XmlObject.getSignature();
		this.setTerminal(x_XmlObject.getTerminal());
		this.setUserId(x_XmlObject.getUserId());
		
		m_Attachments=new ArrayList<Object>();
		m_AttachmentsName=new ArrayList<String>();
		SDLogger.getApplicationLog().add(this,"File action begin, signature:"+ getSignature() ,"");		
		for(int i=0;i<m_XmlObject.get_DocsList().size();i++ ) {
			DocObject l_doc=(DocObject) m_XmlObject.get_DocsList().get(i);
			m_FileName=l_doc.get_filename();
			saveFile(l_doc.get_byte());			
		}		
		SDLogger.getApplicationLog().add(this,"File action end, signature:"+ getSignature() ,"");		
	}




	private void saveFile(byte[] l_byte) throws IOException {
		String l_SavedFileName=m_FileName;
		FileOutputStream l_FileOutputStream=new FileOutputStream(l_SavedFileName);
		l_FileOutputStream.write(l_byte);
		l_FileOutputStream.close();	
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
	public void setSignature(String xi) {
		m_SessionId=xi;
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
