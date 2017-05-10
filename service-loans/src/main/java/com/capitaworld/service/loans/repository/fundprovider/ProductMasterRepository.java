package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;

public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>{

	@Modifying
	@Query("update ProductMaster pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId ,pm.userId =:userId where pm.isActive = true")
	public int inActive(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.isActive = true")
	public List<ProductMaster> getUserProductList(@Param("userId") Long userId);
}
