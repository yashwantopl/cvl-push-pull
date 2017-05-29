package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

import com.capitaworld.service.loans.model.teaser.primaryview.PersonalLoandsPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;

public class PersonalLoanFinalViewResponse  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RetailPrimaryViewResponse personalLoansPrimaryViewResponse;
	private RetailFinalViewResponse finalViewResponse;
	
	
	public RetailPrimaryViewResponse getPersonalLoansPrimaryViewResponse() {
		return personalLoansPrimaryViewResponse;
	}
	public void setPersonalLoansPrimaryViewResponse(RetailPrimaryViewResponse personalLoansPrimaryViewResponse) {
		this.personalLoansPrimaryViewResponse = personalLoansPrimaryViewResponse;
	}
	public RetailFinalViewResponse getFinalViewResponse() {
		return finalViewResponse;
	}
	public void setFinalViewResponse(RetailFinalViewResponse finalViewResponse) {
		this.finalViewResponse = finalViewResponse;
	}

	
}
