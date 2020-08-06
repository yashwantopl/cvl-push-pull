package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opl.mudra.api.gst.model.GstCompositeResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GstCalcMappingTable implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createdDate;

	private String gstin;

	private Boolean isActive;

	private Date modifiedDate;

	private List<InwardSupp> inwardSupps;

	private List<InwardSuppGst> inwardSuppGsts;

	private List<OutwardSuppOther> outwardSuppOthers;

	private List<OutwardSuppZeroRated> outwardSuppZeroRateds;

	private List<SummB2bSale> summB2bSales;

	private List<SummB2clSale> summB2clSales;

	private List<SummB2cssSale> summB2cssSales;

	private List<SummB2esSale> summB2esSales;

	private List<SummExemOrNilSale> summExemOrNilSales;

	private List<SummOfCreDebNote> summOfCreDebNotes;

	private List<SummOfPurInvWise> summOfPurInvWises;

	private List<SummOfPurPurWise> summOfPurPurWises;

	private List<SuppMadeUinHolder> suppMadeUinHolders;

	private List<SuppMadeUnregPer> suppMadeUnregPers;

	private List<SuppMadeUnregPerComTaxPer> suppMadeUnregPerComTaxPers;

	private List<OutwardSupNilRated> outwardSupNilRated;

	private List<GstExempt> gstExempt;

	private List<SummOfPaymentOfTax> summOfPaymentOfTax;

	private List<SummOfEligItcAvail> summOfEligItcAvail;

	private List<SummOfEligItcNetAvail> summOfEligItcNetAvail;

	private List<SummOfEligItcRe> summOfEligItcRe;

	private List<SummOfPurInvWise> summOfPurInvWises2;

	private Object data;

	private CAMGSTData camgstData;

	private GstCompositeResponse gstComposite;

	private Boolean isComposite;

	private String composite;

	private CAMGSTSpecificData camGstSpecificData;

	private List<SummOfHsn> summOfHsnSales;
	private List<SummOfHsn> summOfHsnPurchases;



	/**
	 * @return the isComposite
	 */
	public Boolean getIsComposite() {
		return isComposite;
	}


	/**
	 * @param isComposite the isComposite to set
	 */
	public void setIsComposite(Boolean isComposite) {
		this.isComposite = isComposite;
	}


	/**
	 * @return the gstComposite
	 */
	public GstCompositeResponse getGstComposite() {
		return gstComposite;
	}


	/**
	 * @param gstComposite the gstComposite to set
	 */
	public void setGstComposite(GstCompositeResponse gstComposite) {
		this.gstComposite = gstComposite;
	}


	public GstCalcMappingTable() {
		this.createdDate = new Date();
		this.gstin ="";
		this.isActive =  Boolean.valueOf(false);
		this.modifiedDate = new Date();
		this.inwardSupps = new ArrayList<>();
		this.inwardSuppGsts = new ArrayList<>();
		this.outwardSuppOthers = new ArrayList<>();
		this.outwardSuppZeroRateds = new ArrayList<>();
		this.summB2bSales = new ArrayList<>();
		this.summB2clSales =new ArrayList<>();
		this.summB2cssSales = new ArrayList<>();
		this.summB2esSales = new ArrayList<>();
		this.summExemOrNilSales = new ArrayList<>();
		this.summOfCreDebNotes = new ArrayList<>();
		this.summOfPurInvWises = new ArrayList<>();
		this.summOfPurPurWises = new ArrayList<>();
		this.suppMadeUinHolders = new ArrayList<>();
		this.suppMadeUnregPers = new ArrayList<>();
		this.suppMadeUnregPerComTaxPers = new ArrayList<>();
		this.outwardSupNilRated = new ArrayList<>();
		this.gstExempt = new ArrayList<>();
		this.summOfPaymentOfTax = new ArrayList<>();
		this.summOfEligItcAvail = new ArrayList<>();
		this.summOfEligItcNetAvail = new ArrayList<>();
		this.summOfEligItcRe = new ArrayList<>();
		this.summOfPurInvWises2 = new ArrayList<>();
		this.data = new Object();
		this.camgstData = new CAMGSTData();
		this.composite = "";
		this.isComposite = false;
		this.gstComposite = new GstCompositeResponse();
		this.camGstSpecificData =new CAMGSTSpecificData();
		this.summOfHsnSales=new ArrayList<>();
		this.summOfHsnPurchases=new ArrayList<>();
	}


	public String getComposite() {
		return composite;
	}

	public void setComposite(String composite) {
		this.composite = composite;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the summOfEligItcRe
	 */
	public List<SummOfEligItcRe> getSummOfEligItcRe() {
		return summOfEligItcRe;
	}

	/**
	 * @param summOfEligItcRe the summOfEligItcRe to set
	 */
	public void setSummOfEligItcRe(List<SummOfEligItcRe> summOfEligItcRe) {
		this.summOfEligItcRe = summOfEligItcRe;
	}

	/**
	 * @return the summOfEligItcNetAvail
	 */
	public List<SummOfEligItcNetAvail> getSummOfEligItcNetAvail() {
		return summOfEligItcNetAvail;
	}

	/**
	 * @param summOfEligItcNetAvail the summOfEligItcNetAvail to set
	 */
	public void setSummOfEligItcNetAvail(List<SummOfEligItcNetAvail> summOfEligItcNetAvail) {
		this.summOfEligItcNetAvail = summOfEligItcNetAvail;
	}

	/**
	 * @return the summOfEligItcAvail
	 */
	public List<SummOfEligItcAvail> getSummOfEligItcAvail() {
		return summOfEligItcAvail;
	}

	/**
	 * @param summOfEligItcAvail the summOfEligItcAvail to set
	 */
	public void setSummOfEligItcAvail(List<SummOfEligItcAvail> summOfEligItcAvail) {
		this.summOfEligItcAvail = summOfEligItcAvail;
	}

	/**
	 * @return the summOfPaymentOfTax
	 */
	public List<SummOfPaymentOfTax> getSummOfPaymentOfTax() {
		return summOfPaymentOfTax;
	}

	/**
	 * @param summOfPaymentOfTax the summOfPaymentOfTax to set
	 */
	public void setSummOfPaymentOfTax(List<SummOfPaymentOfTax> summOfPaymentOfTax) {
		this.summOfPaymentOfTax = summOfPaymentOfTax;
	}

	/**
	 * @return the gstExempt
	 */
	public List<GstExempt> getGstExempt() {
		return gstExempt;
	}

	/**
	 * @param gstExempt the gstExempt to set
	 */
	public void setGstExempt(List<GstExempt> gstExempt) {
		this.gstExempt = gstExempt;
	}

	/**
	 * @return the outwardSupNilRated
	 */
	public List<OutwardSupNilRated> getOutwardSupNilRated() {
		return outwardSupNilRated;
	}

	/**
	 * @param outwardSupNilRated the outwardSupNilRated to set
	 */
	public void setOutwardSupNilRated(List<OutwardSupNilRated> outwardSupNilRated) {
		this.outwardSupNilRated = outwardSupNilRated;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getGstin() {
		return this.gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<InwardSupp> getInwardSupps() {
		return this.inwardSupps;
	}

	public void setInwardSupps(List<InwardSupp> inwardSupps) {
		this.inwardSupps = inwardSupps;
	}

	public InwardSupp addInwardSupp(InwardSupp inwardSupp) {
		getInwardSupps().add(inwardSupp);

		return inwardSupp;
	}

	public InwardSupp removeInwardSupp(InwardSupp inwardSupp) {
		getInwardSupps().remove(inwardSupp);

		return inwardSupp;
	}

	public List<InwardSuppGst> getInwardSuppGsts() {
		return this.inwardSuppGsts;
	}

	public void setInwardSuppGsts(List<InwardSuppGst> inwardSuppGsts) {
		this.inwardSuppGsts = inwardSuppGsts;
	}

	public InwardSuppGst addInwardSuppGst(InwardSuppGst inwardSuppGst) {
		getInwardSuppGsts().add(inwardSuppGst);

		return inwardSuppGst;
	}

	public InwardSuppGst removeInwardSuppGst(InwardSuppGst inwardSuppGst) {
		getInwardSuppGsts().remove(inwardSuppGst);

		return inwardSuppGst;
	}

	public List<OutwardSuppOther> getOutwardSuppOthers() {
		return this.outwardSuppOthers;
	}

	public void setOutwardSuppOthers(List<OutwardSuppOther> outwardSuppOthers) {
		this.outwardSuppOthers = outwardSuppOthers;
	}

	public OutwardSuppOther addOutwardSuppOther(OutwardSuppOther outwardSuppOther) {
		getOutwardSuppOthers().add(outwardSuppOther);

		return outwardSuppOther;
	}

	public OutwardSuppOther removeOutwardSuppOther(OutwardSuppOther outwardSuppOther) {
		getOutwardSuppOthers().remove(outwardSuppOther);

		return outwardSuppOther;
	}

	public List<OutwardSuppZeroRated> getOutwardSuppZeroRateds() {
		return this.outwardSuppZeroRateds;
	}

	public void setOutwardSuppZeroRateds(List<OutwardSuppZeroRated> outwardSuppZeroRateds) {
		this.outwardSuppZeroRateds = outwardSuppZeroRateds;
	}

	public OutwardSuppZeroRated addOutwardSuppZeroRated(OutwardSuppZeroRated outwardSuppZeroRated) {
		getOutwardSuppZeroRateds().add(outwardSuppZeroRated);

		return outwardSuppZeroRated;
	}

	public OutwardSuppZeroRated removeOutwardSuppZeroRated(OutwardSuppZeroRated outwardSuppZeroRated) {
		getOutwardSuppZeroRateds().remove(outwardSuppZeroRated);

		return outwardSuppZeroRated;
	}

	public List<SummB2bSale> getSummB2bSales() {
		return this.summB2bSales;
	}

	public void setSummB2bSales(List<SummB2bSale> summB2bSales) {
		this.summB2bSales = summB2bSales;
	}

	public SummB2bSale addSummB2bSale(SummB2bSale summB2bSale) {
		getSummB2bSales().add(summB2bSale);

		return summB2bSale;
	}

	public SummB2bSale removeSummB2bSale(SummB2bSale summB2bSale) {
		getSummB2bSales().remove(summB2bSale);

		return summB2bSale;
	}

	public List<SummB2clSale> getSummB2clSales() {
		return this.summB2clSales;
	}

	public void setSummB2clSales(List<SummB2clSale> summB2clSales) {
		this.summB2clSales = summB2clSales;
	}

	public SummB2clSale addSummB2clSale(SummB2clSale summB2clSale) {
		getSummB2clSales().add(summB2clSale);

		return summB2clSale;
	}

	public SummB2clSale removeSummB2clSale(SummB2clSale summB2clSale) {
		getSummB2clSales().remove(summB2clSale);

		return summB2clSale;
	}

	public List<SummB2cssSale> getSummB2cssSales() {
		return this.summB2cssSales;
	}

	public void setSummB2cssSales(List<SummB2cssSale> summB2cssSales) {
		this.summB2cssSales = summB2cssSales;
	}

	public SummB2cssSale addSummB2cssSale(SummB2cssSale summB2cssSale) {
		getSummB2cssSales().add(summB2cssSale);

		return summB2cssSale;
	}

	public SummB2cssSale removeSummB2cssSale(SummB2cssSale summB2cssSale) {
		getSummB2cssSales().remove(summB2cssSale);

		return summB2cssSale;
	}

	public List<SummB2esSale> getSummB2esSales() {
		return this.summB2esSales;
	}

	public void setSummB2esSales(List<SummB2esSale> summB2esSales) {
		this.summB2esSales = summB2esSales;
	}

	public SummB2esSale addSummB2esSale(SummB2esSale summB2esSale) {
		getSummB2esSales().add(summB2esSale);

		return summB2esSale;
	}

	public SummB2esSale removeSummB2esSale(SummB2esSale summB2esSale) {
		getSummB2esSales().remove(summB2esSale);

		return summB2esSale;
	}

	public List<SummExemOrNilSale> getSummExemOrNilSales() {
		return this.summExemOrNilSales;
	}

	public void setSummExemOrNilSales(List<SummExemOrNilSale> summExemOrNilSales) {
		this.summExemOrNilSales = summExemOrNilSales;
	}

	public SummExemOrNilSale addSummExemOrNilSale(SummExemOrNilSale summExemOrNilSale) {
		getSummExemOrNilSales().add(summExemOrNilSale);

		return summExemOrNilSale;
	}

	public SummExemOrNilSale removeSummExemOrNilSale(SummExemOrNilSale summExemOrNilSale) {
		getSummExemOrNilSales().remove(summExemOrNilSale);

		return summExemOrNilSale;
	}

	public List<SummOfCreDebNote> getSummOfCreDebNotes() {
		return this.summOfCreDebNotes;
	}

	public void setSummOfCreDebNotes(List<SummOfCreDebNote> summOfCreDebNotes) {
		this.summOfCreDebNotes = summOfCreDebNotes;
	}

	public SummOfCreDebNote addSummOfCreDebNote(SummOfCreDebNote summOfCreDebNote) {
		getSummOfCreDebNotes().add(summOfCreDebNote);

		return summOfCreDebNote;
	}

	public SummOfCreDebNote removeSummOfCreDebNote(SummOfCreDebNote summOfCreDebNote) {
		getSummOfCreDebNotes().remove(summOfCreDebNote);

		return summOfCreDebNote;
	}

	public List<SummOfPurInvWise> getSummOfPurInvWises() {
		return this.summOfPurInvWises;
	}

	public void setSummOfPurInvWises(List<SummOfPurInvWise> summOfPurInvWises) {
		this.summOfPurInvWises = summOfPurInvWises;
	}

	public SummOfPurInvWise addSummOfPurInvWis(SummOfPurInvWise summOfPurInvWis) {
		getSummOfPurInvWises().add(summOfPurInvWis);

		return summOfPurInvWis;
	}

	public SummOfPurInvWise removeSummOfPurInvWis(SummOfPurInvWise summOfPurInvWis) {
		getSummOfPurInvWises().remove(summOfPurInvWis);

		return summOfPurInvWis;
	}

	public List<SummOfPurPurWise> getSummOfPurPurWises() {
		return this.summOfPurPurWises;
	}

	public void setSummOfPurPurWises(List<SummOfPurPurWise> summOfPurPurWises) {
		this.summOfPurPurWises = summOfPurPurWises;
	}

	public SummOfPurPurWise addSummOfPurPurWis(SummOfPurPurWise summOfPurPurWis) {
		getSummOfPurPurWises().add(summOfPurPurWis);

		return summOfPurPurWis;
	}

	public SummOfPurPurWise removeSummOfPurPurWis(SummOfPurPurWise summOfPurPurWis) {
		getSummOfPurPurWises().remove(summOfPurPurWis);

		return summOfPurPurWis;
	}

	public List<SuppMadeUinHolder> getSuppMadeUinHolders() {
		return this.suppMadeUinHolders;
	}

	public void setSuppMadeUinHolders(List<SuppMadeUinHolder> suppMadeUinHolders) {
		this.suppMadeUinHolders = suppMadeUinHolders;
	}

	public SuppMadeUinHolder addSuppMadeUinHolder(SuppMadeUinHolder suppMadeUinHolder) {
		getSuppMadeUinHolders().add(suppMadeUinHolder);

		return suppMadeUinHolder;
	}

	public SuppMadeUinHolder removeSuppMadeUinHolder(SuppMadeUinHolder suppMadeUinHolder) {
		getSuppMadeUinHolders().remove(suppMadeUinHolder);

		return suppMadeUinHolder;
	}

	public List<SuppMadeUnregPer> getSuppMadeUnregPers() {
		return this.suppMadeUnregPers;
	}

	public void setSuppMadeUnregPers(List<SuppMadeUnregPer> suppMadeUnregPers) {
		this.suppMadeUnregPers = suppMadeUnregPers;
	}

	public SuppMadeUnregPer addSuppMadeUnregPer(SuppMadeUnregPer suppMadeUnregPer) {
		getSuppMadeUnregPers().add(suppMadeUnregPer);

		return suppMadeUnregPer;
	}

	public SuppMadeUnregPer removeSuppMadeUnregPer(SuppMadeUnregPer suppMadeUnregPer) {
		getSuppMadeUnregPers().remove(suppMadeUnregPer);

		return suppMadeUnregPer;
	}

	public List<SuppMadeUnregPerComTaxPer> getSuppMadeUnregPerComTaxPers() {
		return this.suppMadeUnregPerComTaxPers;
	}

	public void setSuppMadeUnregPerComTaxPers(List<SuppMadeUnregPerComTaxPer> suppMadeUnregPerComTaxPers) {
		this.suppMadeUnregPerComTaxPers = suppMadeUnregPerComTaxPers;
	}

	public SuppMadeUnregPerComTaxPer addSuppMadeUnregPerComTaxPer(SuppMadeUnregPerComTaxPer suppMadeUnregPerComTaxPer) {
		getSuppMadeUnregPerComTaxPers().add(suppMadeUnregPerComTaxPer);

		return suppMadeUnregPerComTaxPer;
	}

	public SuppMadeUnregPerComTaxPer removeSuppMadeUnregPerComTaxPer(SuppMadeUnregPerComTaxPer suppMadeUnregPerComTaxPer) {
		getSuppMadeUnregPerComTaxPers().remove(suppMadeUnregPerComTaxPer);

		return suppMadeUnregPerComTaxPer;
	}

	public OutwardSupNilRated addOutwardSupNilRated(OutwardSupNilRated outwardSupNilRated) {
		getOutwardSupNilRated().add(outwardSupNilRated);

		return outwardSupNilRated;
	}


	public OutwardSupNilRated removeSuppMadeUnregPerComTaxPer(OutwardSupNilRated outwardSupNilRated) {
		getOutwardSupNilRated().remove(outwardSupNilRated);

		return outwardSupNilRated;
	}


	public GstExempt addGstExempt(GstExempt gstExempt) {
		getGstExempt().add(gstExempt);

		return gstExempt;
	}


	public GstExempt removeGstExempt(GstExempt gstExempt) {
		getGstExempt().remove(gstExempt);

		return gstExempt;
	}

	public SummOfPaymentOfTax addSummOfPaymentOfTax(SummOfPaymentOfTax summOfPaymentOfTax) {
		getSummOfPaymentOfTax().add(summOfPaymentOfTax);

		return summOfPaymentOfTax;
	}


	public SummOfPaymentOfTax removeSummOfPaymentOfTax(SummOfPaymentOfTax summOfPaymentOfTax) {
		getSummOfPaymentOfTax().remove(summOfPaymentOfTax);

		return summOfPaymentOfTax;
	}


	public SummOfEligItcAvail addSummOfEligItcAvail(SummOfEligItcAvail summOfEligItcAvail) {
		getSummOfEligItcAvail().add(summOfEligItcAvail);

		return summOfEligItcAvail;
	}

	public SummOfEligItcNetAvail addSummOfEligItcNetAvail(SummOfEligItcNetAvail summOfEligItcNetAvail) {
		getSummOfEligItcNetAvail().add(summOfEligItcNetAvail);

		return summOfEligItcNetAvail;
	}

	public SummOfEligItcRe addSummOfEligItcRe(SummOfEligItcRe summOfEligItcRe) {
		getSummOfEligItcRe().add(summOfEligItcRe);

		return summOfEligItcRe;
	}


	public List<SummOfPurInvWise> getSummOfPurInvWises2() {
		return this.summOfPurInvWises2;
	}

	public void setSummOfPurInvWises2(List<SummOfPurInvWise> summOfPurInvWises) {
		this.summOfPurInvWises2 = summOfPurInvWises;
	}

	public SummOfPurInvWise addSummOfPurInvWis2(SummOfPurInvWise summOfPurInvWis) {
		getSummOfPurInvWises2().add(summOfPurInvWis);

		return summOfPurInvWis;
	}

	public SummOfPurInvWise removeSummOfPurInvWis2(SummOfPurInvWise summOfPurInvWis2) {
		getSummOfPurInvWises2().remove(summOfPurInvWis2);

		return summOfPurInvWis2;
	}

	/**
	 * @return the camgstData
	 */
	public CAMGSTData getCamgstData() {
		return camgstData;
	}

	/**
	 * @param camgstData the camgstData to set
	 */
	public void setCamgstData(CAMGSTData camgstData) {
		this.camgstData = camgstData;
	}


	public CAMGSTSpecificData getCamGstSpecificData() {
		return camGstSpecificData;
	}


	public void setCamGstSpecificData(CAMGSTSpecificData camGstSpecificData) {
		this.camGstSpecificData = camGstSpecificData;
	}


	public List<SummOfHsn> getSummOfHsnSales() {
		return summOfHsnSales;
	}


	public void setSummOfHsnSales(List<SummOfHsn> summOfHsn) {
		this.summOfHsnSales = summOfHsn;
	}
	
	public SummOfHsn addSummOfHsnSales(SummOfHsn summOfHsn) {
		getSummOfHsnSales().add(summOfHsn);

		return summOfHsn;
	}

	public SummOfHsn removeSummOfHsnSales(SummOfHsn summOfHsn) {
		getSummOfHsnSales().remove(summOfHsn);

		return summOfHsn;
	}


	public List<SummOfHsn> getSummOfHsnPurchases() {
		return summOfHsnPurchases;
	}


	public void setSummOfHsnPurchases(List<SummOfHsn> summOfHsnPurchases) {
		this.summOfHsnPurchases = summOfHsnPurchases;
	}
	public SummOfHsn addSummOfHsnPurchases(SummOfHsn summOfHsn) {
		getSummOfHsnPurchases().add(summOfHsn);

		return summOfHsn;
	}

	public SummOfHsn removeSummOfHsnPurchases(SummOfHsn summOfHsn) {
		getSummOfHsnPurchases().remove(summOfHsn);

		return summOfHsn;
	}

	
}