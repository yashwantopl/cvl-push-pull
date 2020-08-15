package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.opl.service.loans.domain.fundprovider.FpCoLendingBanks;

public interface FpCoLendingBanksRepository   extends JpaRepository<FpCoLendingBanks, Long>{
	
	@Query("from FpCoLendingBanks fc where fc.isActive = true ")
	public List<FpCoLendingBanks> getAllActive();

}
