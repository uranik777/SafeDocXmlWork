package com._4ds.Xml2Cobol.Base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
  /*
   * class Scale
   */
public class Xml2cblSerialScaled< BaseType >
{
    public Long m_Value; 
    private short m_Scale;
    // up to 8 decimal values are supported
    private final short m_MaxScale = 8;
    
    public Xml2cblSerialScaled(){
      m_Scale = 6;
      m_Value = new Long(0);
    }
    public Xml2cblSerialScaled(short xi_Scale){
      m_Scale = xi_Scale;
      m_Value = new Long(0);
    }
    public Xml2cblSerialScaled(BaseType xi_Value, short xi_Scale){
      m_Scale = xi_Scale;
      Number l_Value = (Number)xi_Value;
      m_Value = new Long(l_Value.longValue());
    }
    public boolean equals(Xml2cblSerialScaled xi_Value){
      if ( xi_Value == null ) {
        return false;
      }
      Long thisInteger = new Long(0);
      Long thisDecimal = new Long(0);
      Long thatInteger = new Long(0);
      Long thatDecimal = new Long(0);
      Components(thisInteger,thisDecimal);
      xi_Value.Components(thatInteger,thatDecimal);
      if ( thisInteger == thatInteger && thisDecimal == thatDecimal ){
        return true;
      }
      return false;
    }
    public void Components(Long Xo_Integer, Long Xo_Decimal)
    {
      long l_Exp;
      l_Exp = exponent();
      Xo_Integer = new Long( m_Value / l_Exp );
      Xo_Decimal = new Long( m_Value % l_Exp );
    };
    public long exponent(){
      long l_exp = 1;
      if (m_Scale>0){
        for (int ii=0; ii < m_Scale && ii < 8; ii++){
          l_exp = l_exp * 10;
        }
      }
      return l_exp;
    }
    public boolean fromString(String xi_String) {
      Double l_Double = Double.valueOf(xi_String);
      if ( m_Scale > 0 ) {
        long l_Exp = exponent();
        m_Value = (long)(l_Double * l_Exp);
      }else{
        m_Value = l_Double.longValue();
      }
      return true;
    }
    public String toString() {
      Long l_Integer = new Long(0);
      Long l_Decimal = new Long(0);
      Components(l_Integer, l_Decimal);
      if ( this.m_Scale == 0 )
        return l_Integer.toString();
      else{
        String l_Result = l_Integer.toString();
        if (l_Decimal.longValue() != 0){
          l_Result = l_Result + "." + l_Decimal.toString();
        }
        return l_Result;
      }
    }
    public String toXmlString() {
      return toString();
    }
    /*
    public int packToCobol(
      Xml2cblBufferNativeOutput xio_Buffer
    ){
      // TODO pack the correspondent length accordint the BaseType
      xio_Buffer.put(m_Value);
      return 8;
    }
    public boolean unpackFromCobol(
        Xml2cblBufferCommInput xio_Buffer
    )throws Exception{
      // TODO unpack the correspondent length accordint the BaseType
      if ( 8 <= xio_Buffer.remaining() ){
        m_Value = xio_Buffer.getLong();
        return true;
      }
      return false;
    }
    */
    public void commPack(Xml2cblBufferCommOutput xio_Buffer)
    {
      // TODO unpack the correspondent length accordint the BaseType
      xio_Buffer.put(m_Value);
    }
    public boolean commUnpack(
      Xml2cblBufferCommInput xio_Buffer
    )throws Exception{
      // TODO unpack the correspondent length accordint the BaseType
      if ( 8 <= xio_Buffer.remaining() ){
        m_Value = xio_Buffer.getLong();
        return true;
      }
      return false;
    }
}
