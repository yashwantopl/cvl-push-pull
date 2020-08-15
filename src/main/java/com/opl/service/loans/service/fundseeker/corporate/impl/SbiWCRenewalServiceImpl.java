package com.opl.service.loans.service.fundseeker.corporate.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.connect.ConnectResponse;
import com.opl.mudra.api.connect.exception.ConnectException;
import com.opl.mudra.api.loans.model.LoanApplicationRequest;
import com.opl.mudra.api.matchengine.exception.MatchException;
import com.opl.mudra.api.matchengine.model.ApplicationProductAuditRequest;
import com.opl.mudra.api.matchengine.model.MatchRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingRequest;
import com.opl.mudra.api.matchengine.utils.CommonUtils;
import com.opl.mudra.api.payment.model.GatewayRequest;
import com.opl.mudra.api.payment.model.GatewayResponse;
import com.opl.mudra.client.connect.ConnectClient;
import com.opl.mudra.client.matchengine.MatchEngineClient;
import com.opl.mudra.client.matchengine.ProposalDetailsClient;
import com.opl.mudra.client.payment.GatewayClient;
import com.opl.service.loans.domain.fundprovider.ProductMaster;
import com.opl.service.loans.repository.fundprovider.ProductMasterRepository;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.service.fundseeker.corporate.SbiWCRenewalService;

/**
 * Created by dhaval.panchal on 25-Apr-19.
 */
@Service
@Transactional
public class SbiWCRenewalServiceImpl implements SbiWCRenewalService {

	private static final Logger logger = LoggerFactory.getLogger(SbiWCRenewalServiceImpl.class);
	
    @Autowired
    private GatewayClient gatewayClient;

    @Autowired
    private ConnectClient connectClient;

    @Autowired
    private MatchEngineClient matchEngineClient;

    @Autowired
    private ProposalDetailsClient proposalDetailsClient;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Override
    public boolean callSkipPayment(Long application_id, Long userId) {
        try {
            GatewayRequest gatewayRequest = new GatewayRequest();
            gatewayRequest.setApplicationId(application_id);
            gatewayRequest.setIsSbiSpecific(true);
            GatewayResponse response = gatewayClient.skipPayment(gatewayRequest);
            if("Success".equals(response.getData())){
                ConnectResponse postOneForm = null;
                try {
                    postOneForm = connectClient.postInPrinciple(application_id,userId,null);
                    return true;
                } catch (ConnectException e1) {
                    return false;
                }
            }else{
                return false;
            }
        } catch (Exception e) {
        	logger.error("Erroe while callSkipPayment",e);
            return false;
        }
    }

    @Override
    public boolean callMatchEngine(Long application_id, Long userId) {
        MatchRequest request = new MatchRequest();
        request.setApplicationId(application_id);
        request.setStage(CommonUtils.MatchesStages.ONE_FORM);
        try {
            ApplicationProductAuditRequest response = matchEngineClient.listSbiMatchesList(request);
            if (response != null) {//no matches found [proposal eligible]
                //for update matches proposal detail entry
                ProposalMappingRequest mappingRequest = new ProposalMappingRequest();
                mappingRequest.setApplicationId(response.getApplicationId());
                mappingRequest.setFpProductId(response.getFpProductId());
                mappingRequest.setExistingLoanAmount(response.getExistingLoanAmount());
                mappingRequest.setAdditionalLoanAmount(response.getAdditionalLoanAmount());
                mappingRequest.setEmi(response.getEmi());
                mappingRequest.setProcessingFee(response.getProcessingFee());
                mappingRequest.setElAmount(response.getMaxLoanAmount());
                mappingRequest.setElRoi(response.getOfferedRoi());
                mappingRequest.setElTenure(response.getMaxTenure());
                mappingRequest.setUserId(response.getFpUserId());

                //calling connect for fetch branch and org details.
                ConnectResponse postOneForm = null;
                try {
                    postOneForm = connectClient.postPayment(application_id, userId, null);
                } catch (ConnectException e1) {
                	logger.error("Exception while calling callMatchEngine() of postPayment()",e1);
                }
                if (postOneForm != null && postOneForm.getProceed() != null && postOneForm.getProceed().booleanValue()) {
                    mappingRequest.setUserOrgId(postOneForm.getOrgId());
                    mappingRequest.setBranchId(postOneForm.getBranchId());
                }
                //saving details in proposal details
                
                proposalDetailsClient.savePoposalOnLoanSelection(mappingRequest);


                //for loan details update call. [existing service re-used]
                LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
                loanApplicationRequest.setId(application_id);
                loanApplicationRequest.setAmount(response.getMaxLoanAmount());
                loanApplicationRequest.setTenure(response.getMaxTenure());
                ProductMaster productMaster = productMasterRepository.getById(response.getFpProductId());
                loanApplicationRequest.setProductId(productMaster.getProductId());
                if(null!=postOneForm) {
                	loanApplicationRequest.setNpOrgId(postOneForm.getOrgId());
                }
                
                return loanApplicationService.updateProductDetails(loanApplicationRequest);
            }
        } catch (MatchException e) {
        	logger.error("Exception while calling callMatchEngine()",e);
        }
        return false;
    }
}
