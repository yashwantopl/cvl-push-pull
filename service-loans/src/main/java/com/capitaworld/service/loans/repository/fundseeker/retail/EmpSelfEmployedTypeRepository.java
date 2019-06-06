package com.capitaworld.service.loans.repository.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.EmpAgriculturistType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSelfEmployedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.OtherPropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpSelfEmployedTypeRepository extends JpaRepository<EmpSelfEmployedType, Long> {

    @Query("select o from EmpSelfEmployedType o where o.applicationId.id = :applicationId and o.isActive = true")
    List<EmpSelfEmployedType> getListByApplicationId(@Param("applicationId")Long applicationId);

    @Query("select o from EmpSelfEmployedType o where o.proposalId.proposalId = :proposalId and o.isActive = true")
    public List<EmpSelfEmployedType> listSalariedEmpRetailFromPropsalId(@Param("proposalId")Long proposalId);
}
