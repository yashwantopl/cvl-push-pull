package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;

public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>{

	@Query("from ProductMaster pm where pm.userId =:userId and pm.isActive = true")
	public List<ProductMaster> getUserProductList(@Param("userId") Long userId);
}
