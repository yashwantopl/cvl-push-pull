package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sanket
 *
 */
public class WorkingCapitalFinalViewResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BoardOfDirectorsResponse> boardOfDirectorsResponseList ;
    private List<StrategicAlliancesResponse> strategicAlliancesResponseList; 
    private List<KeyManagementResponse> keyManagementResponseList;
    private List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList;
    private List<TechnologyPositioningResponse> technologyPositioningResponseList;
    private List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList;
    private List<CapacityDetailResponse> capacityDetailResponses;
    private List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponse;
    private List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponse;
    private List<ScotAnalysisDetailResponse> scotAnalysisDetailResponses;
    private List<DprUserDataDetailResponse> dprUserDataDetailResponses;
    
    private List<Object> lastAuditedAnnualReportList;
    private List<Object> sanctionLetterCopyList;
    private List<Object> lastITReturnList;
    private List<Object> netWorthStatementOfdirectorsList;
    private List<Object> provisionalFinancialsList;
    private List<Object> panOfDirectorsList;
    private List<Object> detailedListOfShareholdersList;
    private List<Object> photoOfDirectorsList;
    
	public List<BoardOfDirectorsResponse> getBoardOfDirectorsResponseList() {
		return boardOfDirectorsResponseList;
	}
	public void setBoardOfDirectorsResponseList(List<BoardOfDirectorsResponse> boardOfDirectorsResponseList) {
		this.boardOfDirectorsResponseList = boardOfDirectorsResponseList;
	}
	public List<StrategicAlliancesResponse> getStrategicAlliancesResponseList() {
		return strategicAlliancesResponseList;
	}
	public void setStrategicAlliancesResponseList(List<StrategicAlliancesResponse> strategicAlliancesResponseList) {
		this.strategicAlliancesResponseList = strategicAlliancesResponseList;
	}
	public List<KeyManagementResponse> getKeyManagementResponseList() {
		return keyManagementResponseList;
	}
	public void setKeyManagementResponseList(List<KeyManagementResponse> keyManagementResponseList) {
		this.keyManagementResponseList = keyManagementResponseList;
	}
	public List<EmployeesCategoryBreaksResponse> getEmployeesCategoryBreaksResponseList() {
		return employeesCategoryBreaksResponseList;
	}
	public void setEmployeesCategoryBreaksResponseList(
			List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList) {
		this.employeesCategoryBreaksResponseList = employeesCategoryBreaksResponseList;
	}
	public List<TechnologyPositioningResponse> getTechnologyPositioningResponseList() {
		return technologyPositioningResponseList;
	}
	public void setTechnologyPositioningResponseList(
			List<TechnologyPositioningResponse> technologyPositioningResponseList) {
		this.technologyPositioningResponseList = technologyPositioningResponseList;
	}
	public List<RevenueAndOrderBookResponse> getRevenueAndOrderBookResponseList() {
		return revenueAndOrderBookResponseList;
	}
	public void setRevenueAndOrderBookResponseList(List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList) {
		this.revenueAndOrderBookResponseList = revenueAndOrderBookResponseList;
	}
	public List<CapacityDetailResponse> getCapacityDetailResponses() {
		return capacityDetailResponses;
	}
	public void setCapacityDetailResponses(List<CapacityDetailResponse> capacityDetailResponses) {
		this.capacityDetailResponses = capacityDetailResponses;
	}
	public List<AvailabilityProposedPlantDetailResponse> getAvailabilityProposedPlantDetailResponse() {
		return availabilityProposedPlantDetailResponse;
	}
	public void setAvailabilityProposedPlantDetailResponse(
			List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponse) {
		this.availabilityProposedPlantDetailResponse = availabilityProposedPlantDetailResponse;
	}
	public List<RequirementsAndAvailabilityRawMaterialsDetailResponse> getRequirementsAndAvailabilityRawMaterialsDetailResponse() {
		return requirementsAndAvailabilityRawMaterialsDetailResponse;
	}
	public void setRequirementsAndAvailabilityRawMaterialsDetailResponse(
			List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponse) {
		this.requirementsAndAvailabilityRawMaterialsDetailResponse = requirementsAndAvailabilityRawMaterialsDetailResponse;
	}
	public List<ScotAnalysisDetailResponse> getScotAnalysisDetailResponses() {
		return scotAnalysisDetailResponses;
	}
	public void setScotAnalysisDetailResponses(List<ScotAnalysisDetailResponse> scotAnalysisDetailResponses) {
		this.scotAnalysisDetailResponses = scotAnalysisDetailResponses;
	}
	public List<DprUserDataDetailResponse> getDprUserDataDetailResponses() {
		return dprUserDataDetailResponses;
	}
	public void setDprUserDataDetailResponses(List<DprUserDataDetailResponse> dprUserDataDetailResponses) {
		this.dprUserDataDetailResponses = dprUserDataDetailResponses;
	}
	public List<Object> getLastAuditedAnnualReportList() {
		return lastAuditedAnnualReportList;
	}
	public void setLastAuditedAnnualReportList(List<Object> lastAuditedAnnualReportList) {
		this.lastAuditedAnnualReportList = lastAuditedAnnualReportList;
	}
	public List<Object> getSanctionLetterCopyList() {
		return sanctionLetterCopyList;
	}
	public void setSanctionLetterCopyList(List<Object> sanctionLetterCopyList) {
		this.sanctionLetterCopyList = sanctionLetterCopyList;
	}
	public List<Object> getLastITReturnList() {
		return lastITReturnList;
	}
	public void setLastITReturnList(List<Object> lastITReturnList) {
		this.lastITReturnList = lastITReturnList;
	}
	public List<Object> getNetWorthStatementOfdirectorsList() {
		return netWorthStatementOfdirectorsList;
	}
	public void setNetWorthStatementOfdirectorsList(List<Object> netWorthStatementOfdirectorsList) {
		this.netWorthStatementOfdirectorsList = netWorthStatementOfdirectorsList;
	}
	public List<Object> getProvisionalFinancialsList() {
		return provisionalFinancialsList;
	}
	public void setProvisionalFinancialsList(List<Object> provisionalFinancialsList) {
		this.provisionalFinancialsList = provisionalFinancialsList;
	}
	public List<Object> getPanOfDirectorsList() {
		return panOfDirectorsList;
	}
	public void setPanOfDirectorsList(List<Object> panOfDirectorsList) {
		this.panOfDirectorsList = panOfDirectorsList;
	}
	public List<Object> getDetailedListOfShareholdersList() {
		return detailedListOfShareholdersList;
	}
	public void setDetailedListOfShareholdersList(List<Object> detailedListOfShareholdersList) {
		this.detailedListOfShareholdersList = detailedListOfShareholdersList;
	}
	public List<Object> getPhotoOfDirectorsList() {
		return photoOfDirectorsList;
	}
	public void setPhotoOfDirectorsList(List<Object> photoOfDirectorsList) {
		this.photoOfDirectorsList = photoOfDirectorsList;
	}
    
    

}
