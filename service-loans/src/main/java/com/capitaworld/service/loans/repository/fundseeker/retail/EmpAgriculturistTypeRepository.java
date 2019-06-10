package com.capitaworld.service.loans.repository.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.EmpAgriculturistType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpAgriculturistTypeRepository extends JpaRepository<EmpAgriculturistType, Long> {

    @Query("select o from EmpAgriculturistType o where o.applicationId.id = :applicationId and o.isActive = true")
    List<EmpAgriculturistType> getListByApplicationId(@Param("applicationId")Long applicationId);

    @Query("select o from EmpAgriculturistType o where o.proposalId.proposalId = :proposalId and o.isActive = true")
    public List<EmpAgriculturistType> listSalariedEmpRetailFromPropsalId(@Param("proposalId")Long proposalId);
}
