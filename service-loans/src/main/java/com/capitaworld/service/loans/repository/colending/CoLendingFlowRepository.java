package com.capitaworld.service.loans.repository.colending;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by dhaval.panchal on 14-Aug-19.
 */

public interface CoLendingFlowRepository {
    public Object[] getStageAndStatus(Long userId);

    public Object[] getRatioNbfcBankProduct(Long applicationId);

    public Integer saveBlendedValues(Long applicationId,Long nbfcOrgId,Long bankOrgId,Double blRoi,Double blEmi,Double blProcessingPf);

    public List<BigInteger> getBankList(Long nbfcOrgId);
}
