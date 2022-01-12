/**
 * Incidencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package views.core.soap.services.apps;

public class Incidencia  implements java.io.Serializable {
    private java.lang.String idIncidencia;

    private java.lang.String rfcEmisor;

    private java.lang.String uuid;

    private java.lang.String codigoError;

    private java.lang.String workProcessId;

    private java.lang.String mensajeIncidencia;

    private java.lang.String extraInfo;

    private java.lang.String noCertificadoPac;

    private java.lang.String fechaRegistro;

    public Incidencia() {
    }

    public Incidencia(
           java.lang.String idIncidencia,
           java.lang.String rfcEmisor,
           java.lang.String uuid,
           java.lang.String codigoError,
           java.lang.String workProcessId,
           java.lang.String mensajeIncidencia,
           java.lang.String extraInfo,
           java.lang.String noCertificadoPac,
           java.lang.String fechaRegistro) {
           this.idIncidencia = idIncidencia;
           this.rfcEmisor = rfcEmisor;
           this.uuid = uuid;
           this.codigoError = codigoError;
           this.workProcessId = workProcessId;
           this.mensajeIncidencia = mensajeIncidencia;
           this.extraInfo = extraInfo;
           this.noCertificadoPac = noCertificadoPac;
           this.fechaRegistro = fechaRegistro;
    }


    /**
     * Gets the idIncidencia value for this Incidencia.
     * 
     * @return idIncidencia
     */
    public java.lang.String getIdIncidencia() {
        return idIncidencia;
    }


    /**
     * Sets the idIncidencia value for this Incidencia.
     * 
     * @param idIncidencia
     */
    public void setIdIncidencia(java.lang.String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }


    /**
     * Gets the rfcEmisor value for this Incidencia.
     * 
     * @return rfcEmisor
     */
    public java.lang.String getRfcEmisor() {
        return rfcEmisor;
    }


    /**
     * Sets the rfcEmisor value for this Incidencia.
     * 
     * @param rfcEmisor
     */
    public void setRfcEmisor(java.lang.String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }


    /**
     * Gets the uuid value for this Incidencia.
     * 
     * @return uuid
     */
    public java.lang.String getUuid() {
        return uuid;
    }


    /**
     * Sets the uuid value for this Incidencia.
     * 
     * @param uuid
     */
    public void setUuid(java.lang.String uuid) {
        this.uuid = uuid;
    }


    /**
     * Gets the codigoError value for this Incidencia.
     * 
     * @return codigoError
     */
    public java.lang.String getCodigoError() {
        return codigoError;
    }


    /**
     * Sets the codigoError value for this Incidencia.
     * 
     * @param codigoError
     */
    public void setCodigoError(java.lang.String codigoError) {
        this.codigoError = codigoError;
    }


    /**
     * Gets the workProcessId value for this Incidencia.
     * 
     * @return workProcessId
     */
    public java.lang.String getWorkProcessId() {
        return workProcessId;
    }


    /**
     * Sets the workProcessId value for this Incidencia.
     * 
     * @param workProcessId
     */
    public void setWorkProcessId(java.lang.String workProcessId) {
        this.workProcessId = workProcessId;
    }


    /**
     * Gets the mensajeIncidencia value for this Incidencia.
     * 
     * @return mensajeIncidencia
     */
    public java.lang.String getMensajeIncidencia() {
        return mensajeIncidencia;
    }


    /**
     * Sets the mensajeIncidencia value for this Incidencia.
     * 
     * @param mensajeIncidencia
     */
    public void setMensajeIncidencia(java.lang.String mensajeIncidencia) {
        this.mensajeIncidencia = mensajeIncidencia;
    }


    /**
     * Gets the extraInfo value for this Incidencia.
     * 
     * @return extraInfo
     */
    public java.lang.String getExtraInfo() {
        return extraInfo;
    }


    /**
     * Sets the extraInfo value for this Incidencia.
     * 
     * @param extraInfo
     */
    public void setExtraInfo(java.lang.String extraInfo) {
        this.extraInfo = extraInfo;
    }


    /**
     * Gets the noCertificadoPac value for this Incidencia.
     * 
     * @return noCertificadoPac
     */
    public java.lang.String getNoCertificadoPac() {
        return noCertificadoPac;
    }


    /**
     * Sets the noCertificadoPac value for this Incidencia.
     * 
     * @param noCertificadoPac
     */
    public void setNoCertificadoPac(java.lang.String noCertificadoPac) {
        this.noCertificadoPac = noCertificadoPac;
    }


    /**
     * Gets the fechaRegistro value for this Incidencia.
     * 
     * @return fechaRegistro
     */
    public java.lang.String getFechaRegistro() {
        return fechaRegistro;
    }


    /**
     * Sets the fechaRegistro value for this Incidencia.
     * 
     * @param fechaRegistro
     */
    public void setFechaRegistro(java.lang.String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Incidencia)) return false;
        Incidencia other = (Incidencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idIncidencia==null && other.getIdIncidencia()==null) || 
             (this.idIncidencia!=null &&
              this.idIncidencia.equals(other.getIdIncidencia()))) &&
            ((this.rfcEmisor==null && other.getRfcEmisor()==null) || 
             (this.rfcEmisor!=null &&
              this.rfcEmisor.equals(other.getRfcEmisor()))) &&
            ((this.uuid==null && other.getUuid()==null) || 
             (this.uuid!=null &&
              this.uuid.equals(other.getUuid()))) &&
            ((this.codigoError==null && other.getCodigoError()==null) || 
             (this.codigoError!=null &&
              this.codigoError.equals(other.getCodigoError()))) &&
            ((this.workProcessId==null && other.getWorkProcessId()==null) || 
             (this.workProcessId!=null &&
              this.workProcessId.equals(other.getWorkProcessId()))) &&
            ((this.mensajeIncidencia==null && other.getMensajeIncidencia()==null) || 
             (this.mensajeIncidencia!=null &&
              this.mensajeIncidencia.equals(other.getMensajeIncidencia()))) &&
            ((this.extraInfo==null && other.getExtraInfo()==null) || 
             (this.extraInfo!=null &&
              this.extraInfo.equals(other.getExtraInfo()))) &&
            ((this.noCertificadoPac==null && other.getNoCertificadoPac()==null) || 
             (this.noCertificadoPac!=null &&
              this.noCertificadoPac.equals(other.getNoCertificadoPac()))) &&
            ((this.fechaRegistro==null && other.getFechaRegistro()==null) || 
             (this.fechaRegistro!=null &&
              this.fechaRegistro.equals(other.getFechaRegistro())));
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
        if (getIdIncidencia() != null) {
            _hashCode += getIdIncidencia().hashCode();
        }
        if (getRfcEmisor() != null) {
            _hashCode += getRfcEmisor().hashCode();
        }
        if (getUuid() != null) {
            _hashCode += getUuid().hashCode();
        }
        if (getCodigoError() != null) {
            _hashCode += getCodigoError().hashCode();
        }
        if (getWorkProcessId() != null) {
            _hashCode += getWorkProcessId().hashCode();
        }
        if (getMensajeIncidencia() != null) {
            _hashCode += getMensajeIncidencia().hashCode();
        }
        if (getExtraInfo() != null) {
            _hashCode += getExtraInfo().hashCode();
        }
        if (getNoCertificadoPac() != null) {
            _hashCode += getNoCertificadoPac().hashCode();
        }
        if (getFechaRegistro() != null) {
            _hashCode += getFechaRegistro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Incidencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("apps.services.soap.core.views", "Incidencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIncidencia");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "IdIncidencia"));
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
        elemField.setFieldName("uuid");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "Uuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoError");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "CodigoError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workProcessId");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "WorkProcessId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeIncidencia");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "MensajeIncidencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "ExtraInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noCertificadoPac");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "NoCertificadoPac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("apps.services.soap.core.views", "FechaRegistro"));
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
