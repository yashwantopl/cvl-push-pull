package com.opl.service.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.PincodeData;

import java.util.List;

public interface PincodeDataRepository extends JpaRepository<PincodeData,Long> {

    List<PincodeData> findAllByPincode(String pincode);
}
