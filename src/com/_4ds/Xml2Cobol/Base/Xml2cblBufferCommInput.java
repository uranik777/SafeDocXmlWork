package com._4ds.Xml2Cobol.Base;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
 * Class: xml2cblBufferCommInput
 * 
 * Notes: Only non empty fields are packed. 
 * Strings are packed with its actual length. 
 * Buffer size is not more than 32K
 */
public class Xml2cblBufferCommInput extends Xml2cblBufferInput{

  int   m_MapOffset;
  //String    m_InputAreaStr;
  TreeSet<TagMap> m_TagMap;
    
  public Xml2cblBufferCommInput(/*int XiBufferSize*/){
    m_BufferType = XMLBUF_K_Comm;
    m_Size = 0; // XiBufferSize; TODO
    m_OriginSize=m_Size;
    m_Used=8; // 4; // sizeof(m_Used); // m_Used has to be packed always from the begining
    m_MapOffset = 0;
  }
  public void startInput(
      byte[] xi_Buffer
   ) throws Exception {
    super.startInput(xi_Buffer);
    m_ActualSize = getInt();
    m_MapOffset = getInt();
    //m_Used=8;
    getTagMap();
  }
  public boolean getTagMap(){
    m_TagMap = new TreeSet<TagMap>(new TagMapComparator());
    m_Used = m_MapOffset;
    try{
      while (m_Used < m_ActualSize){
        TagMap l_TagMap = new TagMap();
        l_TagMap.m_iTagId = getShort();
        l_TagMap.m_sTagName = getString();
        m_TagMap.add(l_TagMap);
      }
    }catch(Exception exc){
      System.out.println("Xml2cblBufferCommInput.getTagMap: Unexpected error in TagMap"); 
    }
    m_Used = 8;
    return false;
  }
  public boolean searchTag( 
    String xi_TagName, 
    boolean xi_bOpenVsCloseTag, 
    boolean xi_ImmediateOnly 
  ) {
    try{
      short l_CurrentTag = getShort();
      if ( isValidTag( l_CurrentTag, xi_TagName ) ) 
        return true;
      else{
        m_Used-=2;
        return false;
      }
    }catch(Exception exc){
      return false;
    }
  }
  public int skipTag(
    short xi_TagName, 
    boolean xi_bOpenVsCloseTag, 
    boolean xi_ImmediateOnly 
  ){
    //if (xi_bOpenVsCloseTag) 
    //  m_Used+=2;  // Already skiped while reading
    return m_Used;
  }
  public boolean isValidTag(
    short xi_TagId, 
    String xi_sTagName
  ){
    if (xi_TagId < 0 )
      return false;
    if (xi_sTagName ==null || xi_sTagName.length() == 0 )
      return false;
    Iterator oIter = m_TagMap.iterator();
    while ( oIter.hasNext()){
      TagMap oTagMap = (TagMap)oIter.next();
      if ( xi_TagId == oTagMap.m_iTagId && xi_sTagName.equals(oTagMap.m_sTagName))
        return true;
    }
    return false;
  }
  public class TagMapComparator implements Comparator{
    
    public TagMapComparator() {
    }
    
    public int compare(Object obj, Object obj1) {
      if (obj == null && obj1 == null )
        return 0;
      if (obj == null)
        return 1;
      if (obj1 == null)
        return 1;
      short sObjKey, sObjKey1;  
      sObjKey = getKey(obj);
      sObjKey1 = getKey(obj1);
      return  (sObjKey - sObjKey1);
   }
    private short getKey(Object obj){
      return((TagMap)obj).m_iTagId;
    }
  }
}
