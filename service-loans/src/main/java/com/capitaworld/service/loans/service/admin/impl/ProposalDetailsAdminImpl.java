package com.capitaworld.service.loans.service.admin.impl;

import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.service.loans.model.ProposalDetailsAdminRequest;
import com.capitaworld.service.loans.repository.AdminDetailRepository;
import com.capitaworld.service.loans.service.admin.ProposalDetailsAdminService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProposalDetailsAdminImpl implements ProposalDetailsAdminService {
    @Autowired
    private AdminDetailRepository proposalDetailRepository;

    @Override
    public List<ProposalDetailsAdminRequest> listProposal() {
        List<ProposalDetailsAdminRequest> adminRequestList = new ArrayList<>();
        List<Object[]> list = proposalDetailRepository.usersList(new PageRequest(0,100));
        //usr.user_id,usr.email,usr.sign_up_date,usr.last_access_id
        for (Object[] userObj:list) {
            ProposalDetailsAdminRequest adminRequest = new ProposalDetailsAdminRequest();
            adminRequest.setUserId(Long.valueOf(userObj[0].toString()));
            adminRequest.setEmail(String.valueOf(userObj[1]));
            adminRequest.setSignUpDate(CommonUtils.isObjectNullOrEmpty(userObj[2])?null:userObj[2].toString());
            if (!CommonUtils.isObjectNullOrEmpty(userObj[3])){
                //connect query
                Long applicationId  = Long.valueOf(userObj[3].toString());
                Object[] connectData = proposalDetailRepository.connectList(applicationId);
                if (!CommonUtils.isObjectNullOrEmpty(connectData)) {
                    //con.application_id,con.stage_id,con.modified_date,con.status,con.message
                    Object[] obj = (Object[]) connectData[0];
                    Integer stageId = Integer.valueOf(obj[1].toString());
                    adminRequest.setApplicationId(Long.valueOf(obj[0].toString()));
                    adminRequest.setStageId(stageId);
                    adminRequest.setModifiedDate(String.valueOf(obj[2].toString()));
                    adminRequest.setStatus(Long.valueOf(obj[3].toString()));
                    adminRequest.setMessage(String.valueOf(obj[4]));
                    if(stageId == ConnectStage.COMPLETE.getId()
                            || stageId == ConnectStage.IN_PRINCIPLE.getId()
                            || stageId == ConnectStage.NTB_COMPLETE.getId()
                            || stageId == ConnectStage.NTB_IN_PRINCIPLE.getId()){
                        Object[] proposalObjects = proposalDetailRepository.proposalList(applicationId);
                        if (!CommonUtils.isObjectNullOrEmpty(proposalObjects) && proposalObjects.length > 0){
                            /*prop.fp_product_id,0
                            prop.user_org_id,1
                            prop.branch_id,2
                            prop.el_amount,3
                            prop.el_tenure,4
                            prop.el_roi,5
                            prop.application_id 6*/
                            Object[] proposalData = (Object[]) proposalObjects[0];
                            Long fpProductId = Long.valueOf(proposalData[0].toString());
                            Long userOrgId = Long.valueOf(proposalData[1].toString());
                            Long branchId = CommonUtils.isObjectNullOrEmpty(proposalData[2])?null:Long.valueOf(proposalData[2].toString());
                            adminRequest.setFpProductId(fpProductId);
                            adminRequest.setBranchId(branchId);
                            adminRequest.setUserOrgId(userOrgId);
                            adminRequest.setLoanAmount(String.valueOf(proposalData[3]));
                            adminRequest.setTenure(CommonUtils.isObjectNullOrEmpty(String.valueOf(proposalData[4]))?null:String.valueOf(proposalData[4]));
                            adminRequest.setRate(String.valueOf(proposalData[5]));
                            String productName =proposalDetailRepository.productList(fpProductId);
                            if (!CommonUtils.isObjectNullOrEmpty(productName)){
                                adminRequest.setProductName(productName);
                            }
                            if (!CommonUtils.isObjectNullOrEmpty(branchId)) {
                                Object[] branchObject = proposalDetailRepository.branchList(branchId);
                                if (!CommonUtils.isObjectNullOrEmpty(branchObject)) {
                                    Object[] branchData = (Object[]) branchObject[0];
                                    adminRequest.setBranchName(String.valueOf(branchData[0]));
                                    adminRequest.setCity(CommonUtils.isObjectNullOrEmpty(branchData[1]) ? null : String.valueOf(branchData[1]));
                                    adminRequest.setState(CommonUtils.isObjectNullOrEmpty(branchData[2]) ? null : String.valueOf(branchData[2]));
                                }
                            }

                            CommonUtils.BankName bankName = CommonUtils.BankName.getDataFormBankId(Integer.parseInt(userOrgId.toString()));
                            adminRequest.setBankName(bankName.getValue());
                            adminRequest.setBankImageUrl(bankName.getImageUrl());

                            //set organisation name
                            String organisationName = proposalDetailRepository.applicantList(applicationId);
                            if (!CommonUtils.isObjectNullOrEmpty(organisationName)){
                                adminRequest.setOrganizationName(organisationName);
                            }
                        }
                    }
                }
            }
            adminRequestList.add(adminRequest);
        }
        return adminRequestList;
    }
}
