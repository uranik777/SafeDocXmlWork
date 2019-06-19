package com._4ds.Xml2Cobol.Base;

import java.util.HashMap;

public class Xml2cblBufferXmlInput extends Xml2cblBufferInput {

  String    m_InputAreaStr;
  HashMap<String, Integer>   m_CloseTagPositions = null;

  public Xml2cblBufferXmlInput(){
    m_BufferType = XMLBUF_K_XML;
    m_InputAreaStr = null;
    m_CloseTagPositions = new HashMap<String, Integer>();
  }
  public void startInput(
      byte[] xi_Buffer
   ) throws Exception {
    super.startInput(xi_Buffer);
    m_InputAreaStr = new String(xi_Buffer);
    m_CloseTagPositions.clear();
  }
  public int findTag(String xi_TagName, boolean xi_bOpenVsCloseTag, boolean xi_ImmediateOnly )
  {
    if ( xi_TagName == null ) 
      return -1;
    int l_TagLen = xi_TagName.length();
    if ( xi_bOpenVsCloseTag ) 
      l_TagLen += "<>".length();
    else
      l_TagLen += "</>".length();
    StringBuilder l_Tag = new StringBuilder();
    if ( xi_bOpenVsCloseTag ) 
      l_Tag.append("<");
    else
      l_Tag.append("</");
    l_Tag.append(xi_TagName);
    
    String l_Tag1 = l_Tag.toString() + ">";
    String l_Tag2 = l_Tag.toString() + " ";
    String l_Tag3 = l_Tag.toString() + "\t";

    int l_FindTag1 = m_InputAreaStr.indexOf(l_Tag1,m_Used);
    int l_FindTag2 = m_InputAreaStr.indexOf(l_Tag2,m_Used);
    int l_FindTag3 = m_InputAreaStr.indexOf(l_Tag3.toString(),m_Used);
    int l_FindTag = l_FindTag1;
    if ( l_FindTag2 >= 0 && (l_FindTag < 0 || l_FindTag2 >=0 && l_FindTag2 < l_FindTag ))
      l_FindTag = l_FindTag2;
    if ( l_FindTag3 >= 0 && (l_FindTag < 0 || l_FindTag3 >=0 && l_FindTag3 < l_FindTag ))
      l_FindTag = l_FindTag3;
    if (l_FindTag < m_Used )
      return -1;
    int l_FindTagPrefix = l_FindTag;
    int l_TagPrefixEnd = l_FindTag + l_Tag.length();
    if (l_FindTagPrefix >= 0  && l_TagPrefixEnd < m_InputArea.length 
        && m_InputArea[l_TagPrefixEnd] != (byte)' '
        && m_InputArea[l_TagPrefixEnd] != (byte)'>' 
        && m_InputArea[l_TagPrefixEnd] != (byte)'\t' ){
      return -1;
    }
    while (l_FindTagPrefix >= 0  && l_TagPrefixEnd < m_InputArea.length
        && m_InputArea[l_TagPrefixEnd] != (byte)' '
        && m_InputArea[l_TagPrefixEnd] != (byte)'>' 
        && m_InputArea[l_TagPrefixEnd] != (byte)'/' ){
      l_FindTag = l_FindTagPrefix;
      l_FindTagPrefix = m_InputAreaStr.indexOf(l_Tag.toString(),l_TagPrefixEnd);
      if (l_FindTagPrefix < 0 )
        break;
      l_TagPrefixEnd = l_FindTagPrefix + l_Tag.length();
    }
    if (xi_bOpenVsCloseTag){
      boolean l_ImmediateOnly = xi_ImmediateOnly 
            || (m_RecentlyClosedTag!=null && ( m_RecentlyClosedTag.equals(xi_TagName)));
      if (l_ImmediateOnly && l_FindTag != m_Used )
        return -1;
    }
    return l_FindTag;
  }
  public String getTill(String xi_TagName, boolean xi_bOpenVsCloseTag)
  {
    int l_FindTag = findTag(xi_TagName, xi_bOpenVsCloseTag, false);
    if (l_FindTag < 0 )
      return null;
    int iLen = l_FindTag - m_Used;

    String l_NewString = null;
    if( iLen > 0) {
      byte[] l_bBuffer = new byte[iLen];
      for ( int ii = 0; ii < iLen; ii++){
        l_bBuffer[ii] = m_InputArea[m_Used+ii];
      }
      l_NewString = new String(l_bBuffer);
    }
    m_Used += iLen;
    return l_NewString;
  }
  public boolean searchTag(String xi_TagName, boolean xi_bOpenVsCloseTag, boolean xi_ImmediateOnly )
  {
    int l_FindTag = findTag(xi_TagName, xi_bOpenVsCloseTag, xi_ImmediateOnly);
    if ( l_FindTag < 0 )
      return false;
    if (m_RecentParentTag != null )
    {
      Integer l_CurrentParentCloseTagPosition = m_CloseTagPositions.get(m_RecentParentTag);
      if ( l_CurrentParentCloseTagPosition == null || l_CurrentParentCloseTagPosition < l_FindTag ){	
    	  l_CurrentParentCloseTagPosition = findTag(m_RecentParentTag, false, xi_ImmediateOnly); // parent close
    	  m_CloseTagPositions.put(m_RecentParentTag,l_CurrentParentCloseTagPosition);
      }
      if (l_CurrentParentCloseTagPosition < 0 || l_CurrentParentCloseTagPosition < l_FindTag )
        return false;
    }
    int l_CloseBracket = m_InputAreaStr.indexOf(">",l_FindTag);
    if (l_CloseBracket < l_FindTag)
      return false;
    if (xi_bOpenVsCloseTag && m_InputArea[l_CloseBracket-1] == '/') // tag has no elements
      return false;
    return true;
  }
  public int skipTag(String xi_TagName, boolean xi_bOpenVsCloseTag, boolean xi_ImmediateOnly )
  {
    int l_FindTag = findTag(xi_TagName, xi_bOpenVsCloseTag, xi_ImmediateOnly);
    if (l_FindTag < 0)
      return -1;
    if (m_RecentParentTag != null)
    {
      Integer l_CurrentParentCloseTagPosition = m_CloseTagPositions.get(m_RecentParentTag);
      if ( l_CurrentParentCloseTagPosition == null || l_CurrentParentCloseTagPosition < l_FindTag ){	
    	  l_CurrentParentCloseTagPosition = findTag(m_RecentParentTag, false, xi_ImmediateOnly); // parent close
    	  m_CloseTagPositions.put(m_RecentParentTag,l_CurrentParentCloseTagPosition);
      }
      if (l_CurrentParentCloseTagPosition < 0 || l_CurrentParentCloseTagPosition < l_FindTag )
	        return -1;
    }
    int l_CloseBracket = m_InputAreaStr.indexOf(">",l_FindTag);
    if (l_CloseBracket < l_FindTag)
      return -1;
    if (xi_bOpenVsCloseTag && m_InputArea[l_CloseBracket-1] == '/') // tag has no elements
      return -1;
    m_Used = l_CloseBracket + 1;
    if (xi_bOpenVsCloseTag)
    {
      pushTag( xi_TagName );
    }
    else
    {
      String l_ExpectedTag = popTag();
      if (l_ExpectedTag != null )
      {
        boolean l_bCompare = l_ExpectedTag.equals( xi_TagName);
        l_ExpectedTag = null;
        if ( !l_bCompare ) 
          return -1;
      }
      m_RecentlyClosedTag = null;
      m_RecentlyClosedTag = new String(xi_TagName);
    }
    int l_AreaUsed = m_Used;
    while(l_AreaUsed >= 0 && l_AreaUsed < m_InputArea.length )
    {
      if ( m_InputArea[l_AreaUsed] == ' '
        || m_InputArea[l_AreaUsed] == '\t'
        || m_InputArea[l_AreaUsed] == '\n'
        || m_InputArea[l_AreaUsed] == '\r'
        )
      {
        l_AreaUsed++;
        m_Used++;
      }
      else 
        break;
    }
    return l_AreaUsed;
  }
}
