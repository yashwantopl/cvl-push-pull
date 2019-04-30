package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;

public interface CorporateApplicantDetailRepository extends JpaRepository<CorporateApplicantDetail, Long> {

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public CorporateApplicantDetail getByApplicationAndUserId(@Param("userId") Long userId, @Param("applicationId") Long applicationId);

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true AND cr.applicationProposalMapping.proposalId=:proposalId")
	public CorporateApplicantDetail getByApplicationAndProposalIdAndUserId(@Param("userId") Long userId, @Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	@Query("from CorporateApplicantDetail cr where cr.applicationProposalMapping.proposalId =:proposalId and cr.isActive=true")
	public CorporateApplicantDetail getByProposalId(@Param("proposalId") Long proposalId);

	@Query("select organisationName from CorporateApplicantDetail cr where cr.applicationProposalMapping.proposalId =:proposalId and cr.isActive=true")
	public String getOrganisationNameByProposalId(@Param("proposalId") Long proposalId);

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.applicationProposalMapping.proposalId =NULL")
	public CorporateApplicantDetail getByApplicationAndUserIdForSP(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
	@Query("select cr.organisationName,cr.modifiedDate from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public List<Object[]> getByNameAndLastUpdateDate(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);


	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationProposalMapping.proposalId = null")
	public CorporateApplicantDetail findOneByApplicationIdId(@Param("applicationId") Long applicationId);
	
	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public CorporateApplicantDetail getByApplicationIdAndIsAtive(@Param("applicationId") Long applicationId);

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationProposalMapping.proposalId =:proposalId and cr.isActive=true")
	public CorporateApplicantDetail getByApplicationIdAndProposalIdAndIsActive(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationProposalMapping.proposalId =:proposalId and cr.isActive=true")
	public CorporateApplicantDetail getByApplicationIdAndApplicationIdAndIsAtive(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.isActive=:isActive and cr.applicationProposalMapping.proposalId=NULL")
	public CorporateApplicantDetail findByApplicationIdIdAndIsActive(@Param("applicationId") Long applicationId,@Param("isActive") Boolean isActive);

	@Query("select count(cr.applicationId.id) from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and (cr.organisationName != NULL and cr.organisationName != '') and cr.applicationProposalMapping.proposalId =NULL")
	public Long hasAlreadyApplied(@Param("userId") Long userId,
								  @Param("applicationId") Long applicationId);

	@Query("select count(cr.applicationId.id) from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and (cr.organisationName != NULL and cr.organisationName != '') and cr.applicationProposalMapping.proposalId =:proposalId")
	public Long hasAlreadyApplied(@Param("userId") Long userId,
								  @Param("applicationId") Long applicationId,
								  @Param("proposalId") Long proposalId);
	
	@Modifying
	@Query(value="update fs_corporate_applicant_details set latitude =:lat,longitude =:lon where application_id =:applicationId and is_active = 1",nativeQuery = true)
	public int updateLatLong(@Param("lat") Double lat,@Param("lon") Double lon, @Param("applicationId") Long applicationId);
	
	@Query("select cr.latitude,cr.longitude from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public List<Object[]> getLatLonByApplicationAndUserId(@Param("applicationId") Long applicationId,@Param("userId") Long userId);

	@Query("select count(cr.id) from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public long getApplicantCount(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
	@Query("select cr.establishmentYear from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public Integer getApplicantEstablishmentYear(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);

	@Query("select cr.gstIn from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.isActive=true and cr.applicationProposalMapping.proposalId =NULL")
	public String getGstInByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query(value="select CAST(AES_DECRYPT(UNHEX(cr.pan),'C@p!ta@W0rld#AES') AS CHAR(50)) from loan_application.fs_corporate_applicant_details cr where cr.application_id =:applicationId and cr.is_active=true and cr.proposal_mapping_id IS NULL",nativeQuery= true)
	public String getPanNoByApplicationId(@Param("applicationId") Long applicationId);

	@Modifying
	@Query(value="update fs_corporate_applicant_details set is_gst_completed =:flagValue,modified_date =NOW() where application_id =:applicationId and is_active = 1 and gstin =:gstinValue",nativeQuery = true)
	public int updateGSTFlag(@Param("applicationId")Long applicationId,@Param("gstinValue")String gstin, @Param("flagValue")Boolean flag);

	@Modifying
	@Query(value="update fs_corporate_applicant_details set is_gst_completed =:flagValue,modified_date =NOW() where application_id =:applicationId and is_active = 1",nativeQuery = true)
	public int updateGSTFlagWithoutGstin(@Param("applicationId")Long applicationId, @Param("flagValue")Boolean flag);

	@Modifying
	@Query(value="update fs_corporate_applicant_details set is_itr_completed =:flagValue,modified_date =NOW() where application_id =:applicationId and is_active = 1",nativeQuery = true)
	public int updateITRFlag(@Param("applicationId")Long applicationId,@Param("flagValue")Boolean flag);

	@Query("select cr.establishmentYear from CorporateApplicantDetail cr where cr.applicationProposalMapping.proposalId=:proposalId and cr.isActive=true")
	public Integer getApplicantEstablishmentYearFromProposalId(@Param("proposalId") Long proposalId);

	@Query(value="from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationProposalMapping IS NULL")
	public CorporateApplicantDetail getCorporateApplicantDetailByApplicationId(@Param("applicationId") Long applicationId);

	@Query(value="SELECT c.organisation_name FROM loan_application.fs_corporate_applicant_details c WHERE c.application_id=:applicationId LIMIT 1;",nativeQuery = true)
	public String getOrganizationNameFromId(@Param("applicationId") Long applicationId);
}
