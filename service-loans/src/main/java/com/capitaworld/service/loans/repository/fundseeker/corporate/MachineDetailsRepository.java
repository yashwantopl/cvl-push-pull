package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.MachineDetailMudraLoan;

public interface MachineDetailsRepository extends JpaRepository<MachineDetailMudraLoan, Long>{
	
	@Modifying
	@Query("update MachineDetailMudraLoan set isActive = false where applicationId =:applicationId and isActive = true")
	public int inactiveMachineDetails(@Param("applicationId") Long applicationId);
	
	public List<MachineDetailMudraLoan> findByApplicationId(Long applicationId);
	
	public void deleteById(Long id); 
	
	public void deleteByApplicationId(Long applicationId); 
	
}