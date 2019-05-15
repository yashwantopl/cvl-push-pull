package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.fundprovider.LoanPurposeAmountMapping;

@Repository
public interface LoanPurposeAmountMappingRepository extends JpaRepository<LoanPurposeAmountMapping, Long>{

	@Modifying
	@Query("delete from LoanPurposeAmountMapping lpm where lpm.fpProductId =:fpProductId")
	public int deleteExistingMapping(@Param("fpProductId") Long fpProductId);
	
	public List<LoanPurposeAmountMapping> findByFpProductId(Long fpProductId);
	
	public LoanPurposeAmountMapping findById(Long id);
	
}
