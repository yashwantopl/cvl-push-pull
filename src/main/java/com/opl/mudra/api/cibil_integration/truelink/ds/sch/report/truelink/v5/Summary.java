
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

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
 *                             &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}TradelineSummaryInfo" minOccurs="0"/>
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
 *                             &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}InquirySummaryInfo" minOccurs="0"/>
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
 *                             &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}PublicRecordSummaryInfo" minOccurs="0"/>
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
@XmlRootElement(name = "Summary")
public class Summary {

    @XmlElement(name = "TradelineSummary", required = true)
    protected Summary.TradelineSummary tradelineSummary;
    @XmlElement(name = "InquirySummary", required = true)
    protected Summary.InquirySummary inquirySummary;
    @XmlElement(name = "PublicRecordSummary", required = true)
    protected Summary.PublicRecordSummary publicRecordSummary;

    /**
     * Gets the value of the tradelineSummary property.
     *
     * @return
     *     possible object is
     *     {@link Summary.TradelineSummary }
     *
     */
    public Summary.TradelineSummary getTradelineSummary() {
        return tradelineSummary;
    }

    /**
     * Sets the value of the tradelineSummary property.
     *
     * @param value
     *     allowed object is
     *     {@link Summary.TradelineSummary }
     *
     */
    public void setTradelineSummary(Summary.TradelineSummary value) {
        this.tradelineSummary = value;
    }

    /**
     * Gets the value of the inquirySummary property.
     *
     * @return
     *     possible object is
     *     {@link Summary.InquirySummary }
     *
     */
    public Summary.InquirySummary getInquirySummary() {
        return inquirySummary;
    }

    /**
     * Sets the value of the inquirySummary property.
     *
     * @param value
     *     allowed object is
     *     {@link Summary.InquirySummary }
     *
     */
    public void setInquirySummary(Summary.InquirySummary value) {
        this.inquirySummary = value;
    }

    /**
     * Gets the value of the publicRecordSummary property.
     *
     * @return
     *     possible object is
     *     {@link Summary.PublicRecordSummary }
     *
     */
    public Summary.PublicRecordSummary getPublicRecordSummary() {
        return publicRecordSummary;
    }

    /**
     * Sets the value of the publicRecordSummary property.
     *
     * @param value
     *     allowed object is
     *     {@link Summary.PublicRecordSummary }
     *
     */
    public void setPublicRecordSummary(Summary.PublicRecordSummary value) {
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
     *                   &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}InquirySummaryInfo" minOccurs="0"/>
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
         * {@link Summary.InquirySummary.Bureau }
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
         *         &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}InquirySummaryInfo" minOccurs="0"/>
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
            protected InquirySummaryInfo info;

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
             *     {@link InquirySummaryInfo }
             *
             */
            public InquirySummaryInfo getInfo() {
                return info;
            }

            /**
             * Sets the value of the info property.
             *
             * @param value
             *     allowed object is
             *     {@link InquirySummaryInfo }
             *
             */
            public void setInfo(InquirySummaryInfo value) {
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
     *                   &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}PublicRecordSummaryInfo" minOccurs="0"/>
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
         * {@link Summary.PublicRecordSummary.Bureau }
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
         *         &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}PublicRecordSummaryInfo" minOccurs="0"/>
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
            protected PublicRecordSummaryInfo info;

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
             *     {@link PublicRecordSummaryInfo }
             *
             */
            public PublicRecordSummaryInfo getInfo() {
                return info;
            }

            /**
             * Sets the value of the info property.
             *
             * @param value
             *     allowed object is
             *     {@link PublicRecordSummaryInfo }
             *
             */
            public void setInfo(PublicRecordSummaryInfo value) {
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
     *                   &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}TradelineSummaryInfo" minOccurs="0"/>
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
         * {@link Summary.TradelineSummary.Bureau }
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
         *         &lt;element name="Info" type="{com/truelink/ds/sch/report/truelink/v5}TradelineSummaryInfo" minOccurs="0"/>
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
            protected TradelineSummaryInfo info;

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
             *     {@link TradelineSummaryInfo }
             *     
             */
            public TradelineSummaryInfo getInfo() {
                return info;
            }

            /**
             * Sets the value of the info property.
             * 
             * @param value
             *     allowed object is
             *     {@link TradelineSummaryInfo }
             *     
             */
            public void setInfo(TradelineSummaryInfo value) {
                this.info = value;
            }

        }

    }

}
