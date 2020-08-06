package com.opl.mudra.api.enums.oneform;

public enum AssessmentMethods {

	NORMAL_WORKING_CAPITAL(1, "Working Capital Method - Historical Cash Flow method", "Working Capital Method - Historical Cash Flow method", 1,1),
    TERM_LOAN(2, "Term Loan Method - Based on Latest Year Cash Accrual", "Term Loan Method - Based on Latest Year Cash Accrual ", 2,1),
    EQUIPMENT_MACHINERY(3, "Equipment Finance - Fixed Assets to Turnover & Margin Method", "Equipment Finance - Fixed Assets to Turnover & Margin Method", 2,1),
    WORKING_CAPITAL_TERM_LOAN(4, "Working Capital Term Loan Method", "Working Capital Term Loan Method", 16,1),
    WORKING_CAPITAL_NAYAK(5, "Working Capital - Nayak Committee Method", "Working Capital - Nayak Committee Method", 1,1),
    MPBF_METHOD_1(6, "MPBF Method 1", "Working Capital - MPBF Method 1", 1,1),
    MPBF_METHOD_2(7, "MPBF Method 2", "Working Capital - MPBF Method 2", 1,1),
	PAYBACK_METHOD(8,"Term Loan - Equipment Finance - Payback Method", "Term Loan - Equipment Finance - Payback Method", 2,1),
	HYBRID(9,"Term Loan - Equipment Finance - Hybrid Method", "Term Loan - Equipment Finance - Hybrid Method", 2,1),
	NTBTL(10,"New to Business(NTB) Term Loan", "New to Business(NTB) Term Loan", 2,1),
	WORKING_CAPITAL_HYBRID(11,"Working capital Hybrid", "Working capital Hybrid", 1,1),
	UNIVERSAL_METHOD_TL(12,"Term Loan - Universal Method", "Term Loan - Universal Method", 2,1),
	WC_MUDRA(13,"Working Capital - Mudra Loan", "Working Capital - Mudra Loan", 1,10),
	TL_MUDRA(14,"Term Loan - Mudra Loan", "Term Loan - Mudra Loan", 2,10),
	WC_BASED_ON_SALES(15,"Working Capital - Sales Based Assessment", "Working Capital - Sales Based Assessment", 1,21),
	TL_DFS(16,"Term Loan - DFS Loan", "Term Loan - E-DFS  Loan", 2,21),
	TL_ODOP(17,"Term Loan - ODOP Loan", "Term Loan - ODOP Loan", 2,22),
    PL_UNIVERSAL_ELIGIBILITY(18,"Universal Personal Loan Eligibility ", "Universal Personal Loan Eligibility", 7,3),
    PL_ELIGIBILITY_BASED_ON_NMI_(19,"Eligibility  Based on NMI", "Eligibility  Based on NMI", 7,3),
    WC_BASED_ON_PURCHASE(20,"Working Capital- Purchase Based Assessment", "Working Capital- Purchase Based Assessment", 1,21),
	WC_BASED_ON_VFS(22,"Working Capital- WC Based Assessment", "Working Capital- WC Based Assessment", 1,24);
	
	
	
	
    private final Integer id;
    private final String value;
    private final String description;
    private final Integer productId;
    private final Integer businessTypeId;

    AssessmentMethods(Integer id, String value, String description, Integer productId,Integer businessTypeId) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.productId = productId;
        this.businessTypeId = businessTypeId;
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

    public Integer getProductId() {
        return productId;
    }

    public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public static AssessmentMethods getById(Integer id) {
        switch (id) {
            case 1:
            	return NORMAL_WORKING_CAPITAL;
            case 2:
                return TERM_LOAN;
            case 3:
                return EQUIPMENT_MACHINERY;
            case 4:
                return WORKING_CAPITAL_TERM_LOAN;
            case 5:                
                return WORKING_CAPITAL_NAYAK;
            case 6:
                return MPBF_METHOD_1;
            case 7:
                return MPBF_METHOD_2;
            case 8:
                return PAYBACK_METHOD;
            case 9:
                return HYBRID;
            case 10:
                return NTBTL;
            case 11:
                return WORKING_CAPITAL_HYBRID;
            case 12:
                return UNIVERSAL_METHOD_TL;
            case 13:
            	return WC_MUDRA;
            case 14:
            	return TL_MUDRA;
            case 15:
            	return WC_BASED_ON_SALES;
            case 16:
            	return TL_DFS;
            case 17:
            	return TL_ODOP;
            case 18:
                return PL_UNIVERSAL_ELIGIBILITY;
            case 19:
                return PL_ELIGIBILITY_BASED_ON_NMI_;
            case 20:
                return WC_BASED_ON_PURCHASE;
            case 22:
                return WC_BASED_ON_VFS;
            default:
                return null;
        }
    }

    public static AssessmentMethods[] getAll() {
        return AssessmentMethods.values();

    }
}
