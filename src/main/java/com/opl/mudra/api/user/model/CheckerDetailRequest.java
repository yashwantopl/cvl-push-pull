package com.opl.mudra.api.user.model;

public class CheckerDetailRequest {

    private Long userId;

    private Long productId;

    private Double minAmount;

    private Double maxAmount;

    private String firstName;

    private String middleName;

    private String lastName;

    private String checkerDisplayName;

    public String getCheckerDisplayName() {
        return checkerDisplayName;
    }

    public void setCheckerDisplayName(String checkerDisplayName) {
        this.checkerDisplayName = checkerDisplayName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
