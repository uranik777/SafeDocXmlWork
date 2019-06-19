package com._4ds.Xml2Cobol.Base;

import java.nio.ByteBuffer;

public class Xml2cblBufferInput extends Xml2cblBuffer
{
  byte      m_InputArea[];
  xsdStack  m_ParentTagOpened;
  String    m_RecentParentTag;
  String    m_RecentlyClosedTag;
  
  public Xml2cblBufferInput(){
    m_InputArea = null;
    m_ParentTagOpened = null;
    m_RecentParentTag = null;
    m_RecentlyClosedTag = null;
  }
  //
  public void startInput(
    byte[] xi_Buffer 
  ) throws Exception {
    m_InputArea = xi_Buffer;
    m_AreaIndex=0;
    m_Size = 0;
    if (xi_Buffer!=null)
      m_Size = xi_Buffer.length;
    m_OriginSize = m_Size;
    m_ActualSize = m_Size;
    m_Used=0;
  } 
  public int placeFiller( short xi_FillerCount ){
    return 0;
  }
  public boolean skipFiller( short xi_FillerCount ) throws Exception {
    return true;
  }
  public boolean getBoolean() throws Exception {
    if ( m_Used + 1 <= m_InputArea.length ){
      byte oByte = m_InputArea[m_Used];
      m_Used += 1;
      if ( oByte == 0 )
        return false;
      else
        return true;
    }
    throw new Exception("Failed getBoolean with m_Used = " + m_Used);
  }
  public byte getByte() throws Exception {
    if ( m_Used + 1 <= m_InputArea.length ){
      byte oByte = m_InputArea[m_Used];
      m_Used += 1;
      return oByte;
    }
    throw new Exception("Failed getByte with m_Used = " + m_Used);
  }
  public char getChar() throws Exception {
    if ( m_Used + 1 <= m_InputArea.length ){
      byte oByte = m_InputArea[m_Used];
      m_Used += 1;
      return (char)oByte;
    }
    throw new Exception("Failed getChar with m_Used = " + m_Used);
  }
  public short getShort() throws Exception {
    if ( m_Used + 2 <= m_InputArea.length ){
      ByteBuffer oByteBuffer = ByteBuffer.allocate(2);
      oByteBuffer.put(m_InputArea, m_Used, 2);
      oByteBuffer.position(0);
      short oValue = oByteBuffer.getShort();
      m_Used += 2;
      return oValue;
    }
    throw new Exception("Failed getShort with m_Used = " + m_Used);
  }
  public int getInt() throws Exception{
    if ( m_Used + 4 <= m_InputArea.length ){
      ByteBuffer oByteBuffer = ByteBuffer.allocate(4);
      oByteBuffer.put(m_InputArea, m_Used, 4);
      oByteBuffer.position(0);
      int oValue = oByteBuffer.getInt();
      m_Used += 4;
      return oValue;
    }
    throw new Exception("Failed getInt with m_Used = " + m_Used);
  }
  public long getLong() throws Exception {
    if ( m_Used + 8 <= m_InputArea.length ){
      ByteBuffer oByteBuffer = ByteBuffer.allocate(8);
      oByteBuffer.put(m_InputArea, m_Used, 8);
      oByteBuffer.position(0);
      long oValue = oByteBuffer.getLong();
      m_Used += 8;
      return oValue;
    }
    throw new Exception("Failed getLong with m_Used = " + m_Used);
  }
  public String getString() throws Exception {
    if ( m_Used + 2 <= m_InputArea.length ){
      short iStringLength = getShort();
      if ( m_Used + iStringLength <= m_InputArea.length ){
        ByteBuffer oByteBuffer = ByteBuffer.allocate(iStringLength);
        oByteBuffer.put(m_InputArea, m_Used, iStringLength);
        oByteBuffer.position(0);
        String oValue = new String(oByteBuffer.array());
        m_Used += iStringLength;
        return oValue;
      }
    }
    throw new Exception("Failed getLong with m_Used = " + m_Used);
  }
  public void pushTag(String xi_pTag )
  {
    if ( m_ParentTagOpened == null)
    {
      m_ParentTagOpened = new xsdStack();
    }
    xsdTagStackElement l_TagElement = new xsdTagStackElement(xi_pTag);
    m_RecentParentTag = l_TagElement.m_OpenedTag;
    m_ParentTagOpened.push(l_TagElement);
  }
  public String popTag() {
    if ( m_ParentTagOpened != null )
    {
      xsdTagStackElement l_TagElement = (xsdTagStackElement)m_ParentTagOpened.pop();
      if (l_TagElement != null )
      {
        if (m_ParentTagOpened.m_Current != null )
        {
          m_RecentParentTag = ((xsdTagStackElement)(m_ParentTagOpened.m_Current)).m_OpenedTag;
        } else{
          m_RecentParentTag = null;
        }
        String l_Tag = new String(l_TagElement.m_OpenedTag);
        l_TagElement = null;
        return l_Tag;
      }
    }
    m_RecentParentTag = null;
    return null;
  }
  public boolean searchTag(
      String xi_TagName, 
      boolean xi_bOpenVsCloseTag, 
      boolean xi_ImmediateOnly 
  ){
    return false;
  }
  public int skipTag(
      String xi_TagName, 
      boolean xi_bOpenVsCloseTag, 
      boolean xi_ImmediateOnly 
  ){
    return m_Used;
  }
}

