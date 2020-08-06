package com.opl.mudra.api.cibil_integration.msme.request.sbi;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestId",
    "errors"
})
@XmlRootElement(name = "BureauLinkSubmitResponse")
public class BureauLinkSubmitResponse {
	 private Integer requestId;
	 private List<Error> errors;
	 // Getter Methods 

	 public Integer getRequestId() {
	  return requestId;
	 }

	 public List<Error> getErrors() {
	  return errors;
	 }

	 // Setter Methods 

	 public void setRequestId(Integer requestId) {
	  this.requestId = requestId;
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
