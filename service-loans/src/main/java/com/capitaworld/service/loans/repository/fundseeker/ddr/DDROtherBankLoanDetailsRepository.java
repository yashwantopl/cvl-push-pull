package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROtherBankLoanDetails;

public interface DDROtherBankLoanDetailsRepository extends JpaRepository<DDROtherBankLoanDetails, Long>{

	@Query("select * from DDROtherBankLoanDetails where id =:id and isActive = true")
	public DDROtherBankLoanDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select * from DDROtherBankLoanDetails where ddrFormId =:ddrFormId and isActive = true")
	public List<DDROtherBankLoanDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
	
}
