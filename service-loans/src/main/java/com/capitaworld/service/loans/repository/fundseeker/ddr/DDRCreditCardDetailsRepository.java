package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditCardDetails;

public interface DDRCreditCardDetailsRepository extends JpaRepository<DDRCreditCardDetails, Long>{

	@Query("select * from DDRCreditCardDetails where id =:id and isActive = true")
	public DDRCreditCardDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select * from DDRCreditCardDetails where ddrFormId =:ddrFormId and isActive = true")
	public List<DDRCreditCardDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
}
