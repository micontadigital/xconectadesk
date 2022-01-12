/**
 * CancelaCFDResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package views.core.soap.services.apps;

public class CancelaCFDResult  implements java.io.Serializable {
    private views.core.soap.services.apps.Folio[] folios;

    private java.lang.String acuse;

    private java.lang.String fecha;

    private java.lang.String rfcEmisor;

    private java.lang.String codEstatus;

    public CancelaCFDResult() {
    }

    public CancelaCFDResult(
           views.core.soap.services.apps.Folio[] folios,
           java.lang.String acuse,
           java.lang.String fecha,
           java.lang.String rfcEmisor,
           java.lang.String codEstatus) {
           this.folios = folios;
           this.acuse = acuse;
           this.fecha = fecha;
           this.rfcEmisor = rfcEmisor;
           this.codEstatus = codEstatus;
    }


    /**
     * Gets the folios value for this CancelaCFDResult.
     * 
     * @return folios
     */
    public views.core.soap.services.apps.Folio[] getFolios() {
        return folios;
    }


    /**
     * Sets the folios value for this CancelaCFDResult.
     * 
     * @param folios
     */
    public void setFolios(views.core.soap.services.apps.Folio[] folios) {
        this.folios = folios;
    }


    /**
     * Gets the acuse value for this CancelaCFDResult.
     * 
     * @return acuse
     */
    public java.lang.String getAcuse() {
        return acuse;
    }


    /**
     * Sets the acuse value for this CancelaCFDResult.
     * 
     * @param acuse
     */
    public void setAcuse(java.lang.String acuse) {
        this.acuse = acuse;
    }


    /**
     * Gets the fecha value for this CancelaCFDResult.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this CancelaCFDResult.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the rfcEmisor value for this CancelaCFDResult.
     * 
     * @return rfcEmisor
     */
    public java.lang.String getRfcEmisor() {
        return rfcEmisor;
    }


    /**
     * Sets the rfcEmisor value for this CancelaCFDResult.
     * 
     * @param rfcEmisor
     */
    public void setRfcEmisor(java.lang.String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }


    /**
     * Gets the codEstatus value for this CancelaCFDResult.
     * 
     * @return codEstatus
     */
    public java.lang.String getCodEstatus() {
        return codEstatus;
    }


    /**
     * Sets the codEstatus value for this CancelaCFDResult.
     * 
     * @param codEstatus
     */
    public void setCodEstatus(java.lang.String codEstatus) {
        this.codEstatus = codEstatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelaCFDResult)) return false;
        CancelaCFDResult other = (CancelaCFDResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.folios==null && other.getFolios()==null) || 
             (this.folios!=null &&
              java.util.Arrays.equals(this.folios, other.getFolios()))) &&
            ((this.acuse==null && other.getAcuse()==null) || 
             (this.acuse!=null &&
              this.acuse.equals(other.getAcuse()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.rfcEmisor==null && other.getRfcEmisor()==null) || 
             (this.rfcEmisor!=null &&
              this.rfcEmisor.equals(other.getRfcEmisor()))) &&
            ((this.codEstatus==null && other.getCodEstatus()==null) || 
             (this.codEstatus!=null &&
              this.codEstatus.equals(other.getCodEstatus())));
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
        if (getFolios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFolios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFolios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAcuse() != null) {
            _hashCode += getAcuse().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getRfcEmisor() != null) {
            _hashCode += getRfcEmisor().hashCode();
        }
        if (getCodEstatus() != null) {
            _hashCode += getCodEstatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelaCFDResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "CancelaCFDResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folios");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Folios"));
        elemField.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "Folio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Folio"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acuse");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Acuse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rfcEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "RfcEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codEstatus");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "CodEstatus"));
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
