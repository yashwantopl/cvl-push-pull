package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
	@XmlElement(name = "Part")
	private Part part;
	
	@XmlAttribute(name = "files")
	private String files;
	
	@XmlAttribute(name = "parts")
	private String parts;
	
	@XmlAttribute(name = "processing")
	private String processing;
	
	@XmlAttribute(name = "txnId")
	private String txnId;

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	
	
}
