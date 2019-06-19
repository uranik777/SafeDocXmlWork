package com._4ds.Xml2Cobol.Base;

import java.util.ArrayList;

public class Xml2cblStringRestriction extends Xml2cblRestriction<String>
{
  ArrayList<String> m_RestrValues;
  int m_MaxSize;     // used mostly for string
  //
  public boolean validate(String xi_Data){
    return true; // TODO
  }
}
