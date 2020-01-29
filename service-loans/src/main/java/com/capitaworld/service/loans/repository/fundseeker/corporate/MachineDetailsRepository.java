package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.fundseeker.corporate.MachineDetailMudraLoan;

@Repository
public interface MachineDetailsRepository extends JpaRepository<MachineDetailMudraLoan, Long>{
	
	@Modifying
	@Query("update MachineDetailMudraLoan m set m.isActive = false,m.modifiedDate = NOW(),m.modifiedBy =:userId where m.applicationId =:applicationId and m.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
	
	public List<MachineDetailMudraLoan> findByApplicationId(Long applicationId);
	
	public void deleteById(Long id); 
	
	
}
