package com.opl.mudra.api.matchengine.model;

public class ProposalCountResponse {
	
	private Integer status;

	private String message;
	
	private Long matches;
	
	private Long sent;
	
	private Long received;
	
	private Long primary;
	
	private Long advanced;
	
	private Long disbursed;
	
	private Long hold;
	
	private Long rejected;

	private Long sanctionByOtherBank;
	
	private Long inbox;
	
	private Long total;
	
	private Long assignedProposal;

	private Long partiallyDisbursed;
	
	private Long offline;

	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProposalCountResponse() {
		super();
		this.matches = 0l;
		this.sent = 0l;
		this.received = 0l;
		this.primary = 0l;
		this.advanced = 0l;
		this.disbursed = 0l;
		this.hold = 0l;
		this.rejected = 0l;
		this.inbox = 0l;
		this.total = 0l;
		this.assignedProposal = 0l;
		this.partiallyDisbursed = 0l;
		this.offline = 0l;
	}

	public ProposalCountResponse(String message,Integer status) {
		this();
		this.message = message;
		this.status = status;
	}

	public Long getMatches() {
		return matches;
	}

	public void setMatches(Long matches) {
		this.matches = matches;
	}

	public Long getSent() {
		return sent;
	}

	public void setSent(Long sent) {
		this.sent = sent;
	}

	public Long getReceived() {
		return received;
	}

	public void setReceived(Long received) {
		this.received = received;
	}

	public Long getPrimary() {
		return primary;
	}

	public void setPrimary(Long primary) {
		this.primary = primary;
	}

	public Long getAdvanced() {
		return advanced;
	}

	public void setAdvanced(Long advanced) {
		this.advanced = advanced;
	}

	public Long getHold() {
		return hold;
	}

	public void setHold(Long hold) {
		this.hold = hold;
	}

	public Long getRejected() {
		return rejected;
	}

	public void setRejected(Long rejected) {
		this.rejected = rejected;
	}

	public Long getInbox() {
		return inbox;
	}

	public void setInbox(Long inbox) {
		this.inbox = inbox;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getAssignedProposal() {
		return assignedProposal;
	}

	public void setAssignedProposal(Long assignedProposal) {
		this.assignedProposal = assignedProposal;
	}

	public Long getDisbursed() {
		return disbursed;
	}

	public void setDisbursed(Long disbursed) {
		this.disbursed = disbursed;
	}

	public Long getPartiallyDisbursed() {
		return partiallyDisbursed;
	}

	public void setPartiallyDisbursed(Long partiallyDisbursed) {
		this.partiallyDisbursed = partiallyDisbursed;
	}

	public Long getOffline() {
		return offline;
	}

	public void setOffline(Long offline) {
		this.offline = offline;
	}

	public Long getSanctionByOtherBank() {
		return sanctionByOtherBank;
	}

	public void setSanctionByOtherBank(Long sanctionByOtherBank) {
		this.sanctionByOtherBank = sanctionByOtherBank;
	}
}
