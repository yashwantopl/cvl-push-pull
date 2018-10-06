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
	
	@Query(value = "SELECT ipd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, ipd.created_date, ipd.branch_id, branch.name as branchname, branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, lam.application_code, branch.code, branch.street_name, (select state_name from `one_form`.`state` s where s.id = branch.state_id), (select city_name from `one_form`.`city` c where c.id = branch.city_id), branch.premises_no, branch.contact_person_email "
			+ "FROM `loan_application`.`ineligible_proposal_details` ipd,`loan_application`.`proposal_details` pd, `connect`.`connect_log` cl, `users`.`users` usr, `users`.`fund_seeker_details` fs, `users`.`branch_master` branch, `users`.`user_organisation_master` org, `loan_application`.`fs_loan_application_master` lam "
			+ "WHERE pd.user_org_id =:userOrgId and cl.application_id = ipd.application_id and usr.user_id = cl.user_id and usr.user_type_id = 1 and fs.user_id = usr.user_id and branch.id = ipd.branch_id and lam.application_id = ipd.application_id and org.user_org_id = :userOrgId and (ipd.created_date BETWEEN :fromDate and :toDate) GROUP BY ipd.application_id ORDER BY ipd.id DESC", nativeQuery = true)
    public List<Object[]> getOfflineProposalDetailsByOrgId(@Param("userOrgId")Long userOrgId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

}
