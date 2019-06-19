package com._4ds.safedoc.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.servlet.SafeDocServlet;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class XmlObject implements Serializable, SDLoggable {

	private static final long serialVersionUID = 1L;
	public XmlObject() throws IOException {
		super();
	}
	
	private String m_msgType;
	public String getM_msgType() {
		return m_msgType;
	}
	public void setM_msgType(String type) {
		m_msgType = type;
	}
	
	
	
	
	private String m_SessionId;
	private String m_Terminal;
	private String m_UserId;
	
	

	
	
	private String m_ObjectFileName;
	
	
	
	
	Document m_XmlDocument;
	
	public Document getM_XmlDocument() {
		return m_XmlDocument;
	}
	
	public void setM_XmlDocument(Document xmlDocument) {
		m_XmlDocument = xmlDocument;
	}

	public boolean isMultiPart() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		if(checkSavedXmlDocs()==true)
		{
			return true;
		}
		
		String l_String=getTag("/"+m_msgType+"/Delivery/batchEnd");
		if(l_String==null)
		{
			return false;
		}
		else
		{
			if( l_String.compareToIgnoreCase("C")==0 || l_String.compareToIgnoreCase("E")==0 ) 
			{
				
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	
	private String getTag(String x_string) throws XPathExpressionException {
		XPathFactory  factory=XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		XPathExpression  xPathExpression= xPath.compile(x_string);

		String l_Tag=xPathExpression.evaluate( m_XmlDocument  );
		return l_Tag;
	}	
	
	
	private boolean checkSavedXmlDocs() throws ParserConfigurationException, SAXException, IOException {
		
		String l_xmlpartsFolder=SafeDocServlet.get_config().getSavedXmlPartsPath();
		java.io.File l_File=new java.io.File(l_xmlpartsFolder);
		File[] l_FileArray=l_File.listFiles();
		if(l_FileArray!=null)
		{
			for(int i=0;i<l_FileArray.length;i++)
			{
				
				try {
					if(getPartId(l_FileArray[i])==getPartId())
					{
						return true;
					}
				} catch (XPathExpressionException e) {
					
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public Object getPartId(File x_file) throws XPathExpressionException, FileNotFoundException {
		
		XPathFactory  factory=XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		XPathExpression  xPathExpression= xPath.compile("/"+m_msgType+"/msgId");
		InputStream l_InputStream=new java.io.FileInputStream(x_file);
		InputSource l_inputSource=new InputSource(l_InputStream);
		String l_Tag=xPathExpression.evaluate(l_inputSource);
		return l_Tag;
	}	


	
	public  Object getPartId() throws XPathExpressionException {
		XPathFactory  factory=XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		XPathExpression  xPathExpression= xPath.compile("/"+m_msgType+"/msgId");
		String l_Tag=xPathExpression.evaluate( m_XmlDocument  );
		return l_Tag;
	}
	
	public boolean isFileAction() throws XPathExpressionException {
	    if(getTag("/"+m_msgType+"/Delivery/type").compareToIgnoreCase("file")==0) {
	    	return true;
	    }
	    return false;
	}
	
	public boolean isEmailAction() throws XPathExpressionException {
	    if(getTag("/"+m_msgType+"/Delivery/type").compareToIgnoreCase("email")==0) {
		    return true;
	    }
	    return false;
	}

	public boolean isPrintAction() throws XPathExpressionException {
	    if(getTag("/"+m_msgType+"/Delivery/type").compareToIgnoreCase("print")==0) {
		    return true;
	    }
	    return false;
	}
	public void setM_ObjectFileName(String m_ObjectFileName) {
		this.m_ObjectFileName = m_ObjectFileName;
	}
	public String getM_ObjectFileName() {
		return m_ObjectFileName;
	}
	
	public String get_Delivery_tranCode() throws XPathExpressionException {
		String l_tranCode=getTag("/"+m_msgType+"/Delivery/tranCode");
	    return l_tranCode;
	}
	
	
	public boolean needExtWebService() {
	    try {
	    	String l_useWS=getTag("/"+m_msgType+"/Delivery/useWS");
			if(l_useWS.compareToIgnoreCase("n")==0 || l_useWS.compareToIgnoreCase("no")==0) {
			    return false;
			}
			if(l_useWS.compareToIgnoreCase("y")==0 || l_useWS.compareToIgnoreCase("yes")==0 ) {
				SDLogger.getApplicationLog().add(this,"Need send to External Web Sevice, useWS tag:"+l_useWS+" signature:"+this.getSignature() ,"");
				return true;
			}
			if(l_useWS.compareToIgnoreCase("")==0){
				SDLogger.getApplicationLog().add(this,"Unknown value of useWS tag:"+l_useWS+" signature:"+this.getSignature() ,"");
			}
		} catch (Exception e) {
		}
		if( SafeDocServlet.get_config().getSendToWebService().compareToIgnoreCase("true")==0){
			SDLogger.getApplicationLog().add(this,"Need send to External Web Sevice, sendToWebService:"+SafeDocServlet.get_config().getSendToWebService()+" signature:"+this.getSignature() ,"");
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public String getString() throws IOException {
		OutputFormat of = new OutputFormat("XML","iso-8859-15",true); 
		XMLSerializer serializer = new XMLSerializer();
		serializer.setOutputFormat(of);
		ByteArrayOutputStream l_ByteArrayOutputStream=new ByteArrayOutputStream();
		serializer.setOutputByteStream(l_ByteArrayOutputStream); 
		serializer.serialize(m_XmlDocument);
		return l_ByteArrayOutputStream.toString();
	}
	
	
	
	private NodeList getDataList() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		Document l_Document=m_XmlDocument;
		return l_Document.getElementsByTagName("Data"); 
	}
	

	
	public String getXslTemplate() throws XPathExpressionException {
		String l_XslTemplate=getTag("/"+this.getM_msgType()+"/Data/Header/template");
		return l_XslTemplate;
	}	
	
	
	String m_XslName;
	
	
	
	private Document removeDataList() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		Document l_Document=this.getM_XmlDocument();
		NodeList l_NodeList;
		l_NodeList=l_Document.getElementsByTagName("Data");
		for(int i=0;i<l_NodeList.getLength();i++){
			l_Document.getFirstChild().removeChild(l_NodeList.item(i));
		}
		
		return l_Document; 
	}
	
	
	
	private byte[] NodeToXml(Node x_node) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException
	{
		Document document=removeDataList();
		Node adoptedNode = document.adoptNode(x_node);
		document.getFirstChild().appendChild(adoptedNode);
        
		OutputFormat of = new OutputFormat("XML","iso-8859-15",true); // or "UTF-8"
		XMLSerializer serializer = new XMLSerializer();
		serializer.setOutputFormat(of);
		ByteArrayOutputStream l_ByteArrayOutputStream=new ByteArrayOutputStream();
		serializer.setOutputByteStream(l_ByteArrayOutputStream); //(new FileOutputstream("xmlfile.xml"));
		serializer.serialize(document);
		return l_ByteArrayOutputStream.toByteArray();
		
	}
	
	
	
	private boolean isPdf() throws XPathExpressionException {
		String l_str=getXslTemplate();
		if(l_str.endsWith(".pdf.xsl")){
			return true;
		}
		return false;
	}
	
	
	private byte[] transform(Node x_node) throws XPathExpressionException, TransformerException, IOException, ParserConfigurationException, SAXException, SDException {
		m_XslName=getXslTemplate();
	    String templatesPath=SafeDocServlet.get_config().getProperty("templatesPath");
	    java.io.File tp = new java.io.File(templatesPath);
	    if(tp.exists()==false){
	    	throw new SDException(14,"Templates Path Not found, check "+templatesPath);
	    }
	    	
	    java.io.File l_xsltfile = new java.io.File(templatesPath+m_XslName);
        byte[] l_xml=NodeToXml(x_node);
        ByteArrayInputStream l_ByteArrayInputStream = new ByteArrayInputStream(l_xml);
        java.io.FileInputStream l_FileInputStream = new java.io.FileInputStream(l_xsltfile);
        Transform l_Transform=new Transform();
        l_Transform.setM_xmlStream(l_ByteArrayInputStream);
        l_Transform.setM_xsltStream(l_FileInputStream);
        l_Transform.setSignature( getSignature() );
        l_Transform.setTerminal(m_Terminal);
        l_Transform.setUserId(m_UserId);
        
        byte[] l_retval;
        if(isPdf()){
            l_retval=l_Transform.xml2pdf();
        }
        else{
            l_retval=l_Transform.xml2html();
        }
        l_ByteArrayInputStream.close();
        l_FileInputStream.close();
        return l_retval; 
	}
	
	
	
	private List<Object> m_DocsList=new ArrayList<Object>();
	
	public List<Object> get_DocsList() {
		return m_DocsList;
	}
	public void set_DocsList(List<Object> docsList) {
		m_DocsList = docsList;
	}
	
	private Node getTag(String x_string, Node x_node) {
		Node l_node=x_node.getFirstChild();
		do{
			if(l_node.getNodeName().equals(x_string)){
				return l_node;
			}
			l_node=l_node.getNextSibling();
		} while(l_node!=null);
		return l_node;
	}
	

	private String getFileExtension() throws XPathExpressionException {
		String l_reply=null;
		String l_xslfilename=this.getXslTemplate();
		String l_ext=l_xslfilename;
		if(l_ext.contains(".")){
			while(l_ext.contains(".")){
				l_ext=l_ext.substring(1+l_ext.indexOf("."));
			}
			l_reply="."+l_ext;
		}
		else{
			l_reply="";
		}
		
		if(l_reply.equals(".xsl")){
			if(isPdf()){
				l_reply=".pdf";
			}
			else{
				l_reply=".html";
			}
		}
		
		return l_reply;
	}
	
	
	
	private String makeFileName(Node x_node) throws XPathExpressionException {
		Node l_node=getTag("id",getTag("Header",x_node));
		String l_id=l_node.getNodeValue();
		if(l_id==null){
			l_id=l_node.getTextContent();
		}
		if(l_id==null){
			l_id=l_node.getFirstChild().getNodeValue();
		}
		String docPrefics="Doc_";
		return docPrefics+l_id+getFileExtension();
	}
	
	
	


	private String getFileNameWitoutExtension(String x_string) {
		if(x_string.contains(".")){
			return(x_string.substring(0,x_string.indexOf(".")));
		}
		return x_string;
	}
	
	
	
	private String getAttachmentName(Node x_node) throws XPathExpressionException {
		String docDir=SafeDocServlet.get_config().getProperty("savedFilesPath")+"Documents\\";
		String addressTag=getTag("/"+this.getM_msgType()+"/Delivery/address");
		String l_reply = null;
		
		FileOperations l_FileOperations=new FileOperations(); 
		
		if(addressTag.contains("@")){
			l_reply=makeFileName(x_node);
		}
		else{
			if(l_FileOperations.isDiskDirFilename(addressTag))
			{
				l_reply=addressTag;
			}
			if(l_FileOperations.isDirFilename(addressTag))
			{
				l_reply=docDir+addressTag;
			}
			if(l_FileOperations.isFilename(addressTag))
			{
				l_reply=docDir+addressTag;
			}
			if(l_FileOperations.isDiskDir(addressTag))
			{
				l_reply=makeFileName(x_node);
			}
			if(l_FileOperations.isDir(addressTag))
			{
				l_reply=docDir+addressTag+makeFileName(x_node);
			}
			if(addressTag.length()==0 || addressTag==null)
			{
				l_reply=docDir+makeFileName(x_node);
			}
		}
			
			

		
		boolean found;
        do{
        	
        	found=false;
			for(int i=0;i<m_DocsList.size() ;i++){
				String l_string=(String)((DocObject) m_DocsList.get(i)).get_name();
				if(l_string.equals(l_reply)){
					found=true;
		            Calendar cal = Calendar.getInstance();
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		            String l_timestring=sdf.format(cal.getTime());
		            l_reply=getFileNameWitoutExtension(l_reply)+"_"+l_timestring+getFileNameExtension(l_reply);
				}
			}
        }while(found==true);
		
		return l_reply;
	}	
	
	
	
	private String getSavedFileName(Node x_node) throws XPathExpressionException {
		
		String l_docFolderName = "";
		if(this.getM_msgType().equals("SafeAdvMessage")){
			l_docFolderName="Advising";
		}
		if(this.getM_msgType().equals("SafeDocMessage")){
			l_docFolderName="Documents";
		}
		
		FileOperations l_FileOperations=new FileOperations(); 
		
		String docDir=SafeDocServlet.get_config().getProperty("savedFilesPath")+l_docFolderName+"\\";
		l_FileOperations.checkFolder(docDir);
		String addressTag=getTag("/"+this.getM_msgType()+"/Delivery/address");
		String l_reply = null;

		if(l_FileOperations.isDiskDirFilename(addressTag))
		{
			l_reply=addressTag;
		}
		if(l_FileOperations.isDirFilename(addressTag))
		{
			l_reply=docDir+addressTag;
		}
		if(l_FileOperations.isFilename(addressTag))
		{
			l_reply=docDir+addressTag;
		}
		if(l_FileOperations.isDiskDir(addressTag))
		{
			l_reply=makeFileNameF(x_node);
		}
		if(l_FileOperations.isDir(addressTag))
		{
			l_reply=docDir+addressTag+makeFileNameF(x_node);
		}
		if(addressTag.length()==0 || addressTag==null)
		{
			l_reply=docDir+makeFileNameF(x_node);
		}
		
		l_reply=checkAndAddExtension(l_reply);
		
		java.io.File l_File;
        do{
        	l_File=new java.io.File( l_reply );
        
	        if(l_File.exists() ){
	            Calendar cal = Calendar.getInstance();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	            String l_timestring=sdf.format(cal.getTime());
	            l_reply=getFileNameWitoutExtension(l_reply)+"_"+l_timestring+getFileNameExtension(l_reply);
	        }
        }while(l_File.exists());
		
		
		return l_reply;
	}	
	
	
	private String makeFileNameF(Node x_node) throws XPathExpressionException {
		
		String l_id="";
		
		if(this.getM_msgType().equals("SafeDocMessage")){
			Node l_node=getTag("template",getTag("Header",x_node));
			l_id=l_id+l_node.getTextContent()+"_";
			l_node=getTag("id",getTag("Reservation",x_node));
			l_id=l_id+l_node.getTextContent()+"_";
		}
		
		if(this.getM_msgType().equals("SafeAdvMessage")){
			Node l_node=getTag("template",getTag("Header",x_node));
			l_id=l_id+getFileNameWitoutExtension( l_node.getTextContent() )+"_";
			l_node=getTag("id",getTag("Header",x_node));
			l_id=l_id+l_node.getTextContent()+"_";
		}
		

		String l_FileNamePrefics="Doc_";
		if(this.getM_msgType().equals("SafeDocMessage"))
		{
			l_FileNamePrefics="Doc_";
		}
		if(this.getM_msgType().equals("SafeAdvMessage"))
		{
			l_FileNamePrefics="Adv_Doc_";
		}		
		return l_FileNamePrefics+l_id+getFileExtension();
	}
	
	
	
	private String checkAndAddExtension(String x_name) throws XPathExpressionException {
		String l_ext=getFileNameExtension(x_name);
		if(l_ext==null){
			x_name=addFileNameExtension(x_name);
		}
		return x_name;
	}





    private String addFileNameExtension(String x_name) throws XPathExpressionException {
		String ext2=getFileNameExtension(m_XslName);
    	if(ext2==".xsl"){
			if( isPdf()==true )
				ext2=".pdf";
			else
				ext2=".html";
    		
    	}
		return ext2;
	}

	public String getFileNameExtension(String name) {
		String l_reply = null;
		String l_ext = name;
		if (l_ext.contains(".")) {
			while (l_ext.contains(".")) {
				l_ext = l_ext.substring(1 + l_ext.indexOf("."));
			}
			l_reply = "." + l_ext;
		} else {
			l_reply = "";
		}
		return l_reply;
	}	
	
//	private String getFileNameExtension(String x_string) {
//		if(x_string.contains(".")){
//			return(x_string.substring(x_string.indexOf(".")));
//		}
//		return "";
//	}
	
	
	public void divideIntoDocs() throws Exception{
		NodeList l_NodeList=getDataList();
		for(int i=0;i<l_NodeList.getLength();i++ )
		{
			Node l_node=l_NodeList.item(i);
			//m_FileName=getSavedFileName(l_node);
			if(l_node!=null){
				DocObject l_doc=new DocObject();
				l_doc.set_byte( transform(l_node) );
				l_doc.set_name( getAttachmentName( l_node ) );			
				l_doc.set_filename( getSavedFileName(l_node) );
				if(SafeDocServlet.get_config().getProperty("saveIncomingXml").toLowerCase().equals("yes"))
				{
					saveIncomingXml(l_node,l_doc.get_filename());
				}				
				m_DocsList.add(l_doc);
			}
		}
	}
	
	
	

	
	
	private void saveIncomingXml(Node x_node,String x_filename) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
		String l_xmlFileName= x_filename +".xml";
		//checkFolder(l_xmlFileName);
		FileOutputStream l_FileOutputStream=new FileOutputStream( l_xmlFileName );
		l_FileOutputStream.write(NodeToXml(x_node));
		l_FileOutputStream.close();		
	}

	
	
	public void saveObject() throws Exception {
		String l_xmlFileName= getM_ObjectFileName();
		try{
			FileOutputStream l_FileOutputStream=new FileOutputStream( l_xmlFileName );
			l_FileOutputStream.write(XmlToBytes( ));
			l_FileOutputStream.close();
		}
		catch(Exception e){
			throw(new SDException(15,"Cant save incoming object to "+l_xmlFileName+" , check Folder "+SafeDocServlet.get_config().getQueuePath()));
		}
	}
	
	private byte[] XmlToBytes( ) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException
	{
		Document document=this.getM_XmlDocument();
        
		OutputFormat of = new OutputFormat("XML","iso-8859-15",true); // or "UTF-8"
		XMLSerializer serializer = new XMLSerializer();
		serializer.setOutputFormat(of);
		ByteArrayOutputStream l_ByteArrayOutputStream=new ByteArrayOutputStream();
		serializer.setOutputByteStream(l_ByteArrayOutputStream); //(new FileOutputstream("xmlfile.xml"));
		serializer.serialize(document);
		return l_ByteArrayOutputStream.toByteArray();
		
	}	
	
	public void setSignature(String x_SessionId) {
		this.m_SessionId = x_SessionId;
	}
	
	public void setTerminal(String x_Terminal) {
		this.m_Terminal = x_Terminal;
	}
	public void setUser(String x_UserId) {
		this.m_UserId = x_UserId;
	}
	
	
	
	
	
	
	
	@Override
	public String getDocumentId() {
		
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
		return this.m_SessionId;
	}
	@Override
	public String getTerminal() {
		return this.m_Terminal;
	}
	@Override
	public String getUserId() {
		return this.m_UserId;
	}
	

}
