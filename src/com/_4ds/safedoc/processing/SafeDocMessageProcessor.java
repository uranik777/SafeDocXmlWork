package com._4ds.safedoc.processing;

import com._4ds.safedoc.logging.SDLoggable;


public interface SafeDocMessageProcessor extends SDLoggable{
	public void execute();
    public Object getReply();
    public SafeDocError getError();
    
    // mask password value if any before log
    public String decorateRequestBeforeLog(String Xi_InputStreamContent); 

	public void setSessionId(String Xi_sessionId);
	public void setTerminal(String xi);
	public void setUserId(String xi);
}
