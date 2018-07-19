/**
 * 
 */
package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameterTemp;

/**
 * @author sanket
 *
 */
public interface TermLoanParameterTempRepository extends JpaRepository<TermLoanParameterTemp, Long> {
	
	@Query("select o from TermLoanParameterTemp o where o.fpProductId =:fpProductId")
	public TermLoanParameterTemp getTermLoanParameterTempByFpProductId(@Param("fpProductId")Long fpProductId); 
	
	@Query("select o from TermLoanParameterTemp o where o.fpProductMappingId =:fpProductMappingId and isCopied=false")
	public TermLoanParameterTemp getTermLoanParameterTempByFpProductMappingId(@Param("fpProductMappingId")Long fpProductId); 


}
