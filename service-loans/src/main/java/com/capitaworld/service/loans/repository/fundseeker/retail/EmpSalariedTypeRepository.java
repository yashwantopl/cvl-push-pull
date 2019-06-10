package com.capitaworld.service.loans.repository.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.EmpAgriculturistType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSelfEmployedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpSalariedTypeRepository extends JpaRepository<EmpSalariedType, Long> {

    @Query("select o from EmpSalariedType o where o.applicationId.id = :applicationId and o.isActive = true")
    List<EmpSalariedType> getListByApplicationId(@Param("applicationId")Long applicationId);

    @Query("select o from EmpSalariedType o where o.proposalId.proposalId = :proposalId and o.isActive = true")
    public List<EmpSalariedType> listSalariedEmpRetailFromPropsalId(@Param("proposalId")Long proposalId);
}
