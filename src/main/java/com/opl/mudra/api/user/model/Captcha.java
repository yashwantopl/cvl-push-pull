package com.opl.mudra.api.user.model;

import java.io.Serializable;

/**
 * Created by dhaval on 03-May-17.
 */
public class Captcha implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String captchaString;
    private byte[] bytes;

    public String getCaptchaString() {
        return captchaString;
    }

    public void setCaptchaString(String captchaString) {
        this.captchaString = captchaString;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
