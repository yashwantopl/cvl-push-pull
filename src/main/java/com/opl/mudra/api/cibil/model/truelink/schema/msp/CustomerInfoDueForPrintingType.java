
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.ServicingLetterType;


/**
 * <p>Java class for CustomerInfoDueForPrintingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerInfoDueForPrintingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PartnerCustomerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FullNameInfo" type="{com/truelink/schema/msp}FullNameInfoType"/>
 *         &lt;element name="Address" type="{com/truelink/schema/msp}AddressType"/>
 *         &lt;element name="ReportAndScore" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FFR" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="ReportAssetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="Score" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ScoreAssetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}ServicingLetter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerInfoDueForPrintingType", propOrder = {
    "customerId",
    "partnerCustomerId",
    "fullNameInfo",
    "address",
    "reportAndScore",
    "servicingLetter"
})
public class CustomerInfoDueForPrintingType {

    @XmlElement(name = "CustomerId")
    protected long customerId;
    @XmlElement(name = "PartnerCustomerId", required = true)
    protected String partnerCustomerId;
    @XmlElement(name = "FullNameInfo", required = true)
    protected FullNameInfoType fullNameInfo;
    @XmlElement(name = "Address", required = true)
    protected AddressType address;
    @XmlElement(name = "ReportAndScore")
    protected CustomerInfoDueForPrintingType.ReportAndScore reportAndScore;
    @XmlElement(name = "ServicingLetter", namespace = "com/truelink/schema/database/tcps/enumerations")
    @XmlSchemaType(name = "string")
    protected ServicingLetterType servicingLetter;

    /**
     * Gets the value of the customerId property.
     *
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     *
     */
    public void setCustomerId(long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the partnerCustomerId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPartnerCustomerId() {
        return partnerCustomerId;
    }

    /**
     * Sets the value of the partnerCustomerId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPartnerCustomerId(String value) {
        this.partnerCustomerId = value;
    }

    /**
     * Gets the value of the fullNameInfo property.
     *
     * @return
     *     possible object is
     *     {@link FullNameInfoType }
     *
     */
    public FullNameInfoType getFullNameInfo() {
        return fullNameInfo;
    }

    /**
     * Sets the value of the fullNameInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link FullNameInfoType }
     *
     */
    public void setFullNameInfo(FullNameInfoType value) {
        this.fullNameInfo = value;
    }

    /**
     * Gets the value of the address property.
     *
     * @return
     *     possible object is
     *     {@link AddressType }
     *
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the reportAndScore property.
     *
     * @return
     *     possible object is
     *     {@link CustomerInfoDueForPrintingType.ReportAndScore }
     *
     */
    public CustomerInfoDueForPrintingType.ReportAndScore getReportAndScore() {
        return reportAndScore;
    }

    /**
     * Sets the value of the reportAndScore property.
     *
     * @param value
     *     allowed object is
     *     {@link CustomerInfoDueForPrintingType.ReportAndScore }
     *
     */
    public void setReportAndScore(CustomerInfoDueForPrintingType.ReportAndScore value) {
        this.reportAndScore = value;
    }

    /**
     * Gets the value of the servicingLetter property.
     * 
     * @return
     *     possible object is
     *     {@link ServicingLetterType }
     *     
     */
    public ServicingLetterType getServicingLetter() {
        return servicingLetter;
    }

    /**
     * Sets the value of the servicingLetter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicingLetterType }
     *     
     */
    public void setServicingLetter(ServicingLetterType value) {
        this.servicingLetter = value;
    }


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
     *         &lt;element name="FFR" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="ReportAssetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="Score" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ScoreAssetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "ffr",
        "reportAssetId",
        "score",
        "scoreAssetId"
    })
    public static class ReportAndScore {

        @XmlElement(name = "FFR", required = true)
        protected Object ffr;
        @XmlElement(name = "ReportAssetId")
        protected long reportAssetId;
        @XmlElement(name = "Score", required = true)
        protected String score;
        @XmlElement(name = "ScoreAssetId")
        protected long scoreAssetId;

        /**
         * Gets the value of the ffr property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getFFR() {
            return ffr;
        }

        /**
         * Sets the value of the ffr property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setFFR(Object value) {
            this.ffr = value;
        }

        /**
         * Gets the value of the reportAssetId property.
         * 
         */
        public long getReportAssetId() {
            return reportAssetId;
        }

        /**
         * Sets the value of the reportAssetId property.
         * 
         */
        public void setReportAssetId(long value) {
            this.reportAssetId = value;
        }

        /**
         * Gets the value of the score property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getScore() {
            return score;
        }

        /**
         * Sets the value of the score property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setScore(String value) {
            this.score = value;
        }

        /**
         * Gets the value of the scoreAssetId property.
         * 
         */
        public long getScoreAssetId() {
            return scoreAssetId;
        }

        /**
         * Sets the value of the scoreAssetId property.
         * 
         */
        public void setScoreAssetId(long value) {
            this.scoreAssetId = value;
        }

    }

}
