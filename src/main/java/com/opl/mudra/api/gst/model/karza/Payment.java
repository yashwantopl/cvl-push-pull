package com.opl.mudra.api.gst.model.karza;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Payment {
	
	private Double total;
	
	@JsonProperty("tt_csh_pd")
	private Double ttCshPd;
	
	@JsonProperty("tt_itc_pd")
	private Double ttItcPd;
	
	@JsonProperty("tt_pay")
	private Double ttPay;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTtCshPd() {
		return ttCshPd;
	}

	public void setTtCshPd(Double ttCshPd) {
		this.ttCshPd = ttCshPd;
	}

	public Double getTtItcPd() {
		return ttItcPd;
	}

	public void setTtItcPd(Double ttItcPd) {
		this.ttItcPd = ttItcPd;
	}

	public Double getTtPay() {
		return ttPay;
	}

	public void setTtPay(Double ttPay) {
		this.ttPay = ttPay;
	}
	
	
	
}
