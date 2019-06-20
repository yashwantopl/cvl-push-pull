package com.capitaworld.service.loans.repository.sidbi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sidbi.RawMaterialDetails;

/**
 * Created by pooja.patel on 19-06-2019.
 */
public interface RawMaterialDetailsRepository extends JpaRepository<RawMaterialDetails, Long>{
	@Query("from RawMaterialDetails  a where a.applicationId =:applicationId AND a.isActive=true")
	public List<RawMaterialDetails> getRawMaterialDetailsListAppId(@Param("applicationId") Long applicationId);
}
