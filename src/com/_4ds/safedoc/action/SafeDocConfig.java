package com._4ds.safedoc.action;
import java.util.*;
import java.io.*;


public class SafeDocConfig {

	private static final long serialVersionUID = -3226210904748815190L;
	Properties props;
	
	public SafeDocConfig() throws IOException {
		props = new Properties();
		props.load(getClass().getResourceAsStream("SafeDocConfig.properties"));
	}
	
	public String getProperty(String x_property) {
        String reply = props.getProperty(x_property);	
	    return reply;
	}
	
	public String getTemplatesPath() throws IOException {
        String reply = props.getProperty("templatesPath");	
	    return reply;
	}

	public String getSavedFilesPath() throws IOException {
        String reply = props.getProperty("savedFilesPath");	
	    return reply;
	}
	
	public String getWebServiceAddress() throws IOException {
        String reply = props.getProperty("webServiceAddress");	
	    return reply;
	}
	
	public String getSavedXmlPartsPath() throws IOException {
        String reply = props.getProperty("savedXmlPartsPath");	
	    return reply;
	}
	public String getQueuePath() throws IOException {
        String reply = props.getProperty("queuePath");	
	    return reply;
	}

	public String getSendToWebService() {
        String reply = props.getProperty("sendToWebService");	
	    return reply;
	}	
}
