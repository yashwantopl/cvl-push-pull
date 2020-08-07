package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DocumentDetails {
	
	@JsonProperty("doc_url")
	private String docUrl;
	
	private String frmdc;
	
	private String frmno;
	
    private String isdt;

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public String getFrmdc() {
		return frmdc;
	}

	public void setFrmdc(String frmdc) {
		this.frmdc = frmdc;
	}

	public String getFrmno() {
		return frmno;
	}

	public void setFrmno(String frmno) {
		this.frmno = frmno;
	}

	public String getIsdt() {
		return isdt;
	}

	public void setIsdt(String isdt) {
		this.isdt = isdt;
	}
    
    

}
