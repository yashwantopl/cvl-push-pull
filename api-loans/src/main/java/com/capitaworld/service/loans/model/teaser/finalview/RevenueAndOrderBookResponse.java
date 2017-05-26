package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 26-May-17.
 */
public class RevenueAndOrderBookResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String clientName;

    private String geography;

    private String ordersInHand;

    private String potentialOrders;

    private Double revenues;

    public RevenueAndOrderBookResponse(String clientName, String geography, String ordersInHand, String potentialOrders, Double revenues) {
        this.clientName = clientName;
        this.geography = geography;
        this.ordersInHand = ordersInHand;
        this.potentialOrders = potentialOrders;
        this.revenues = revenues;
    }

    public RevenueAndOrderBookResponse() {

    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getOrdersInHand() {
        return ordersInHand;
    }

    public void setOrdersInHand(String ordersInHand) {
        this.ordersInHand = ordersInHand;
    }

    public String getPotentialOrders() {
        return potentialOrders;
    }

    public void setPotentialOrders(String potentialOrders) {
        this.potentialOrders = potentialOrders;
    }

    public Double getRevenues() {
        return revenues;
    }

    public void setRevenues(Double revenues) {
        this.revenues = revenues;
    }
}
