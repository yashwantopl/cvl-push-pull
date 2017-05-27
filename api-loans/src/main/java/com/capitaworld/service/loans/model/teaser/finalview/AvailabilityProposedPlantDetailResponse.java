package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class AvailabilityProposedPlantDetailResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descriptionPM;

	private String estimatedValue;

	private String importedOrIndigenous;
	
	private String supplier;

	private String useOrPurpose;
	
	

	public AvailabilityProposedPlantDetailResponse() {
		super();
	}

	public AvailabilityProposedPlantDetailResponse(String descriptionPM, String estimatedValue,
			String importedOrIndigenous, String supplier, String useOrPurpose) {
		super();
		this.descriptionPM = descriptionPM;
		this.estimatedValue = estimatedValue;
		this.importedOrIndigenous = importedOrIndigenous;
		this.supplier = supplier;
		this.useOrPurpose = useOrPurpose;
	}

	public String getDescriptionPM() {
		return descriptionPM;
	}

	public void setDescriptionPM(String descriptionPM) {
		this.descriptionPM = descriptionPM;
	}

	public String getEstimatedValue() {
		return estimatedValue;
	}

	public void setEstimatedValue(String estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public String getImportedOrIndigenous() {
		return importedOrIndigenous;
	}

	public void setImportedOrIndigenous(String importedOrIndigenous) {
		this.importedOrIndigenous = importedOrIndigenous;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getUseOrPurpose() {
		return useOrPurpose;
	}

	public void setUseOrPurpose(String useOrPurpose) {
		this.useOrPurpose = useOrPurpose;
	}
	
	

}
