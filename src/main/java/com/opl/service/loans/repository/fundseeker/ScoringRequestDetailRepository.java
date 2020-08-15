package com.opl.service.loans.repository.fundseeker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.ScoringRequestDetail;

public interface ScoringRequestDetailRepository extends JpaRepository<ScoringRequestDetail, Long> {

    @Query("from ScoringRequestDetail where applicationId=:applicationId and isActive = true ")
    public List<ScoringRequestDetail> getScoringRequestDetailByApplicationIdAndIsActive(@Param("applicationId")Long applicationId);
}
