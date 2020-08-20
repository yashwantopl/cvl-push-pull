/**
 * 
 */
package com.opl.service.loans.repository.sidbi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.sidbi.PrimaryCollateralSecurity;

/**
 * @author vijay.chauhan
 *
 */
public interface PrimaryCollateralSecurityRepository  extends JpaRepository<PrimaryCollateralSecurity,Long>{

	@Query("from PrimaryCollateralSecurity  a where a.applicationId =:applicationId AND a.isActive=true")
	public List<PrimaryCollateralSecurity> getPrimaryCollateralSecurityListAppId(@Param("applicationId") Long applicationId);
}
