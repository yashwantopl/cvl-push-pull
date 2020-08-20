/**
 * 
 */
package com.opl.service.loans.repository.sidbi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.sidbi.PersonalCorporateGuarantee;

/**
 * @author vijay.chauhan
 *
 */
public interface PersonalCorporateGuaranteeRepository   extends JpaRepository<PersonalCorporateGuarantee,Long>{


	@Query("from PersonalCorporateGuarantee  a where a.applicationId =:applicationId AND a.isActive=true")
	public List<PersonalCorporateGuarantee> getPersonalCorporateGuaranteeListAppId(@Param("applicationId") Long applicationId);
}