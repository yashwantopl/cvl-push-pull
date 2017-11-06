package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.GuarantorsCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.UnsecuredGuarantorDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.model.UnsecuredGuarantorDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.GuarantorsCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.UnsecuredGuarantorDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.UnsecuredGuarantorDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class UnsecuredGuarantorDetailServiceImpl implements UnsecuredGuarantorDetailService {

	private static final Logger logger = LoggerFactory.getLogger(UnsecuredGuarantorDetailServiceImpl.class.getName());
	@Autowired
	private UnsecuredGuarantorDetailRepository unsecuredGuarantorsDetailRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest = (GuarantorsCorporateDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, GuarantorsCorporateDetailRequest.class);
				GuarantorsCorporateDetail guarantorsCorporateDetail = null;

				if (guarantorsCorporateDetailRequest.getId() != null) {
					guarantorsCorporateDetail = unsecuredGuarantorsDetailRepository
							.findOne(guarantorsCorporateDetailRequest.getId());
				} else {
					guarantorsCorporateDetail = new GuarantorsCorporateDetail();
					guarantorsCorporateDetail.setCreatedBy(frameRequest.getUserId());
					guarantorsCorporateDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(guarantorsCorporateDetailRequest, guarantorsCorporateDetail);
				guarantorsCorporateDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				guarantorsCorporateDetail.setModifiedBy(frameRequest.getUserId());
				guarantorsCorporateDetail.setModifiedDate(new Date());
				unsecuredGuarantorsDetailRepository.save(guarantorsCorporateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save GuarantorDetails  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<UnsecuredGuarantorDetailRequest> getGuarantorsCorporateDetailList(Long id,Long userId) throws Exception {
		try {
			List<UnsecuredGuarantorDetail> guarantorsCorporateDetail = unsecuredGuarantorsDetailRepository
					.listGuarantorsCorporateFromAppId(id,userId);
			List<UnsecuredGuarantorDetailRequest> guarantorsCorporateDetailRequests = new ArrayList<UnsecuredGuarantorDetailRequest>();

			for (UnsecuredGuarantorDetail detail : guarantorsCorporateDetail) {
				UnsecuredGuarantorDetailRequest guarantorsCorporateDetailRequest = new UnsecuredGuarantorDetailRequest();
				BeanUtils.copyProperties(detail, guarantorsCorporateDetailRequest);
				guarantorsCorporateDetailRequests.add(guarantorsCorporateDetailRequest);
			}
			return guarantorsCorporateDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception  in get Guarantor Details :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
