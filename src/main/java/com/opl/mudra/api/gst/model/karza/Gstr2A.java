package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Gstr2A {

	@JsonProperty("details")
	private DetailsGstr2a[] detailsGstr2a;

	@JsonProperty("top3Sup")
	private Top3Sup[] top3sup;

	@JsonProperty("total")
	private Total total;

	public DetailsGstr2a[] getDetailsGstr2a() {
		return detailsGstr2a;
	}

	public void setDetailsGstr2a(DetailsGstr2a[] detailsGstr2a) {
		this.detailsGstr2a = detailsGstr2a;
	}

	public Top3Sup[] getTop3sup() {
		return top3sup;
	}

	public void setTop3sup(Top3Sup[] top3sup) {
		this.top3sup = top3sup;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Gstr2A [detailsGstr2a=" + Arrays.toString(detailsGstr2a) + ", top3sup=" + Arrays.toString(top3sup)
				+ ", total=" + total + "]";
	}

}
