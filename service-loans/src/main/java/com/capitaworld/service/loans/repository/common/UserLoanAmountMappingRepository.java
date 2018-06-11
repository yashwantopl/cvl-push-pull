package com.capitaworld.service.loans.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.common.UserLoanAmountMapping;

/**
 * @author harshit
 * Date : 09-Jun-2018
 */
public interface UserLoanAmountMappingRepository extends JpaRepository<UserLoanAmountMapping, Long> {

	@Query("select count(*) from UserLoanAmountMapping where minAmount <= :amount and maxAmount >= :amount and productId =:productId and userId =:userId and isActive = true")
	public Long checkAmountByUserId(@Param("amount") Double amount,@Param("userId") Long userId,@Param("productId") Long productId);
	
	public UserLoanAmountMapping findByIdAndIsActive(Long id,Boolean isActive);
	
	public UserLoanAmountMapping findByUserIdAndIsActive(Long userId,Boolean isActive);
	
	public UserLoanAmountMapping findByUserIdAndProductIdAndIsActive(Long userId,Long productId,Boolean isActive);
}
