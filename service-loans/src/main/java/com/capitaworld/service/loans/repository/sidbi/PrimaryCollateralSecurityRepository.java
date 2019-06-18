/**
 * 
 */
package com.capitaworld.service.loans.repository.sidbi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.sidbi.PrimaryCollateralSecurity;

/**
 * @author vijay.chauhan
 *
 */
public interface PrimaryCollateralSecurityRepository  extends JpaRepository<PrimaryCollateralSecurity,Long>{

}
