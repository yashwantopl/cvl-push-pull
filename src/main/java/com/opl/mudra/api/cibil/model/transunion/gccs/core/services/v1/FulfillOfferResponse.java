
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.msp.FailureResponseType;
import com.opl.mudra.api.cibil.model.truelink.schema.msp.ServiceErrorType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{com/transunion/gccs/core/services/v1}BaseResponseType">
 *       &lt;sequence>
 *         &lt;element name="ServiceError" type="{com/truelink/schema/msp}ServiceErrorType" minOccurs="0"/>
 *         &lt;element name="Failure" type="{com/truelink/schema/msp}FailureResponseType" minOccurs="0"/>
 *         &lt;element ref="{com/transunion/gccs/core/services/v1}FulfillOfferSuccess" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "serviceError", "failure", "fulfillOfferSuccess" })
@XmlRootElement(name = "FulfillOfferResponse")
public class FulfillOfferResponse extends BaseResponseType {

	@XmlElement(name = "ServiceError",namespace="com/truelink/schema/msp")
	protected ServiceErrorType serviceError;
	@XmlElement(name = "Failure")
	protected FailureResponseType failure;
	@XmlElement(name = "FulfillOfferSuccess")
	protected FulfillOfferSuccess fulfillOfferSuccess;

	/**
	 * Gets the value of the serviceError property.
	 * 
	 * @return possible object is {@link ServiceErrorType }
	 * 
	 */
	public ServiceErrorType getServiceError() {
		return serviceError;
	}

	/**
	 * Sets the value of the serviceError property.
	 * 
	 * @param value
	 *            allowed object is {@link ServiceErrorType }
	 * 
	 */
	public void setServiceError(ServiceErrorType value) {
		this.serviceError = value;
	}

	/**
	 * Gets the value of the failure property.
	 * 
	 * @return possible object is {@link FailureResponseType }
	 * 
	 */
	public FailureResponseType getFailure() {
		return failure;
	}

	/**
	 * Sets the value of the failure property.
	 * 
	 * @param value
	 *            allowed object is {@link FailureResponseType }
	 * 
	 */
	public void setFailure(FailureResponseType value) {
		this.failure = value;
	}

	/**
	 * Gets the value of the fulfillOfferSuccess property.
	 * 
	 * @return possible object is {@link FulfillOfferSuccess }
	 * 
	 */
	public FulfillOfferSuccess getFulfillOfferSuccess() {
		return fulfillOfferSuccess;
	}

	/**
	 * Sets the value of the fulfillOfferSuccess property.
	 * 
	 * @param value
	 *            allowed object is {@link FulfillOfferSuccess }
	 * 
	 */
	public void setFulfillOfferSuccess(FulfillOfferSuccess value) {
		this.fulfillOfferSuccess = value;
	}

	@Override
	public String toString() {
		return "FulfillOfferResponse [serviceError=" + (serviceError != null ? serviceError.toString() : null)
				+ ", failure=" + (failure != null ? failure.toString() : null) + ", fulfillOfferSuccess="
				+ (fulfillOfferSuccess != null ? fulfillOfferSuccess.toString() : null) + ", responseStatus="
				+ responseStatus + "]";
	}

}
