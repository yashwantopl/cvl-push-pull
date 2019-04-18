package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModelTemp;

public interface HomeLoanModelTempRepository extends JpaRepository<HomeLoanModelTemp, Long> {
	
	/**
	 * Getting Object By id and Copyflag and Approved Flag
	 * @param id
	 * @param copy
	 * @param approved
	 * @return
	 */
	public HomeLoanModelTemp findByIdAndIsCopiedAndIsApproved(Long id,boolean copy, boolean approved);
	
}
