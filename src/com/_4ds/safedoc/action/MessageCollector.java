package com._4ds.safedoc.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com._4ds.safedoc.log4j.SDLogger;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.servlet.SafeDocServlet;

public class MessageCollector implements SDLoggable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	XmlObject m_XmlObject;
	private String m_SessionId;
	
	
	
	public void setSignature(String sessionId) {
		m_SessionId = sessionId;
	}






	private void makeSingleDocument() throws ParserConfigurationException, SAXException, IOException {
		String l_xmlpartsFolder=SafeDocServlet.get_config().getSavedXmlPartsPath() ;
		java.io.File l_File=new java.io.File(l_xmlpartsFolder);
		File[] l_FileArray=l_File.listFiles();
		if(l_FileArray!=null)
		{
			for(int i=0;i<l_FileArray.length;i++)
			{
				try {
					if(m_XmlObject.getPartId(l_FileArray[i]).equals(m_XmlObject.getPartId() ) )
					{
						//make document
						NodeList l_NodeList=getPartsList(l_FileArray[i]);
						insertPartsList(l_NodeList);
						l_FileArray[i].delete();
					}
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}

	


	private boolean isLastPart() throws XPathExpressionException {
		String l_String = getTag("/"+m_XmlObject.getM_msgType()+"/Delivery/batchEnd");
		if (l_String == null) {
			return false;
		}
		if (l_String.equals("E")) {
			// last Part
			return true;
		}
		return false;
	}



	
	private String getTag(String x_string) throws XPathExpressionException {
		XPathFactory  factory=XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		XPathExpression  xPathExpression= xPath.compile(x_string);
		String l_Tag=xPathExpression.evaluate( m_XmlObject.getM_XmlDocument()  );
		return l_Tag;
	}	
	
	private NodeList getPartsList(File x_file) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		if(m_XmlObject.getM_msgType().equals("SafeDocMessage")){
			DocumentBuilderFactory l_dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder l_docBuilder = l_dbfac.newDocumentBuilder();
			Document l_Document=l_docBuilder.parse(x_file );
			return l_Document.getElementsByTagName("Data"); 
		}
		if(m_XmlObject.getM_msgType().equals("SafeAdvMessage")){
			DocumentBuilderFactory l_dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder l_docBuilder = l_dbfac.newDocumentBuilder();
			Document l_Document=l_docBuilder.parse(x_file );
			return l_Document.getElementsByTagName("Reservation"); 
		}
		return null;
	}

	
	
    public void writeXml(Document doc, OutputStream out) throws IOException {

        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            DocumentType dt = doc.getDoctype();
            if (dt != null) {
                String pub = dt.getPublicId();
                if (pub != null) {
                    t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, pub);
                }
                t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dt.getSystemId());
            }

            t.setOutputProperty(OutputKeys.INDENT, "yes"); // NOI18N

            Source source = new DOMSource(doc);
            Result result = new StreamResult(out);
            t.transform(source, result);
        } catch (Exception e) {
            throw (IOException)new IOException(e.toString()).initCause(e);
        } catch (TransformerFactoryConfigurationError e) {
            throw (IOException)new IOException(e.toString()).initCause(e);
        }
    }	
    
   

	
	private void insertPartsList(NodeList x_NodeList) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		if(m_XmlObject.getM_msgType().equals("SafeDocMessage")){
			Document l_Document=m_XmlObject.getM_XmlDocument();
			Node l_FirstDataNode=l_Document.getElementsByTagName("Data").item(0);
			for(int i=0;i<x_NodeList.getLength();i++)
			{
				Node adoptedNode = l_Document.adoptNode(x_NodeList.item(i));
				l_FirstDataNode.getParentNode().insertBefore(adoptedNode,l_FirstDataNode ) ;
			}
			m_XmlObject.setM_XmlDocument(l_Document);
		}
		if(m_XmlObject.getM_msgType().equals("SafeAdvMessage")){
			Document l_Document=m_XmlObject.getM_XmlDocument();
			Node l_FirstDataNode=l_Document.getElementsByTagName("Reservation").item(0);
			for(int i=0;i<x_NodeList.getLength();i++)
			{
				Node adoptedNode = l_Document.adoptNode(x_NodeList.item(i));
				l_FirstDataNode.getParentNode().insertBefore(adoptedNode,l_FirstDataNode ) ;
			}

			m_XmlObject.setM_XmlDocument(l_Document);
		}
		
		
	}
	
	
	public String getRandomFileName() {
        return (new Double(Math.random()*10000000).toString() );
    }
	
	private void writeXmlPart() throws IOException {
		String l_xmlpartsFolder=SafeDocServlet.get_config().getSavedXmlPartsPath();
		FileOutputStream l_FileOutputStream=new FileOutputStream(l_xmlpartsFolder+"\\"+getRandomFileName() );
		l_FileOutputStream.write(doc2byte(m_XmlObject.getM_XmlDocument()));
		l_FileOutputStream.close();
	}
	
    public byte[] doc2byte( Document x_doc ) throws IOException {
    	ByteArrayOutputStream l_OutputStream;
    	l_OutputStream=new ByteArrayOutputStream();
    	writeXml(x_doc,l_OutputStream);
    	return l_OutputStream.toByteArray() ;
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



	public XmlObject execute(XmlObject x_XmlObject) throws Exception {
		SDLogger.getApplicationLog().add(this,"Collect xml: "+this.getSignature(),"begin");		
		
		m_XmlObject=x_XmlObject;
	    
    	if(isLastPart()==true)
    	{
    		makeSingleDocument();
    	}
    	else
    	{
	    	writeXmlPart();
	    	return null;
    	}
	    
		SDLogger.getApplicationLog().add(this,"Collect xml: "+getSignature(),"end");		
	    return m_XmlObject;
	}

}
