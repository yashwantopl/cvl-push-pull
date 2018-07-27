package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.corporate.MsmeValueMappingRequest;

import java.util.List;

public interface MsmeValueMappingService {

    public boolean updateMsmeValueMapping(Boolean isNew, Long fpProductId);

    public boolean updateMsmeValueMappingTemp(List<Integer> requestList, Long fpProductId, Long userId);

    public List<Integer> getDataListFromFpProductId(int dataRequestType,Long fpProductId, Long userId);
}
