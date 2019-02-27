package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamter;

public interface UniformProductParameterRepository extends JpaRepository<UniformProductParamter, Long>{
	
	/**
	 * Getting Single Row by Id
	 * @param id
	 * @return
	 */
	public UniformProductParamter findById(Long id);
	
	/**
	 * Find UniformProduct by Org Id as there will be only one product of Uniform
	 * @param userOrgId
	 * @return
	 */
	public UniformProductParamter findFirstByUserOrgIdOrderByIdDesc(Long userOrgId);
	
	/**
	 * Getting Active Uniform product for the given Organization.
	 * @param userOrgId
	 * @param isActive
	 * @return
	 */
	public UniformProductParamter findFirstByUserOrgIdAndIsActiveOrderByIdDesc(Long userOrgId,Boolean isActive);
	
}
