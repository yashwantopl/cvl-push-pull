/**
 * 
 */
package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq;


/**
 * @author harsukh.ghumaliya
 *
 */
public interface MfiIncomeDetailsRepository extends JpaRepository<MfiIncomeDetails,Long> {
	
	@Query("select new com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq(fn.applicationId,fn.occupation,fn.netIncome,fn.frequencyIncome,fn.monthlyIncome,fn.yearlyIncome,fn.relationId) from MfiIncomeDetails fn where fn.applicationId = :appId")
	public List<MfiIncomeDetailsReq> findIncomeDetailsByAppId(@Param("appId") Long appId);
	
}
