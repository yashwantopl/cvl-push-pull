
package com.opl.mudra.api.gst.model.yuva.response.gstr1cdnurinvoices;

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
public class GSTR1CDNURInvoicesResponse  extends GSTDataResponse {

    @JsonProperty("cdnur")
    private Cdnur[] cdnur;
    
    @JsonProperty("cdnur")
    public Cdnur[] getCdnur() {
        return cdnur;
    }

    @JsonProperty("cdnur")
    public void setCdnur(Cdnur[] cdnur) {
        this.cdnur = cdnur;
    }

}
