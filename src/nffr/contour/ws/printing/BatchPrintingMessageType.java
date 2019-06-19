/**
 * BatchPrintingMessageType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nffr.contour.ws.printing;

public class BatchPrintingMessageType  implements java.io.Serializable {
    private java.lang.String docId;

    private java.lang.String data;

    private java.lang.String endFlag;

    public BatchPrintingMessageType() {
    }

    public BatchPrintingMessageType(
           java.lang.String docId,
           java.lang.String data,
           java.lang.String endFlag) {
           this.docId = docId;
           this.data = data;
           this.endFlag = endFlag;
    }


    /**
     * Gets the docId value for this BatchPrintingMessageType.
     * 
     * @return docId
     */
    public java.lang.String getDocId() {
        return docId;
    }


    /**
     * Sets the docId value for this BatchPrintingMessageType.
     * 
     * @param docId
     */
    public void setDocId(java.lang.String docId) {
        this.docId = docId;
    }


    /**
     * Gets the data value for this BatchPrintingMessageType.
     * 
     * @return data
     */
    public java.lang.String getData() {
        return data;
    }


    /**
     * Sets the data value for this BatchPrintingMessageType.
     * 
     * @param data
     */
    public void setData(java.lang.String data) {
        this.data = data;
    }


    /**
     * Gets the endFlag value for this BatchPrintingMessageType.
     * 
     * @return endFlag
     */
    public java.lang.String getEndFlag() {
        return endFlag;
    }


    /**
     * Sets the endFlag value for this BatchPrintingMessageType.
     * 
     * @param endFlag
     */
    public void setEndFlag(java.lang.String endFlag) {
        this.endFlag = endFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchPrintingMessageType)) return false;
        BatchPrintingMessageType other = (BatchPrintingMessageType) obj;
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
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.endFlag==null && other.getEndFlag()==null) || 
             (this.endFlag!=null &&
              this.endFlag.equals(other.getEndFlag())));
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
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getEndFlag() != null) {
            _hashCode += getEndFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchPrintingMessageType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "BatchPrintingMessageType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "docId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("endFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:printing.ws.contour.nffr/0.2.2", "endFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
