package com._4ds.Xml2Cobol.Base;
/*
 * Class:xml2cblBufferNativeOutput
 *
 * Buffer content is a flat copy of the C structure
 * Special internal actions:  - byte swapping into Network byte order
 * Empty integers are zeros and empty strings are filled by spaces
 * Buffer size is not more than 32K
 */
public class Xml2cblBufferNativeOutput extends Xml2cblBufferOutput{
  
  public Xml2cblBufferNativeOutput(){
    m_BufferType = XMLBUF_K_Native;
    //this.m_IsResizeProhibited = true; // TODO it means the 30K buffer size limitation
  }
  public int writeNativeString(String xi_Value, int xi_FieldSize){
    if (xi_FieldSize<=0)
      return 0;
    int l_ValueLen = 0;
    if ( xi_Value != null ){
      l_ValueLen = xi_Value.length();
    }
    if (l_ValueLen == xi_FieldSize){
      put(xi_Value);
    }else{
      StringBuffer l_FixedLenValue = new StringBuffer();
      if (l_ValueLen>0){ 
        l_FixedLenValue.append(xi_Value);
        if (l_ValueLen > xi_FieldSize)
          l_FixedLenValue.append(xi_Value);
        else
          l_FixedLenValue.setLength(xi_FieldSize);
      }
      for ( int ii = l_ValueLen; ii < xi_FieldSize; ii ++){
        l_FixedLenValue.append(' ');
      }
      put(l_FixedLenValue.toString());
      l_FixedLenValue = null;
    }
    return xi_FieldSize;
  }
  public int placeFiller( short xi_FillerCount ) {
    char sFiller = ' ';
    for (int ii=0; ii < xi_FillerCount; ii++)
      put( sFiller);
    return xi_FillerCount;
  }
  public void put(String xi_Data)
  {
    if (xi_Data == null || xi_Data.length() == 0){
      return;
    }
    super.put(xi_Data);
  }

}
