package com.opl.mudra.api.oneform.enums;

public enum RepaymentModeRetail {


    CHECK_OFF (1,"Check Off ","Check Off "),
    ECS_OTHER_ACCOUNT(2,"ECS/Standing Instruction (Other Account) ","ECS/Standing Instruction (Other Account)"),
    ECS_SALARY_ACCOUNT(3,"ECS/Standing Instruction (Salary Account) ","ECS/Standing Instruction (Salary Account) "),
    POST_DATED_CHEQUES(4,"Post Dated Cheques","Post Dated Cheques"),
    OTHERS(5,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    RepaymentModeRetail(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static RepaymentModeRetail[] getAll() {
        return RepaymentModeRetail.values();

    }

    public static RepaymentModeRetail getById(Integer id) {

        switch (id) {
            case 1:
                return CHECK_OFF;
            case 2:
                return ECS_OTHER_ACCOUNT;
            case 3:
                return ECS_SALARY_ACCOUNT;
            case 4:
                return POST_DATED_CHEQUES;
            case 5:
                return OTHERS;
            default:
                return null;

        }
    }
}
