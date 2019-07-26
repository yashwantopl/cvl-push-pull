package com.capitaworld.service.loans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.GstRelatedParty;

/**
  *@auther : Maaz Shaikh
  */
public interface GstRelatedpartyRepository  extends JpaRepository<GstRelatedParty, Long>{
	
	List<GstRelatedParty> findAllByApplicationIdAndIsActiveIsTrue(Long applicationId);
	
}
