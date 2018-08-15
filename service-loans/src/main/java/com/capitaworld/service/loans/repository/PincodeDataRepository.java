package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.PincodeData;
import com.capitaworld.service.loans.model.PincodeDataResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PincodeDataRepository extends JpaRepository<PincodeData,Long> {

    List<PincodeData> findAllByPincode(String pincode);
}
