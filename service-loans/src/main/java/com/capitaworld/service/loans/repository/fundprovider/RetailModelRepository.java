package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.RetailModel;

public interface RetailModelRepository extends JpaRepository<RetailModel, Long>{

	public RetailModel findById(Long id);
	
	public List<RetailModel> findByOrgId(Long id);
}
