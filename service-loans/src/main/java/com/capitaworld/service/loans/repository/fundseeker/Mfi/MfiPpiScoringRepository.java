package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiPpiScoringMaster;

public interface MfiPpiScoringRepository extends JpaRepository<MfiPpiScoringMaster,Long> {

}
