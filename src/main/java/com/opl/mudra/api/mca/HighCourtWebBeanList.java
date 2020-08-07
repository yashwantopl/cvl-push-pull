package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class HighCourtWebBeanList {
	

	private String year;

	@JsonProperty("high-court-name")
	private String high_court_name;

	@JsonProperty("branch-name")
	private String branch_name;

	@JsonProperty("case-type")
	private String case_type;

	@JsonProperty("case-no")
	private String case_no;

	@JsonProperty("name-of-petitioner")
	private String name_of_petitioner;

	@JsonProperty("name-of-respondent")
	private String name_of_respondent;

	private String companyName;

	private String caseType;

	private String nameOfRespondent;

	private String highCourtName;

	private String linkOfCase;

	private String caseNo;

	private String branchName;

	@JsonProperty("company-name")
	private String company_name;

	@JsonProperty("link-of-case")
	private String link_of_case;

	@JsonProperty("case-type-full-name")
	private String case_type_full_name;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHigh_court_name() {
		return high_court_name;
	}

	public void setHigh_court_name(String high_court_name) {
		this.high_court_name = high_court_name;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getCase_type() {
		return case_type;
	}

	public void setCase_type(String case_type) {
		this.case_type = case_type;
	}

	public String getCase_no() {
		return case_no;
	}

	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}

	public String getName_of_petitioner() {
		return name_of_petitioner;
	}

	public void setName_of_petitioner(String name_of_petitioner) {
		this.name_of_petitioner = name_of_petitioner;
	}

	public String getName_of_respondent() {
		return name_of_respondent;
	}

	public void setName_of_respondent(String name_of_respondent) {
		this.name_of_respondent = name_of_respondent;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getNameOfRespondent() {
		return nameOfRespondent;
	}

	public void setNameOfRespondent(String nameOfRespondent) {
		this.nameOfRespondent = nameOfRespondent;
	}

	public String getHighCourtName() {
		return highCourtName;
	}

	public void setHighCourtName(String highCourtName) {
		this.highCourtName = highCourtName;
	}

	public String getLinkOfCase() {
		return linkOfCase;
	}

	public void setLinkOfCase(String linkOfCase) {
		this.linkOfCase = linkOfCase;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getLink_of_case() {
		return link_of_case;
	}

	public void setLink_of_case(String link_of_case) {
		this.link_of_case = link_of_case;
	}

	public String getCase_type_full_name() {
		return case_type_full_name;
	}

	public void setCase_type_full_name(String case_type_full_name) {
		this.case_type_full_name = case_type_full_name;
	}

}
