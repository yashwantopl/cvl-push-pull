package com.opl.mudra.api.analyzer.model.finbit;

import java.util.List;

/**
 * Created by ravina.panchal on 12-09-2018.
 */
public class UploadStatementResponse {

    private String status;
    private String message;
    private String invalidBank;
    private List<FinbitAccountList> accountList;
    private String dataStatus;
    private List<FinBitUploadError> errors;
    private String accountUID;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

	public List<FinBitUploadError> getErrors() {
		return errors;
	}


	public void setErrors(List<FinBitUploadError> errors) {
		this.errors = errors;
	}



	public List<FinbitAccountList> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<FinbitAccountList> accountList) {
		this.accountList = accountList;
	}

    public String getInvalidBank() {
        return invalidBank;
    }

    public void setInvalidBank(String invalidBank) {
        this.invalidBank = invalidBank;
    }
    
    

    public String getAccountUID() {
		return accountUID;
	}

	public void setAccountUID(String accountUID) {
		this.accountUID = accountUID;
	}

	@Override
    public String toString() {
        return "UploadStatementResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", invalidBank='" + invalidBank + '\'' +
                ", accountList=" + accountList +
                ", dataStatus='" + dataStatus + '\'' +
                ", errors=" + errors +
                '}';
    }
}
