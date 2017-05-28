package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 25-May-17.
 */
public class EmployeesCategoryBreaksResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String employment;

    private String employmentStatusFuture;

    private String employmentStatusPresent;

    public EmployeesCategoryBreaksResponse(String employment, String employmentStatusFuture, String employmentStatusPresent) {
        this.employment = employment;
        this.employmentStatusFuture = employmentStatusFuture;
        this.employmentStatusPresent = employmentStatusPresent;
    }

    public EmployeesCategoryBreaksResponse() {
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getEmploymentStatusFuture() {
        return employmentStatusFuture;
    }

    public void setEmploymentStatusFuture(String employmentStatusFuture) {
        this.employmentStatusFuture = employmentStatusFuture;
    }

    public String getEmploymentStatusPresent() {
        return employmentStatusPresent;
    }

    public void setEmploymentStatusPresent(String employmentStatusPresent) {
        this.employmentStatusPresent = employmentStatusPresent;
    }
}
