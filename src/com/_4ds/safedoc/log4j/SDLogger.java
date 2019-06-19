package com._4ds.safedoc.log4j;

/*
 * Created on Oct 8, 2004
 * Updated on Jan 20, 2009
 */

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.apache.log4j.PropertyConfigurator;

import com._4ds.safedoc.log.SDLogEntryImpl;
import com._4ds.safedoc.logging.SDLog;
import com._4ds.safedoc.logging.SDLoggable;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;

public class SDLogger extends Logger implements SDLog{
	
  private static HashMap<String,SDLogger> m_oLoggerList = null;
	//
  private static final String sLOGGER_FILE = "log4jSD.properties";    
	//
  public static final String sLOGGER_NAME_SAFE_DOC = "SafeDoc";   
  private static final String sLOGGER_PATH="C:\\ECLog\\";

  private static final String sLOG4J_APPENDER_SAFE_DOC = "SafeDocRollingXMLFile";   

  static final String OPEN_LOG_RECORDS_GROUP = "Open"; 
  static final String CLOSE_LOG_RECORDS_GROUP = "Close"; 
  
  private String m_pathToLog = null;
  private String m_loggerName = null;
  
  private static boolean m_isECVLoggerDefined = false;
	//
  public static final int sSeverityDebug = 1;
  public static final int sSeverityInfo = 2;
  public static final int sSeverityWarning = 3;
  public static final int sSeverityError = 4;
  public static final int sSeverityFatal = 5;
	//
  /*
   * ELLogger()
   */
  private SDLogger(){
	  super(sLOGGER_NAME_SAFE_DOC);
    m_loggerName = sLOGGER_NAME_SAFE_DOC;
	  setPathToLog( null );
	  loggerInit();
  }
  
  private SDLogger( String Xi_pathToLog, String Xi_LoggerName ){
    super(Xi_LoggerName);
    m_loggerName = Xi_LoggerName;
    setPathToLog( Xi_pathToLog );
	  loggerInit();
  }
  
  private void setPathToLog( String Xi_path ){
	  if( Xi_path != null && Xi_path.length() > 0 ){
		  m_pathToLog = Xi_path;
	  } else {
		  m_pathToLog = sLOGGER_PATH;  
	  }
  }
  
  public static SDLogger getLog(){
    // Customized ECLogger
    SDLogger oLogger = null;
	  if(m_oLoggerList==null){
		  synchronized(SDLogger.class){
			  if(m_oLoggerList==null){
          m_oLoggerList = new HashMap<String,SDLogger>();
				  oLogger = new SDLogger();
          m_oLoggerList.put( sLOGGER_NAME_SAFE_DOC, oLogger);
			  }
		  }
      return oLogger; 
	  }
    oLogger = m_oLoggerList.get( sLOGGER_NAME_SAFE_DOC ); 
    if (oLogger == null){
      synchronized(SDLogger.class){
        oLogger = m_oLoggerList.get( sLOGGER_NAME_SAFE_DOC ); 
        if (oLogger == null){
          oLogger= new SDLogger();
          m_oLoggerList.put( sLOGGER_NAME_SAFE_DOC, oLogger);
        }
      }
    }
    return oLogger; 
  }
  
  public static SDLogger getLog( String Xi_pathToLog, String Xi_loggerName ){
	  SDLogger oLogger = null;
    if(m_oLoggerList==null){
      synchronized(SDLogger.class){
        if(m_oLoggerList==null){
          m_oLoggerList = new HashMap<String,SDLogger>();
				  oLogger= new SDLogger( Xi_pathToLog, Xi_loggerName );
          m_oLoggerList.put( Xi_loggerName, oLogger);
			  }
		  }
      return oLogger; 
	  }
    oLogger = m_oLoggerList.get( Xi_loggerName ); 
    if (oLogger == null){
      synchronized(SDLogger.class){
        oLogger = m_oLoggerList.get( Xi_loggerName ); 
        if (oLogger == null){
          oLogger= new SDLogger( Xi_pathToLog, Xi_loggerName );
          m_oLoggerList.put( Xi_loggerName, oLogger);
        }
      }
    }
    return oLogger; 
  }

  public static SDLogger getApplicationLog(){
    if(m_oLoggerList==null){
      return null; 
    }
    return m_oLoggerList.get( sLOGGER_NAME_SAFE_DOC ); 
  }
  public static SDLogger getApplicationLog( String Xi_loggerName ){
	    if(m_oLoggerList==null){
	      return null; 
	    }
	    return m_oLoggerList.get( Xi_loggerName ); 
	  }
  
  public SDLogger getLog( String Xi_loggerName ){
    if(m_oLoggerList==null){
      return null; 
    }
    return m_oLoggerList.get( Xi_loggerName ); 
  }
	/*
   * Logger Initialization 
   */
  private void loggerInit() {
	  
	String sFileName = m_pathToLog + sLOGGER_FILE;
	java.io.File oFile = new java.io.File(sFileName);
	if (!oFile.exists()){
	       System.out.println("Logger configuration failed. Configuration file not found at " + sFileName );
	       // here throw exception todo
	}
	
	PropertyConfigurator.configure(sFileName);
    setLevel(Level.INFO);
  
    if ( !m_isECVLoggerDefined ){
     if ( getLogger(m_loggerName).getAppender(sLOG4J_APPENDER_SAFE_DOC) != null ){
        m_isECVLoggerDefined = true;
      }else{
        System.out.println("Logger com._4ds.safedoc.log4j.ECLogger is called without ECLogView compatible configuration: " + sFileName);
        m_isECVLoggerDefined = false;
      }
    }
  }
    
  public void setNDC() {
    NDC.push( Thread.currentThread().getName() );
  }
  public void setNDC(String sNDC) {
    NDC.push( sNDC );
  }
  public void releaseNDC() {
    NDC.remove();
  }
  public void begin(SDLoggable Xi_Data) {
  if ( !m_isECVLoggerDefined ){
    getLogger(m_loggerName).log(Level.INFO,getUserSignature(Xi_Data)+" Start "+Xi_Data.getClass().getSimpleName()); 
  }else{
    String l_GroupFlag = null;
    String l_logLevel = Level.DEBUG.toString();
    if ( Xi_Data instanceof  SafeDocMessageProcessor ){
      l_GroupFlag = OPEN_LOG_RECORDS_GROUP;
      l_logLevel = Level.INFO.toString();
    }
    getLogger(m_loggerName).info(
        new SDLogEntryImpl(
        	Xi_Data.getSignature(),        //String signature,
            l_logLevel,                           //String status,
            Xi_Data.getUserId(),    // String user
            Xi_Data.getTerminal(),  // String terminal
            Xi_Data.getOperation(),       // String crs
            Xi_Data.getRequestType(),       // RequestType
            Xi_Data.getClass().getSimpleName(), // processName
            Xi_Data.getReservationId(), // reservationId
            l_GroupFlag, // groupFlag
            Xi_Data.getDocumentId(),         // External reference
            null, // String timeSpent
            "Start",                          // String description
            null));                           // String message
  }
}

public void end(SDLoggable Xi_Data) {
  if ( !m_isECVLoggerDefined ){
    getLogger(m_loggerName).log(Level.INFO,getUserSignature(Xi_Data)+" End "+Xi_Data.getClass().getSimpleName()); 
  }else{
    String l_GroupFlag = null;
    String l_logLevel = Level.DEBUG.toString();
    if ( Xi_Data instanceof  SafeDocMessageProcessor ){
      l_GroupFlag = CLOSE_LOG_RECORDS_GROUP;
      l_logLevel = Level.INFO.toString();
    }
    getLogger(m_loggerName).info(
            new SDLogEntryImpl(
        	Xi_Data.getSignature(),        //String signature,
            l_logLevel,                           //String status,
            Xi_Data.getUserId(),    // String user
            Xi_Data.getTerminal(),  // String terminal
            Xi_Data.getOperation(),       // String crs
            Xi_Data.getRequestType(),       // RequestType
            Xi_Data.getClass().getSimpleName(), // processName
            Xi_Data.getReservationId(), // reservationId
            l_GroupFlag, // groupFlag
            Xi_Data.getDocumentId(),         // External reference
            null, // String timeSpent
            "End",                          // String description
            null));                           // String message
  }
}

public void end(SDLoggable Xi_Data, String Xi_logLevel) {
  Level l_logLevel = Level.toLevel(Xi_logLevel);
  if ( !m_isECVLoggerDefined ){
    getLogger(m_loggerName).log(l_logLevel,getUserSignature(Xi_Data)+" End "+Xi_Data.getClass().getSimpleName()); 
  }else{
    String l_GroupFlag = null;
    if ( Xi_Data instanceof  SafeDocMessageProcessor ){
      l_GroupFlag = CLOSE_LOG_RECORDS_GROUP;
    }
    getLogger(m_loggerName).info(
            new SDLogEntryImpl(
            Xi_Data.getSignature(),        //String signature,
            Xi_logLevel,    //String status,
            Xi_Data.getUserId(),    // String user
            Xi_Data.getTerminal(),  // String terminal
            Xi_Data.getOperation(),       // String crs
            Xi_Data.getRequestType(),       // RequestType
            Xi_Data.getClass().getSimpleName(), // processName
            Xi_Data.getReservationId(), // reservationId
            l_GroupFlag, // groupFlag
            Xi_Data.getDocumentId(),         // External reference
            null, // String timeSpent
            "End",                          // String description
            null));                           // String message
  }
}

private String getUserSignature(SDLoggable Xi_Data){
	return "User: "+Xi_Data.getUserId()+" Terminal:"+Xi_Data.getTerminal();
}
/*
private String getSignature(ECLoggable Xi_Data){
  //return Xi_Data.getUser().GetUserId() +"#"+ Xi_Data.getUser().GetTerminal() +"#"+ Xi_Data.getUser().GetMessageId();
  return Xi_Data.getUser().GetUserId() 
  +"#"+ Xi_Data.getUser().GetTerminal(); 
}
*/
/*
private String getItemId(ECLoggable Xi_Data){
  //return Xi_Data.getUser().GetUserId() +"#"+ Xi_Data.getUser().GetTerminal() +"#"+ Xi_Data.getUser().GetMessageId();
  return Xi_Data.getUserId() 
  +"#"+ Xi_Data.getTerminal()
  +"#"+ Xi_Data.getMessageId();
}
*/

	public void add(SDLoggable Xi_Data,Exception Xi_Exception) {
	if(Xi_Data!=null){
      if ( !m_isECVLoggerDefined ){
        getLogger(m_loggerName).log(Level.ERROR,getUserSignature(Xi_Data)+" "+Xi_Exception.getMessage(),Xi_Exception);
      }else{
        getLogger(m_loggerName).error(
          new SDLogEntryImpl(
          	Xi_Data.getSignature(),        //String signature,
          	Level.ERROR.toString(),                           //String status,
            Xi_Data.getUserId(),    // String user
            Xi_Data.getTerminal(),  // String terminal
            Xi_Data.getOperation(),       // String crs
            Xi_Data.getRequestType(),       // RequestType
            Xi_Data.getClass().getSimpleName(), // processName
            Xi_Data.getReservationId(), // reservationId
            null, // groupFlag
            Xi_Data.getDocumentId(),         // External reference
              null, // String timeSpent
              "Exception",                          // String description
              Xi_Exception.getMessage()),           // String message
              Xi_Exception);         
      }
		}
		else{
      if ( !m_isECVLoggerDefined ){
        getLogger(m_loggerName).log(Level.ERROR,"User: unknown",Xi_Exception);
      }else{
        getLogger(m_loggerName).log(
              Level.ERROR,
              new SDLogEntryImpl(
              "System",        //String signature,
              Level.ERROR.toString(),                           //String status,
              "System",    // String user
              null,  // String terminal
              null,       // String crs
              null,
              null,
              null,
              null,
              null,
              null, // String timeSpent
              "Error",                          // String description
              Xi_Exception.getMessage()),       // String message
              Xi_Exception
              );
      }
		}
	}

  public void add(SDLoggable Xi_Data, String Xi_Message, String Xi_Description) {
    if ( !m_isECVLoggerDefined ){
      getLogger(m_loggerName).log(Level.INFO,getUserSignature(Xi_Data)+ " " + Xi_Description + " : " + Xi_Message);
    }else{
      getLogger(m_loggerName).info(
        new SDLogEntryImpl(
          	Xi_Data.getSignature(),        //String signature,
          	Level.INFO.toString(),                           //String status,
            Xi_Data.getUserId(),    // String user
            Xi_Data.getTerminal(),  // String terminal
            Xi_Data.getOperation(),       // String crs
            Xi_Data.getRequestType(),       // RequestType
            Xi_Data.getClass().getSimpleName(), // processName
            Xi_Data.getReservationId(), // reservationId
            null, // groupFlag
            Xi_Data.getDocumentId(),         // External reference
            null, // String timeSpent
            Xi_Description,                      // String description
            Xi_Message));                        // String message
     }
  }

  public void addError(SDLoggable Xi_Data, String Xi_Message, String Xi_Description) {
    if ( !m_isECVLoggerDefined ){
      getLogger(m_loggerName).log(Level.ERROR,getUserSignature(Xi_Data)+ " " + Xi_Description + " : " + Xi_Message);
    }else{
      getLogger(m_loggerName).info(
        new SDLogEntryImpl(
          	Xi_Data.getSignature(),        //String signature,
          	Level.ERROR.toString(),                           //String status,
            Xi_Data.getUserId(),    // String user
            Xi_Data.getTerminal(),  // String terminal
            Xi_Data.getOperation(),       // String crs
            Xi_Data.getRequestType(),       // RequestType
            Xi_Data.getClass().getSimpleName(), // processName
            Xi_Data.getReservationId(), // reservationId
            null, // groupFlag
            Xi_Data.getDocumentId(),         // External reference
            null, // String timeSpent
            Xi_Description,                      // String description
            Xi_Message));                        // String message
     }
  }


}
