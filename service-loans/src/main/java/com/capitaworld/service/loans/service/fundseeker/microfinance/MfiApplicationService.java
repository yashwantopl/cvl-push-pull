package com.capitaworld.service.loans.service.fundseeker.microfinance;

import java.util.List;

import com.capitaworld.service.loans.model.micro_finance.*;

public interface MfiApplicationService {

    public boolean saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq);

    public AadharDetailsReq getAadharDetailsByAppId(Long applicationId);
    
    public boolean saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq);
    
    public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId);
    
    public boolean saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq);
    
	public List<MfiReqResponse> getMfiApplicantDetails(Long applicationId);

    public boolean saveOrUpdateBankDetails(MfiBankDetailsReq bankDetailsReq);

    public MfiBankDetailsReq fetchBankDetail(Long applicationId);

    public List<MfiApplicantDetailsReq> getAllApplicantDetails(Long applicationId);
    
}
