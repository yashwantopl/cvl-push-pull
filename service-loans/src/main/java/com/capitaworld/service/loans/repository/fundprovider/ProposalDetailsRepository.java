
package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProposalDetailsRepository extends JpaRepository<ProposalDetails,Long>{

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and fpProductId=:fpProductId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchIdAndFpProductId(@Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchId(@Param("branchId") Long branchId);

    @Query("SELECT count(pd)  FROM ProposalDetails pd WHERE pd.userOrgId =:userOrgId and pd.applicationId =:applicationId  and isActive = 1")
    public Long getApplicationIdCountByOrgId(@Param("applicationId") Long applicationId,@Param("userOrgId") Long userOrgId);
    
    @Query(value = "select count(id) from loan_application.proposal_details pd where pd.fp_product_id=:fp_product_id and pd.is_active=1 and pd.application_id in (select application_id from fs_loan_application_master where is_active=1)", nativeQuery = true)
    public Long getProposalCountByFpProductId(@Param("fp_product_id") Long fpProductId);

    @Query(value = "select count(id) from loan_application.proposal_details pd where pd.fp_product_id=:fp_product_id and pd.branch_id=:branchId and pd.is_active=1 and pd.application_id in (select application_id from fs_loan_application_master where is_active=1)", nativeQuery = true)
    public Long getProposalCountByFpProductIdAndBranchId(@Param("fp_product_id") Long fpProductId,@Param("branchId") Long branchId);

    @Query(value = "select count(id) from loan_application.proposal_details pd where pd.fp_product_id=:fp_product_id and pd.branch_id IN :branchIdList and pd.is_active=1 and pd.application_id in (select application_id from fs_loan_application_master where is_active=1)", nativeQuery = true)
    public Long getProposalCountByFpProductIdAndBranchId(@Param("fp_product_id") Long fpProductId,@Param("branchIdList") List<Long> branchIdList);
    
    @Query(value = "SELECT COUNT(pd.id) FROM loan_application.proposal_details AS pd JOIN loan_application.fs_loan_application_master AS la ON la.application_id = pd.application_id AND la.is_active = TRUE JOIN users.users AS u ON u.branch_id = pd.branch_id WHERE pd.fp_product_id =:fp_product_id AND pd.is_active=TRUE AND u.user_id =:user_id", nativeQuery = true)
    public Long getProposalCountByUserIdAndFpProductId(@Param("fp_product_id") Long fpProductId,@Param("user_id") Long userId);
    
    @Query(value = "SELECT pd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, pd.created_date, pd.branch_id, \n" + 
    		"pd.el_amount, pd.el_tenure, pd.el_roi, pd.emi, pd.processing_fee, branch.name AS branchname, \n" + 
    		"branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, \n" + 
    		"lam.application_code, branch.code, branch.street_name, (SELECT state_name FROM `one_form`.`state` s \n" + 
    		"WHERE s.id = branch.state_id), (SELECT city_name FROM `one_form`.`city` c WHERE c.id = branch.city_id), branch.premises_no, \n" + 
    		"(SELECT product_id FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = pd.fp_product_id), branch.contact_person_email, \n" + 
    		"(SELECT COUNT(id) FROM `users`.`campaign_details` cd WHERE cd.user_id = cl.user_id),cl.`gstin` \n" + 
    		"FROM  `loan_application`.`proposal_details` pd \n" + 
    		"LEFT JOIN `connect`.`connect_log` cl \n" + 
    		"ON cl.application_id = pd.application_id \n" + 
    		"LEFT JOIN `users`.`users` usr \n" + 
    		"ON usr.user_id = cl.user_id \n" + 
    		"LEFT JOIN `users`.`fund_seeker_details` fs \n" + 
    		"ON fs.user_id = usr.user_id \n" + 
    		"LEFT JOIN  `users`.`branch_master` branch \n" + 
    		"ON branch.id = pd.branch_id \n" + 
    		"LEFT JOIN `users`.`user_organisation_master` org \n" + 
    		"ON org.user_org_id = pd.user_org_id \n" + 
    		"LEFT JOIN `loan_application`.`fs_loan_application_master` lam \n" + 
    		"ON lam.application_id = pd.application_id \n" + 
    		"WHERE pd.user_org_id = :userOrgId AND usr.user_type_id = 1 AND pd.is_active = TRUE AND cl.stage_id > 6 AND cl.stage_id != 8 \n" + 
    		"and (pd.created_date BETWEEN :fromDate and :toDate) ORDER BY pd.id DESC;", nativeQuery = true)
    public List<Object[]> getProposalDetailsByOrgId(@Param("userOrgId")Long userOrgId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
    
    
    @Query(value="SELECT DATE_FORMAT(pd.modified_date, '%d/%m/%Y') AS modified_date, proposal_status_id, psm.code FROM proposal_details AS pd JOIN proposal_status_master AS psm ON psm.id = pd.proposal_status_id WHERE application_id = :applicationId AND pd.is_active = TRUE",nativeQuery=true)
    public List<Object[]> findProposalDetailByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value="SELECT fp_product_id FROM proposal_details WHERE application_id =:applicationId  AND is_active = TRUE LIMIT 1",nativeQuery= true)
    public Long getFpProductIdByApplicationId(@Param("applicationId") Long applicationId);
    
    @Modifying 
    @Query("UPDATE ProposalDetails set proposalStatusId.id =:statuId , modifiedDate = now()  WHERE  applicationId =:applicationIl̥d AND isActive = true " )
    public Integer updateSanctionStatus(@Param("statuId") Long statuId  , @Param("applicationId") Long applicationId);
    
    @Modifying 
    @Query("UPDATE ProposalDetails set proposalStatusId.id =:statuId , modifiedDate = now(),reason =:remarks  WHERE  applicationId =:applicationId AND isActive = true and fpProductId =:fpProductId")
    public Integer updateStatus(@Param("statuId") Long statuId  , @Param("applicationId") Long applicationId, @Param("fpProductId") Long fpProductId,@Param("remarks") String remarks);
    
    @Query(value = "SELECT lm.id,cap.organisationName,lm.applicationCode,lm.businessTypeId from ProposalDetails pd,CorporateApplicantDetail cap,LoanApplicationMaster lm where pd.fpProductId =:fpProductId and pd.proposalStatusId.id =:proposalStatusId and pd.isActive = true and pd.branchId =:branchId and cap.applicationId.id = pd.applicationId and cap.applicationId.id = pd.applicationId and cap.applicationId.id = lm.id")
    public List<Object[]> getAllProposalsForSearchWithBranch(@Param("fpProductId") Long fpProductId,@Param("proposalStatusId") Long proposalStatusId,@Param("branchId") Long branchId);

    @Query(value = "SELECT lm.id,cap.organisationName,lm.applicationCode,lm.businessTypeId from ProposalDetails pd,CorporateApplicantDetail cap,LoanApplicationMaster lm where pd.fpProductId =:fpProductId and pd.proposalStatusId.id =:proposalStatusId and pd.branchId IN :branchIdList and pd.isActive = true and cap.applicationId.id = pd.applicationId and cap.applicationId.id = pd.applicationId and cap.applicationId.id = lm.id")
    public List<Object[]> getAllProposalsForSearchWithBranch(@Param("fpProductId") Long fpProductId,@Param("proposalStatusId") Long proposalStatusId,@Param("branchIdList") List<Long> branchIdList);
    
    @Query(value = "SELECT lm.id,cap.organisationName,lm.applicationCode,lm.businessTypeId from ProposalDetails pd,CorporateApplicantDetail cap,LoanApplicationMaster lm where pd.fpProductId =:fpProductId and pd.proposalStatusId.id =:proposalStatusId and pd.isActive = true and cap.applicationId.id = pd.applicationId and lm.id = pd.applicationId and cap.applicationId.id = lm.id")
    public List<Object[]> getAllProposalsForSearch(@Param("fpProductId") Long fpProductId,@Param("proposalStatusId") Long proposalStatusId);
}

