package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModel;

public interface HomeLoanModelRepository extends JpaRepository<HomeLoanModel, Long> {
	
	/**
	 * Getting Single Object of Loan Purpose Approved Model
	 * @param id
	 * @return
	 */
	public HomeLoanModel findById(Long id);
	
	/**
	 * Getting Master Model by Temp Reference Id(PK)
	 * @param retailModelTempRefId
	 * @return
	 */
	public HomeLoanModel findByRetailModelTempRefId(Long retailModelTempRefId);
	
}
