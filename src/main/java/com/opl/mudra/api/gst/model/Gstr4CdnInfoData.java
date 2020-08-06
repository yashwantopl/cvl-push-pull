/**
 * 
 */
package com.opl.mudra.api.gst.model;

/**
 * @author nilay.darji
 *
 */
public class Gstr4CdnInfoData {
	
	private String retPer;
	private String gstIn;
	private Long creditNote;
	private Long creditNoteUr;
	private Long sales;
	private Long debitNote;
	private Long debitNoteUr;
	private Long purchase;
	private Long total;
	
	public String getRetPer() {
		return retPer;
	}
	public void setRetPer(String retPer) {
		this.retPer = retPer;
	}
	public Long getCreditNote() {
		return creditNote;
	}
	public void setCreditNote(Long creditNote) {
		this.creditNote = creditNote;
	}
	public Long getCreditNoteUr() {
		return creditNoteUr;
	}
	public void setCreditNoteUr(Long creditNoteUr) {
		this.creditNoteUr = creditNoteUr;
	}
	public Long getSales() {
		return sales;
	}
	public void setSales(Long sales) {
		this.sales = sales;
	}
	public Long getDebitNote() {
		return debitNote;
	}
	public void setDebitNote(Long debitNote) {
		this.debitNote = debitNote;
	}
	public Long getDebitNoteUr() {
		return debitNoteUr;
	}
	public void setDebitNoteUr(Long debitNoteUr) {
		this.debitNoteUr = debitNoteUr;
	}
	public Long getPurchase() {
		return purchase;
	}
	public void setPurchase(Long purchase) {
		this.purchase = purchase;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getGstIn() {
		return gstIn;
	}
	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}
}
