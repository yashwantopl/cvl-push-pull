package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.common.LogDetails;
import com.capitaworld.service.loans.model.common.LogDetailsModel;
import com.capitaworld.service.loans.repository.common.LogDetailsRepository;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class LogServiceImpl implements LogService {
	private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

	private static final String SAVE_FS_LOG = "saveFsLog";

	@Autowired
	private LogDetailsRepository logDetailsRepository;

	@Override
	public Boolean save(LogDetailsModel logDetailsModel) {
		CommonDocumentUtils.startHook(logger, "save");
		try {
			if (!CommonUtils.isObjectNullOrEmpty(logDetailsModel)) {
				LogDetails logDetails = new LogDetails();
				BeanUtils.copyProperties(logDetailsModel, logDetails);
				logDetails.setCreatedDate(new Date());
				logDetailsRepository.save(logDetails);
				CommonDocumentUtils.endHook(logger, "save");
				return true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		CommonDocumentUtils.endHook(logger, "save");
		return false;

	}

	@Override
	public Boolean saveFsLog(Long applicationId, Integer logType) {
		CommonDocumentUtils.startHook(logger, SAVE_FS_LOG);
		try {
			LogDetails logDetails = new LogDetails();
			logDetails.setLoanApplicationMasterId(applicationId);
			logDetails.setDateTypeMasterId(logType);
			logDetails.setCreatedDate(new Date());
			logDetailsRepository.save(logDetails);
			CommonDocumentUtils.endHook(logger, SAVE_FS_LOG);
			return true;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		CommonDocumentUtils.endHook(logger, SAVE_FS_LOG);
		return false;
	}

	@Override
	public Boolean saveFsLog(Long applicationId, Integer logType, Long proposalMapId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveFsLog");
		try {
			LogDetails logDetails = new LogDetails();
			logDetails.setLoanApplicationMasterId(applicationId);
			logDetails.setProductMappingId(proposalMapId);
			logDetails.setDateTypeMasterId(logType);
			logDetails.setCreatedDate(new Date());
			logDetailsRepository.save(logDetails);
			CommonDocumentUtils.endHook(logger, "saveFsLog");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}
		CommonDocumentUtils.endHook(logger, "saveFsLog");
		return false;
	}
	
	@Override
	public Date getDateByLogType(Long applicationId, Integer logType) {
		CommonDocumentUtils.startHook(logger, "getDateByLogType");
		try {
			return logDetailsRepository.getDateByLogType(applicationId, logType);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		CommonDocumentUtils.endHook(logger, "getDateByLogType");
		return null;
	}

}
