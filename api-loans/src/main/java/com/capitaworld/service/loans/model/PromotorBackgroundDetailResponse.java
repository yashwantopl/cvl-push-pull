package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotorBackgroundDetailResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String promotorsName;

    private String gender;

    private String panNo;

    private String relationshipType;

    private String din;

    private String designation;

    private String address;

    private String mobile;

    private String dob;
    
    private Date dobDate;

    private String totalExperience;
    
    private String networth;

    private String appointmentDate;
    
    private Date appointment;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPromotorsName() {
        return promotorsName;
    }

    public void setPromotorsName(String promotorsName) {
        this.promotorsName = promotorsName;
    }



	
	public String getNetworth() {
		return networth;
	}

	public void setNetworth(String networth) {
		this.networth = networth;
	}

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getDin() {
        return din;
    }

    public void setDin(String din) {
        this.din = din;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    

    /**
	 * @return the appointment
	 */
	public Date getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment the appointment to set
	 */
	public void setAppointment(Date appointment) {
		this.appointment = appointment;
	}

	/**
	 * @return the dobDate
	 */
	public Date getDobDate() {
		return dobDate;
	}

	/**
	 * @param dobDate the dobDate to set
	 */
	public void setDobDate(Date dobDate) {
		this.dobDate = dobDate;
	}

	public static void printFields(Object obj) throws Exception {
        Field[] fields = PromotorBackgroundDetailResponse.class.getDeclaredFields();
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
