package com.capitaworld.service.loans.repository.fundseeker.Mfi;


import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MfiApplicationDetailsRepository extends JpaRepository<MFIApplicationDetail,Long> {
}
