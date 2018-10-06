package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by ravina.panchal on 04-10-2018.
 */
public class ObligationDetailRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Boolean isActive = true;

    private String obligationHead;

    private Double grossAmount;

    private Double netAmount;

    private Double periodicity;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getObligationHead() {
        return obligationHead;
    }

    public void setObligationHead(String obligationHead) {
        this.obligationHead = obligationHead;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Double periodicity) {
        this.periodicity = periodicity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static void printFields(Object obj) throws Exception {
        Field[] fields = ObligationDetailRequest.class.getDeclaredFields();

        for(Field field : fields) {
            Object value = field.get(obj);
            if(value instanceof String){
                String a = value.toString().replaceAll("&", "&amp;");
                value = a;
                field.set(obj, value);
            }
        }
    }
}
