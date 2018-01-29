package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRAuthorizedSignDetails;

public interface DDRAuthorizedSignDetailsRepository extends JpaRepository<DDRAuthorizedSignDetails,Long>{

	@Query("select * from DDRAuthorizedSignDetails where id =:id and isActive = true")
	public DDRAuthorizedSignDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select * from DDRAuthorizedSignDetails where ddrFormId =:ddrFormId and isActive = true")
	public List<DDRAuthorizedSignDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
}
