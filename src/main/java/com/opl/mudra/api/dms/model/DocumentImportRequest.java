package com.opl.mudra.api.dms.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DocumentImportRequest implements Serializable {
    private Long toApplicationId;
    private Long storageId;

    public Long getToApplicationId() {
        return toApplicationId;
    }

    public void setToApplicationId(Long toApplicationId) {
        this.toApplicationId = toApplicationId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "DocumentImportRequest{" +
                "toApplicationId=" + toApplicationId +
                ", storageId=" + storageId +
                '}';
    }
}
