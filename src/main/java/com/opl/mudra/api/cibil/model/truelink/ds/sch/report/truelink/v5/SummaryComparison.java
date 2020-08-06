
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="TradelineSummary">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Bureau" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}TradelineSummaryInfoComparison" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="InquirySummary">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Bureau" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}InquirySummaryInfoComparison" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PublicRecordSummary">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Bureau" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}PublicRecordSummaryInfoComparison" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
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
    "tradelineSummary",
    "inquirySummary",
    "publicRecordSummary"
})
@XmlRootElement(name = "SummaryComparison")
public class SummaryComparison {

    @XmlElement(name = "TradelineSummary", required = true)
    protected SummaryComparison.TradelineSummary tradelineSummary;
    @XmlElement(name = "InquirySummary", required = true)
    protected SummaryComparison.InquirySummary inquirySummary;
    @XmlElement(name = "PublicRecordSummary", required = true)
    protected SummaryComparison.PublicRecordSummary publicRecordSummary;

    /**
     * Gets the value of the tradelineSummary property.
     *
     * @return
     *     possible object is
     *     {@link SummaryComparison.TradelineSummary }
     *
     */
    public SummaryComparison.TradelineSummary getTradelineSummary() {
        return tradelineSummary;
    }

    /**
     * Sets the value of the tradelineSummary property.
     *
     * @param value
     *     allowed object is
     *     {@link SummaryComparison.TradelineSummary }
     *
     */
    public void setTradelineSummary(SummaryComparison.TradelineSummary value) {
        this.tradelineSummary = value;
    }

    /**
     * Gets the value of the inquirySummary property.
     *
     * @return
     *     possible object is
     *     {@link SummaryComparison.InquirySummary }
     *
     */
    public SummaryComparison.InquirySummary getInquirySummary() {
        return inquirySummary;
    }

    /**
     * Sets the value of the inquirySummary property.
     *
     * @param value
     *     allowed object is
     *     {@link SummaryComparison.InquirySummary }
     *
     */
    public void setInquirySummary(SummaryComparison.InquirySummary value) {
        this.inquirySummary = value;
    }

    /**
     * Gets the value of the publicRecordSummary property.
     *
     * @return
     *     possible object is
     *     {@link SummaryComparison.PublicRecordSummary }
     *
     */
    public SummaryComparison.PublicRecordSummary getPublicRecordSummary() {
        return publicRecordSummary;
    }

    /**
     * Sets the value of the publicRecordSummary property.
     *
     * @param value
     *     allowed object is
     *     {@link SummaryComparison.PublicRecordSummary }
     *
     */
    public void setPublicRecordSummary(SummaryComparison.PublicRecordSummary value) {
        this.publicRecordSummary = value;
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
     *         &lt;element name="Bureau" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}InquirySummaryInfoComparison" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
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
        "bureau"
    })
    public static class InquirySummary {

        @XmlElement(name = "Bureau")
        protected List<Bureau> bureau;

        /**
         * Gets the value of the bureau property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bureau property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBureau().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SummaryComparison.InquirySummary.Bureau }
         *
         *
         */
        public List<Bureau> getBureau() {
            if (bureau == null) {
                bureau = new ArrayList<Bureau>();
            }
            return this.bureau;
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
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}InquirySummaryInfoComparison" minOccurs="0"/>
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
            "name",
            "info"
        })
        public static class Bureau {

            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Info")
            protected InquirySummaryInfoComparison info;

            /**
             * Gets the value of the name property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the info property.
             *
             * @return
             *     possible object is
             *     {@link InquirySummaryInfoComparison }
             *
             */
            public InquirySummaryInfoComparison getInfo() {
                return info;
            }

            /**
             * Sets the value of the info property.
             *
             * @param value
             *     allowed object is
             *     {@link InquirySummaryInfoComparison }
             *
             */
            public void setInfo(InquirySummaryInfoComparison value) {
                this.info = value;
            }

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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Bureau" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}PublicRecordSummaryInfoComparison" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
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
        "bureau"
    })
    public static class PublicRecordSummary {

        @XmlElement(name = "Bureau")
        protected List<Bureau> bureau;

        /**
         * Gets the value of the bureau property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bureau property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBureau().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SummaryComparison.PublicRecordSummary.Bureau }
         *
         *
         */
        public List<Bureau> getBureau() {
            if (bureau == null) {
                bureau = new ArrayList<Bureau>();
            }
            return this.bureau;
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
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}PublicRecordSummaryInfoComparison" minOccurs="0"/>
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
            "name",
            "info"
        })
        public static class Bureau {

            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Info")
            protected PublicRecordSummaryInfoComparison info;

            /**
             * Gets the value of the name property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the info property.
             *
             * @return
             *     possible object is
             *     {@link PublicRecordSummaryInfoComparison }
             *
             */
            public PublicRecordSummaryInfoComparison getInfo() {
                return info;
            }

            /**
             * Sets the value of the info property.
             *
             * @param value
             *     allowed object is
             *     {@link PublicRecordSummaryInfoComparison }
             *
             */
            public void setInfo(PublicRecordSummaryInfoComparison value) {
                this.info = value;
            }

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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Bureau" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}TradelineSummaryInfoComparison" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
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
        "bureau"
    })
    public static class TradelineSummary {

        @XmlElement(name = "Bureau")
        protected List<Bureau> bureau;

        /**
         * Gets the value of the bureau property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bureau property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBureau().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SummaryComparison.TradelineSummary.Bureau }
         *
         *
         */
        public List<Bureau> getBureau() {
            if (bureau == null) {
                bureau = new ArrayList<Bureau>();
            }
            return this.bureau;
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
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}TradelineSummaryInfoComparison" minOccurs="0"/>
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
            "name",
            "info"
        })
        public static class Bureau {

            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Info")
            protected TradelineSummaryInfoComparison info;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the info property.
             * 
             * @return
             *     possible object is
             *     {@link TradelineSummaryInfoComparison }
             *     
             */
            public TradelineSummaryInfoComparison getInfo() {
                return info;
            }

            /**
             * Sets the value of the info property.
             * 
             * @param value
             *     allowed object is
             *     {@link TradelineSummaryInfoComparison }
             *     
             */
            public void setInfo(TradelineSummaryInfoComparison value) {
                this.info = value;
            }

        }

    }

}
