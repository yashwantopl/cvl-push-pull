package com.capitaworld.service.loans.repository.colending;

/**
 * Created by dhaval.panchal on 14-Aug-19.
 */

public interface CoLendingFlowRepository {
    public Object[] getStageAndStatus(Long userId);
}
