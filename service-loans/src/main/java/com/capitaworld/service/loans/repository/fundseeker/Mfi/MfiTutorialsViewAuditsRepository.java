package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MFITutorialsViewAudits;

public interface MfiTutorialsViewAuditsRepository extends JpaRepository<MFITutorialsViewAudits,Long> {

	@Query(value = "SELECT a.id as id,u.email as userName,r.role_name as roleName,b.name as branchName,a.view_date as viewDate FROM `loan_application`.tutorial_view_audit a LEFT JOIN users.`users` u ON u.user_id = a.user_id LEFT JOIN users.`user_role_master` r ON r.role_id = a.role_id LEFT JOIN users.`branch_master` b ON u.branch_id=b.id  WHERE a.tutorial_id =:tutorialId \n#pageable\n",nativeQuery = true)
	public List<Object[]> getTutorialListData(@Param("tutorialId") Long tutorialId,Pageable pageable);
	
	@Query(value = "SELECT CAST(JSON_ARRAYAGG(JSON) AS CHAR)  FROM ( SELECT JSON_OBJECT  \n" + 
					" ('id', a.id  , 'userName', u.email ,'roleName',  r.role_name , 'branchName' , b.name , 'viewDate' , a.view_date )\n" + 
					" AS JSON    FROM  `loan_application`.tutorial_view_audit a LEFT JOIN users.`users` u ON u.user_id = a.user_id \n" + 
					" LEFT JOIN users.`user_role_master` r ON r.role_id = a.role_id \n" + 
					" LEFT JOIN users.`branch_master` b ON u.branch_id=b.id  WHERE a.tutorial_id =:tutorialId ) AS main ",nativeQuery = true)
	public String getTutorialAuditList(@Param("tutorialId") Long tutorialId);
	
	@Query(value = "SELECT a.id FROM `loan_application`.tutorial_view_audit a LEFT JOIN users.`users` u ON u.user_id = a.user_id LEFT JOIN users.`user_role_master` r ON r.role_id = a.role_id LEFT JOIN users.`branch_master` b ON u.branch_id=b.id  WHERE a.tutorial_id =:tutorialId",nativeQuery = true)
	public List<BigInteger> getTutorialCount(@Param("tutorialId") Long tutorialId);

}
