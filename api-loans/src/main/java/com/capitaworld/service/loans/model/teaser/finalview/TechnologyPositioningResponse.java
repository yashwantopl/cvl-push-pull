package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 26-May-17.
 */
public class TechnologyPositioningResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String details;

    private String type;

    public TechnologyPositioningResponse(String details, String type) {
        this.details = details;
        this.type = type;
    }

    public TechnologyPositioningResponse() {
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
