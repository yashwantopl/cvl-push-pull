package com.opl.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.EmpAgriculturistType;

public interface EmpAgriculturistTypeRepository extends JpaRepository<EmpAgriculturistType, Long> {

    @Query("select o from EmpAgriculturistType o where o.applicationId.id = :applicationId and o.isActive = true")
    List<EmpAgriculturistType> getListByApplicationId(@Param("applicationId")Long applicationId);

    @Query("select o from EmpAgriculturistType o where o.proposalId.proposalId = :proposalId and o.isActive = true and o.coAppId=null")
    public List<EmpAgriculturistType> listSalariedEmpRetailFromPropsalId(@Param("proposalId")Long proposalId);

    @Query("select o from EmpAgriculturistType o where o.proposalId.proposalId = :proposalId and o.coAppId=:coAppId and o.isActive = true")
    public List<EmpAgriculturistType> listSalariedEmpRetailFromPropsalIdAndCoAppId(@Param("proposalId")Long proposalId,@Param("coAppId")Long coAppId);
}
