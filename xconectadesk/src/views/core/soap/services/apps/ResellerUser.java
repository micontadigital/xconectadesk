/**
 * ResellerUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package views.core.soap.services.apps;

public class ResellerUser  implements java.io.Serializable {
    private java.lang.String status;

    private java.lang.String taxpayer_id;

    public ResellerUser() {
    }

    public ResellerUser(
           java.lang.String status,
           java.lang.String taxpayer_id) {
           this.status = status;
           this.taxpayer_id = taxpayer_id;
    }


    /**
     * Gets the status value for this ResellerUser.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ResellerUser.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the taxpayer_id value for this ResellerUser.
     * 
     * @return taxpayer_id
     */
    public java.lang.String getTaxpayer_id() {
        return taxpayer_id;
    }


    /**
     * Sets the taxpayer_id value for this ResellerUser.
     * 
     * @param taxpayer_id
     */
    public void setTaxpayer_id(java.lang.String taxpayer_id) {
        this.taxpayer_id = taxpayer_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResellerUser)) return false;
        ResellerUser other = (ResellerUser) obj;
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
            ((this.taxpayer_id==null && other.getTaxpayer_id()==null) || 
             (this.taxpayer_id!=null &&
              this.taxpayer_id.equals(other.getTaxpayer_id())));
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
        if (getTaxpayer_id() != null) {
            _hashCode += getTaxpayer_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResellerUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "ResellerUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxpayer_id");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "taxpayer_id"));
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
