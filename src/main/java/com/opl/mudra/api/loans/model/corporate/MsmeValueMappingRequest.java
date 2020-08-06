package com.opl.mudra.api.loans.model.corporate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MsmeValueMappingRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fpProductId;
    private Long msmeFundingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public Long getMsmeFundingId() {
        return msmeFundingId;
    }

    public void setMsmeFundingId(Long msmeFundingId) {
        this.msmeFundingId = msmeFundingId;
    }
}
