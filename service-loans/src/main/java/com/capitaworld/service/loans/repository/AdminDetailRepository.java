package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.BankCWAuditTrailDomain;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminDetailRepository extends JpaRepository<BankCWAuditTrailDomain, Long> {
    @Query(value = "SELECT usr.user_id,usr.email,usr.sign_up_date,usr.last_access_id FROM `users`.`users` usr WHERE usr.user_type_id = 1 ORDER BY usr.sign_up_date DESC \n#pageable\n",nativeQuery = true)
    public List<Object[]> usersList(Pageable pageable);

    @Query(value = "SELECT con.application_id,con.stage_id,con.modified_date,con.status,con.message FROM `connect`.`connect_log` con WHERE con.application_id=:applicationId",nativeQuery = true)
    public Object[] connectList(@Param("applicationId")Long applicationId);

    @Query(value = "SELECT prop.fp_product_id,prop.user_org_id,prop.branch_id,prop.el_amount,prop.el_tenure,prop.el_roi,prop.application_id FROM `loan_application`.`proposal_details` prop WHERE prop.is_active = TRUE AND prop.application_id=:applicationId",nativeQuery = true)
    public Object[] proposalList(@Param("applicationId")Long applicationId);

    @Query(value = "SELECT prod.name FROM `loan_application`.`fp_product_master` prod WHERE prod.fp_product_id=:fpProductId AND prod.is_active = TRUE",nativeQuery = true)
    public String productList(@Param("fpProductId")Long fpProductId);

    @Query(value = "SELECT app.organisation_name FROM `loan_application`.`fs_corporate_applicant_details` app WHERE app.application_id=:applicationId",nativeQuery = true)
    public String applicantList(@Param("applicationId")Long applicationId);

    @Query(value = "SELECT branch.name,city.city_name,state.state_name FROM `users`.`branch_master` branch  \n" +
            "LEFT JOIN `users`.`location_master` loc ON branch.location_id = loc.id\n" +
            "LEFT JOIN `one_form`.`city` city ON city.id = loc.city_id\n" +
            "LEFT JOIN `one_form`.`state` state ON state.id = loc.state_id\n" +
            "WHERE branch.id=:branchId",nativeQuery = true)
    public Object[] branchList(@Param("branchId")Long branchId);

}
