/**
 * 
 */
package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq;


/**
 * @author harsukh.ghumaliya
 *
 */
public interface MfiIncomeDetailsRepository extends JpaRepository<MfiIncomeDetails,Long> {
	

	@Query("select new com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq(fn.id, fn.applicationId,fn.occupation,fn.netIncome,fn.frequencyIncome,fn.monthlyIncome,fn.yearlyIncome,fn.relationId,fn.type) from MfiIncomeDetails fn where fn.applicationId = :appId and fn.type =:type")
	public List<MfiIncomeDetailsReq> findIncomeDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);
	
}
