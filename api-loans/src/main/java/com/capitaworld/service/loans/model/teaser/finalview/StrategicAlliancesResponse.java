package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 25-May-17.
 */
public class StrategicAlliancesResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String keyAlliancePartners;

    private String name;

    private String relationshipDetails;


        public StrategicAlliancesResponse(String keyAlliancePartners, String name, String relationshipDetails) {
        this.keyAlliancePartners = keyAlliancePartners;
        this.name = name;
        this.relationshipDetails = relationshipDetails;
    }

    public StrategicAlliancesResponse() {

    }

    public String getKeyAlliancePartners() {
        return keyAlliancePartners;
    }

    public void setKeyAlliancePartners(String keyAlliancePartners) {
        this.keyAlliancePartners = keyAlliancePartners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationshipDetails() {
        return relationshipDetails;
    }

    public void setRelationshipDetails(String relationshipDetails) {
        this.relationshipDetails = relationshipDetails;
    }
}
