package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;

public interface LoanApplicationRepository extends JpaRepository<LoanApplicationMaster, Long> {

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isActive = false,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int inActive(@Param("id") Long id, @Param("userId") Long userId);

	@Query("from LoanApplicationMaster lm where lm.userId =:userId and lm.isActive = true")
	public List<LoanApplicationMaster> getUserLoans(@Param("userId") Long userId);

	@Query("from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true order by lm.id")
	public LoanApplicationMaster getByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("select new com.capitaworld.service.loans.model.LoanApplicationDetailsForSp(lm.id,lm.productId,lm.amount,lm.denominationId)  from LoanApplicationMaster lm where lm.userId=:userId")
	public List<LoanApplicationDetailsForSp> getListByUserId(@Param("userId") Long userId);
	

}
