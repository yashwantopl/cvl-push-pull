package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFIConversationRes {

    private static final long serialVersionUID = 1L;

    private List<MFIConversationReq> selfToSubConversation;
    private List<MFIConversationReq> selfToSuperConversation;

    public List<MFIConversationReq> getSelfToSubConversation() {
        return selfToSubConversation;
    }

    public void setSelfToSubConversation(List<MFIConversationReq> selfToSubConversation) {
        this.selfToSubConversation = selfToSubConversation;
    }

    public List<MFIConversationReq> getSelfToSuperConversation() {
        return selfToSuperConversation;
    }

    public void setSelfToSuperConversation(List<MFIConversationReq> selfToSuperConversation) {
        this.selfToSuperConversation = selfToSuperConversation;
    }
}
