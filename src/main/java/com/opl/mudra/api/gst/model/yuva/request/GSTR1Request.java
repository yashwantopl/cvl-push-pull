/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.gst.model.CommonRequest;
import com.opl.mudra.api.gst.model.model.DataMapping;
import com.opl.mudra.api.gst.model.model.GSTNotApplicable;
import com.opl.mudra.api.gst.model.util.CommonUtils;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GSTR1Request extends CommonRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gstin;

	private String username;

	private Long applicationId;

	@JsonProperty("ret_period")
	private String retPeriod;

	private String otp;

	private String password;

	private String consent;

	private CommonUtils.Step currentStep;

	private CommonUtils.Step nextStep;
	
	
	private Long userId;
	
	private Integer businessTypeId;
	private String imGstin;
	

	private Double noOfCustomer;
	
	private Double concentration;
	
	private String gstCompositData;
	
	private Boolean isVayana;
	
	private Long gstResponseId;
	
	private Long retryCount;
	
	private DataMapping dataMapping;
	
	
	private String pan;
	
	private GSTNotApplicable gstNotApplicableData;
	
    private Boolean isMultiGST;
    
    private Long fpUserId;
    private Long fsUserId;
    private Long fileId;
    private Integer amountOfLoan;
    
    private String gstr4PopUpResponse;
    
    private Long requestedMonths;
    private Date dob;
    
    private CommonUtils.GSTDataType gstDataType;

    private Double totalPurchaseFromIm;	
    private Double projectedPurchaseFromIm;
    private Double highestSalesPerticularfromIM;
    
    private Long profileId;
    private Long gstId;
    private Boolean isManualGst;
    private Integer gstType;
    private Boolean isProceedCopyData;
    
	public Double getHighestSalesPerticularfromIM() {
		return highestSalesPerticularfromIM;
	}

	public void setHighestSalesPerticularfromIM(Double highestSalesPerticularfromIM) {
		this.highestSalesPerticularfromIM = highestSalesPerticularfromIM;
	}

	public CommonUtils.GSTDataType getGstDataType() {
		return gstDataType;
	}

	public void setGstDataType(CommonUtils.GSTDataType gstDataType) {
		this.gstDataType = gstDataType;
	}

	/**
	 * @return the isMultiGST
	 */
	public Boolean getIsMultiGST() {
		return isMultiGST;
	}

	/**
	 * @param isMultiGST the isMultiGST to set
	 */
	public void setIsMultiGST(Boolean isMultiGST) {
		this.isMultiGST = isMultiGST;
	}
	
	/**
	 * @return the dataMapping
	 */
	public DataMapping getDataMapping() {
		return dataMapping;
	}

	/**
	 * @param dataMapping the dataMapping to set
	 */
	public void setDataMapping(DataMapping dataMapping) {
		this.dataMapping = dataMapping;
	}

	/**
	 * @return the isVayana
	 */
	public Boolean getIsVayana() {
		return isVayana;
	}

	/**
	 * @param isVayana the isVayana to set
	 */
	public void setIsVayana(Boolean isVayana) {
		this.isVayana = isVayana;
	}

	/**
	 * @return the noOfCustomer
	 */
	public Double getNoOfCustomer() {
		return noOfCustomer;
	}

	/**
	 * @param noOfCustomer the noOfCustomer to set
	 */
	public void setNoOfCustomer(Double noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}

	/**
	 * @return the concentration
	 */
	public Double getConcentration() {
		return concentration;
	}

	/**
	 * @param concentration the concentration to set
	 */
	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}

	/**
	 * @return the projectedSales
	 */
	public Double getProjectedSales() {
		return projectedSales;
	}

	/**
	 * @param projectedSales the projectedSales to set
	 */
	public void setProjectedSales(Double projectedSales) {
		this.projectedSales = projectedSales;
	}

	/**
	 * @return the noOfReturnPeriod
	 */
	public Double getNoOfReturnPeriod() {
		return noOfReturnPeriod;
	}

	/**
	 * @param noOfReturnPeriod the noOfReturnPeriod to set
	 */
	public void setNoOfReturnPeriod(Double noOfReturnPeriod) {
		this.noOfReturnPeriod = noOfReturnPeriod;
	}

	private Double projectedSales;
	
	private Double noOfReturnPeriod;
	
	

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the currentStep
	 */
	public CommonUtils.Step getCurrentStep() {
		return currentStep;
	}

	/**
	 * @param currentStep
	 *            the currentStep to set
	 */
	public void setCurrentStep(CommonUtils.Step currentStep) {
		this.currentStep = currentStep;
	}

	/**
	 * @return the nextStep
	 */
	public CommonUtils.Step getNextStep() {
		return nextStep;
	}

	/**
	 * @param nextStep
	 *            the nextStep to set
	 */
	public void setNextStep(CommonUtils.Step nextStep) {
		this.nextStep = nextStep;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the consent
	 */
	public String getConsent() {
		return consent;
	}

	/**
	 * @param consent
	 *            the consent to set
	 */
	public void setConsent(String consent) {
		this.consent = consent;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp
	 *            the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin
	 *            the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the retPeriod
	 */
	public String getRetPeriod() {
		return retPeriod;
	}

	/**
	 * @param retPeriod
	 *            the retPeriod to set
	 */
	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the businessTypeId
	 */
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	/**
	 * @param businessTypeId the businessTypeId to set
	 */
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getGstCompositData() {
		return gstCompositData;
	}

	public void setGstCompositData(String gstCompositData) {
		this.gstCompositData = gstCompositData;
	}

	public Long getGstResponseId() {
		return gstResponseId;
	}

	public void setGstResponseId(Long gstResponseId) {
		this.gstResponseId = gstResponseId;
	}

	public Long getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Long retryCount) {
		this.retryCount = retryCount;
	}
	
	private Long serviceProviderId;


	public Long getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(Long serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	private String actionRequired;


	public String getActionRequired() {
		return actionRequired;
	}

	public void setActionRequired(String actionRequired) {
		this.actionRequired = actionRequired;
	}



	/**
	 * @return the pan
	 */
	public String getPan() {
		return pan;
	}

	/**
	 * @param pan the pan to set
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}

	public GSTNotApplicable getGstNotApplicableData() {
		return gstNotApplicableData;
	}

	public void setGstNotApplicableData(GSTNotApplicable gstNotApplicableData) {
		this.gstNotApplicableData = gstNotApplicableData;
	}

	public Long getFpUserId() {
		return fpUserId;
	}

	public void setFpUserId(Long fpUserId) {
		this.fpUserId = fpUserId;
	}

	public Long getFsUserId() {
		return fsUserId;
	}

	public void setFsUserId(Long fsUserId) {
		this.fsUserId = fsUserId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}

	public String getGstr4PopUpResponse() {
		return gstr4PopUpResponse;
	}

	public void setGstr4PopUpResponse(String gstr4PopUpResponse) {
		this.gstr4PopUpResponse = gstr4PopUpResponse;
	}

	public Long getRequestedMonths() {
		return requestedMonths;
	}

	public void setRequestedMonths(Long requestedMonths) {
		this.requestedMonths = requestedMonths;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getImGstin() {
		return imGstin;
	}

	public void setImGstin(String imGstin) {
		this.imGstin = imGstin;
	}

	public Double getTotalPurchaseFromIm() {
		return totalPurchaseFromIm;
	}

	public void setTotalPurchaseFromIm(Double totalPurchaseFromIm) {
		this.totalPurchaseFromIm = totalPurchaseFromIm;
	}

	public Double getProjectedPurchaseFromIm() {
		return projectedPurchaseFromIm;
	}

	public void setProjectedPurchaseFromIm(Double projectedPurchaseFromIm) {
		this.projectedPurchaseFromIm = projectedPurchaseFromIm;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getGstId() {
		return gstId;
	}

	public void setGstId(Long gstId) {
		this.gstId = gstId;
	}

	public Boolean getIsManualGst() {
		return isManualGst;
	}

	public void setIsManualGst(Boolean isManualGst) {
		this.isManualGst = isManualGst;
	}

	public Integer getGstType() {
		return gstType;
	}

	public void setGstType(Integer gstType) {
		this.gstType = gstType;
	}

	public Boolean getIsProceedCopyData() {
		return isProceedCopyData;
	}

	public void setIsProceedCopyData(Boolean isProceedCopyData) {
		this.isProceedCopyData = isProceedCopyData;
	}
	
}
