package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ProposalDetailsRepository extends JpaRepository<ProposalDetails,Long>{

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and fpProductId=:fpProductId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchIdAndFpProductId(@Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchId(@Param("branchId") Long branchId);

    @Query("SELECT count(pd)  FROM ProposalDetails pd WHERE pd.userOrgId =:userOrgId and pd.applicationId =:applicationId  and isActive = 1")
    public Long getApplicationIdCountByOrgId(@Param("applicationId") Long applicationId,@Param("userOrgId") Long userOrgId);
    
    @Query(value = "SELECT pd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, pd.created_date, pd.branch_id, pd.el_amount, pd.el_tenure, pd.el_roi, pd.emi, pd.processing_fee, branch.name as branchname FROM `loan_application`.`proposal_details` pd, `connect`.`connect_log` cl, `users`.`users` usr, `users`.`fund_seeker_details` fs, `users`.`branch_master` branch WHERE pd.user_org_id =:userOrgId and cl.application_id = pd.application_id and usr.user_id = cl.user_id and usr.user_type_id = 1 and fs.user_id = usr.user_id and branch.id = pd.branch_id and (pd.created_date BETWEEN :fromDate and :toDate) ORDER BY pd.id DESC", nativeQuery = true)
    public List<Object[]> getProposalDetailsByOrgId(@Param("userOrgId")Long userOrgId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

}
