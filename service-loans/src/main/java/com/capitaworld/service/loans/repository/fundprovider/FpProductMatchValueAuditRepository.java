package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.FpProductMatchValueAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jaimin.darji
 */
public interface FpProductMatchValueAuditRepository extends JpaRepository<FpProductMatchValueAudit, Long> {

    @Modifying
    @Query("update FpProductMatchValueAudit pm set pm.isActive = false where pm.applicationId =:applicationId and pm.isActive = true")
    public int inactiveAllByProductId(@Param("applicationId") Long applicationId);

    @Query("select p from FpProductMatchValueAudit p where p.applicationId =:applicationId and p.isActive = true")
    public List<FpProductMatchValueAudit> findAllByProductId(@Param("applicationId") Long applicationId);
}
