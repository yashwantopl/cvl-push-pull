package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PIIVerificationResponse {
	
	@JsonProperty("BureauResponse")
	private BureauResponse bureauResponse;
	
	@JsonProperty("UIDAI_AUA_Response")
	private AUAResponse auaResponse;
	
	@JsonProperty("NSDLResponse")
	private NSDLResponse nsdlResponse;
	
	@JsonProperty("ElectoralReponse")
	private ElectoralReponse electoralReponse;
	
	@JsonProperty("Decision")
	private Decision decision;

	
	public AUAResponse getAuaResponse() {
		return auaResponse;
	}

	public void setAuaResponse(AUAResponse auaResponse) {
		this.auaResponse = auaResponse;
	}

	public BureauResponse getBureauResponse() {
		return bureauResponse;
	}

	public void setBureauResponse(BureauResponse bureauResponse) {
		this.bureauResponse = bureauResponse;
	}


	public NSDLResponse getNsdlResponse() {
		return nsdlResponse;
	}

	public void setNsdlResponse(NSDLResponse nsdlResponse) {
		this.nsdlResponse = nsdlResponse;
	}

	public ElectoralReponse getElectoralReponse() {
		return electoralReponse;
	}

	public void setElectoralReponse(ElectoralReponse electoralReponse) {
		this.electoralReponse = electoralReponse;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	
	
	

}
