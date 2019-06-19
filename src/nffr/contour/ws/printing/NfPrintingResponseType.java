/**
 * NfPrintingResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nffr.contour.ws.printing;


/**
 * XML Message for response
 */
public class NfPrintingResponseType  implements java.io.Serializable {
    private java.math.BigInteger errorCode;

    /* Obligatoire donc si errorCode = 0 alors
     * 						errorText=OK. */
    private java.lang.String errorText;

    private java.lang.String[] comment;

    private java.lang.String[] savedFileName;

    public NfPrintingResponseType() {
    }

    public NfPrintingResponseType(
           java.math.BigInteger errorCode,
           java.lang.String errorText,
           java.lang.String[] comment,
           java.lang.String[] savedFileName) {
           this.errorCode = errorCode;
           this.errorText = errorText;
           this.comment = comment;
           this.savedFileName = savedFileName;
    }


    /**
     * Gets the errorCode value for this NfPrintingResponseType.
     * 
     * @return errorCode
     */
    public java.math.BigInteger getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this NfPrintingResponseType.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.math.BigInteger errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorText value for this NfPrintingResponseType.
     * 
     * @return errorText   * Obligatoire donc si errorCode = 0 alors
     * 						errorText=OK.
     */
    public java.lang.String getErrorText() {
        return errorText;
    }


    /**
     * Sets the errorText value for this NfPrintingResponseType.
     * 
     * @param errorText   * Obligatoire donc si errorCode = 0 alors
     * 						errorText=OK.
     */
    public void setErrorText(java.lang.String errorText) {
        this.errorText = errorText;
    }


    /**
     * Gets the comment value for this NfPrintingResponseType.
     * 
     * @return comment
     */
    public java.lang.String[] getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this NfPrintingResponseType.
     * 
     * @param comment
     */
    public void setComment(java.lang.String[] comment) {
        this.comment = comment;
    }

    public java.lang.String getComment(int i) {
        return this.comment[i];
    }

    public void setComment(int i, java.lang.String _value) {
        this.comment[i] = _value;
    }


    /**
     * Gets the savedFileName value for this NfPrintingResponseType.
     * 
     * @return savedFileName
     */
    public java.lang.String[] getSavedFileName() {
        return savedFileName;
    }


    /**
     * Sets the savedFileName value for this NfPrintingResponseType.
     * 
     * @param savedFileName
     */
    public void setSavedFileName(java.lang.String[] savedFileName) {
        this.savedFileName = savedFileName;
    }

    public java.lang.String getSavedFileName(int i) {
        return this.savedFileName[i];
    }

    public void setSavedFileName(int i, java.lang.String _value) {
        this.savedFileName[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NfPrintingResponseType)) return false;
        NfPrintingResponseType other = (NfPrintingResponseType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorText==null && other.getErrorText()==null) || 
             (this.errorText!=null &&
              this.errorText.equals(other.getErrorText()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              java.util.Arrays.equals(this.comment, other.getComment()))) &&
            ((this.savedFileName==null && other.getSavedFileName()==null) || 
             (this.savedFileName!=null &&
              java.util.Arrays.equals(this.savedFileName, other.getSavedFileName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorText() != null) {
            _hashCode += getErrorText().hashCode();
        }
        if (getComment() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComment());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComment(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSavedFileName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSavedFileName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSavedFileName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NfPrintingResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingResponseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorText");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "errorText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "CommentType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("savedFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "savedFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "URLMessageType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
