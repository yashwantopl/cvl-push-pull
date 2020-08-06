//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.30 at 11:50:45 AM IST 
//


package com.opl.mudra.api.cibil.model.highmark.commercial.request.issue;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



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
 *         &lt;element name="HEADER-SEGMENT">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PRODUCT-TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PRODUCT-VER" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="REQ-MBR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SUB-MBR-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="INQ-DT-TM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REQ-VOL-TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REQ-ACTN-TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TEST-FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="USER-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PWD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUTH-FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUTH-TITLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RES-FRMT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MEMBER-PRE-OVERRIDE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RES-FRMT-EMBD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOS-NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOS-VENDER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOS-VERSION" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="INQUIRY">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="INQUIRY-UNIQUE-REF-NO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="REQUEST-DT-TM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REPORT-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "headersegment",
    "inquiry"
})
@XmlRootElement(name = "REQUEST-REQUEST-FILE")
public class REQUESTREQUESTFILE {

    @XmlElement(name = "HEADER-SEGMENT", required = true)
    protected HEADERSEGMENT headersegment;
    @XmlElement(name = "INQUIRY", required = true)
    protected INQUIRY inquiry;

    /**
     * Gets the value of the headersegment property.
     * 
     * @return
     *     possible object is
     *     {@link HEADERSEGMENT }
     *     
     */
    public HEADERSEGMENT getHEADERSEGMENT() {
        return headersegment;
    }

    /**
     * Sets the value of the headersegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link HEADERSEGMENT }
     *     
     */
    public void setHEADERSEGMENT(HEADERSEGMENT value) {
        this.headersegment = value;
    }

    /**
     * Gets the value of the inquiry property.
     * 
     * @return
     *     possible object is
     *     {@link INQUIRY }
     *     
     */
    public INQUIRY getINQUIRY() {
        return inquiry;
    }

    /**
     * Sets the value of the inquiry property.
     * 
     * @param value
     *     allowed object is
     *     {@link INQUIRY }
     *     
     */
    public void setINQUIRY(INQUIRY value) {
        this.inquiry = value;
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
     *         &lt;element name="PRODUCT-TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PRODUCT-VER" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="REQ-MBR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SUB-MBR-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="INQ-DT-TM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="REQ-VOL-TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="REQ-ACTN-TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TEST-FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="USER-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PWD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AUTH-FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AUTH-TITLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RES-FRMT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MEMBER-PRE-OVERRIDE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RES-FRMT-EMBD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOS-NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOS-VENDER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOS-VERSION" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
        "producttyp",
        "productver",
        "reqmbr",
        "submbrid",
        "inqdttm",
        "reqvoltyp",
        "reqactntyp",
        "testflg",
        "userid",
        "pwd",
        "authflg",
        "authtitle",
        "resfrmt",
        "memberpreoverride",
        "resfrmtembd",
        "losname",
        "losvender",
        "losversion",
        "commercial",
        "consumer"
    })
    public static class HEADERSEGMENT {

        @XmlElement(name = "PRODUCT-TYP", required = true)
        protected String producttyp;
        @XmlElement(name = "PRODUCT-VER")
        protected String productver;
        @XmlElement(name = "REQ-MBR", required = true)
        protected String reqmbr;
        @XmlElement(name = "SUB-MBR-ID", required = true)
        protected String submbrid;
        @XmlElement(name = "INQ-DT-TM", required = true)
        protected String inqdttm;
        @XmlElement(name = "REQ-VOL-TYP", required = true)
        protected String reqvoltyp;
        @XmlElement(name = "REQ-ACTN-TYP", required = true)
        protected String reqactntyp;
        @XmlElement(name = "TEST-FLG", required = true)
        protected String testflg;
        @XmlElement(name = "USER-ID", required = true)
        protected String userid;
        @XmlElement(name = "PWD", required = true)
        protected String pwd;
        @XmlElement(name = "AUTH-FLG", required = true)
        protected String authflg;
        @XmlElement(name = "AUTH-TITLE", required = true)
        protected String authtitle;
        @XmlElement(name = "RES-FRMT", required = true)
        protected String resfrmt;
        @XmlElement(name = "MEMBER-PRE-OVERRIDE", required = true)
        protected String memberpreoverride;
        @XmlElement(name = "RES-FRMT-EMBD", required = true)
        protected String resfrmtembd;
        @XmlElement(name = "LOS-NAME", required = true)
        protected String losname;
        @XmlElement(name = "LOS-VENDER", required = true)
        protected String losvender;
        @XmlElement(name = "LOS-VERSION")
        protected String losversion;
        @XmlElement(name = "COMMERCIAL", required = true)
        protected COMMERCIAL commercial;
        @XmlElement(name = "CONSUMER", required = true)
        protected CONSUMER consumer;

        /**
         * Gets the value of the producttyp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPRODUCTTYP() {
            return producttyp;
        }

        /**
         * Sets the value of the producttyp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPRODUCTTYP(String value) {
            this.producttyp = value;
        }

        /**
         * Gets the value of the productver property.
         * 
         */
        public String getPRODUCTVER() {
            return productver;
        }

        /**
         * Sets the value of the productver property.
         * 
         */
        public void setPRODUCTVER(String value) {
            this.productver = value;
        }

        /**
         * Gets the value of the reqmbr property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREQMBR() {
            return reqmbr;
        }

        /**
         * Sets the value of the reqmbr property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREQMBR(String value) {
            this.reqmbr = value;
        }

        /**
         * Gets the value of the submbrid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBMBRID() {
            return submbrid;
        }

        /**
         * Sets the value of the submbrid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBMBRID(String value) {
            this.submbrid = value;
        }

        /**
         * Gets the value of the inqdttm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getINQDTTM() {
            return inqdttm;
        }

        /**
         * Sets the value of the inqdttm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setINQDTTM(String value) {
            this.inqdttm = value;
        }

        /**
         * Gets the value of the reqvoltyp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREQVOLTYP() {
            return reqvoltyp;
        }

        /**
         * Sets the value of the reqvoltyp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREQVOLTYP(String value) {
            this.reqvoltyp = value;
        }

        /**
         * Gets the value of the reqactntyp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREQACTNTYP() {
            return reqactntyp;
        }

        /**
         * Sets the value of the reqactntyp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREQACTNTYP(String value) {
            this.reqactntyp = value;
        }

        /**
         * Gets the value of the testflg property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTESTFLG() {
            return testflg;
        }

        /**
         * Sets the value of the testflg property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTESTFLG(String value) {
            this.testflg = value;
        }

        /**
         * Gets the value of the userid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUSERID() {
            return userid;
        }

        /**
         * Sets the value of the userid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUSERID(String value) {
            this.userid = value;
        }

        /**
         * Gets the value of the pwd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPWD() {
            return pwd;
        }

        /**
         * Sets the value of the pwd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPWD(String value) {
            this.pwd = value;
        }

        /**
         * Gets the value of the authflg property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAUTHFLG() {
            return authflg;
        }

        /**
         * Sets the value of the authflg property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAUTHFLG(String value) {
            this.authflg = value;
        }

        /**
         * Gets the value of the authtitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAUTHTITLE() {
            return authtitle;
        }

        /**
         * Sets the value of the authtitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAUTHTITLE(String value) {
            this.authtitle = value;
        }

        /**
         * Gets the value of the resfrmt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRESFRMT() {
            return resfrmt;
        }

        /**
         * Sets the value of the resfrmt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRESFRMT(String value) {
            this.resfrmt = value;
        }

        /**
         * Gets the value of the memberpreoverride property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMEMBERPREOVERRIDE() {
            return memberpreoverride;
        }

        /**
         * Sets the value of the memberpreoverride property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMEMBERPREOVERRIDE(String value) {
            this.memberpreoverride = value;
        }

        /**
         * Gets the value of the resfrmtembd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRESFRMTEMBD() {
            return resfrmtembd;
        }

        /**
         * Sets the value of the resfrmtembd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRESFRMTEMBD(String value) {
            this.resfrmtembd = value;
        }

        /**
         * Gets the value of the losname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLOSNAME() {
            return losname;
        }

        /**
         * Sets the value of the losname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLOSNAME(String value) {
            this.losname = value;
        }

        /**
         * Gets the value of the losvender property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLOSVENDER() {
            return losvender;
        }

        /**
         * Sets the value of the losvender property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLOSVENDER(String value) {
            this.losvender = value;
        }

        /**
         * Gets the value of the losversion property.
         * 
         */
        public String getLOSVERSION() {
            return losversion;
        }

        /**
         * Sets the value of the losversion property.
         * 
         */
        public void setLOSVERSION(String value) {
            this.losversion = value;
        }
        
        
        
        /**
         * Gets the value of the commercial property.
         * 
         * @return
         *     possible object is
         *     {@link COMMERCIAL }
         *     
         */
        public COMMERCIAL getCOMMERCIAL() {
            return commercial;
        }

        /**
         * Sets the value of the commercial property.
         * 
         * @param value
         *     allowed object is
         *     {@link COMMERCIAL }
         *     
         */
        public void setCOMMERCIAL(COMMERCIAL value) {
            this.commercial = value;
        }

        /**
         * Gets the value of the consumer property.
         * 
         * @return
         *     possible object is
         *     {@link CONSUMER }
         *     
         */
        public CONSUMER getCONSUMER() {
            return consumer;
        }

        /**
         * Sets the value of the consumer property.
         * 
         * @param value
         *     allowed object is
         *     {@link CONSUMER }
         *     
         */
        public void setCONSUMER(CONSUMER value) {
            this.consumer = value;
        }
        
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "cir",
            "score"
        })
        public static class COMMERCIAL {

            @XmlElement(name = "CIR", required = true)
            protected String cir;
            @XmlElement(name = "SCORE")
            protected String score;

            /**
             * Gets the value of the cir property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCIR() {
                return cir;
            }

            /**
             * Sets the value of the cir property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCIR(String value) {
                this.cir = value;
            }

            /**
             * Gets the value of the score property.
             * 
             */
            public String getSCORE() {
                return score;
            }

            /**
             * Sets the value of the score property.
             * 
             */
            public void setSCORE(String value) {
                this.score = value;
            }

        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "cir",
            "score"
        })
        public static class CONSUMER {

            @XmlElement(name = "CIR", required = true)
            protected String cir;
            @XmlElement(name = "SCORE")
            protected String score;

            /**
             * Gets the value of the cir property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCIR() {
                return cir;
            }

            /**
             * Sets the value of the cir property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCIR(String value) {
                this.cir = value;
            }

            /**
             * Gets the value of the score property.
             * 
             */
            public String getSCORE() {
                return score;
            }

            /**
             * Sets the value of the score property.
             * 
             */
            public void setSCORE(String value) {
                this.score = value;
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
     *         &lt;element name="INQUIRY-UNIQUE-REF-NO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="REQUEST-DT-TM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="REPORT-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "inquiryuniquerefno",
        "requestdttm",
        "reportid"
    })
    public static class INQUIRY {

        @XmlElement(name = "INQUIRY-UNIQUE-REF-NO", required = true)
        protected String inquiryuniquerefno;
        @XmlElement(name = "REQUEST-DT-TM", required = true)
        protected String requestdttm;
        @XmlElement(name = "REPORT-ID", required = true)
        protected String reportid;

        /**
         * Gets the value of the inquiryuniquerefno property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public String getINQUIRYUNIQUEREFNO() {
            return inquiryuniquerefno;
        }

        /**
         * Sets the value of the inquiryuniquerefno property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setINQUIRYUNIQUEREFNO(String value) {
            this.inquiryuniquerefno = value;
        }

        /**
         * Gets the value of the requestdttm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREQUESTDTTM() {
            return requestdttm;
        }

        /**
         * Sets the value of the requestdttm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREQUESTDTTM(String value) {
            this.requestdttm = value;
        }

        /**
         * Gets the value of the reportid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREPORTID() {
            return reportid;
        }

        /**
         * Sets the value of the reportid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREPORTID(String value) {
            this.reportid = value;
        }

    }

}
