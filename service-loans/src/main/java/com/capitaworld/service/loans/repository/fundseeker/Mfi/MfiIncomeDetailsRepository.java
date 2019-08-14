/**
 * 
 */
package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq;


/**
 * @author harsukh.ghumaliya
 *
 */
public interface MfiIncomeDetailsRepository extends JpaRepository<MfiIncomeDetails,Long> {
	

	@Query("select new com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq(fn.id, fn.applicationId,fn.occupation,fn.frequencyIncome,fn.monthlyIncome,fn.yearlyIncome,fn.relationId,fn.type,fn.incomeDays) from MfiIncomeDetails fn where fn.applicationId = :appId and fn.type =:type and fn.isActive = true")
	public List<MfiIncomeDetailsReq> findIncomeDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);

	@Modifying
	@Query("update MfiIncomeDetails n set n.isActive = false where n.applicationId =:applicationId")
	public int inActiveMappingByAppId(@Param("applicationId") Long applicationId);
	
}
