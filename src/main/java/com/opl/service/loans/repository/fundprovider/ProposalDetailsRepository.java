
package com.opl.service.loans.repository.fundprovider;
import java.math.BigInteger;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;

import com.opl.service.loans.domain.fundprovider.ProposalDetails;

public interface ProposalDetailsRepository extends JpaRepository<ProposalDetails,Long>{
	
	public ProposalDetails findFirstByApplicationIdAndIsActiveAndNbfcFlowOrderByIdDesc(Long applicationId, Boolean isActive ,Integer nbfcFlow);
	
	public ProposalDetails findFirstByApplicationIdAndIsActiveOrderByIdDesc(Long applicationId, Boolean isActive);

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and fpProductId=:fpProductId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchIdAndFpProductId(@Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchId(@Param("branchId") Long branchId);

    @Query("SELECT count(pd)  FROM ProposalDetails pd WHERE pd.userOrgId =:userOrgId and pd.applicationId =:applicationId  and isActive = 1")
    public Long getApplicationIdCountByOrgId(@Param("applicationId") Long applicationId,@Param("userOrgId") Long userOrgId);

    @Query(value = "select count(id) from proposal_details pd where pd.fp_product_id=:fp_product_id and pd.is_active=1 and pd.application_id in (select application_id from fs_loan_application_master where is_active=1)", nativeQuery = true)
    public Long getProposalCountByFpProductId(@Param("fp_product_id") Long fpProductId);

    @Query(value = "select count(id) from proposal_details pd where pd.fp_product_id=:fp_product_id and pd.branch_id=:branchId and pd.is_active=1 and pd.application_id in (select application_id from fs_loan_application_master where is_active=1)", nativeQuery = true)
    public Long getProposalCountByFpProductIdAndBranchId(@Param("fp_product_id") Long fpProductId,@Param("branchId") Long branchId);

    @Query(value = "select count(id) from proposal_details pd where pd.fp_product_id=:fp_product_id and pd.branch_id IN :branchIdList and pd.is_active=1 and pd.application_id in (select application_id from fs_loan_application_master where is_active=1)", nativeQuery = true)
    public Long getProposalCountByFpProductIdAndBranchId(@Param("fp_product_id") Long fpProductId,@Param("branchIdList") List<Long> branchIdList);


    @Query(value = "SELECT COUNT(pd.id) FROM proposal_details AS pd JOIN fs_loan_application_master AS la ON la.application_id = pd.application_id AND la.is_active = TRUE JOIN users.users AS u ON u.branch_id = pd.branch_id WHERE pd.fp_product_id =:fp_product_id AND pd.is_active=TRUE AND u.user_id =:user_id", nativeQuery = true)
    public Long getProposalCountByUserIdAndFpProductId(@Param("fp_product_id") Long fpProductId,@Param("user_id") Long userId);

    @Query(value = "SELECT pd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, pd.created_date, pd.branch_id, \n" +
            "pd.el_amount, pd.el_tenure, pd.el_roi, pd.emi, pd.processing_fee, branch.name AS branchname, \n" +
            "branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, \n" +
            "lam.application_code, branch.code, branch.street_name, (SELECT state_name FROM `one_form_mudra`.`state` s \n" +
            "WHERE s.id = branch.state_id), (SELECT city_name FROM `one_form_mudra`.`city` c WHERE c.id = branch.city_id), branch.premises_no, \n" +
            "(SELECT product_id FROM `loan_application_mudra`.`fp_product_master` pm WHERE pm.fp_product_id = pd.fp_product_id), branch.contact_person_email, \n" +
            "(SELECT COUNT(id) FROM `users`.`campaign_details` cd WHERE cd.user_id = cl.user_id),cl.`gstin` \n" +
            "FROM  `loan_application_mudra`.`proposal_details` pd \n" +
            "LEFT JOIN `connect_mudra`.`connect_log` cl \n" +
            "ON cl.application_id = pd.application_id \n" +
            "LEFT JOIN `users`.`users` usr \n" +
            "ON usr.user_id = cl.user_id \n" +
            "LEFT JOIN `users`.`fund_seeker_details` fs \n" +
            "ON fs.user_id = usr.user_id \n" +
            "LEFT JOIN  `users`.`branch_master` branch \n" +
            "ON branch.id = pd.branch_id \n" +
            "LEFT JOIN `users`.`user_organisation_master` org \n" +
            "ON org.user_org_id = pd.user_org_id \n" +
            "LEFT JOIN `fs_loan_application_master` lam \n" +
            "ON lam.application_id = pd.application_id \n" +
            "WHERE pd.user_org_id = :userOrgId AND usr.user_type_id = 1 AND pd.is_active = TRUE AND cl.stage_id > 6 AND cl.stage_id != 8 \n" +
            "and (pd.created_date BETWEEN :fromDate and :toDate) ORDER BY pd.id DESC;", nativeQuery = true)
    public List<Object[]> getProposalDetailsByOrgId(@Param("userOrgId")Long userOrgId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);


    @Query(value="SELECT DATE_FORMAT(pd.modified_date, '%d/%m/%Y') AS modified_date, proposal_status_id, psm.code, branch_id FROM proposal_details AS pd JOIN proposal_status_master AS psm ON psm.id = pd.proposal_status_id WHERE application_id = :applicationId AND pd.is_active = TRUE",nativeQuery=true)
    public List<Object[]> findProposalDetailByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value="SELECT fp_product_id FROM proposal_details WHERE application_id =:applicationId  AND is_active = TRUE LIMIT 1",nativeQuery= true)
    public Long getFpProductIdByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value="SELECT fp_product_id FROM proposal_details WHERE application_id =:applicationId AND id =:proposalId AND is_active = TRUE LIMIT 1",nativeQuery= true)
    public Long getFpProductIdByApplicationIdAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);

    @Modifying
    @Query("UPDATE ProposalDetails set proposalStatusId.id =:statuId , modifiedDate = now()  WHERE  applicationId =:applicationId AND isActive = true " )
    public Integer updateSanctionStatus(@Param("statuId") Long statuId  , @Param("applicationId") Long applicationId);

    @Modifying
    @Query("UPDATE ProposalDetails set proposalStatusId.id =:statuId , modifiedDate = now(),reason =:remarks  WHERE  applicationId =:applicationId AND isActive = true and fpProductId =:fpProductId")
    public Integer updateStatus(@Param("statuId") Long statuId  , @Param("applicationId") Long applicationId, @Param("fpProductId") Long fpProductId,@Param("remarks") String remarks);

    @Query(value = "SELECT lm.applicationId,cap.organisationName,lm.applicationCode,lm.businessTypeId from ProposalDetails pd,CorporateApplicantDetail cap,ApplicationProposalMapping lm where pd.fpProductId =:fpProductId and pd.proposalStatusId.id =:proposalStatusId and pd.isActive = true and pd.branchId =:branchId and cap.applicationId.id = pd.applicationId and cap.applicationId.id = pd.applicationId and cap.applicationId.id = lm.applicationId and cap.applicationProposalMapping is null")
    public List<Object[]> getAllProposalsForSearchWithBranch(@Param("fpProductId") Long fpProductId,@Param("proposalStatusId") Long proposalStatusId,@Param("branchId") Long branchId);

    @Query(value = "SELECT pd.application_id,fcd.organisation_name,apm.application_code,apm.business_type_id FROM proposal_details pd " +
            "INNER JOIN fs_corporate_applicant_details fcd " +
            "INNER JOIN application_proposal_mapping apm " +
            "WHERE pd.is_active =TRUE AND pd.branch_id=:branchId " +
            "AND pd.fp_product_id=:fpProductId " +
            "AND pd.application_id IN (SELECT application_id FROM proposal_details WHERE proposal_status_id IN (5,11,13) AND user_org_id <>:userOrgId) " +
            "AND pd.application_id = fcd.application_id " +
            "AND pd.id = apm.proposal_id " +
            "AND fcd.proposal_mapping_id = apm.proposal_id",nativeQuery = true)
    public List<Object[]> getSanctionByOtherBankProposalsForSearchWithBranch(@Param("fpProductId") Long fpProductId, @Param("branchId") Long branchId, @Param("userOrgId") Long userOrgId);

    @Query(value = "SELECT pd.application_id,fcd.organisation_name,apm.application_code,apm.business_type_id FROM proposal_details pd " +
            "INNER JOIN fs_corporate_applicant_details fcd " +
            "INNER JOIN application_proposal_mapping apm " +
            "WHERE pd.is_active =TRUE " +
            "AND pd.fp_product_id=:fpProductId " +
            "AND pd.application_id IN (SELECT application_id FROM proposal_details WHERE proposal_status_id IN (5,11,13) AND user_org_id <>:userOrgId) " +
            "AND pd.application_id = fcd.application_id " +
            "AND pd.id = apm.proposal_id " +
            "AND fcd.proposal_mapping_id = apm.proposal_id",nativeQuery = true)
    public List<Object[]> getAllSanctionByOtherBankProposalsForSearch(@Param("fpProductId") Long fpProductId,@Param("userOrgId") Long userOrgId);

    @Query(value = "SELECT lm.applicationId,cap.organisationName,lm.applicationCode,lm.businessTypeId from ProposalDetails pd,CorporateApplicantDetail cap,ApplicationProposalMapping lm where pd.fpProductId =:fpProductId and pd.proposalStatusId.id =:proposalStatusId and pd.branchId IN :branchIdList and pd.isActive = true and cap.applicationId.id = pd.applicationId and cap.applicationId.id = pd.applicationId and cap.applicationId.id = lm.applicationId and cap.applicationProposalMapping is null")
    public List<Object[]> getAllProposalsForSearchWithBranch(@Param("fpProductId") Long fpProductId,@Param("proposalStatusId") Long proposalStatusId,@Param("branchIdList") List<Long> branchIdList);

    @Query(value = "SELECT lm.applicationId,cap.organisationName,lm.applicationCode,lm.businessTypeId from ProposalDetails pd,CorporateApplicantDetail cap,ApplicationProposalMapping lm where pd.fpProductId =:fpProductId and pd.proposalStatusId.id =:proposalStatusId and pd.isActive = true and cap.applicationId.id = pd.applicationId and lm.id = pd.applicationId and cap.applicationId.id = lm.applicationId and cap.applicationProposalMapping is null")
    public List<Object[]> getAllProposalsForSearch(@Param("fpProductId") Long fpProductId,@Param("proposalStatusId") Long proposalStatusId);


    @Query("SELECT pd.id FROM ProposalDetails pd WHERE pd.userOrgId =:userOrgId and pd.applicationId =:applicationId  and isActive = 1")
    public Long findByApplicationIdAndUserOrgId(@Param("userOrgId") Long userOrgId  , @Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM proposal_details pd WHERE application_id =:applicationId  ORDER BY id desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getLastProposalByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM proposal_details pd WHERE application_id =:applicationId and pd.proposal_status_id In(5,11,13) and pd.nbfc_flow IS NULL ORDER BY pd.modified_date desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getSanctionProposalByApplicationId(@Param("applicationId") Long applicationId);

    public List<ProposalDetails> findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);
    
    @Query(value = "SELECT * FROM proposal_details pd WHERE application_id =:applicationId and pd.is_active=:isActive and (pd.is_offline is null or  pd.is_offline=:isOffline)",nativeQuery = true)
    public List<ProposalDetails> findByApplicationIdAndIsActiveForMBOnline(@Param("applicationId")Long applicationId,@Param("isActive") Boolean isActive,@Param("isOffline")Boolean isOffline);

    @Query("SELECT pd.userOrgId FROM ProposalDetails pd WHERE pd.id =:proposalId")
    public Long getOrgIdByProposalId(@Param("proposalId") Long proposalId);

    @Query(value = "SELECT * FROM proposal_details as  pd WHERE pd.application_id =:applicationId and pd.proposal_status_id In(5,11,13) ORDER BY pd.modified_date desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getProposalId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM proposal_details as  pd WHERE pd.id =:proposalId ORDER BY pd.modified_date desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getProposalByProposalId(@Param("proposalId") Long proposalId);

    @Query("SELECT COUNT(p.applicationId) FROM ProposalDetails p WHERE p.applicationId=:applicationId")
    public Integer getCountOfProposalDetailsByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM proposal_details pd WHERE application_id =:applicationId and pd.proposal_status_id In(5,11,13) AND user_org_id <>:userOrgId ORDER BY pd.modified_date desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getSanctionProposalByApplicationIdAndUserOrgId(@Param("applicationId") Long applicationId,@Param("userOrgId") Long userOrgId);

    @Query(value = "SELECT * FROM proposal_details as  pd WHERE pd.application_id =:applicationId and is_active=true",nativeQuery = true)
    public ProposalDetails getByApplicationIdAndFPProductId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM proposal_details as  pd WHERE pd.application_id =:applicationId and fp_product_id=:fpProductId and is_active=true",nativeQuery = true)
    public ProposalDetails getByApplicationIdAndFPProductId(@Param("applicationId") Long applicationId,@Param("fpProductId") Long fpProductId);

    @Query(value = "SELECT p.application_id FROM proposal_details p WHERE p.proposal_status_id=:proposalStatus AND p.application_id IN (SELECT lm.application_id FROM fs_loan_application_master lm WHERE lm.business_type_id = 6 AND STATUS>4 AND np_org_id=:npOrgId)",nativeQuery = true)
    public List<BigInteger> getProposalsByProposalStatusAndBusinessTypeId(@Param("proposalStatus") Long proposalStatus,@Param("npOrgId") Long npOrgId);

    @Query(value = "SELECT p.application_id FROM proposal_details p WHERE p.proposal_status_id=:proposalStatus AND p.application_id IN (SELECT lm.application_id FROM fs_loan_application_master lm WHERE lm.business_type_id = 6 AND STATUS>4)",nativeQuery = true)
    public List<BigInteger> getProposalsByProposalStatusAndBusinessTypeIdForSidbi(@Param("proposalStatus") Long proposalStatus);

    @Query(value = "SELECT p.application_id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.application_id IN (SELECT lm.application_id FROM fs_loan_application_master lm WHERE lm.business_type_id = 6 AND STATUS>4 AND np_org_id=:npOrgId)",nativeQuery = true)
    public List<BigInteger> getProposalsByProposalStatusListAndBusinessTypeId(@Param("proposalStatus") List<Long> proposalStatus,@Param("npOrgId") Long npOrgId);

    @Query(value = "SELECT p.application_id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.application_id IN (SELECT lm.application_id FROM fs_loan_application_master lm WHERE lm.business_type_id = 6 AND STATUS>4)",nativeQuery = true)
    public List<BigInteger> getProposalsByProposalStatusListAndBusinessTypeIdForSidbi(@Param("proposalStatus") List<Long> proposalStatus);

    @Query(value="SELECT display_org_name FROM `users`.`user_organisation_master` WHERE user_org_id =:id",nativeQuery= true)
    public String getOrgNameById(@Param("id") Long id);

    public List<ProposalDetails> findByApplicationId(Long applicationId);

    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow and p.is_active=true and p.branch_id=:branchId",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgId(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow") Integer nbfcFlow, @Param("branchId") Long branchId);

    // Count for NBFC (sanction, disbursed by both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgId(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2, @Param("branchId") Long branchId);

    // Count for NBFC (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow_1 and p.is_active=true and p.branch_id=:branchId AND p.application_id NOT IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdNBFC(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("nbfcFlow_2") Integer nbfcFlow_2, @Param("branchId") Long branchId);

    @Query(value="SELECT * FROM proposal_details WHERE application_id =:applicationId AND id =:proposalId AND is_active = TRUE LIMIT 1",nativeQuery= true)
    public ProposalDetails getBranchId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);

    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow and p.is_active=true and p.branch_id=:branchId limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow") Integer nbfcFlow, @Param("branchId") Long branchId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // In-principle bank
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2, @Param("branchId") Long branchId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Proposal List for NBFC (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow_1 and p.is_active=true and p.branch_id=:branchId AND p.application_id NOT IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalListByStatusIdAndUserOrgIdPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("branchId") Long branchId, @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Proposal List for NBFC (sanction, disbursed by Both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) LIMIT :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalsByStatusIdAndUserOrgIdPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("branchId") Long branchId, @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Count for Bank (In principle,sanction, disbursed by both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdForBank(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2, @Param("branchId") Long branchId);

    // Count for Bank (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id <:proposalStatus AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdFNBFCorBank(@Param("proposalStatus") Long proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2, @Param("branchId") Long branchId);

    // Proposal List for BANK (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id <:proposalStatus AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalListByStatusIdAndUserOrgIdNBFCForBankPageable(@Param("proposalStatus") Long proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("branchId") Long branchId, @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Proposal List for BANK (sanction, disbursed by Both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.branch_id=:branchId AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) LIMIT :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalsByStatusIdAndUserOrgIdForBankPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("branchId") Long branchId, @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Count for HO
    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow and p.is_active=true",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdForHO(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow") Integer nbfcFlow);

    // Count for NBFC (sanction, disbursed by both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdFoHO(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2);

    // Count for NBFC (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow_1 and p.is_active=true AND p.application_id NOT IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdForHONBFC(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("nbfcFlow_2") Integer nbfcFlow_2);

    // Proposal List for NBFC HO(In principle)
    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow and p.is_active=true limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdForHOPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow") Integer nbfcFlow, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Proposal List for NBFC HO (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p where p.proposal_status_id IN (:proposalStatus) and p.user_org_id=:userOrgId and p.nbfc_flow=:nbfcFlow_1 and p.is_active=true AND p.application_id NOT IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalListByStatusIdAndUserOrgIdForHOPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Proposal List for NBFC HO (sanction, disbursed by Both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) LIMIT :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalsByStatusIdAndUserOrgIdForHOPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1, @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Count for Bank HO (In principle,sanction, disbursed by both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdForBankHO(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2);

    // Count for Bank HO (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id <:proposalStatus AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2)",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndUserOrgIdFNBFCorBankHO(@Param("proposalStatus") Long proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,@Param("nbfcFlow_2") Integer nbfcFlow_2);

    // Proposal List for BANK HO (sanction, disbursed by NBFC)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id <:proposalStatus AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) limit :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalListByStatusIdAndUserOrgIdNBFCForBankHOPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,  @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Proposal List for BANK HO (sanction, disbursed by Both)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.proposal_status_id IN (:proposalStatus) AND p.user_org_id=:userOrgId AND p.nbfc_flow=:nbfcFlow_1 AND p.is_active=TRUE AND p.application_id IN (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) AND pd.is_active=TRUE AND pd.nbfc_flow=:nbfcFlow_2) LIMIT :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalsByStatusIdAndUserOrgIdForBankHOPageable(@Param("proposalStatus") List<Long> proposalStatus, @Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,  @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @Query(value = "SELECT * FROM proposal_details pd WHERE application_id =:applicationId and pd.nbfc_flow =:nbfcFlow ORDER BY pd.modified_date desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getSanctionProposalByApplicationNBFCFlow(@Param("applicationId") Long applicationId,@Param("nbfcFlow") Integer nbfcFlow);

    @Query(value = "SELECT * FROM proposal_details pd WHERE application_id =:applicationId and pd.nbfc_flow =:nbfcFlow and pd.proposal_status_id =5 ORDER BY pd.modified_date desc LIMIT 1",nativeQuery = true)
    public ProposalDetails getSanctionProposalByApplicationBankFlow(@Param("applicationId") Long applicationId,@Param("nbfcFlow") Integer nbfcFlow);

    // Count for checker maker (HOLD REJECT proposal)    
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.is_active=TRUE and p.user_org_id=:userOrgId and p.branch_id=:branchId and nbfc_flow=:nbfcFlow_1 and p.application_id in (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) and pd.is_active=TRUE and (pd.nbfc_flow=:nbfcFlow_2 OR pd.nbfc_flow=:nbfcFlow_1))",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndNBFCFlowType(@Param("proposalStatus") List<Long> proposalStatus,@Param("userOrgId") Long userOrgId, @Param("branchId") Long branchId,@Param("nbfcFlow_1") Integer nbfcFlow_1,  @Param("nbfcFlow_2") Integer nbfcFlow_2);

    // Proposal List for checker maker (HOLD REJECTED)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.is_active=TRUE and p.user_org_id=:userOrgId and p.branch_id=:branchId and nbfc_flow=:nbfcFlow_1 and p.application_id in (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) and pd.is_active=TRUE and (pd.nbfc_flow=:nbfcFlow_2 OR pd.nbfc_flow=:nbfcFlow_1)) LIMIT :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalPageableByStatusIdAndNBFCFlowType(@Param("proposalStatus") List<Long> proposalStatus,@Param("userOrgId") Long userOrgId, @Param("branchId") Long branchId, @Param("nbfcFlow_1") Integer nbfcFlow_1,  @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    // Count for ho (HOLD REJECT proposal)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.is_active=TRUE and p.user_org_id=:userOrgId and nbfc_flow=:nbfcFlow_1 and p.application_id in (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) and pd.is_active=TRUE and (pd.nbfc_flow=:nbfcFlow_2 OR pd.nbfc_flow=:nbfcFlow_1))",nativeQuery = true)
    public List<BigInteger> getFPProposalCountByStatusIdAndNBFCFlowType(@Param("proposalStatus") List<Long> proposalStatus,@Param("userOrgId") Long userOrgId, @Param("nbfcFlow_1") Integer nbfcFlow_1,  @Param("nbfcFlow_2") Integer nbfcFlow_2);

    // Proposal List ho (HOLD REJECTED)
    @Query(value = "SELECT p.id FROM proposal_details p WHERE p.is_active=TRUE and p.user_org_id=:userOrgId and nbfc_flow=:nbfcFlow_1 and p.application_id in (SELECT pd.application_id FROM proposal_details pd WHERE pd.proposal_status_id IN (:proposalStatus) and pd.is_active=TRUE and (pd.nbfc_flow=:nbfcFlow_2 OR pd.nbfc_flow=:nbfcFlow_1)) LIMIT :pageIndex,:pageSize",nativeQuery = true)
    public List<BigInteger> getFPProposalPageableByStatusIdAndNBFCFlowType(@Param("proposalStatus") List<Long> proposalStatus,@Param("userOrgId") Long userOrgId,@Param("nbfcFlow_1") Integer nbfcFlow_1,  @Param("nbfcFlow_2") Integer nbfcFlow_2,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	@Transactional
	@Modifying
	@Query("delete from ProposalDetails pd where pd.id=:proposalId")
	public Integer deleteByProposalId(@Param("proposalId")Long proposalId);
	
	
	

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	@Modifying
	@Query(" UPDATE ProposalDetails pd set pd.isActive = :isActive , pd.modifiedDate = now() ,pd.paymentTypeId=:paymentTypeId where pd.applicationId = :applicationId AND pd.fpProductId = :fpProductId")
	public Integer activeProposalDetailStatusByAppIdAndFpProductId(@Param("isActive") Boolean isActive , @Param("applicationId") Long applicationId ,@Param("fpProductId")  Long fpProductId,@Param("paymentTypeId")  Long paymentTypeId  );
	
	@Query("select pd from ProposalDetails pd where pd.id = :proposalId and pd.applicationId = :applicationId and pd.isActive = true AND isOffline = false")
	public ProposalDetails findByProposalIdAndApplicationIdAndIsActiveTrue(@Param("proposalId") Long proposalId ,@Param("applicationId")Long applicationId);
	
	@Query("SELECT COUNT(p.applicationId) FROM ProposalDetails p WHERE p.applicationId=:applicationId and isActive = true AND isOffline = false")
    public Integer getCountOfProposalDetailsByApplicationIdAndIsActiveTrue(@Param("applicationId") Long applicationId);

    @Modifying //  For In-eligible proposals
    @Query(value="update proposal_details set is_active =:isActive where application_id =:applicationId AND is_offline = true", nativeQuery = true)
    public Integer updateInEligibleDataBasedonApplicationId(@Param("applicationId")Long applicationId,@Param("isActive")Boolean isActive);

    @Modifying //  For In-eligible proposals
    @Query(value="update connect_mudra.connect_log set proposal_id =:proposalId where application_id =:applicationId", nativeQuery = true)
    public Integer updateProposalId(@Param("applicationId")Long applicationId,@Param("proposalId")Long proposalId);

    @Modifying //  For In-eligible proposals
    @Query(value="update user_profile.profile_loan_mapping set proposal_id =:proposalId where application_id =:applicationId and business_type_id = 10", nativeQuery = true)
    public Integer updateProposalIdInProfile(@Param("applicationId")Long applicationId,@Param("proposalId")Long proposalId);

    @Query(value = "SELECT inl.user_org_id FROM `proposal_details` inl WHERE inl.application_id =:applicationId AND is_offline = true", nativeQuery = true)
    public Long getOrgId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT inl.old_user_org_id FROM `proposal_details` inl WHERE inl.application_id =:applicationId AND is_offline = true", nativeQuery = true)
    public Integer getOldUserOrgId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM proposal_details WHERE application_id=:applicationId AND is_active=TRUE AND (is_sanctioned = TRUE OR is_disbursed = TRUE) AND is_offline = true", nativeQuery = true)
    public ProposalDetails getSanctionedByApplicationId(@Param("applicationId")Long applicationId);

    @Query("SELECT p FROM ProposalDetails p WHERE p.applicationId=:applicationId and p.userOrgId=:orgId and isActive = true")
    public ProposalDetails findByAppliationIdAndOrgId(@Param("applicationId") Long applicationId,@Param("orgId") Long orgId);

    @Modifying
    @Query("delete from ProposalDetails where applicationId=:applicationId and fpProductId=:fpProductId")
    public Integer deleteByApplicationId(@Param("applicationId") Long applicationId, @Param("fpProductId") Long fpProductId);

    @Modifying
    @Query(value = "UPDATE sanction_detail set status =:status WHERE  application_id =:applicationId AND org_id =:orgId AND is_active = true;",nativeQuery = true)
    public int updateSanctionStatus(@Param("applicationId") Long applicationId, @Param("orgId") Long orgId, @Param("status") Integer status);

    @Query(value = "SELECT * FROM `proposal_details` inl WHERE SUBSTR(inl.gstin,3,10) =:gstin AND inl.`is_active` = TRUE and inl.is_offline = true", nativeQuery = true)
    public List<ProposalDetails> findByGstinPan(@Param("gstin")String gstin);

    @Query(value = "SELECT ipd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, ipd.created_date, ipd.branch_id, branch.name AS branchname, branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, lam.application_code, branch.code, branch.street_name, (SELECT state_name FROM `one_form`.`state` s WHERE s.id = branch.state_id), (SELECT city_name FROM `one_form`.`city` c WHERE c.id = branch.city_id), branch.premises_no, branch.contact_person_email\n" +
            "			FROM `proposal_details` ipd \n" +
            "			LEFT JOIN `connect_mudra`.`connect_log` cl ON cl.application_id = ipd.application_id \n" +
            "			LEFT JOIN `users`.`users` usr ON usr.user_id = cl.user_id\n" +
            "			LEFT JOIN `users`.`fund_seeker_details` fs ON fs.user_id = usr.user_id\n" +
            "			LEFT JOIN `users`.`branch_master` branch ON branch.id = ipd.branch_id\n" +
            "			LEFT JOIN `users`.`user_organisation_master` org ON org.user_org_id = ipd.user_org_id\n" +
            "			LEFT JOIN `fs_loan_application_master` lam ON lam.application_id = ipd.application_id\n" +
            "			WHERE ipd.user_org_id = :userOrgId AND usr.user_id = cl.user_id AND usr.user_type_id = 1 AND org.user_org_id = :userOrgId and " +
            "			(ipd.created_date BETWEEN :fromDate and :toDate) AND ipd.is_offline = true GROUP BY ipd.application_id ORDER BY ipd.id DESC", nativeQuery = true)
    public List<Object[]> getOfflineProposalDetailsByOrgId(@Param("userOrgId")Long userOrgId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    public ProposalDetails findFirstByApplicationIdAndUserOrgIdAndIsActiveOrderByIdDesc(Long applicationId,Long userOrgId,Boolean isActive);

    @Query("select pd from ProposalDetails pd where pd.applicationId=:applicationId and pd.isActive = true and pd.isOffline = true")
    public List<ProposalDetails> getOfflineProposalByApplicationId(@Param("applicationId") Long applicationId);
    
	
}


