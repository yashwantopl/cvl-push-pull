package com.capitaworld.service.loans.repository.colending;

import com.capitaworld.service.loans.domain.colending.RecommendDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dhaval.panchal on 12-Sep-19.
 */

public interface RecommendDetailRepository extends JpaRepository<RecommendDetail,Long>{

    @Query(value = "select * from recommend_detail rd where rd.application_id =:applicationId order by id desc limit 1",nativeQuery = true)
    public RecommendDetail getByApplicationIdOrderByIdDescLimit1(@Param("applicationId") Long applicationId);
}
