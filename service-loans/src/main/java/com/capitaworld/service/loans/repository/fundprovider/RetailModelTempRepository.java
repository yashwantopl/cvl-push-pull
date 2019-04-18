package com.capitaworld.service.loans.repository.fundprovider;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.RetailModelTemp;

public interface RetailModelTempRepository extends JpaRepository<RetailModelTemp, Long>{

	/**
	 * Getting Single Object By Id(PK)
	 * @param id
	 * @return
	 */
	public RetailModelTemp findById(Long id);
	
	/**
	 * Getting List of InProgress List of Loan Purpose Models by OrgId
	 * @param id
	 * @return
	 */
	public List<RetailModelTemp> findByOrgIdAndIsCopiedAndIsApproved(Long orgId,boolean isCopied,boolean isApproved);
	
	/**
	 * Updating Status of InProgress Loan Purpose Model
	 * @param id
	 * @param statusId
	 * @param copied
	 * @param deleted
	 * @param approved
	 * @param approvedDate
	 * @return
	 */
	@Modifying
	@Query(value = "update retail_model_temp rt set rt.status_id =:status,rt.is_copied =:copied,rt.is_deleted =:deleted,rt.is_approved =:approved,rt.approval_date =:approvedDate where rt.id =:id",nativeQuery = true)
	public int changeStatus(@Param("id") Long id,@Param("status") Integer statusId,@Param("copied") Boolean copied,@Param("deleted") Boolean deleted,@Param("approved") Boolean approved,@Param("approvedDate") Date approvedDate);
}
