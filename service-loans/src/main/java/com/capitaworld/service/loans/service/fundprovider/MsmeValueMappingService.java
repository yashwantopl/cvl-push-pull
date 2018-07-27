package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.corporate.MsmeValueMappingRequest;

import java.util.List;

public interface MsmeValueMappingService {

    public boolean updateMsmeValueMapping(Boolean isNew, Long fpProductId);

    public boolean updateMsmeValueMappingTemp(List<MsmeValueMappingRequest> requestList, Long fpProductId);

    public List<MsmeValueMappingRequest> getDataListFromFpProductId(int dataRequestType,Long fpProductId);
}
