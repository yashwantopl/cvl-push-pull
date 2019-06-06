package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.FpProductConditions;
import com.capitaworld.service.loans.model.ProductConditionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FpProductConditionsRepository extends JpaRepository<FpProductConditions, Long> {
    @Query("select new com.capitaworld.service.loans.model.ProductConditionResponse(c.id,c.fpProductId,c.conditionName,c.isMandatory,c.isAllMandatory,c.allLogicalCondition,c.isActive,p.productId) from FpProductConditions c Left join ProductMasterTemp p on p.id = c.fpProductId where c.fpProductId =:productId")
    public List<ProductConditionResponse> findAllByFpProductId(@Param("productId") Long productId);
}
