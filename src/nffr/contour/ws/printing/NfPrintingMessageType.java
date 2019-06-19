/**
 * NfPrintingMessageType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nffr.contour.ws.printing;

public class NfPrintingMessageType  implements java.io.Serializable {
    private java.lang.String docId;

    private nffr.contour.ws.printing.MessageTypeType messageType;

    private java.lang.String data;

    private nffr.contour.ws.printing.ReissuingType reissuing;

    private java.lang.String from;

    private java.lang.String[] to;

    private java.lang.String[] cc;

    public NfPrintingMessageType() {
    }

    public NfPrintingMessageType(
           java.lang.String docId,
           nffr.contour.ws.printing.MessageTypeType messageType,
           java.lang.String data,
           nffr.contour.ws.printing.ReissuingType reissuing,
           java.lang.String from,
           java.lang.String[] to,
           java.lang.String[] cc) {
           this.docId = docId;
           this.messageType = messageType;
           this.data = data;
           this.reissuing = reissuing;
           this.from = from;
           this.to = to;
           this.cc = cc;
    }


    /**
     * Gets the docId value for this NfPrintingMessageType.
     * 
     * @return docId
     */
    public java.lang.String getDocId() {
        return docId;
    }


    /**
     * Sets the docId value for this NfPrintingMessageType.
     * 
     * @param docId
     */
    public void setDocId(java.lang.String docId) {
        this.docId = docId;
    }


    /**
     * Gets the messageType value for this NfPrintingMessageType.
     * 
     * @return messageType
     */
    public nffr.contour.ws.printing.MessageTypeType getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this NfPrintingMessageType.
     * 
     * @param messageType
     */
    public void setMessageType(nffr.contour.ws.printing.MessageTypeType messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the data value for this NfPrintingMessageType.
     * 
     * @return data
     */
    public java.lang.String getData() {
        return data;
    }


    /**
     * Sets the data value for this NfPrintingMessageType.
     * 
     * @param data
     */
    public void setData(java.lang.String data) {
        this.data = data;
    }


    /**
     * Gets the reissuing value for this NfPrintingMessageType.
     * 
     * @return reissuing
     */
    public nffr.contour.ws.printing.ReissuingType getReissuing() {
        return reissuing;
    }


    /**
     * Sets the reissuing value for this NfPrintingMessageType.
     * 
     * @param reissuing
     */
    public void setReissuing(nffr.contour.ws.printing.ReissuingType reissuing) {
        this.reissuing = reissuing;
    }


    /**
     * Gets the from value for this NfPrintingMessageType.
     * 
     * @return from
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this NfPrintingMessageType.
     * 
     * @param from
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the to value for this NfPrintingMessageType.
     * 
     * @return to
     */
    public java.lang.String[] getTo() {
        return to;
    }


    /**
     * Sets the to value for this NfPrintingMessageType.
     * 
     * @param to
     */
    public void setTo(java.lang.String[] to) {
        this.to = to;
    }

    public java.lang.String getTo(int i) {
        return this.to[i];
    }

    public void setTo(int i, java.lang.String _value) {
        this.to[i] = _value;
    }


    /**
     * Gets the cc value for this NfPrintingMessageType.
     * 
     * @return cc
     */
    public java.lang.String[] getCc() {
        return cc;
    }


    /**
     * Sets the cc value for this NfPrintingMessageType.
     * 
     * @param cc
     */
    public void setCc(java.lang.String[] cc) {
        this.cc = cc;
    }

    public java.lang.String getCc(int i) {
        return this.cc[i];
    }

    public void setCc(int i, java.lang.String _value) {
        this.cc[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NfPrintingMessageType)) return false;
        NfPrintingMessageType other = (NfPrintingMessageType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.docId==null && other.getDocId()==null) || 
             (this.docId!=null &&
              this.docId.equals(other.getDocId()))) &&
            ((this.messageType==null && other.getMessageType()==null) || 
             (this.messageType!=null &&
              this.messageType.equals(other.getMessageType()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.reissuing==null && other.getReissuing()==null) || 
             (this.reissuing!=null &&
              this.reissuing.equals(other.getReissuing()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.to==null && other.getTo()==null) || 
             (this.to!=null &&
              java.util.Arrays.equals(this.to, other.getTo()))) &&
            ((this.cc==null && other.getCc()==null) || 
             (this.cc!=null &&
              java.util.Arrays.equals(this.cc, other.getCc())));
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
        if (getDocId() != null) {
            _hashCode += getDocId().hashCode();
        }
        if (getMessageType() != null) {
            _hashCode += getMessageType().hashCode();
        }
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getReissuing() != null) {
            _hashCode += getReissuing().hashCode();
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCc() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCc());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCc(), i);
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
        new org.apache.axis.description.TypeDesc(NfPrintingMessageType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "NfPrintingMessageType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "docId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "messageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "MessageTypeType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reissuing");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "reissuing"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "ReissuingType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "to"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "ToType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "cc"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "CcType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
