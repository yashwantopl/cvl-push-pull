package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.corporate.CreditRatingCompanyDetail;

public interface CreditRatingCompanyDetailsRepository extends JpaRepository<CreditRatingCompanyDetail, Long>{

	@Query("Select cd from CreditRatingCompanyDetail cd where cd.name like :companyName%")
	public List<CreditRatingCompanyDetail> getCompanyDetail(@Param("companyName") String companyName);
}
