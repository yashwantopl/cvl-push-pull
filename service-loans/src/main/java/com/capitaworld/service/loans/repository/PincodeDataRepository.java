package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.PincodeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PincodeDataRepository extends JpaRepository<PincodeData,Long> {
}
