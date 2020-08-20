package com.opl.service.loans.service.common;

import java.util.Date;

import com.opl.mudra.api.loans.model.common.LogDetailsModel;

public interface LogService {
public Boolean save(LogDetailsModel logDetailsModel);
public Boolean saveFsLog(Long applicationId,Integer logType);
public Boolean saveFsLog(Long applicationId,Integer logType, Long proposalMapId);
public Date getDateByLogType(Long applicationId,Integer logType);

}
