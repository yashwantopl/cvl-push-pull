package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.GuarantorsCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.UnsecuredGuarantorDetail;

/**
 * @author Sanket
 *
 */
public interface UnsecuredGuarantorDetailRepository extends JpaRepository<GuarantorsCorporateDetail, Long> {

	@Query("select o from UnsecuredGuarantorDetail o where o.applicationId.id =:id and o.isActive = true")
	public List<UnsecuredGuarantorDetail> listGuarantorsCorporateFromAppId(@Param("id")Long id);

}
