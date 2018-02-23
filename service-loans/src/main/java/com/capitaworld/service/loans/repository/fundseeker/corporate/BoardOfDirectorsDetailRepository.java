package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.model.teaser.finalview.BoardOfDirectorsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.BoardOfDirectorsDetail;

import java.util.List;

/**
 * @author Sanket
 *
 */
public interface BoardOfDirectorsDetailRepository extends JpaRepository<BoardOfDirectorsDetail, Long> {

	@Modifying
	@Transactional
	@Query("update BoardOfDirectorsDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveBoardOfDirectorsDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update BoardOfDirectorsDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveBoardOfDirectorsDetailsByAppId(@Param("applicationId")Long applicationId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.BoardOfDirectorsResponse(a.anySpecialAchievement,a.designation,a.experience,a.functionalDuties,a.name,a.qualification) from BoardOfDirectorsDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<BoardOfDirectorsResponse> listByApplicationId(@Param("applicationId")Long applicationId);
}
