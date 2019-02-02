package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.PincodeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PincodeDataRepository extends JpaRepository<PincodeData,Long> {

    List<PincodeData> findAllByPincode(String pincode);
}
