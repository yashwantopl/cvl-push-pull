
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{com/truelink/ds/sch/report/truelink/v5}CodeRef">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="remarksCode" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{com/truelink/ds/sch/report/truelink/v5}CodeRef">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "errorCode",
    "remarksCode"
})
@XmlRootElement(name = "DisputeRemarks")
public class DisputeRemarks {

    protected DisputeRemarks.ErrorCode errorCode;
    protected DisputeRemarks.RemarksCode remarksCode;

    /**
     * Gets the value of the errorCode property.
     *
     * @return
     *     possible object is
     *     {@link DisputeRemarks.ErrorCode }
     *
     */
    public DisputeRemarks.ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     *
     * @param value
     *     allowed object is
     *     {@link DisputeRemarks.ErrorCode }
     *
     */
    public void setErrorCode(DisputeRemarks.ErrorCode value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the remarksCode property.
     *
     * @return
     *     possible object is
     *     {@link DisputeRemarks.RemarksCode }
     *
     */
    public DisputeRemarks.RemarksCode getRemarksCode() {
        return remarksCode;
    }

    /**
     * Sets the value of the remarksCode property.
     *
     * @param value
     *     allowed object is
     *     {@link DisputeRemarks.RemarksCode }
     *
     */
    public void setRemarksCode(DisputeRemarks.RemarksCode value) {
        this.remarksCode = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{com/truelink/ds/sch/report/truelink/v5}CodeRef">
     *       &lt;sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ErrorCode
        extends CodeRef
    {

        @XmlAttribute(name = "dateUpdated")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateUpdated;

        /**
         * Gets the value of the dateUpdated property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateUpdated() {
            return dateUpdated;
        }

        /**
         * Sets the value of the dateUpdated property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateUpdated(XMLGregorianCalendar value) {
            this.dateUpdated = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{com/truelink/ds/sch/report/truelink/v5}CodeRef">
     *       &lt;sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class RemarksCode
        extends CodeRef
    {

        @XmlAttribute(name = "dateUpdated")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateUpdated;

        /**
         * Gets the value of the dateUpdated property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateUpdated() {
            return dateUpdated;
        }

        /**
         * Sets the value of the dateUpdated property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateUpdated(XMLGregorianCalendar value) {
            this.dateUpdated = value;
        }

    }

}
