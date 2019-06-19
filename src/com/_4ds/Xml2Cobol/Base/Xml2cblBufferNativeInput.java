package com._4ds.Xml2Cobol.Base;
/*
 * Class: xml2cblBufferNativeInput
 *  
 * Notes:  Buffer content is a flat copy of the C structure
 * Special internal actions:  - byte swapping from the Network byte order
 * All fields are filled up
 * Empty integers are zeros and empty strings are filled by spaces
 * Buffer size is not more than 32K
 */
public class Xml2cblBufferNativeInput extends Xml2cblBufferInput{

  public Xml2cblBufferNativeInput(){
    m_BufferType = XMLBUF_K_Native;
  }
  public boolean skipFiller( 
    short xi_FillerCount 
  ) throws Exception {
    if ( remaining() < xi_FillerCount)
      return true;
    char sFiller;
    for ( int ii = 0; ii < xi_FillerCount; ii++ ){
      sFiller = getChar();
    }
    return true;
  }
}
