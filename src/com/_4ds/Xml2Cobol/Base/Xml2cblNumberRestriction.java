package com._4ds.Xml2Cobol.Base;

import java.util.ArrayList;

 
public class Xml2cblNumberRestriction < BaseType > extends Xml2cblRestriction < BaseType >
{
  private Number m_MinValue;
  private Number m_MaxValue;
  /*
   *: Method name: XMLSER_CRestriction
   **/
  public Xml2cblNumberRestriction()
  {
    m_MinValue = null;
    m_MaxValue = null;
  };
  public boolean validate(BaseType xi_Data)
  {
    Number l_Data = (Number)xi_Data;
    if( m_MaxValue != null && ((Comparable)l_Data).compareTo((Comparable)m_MaxValue) > 0 || 
        m_MinValue != null && ((Comparable)l_Data).compareTo((Comparable)m_MinValue) < 0 ){
      return false;
    };
    if( !find(xi_Data) ){
      return false;
    }
    return true;
  }
  public void setMin(Number xi_Data)
  {
    m_MinValue=xi_Data;
  }
  public void setMax(Number xi_Data)
  {
    m_MaxValue=xi_Data;
  }

}