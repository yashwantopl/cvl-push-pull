package com.opl.mudra.api.dms.model;

/**
 * Created by dhaval on 29-Apr-17.
 */
public class ProductDocumentResponse {

    private Long id;
    private String documentName;
    private String documentType;
    private boolean documentMandatory;
    private Integer limit;

    public ProductDocumentResponse(Long id, String documentName, String documentType, boolean documentMandatory, Integer limit) {
        this.id = id;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentMandatory = documentMandatory;
        this.limit = limit;
    }

    public ProductDocumentResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public boolean isDocumentMandatory() {
        return documentMandatory;
    }

    public void setDocumentMandatory(boolean documentMandatory) {
        this.documentMandatory = documentMandatory;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ProductDocumentResponse{" +
                "id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentMandatory=" + documentMandatory +
                ", limit=" + limit +
                '}';
    }
}
