package com.capitaworld.service.loans.repository.fundseeker;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by KushalCW on 22-09-2018.
 */

public interface IneligibleProposalDetailsRepository extends JpaRepository<IneligibleProposalDetails,Long> {

}
