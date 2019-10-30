package com.capitaworld.service.loans.repository.colending;

import com.capitaworld.service.loans.domain.colending.RecommendDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dhaval.panchal on 12-Sep-19.
 */

public interface RecommendDetailRepository extends JpaRepository<RecommendDetail,Long>{
    RecommendDetail getOneByApplicationId(Long applicationId);
}
