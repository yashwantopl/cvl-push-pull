package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateMcqDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CorporateMcqDetailRepository extends JpaRepository<CorporateMcqDetail, Long> {

    @Query("from CorporateMcqDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
    public CorporateMcqDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
                                                         @Param("userId") Long id);
    
    @Query("from CorporateMcqDetail pd where pd.applicationId.id =:applicationId")
    public CorporateMcqDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId);
}
