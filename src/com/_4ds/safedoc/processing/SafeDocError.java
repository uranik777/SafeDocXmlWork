package com._4ds.safedoc.processing; 

import java.io.Serializable;

public interface SafeDocError extends Serializable{ 
	
	public static String K_ERROR   = "E";
	public static String K_WARNING = "W";
	public static String K_INFO    = "I";
	
	public String getMessage();
	public String getLocalizedMessage();
	public String getReplyCode();
	
}
