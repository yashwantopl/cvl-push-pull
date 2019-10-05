package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.CspCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CspCodeRepository extends JpaRepository<CspCode,Long> {
	
	@Query(value="SELECT count(cs.id) from loan_application.csp_code cs where loan_application.fn_remove_accents(cs.description) =:code and cs.org_id =:orgId")
	public Long isCodeExists(@Param("code")String code,@Param("orgId")Long orgId);
}
