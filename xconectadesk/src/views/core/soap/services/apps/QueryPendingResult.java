/**
 * QueryPendingResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package views.core.soap.services.apps;

public class QueryPendingResult  implements java.io.Serializable {
    private java.lang.String status;

    private java.lang.String xml;

    private java.lang.String uuid;

    private java.lang.String uuid_status;

    private java.lang.String next_attempt;

    private java.lang.String attempts;

    private java.lang.String error;

    private java.lang.String date;

    public QueryPendingResult() {
    }

    public QueryPendingResult(
           java.lang.String status,
           java.lang.String xml,
           java.lang.String uuid,
           java.lang.String uuid_status,
           java.lang.String next_attempt,
           java.lang.String attempts,
           java.lang.String error,
           java.lang.String date) {
           this.status = status;
           this.xml = xml;
           this.uuid = uuid;
           this.uuid_status = uuid_status;
           this.next_attempt = next_attempt;
           this.attempts = attempts;
           this.error = error;
           this.date = date;
    }


    /**
     * Gets the status value for this QueryPendingResult.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this QueryPendingResult.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the xml value for this QueryPendingResult.
     * 
     * @return xml
     */
    public java.lang.String getXml() {
        return xml;
    }


    /**
     * Sets the xml value for this QueryPendingResult.
     * 
     * @param xml
     */
    public void setXml(java.lang.String xml) {
        this.xml = xml;
    }


    /**
     * Gets the uuid value for this QueryPendingResult.
     * 
     * @return uuid
     */
    public java.lang.String getUuid() {
        return uuid;
    }


    /**
     * Sets the uuid value for this QueryPendingResult.
     * 
     * @param uuid
     */
    public void setUuid(java.lang.String uuid) {
        this.uuid = uuid;
    }


    /**
     * Gets the uuid_status value for this QueryPendingResult.
     * 
     * @return uuid_status
     */
    public java.lang.String getUuid_status() {
        return uuid_status;
    }


    /**
     * Sets the uuid_status value for this QueryPendingResult.
     * 
     * @param uuid_status
     */
    public void setUuid_status(java.lang.String uuid_status) {
        this.uuid_status = uuid_status;
    }


    /**
     * Gets the next_attempt value for this QueryPendingResult.
     * 
     * @return next_attempt
     */
    public java.lang.String getNext_attempt() {
        return next_attempt;
    }


    /**
     * Sets the next_attempt value for this QueryPendingResult.
     * 
     * @param next_attempt
     */
    public void setNext_attempt(java.lang.String next_attempt) {
        this.next_attempt = next_attempt;
    }


    /**
     * Gets the attempts value for this QueryPendingResult.
     * 
     * @return attempts
     */
    public java.lang.String getAttempts() {
        return attempts;
    }


    /**
     * Sets the attempts value for this QueryPendingResult.
     * 
     * @param attempts
     */
    public void setAttempts(java.lang.String attempts) {
        this.attempts = attempts;
    }


    /**
     * Gets the error value for this QueryPendingResult.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this QueryPendingResult.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }


    /**
     * Gets the date value for this QueryPendingResult.
     * 
     * @return date
     */
    public java.lang.String getDate() {
        return date;
    }


    /**
     * Sets the date value for this QueryPendingResult.
     * 
     * @param date
     */
    public void setDate(java.lang.String date) {
        this.date = date;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryPendingResult)) return false;
        QueryPendingResult other = (QueryPendingResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.xml==null && other.getXml()==null) || 
             (this.xml!=null &&
              this.xml.equals(other.getXml()))) &&
            ((this.uuid==null && other.getUuid()==null) || 
             (this.uuid!=null &&
              this.uuid.equals(other.getUuid()))) &&
            ((this.uuid_status==null && other.getUuid_status()==null) || 
             (this.uuid_status!=null &&
              this.uuid_status.equals(other.getUuid_status()))) &&
            ((this.next_attempt==null && other.getNext_attempt()==null) || 
             (this.next_attempt!=null &&
              this.next_attempt.equals(other.getNext_attempt()))) &&
            ((this.attempts==null && other.getAttempts()==null) || 
             (this.attempts!=null &&
              this.attempts.equals(other.getAttempts()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getXml() != null) {
            _hashCode += getXml().hashCode();
        }
        if (getUuid() != null) {
            _hashCode += getUuid().hashCode();
        }
        if (getUuid_status() != null) {
            _hashCode += getUuid_status().hashCode();
        }
        if (getNext_attempt() != null) {
            _hashCode += getNext_attempt().hashCode();
        }
        if (getAttempts() != null) {
            _hashCode += getAttempts().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryPendingResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "QueryPendingResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xml");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "xml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uuid");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "uuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uuid_status");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "uuid_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("next_attempt");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "next_attempt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attempts");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "attempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
