package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.corporate.MachineDetailMudraLoan;

public interface MachineDetailsRepository extends JpaRepository<MachineDetailMudraLoan, Long>{
	
	
	public List<MachineDetailMudraLoan> findByApplicationId(Long applicationId);
	
	public void deleteById(Long id); 
	
	
}
