/**
 * 
 */
package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;

/**
 * @author sanket
 *
 */
public interface ProductMasterTempRepository extends JpaRepository<ProductMasterTemp, Long>{

	@Query("SELECT o FROM ProductMasterTemp o WHERE o.id=:fpProductId")
	public ProductMasterTemp getProductMasterTemp(@Param("fpProductId")Long fpProductId);
	
}
