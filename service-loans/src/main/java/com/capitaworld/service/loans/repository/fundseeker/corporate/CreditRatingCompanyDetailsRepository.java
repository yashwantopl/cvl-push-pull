package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CreditRatingCompanyDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreditRatingCompanyDetailsRepository extends JpaRepository<CreditRatingCompanyDetail, Long>{

	@Query("Select cd from CreditRatingCompanyDetail cd where cd.name like :companyName%")
	public List<CreditRatingCompanyDetail> getCompanyDetail(@Param("companyName") String companyName);
}
