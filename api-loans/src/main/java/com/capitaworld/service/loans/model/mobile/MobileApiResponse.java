package com.capitaworld.service.loans.model.mobile;
import java.io.Serializable;

public class MobileApiResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String success;
    private Object data;
    private Long response_code;
    private String response_code_message;
    private String message;


    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Long getResponse_code() {
        return response_code;
    }
    public void setResponse_code(Long response_code) {
        this.response_code = response_code;
    }
    public String getResponse_code_message() {
        return response_code_message;
    }
    public void setResponse_code_message(String response_code_message) {
        this.response_code_message = response_code_message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public MobileApiResponse(String success, Object data, Long response_code, String response_code_message, String message) {
        super();
        this.success = success;
        this.data = data;
        this.response_code = response_code;
        this.message = message;
        this.response_code_message = response_code_message;
    }
    public MobileApiResponse(String success, Long response_code, String response_code_message, String message) {
        super();
        this.success = success;
        this.response_code = response_code;
        this.response_code_message = response_code_message;
        this.message = message;
    }

    public MobileApiResponse() {
        super();
    }





}