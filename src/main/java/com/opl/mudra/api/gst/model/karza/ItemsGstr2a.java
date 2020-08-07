package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ItemsGstr2a {

	@JsonProperty("num")
	private Double num;
	
	@JsonProperty("itm_det")
	private ItemDetGstr2a itmDet;

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public ItemDetGstr2a getItmDet() {
		return itmDet;
	}

	public void setItmDet(ItemDetGstr2a itmDet) {
		this.itmDet = itmDet;
	}

	@Override
	public String toString() {
		return "ItemsGstr2a [num=" + num + ", itmDet=" + itmDet+ "]";
	}
	
	
	
}
