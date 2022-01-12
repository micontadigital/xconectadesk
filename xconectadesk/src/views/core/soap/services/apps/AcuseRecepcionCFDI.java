/**
 * AcuseRecepcionCFDI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package views.core.soap.services.apps;

public class AcuseRecepcionCFDI  implements java.io.Serializable {
    private java.lang.String xml;

    private java.lang.String UUID;

    private java.lang.String faultstring;

    private java.lang.String fecha;

    private java.lang.String codEstatus;

    private java.lang.String faultcode;

    private java.lang.String satSeal;

    private views.core.soap.services.apps.Incidencia[] incidencias;

    private java.lang.String noCertificadoSAT;

    public AcuseRecepcionCFDI() {
    }

    public AcuseRecepcionCFDI(
           java.lang.String xml,
           java.lang.String UUID,
           java.lang.String faultstring,
           java.lang.String fecha,
           java.lang.String codEstatus,
           java.lang.String faultcode,
           java.lang.String satSeal,
           views.core.soap.services.apps.Incidencia[] incidencias,
           java.lang.String noCertificadoSAT) {
           this.xml = xml;
           this.UUID = UUID;
           this.faultstring = faultstring;
           this.fecha = fecha;
           this.codEstatus = codEstatus;
           this.faultcode = faultcode;
           this.satSeal = satSeal;
           this.incidencias = incidencias;
           this.noCertificadoSAT = noCertificadoSAT;
    }


    /**
     * Gets the xml value for this AcuseRecepcionCFDI.
     * 
     * @return xml
     */
    public java.lang.String getXml() {
        return xml;
    }


    /**
     * Sets the xml value for this AcuseRecepcionCFDI.
     * 
     * @param xml
     */
    public void setXml(java.lang.String xml) {
        this.xml = xml;
    }


    /**
     * Gets the UUID value for this AcuseRecepcionCFDI.
     * 
     * @return UUID
     */
    public java.lang.String getUUID() {
        return UUID;
    }


    /**
     * Sets the UUID value for this AcuseRecepcionCFDI.
     * 
     * @param UUID
     */
    public void setUUID(java.lang.String UUID) {
        this.UUID = UUID;
    }


    /**
     * Gets the faultstring value for this AcuseRecepcionCFDI.
     * 
     * @return faultstring
     */
    public java.lang.String getFaultstring() {
        return faultstring;
    }


    /**
     * Sets the faultstring value for this AcuseRecepcionCFDI.
     * 
     * @param faultstring
     */
    public void setFaultstring(java.lang.String faultstring) {
        this.faultstring = faultstring;
    }


    /**
     * Gets the fecha value for this AcuseRecepcionCFDI.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this AcuseRecepcionCFDI.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the codEstatus value for this AcuseRecepcionCFDI.
     * 
     * @return codEstatus
     */
    public java.lang.String getCodEstatus() {
        return codEstatus;
    }


    /**
     * Sets the codEstatus value for this AcuseRecepcionCFDI.
     * 
     * @param codEstatus
     */
    public void setCodEstatus(java.lang.String codEstatus) {
        this.codEstatus = codEstatus;
    }


    /**
     * Gets the faultcode value for this AcuseRecepcionCFDI.
     * 
     * @return faultcode
     */
    public java.lang.String getFaultcode() {
        return faultcode;
    }


    /**
     * Sets the faultcode value for this AcuseRecepcionCFDI.
     * 
     * @param faultcode
     */
    public void setFaultcode(java.lang.String faultcode) {
        this.faultcode = faultcode;
    }


    /**
     * Gets the satSeal value for this AcuseRecepcionCFDI.
     * 
     * @return satSeal
     */
    public java.lang.String getSatSeal() {
        return satSeal;
    }


    /**
     * Sets the satSeal value for this AcuseRecepcionCFDI.
     * 
     * @param satSeal
     */
    public void setSatSeal(java.lang.String satSeal) {
        this.satSeal = satSeal;
    }


    /**
     * Gets the incidencias value for this AcuseRecepcionCFDI.
     * 
     * @return incidencias
     */
    public views.core.soap.services.apps.Incidencia[] getIncidencias() {
        return incidencias;
    }


    /**
     * Sets the incidencias value for this AcuseRecepcionCFDI.
     * 
     * @param incidencias
     */
    public void setIncidencias(views.core.soap.services.apps.Incidencia[] incidencias) {
        this.incidencias = incidencias;
    }


    /**
     * Gets the noCertificadoSAT value for this AcuseRecepcionCFDI.
     * 
     * @return noCertificadoSAT
     */
    public java.lang.String getNoCertificadoSAT() {
        return noCertificadoSAT;
    }


    /**
     * Sets the noCertificadoSAT value for this AcuseRecepcionCFDI.
     * 
     * @param noCertificadoSAT
     */
    public void setNoCertificadoSAT(java.lang.String noCertificadoSAT) {
        this.noCertificadoSAT = noCertificadoSAT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AcuseRecepcionCFDI)) return false;
        AcuseRecepcionCFDI other = (AcuseRecepcionCFDI) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.xml==null && other.getXml()==null) || 
             (this.xml!=null &&
              this.xml.equals(other.getXml()))) &&
            ((this.UUID==null && other.getUUID()==null) || 
             (this.UUID!=null &&
              this.UUID.equals(other.getUUID()))) &&
            ((this.faultstring==null && other.getFaultstring()==null) || 
             (this.faultstring!=null &&
              this.faultstring.equals(other.getFaultstring()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.codEstatus==null && other.getCodEstatus()==null) || 
             (this.codEstatus!=null &&
              this.codEstatus.equals(other.getCodEstatus()))) &&
            ((this.faultcode==null && other.getFaultcode()==null) || 
             (this.faultcode!=null &&
              this.faultcode.equals(other.getFaultcode()))) &&
            ((this.satSeal==null && other.getSatSeal()==null) || 
             (this.satSeal!=null &&
              this.satSeal.equals(other.getSatSeal()))) &&
            ((this.incidencias==null && other.getIncidencias()==null) || 
             (this.incidencias!=null &&
              java.util.Arrays.equals(this.incidencias, other.getIncidencias()))) &&
            ((this.noCertificadoSAT==null && other.getNoCertificadoSAT()==null) || 
             (this.noCertificadoSAT!=null &&
              this.noCertificadoSAT.equals(other.getNoCertificadoSAT())));
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
        if (getXml() != null) {
            _hashCode += getXml().hashCode();
        }
        if (getUUID() != null) {
            _hashCode += getUUID().hashCode();
        }
        if (getFaultstring() != null) {
            _hashCode += getFaultstring().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getCodEstatus() != null) {
            _hashCode += getCodEstatus().hashCode();
        }
        if (getFaultcode() != null) {
            _hashCode += getFaultcode().hashCode();
        }
        if (getSatSeal() != null) {
            _hashCode += getSatSeal().hashCode();
        }
        if (getIncidencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIncidencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIncidencias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNoCertificadoSAT() != null) {
            _hashCode += getNoCertificadoSAT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AcuseRecepcionCFDI.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "AcuseRecepcionCFDI"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xml");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "xml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UUID");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "UUID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultstring");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "faultstring"));
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
        elemField.setFieldName("codEstatus");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "CodEstatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultcode");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "faultcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("satSeal");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "SatSeal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incidencias");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Incidencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "Incidencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Incidencia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noCertificadoSAT");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "NoCertificadoSAT"));
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
