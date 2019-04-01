package com.capitaworld.service.loans.repository.fundseeker;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by KushalCW on 22-09-2018.
 */

public interface IneligibleProposalDetailsRepository extends JpaRepository<IneligibleProposalDetails,Long> {
	
	@Query(value = "SELECT ipd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, ipd.created_date, ipd.branch_id, branch.name AS branchname, branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, lam.application_code, branch.code, branch.street_name, (SELECT state_name FROM `one_form`.`state` s WHERE s.id = branch.state_id), (SELECT city_name FROM `one_form`.`city` c WHERE c.id = branch.city_id), branch.premises_no, branch.contact_person_email\n" + 
			"			FROM `loan_application`.`ineligible_proposal_details` ipd \n" + 
			"			LEFT JOIN `loan_application`.`proposal_details` pd ON  pd.user_org_id = ipd.user_org_id \n" + 
			"			LEFT JOIN `connect`.`connect_log` cl ON cl.application_id = ipd.application_id \n" + 
			"			LEFT JOIN `users`.`users` usr ON usr.user_id = cl.user_id\n" + 
			"			LEFT JOIN `users`.`fund_seeker_details` fs ON fs.user_id = usr.user_id\n" + 
			"			LEFT JOIN `users`.`branch_master` branch ON branch.id = ipd.branch_id\n" + 
			"			LEFT JOIN `users`.`user_organisation_master` org ON org.user_org_id = ipd.user_org_id\n" + 
			"			LEFT JOIN `loan_application`.`fs_loan_application_master` lam ON lam.application_id = ipd.application_id\n" + 
			"			WHERE pd.user_org_id = :userOrgId AND usr.user_id = cl.user_id AND usr.user_type_id = 1 AND org.user_org_id = :userOrgId and (ipd.created_date BETWEEN :fromDate and :toDate) GROUP BY ipd.application_id ORDER BY ipd.id DESC", nativeQuery = true)
    public List<Object[]> getOfflineProposalDetailsByOrgId(@Param("userOrgId")Long userOrgId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

    public IneligibleProposalDetails findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);

    public IneligibleProposalDetails findByApplicationIdAndUserOrgIdAndIsActive(Long applicationId,Long userOrgId,Boolean isActive);

    public List<IneligibleProposalDetails> findByGstinAndIsActive(String gstin,Boolean isActive);

    @Query(value = "SELECT * FROM loan_application.`ineligible_proposal_details` inl WHERE SUBSTR(inl.gstin,3,10) =:gstin AND inl.`is_active` = TRUE", nativeQuery = true)
    public List<IneligibleProposalDetails> findByGstinPan(@Param("gstin")String gstin);

    public IneligibleProposalDetails findByGstinAndUserOrgIdAndIsActive(String gstin,Long userOrgId,Boolean isActive);

	@Query(value = "SELECT * FROM loan_application.ineligible_proposal_details WHERE application_id=:applicationId AND is_active=TRUE AND (is_sanctioned = TRUE OR is_disbursed = TRUE)", nativeQuery = true)
    public IneligibleProposalDetails getSanctionedByApplicationId(@Param("applicationId")Long applicationId);

}
