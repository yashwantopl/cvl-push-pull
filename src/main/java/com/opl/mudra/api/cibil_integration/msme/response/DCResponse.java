//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.19 at 05:48:48 PM IST 
//


package com.opl.mudra.api.cibil_integration.msme.response;

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
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Authentication">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ResponseInfo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ApplicationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="SolutionSetInstanceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CurrentQueue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ContextData">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Field" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
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
    "status",
    "authentication",
    "responseInfo",
    "contextData",
    "errorInfo"
})
@XmlRootElement(name = "DCResponse")
public class DCResponse {

    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "Authentication", required = true)
    protected DCResponse.Authentication authentication;
    @XmlElement(name = "ResponseInfo", required = true)
    protected DCResponse.ResponseInfo responseInfo;
    @XmlElement(name = "ContextData", required = true)
    protected DCResponse.ContextData contextData;
    @XmlElement(name = "ErrorInfo", required = false)
    protected DCResponse.ErrorInfo errorInfo;

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the authentication property.
     *
     * @return
     *     possible object is
     *     {@link DCResponse.Authentication }
     *
     */
    public DCResponse.Authentication getAuthentication() {
        return authentication;
    }

    /**
     * Sets the value of the authentication property.
     *
     * @param value
     *     allowed object is
     *     {@link DCResponse.Authentication }
     *
     */
    public void setAuthentication(DCResponse.Authentication value) {
        this.authentication = value;
    }

    /**
     * Gets the value of the responseInfo property.
     *
     * @return
     *     possible object is
     *     {@link DCResponse.ResponseInfo }
     *
     */
    public DCResponse.ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    /**
     * Sets the value of the responseInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link DCResponse.ResponseInfo }
     *
     */
    public void setResponseInfo(DCResponse.ResponseInfo value) {
        this.responseInfo = value;
    }

    /**
     * Gets the value of the contextData property.
     *
     * @return
     *     possible object is
     *     {@link DCResponse.ContextData }
     *
     */
    public DCResponse.ContextData getContextData() {
        return contextData;
    }

    /**
     * Sets the value of the contextData property.
     *
     * @param value
     *     allowed object is
     *     {@link DCResponse.ContextData }
     *
     */
    public void setContextData(DCResponse.ContextData value) {
        this.contextData = value;
    }


    /**
     * Sets the value of the errorInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link DCResponse.ErrorInfo }
     *
     */
    public DCResponse.ErrorInfo getErrorInfo() {
		return errorInfo;
	}

    /**
     * Gets the value of the errorInfo property.
     *
     * @return
     *     possible object is
     *     {@link DCResponse.ErrorInfo }
     *
     */

	public void setErrorInfo(DCResponse.ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
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
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "status",
        "token"
    })
    public static class Authentication {

        @XmlElement(name = "Status", required = true)
        protected String status;
        @XmlElement(name = "Token", required = true)
        protected String token;

        /**
         * Gets the value of the status property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the token property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getToken() {
            return token;
        }

        /**
         * Sets the value of the token property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setToken(String value) {
            this.token = value;
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
     *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "message",
    })
    public static class ErrorInfo {

        @XmlElement(name = "Message", required = true)
        protected String message;

        /**
         * Gets the value of the status property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the value of the status property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMessage(String message) {
            this.message = message;
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
     *         &lt;element name="Field" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
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
        "field"
    })
    public static class ContextData {

        @XmlElement(name = "Field", required = true)
        protected List<Field> field;

        /**
         * Gets the value of the field property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the field property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getField().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DCResponse.ContextData.Field }
         *
         *
         */
        public List<Field> getField() {
            if (field == null) {
                field = new ArrayList<Field>();
            }
            return this.field;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Field {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "key", required = true)
            protected String key;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
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
     *         &lt;element name="ApplicationId" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="SolutionSetInstanceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CurrentQueue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "applicationId",
        "solutionSetInstanceId",
        "currentQueue"
    })
    public static class ResponseInfo {

        @XmlElement(name = "ApplicationId")
        @XmlSchemaType(name = "unsignedInt")
        protected Long applicationId;
        @XmlElement(name = "SolutionSetInstanceId", required = true)
        protected String solutionSetInstanceId;
        @XmlElement(name = "CurrentQueue", required = true)
        protected String currentQueue;

        /**
         * Gets the value of the applicationId property.
         * 
         */
        public long getApplicationId() {
            return applicationId;
        }

        /**
         * Sets the value of the applicationId property.
         * 
         */
        public void setApplicationId(Long value) {
            this.applicationId = value;
        }

        /**
         * Gets the value of the solutionSetInstanceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSolutionSetInstanceId() {
            return solutionSetInstanceId;
        }

        /**
         * Sets the value of the solutionSetInstanceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSolutionSetInstanceId(String value) {
            this.solutionSetInstanceId = value;
        }

        /**
         * Gets the value of the currentQueue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurrentQueue() {
            return currentQueue;
        }

        /**
         * Sets the value of the currentQueue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCurrentQueue(String value) {
            this.currentQueue = value;
        }

    }

}
