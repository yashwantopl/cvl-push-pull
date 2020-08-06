package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Profile {
	
	private String cmpRt;
	
	private String ctb;
	
	private String lgnm;
	
	private String rgdt;
	
	private String sts;
	
	private String tradeNam;
	
	private Contacted contacted;

	private String[] nba;

	private String[] mbr;

	@JsonProperty("document_detail")
	private DocumentDetails[] documentDetails;




	public DocumentDetails[] getDocumentDetails() {
		return documentDetails;
	}

	public void setDocumentDetails(DocumentDetails[] documentDetails) {
		this.documentDetails = documentDetails;
	}

	public String[] getNba() {
		return nba;
	}

	public void setNba(String[] nba) {
		this.nba = nba;
	}

	public String[] getMbr() {
		return mbr;
	}

	public void setMbr(String[] mbr) {
		this.mbr = mbr;
	}

	public Contacted getContacted() {
		return contacted;
	}

	public void setContacted(Contacted contacted) {
		this.contacted = contacted;
	}

	public String getCmpRt() {
		return cmpRt;
	}

	public void setCmpRt(String cmpRt) {
		this.cmpRt = cmpRt;
	}

	public String getCtb() {
		return ctb;
	}

	public void setCtb(String ctb) {
		this.ctb = ctb;
	}

	public String getLgnm() {
		return lgnm;
	}

	public void setLgnm(String lgnm) {
		this.lgnm = lgnm;
	}

	public String getRgdt() {
		return rgdt;
	}

	public void setRgdt(String rgdt) {
		this.rgdt = rgdt;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getTradeNam() {
		return tradeNam;
	}

	public void setTradeNam(String tradeNam) {
		this.tradeNam = tradeNam;
	}
	
	

}
