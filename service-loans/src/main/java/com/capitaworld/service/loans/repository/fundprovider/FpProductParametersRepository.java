package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.FpProductParameters;
import com.capitaworld.service.loans.model.ProductParameterResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FpProductParametersRepository extends JpaRepository<FpProductParameters, Long> {

    @Query("SELECT new com.capitaworld.service.loans.model.ProductParameterResponse(a.id,a.conditionId,a.bodmasFormulaId,a.compareValue,a.minValue,a.maxValue,a.logicalCondition,a.parameterOperator,a.parentId,a.isGroup,a.level,a.isActive) FROM FpProductParameters a where a.conditionId =:conditionId and a.parentId IS NULL")
    public List<ProductParameterResponse> findAllByConditionId(@Param("conditionId") Long conditionId);

    @Query("SELECT new com.capitaworld.service.loans.model.ProductParameterResponse(a.id,a.conditionId,a.bodmasFormulaId,a.compareValue,a.minValue,a.maxValue,a.logicalCondition,a.parameterOperator,a.parentId,a.isGroup,a.level,a.isActive) FROM FpProductParameters a where a.conditionId =:conditionId and a.parentId=:id")
    public List<ProductParameterResponse> findAllByConditionIdWithParent(@Param("id") Long id,@Param("conditionId") Long conditionId);

    @Query(value = "select formula_name from `bodmas_sidbi`.`formula_master` where id =:formulaId", nativeQuery = true)
    public List<String> getFormulaNameById(@Param("formulaId") Long formulaId);
}
