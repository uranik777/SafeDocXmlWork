package com._4ds.Xml2Cobol.Base;

import java.util.ArrayList;
import java.util.Iterator;

import com._4ds.Xml2Cobol.Base.Xml2cblBuffer.TagMap;

/*
 *  Class: xml2cblBufferCommOutput
 *  
 *  Notes: Only non empty fields are packed. 
 *  Strings are packed with its actual length. 
 *  Buffer size is not more than 32K
 */
public class Xml2cblBufferCommOutput extends Xml2cblBufferOutput {

  int   m_MapOffset;
  ArrayList<String> m_TagMap;

  public Xml2cblBufferCommOutput(){
    m_BufferType = XMLBUF_K_Comm;
    m_bSwapToNetwork = false; // true;
    m_Used = 8; //sizeof(m_Used);// m_Used has to be packed always from the begining
    m_MapOffset = 0;
    m_TagMap = new ArrayList<String>();
  }
  public void startOutput()
  {
    super.startOutput();
    clear();
  };
  public void clear()
  {
    m_Used=0; //sizeof(m_Used);
    m_ActualSize = 0;
    m_MapOffset = 0;
    put(m_MapOffset);
    put(m_ActualSize);
  };
  public void setPackedSize ()
  {
    int l_Data;
    m_MapOffset = m_Used;
    writeTagMap();
    byte[] oOutput = m_OutputArea.toByteArray();
    m_OutputArea.reset();
    int iActualSize = m_Used;
    l_Data = iActualSize;
    put(l_Data);
    l_Data = m_MapOffset;
    put(l_Data);
    m_OutputArea.write(oOutput,8, oOutput.length - 8);
  };
  public int writeTag( String Xio_TagName, boolean xi_bOpenVsCloseTag ){
    if (xi_bOpenVsCloseTag){
      short iTagId = addTag(Xio_TagName);
      put(iTagId);
      return 2;
    }else{
      return 0;
    }
  }
  public short addTag(
      String xi_TagName
    ){
    int iIndx = m_TagMap.indexOf(xi_TagName);
    if (iIndx < 0 ){
      m_TagMap.add(xi_TagName);
      iIndx = m_TagMap.indexOf(xi_TagName);;
    }
    return ((short)iIndx);
  }
  public void writeTagMap(){
    for ( int ii = 0; ii < m_TagMap.size(); ii++){
      put(((short)ii));
      put(m_TagMap.get(ii));
    }
  }
}
