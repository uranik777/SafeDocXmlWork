package com._4ds.Xml2Cobol.Base;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

public class Xml2cblUtils {

  public static final boolean XML2CBL_OPEN_TAG = true;
  public static final boolean XML2CBL_CLOSE_TAG = false;
  public static final int XMLBUF_K_XML  = 1;
  public static final int XMLBUF_K_Native = 4;
  public static final int XMLBUF_K_Comm   = 5;
  public static final int XMLBUF_K_MaxBuffer =  32764;

  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      String xi_StringToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
       xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
       String l_StringToPack = xi_StringToPack.replaceAll("&", "&amp;");
       l_StringToPack = l_StringToPack.replaceAll("€", "&#128;");
       xo_Buffer.put(l_StringToPack);
       xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      xo_Buffer.put(xi_StringToPack);
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      String sStringToPack
     ){
       xo_Buffer.put(sStringToPack);
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Byte xi_ByteToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      xo_Buffer.put(xi_ByteToPack.toString());
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      xo_Buffer.put(xi_ByteToPack.byteValue());
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      Byte xi_ByteToPack
     ){
       xo_Buffer.put(xi_ByteToPack.toString());
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Short xi_ShortToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      xo_Buffer.put(xi_ShortToPack.toString());
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      xo_Buffer.put(xi_ShortToPack.shortValue());
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      Short xi_ShortToPack
     ){
       xo_Buffer.put(xi_ShortToPack.toString());
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Integer xi_IntToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      xo_Buffer.put(xi_IntToPack.toString());
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      xo_Buffer.put(xi_IntToPack.intValue());
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      Integer xi_IntToPack
     ){
       xo_Buffer.put(xi_IntToPack.toString());
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Long xi_LongToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      xo_Buffer.put(xi_LongToPack.toString());
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      xo_Buffer.put(xi_LongToPack.longValue());
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      Long xi_LongToPack
     ){
       xo_Buffer.put(xi_LongToPack.toString());
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Float xi_FloatToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      xo_Buffer.put(xi_FloatToPack.toString());
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      Float oFloat = xi_FloatToPack * 100; // TODO
      int iValue = oFloat.intValue();
      xo_Buffer.put(iValue);
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      Float xi_FloatToPack
     ){
       xo_Buffer.put(xi_FloatToPack.toString());
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferXmlOutput xo_Buffer, 
      BigDecimal xi_DecimalToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      xo_Buffer.put(xi_DecimalToPack.toString());
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      
    }
  }
  public static void packToXml(
      Xml2cblBufferXmlOutput xo_Buffer, 
      BigDecimal xi_DecimalToPack
     ){
       xo_Buffer.put(xi_DecimalToPack.toString());
    }
  public static void packToXml(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Calendar xi_DateToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      packToXmlDate((Xml2cblBufferXmlOutput)xo_Buffer,xi_DateToPack);
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      int iYear = xi_DateToPack.get(Calendar.YEAR);
      int iMonth = xi_DateToPack.get(Calendar.MONTH)+1;
      int iDay = xi_DateToPack.get(Calendar.DAY_OF_MONTH);
      int iValue = iYear * 10000 
                 + iMonth * 100
                 + iDay;
      xo_Buffer.put(iValue);
    }
  }
  public static void packToXmlDate(
    Xml2cblBufferXmlOutput xo_Buffer, 
    Calendar xi_DateToPack
   ){
     SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
     xo_Buffer.put(formatterDate.format(xi_DateToPack.getTime()));
  }
  public static void packToXmlTime(
      String xi_TagName,
      Xml2cblBufferOutput xo_Buffer, 
      Calendar xi_TimeToPack
     ){
    if (xo_Buffer.m_BufferType == XMLBUF_K_XML){
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_OPEN_TAG);
      packToXmlTime((Xml2cblBufferXmlOutput)xo_Buffer,xi_TimeToPack);
      xo_Buffer.writeTag( xi_TagName, Xml2cblUtils.XML2CBL_CLOSE_TAG);
    }
    if (xo_Buffer.m_BufferType == XMLBUF_K_Comm){
      short iIndx = xo_Buffer.addTag(xi_TagName);
      xo_Buffer.put(((short)iIndx));
      int iValue = xi_TimeToPack.getTime().getHours() * 100 + xi_TimeToPack.getTime().getMinutes();
      xo_Buffer.put((short)iValue);
    }
  }
  public static void packToXmlTime(
    Xml2cblBufferXmlOutput xo_Buffer, 
    Calendar xi_TimeToPack
  ){
     SimpleDateFormat formatterDate = new SimpleDateFormat("HH:mm:ss");
     xo_Buffer.put(formatterDate.format(xi_TimeToPack.getTime()));
  }
  public static String unpackStringFromXml( 
    String xi_Tag, 
    Xml2cblBufferInput xi_Buffer
    )throws Exception{
    if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
      return null;
    if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
      return null;
    if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
      String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 && l_Value != null){
          String l_ValueResult = l_Value.replaceAll("&amp;","&");
          l_ValueResult = l_ValueResult.replaceAll("&#128;","€");
          return l_ValueResult;
      }
    }
    if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
      String l_Value = xi_Buffer.getString();
      if (l_Value != null ){
	      String l_ValueResult = l_Value.replaceAll("&amp;","&");
          l_ValueResult = l_ValueResult.replaceAll("&#128;","€");
	      return l_ValueResult;
      }
    }
    
    return null;
  }
  public static Byte unpackByteFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;
      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Byte i_Value = Byte.valueOf(l_Value);
  
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return i_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        return new Byte(xi_Buffer.getByte());
      }
      
      return null;
    }
  public static Short unpackShortFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;
      
      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Short i_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          i_Value = Short.valueOf(l_Value);
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return i_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        return new Short(xi_Buffer.getShort());
      }
      
      return null;
    }
  public static Integer unpackIntegerFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;

      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Integer i_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          i_Value = Integer.valueOf(l_Value);
  
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return i_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        return new Integer(xi_Buffer.getInt());
      }
      
      return null;
    }
  public static Long unpackLongFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;

      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Long i_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          i_Value = Long.valueOf(l_Value);
  
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return i_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        return new Long(xi_Buffer.getLong());
      }
      
      return null;
    }
  public static Float unpackFloatFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;

      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Float f_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          f_Value = Float.valueOf(l_Value);
  
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return f_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        Float fValue = new Float(xi_Buffer.getInt()); 
        return fValue/100; // TODO
      }
      
      return null;
    }
  public static BigDecimal unpackBigDecimalFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;

      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        BigDecimal d_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          d_Value = new BigDecimal(l_Value);
  
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return d_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        return new BigDecimal(xi_Buffer.getLong()); // TODO ???
      }
      
      return null;
    }
  public static Calendar unpackDateFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;

      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Calendar d_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          d_Value = unpackDate(l_Value);
  
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
            return d_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        int iDate = xi_Buffer.getInt();
        Calendar d_Value = null;
        d_Value = unpackDate(iDate);
        return d_Value; //new Calendar(/*xi_Buffer.getLong()*/); // TODO ???
      }
      
      return null;
    }
  public static Calendar unpackTimeFromXml( 
      String xi_Tag, 
      Xml2cblBufferInput xi_Buffer
      )throws Exception{
      if ( !xi_Buffer.searchTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) )
        return null;
      if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_OPEN_TAG, false) <= 0 )
        return null;
      if (xi_Buffer.m_BufferType == XMLBUF_K_XML){
        String l_Value = ((Xml2cblBufferXmlInput)xi_Buffer).getTill(xi_Tag,Xml2cblUtils.XML2CBL_CLOSE_TAG);
        Calendar d_Value = null;
        if ( l_Value!=null && l_Value.length() > 0 )
          d_Value = unpackTime(l_Value);
        if ( xi_Buffer.skipTag( xi_Tag, Xml2cblUtils.XML2CBL_CLOSE_TAG, false) > 0 )
          return d_Value;
      }
      if (xi_Buffer.m_BufferType == XMLBUF_K_Comm){
        short l_iTime = xi_Buffer.getShort();
        Calendar d_Value = null;
        d_Value = unpackTime(l_iTime);
        return d_Value;
      }
      
      return null;
    }
  public static Calendar unpackDate(String xi_Date){
    try{
      SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
      formatterDate.parse(xi_Date);
      Calendar oCalendar = formatterDate.getCalendar();
      return oCalendar;
    }catch(Exception exc){
      exc.printStackTrace(); //TODO
      return null;
    }
  }
  public static Calendar unpackDate(int xi_Date){
    try{
      SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
      int iYear = xi_Date/10000;
      int iMonth = (xi_Date-iYear*10000)/100;
      int iDay = xi_Date%100;
      String sDate = iYear + "-" + iMonth + "-" + iDay;
      formatterDate.parse(sDate);
      Calendar oCalendar = formatterDate.getCalendar();
      return oCalendar;
    }catch(Exception exc){
      exc.printStackTrace(); //TODO
      return null;
    }
  }
  public static Calendar unpackTime(String xi_Date){
    try{
      SimpleDateFormat formatterDate = new SimpleDateFormat("HH:mm:ss");
      formatterDate.parse(xi_Date);
      Calendar oCalendar = formatterDate.getCalendar();
      return oCalendar;
    }catch(Exception exc){
      exc.printStackTrace(); //TODO
      return null;
    }
  }
  public static Calendar unpackTime(int xi_Time){
    try{
      SimpleDateFormat formatterDate = new SimpleDateFormat("HH:mm:ss");
      int iHour = xi_Time/100;
      int iMinute = xi_Time%100;
      String sTime = iHour + ":" + iMinute + ":00";
      formatterDate.parse(sTime);
      Calendar oCalendar = formatterDate.getCalendar();
      return oCalendar;
    }catch(Exception exc){
      exc.printStackTrace(); //TODO
      return null;
    }
  }
}
