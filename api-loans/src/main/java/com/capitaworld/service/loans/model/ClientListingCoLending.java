package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ClientListingCoLending implements Serializable {

    private Long clientId;
    private String clientEmail;
    private String originalEmailId;
    private String clientName;
    private String clientCountry;
    private String clientState;
    private String clientCity;
    private String clientImagePath;
    private Long lastAccessId;
    private String clientMobile;
    private String clientStatus;
    private String pan;
    private List<?> listData = Collections.emptyList();

    //constructor
    public ClientListingCoLending() {

    }

    //getter setter
    public Long getLastAccessId() {
        return lastAccessId;
    }

    public void setLastAccessId(Long lastAccessId) {
        this.lastAccessId = lastAccessId;
    }

    public String getClientImagePath() {
        return clientImagePath;
    }

    public void setClientImagePath(String clientImagePath) {
        this.clientImagePath = clientImagePath;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

    public List<?> getListData() {
        return listData;
    }

    public void setListData(List<?> listData) {
        this.listData = listData;
    }

    public String getOriginalEmailId() {
        return originalEmailId;
    }

    public void setOriginalEmailId(String originalEmailId) {
        this.originalEmailId = originalEmailId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
