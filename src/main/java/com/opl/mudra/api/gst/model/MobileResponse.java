
package com.opl.mudra.api.gst.model;

/**
 * @author harshit 
 * Date : 15-May-2018
 */

public enum MobileResponse {


    ERROR400(400,"Auth token is missing"),
    ERROR401(401,"Invalid auth token"),
    ERROR402(402,"Auth token Expire"),
    ERROR403(403,"Invalid request"),
    ERROR404(404,"Invalid parameter"),
    ERROR405(405,"The user is Inactive from system"),
    ERROR406(406,"Invalid Login"),
    ERROR407(407,"Other Error"),
    SUCCESS200(200,"Success"),
    SUCCESS204(204,"No data Found"),
    INTERNALSERVERERROR407(407,"Other Error");

    private final Integer statusCode;
    private final String description;

    private MobileResponse(Integer statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }


}
