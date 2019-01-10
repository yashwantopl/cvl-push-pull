package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamterAudit;

public interface UniformProductParameterAuditRepository extends JpaRepository<UniformProductParamterAudit, Long>{
	
	/**
	 * Getting Single Row by Id
	 * @param id
	 * @return
	 */
	public UniformProductParamterAudit findById(Long id);
	
	/**
	 * Find UniformProduct by Org Id as there will be only one product of Uniform
	 * @param userOrgId
	 * @return
	 */
	public List<UniformProductParamterAudit> findByOrgId(Long userOrgId);
	
	
	/**
	 * Getting Last record To Update Modified Date.
	 * @param userOrgId
	 * @return
	 */
	public UniformProductParamterAudit findFirstByOrgIdOrderByIdDesc(Long userOrgId);
	
	/**
	 * Getting Last record To Update Modified Date.
	 * @param userOrgId
	 * @return
	 */
	public UniformProductParamterAudit findFirstByFpProductIdOrderByIdDesc(Long fpProductId);
	
	
}
