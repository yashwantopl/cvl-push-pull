/**
 * 
 */
package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.NtbTermLoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameterTemp;

/**
 * @author sanket
 *
 */
public interface NtbTermLoanParameterTempRepository extends JpaRepository<NtbTermLoanParameterTemp, Long> {
	
	@Query("select o from NtbTermLoanParameterTemp o where o.fpProductId.id =:fpProductId")
	public NtbTermLoanParameterTemp getNtbTermLoanParameterTempByFpProductId(@Param("fpProductId")Long fpProductId); 
	
	@Query("select o from NtbTermLoanParameterTemp o where o.fpProductMappingId =:fpProductMappingId and isCopied=false")
	public NtbTermLoanParameterTemp getNtbTermLoanParameterTempByFpProductMappingId(@Param("fpProductMappingId")Long fpProductId); 


}
