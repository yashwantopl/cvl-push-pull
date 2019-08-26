package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.CoLendingRatio;
import com.capitaworld.service.loans.model.DataRequest;

public interface CoLendingRatioRepository extends JpaRepository<CoLendingRatio, Long>{
	@Modifying
	@Query("update CoLendingRatio clr set clr.isActive = false where clr.id=:id and clr.isActive = true")
	public int inActiveRatio(@Param("id") Long id);
	
	@Modifying
	@Query("update CoLendingRatio clr set clr.isProposalActive=:status where clr.jobId=:jobId and clr.isActive = true")
	public int updateActivatedRatio(@Param("jobId") Long jobId,@Param("status") Boolean status);

	@Query("from CoLendingRatio clr where clr.userOrgId =:userOrgId and isActive=true")
	public List<CoLendingRatio> listAllActiveByUserId(@Param("userOrgId")Long userId);

	@Query("from CoLendingRatio clr where clr.bankId =:bankId and isActive=true")
	public List<CoLendingRatio> listAllActiveByBankId(@Param("bankId")Long bankId);

	@Modifying
	@Query("update CoLendingRatio clr set clr.reason =:reason where clr.jobId=:jobId and clr.isActive = true")
	public int addReasonByJobId(@Param("reason")String reason,@Param("jobId")Long  jobId);

}
