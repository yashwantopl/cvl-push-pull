package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnershipDetailResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String remarks;

    private String shareHoldingCategory;

    private Double stackPercentage;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getShareHoldingCategory() {
        return shareHoldingCategory;
    }

    public void setShareHoldingCategory(String shareHoldingCategory) {
        this.shareHoldingCategory = shareHoldingCategory;
    }

    public Double getStackPercentage() {
        return stackPercentage;
    }

    public void setStackPercentage(Double stackPercentage) {
        this.stackPercentage = stackPercentage;
    }
    
    public static void printFields(Object obj) throws Exception {
        Field[] fields = OwnershipDetailResponse.class.getDeclaredFields();
        System.out.println("length : "+fields.length);
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
