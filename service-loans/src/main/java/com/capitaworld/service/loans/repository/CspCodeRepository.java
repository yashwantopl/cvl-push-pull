package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.CspCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CspCodeRepository extends JpaRepository<CspCodes,Long> {
}
