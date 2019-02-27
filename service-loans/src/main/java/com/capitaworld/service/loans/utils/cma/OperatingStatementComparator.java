package com.capitaworld.service.loans.utils.cma;

import java.util.Comparator;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;

public class OperatingStatementComparator implements Comparator<OperatingStatementDetails>{
	
	@Override
    public int compare(OperatingStatementDetails o1, OperatingStatementDetails o2) {
        return o1.getYear().compareTo(o2.getYear());
    }
}
