package com.capitaworld.service.loans.repository.fundseeker.agri;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.agri.PrimaryAgriLoanDetail;

public interface PrimaryAgriLoanDetailRepository extends JpaRepository<PrimaryAgriLoanDetail, Long> {

    public PrimaryAgriLoanDetail findById(Long id);
    
    public PrimaryAgriLoanDetail findByIdAndUserId(Long id,Long userId);
}


