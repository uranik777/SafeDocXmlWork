package com._4ds.Xml2Cobol.Base;
/*
 * Class xml2cblBufferXmlOutput
 * 
 * Notes:  Buffer content is a common XML string
 * Buffer size is growing accoring to actual size of the data to send
 */
public class Xml2cblBufferXmlOutput extends Xml2cblBufferOutput{
  
  public Xml2cblBufferXmlOutput(){
    m_BufferType = XMLBUF_K_XML;
  }
  public void startOutput()
  {
    super.startOutput();
    put("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
  };
  public void put(String xi_Data)
  {
    short l_Size = 0;
    if (xi_Data == null ){
      return;
    }
    l_Size = (short)xi_Data.length();
    m_OutputArea.write(xi_Data.getBytes(),0,l_Size);
  }
  public int writeTag( String Xio_TagName, boolean xi_bOpenVsCloseTag ){
    if ( Xio_TagName == null ) 
      return 0;
    int l_TagLen = Xio_TagName.length();
    if ( xi_bOpenVsCloseTag ) 
      l_TagLen += "<>".length();
    else
      l_TagLen += "</>".length();
    if ( xi_bOpenVsCloseTag ) 
      put("<");
    else
      put("</");
    put(Xio_TagName);
    put(">");
    return l_TagLen;
  }
  public void restorePosition() {
    if ( m_SavedPositions != null )
    {
      xsdPositionStackElement l_PosElement = (xsdPositionStackElement)m_SavedPositions.pop();
      if (l_PosElement != null )
      {
        int l_SavedPosition = l_PosElement.m_SavedPosition;
        l_PosElement = null;
        if ( l_SavedPosition < m_Used )
        {
          m_Used = l_SavedPosition;
          m_ActualSize = m_Used;
        }
      }
    }
  }
}
