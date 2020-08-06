
package com.opl.mudra.api.gst.model.apilist;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author vijay.chauhan
 *
 */


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Api  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("apiUrl")
    private String apiUrl;
    @JsonProperty("method")
    private String method;
    @JsonProperty("apiName")
    private String apiName;
    @JsonProperty("apiAction")
    private String apiAction;
    @JsonProperty("apiOrderNo")
    private Integer apiOrderNo;
    @JsonProperty("domainName")
    private String domainName;
    @JsonProperty("encryption")
    private String encryption;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("urlParameters")
    private String urlParameters;

    @JsonProperty("apiUrl")
    public String getApiUrl() {
        return apiUrl;
    }

    @JsonProperty("apiUrl")
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("apiName")
    public String getApiName() {
        return apiName;
    }

    @JsonProperty("apiName")
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @JsonProperty("apiAction")
    public String getApiAction() {
        return apiAction;
    }

    @JsonProperty("apiAction")
    public void setApiAction(String apiAction) {
        this.apiAction = apiAction;
    }

    @JsonProperty("apiOrderNo")
    public Integer getApiOrderNo() {
        return apiOrderNo;
    }

    @JsonProperty("apiOrderNo")
    public void setApiOrderNo(Integer apiOrderNo) {
        this.apiOrderNo = apiOrderNo;
    }

    @JsonProperty("domainName")
    public String getDomainName() {
        return domainName;
    }

    @JsonProperty("domainName")
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @JsonProperty("encryption")
    public String getEncryption() {
        return encryption;
    }

    @JsonProperty("encryption")
    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("urlParameters")
    public String getUrlParameters() {
        return urlParameters;
    }

    @JsonProperty("urlParameters")
    public void setUrlParameters(String urlParameters) {
        this.urlParameters = urlParameters;
    }


}
