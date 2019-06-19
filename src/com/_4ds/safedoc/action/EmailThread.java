package com._4ds.safedoc.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.servlet.SafeDocServlet;

public class EmailThread extends Thread implements SDLoggable{

	private static final long serialVersionUID = 1L;
	
	private String m_SessionId;
	private String m_Terminal;
	private String m_UserId;	
	
	private ArrayList<XmlObject> m_Queue;
	private boolean m_NeedExit=false;

	public EmailThread( ) throws IOException {
		m_Queue=new ArrayList<XmlObject>();
	}
	public void stop2(){
		m_NeedExit=true;
	}
	public synchronized void run() {
		this.setSignature("");
		this.setTerminal("");
		this.setUserId("");		
		SDLogger.getApplicationLog().add(this,"Email thread started","ok");
		while(m_NeedExit==false){
			while(m_Queue.size()>0){
				this.setSignature("");
				this.setTerminal("");
				this.setUserId("");				
				SDLogger.getApplicationLog().add(this,"Email queue size: "+m_Queue.size(),"");
				com._4ds.safedoc.action.Email l_Email=new com._4ds.safedoc.action.Email();
				try {
					XmlObject l_XmlObject=m_Queue.get(0);
					this.setSignature(l_XmlObject.getSignature());
					this.setTerminal(l_XmlObject.getTerminal());
					this.setUserId(l_XmlObject.getUserId());					
					SDLogger.getApplicationLog().add(this,"Get Email object from queue", l_XmlObject.getM_ObjectFileName() );
					l_Email.execute( l_XmlObject );
					java.io.File l_File=new java.io.File( l_XmlObject.getM_ObjectFileName() );
					SDLogger.getApplicationLog().add(this,"Remove Email object from queue", l_XmlObject.getM_ObjectFileName() );
					l_File.delete();
					m_Queue.remove(0);
				} catch (Exception e) {
					SDLogger.getApplicationLog().add(this,"Email thread error:",e.getMessage());
					e.printStackTrace();
					m_Queue.remove(0);
				}
			}
			try {
				wait();
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}
		SDLogger.getApplicationLog().add(this,"Email thread end","ok");
	}
	
	
	public void addToQueue(XmlObject x_XmlObject) throws Exception, IOException {
		m_Queue.add(x_XmlObject);
		x_XmlObject.setM_ObjectFileName(getFileName());
		SDLogger.getApplicationLog().add(this,"add to queue", x_XmlObject.getM_ObjectFileName() );
		x_XmlObject.saveObject();
	}
	
	
	private String getFileName() throws IOException {
		java.io.File l_File;
		String l_FileName;
		do{
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String l_timestring=sdf.format(cal.getTime());
	        l_FileName= SafeDocServlet.get_config().getQueuePath()+l_timestring+"-EmailObject.xml";
	        l_File=new java.io.File( l_FileName );
		}while ( l_File.exists()==true );
        
        return l_FileName;
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
	
}
