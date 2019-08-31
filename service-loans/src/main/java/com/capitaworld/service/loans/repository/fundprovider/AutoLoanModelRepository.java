package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.AutoLoanModel;

public interface AutoLoanModelRepository extends JpaRepository<AutoLoanModel, Long> {
	
	/**
	 * Getting Single Object of Loan Purpose Approved Model
	 * @param id
	 * @return
	 */
	public AutoLoanModel findById(Long id);
	
	/**
	 * Getting Master Model by Temp Reference Id(PK)
	 * @param retailModelTempRefId
	 * @return
	 */
	public AutoLoanModel findByRetailModelTempRefId(Long retailModelTempRefId);
	
}
