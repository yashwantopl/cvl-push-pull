package com.opl.mudra.api.cibil.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilMSMERequest extends CibilRequest implements Serializable {

	private static final long serialVersionUID = 5320677886683244080L;

	private List<CibilMSMEIndividualRequest> individualRequests;
	private CibilMSMEApplicationDataRequest applicationDataRequest;

	public CibilMSMERequest() {
		super();
	}

	public List<CibilMSMEIndividualRequest> getIndividualRequests() {
		return individualRequests;
	}

	public void setIndividualRequests(List<CibilMSMEIndividualRequest> individualRequests) {
		this.individualRequests = individualRequests;
	}

	public CibilMSMEApplicationDataRequest getApplicationDataRequest() {
		return applicationDataRequest;
	}

	public void setApplicationDataRequest(CibilMSMEApplicationDataRequest applicationDataRequest) {
		this.applicationDataRequest = applicationDataRequest;
	}

}
