
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceErrorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceErrorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorStatus" type="{com/truelink/schema/msp}ErrorStatusEnum"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MethodErrorLog" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ApplicationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MethodName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MethodArgument" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MethodResponse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PrimaryEntityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="PrimaryEntityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="SecondaryEntityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="SecondaryEntityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ServiceErrorType", propOrder = {
    "errorStatus",
    "errorMessage",
    "errorDetail",
    "methodErrorLog"
})
public class ServiceErrorType {

    @XmlElement(name = "ErrorStatus", required = true)
    @XmlSchemaType(name = "string")
    protected ErrorStatusEnum errorStatus;
    @XmlElement(name = "ErrorMessage", required = true)
    protected String errorMessage;
    @XmlElement(name = "ErrorDetail")
    protected String errorDetail;
    @XmlElement(name = "MethodErrorLog")
    protected ServiceErrorType.MethodErrorLog methodErrorLog;

    /**
     * Gets the value of the errorStatus property.
     *
     * @return
     *     possible object is
     *     {@link ErrorStatusEnum }
     *
     */
    public ErrorStatusEnum getErrorStatus() {
        return errorStatus;
    }

    /**
     * Sets the value of the errorStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link ErrorStatusEnum }
     *
     */
    public void setErrorStatus(ErrorStatusEnum value) {
        this.errorStatus = value;
    }

    /**
     * Gets the value of the errorMessage property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the errorDetail property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * Sets the value of the errorDetail property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setErrorDetail(String value) {
        this.errorDetail = value;
    }

    /**
     * Gets the value of the methodErrorLog property.
     *
     * @return
     *     possible object is
     *     {@link ServiceErrorType.MethodErrorLog }
     *
     */
    public ServiceErrorType.MethodErrorLog getMethodErrorLog() {
        return methodErrorLog;
    }

    /**
     * Sets the value of the methodErrorLog property.
     *
     * @param value
     *     allowed object is
     *     {@link ServiceErrorType.MethodErrorLog }
     *
     */
    public void setMethodErrorLog(ServiceErrorType.MethodErrorLog value) {
        this.methodErrorLog = value;
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
     *         &lt;element name="ApplicationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MethodName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MethodArgument" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MethodResponse" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PrimaryEntityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="PrimaryEntityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="SecondaryEntityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="SecondaryEntityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "applicationName",
        "methodName",
        "methodArgument",
        "methodResponse",
        "primaryEntityId",
        "primaryEntityName",
        "secondaryEntityId",
        "secondaryEntityName"
    })
    public static class MethodErrorLog {

        @XmlElement(name = "ApplicationName", required = true)
        protected String applicationName;
        @XmlElement(name = "MethodName", required = true)
        protected String methodName;
        @XmlElement(name = "MethodArgument", required = true)
        protected String methodArgument;
        @XmlElement(name = "MethodResponse", required = true)
        protected String methodResponse;
        @XmlElement(name = "PrimaryEntityId")
        protected String primaryEntityId;
        @XmlElement(name = "PrimaryEntityName")
        protected String primaryEntityName;
        @XmlElement(name = "SecondaryEntityId")
        protected String secondaryEntityId;
        @XmlElement(name = "SecondaryEntityName")
        protected String secondaryEntityName;

        /**
         * Gets the value of the applicationName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplicationName() {
            return applicationName;
        }

        /**
         * Sets the value of the applicationName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplicationName(String value) {
            this.applicationName = value;
        }

        /**
         * Gets the value of the methodName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMethodName() {
            return methodName;
        }

        /**
         * Sets the value of the methodName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMethodName(String value) {
            this.methodName = value;
        }

        /**
         * Gets the value of the methodArgument property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMethodArgument() {
            return methodArgument;
        }

        /**
         * Sets the value of the methodArgument property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMethodArgument(String value) {
            this.methodArgument = value;
        }

        /**
         * Gets the value of the methodResponse property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMethodResponse() {
            return methodResponse;
        }

        /**
         * Sets the value of the methodResponse property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMethodResponse(String value) {
            this.methodResponse = value;
        }

        /**
         * Gets the value of the primaryEntityId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimaryEntityId() {
            return primaryEntityId;
        }

        /**
         * Sets the value of the primaryEntityId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimaryEntityId(String value) {
            this.primaryEntityId = value;
        }

        /**
         * Gets the value of the primaryEntityName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimaryEntityName() {
            return primaryEntityName;
        }

        /**
         * Sets the value of the primaryEntityName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimaryEntityName(String value) {
            this.primaryEntityName = value;
        }

        /**
         * Gets the value of the secondaryEntityId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSecondaryEntityId() {
            return secondaryEntityId;
        }

        /**
         * Sets the value of the secondaryEntityId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSecondaryEntityId(String value) {
            this.secondaryEntityId = value;
        }

        /**
         * Gets the value of the secondaryEntityName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSecondaryEntityName() {
            return secondaryEntityName;
        }

        /**
         * Sets the value of the secondaryEntityName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSecondaryEntityName(String value) {
            this.secondaryEntityName = value;
        }

		@Override
		public String toString() {
			return "MethodErrorLog [applicationName=" + applicationName + ", methodName=" + methodName
					+ ", methodArgument=" + methodArgument + ", methodResponse=" + methodResponse + ", primaryEntityId="
					+ primaryEntityId + ", primaryEntityName=" + primaryEntityName + ", secondaryEntityId="
					+ secondaryEntityId + ", secondaryEntityName=" + secondaryEntityName + "]";
		}
        
    }


	@Override
	public String toString() {
		return "ServiceErrorType [errorStatus=" + errorStatus + ", errorMessage=" + errorMessage + ", errorDetail="
				+ errorDetail + ", methodErrorLog=" + methodErrorLog + "]";
	}
    

}
