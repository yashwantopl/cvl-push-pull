package com.capitaworld.service.loans.repository.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.OtherPropertyDetails;
import com.capitaworld.service.loans.domain.fundseeker.retail.PurchasePropertyDetails;
import org.apache.xpath.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchasePropertyDetailsRepository extends JpaRepository<PurchasePropertyDetails, Long> {

    @Query("select o from PurchasePropertyDetails o where o.applicationId.id = :applicationId and o.isActive = true")
    List<PurchasePropertyDetails> getListByApplicationId(@Param("applicationId") Long applicationId);

    @Query("select o from PurchasePropertyDetails o where o.proposalId.proposalId = :proposalId and o.isActive = true")
    public List<PurchasePropertyDetails> listPropertyFromPropsalId(@Param("proposalId")Long proposalId);
}
