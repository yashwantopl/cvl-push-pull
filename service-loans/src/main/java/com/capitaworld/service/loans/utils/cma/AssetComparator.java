package com.capitaworld.service.loans.utils.cma;

import java.util.Comparator;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;

public class AssetComparator implements Comparator<AssetsDetails>{
	
	@Override
    public int compare(AssetsDetails o1, AssetsDetails o2) {
        return o1.getYear().compareTo(o2.getYear());
    }
}
