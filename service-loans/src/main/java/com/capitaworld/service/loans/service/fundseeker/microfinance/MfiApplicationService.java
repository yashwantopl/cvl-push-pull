package com.capitaworld.service.loans.service.fundseeker.microfinance;

import java.util.List;

import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiReqResponse;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;

public interface MfiApplicationService {

    public boolean saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq);

    public AadharDetailsReq getAadharDetailsByAppId(Long applicationId);
    
    public boolean saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq);
    
    public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId);
    
    public boolean saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq);
    
	public List<MfiReqResponse> getMfiApplicantDetails(Long applicationId);
    
}
