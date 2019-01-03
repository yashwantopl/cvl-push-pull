package com.capitaworld.service.loans.repository.fundseeker;

import com.capitaworld.service.loans.domain.ScoringRequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoringRequestDetailRepository extends JpaRepository<ScoringRequestDetail, Long> {

    @Query("from ScoringRequestDetail where applicationId=:applicationId and isActive = true ")
    public List<ScoringRequestDetail> getScoringRequestDetailByApplicationIdAndIsActive(@Param("applicationId")Long applicationId);
}
