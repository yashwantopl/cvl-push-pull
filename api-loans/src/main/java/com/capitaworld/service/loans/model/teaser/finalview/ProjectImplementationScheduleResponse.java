package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 26-May-17.
 */
public class ProjectImplementationScheduleResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String activities;

    private String commencementDate;

    private String completionDate;

    private String timelineTotal;

    public ProjectImplementationScheduleResponse(String activities, String commencementDate, String completionDate, String timelineTotal) {
        this.activities = activities;
        this.commencementDate = commencementDate;
        this.completionDate = completionDate;
        this.timelineTotal = timelineTotal;
    }

    public ProjectImplementationScheduleResponse() {

    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(String commencementDate) {
        this.commencementDate = commencementDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getTimelineTotal() {
        return timelineTotal;
    }

    public void setTimelineTotal(String timelineTotal) {
        this.timelineTotal = timelineTotal;
    }
}
