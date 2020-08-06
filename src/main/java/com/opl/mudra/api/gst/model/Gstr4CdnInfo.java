/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.util.List;

/**
 * @author nilay.darji
 *
 */
public class Gstr4CdnInfo {
	
	private List<Gstr4CdnInfoData> cdnInfoDatas;
	private Long creditNoteTotal;
	private Long creditNoteUrTotal;
	private Long salesTotal;
	private Long debitNoteTotal;
	private Long debitNoteUrTotal;
	private Long purchaseTotal;
	private Long totalOfTotal;
	
	public List<Gstr4CdnInfoData> getCdnInfoDatas() {
		return cdnInfoDatas;
	}
	public void setCdnInfoDatas(List<Gstr4CdnInfoData> cdnInfoDatas) {
		this.cdnInfoDatas = cdnInfoDatas;
	}
	public Long getCreditNoteTotal() {
		return creditNoteTotal;
	}
	public void setCreditNoteTotal(Long creditNoteTotal) {
		this.creditNoteTotal = creditNoteTotal;
	}
	public Long getCreditNoteUrTotal() {
		return creditNoteUrTotal;
	}
	public void setCreditNoteUrTotal(Long creditNoteUrTotal) {
		this.creditNoteUrTotal = creditNoteUrTotal;
	}
	public Long getSalesTotal() {
		return salesTotal;
	}
	public void setSalesTotal(Long salesTotal) {
		this.salesTotal = salesTotal;
	}
	public Long getDebitNoteTotal() {
		return debitNoteTotal;
	}
	public void setDebitNoteTotal(Long debitNoteTotal) {
		this.debitNoteTotal = debitNoteTotal;
	}
	public Long getDebitNoteUrTotal() {
		return debitNoteUrTotal;
	}
	public void setDebitNoteUrTotal(Long debitNoteUrTotal) {
		this.debitNoteUrTotal = debitNoteUrTotal;
	}
	public Long getPurchaseTotal() {
		return purchaseTotal;
	}
	public void setPurchaseTotal(Long purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}
	public Long getTotalOfTotal() {
		return totalOfTotal;
	}
	public void setTotalOfTotal(Long totalOfTotal) {
		this.totalOfTotal = totalOfTotal;
	}
}
