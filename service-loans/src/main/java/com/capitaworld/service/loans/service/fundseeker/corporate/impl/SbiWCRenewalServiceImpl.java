package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.api.payment.gateway.model.GatewayRequest;
import com.capitaworld.api.payment.gateway.model.GatewayResponse;

import com.capitaworld.client.payment.gateway.GatewayClient;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.api.exception.ConnectException;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SbiWCRenewalService;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.exception.MatchException;
import com.capitaworld.service.matchengine.model.ApplicationProductAuditRequest;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.matchengine.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by dhaval.panchal on 25-Apr-19.
 */
@Service
@Transactional
public class SbiWCRenewalServiceImpl implements SbiWCRenewalService {

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

    private static Long orgId = null;
    private static Long branchId = null;

    @Override
    public boolean doSbiRenewalChanges(Long application_id, Long userId) {
        boolean isMatchesDone = callMatchEngine(application_id);
        if (isMatchesDone){
            boolean isConnectDone = callConnect(application_id,userId);
            if (isConnectDone){
                boolean isPaymentSkip = callSkipPayment(application_id);
                if (isPaymentSkip){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean callSkipPayment(Long application_id) {
        try {
            GatewayRequest gatewayRequest = new GatewayRequest();
            gatewayRequest.setApplicationId(application_id);
            gatewayRequest.setIsSbiSpecific(true);
            GatewayResponse response = gatewayClient.skipPayment(gatewayRequest);
            return "Success".equals(response.getData()) ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean callConnect(Long application_id,Long userId) {
        try {
            ConnectResponse postOneForm = connectClient.postPayment(application_id, userId, null);
            if (postOneForm != null && postOneForm.getProceed() != null && postOneForm.getProceed().booleanValue()) {
                branchId = postOneForm.getBranchId();
                orgId = postOneForm.getOrgId();
                return true;
            }else{
                return false;
            }
        } catch (ConnectException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean callMatchEngine(Long application_id) {
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
                mappingRequest.setUserOrgId(orgId);
                mappingRequest.setBranchId(branchId);
                ProposalMappingResponse proposalMappingResponse = proposalDetailsClient.savePoposalOnLoanSelection(mappingRequest);

                //for loan details update call. [existing service re-used]
                LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
                loanApplicationRequest.setId(application_id);
                loanApplicationRequest.setAmount(response.getMaxLoanAmount());
                loanApplicationRequest.setTenure(response.getMaxTenure());
                loanApplicationRequest.setProductId(Integer.parseInt(String.valueOf(response.getFpProductId())));
                loanApplicationRequest.setNpOrgId(orgId);
                boolean isLoanDetailsUpdated  = loanApplicationService.updateProductDetails(loanApplicationRequest);

            }else{//matches found and return first object [proposal in-eligible]
                System.out.println(response.getEmi());
            }
        } catch (MatchException e) {
            e.printStackTrace();
        }
        return true;
    }
}
