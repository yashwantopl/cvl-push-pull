package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorPersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DirectorPersonalDetailRepository extends JpaRepository<DirectorPersonalDetail, Long> {
	
	@Query("from DirectorPersonalDetail o where o.id.id = :Id")
	public DirectorPersonalDetail getById(@Param("Id") Long Id);
	
}
