/**
 * Poll.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uts.wsd.soap.client;

public class Poll  implements java.io.Serializable {
    private java.lang.String title;

    private java.util.Calendar creationDate;

    private java.lang.String meetingLocation;

    private java.lang.String description;

    private java.lang.String status;

    private java.util.Calendar[] possibleMeetingDates;

    private java.util.Calendar[][] all_responses;

    private java.lang.String id;  // attribute

    public Poll() {
    }

    public Poll(
           java.lang.String title,
           java.util.Calendar creationDate,
           java.lang.String meetingLocation,
           java.lang.String description,
           java.lang.String status,
           java.util.Calendar[] possibleMeetingDates,
           java.util.Calendar[][] all_responses,
           java.lang.String id) {
           this.title = title;
           this.creationDate = creationDate;
           this.meetingLocation = meetingLocation;
           this.description = description;
           this.status = status;
           this.possibleMeetingDates = possibleMeetingDates;
           this.all_responses = all_responses;
           this.id = id;
    }


    /**
     * Gets the title value for this Poll.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Poll.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the creationDate value for this Poll.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this Poll.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the meetingLocation value for this Poll.
     * 
     * @return meetingLocation
     */
    public java.lang.String getMeetingLocation() {
        return meetingLocation;
    }


    /**
     * Sets the meetingLocation value for this Poll.
     * 
     * @param meetingLocation
     */
    public void setMeetingLocation(java.lang.String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }


    /**
     * Gets the description value for this Poll.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Poll.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the status value for this Poll.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Poll.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the possibleMeetingDates value for this Poll.
     * 
     * @return possibleMeetingDates
     */
    public java.util.Calendar[] getPossibleMeetingDates() {
        return possibleMeetingDates;
    }


    /**
     * Sets the possibleMeetingDates value for this Poll.
     * 
     * @param possibleMeetingDates
     */
    public void setPossibleMeetingDates(java.util.Calendar[] possibleMeetingDates) {
        this.possibleMeetingDates = possibleMeetingDates;
    }


    /**
     * Gets the all_responses value for this Poll.
     * 
     * @return all_responses
     */
    public java.util.Calendar[][] getAll_responses() {
        return all_responses;
    }


    /**
     * Sets the all_responses value for this Poll.
     * 
     * @param all_responses
     */
    public void setAll_responses(java.util.Calendar[][] all_responses) {
        this.all_responses = all_responses;
    }


    /**
     * Gets the id value for this Poll.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Poll.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Poll)) return false;
        Poll other = (Poll) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.meetingLocation==null && other.getMeetingLocation()==null) || 
             (this.meetingLocation!=null &&
              this.meetingLocation.equals(other.getMeetingLocation()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.possibleMeetingDates==null && other.getPossibleMeetingDates()==null) || 
             (this.possibleMeetingDates!=null &&
              java.util.Arrays.equals(this.possibleMeetingDates, other.getPossibleMeetingDates()))) &&
            ((this.all_responses==null && other.getAll_responses()==null) || 
             (this.all_responses!=null &&
              java.util.Arrays.equals(this.all_responses, other.getAll_responses()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getMeetingLocation() != null) {
            _hashCode += getMeetingLocation().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getPossibleMeetingDates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPossibleMeetingDates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPossibleMeetingDates(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAll_responses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAll_responses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAll_responses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Poll.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.wsd.uts/", "poll"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meetingLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "meetingLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("possibleMeetingDates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "possibleMeetingDates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "meetingDate"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("all_responses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "all_responses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.wsd.uts/", "pollResponse"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "person_response"));
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
