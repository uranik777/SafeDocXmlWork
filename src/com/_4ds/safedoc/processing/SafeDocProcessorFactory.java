package com._4ds.safedoc.processing;


public interface SafeDocProcessorFactory {
     SafeDocMessageProcessor create(String Xi_RequestName, Object sessionId, byte[] Xi_Request) throws Exception;
     byte[] createReply(String Xi_RequestName); 
}
