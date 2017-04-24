package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;

public interface TermLoanDetailRepository extends JpaRepository<TermLoanParameter, Long>{

}
