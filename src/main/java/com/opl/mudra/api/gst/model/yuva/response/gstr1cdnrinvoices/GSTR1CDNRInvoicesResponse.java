
package com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.gst.model.yuva.response.gstr1summary.GSTDataResponse;

/**
 * 
 * @author vijay.chauhan
 *
 */


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GSTR1CDNRInvoicesResponse  extends GSTDataResponse {

	
	@JsonProperty("cdnr")
    private Cdnr[] cdnr;
    
    @JsonProperty("cdnr")
	public Cdnr[] getCdnr() {
		return cdnr;
	}

    @JsonProperty("cdnr")
	public void setCdnr(Cdnr[] cdnr) {
		this.cdnr = cdnr;
	}
    
}
