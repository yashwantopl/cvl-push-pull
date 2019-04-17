package com.capitaworld.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.fundprovider.RetailModelTemp;

@Repository
public interface RetailModelTempRepository extends JpaRepository<RetailModelTemp, Long>{

}
