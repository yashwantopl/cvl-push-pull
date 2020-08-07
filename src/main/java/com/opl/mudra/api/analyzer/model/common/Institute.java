package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Institution")
@XmlAccessorType(XmlAccessType.FIELD)
public class Institute {

	@XmlElement(name = "addressAvailable")
	private String addressAvailable;
	
	@XmlElement(name = "form26AsAvailable")
	private String form26AsAvailable;
	
	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "institutionType")
	private String institutionType;
	
	@XmlElement(name = "itrVAvailable")
	private String itrVAvailable;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "originalStatementAvailable")
	private String originalStatementAvailable;

	public String getAddressAvailable() {
		return addressAvailable;
	}

	public void setAddressAvailable(String addressAvailable) {
		this.addressAvailable = addressAvailable;
	}

	public String getForm26AsAvailable() {
		return form26AsAvailable;
	}

	public void setForm26AsAvailable(String form26AsAvailable) {
		this.form26AsAvailable = form26AsAvailable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public String getItrVAvailable() {
		return itrVAvailable;
	}

	public void setItrVAvailable(String itrVAvailable) {
		this.itrVAvailable = itrVAvailable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalStatementAvailable() {
		return originalStatementAvailable;
	}

	public void setOriginalStatementAvailable(String originalStatementAvailable) {
		this.originalStatementAvailable = originalStatementAvailable;
	}
	
	
	
}
