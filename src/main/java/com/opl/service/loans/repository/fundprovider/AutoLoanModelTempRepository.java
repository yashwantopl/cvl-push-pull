package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.AutoLoanModelTemp;

public interface AutoLoanModelTempRepository extends JpaRepository<AutoLoanModelTemp, Long> {
	
	/**
	 * Getting Object By id and Copyflag and Approved Flag
	 * @param id
	 * @param copy
	 * @param approved
	 * @return
	 */
	public AutoLoanModelTemp findByIdAndIsCopiedAndIsApproved(Long id,boolean copy, boolean approved);
	
}
