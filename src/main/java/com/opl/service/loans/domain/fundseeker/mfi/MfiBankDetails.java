package com.opl.service.loans.domain.fundseeker.mfi;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fs_mfi_bank_details")
public class MfiBankDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "account_type")
    private Integer accountType;

    @Column(name = "passbook_img")
    private String passbookImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getPassbookImg() {
        return passbookImg;
    }

    public void setPassbookImg(String passbookImg) {
        this.passbookImg = passbookImg;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
