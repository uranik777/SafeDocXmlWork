package com._4ds.safedoc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.servlet.SafeDocServlet;
import com.sun.xml.internal.ws.util.ByteArrayDataSource;


public class Email implements SDLoggable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	XmlObject m_XmlObject;
	
	List<Object> m_Attachments;
	List<String> m_AttachmentsName;

	private String m_SessionId;
	private String m_Terminal;
	private String m_UserId;

	
	public void execute() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException, TransformerException, AddressException, MessagingException
	{
		m_Attachments=new ArrayList<Object>();
		m_AttachmentsName=new ArrayList<String>();
		sendEmail();
	}
	

	private void sendEmail() throws XPathExpressionException, AddressException, MessagingException{
		for(int i=0;i<m_XmlObject.get_DocsList().size();i++ ) {
			DocObject l_doc=(DocObject) m_XmlObject.get_DocsList().get(i);

			addAttachment( l_doc.get_byte() );
			addAttachmentName( l_doc.get_name() );			
		}
		sendEmailAction();
	}
	

	
	
	private void addAttachment(byte[] l_byte) {
		m_Attachments.add(l_byte );
	}
	
	private void addAttachmentName(String x_name) throws XPathExpressionException {
		m_AttachmentsName.add(x_name);
	}

	
	


	
	
	private void sendEmailAction() throws AddressException, MessagingException, XPathExpressionException {
		List<MimeBodyPart> l_MimeBodyPartList=new ArrayList<MimeBodyPart>();
		Properties props = System.getProperties();
		props.put("mail.smtp.host", SafeDocServlet.get_config().getProperty("smtpServer") );
		Session l_session = Session.getInstance(props, null);
		MimeMessage l_MimeMessage = new MimeMessage(l_session);
		String l_from=SafeDocServlet.get_config().getProperty("returnEmail");
		l_MimeMessage.setFrom(new InternetAddress(l_from));
		String l_to=getReceiver();
		InternetAddress[] l_address = {new InternetAddress(l_to)};
		l_MimeMessage.setRecipients(Message.RecipientType.TO, l_address);
		String l_subject=getSubject();
		l_MimeMessage.setSubject(l_subject);
	    // create and fill the first message part
		MimeBodyPart l_MimeBodyPart = new MimeBodyPart();
		String l_MessageText= getMessageText();
		l_MimeBodyPart.setText(l_MessageText);
		l_MimeBodyPartList.add(l_MimeBodyPart);
		
	    // create and fill
		for(int i=0;i<m_Attachments.size();i++) {
			MimeBodyPart l_MimeBodyPart2 = new MimeBodyPart();
	        DataSource l_DataSource = new ByteArrayDataSource((byte[]) m_Attachments.get(i), "application/octet-stream");
	        l_MimeBodyPart2.setDataHandler(new DataHandler(l_DataSource));
	        l_MimeBodyPart2.setFileName( (String) m_AttachmentsName.get(i) );
	        l_MimeBodyPartList.add( l_MimeBodyPart2 );
		}
	    // create the Multipart and add its parts to it
		Multipart l_Multipart = new MimeMultipart();
		for(int i=0;i<l_MimeBodyPartList.size();i++) {
			l_Multipart.addBodyPart(l_MimeBodyPartList.get(i));
		}
		
	    // add the Multipart to the message
		l_MimeMessage.setContent(l_Multipart);
		// set the Date: header
		l_MimeMessage.setSentDate(new Date());		
	    // send the message
	    Transport.send(l_MimeMessage);
	}

	private String getMessageText() {
		return SafeDocServlet.get_config().getProperty("messageText");
		
	}

	private String getSubject() {
		return SafeDocServlet.get_config().getProperty("messageSubject");
	}

	private String getReceiver() throws XPathExpressionException {
		String addressTag=getTag("/"+m_XmlObject.getM_msgType()+"/Delivery/address");
		return addressTag;
	}






	
	
	

	
	
	
	
	private String getTag(String x_string) throws XPathExpressionException {
		XPathFactory  factory=XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		XPathExpression  xPathExpression= xPath.compile(x_string);
		String l_Tag=xPathExpression.evaluate( m_XmlObject.getM_XmlDocument()  );
		return l_Tag;
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

	public void execute(XmlObject x_XmlObject) throws Exception {
		m_XmlObject=x_XmlObject;
		m_Attachments=new ArrayList<Object>();
		m_AttachmentsName=new ArrayList<String>();
		
		m_SessionId=x_XmlObject.getSignature();
		this.setTerminal(x_XmlObject.getTerminal());
		this.setUserId(x_XmlObject.getUserId());
		
		SDLogger.getApplicationLog().add(this,"Send Email begin, signature:"+this.getSignature(),"" );		
		sendEmail();
		SDLogger.getApplicationLog().add(this,"Send Email end, signature:"+this.getSignature(),"" );		
	}



}

