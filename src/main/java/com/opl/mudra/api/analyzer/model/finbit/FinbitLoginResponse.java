package com.opl.mudra.api.analyzer.model.finbit;

/**
 * Created by ravina.panchal on 12-09-2018.
 */
public class FinbitLoginResponse {

    private String status;
    private String access_token;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
