package com.opl.service.loans.service.vehicledetails.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.loans.model.CurrentOperatedVehicleRequest;
import com.opl.mudra.api.loans.model.OperatorRequest;
import com.opl.mudra.api.loans.model.PastVehicleLoanRequest;
import com.opl.mudra.api.loans.model.ProposedVehicleRequest;
import com.opl.mudra.api.loans.model.VehicleOperatorRequest;
import com.opl.service.loans.domain.CurrentOperatedVehicleDetail;
import com.opl.service.loans.domain.OperatorDetail;
import com.opl.service.loans.domain.PastVehicleLoanDetail;
import com.opl.service.loans.domain.ProposedVehicleDetail;
import com.opl.service.loans.domain.VehicleOperatorDetail;
import com.opl.service.loans.repository.CurrentOperatedVehicleDetailRepository;
import com.opl.service.loans.repository.OperatorDetailRepository;
import com.opl.service.loans.repository.PastVehicleLoanDetailRepository;
import com.opl.service.loans.repository.ProposedVehicleDetailRepository;
import com.opl.service.loans.repository.VehicleOperatorDetailRepository;
import com.opl.service.loans.service.vehicledetails.VehicleOperatorService;

@Service
@Transactional
public class VehicleOperatorServiceImpl implements VehicleOperatorService{

	@Autowired
	VehicleOperatorDetailRepository vehicleOperatorDetailRepository;
	
	@Autowired
	CurrentOperatedVehicleDetailRepository currentOperatedVehicleDetailRepository;

	@Autowired
	PastVehicleLoanDetailRepository pastVehicleLoanDetailRepository;
	
	@Autowired
	OperatorDetailRepository operatorDetailRepository;
	
	@Autowired
	ProposedVehicleDetailRepository proposedVehicleDetailRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleOperatorServiceImpl.class);
	
	@Override
	public VehicleOperatorRequest getByApplicationId(Long applicationId) {
		VehicleOperatorRequest request = new VehicleOperatorRequest();
		VehicleOperatorDetail vehicleOperatorDetail = vehicleOperatorDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		if(vehicleOperatorDetail != null) {
			BeanUtils.copyProperties(vehicleOperatorDetail, request);
			
			if(vehicleOperatorDetail.getVehicleOperateIn() != null) {
				request.setVehicleOperateIn(Arrays.asList(vehicleOperatorDetail.getVehicleOperateIn().split("\\s*,\\s*")));
			}
		}
		
		
		List<CurrentOperatedVehicleDetail> currentOperatedVehicleDetails = currentOperatedVehicleDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		List<CurrentOperatedVehicleRequest> currentOperatedVehicleRequests = new ArrayList<>();
		
		if(currentOperatedVehicleDetails != null && !currentOperatedVehicleDetails.isEmpty()) {
			for(CurrentOperatedVehicleDetail currentOperatedVehicleDetail : currentOperatedVehicleDetails) {
				CurrentOperatedVehicleRequest req = new CurrentOperatedVehicleRequest();
				BeanUtils.copyProperties(currentOperatedVehicleDetail, req);
				currentOperatedVehicleRequests.add(req);
			}
			request.setCurrentOperatedVehicleDetails(currentOperatedVehicleRequests);
		}
		
		List<PastVehicleLoanDetail> pastVehicleLoanDetails = pastVehicleLoanDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		List<PastVehicleLoanRequest> pastVehicleLoanRequests = new ArrayList<>();
		if(pastVehicleLoanDetails != null && !pastVehicleLoanDetails.isEmpty()) {
			for(PastVehicleLoanDetail pastVehicleLoanDetail : pastVehicleLoanDetails) {
				PastVehicleLoanRequest req = new PastVehicleLoanRequest();
				BeanUtils.copyProperties(pastVehicleLoanDetail, req);
				pastVehicleLoanRequests.add(req);
			}
			request.setPastVehicleLoanDetails(pastVehicleLoanRequests);
		}
		
		List<OperatorDetail> operatorDetails = operatorDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		List<OperatorRequest> operatorRequests = new ArrayList<>();
		if(operatorDetails != null && !operatorDetails.isEmpty()) {
			for(OperatorDetail operatorDetail : operatorDetails) {
				OperatorRequest req = new OperatorRequest();
				BeanUtils.copyProperties(operatorDetail, req);
				operatorRequests.add(req);
			}
			request.setOperatorDetails(operatorRequests);
		}
		
		List<ProposedVehicleDetail> proposedVehicleDetails = proposedVehicleDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		List<ProposedVehicleRequest> proposedVehicleRequests = new ArrayList<>();
		if(proposedVehicleDetails != null && !proposedVehicleDetails.isEmpty()) {
			for(ProposedVehicleDetail proposedVehicleDetail : proposedVehicleDetails) {
				ProposedVehicleRequest req = new ProposedVehicleRequest();
				BeanUtils.copyProperties(proposedVehicleDetail, req);
				proposedVehicleRequests.add(req);
			}
			request.setProposedVehicleDetails(proposedVehicleRequests);
		}
		
		return request;
	}

	@Override
	public Boolean saveDetail(VehicleOperatorRequest req, Long userId) {
		VehicleOperatorDetail vehicleOperatorDetail = null;
		if(req != null) {
			logger.info("req getIsCurrentlyVehicleOperated "+req.getIsCurrentlyVehicleOperated());
			vehicleOperatorDetail =  req.getId() != null ? vehicleOperatorDetailRepository.findOne(req.getId()) : null;
			if(vehicleOperatorDetail != null) {
				BeanUtils.copyProperties(req, vehicleOperatorDetail, "id");
				vehicleOperatorDetail.setModifiedBy(userId);
				vehicleOperatorDetail.setModifiedDate(new Date());
			}else {
				vehicleOperatorDetail = new VehicleOperatorDetail();
				BeanUtils.copyProperties(req, vehicleOperatorDetail, "id");
				vehicleOperatorDetail.setCreatedBy(userId);
				vehicleOperatorDetail.setCreatedDate(new Date());
				vehicleOperatorDetail.setIsActive(true);
			}
			
			if(req.getVehicleOperateIn() != null && !req.getVehicleOperateIn().isEmpty()) {
				vehicleOperatorDetail.setVehicleOperateIn(String.join(",", req.getVehicleOperateIn()));
			}
			
			if(req.getCurrentOperatedVehicleDetails() != null) {
				currentOperatedVehicleDetailRepository.deleteByApplicationId(req.getApplicationId());
				List<CurrentOperatedVehicleDetail> list = new ArrayList<CurrentOperatedVehicleDetail>();
				
				for(CurrentOperatedVehicleRequest currentOperatedVehicleRequest : req.getCurrentOperatedVehicleDetails()) {
					CurrentOperatedVehicleDetail currentOperatedVehicleDetail = new CurrentOperatedVehicleDetail();
					BeanUtils.copyProperties(currentOperatedVehicleRequest, currentOperatedVehicleDetail, "id");
					currentOperatedVehicleDetail.setApplicationId(req.getApplicationId());
					currentOperatedVehicleDetail.setCreatedBy(userId);
					currentOperatedVehicleDetail.setCreatedDate(new Date());
					currentOperatedVehicleDetail.setIsActive(true);
					list.add(currentOperatedVehicleDetail);
				}
				currentOperatedVehicleDetailRepository.save(list);
			}
			
			if(req.getPastVehicleLoanDetails() != null) {
				pastVehicleLoanDetailRepository.deleteByApplicationId(req.getApplicationId());
				List<PastVehicleLoanDetail> list = new ArrayList<PastVehicleLoanDetail>();
				
				for(PastVehicleLoanRequest pastVehicleLoanRequest : req.getPastVehicleLoanDetails()) {
					PastVehicleLoanDetail pastVehicleLoanDetail = new PastVehicleLoanDetail();
					BeanUtils.copyProperties(pastVehicleLoanRequest, pastVehicleLoanDetail, "id");
					pastVehicleLoanDetail.setApplicationId(req.getApplicationId());
					pastVehicleLoanDetail.setCreatedBy(userId);
					pastVehicleLoanDetail.setCreatedDate(new Date());
					pastVehicleLoanDetail.setIsActive(true);
					list.add(pastVehicleLoanDetail);
				}
				pastVehicleLoanDetailRepository.save(list);
			}
			
			if(req.getOperatorDetails() != null) {
				operatorDetailRepository.deleteByApplicationId(req.getApplicationId());
				List<OperatorDetail> list = new ArrayList<OperatorDetail>();
				
				for(OperatorRequest operatorRequest : req.getOperatorDetails()) {
					OperatorDetail operatorDetail = new OperatorDetail();
					BeanUtils.copyProperties(operatorRequest, operatorDetail, "id");
					operatorDetail.setApplicationId(req.getApplicationId());
					operatorDetail.setCreatedBy(userId);
					operatorDetail.setCreatedDate(new Date());
					operatorDetail.setIsActive(true);
					list.add(operatorDetail);
				}
				operatorDetailRepository.save(list);
			}
			
			if(req.getProposedVehicleDetails() != null) {
				proposedVehicleDetailRepository.deleteByApplicationId(req.getApplicationId());
				List<ProposedVehicleDetail> list = new ArrayList<ProposedVehicleDetail>();
				
				for(ProposedVehicleRequest proposedVehicleRequest : req.getProposedVehicleDetails()) {
					ProposedVehicleDetail proposedVehicleDetail = new ProposedVehicleDetail();
					BeanUtils.copyProperties(proposedVehicleRequest, proposedVehicleDetail, "id");
					proposedVehicleDetail.setApplicationId(req.getApplicationId());
					proposedVehicleDetail.setCreatedBy(userId);
					proposedVehicleDetail.setCreatedDate(new Date());
					proposedVehicleDetail.setIsActive(true);
					list.add(proposedVehicleDetail);
				}
				proposedVehicleDetailRepository.save(list);
			}
			
			if(vehicleOperatorDetailRepository.save(vehicleOperatorDetail) != null) {
				return true;
			}
		}
		return false;
	}

}
