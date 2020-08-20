package com.opl.mudra.api.user.model;

import java.util.List;

/**
 * Created by dhaval.panchal on 18-Oct-18.
 */
public class BankUserDumpRespose {
    private List<BankUserInsertedResponse> bankUserInserted;
    private List<BankUserNotInsertedResponse> bankUserNotInserted;

    public List<BankUserInsertedResponse> getBankUserInserted() {
        return bankUserInserted;
    }

    public void setBankUserInserted(List<BankUserInsertedResponse> bankUserInserted) {
        this.bankUserInserted = bankUserInserted;
    }

    public List<BankUserNotInsertedResponse> getBankUserNotInserted() {
        return bankUserNotInserted;
    }

    public void setBankUserNotInserted(List<BankUserNotInsertedResponse> bankUserNotInserted) {
        this.bankUserNotInserted = bankUserNotInserted;
    }
}
