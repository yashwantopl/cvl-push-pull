package com.opl.mudra.api.cibil_integration.msme.response.sbi.individual;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.msme.response.DCResponseSBI;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestId", "applicationName", "requestedDate", "issuedDate","bureaus","errors" })
@XmlRootElement(name = "BureauLinkResponse")
public class BureauLinkResponse {
	private String requestId;
	private String applicationName;
	private String requestedDate;
	private String issuedDate;
	@XmlElement(name = "bureau", required = true)
	private List<Bureau> bureaus;
	@XmlElement(name = "errors", required = true)
	private List<Error> errors;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public List<Bureau> getBureaus() {
		return bureaus;
	}

	public void setBureaus(List<Bureau> bureaus) {
		this.bureaus = bureaus;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}


	@XmlAccessorType(XmlAccessType.FIELD)
	 @XmlType(name = "", propOrder = {
	            "errorCode",
	            "errorMessage"
	        })
	 public static class Error {
			
		@XmlElement(required = true)
		private String errorCode;
		@XmlElement(required = true)
		private String errorMessage;

		 // Getter Methods 

		 public Error(){
			 
		 }
		 public String getErrorCode() {
		  return errorCode;
		 }

		 public String getErrorMessage() {
		  return errorMessage;
		 }

		 // Setter Methods 

		 public void setErrorCode(String errorCode) {
		  this.errorCode = errorCode;
		 }

		 public void setErrorMessage(String errorMessage) {
		  this.errorMessage = errorMessage;
		 }
	 }


	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "dCResponse","name" })
	public static class Bureau {

//		@XmlValue
		@XmlElement(name = "DCResponse")
		protected DCResponseSBI dCResponse;
		
		@XmlAttribute(name = "name", required = true)
		protected String name;

		/**
		 * Gets the value of the value property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public DCResponseSBI getdCResponse() {
			return dCResponse;
		}

		public void setdCResponse(DCResponseSBI dCResponse) {
			this.dCResponse = dCResponse;
		}
	
	}
}
