package com.opl.service.loans.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.common.MinMaxProductDetail;

public interface MinMaxProductDetailRepository extends JpaRepository<MinMaxProductDetail,Long> {

    @Query("from MinMaxProductDetail where orgId=:orgId and isActive=true")
    public List<MinMaxProductDetail> listMinMaxProductDetailByOrgId(@Param("orgId")Long orgId);

    @Query("from MinMaxProductDetail where orgId IS NULL and isActive=true")
    public List<MinMaxProductDetail> listMinMaxProductDetail();
}
