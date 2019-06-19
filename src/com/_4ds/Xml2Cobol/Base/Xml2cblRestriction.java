package com._4ds.Xml2Cobol.Base;

import java.util.ArrayList;

public abstract class Xml2cblRestriction < BaseType >
{
  ArrayList<BaseType> m_RestrValues;
  int m_MaxSize;     // used mostly for string
  //
  abstract public boolean validate(BaseType xi_Data);
  //
  /*
   *: Method name: Add
   */
  void add(BaseType xi_Data)
  {
    if(!find(xi_Data))
      m_RestrValues.add(xi_Data);
  };  
  public boolean find(BaseType xi_Data){
    return (m_RestrValues!=null && m_RestrValues.contains(xi_Data));
  }
  public void delete(BaseType xi_Data)
  {
    if (m_RestrValues!=null)
    m_RestrValues.remove(xi_Data);
  }
  public void reset() {
    m_RestrValues.clear();
  };
  public void setSize(int xi_Size)
  {
    m_MaxSize=xi_Size;
  };
  public int getSize()
  {
    return m_MaxSize;
  };
}
