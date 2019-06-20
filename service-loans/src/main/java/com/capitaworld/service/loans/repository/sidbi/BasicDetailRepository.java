package com.capitaworld.service.loans.repository.sidbi;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.sidbi.SidbiBasicDetail;

public interface BasicDetailRepository extends JpaRepository<SidbiBasicDetail, Long>{

	
	
	
}
