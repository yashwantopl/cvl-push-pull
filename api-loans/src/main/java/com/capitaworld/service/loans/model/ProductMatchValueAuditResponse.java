package com.capitaworld.service.loans.model;

public class ProductMatchValueAuditResponse {


    private Long applicationId;

    private Long productId;

    private Long parameterId;

    private Long bodmasFormulaId;

    private Double formulaAnswer;

    private Double maxVal;

    private Double minVal;

    private Double compareVal;

    private String condition;

    private Boolean isMatch;


    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public Long getBodmasFormulaId() {
        return bodmasFormulaId;
    }

    public void setBodmasFormulaId(Long bodmasFormulaId) {
        this.bodmasFormulaId = bodmasFormulaId;
    }

    public Double getFormulaAnswer() {
        return formulaAnswer;
    }

    public void setFormulaAnswer(Double formulaAnswer) {
        this.formulaAnswer = formulaAnswer;
    }

    public Double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Double maxVal) {
        this.maxVal = maxVal;
    }

    public Double getMinVal() {
        return minVal;
    }

    public void setMinVal(Double minVal) {
        this.minVal = minVal;
    }

    public Double getCompareVal() {
        return compareVal;
    }

    public void setCompareVal(Double compareVal) {
        this.compareVal = compareVal;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(Boolean isMatch) {
        this.isMatch = isMatch;
    }


}
