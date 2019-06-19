package com._4ds.safedoc.processing.impl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com._4ds.eclient.NskCall.EClientError;
import com._4ds.safedoc.processing.SafeDocError;

/**
 * @author Yan
 * @author AlexD updated: 20090120
 *
 */

public class SafeDocErrorImpl implements SafeDocError {
	
	private static final long serialVersionUID = 7318595378613735889L;

	private static final String S_PATH_TO_RESOURCES = "com._4ds.safedoc.locexceptions.ICExceptionResource";
	
	protected String m_Key=null;
	protected String m_LanguageCode;
	protected String m_Value1=null;
	
	protected String m_PathToResources=S_PATH_TO_RESOURCES;
	protected Throwable m_CauseException=null;
	
	protected String m_Severity;

	private EClientError m_ClientError=null;

	private String m_Message;
	
	public SafeDocErrorImpl(EClientError Xi_ClientError){
		m_ClientError=Xi_ClientError;
		m_Severity=Xi_ClientError.getSeverity();
	}

	public SafeDocErrorImpl(String Xi_Message){
		m_Severity=K_ERROR;
		m_Message=Xi_Message;
	}

	public SafeDocErrorImpl(String Xi_Key, String Xi_Severity){
		validateKey(Xi_Key);
		m_Severity=Xi_Severity;
	}

	public SafeDocErrorImpl(String Xi_Key, String Xi_Severity, String Xi_Value){
		validateKey(Xi_Key);
		m_Severity=Xi_Severity;
		m_Value1=Xi_Value;
	}

	public String getMessage(){
		if(m_ClientError==null){
			if(m_Key==null)
				return m_Message;
			else
				return getLocalizedMessage();
		}else
			return m_ClientError.getError();

	}

	public String getLocalizedMessage(){
		ResourceBundle l_rb=null;
		String l_error= new String();
		try{
			Locale l_Loc = getLocale();
			l_rb=ResourceBundle.getBundle(m_PathToResources,l_Loc);
			l_error= l_error+l_rb.getString(m_Key);
			if(	m_Value1!=null){
					l_error=MessageFormat.format(l_error,m_Value1);
			}
		}catch(MissingResourceException e){
			if(	m_Value1!=null){
				l_error=m_Value1 + "; " + l_error+m_Key+" "+e.getMessage();
			}else{
				l_error=l_error+m_Key+" "+e.getMessage();
			}
		}
		return l_error;
	}

	
	private Locale getLocale() {
		if(m_LanguageCode!=null){
			return new Locale(m_LanguageCode);
		}else{
			return Locale.getDefault();
		}
	}

	
	private void validateKey(String Xi_Key){
		if(Xi_Key!=null ){
			m_Key=Xi_Key;
		}else{
			m_Key=null;
		}

	}

	public String getReplyCode() {
		if(m_ClientError==null && m_Key!=null)
			return m_Key;
		else
			return "999";
	}
	
}

