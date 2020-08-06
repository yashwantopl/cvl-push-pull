package com.opl.mudra.api.cibil_integration.msme.request.sbi;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "response",
    "errors"
})
@XmlRootElement(name = "BureauLinkReportResponse")
public class BureauLinkReportResponse {
	 private String response;
	 private List<Error> errors;
	 // Getter Methods 

	 public List<Error> getErrors() {
	  return errors;
	 }

	 // Setter Methods 
	 public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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
}
