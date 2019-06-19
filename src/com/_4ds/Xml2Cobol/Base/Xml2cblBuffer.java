package com._4ds.Xml2Cobol.Base;


/*
 * Class xsdStackElement
 */
class xsdStackElement {
  xsdStackElement m_Previous;
  public xsdStackElement(){
    m_Previous = null;
  }
};
/*
 * Class xsdPositionStackElement
 */
class xsdPositionStackElement extends xsdStackElement{
  public int m_SavedPosition;
  public xsdPositionStackElement(int  xi_SavedPosition ){
    m_SavedPosition = xi_SavedPosition;
  }
};
/*
 * Class xsdTagStackElement
 */
class xsdTagStackElement extends xsdStackElement{
    public String m_OpenedTag;
    //
    public xsdTagStackElement(){
      m_OpenedTag = null;
    }
    public xsdTagStackElement(String  xi_Tag ){
      m_OpenedTag = null;
      if (xi_Tag != null){
        m_OpenedTag = new String(xi_Tag);
      }
    }
};
/*
 * Class xsdStack
 */
class xsdStack {
  public xsdStackElement m_Current;
  public xsdStack(){ 
    m_Current =null;
  }
  public void push( xsdStackElement xi_Element ){
    xi_Element.m_Previous = m_Current;
    m_Current = xi_Element;
  }
  public xsdStackElement pop (){
    if ( m_Current == null )
      return null;
    xsdStackElement l_XsdStackElement = m_Current;
    m_Current = l_XsdStackElement.m_Previous;
    return l_XsdStackElement;
  }
};

/*
 *: Class name: xmlBuffer
 *
 *: Abstract: buffer in use for  blob
 *
 **/
public class Xml2cblBuffer
{
  public static final int XMLBUF_K_XML  = 1;
  public static final int XMLBUF_K_Native = 4;
  public static final int XMLBUF_K_Comm   = 5;
  public static final int XMLBUF_K_MaxBuffer =  32764;
  //
  int       m_Size;
  int       m_OriginSize;
  int       m_Used;
  int       m_ActualSize;
  short     m_BufferType;
  boolean   m_bSwapToNetwork;
  short     m_AreaIndex;
  xsdStack  m_SavedPositions;
  //
  public Xml2cblBuffer (){
    m_Used=0; // m_Used has to be packed always from the begining
    m_Size = 0;
    m_ActualSize = 0;
    m_BufferType = 0;
    m_bSwapToNetwork = false;
    m_AreaIndex=0;
    m_OriginSize = 0;
    m_SavedPositions = null;
  }

  public int remaining (){
    return  (int)(m_Size - m_Used);
  }
  public int toRead (){
    return  (int) m_Size;
  }
  public int size (){
    return  (int)(m_ActualSize - m_Used);
  }
  public void clear(){
    m_Used=0;
    m_ActualSize = 0;
  }
  public int usedSize (){
    return  (int) m_Used;
  }
  public int actualSize(){
    return  (int) m_ActualSize;
  }
  public void savePosition(){
    if (m_SavedPositions == null){
      m_SavedPositions = new xsdStack();
    }
    xsdPositionStackElement l_PosElement = new xsdPositionStackElement( m_Used );
    m_SavedPositions.push( l_PosElement );
  }
  public void restorePosition(){
    if ( m_SavedPositions != null )
    {
      xsdPositionStackElement l_PosElement = (xsdPositionStackElement)(m_SavedPositions.pop());
      if (l_PosElement != null )
      {
        int l_SavedPosition = l_PosElement.m_SavedPosition;
        l_PosElement = null;
        if ( l_SavedPosition < m_Used )
        {
          m_Used = l_SavedPosition;
        }
      }
    }
  }
  public void freePosition(){
    if ( m_SavedPositions != null )
    {
      m_SavedPositions.pop();
    }
  }
  class TagMap {
    short m_iTagId;
    String m_sTagName;
    public TagMap(){
      m_iTagId = -1;
      m_sTagName = null;
    }
  }
}
