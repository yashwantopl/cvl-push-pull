package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DetailsGstr2a {
	
	@JsonProperty("ret_period")
	private String retPeriod;
	
	@JsonProperty("sec_sum")
	private SecSumGstr2a[] secSum;

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public SecSumGstr2a[] getSecSum() {
		return secSum;
	}

	public void setSecSum(SecSumGstr2a[] secSum) {
		this.secSum = secSum;
	}

	@Override
	public String toString() {
		return "DetailsGstr2a [retPeriod=" + retPeriod + ", secSum=" + Arrays.toString(secSum) + "]";
	}
	
	

}
