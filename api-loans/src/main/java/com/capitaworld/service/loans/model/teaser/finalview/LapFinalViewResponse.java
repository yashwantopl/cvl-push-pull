package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

import com.capitaworld.service.loans.model.teaser.primaryview.LapPrimaryViewResponse;

public class LapFinalViewResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LapPrimaryViewResponse lapPrimaryViewResponse;
	private RetailFinalViewResponse finalViewResponse;

	public LapPrimaryViewResponse getLapPrimaryViewResponse() {
		return lapPrimaryViewResponse;
	}

	public void setLapPrimaryViewResponse(LapPrimaryViewResponse lapPrimaryViewResponse) {
		this.lapPrimaryViewResponse = lapPrimaryViewResponse;
	}

	public RetailFinalViewResponse getFinalViewResponse() {
		return finalViewResponse;
	}

	public void setFinalViewResponse(RetailFinalViewResponse finalViewResponse) {
		this.finalViewResponse = finalViewResponse;
	}
	
	
}
