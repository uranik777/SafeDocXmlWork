package com._4ds.Xml2Cobol.Base;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class Xml2cblBufferOutput extends Xml2cblBuffer{
  ByteArrayOutputStream m_OutputArea;
  boolean   m_IsResizeProhibited;
  //
  public Xml2cblBufferOutput (){
    m_OutputArea = new ByteArrayOutputStream();
    m_IsResizeProhibited=false;
  }
  public void put (char xi_Data)
  {
    Byte oByte = (byte)xi_Data;
    m_OutputArea.write(oByte);
    m_Used += 1;
  };
  public void put (boolean xi_Data)
  {
    Byte oByte = 0;
    if (xi_Data)
      oByte = (byte)1;
    m_OutputArea.write(oByte);
    m_Used += 2;
  };
  public void put (short xi_Data)
  {
    ByteBuffer oByteBuffer = ByteBuffer.allocate(2);
    oByteBuffer.putShort(xi_Data);
    m_OutputArea.write(oByteBuffer.array(),0,2);
    m_Used += 2;
  };
  public void put (int xi_Data)
  {
    ByteBuffer oByteBuffer = ByteBuffer.allocate(4);
    oByteBuffer.putInt(xi_Data);
    m_OutputArea.write(oByteBuffer.array(),0,4);
    m_Used += 4;
  };
  public void put (long xi_Data)
  {
    ByteBuffer oByteBuffer = ByteBuffer.allocate(8);
    oByteBuffer.putLong(xi_Data);
    m_OutputArea.write(oByteBuffer.array(),0,8);
    m_Used += 8;
  }
  public void put(String xi_Data)
  {
    short l_Size = 0;
    if (xi_Data == null ){
      put(l_Size);
      return;
    }
    l_Size = (short)xi_Data.length();
    put(l_Size);
    m_OutputArea.write(xi_Data.getBytes(),0,l_Size);
    m_Used += l_Size;
    return;
  }
  public void startOutput()
  {
    m_OutputArea.reset();
    m_ActualSize = 0;
    m_Used=0;
    m_AreaIndex=0;
    m_IsResizeProhibited=false; // TODO ??? xi_IsResizeProhibited;
  }
  public ByteArrayOutputStream getOutputStream(){
    return this.m_OutputArea;
  }
  public int writeTag( String Xio_TagName, boolean xi_bOpenVsCloseTag ){
    return 0;
  }
  public short addTag(
      String xi_TagName
    ){
    return -1;
  }
}

