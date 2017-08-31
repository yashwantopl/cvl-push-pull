package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.common.LogDetailsModel;

public interface LogService {
public Boolean save(LogDetailsModel logDetailsModel);
public Boolean saveFsLog(Long applicationId,Integer logType);
}
