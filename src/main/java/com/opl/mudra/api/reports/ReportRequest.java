package com.opl.mudra.api.reports;

import java.util.Map;

public class ReportRequest {

	Map<String, Object> params;
	String template;
	String type;
//	Used for JasperReport API
	Long templateId;
	Object data;
	Boolean isStaticContent;
	String documentName;

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getIsStaticContent() {
		return isStaticContent;
	}

	public void setIsStaticContent(Boolean isStaticContent) {
		this.isStaticContent = isStaticContent;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	

}
