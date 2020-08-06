package com.opl.mudra.api.cibil_integration.msme.response.sbi.comercial;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.msme.Base;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestId",
    "applicationName",
    "requestedDate",
    "issuedDate",
    "bureau"
})
@XmlRootElement(name = "BureauLinkCommercialResponse")
public class BureauLinkCommercialResponse {

    protected short requestId;
    @XmlElement(required = true)
    protected String applicationName;
    @XmlElement(required = true)
    protected String requestedDate;
    @XmlElement(required = true)
    protected String issuedDate;
    protected List<Bureau> bureau;

    public short getRequestId() {
        return requestId;
    }

    public void setRequestId(short value) {
        this.requestId = value;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String value) {
        this.requestedDate = value;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String value) {
        this.issuedDate = value;
    }

    public List<Bureau> getBureau() {
        if (bureau == null) {
            bureau = new ArrayList<Bureau>();
        }
        return this.bureau;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "base"
    })
    public static class Bureau {

        protected Base base;
        @XmlAttribute(name = "name")
        protected String name;
        
        public Base getBase() {
            return base;
        }

        public void setBase(Base value) {
            this.base = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }
    }
	
}
