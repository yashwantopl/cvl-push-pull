/**
 * 
 */
package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameterTemp;

/**
 * @author sanket
 *
 */
public interface WorkingCapitalParameterTempRepository extends JpaRepository<WorkingCapitalParameterTemp, Long> {
	
	@Query("select o from WorkingCapitalParameterTemp o where o.fpProductId =:fpProductId")
	public WorkingCapitalParameterTemp getworkingCapitalParameterTempByFpProductId(@Param("fpProductId")Long fpProductId); 
	
	@Query("select o from WorkingCapitalParameterTemp o where o.fpProductMappingId =:fpProductMappingId and isCopied=false")
	public WorkingCapitalParameterTemp getworkingCapitalParameterTempByFpProductMappingId(@Param("fpProductMappingId")Long fpProductId); 

}
