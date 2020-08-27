package com.opl.service.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.CspCode;

public interface CspCodeRepository extends JpaRepository<CspCode,Long> {
	
	@Query(value="SELECT count(cs.id) from csp_code cs where LOWER(fn_remove_special_chars(cs.description)) =:code and cs.org_id =:orgId",nativeQuery = true)
	public Long isCodeExists(@Param("code")String code,@Param("orgId")Long orgId);
}
