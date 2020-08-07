package com.opl.mudra.api.mca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryAnnualReturn {
	

    @JsonProperty("as-on-date")
    private String asOnDate;
    
    @JsonProperty("financial-year")
    private String financialYear;
    
    @JsonProperty("promoters-equity-indian-no-of-shares")
    private String peIndianNoOfShares;
    
    @JsonProperty("promoters-equity-indian-percentage")
    private String peIndianPercentage;
    
    @JsonProperty("promoters-equity-nri-no-of-shares")
    private String peNriNoOfShares;
    
    @JsonProperty("promoters-equity-nri-percentage")
    private String peNriPercentage;
    
    @JsonProperty("promoters-equity-foreign-national-(other-than-nri)-no-of-shares")
    private String peForeignNationalOtnNoOfShares;
    
    @JsonProperty("promoters-equity-foreign-national-(other-than-nri)-percentage")
    private String pe_foreign_national_otn_percentage;
    
    @JsonProperty("promoters-equity-central-government-no-of-shares")
    private String pe_central_government_no_of_shares;
    
    @JsonProperty("promoters-equity-central-government-percentage")
    private String peCentralGovernmentPercentage;
    
    @JsonProperty("promoters-equity-state-government-no-of-shares")
    private String peStateGovernmentNoOfShares;
    
    @JsonProperty("promoters-equity-state-government-percentage")
    private String peStateGovernmentPercentage;
    
    @JsonProperty("promoters-equity-government-companies-no-of-shares")
    private String peGovernmentCompaniesNoOfShares;
    
    @JsonProperty("promoters-equity-government-companies-percentage")
    private String peGovernmentCompaniesPercentage;
    
    @JsonProperty("promoters-equity-insurance-no-of-shares")
    private String peInsuranceNoOfShares;
    
    @JsonProperty("promoters-equity-insurance-companies-percentage")
    private String peInsuranceCompaniesPercentage;
    
    @JsonProperty("promoters-equity-banks-no-of-shares")
    private String peBanksNoOfShares;
    
    @JsonProperty("promoters-equity-banks-companies-percentage")
    private String peBanksCompaniesPercentage;
    
    @JsonProperty("promoters-equity-financial-institutions-no-of-shares")
    private String peFinancialInstitutionsNoOfShares;
    
    @JsonProperty("promoters-equity-financial-institutions-companies-percentage")
    private String peFinancialInstitutionsCompaniesPercentage;
    
    @JsonProperty("promoters-equity-foreign-institutional-investors-no-of-shares")
    private String peForeignInstitutionalInvestorsNoOfShares;
    
    @JsonProperty("promoters-equity-foreign-institutional-investors-percentage")
    private String peForeignInstitutionalInvestorsPercentage;
    
    @JsonProperty("promoters-equity-mutual-funds-no-of-shares")
    private String peMutualFundsNoOfShares;
    
    @JsonProperty("promoters-equity-mutual-funds-percentage")
    private String peMutualFundsPercentage;
    
    @JsonProperty("promoters-equity-venture-capital-no-of-shares")
    private String peVentureCapitalNoOfShares;
    
    @JsonProperty("promoters-equity-venture-capital-percentage")
    private String peVentureCapitalPercentage;
    
    @JsonProperty("promoters-equity-body-corporate-no-of-shares")
    private String peBodyCorporateNoOfShares;
    
    @JsonProperty("promoters-equity-body-corporate-percentage")
    private String peBodyCorporatePercentage;
    
    @JsonProperty("promoters-equity-others")
    private String peOthers;
    
    @JsonProperty("promoters-equity-others-no-of-shares")
    private String peOthersNoOfShares;
    
    @JsonProperty("promoters-equity-others-percentage")
    private String peOthersPercentage;
    
    @JsonProperty("promoters-equity-total-no-of-shares")
    private String peTotalNoOfShares;
    
    @JsonProperty("promoters-equity-total-percentage")
    private String peTotalPercentage;
    
    @JsonProperty("promoters-preference-indian-no-of-shares")
    private String ppIndianNoOfShares;
    
    @JsonProperty("promoters-preference-indian-percentage")
    private String ppIndianPercentage;
    
    @JsonProperty("promoters-preference-nri-no-of-shares")
    private String ppNriNoOfShares;
    
    @JsonProperty("promoters-preference-nri-percentage")
    private String ppNriPercentage;
    
    @JsonProperty("promoters-preference-foreign-national-(other-than-nri)-no-of-shares")
    private String ppForeignNationalOtnNoOfShares;
    
    @JsonProperty("promoters-preference-foreign-national-(other-than-nri)-percentage")
    private String ppForeignNationalOtnPercentage;
    
    @JsonProperty("promoters-preference-central-government-no-of-shares")
    private String ppCentralGovernmentNoOfShares;
    
    @JsonProperty("promoters-preference-central-government-percentage")
    private String ppCentralGovernmentPercentage;
    
    @JsonProperty("promoters-preference-state-government-no-of-shares")
    private String ppStateGovernmentNoOfShares;
    
    @JsonProperty("promoters-preference-state-government-percentage")
    private String ppStateGovernmentPercentage;
    
    @JsonProperty("promoters-preference-government-companies-no-of-shares")
    private String ppGovernmentCompaniesNoOfShares;
    
    @JsonProperty("promoters-preference-government-companies-percentage")
    private String ppGovernmentCompaniesPercentage;
    
    @JsonProperty("promoters-preference-insurance-no-of-shares")
    private String ppInsuranceNoOfShares;
    
    @JsonProperty("promoters-preference-insurance-companies-percentage")
    private String ppInsuranceCompaniesPercentage;
    
    @JsonProperty("promoters-preference-banks-no-of-shares")
    private String ppBanksNoOfShares;
    
    @JsonProperty("promoters-preference-banks-companies-percentage")
    private String ppBanksCompaniesPercentage;
    
    @JsonProperty("promoters-preference-financial-institutions-no-of-shares")
    private String ppFinancialInstitutionsNoOfShares;
    
    @JsonProperty("promoters-preference-financial-institutions-companies-percentage")
    private String ppFinancialInstitutionsCompaniesPercentage;
    
    @JsonProperty("promoters-preference-foreign-institutional-investors-no-of-shares")
    private String ppForeignInstitutionalInvestorsNoOfShares;
    
    @JsonProperty("promoters-preference-foreign-institutional-investors-percentage")
    private String ppForeignInstitutionalInvestorsPercentage;
    
    @JsonProperty("promoters-preference-mutual-funds-no-of-shares")
    private String ppMutualFundsNoOfShares;
    
    @JsonProperty("promoters-preference-mutual-funds-percentage")
    private String ppMutualFundsPercentage;
    
    @JsonProperty("promoters-preference-venture-capital-no-of-shares")
    private String ppVentureCapitalNoOfShares;
    
    @JsonProperty("promoters-preference-venture-capital-percentage")
    private String ppVentureCapitalPercentage;
    
    @JsonProperty("promoters-preference-body-corporate-no-of-shares")
    private String ppBodyCorporateNoOfShares;
    
    @JsonProperty("promoters-preference-body-corporate-percentage")
    private String ppBodyCorporatePercentage;
    
    @JsonProperty("promoters-preference-others")
    private String ppOthers;
    
    @JsonProperty("promoters-preference-others-no-of-shares")
    private String ppOthersNoOfShares;
    
    @JsonProperty("promoters-preference-others-percentage")
    private String ppOthersPercentage;
    
    @JsonProperty("promoters-preference-total-no-of-shares")
    private String ppTotalNoOfShares;
    
    @JsonProperty("promoters-preference-total-percentage")
    private String ppTotalPercentage;
    
    @JsonProperty("other-than-promoters-equity-indian-no-of-shares")
    private String otpeIndianNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-indian-percentage")
    private String otpeIndianPercentage;
    
    @JsonProperty("other-than-promoters-equity-nri-no-of-shares")
    private String otpeNriNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-nri-percentage")
    private String otpeNriPercentage;
    
    @JsonProperty("other-than-promoters-equity-foreign-national-(other-than-nri)-no-of-shares")
    private String otpeForeignNationalOtnNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-foreign-national-(other-than-nri)-percentage")
    private String otpeForeignNationalOtnPercentage;
    
    @JsonProperty("other-than-promoters-equity-central-government-no-of-shares")
    private String otpeCentralGovernmentNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-central-government-percentage")
    private String otpeCentralGovernmentPercentage;
    
    @JsonProperty("other-than-promoters-equity-state-government-no-of-shares")
    private String otStateGovernmentNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-state-government-percentage")
    private String otpetateGovernmentPercentage;
    
    @JsonProperty("other-than-promoters-equity-government-companies-no-of-shares")
    private String otpeGovernmentCompaniesNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-government-companies-percentage")
    private String otpeGovernmentCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-equity-insurance-no-of-shares")
    private String otpeInsuranceNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-insurance-companies-percentage")
    private String otpeInsuranceCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-equity-banks-no-of-shares")
    private String otpeBanksNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-banks-companies-percentage")
    private String otpeBanksCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-equity-financial-institutions-no-of-shares")
    private String otpeFiNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-financial-institutions-companies-percentage")
    private String otpeFiCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-equity-foreign-institutional-investors-no-of-shares")
    private String otpeForeignInstitutionalInvestorsNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-foreign-institutional-investors-percentage")
    private String otpeForeignInstitutionalInvestorsPercentage;
    
    @JsonProperty("other-than-promoters-equity-mutual-funds-no-of-shares")
    private String otpeMutualFundsNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-mutual-funds-percentage")
    private String otpeMutualFundsPercentage;
    
    @JsonProperty("other-than-promoters-equity-venture-capital-no-of-shares")
    private String otpeVentureCapitalNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-venture-capital-percentage")
    private String otpeVentureCapitalPercentage;
    
    @JsonProperty("other-than-promoters-equity-body-corporate-no-of-shares")
    private String otpeBodyCorporateNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-body-corporate-percentage")
    private String otpeBodyCorporatePercentage;
    
    @JsonProperty("other-than-promoters-equity-others")
    private String otpeOthers;
    
    @JsonProperty("other-than-promoters-equity-others-no-of-shares")
    private String otpeOthersNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-others-percentage")
    private String otpeOthersPercentage;
    
    @JsonProperty("other-than-promoters-equity-total-no-of-shares")
    private String otpeTotalNoOfShares;
    
    @JsonProperty("other-than-promoters-equity-total-percentage")
    private String otpeTotalPercentage;
    
    @JsonProperty("other-than-promoters-preference-indian-no-of-shares")
    private String otppIndianNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-indian-percentage")
    private String otppIndianPercentage;
    
    @JsonProperty("other-than-promoters-preference-nri-no-of-shares")
    private String otppNriNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-nri-percentage")
    private String otppNriPercentage;
    
    @JsonProperty("other-than-promoters-preference-foreign-national-(other-than-nri)-no-of-shares")
    private String otpp_foreign_national_otn_no_of_shares;
    
    @JsonProperty("other-than-promoters-preference-foreign-national-(other-than-nri)-percentage")
    private String otpp_foreign_national_otn_percentage;
    
    @JsonProperty("other-than-promoters-preference-central-government-no-of-shares")
    private String otppCentralGovernmentNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-central-government-percentage")
    private String otppCentralGovernmentPercentage;
    
    @JsonProperty("other-than-promoters-preference-state-government-no-of-shares")
    private String otppStateGovernmentNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-state-government-percentage")
    private String otppStateGovernmentPercentage;
    
    @JsonProperty("other-than-promoters-preference-government-companies-no-of-shares")
    private String otppGovernmentCompaniesNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-government-companies-percentage")
    private String otppGovernmentCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-preference-insurance-no-of-shares")
    private String otppInsuranceNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-insurance-companies-percentage")
    private String otppInsuranceCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-preference-banks-no-of-shares")
    private String otppBanksNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-banks-companies-percentage")
    private String otppBanksCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-preference-financial-institutions-no-of-shares")
    private String otppFinancialInstitutionsNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-financial-institutions-companies-percentage")
    private String otppFinancialInstitutionsCompaniesPercentage;
    
    @JsonProperty("other-than-promoters-preference-foreign-institutional-investors-no-of-shares")
    private String otppForeignInstitutionalInvestorsNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-foreign-institutional-investors-percentage")
    private String otppForeignInstitutionalInvestorsPercentage;
    
    @JsonProperty("other-than-promoters-preference-mutual-funds-no-of-shares")
    private String otppMutualFundsNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-mutual-funds-percentage")
    private String otppMutualFundsPercentage;
    
    @JsonProperty("other-than-promoters-preference-venture-capital-no-of-shares")
    private String otppVentureCapitalNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-venture-capital-percentage")
    private String otppVentureCapitalPercentage;
    
    @JsonProperty("other-than-promoters-preference-body-corporate-no-of-shares")
    private String otppBodyCorporateNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-body-corporate-percentage")
    private String otppBodyCorporatePercentage;
    
    @JsonProperty("other-than-promoters-preference-others")
    private String otppOthers;
    
    @JsonProperty("other-than-promoters-preference-others-no-of-shares")
    private String otppOthersNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-others-percentage")
    private String otppOthersPercentage;
    
    @JsonProperty("other-than-promoters-preference-total-no-of-shares")
    private String otppTotalNoOfShares;
    
    @JsonProperty("other-than-promoters-preference-total-percentage")
    private String otppTotalPercentage;
    
    @JsonProperty("unclassified-equity-no-of-shares")
    private String unclassifiedEquityNoOfShares;
    
    @JsonProperty("unclassified-equity-shares-percentage")
    private String unclassifiedEquitySharesPercentage;
    
    @JsonProperty("equity-shares-without-differential-rights")
    private List<Object> equitySharesWithoutDifferentialRights = null;
    
    @JsonProperty("unclassified-preference-no-of-shares")
    private String unclassifiedPreferenceNoOfShares;
    
    @JsonProperty("unclassified-preference-shares-percentage")
    private String unclassifiedPreferenceSharesPercentage;
    
    @JsonProperty("preference-shares-without-differential-rights")
    private List<Object> preferenceSharesWithoutDifferentialRights = null;
    
    @JsonProperty("dummy-promoters-equity-indian-message")
    private String dpeIndianMessage;
    
    @JsonProperty("dummy-promoters-equity-indian-percentage-formatted")
    private String dpeIndianPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-nri-message")
    private String dpeNriMessage;
    
    @JsonProperty("dummy-promoters-equity-nri-percentage-formatted")
    private String dpeNriPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-foreign-national-(other-than-nri)-message")
    private String dpeForeignNationalOtnMessage;
    
    @JsonProperty("dummy-promoters-equity-foreign-national-(other-than-nri)-percentage-formatted")
    private String dpeForeignNationalOtnPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-central-government-message")
    private String dpeCentralGovernmentMessage;
    
    @JsonProperty("dummy-promoters-equity-central-government-percentage-formatted")
    private String dpeCentralGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-state-government-message")
    private String dpeStateGovernmentMessage;
    
    @JsonProperty("dummy-promoters-equity-state-government-percentage-formatted")
    private String dpeStateGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-government-companies-message")
    private String dpeGovernmentCompaniesMessage;
    
    @JsonProperty("dummy-promoters-equity-government-companies-percentage-formatted")
    private String dpeGovernmentCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-insurance-companies-percentage-formatted")
    private String dpeInsuranceCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-banks-message")
    private String dpeBanksMessage;
    
    @JsonProperty("dummy-promoters-equity-banks-companies-percentage-formatted")
    private String dpeBanksCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-financial-institutions-message")
    private String dpeFinancialInstitutionsMessage;
    
    @JsonProperty("dummy-promoters-equity-financial-institutions-companies-percentage-formatted")
    private String dpeFinancialInstitutionsCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-foreign-institutional-investors-message")
    private String dpeForeignInstitutionalInvestorsMessage;
    
    @JsonProperty("dummy-promoters-equity-foreign-institutional-investors-percentage-formatted")
    private String dpeForeignInstitutionalInvestorsPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-mutual-funds-message")
    private String dpeMutualFundsMessage;
    
    @JsonProperty("dummy-promoters-equity-mutual-funds-percentage-formatted")
    private String dpeMutualFundsPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-venture-capital-message")
    private String dpeVentureCapitalMessage;
    
    @JsonProperty("dummy-promoters-equity-venture-capital-percentage-formatted")
    private String dpeVentureCapitalPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-body-corporate-message")
    private String dpeBodyCorporateMessage;
    
    @JsonProperty("dummy-promoters-equity-body-corporate-percentage-formatted")
    private String dpeBodyCorporatePercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-others-message")
    private String dpeOthersMessage;
    
    @JsonProperty("dummy-promoters-equity-others-percentage-formatted")
    private String dpeOthersPercentageFormatted;
    
    @JsonProperty("dummy-promoters-equity-total-message")
    private String dpeTotalMessage;
    
    @JsonProperty("dummy-promoters-equity-total-percentage-formatted")
    private String dpeTotalPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-indian-message")
    private String dppIndianMessage;
    
    @JsonProperty("dummy-promoters-preference-indian-percentage-formatted")
    private String dppIndianPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-nri-message")
    private String dppNriMessage;
    
    @JsonProperty("dummy-promoters-preference-nri-percentage-formatted")
    private String dppNriPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-foreign-national-(other-than-nri)-message")
    private String dppForeignNationalOtnMessage;
    
    @JsonProperty("dummy-promoters-preference-foreign-national-(other-than-nri)-percentage-formatted")
    private String dppForeignNationalOtnPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-central-government-message")
    private String dppCentralGovernmentMessage;
    
    @JsonProperty("dummy-promoters-preference-central-government-percentage-formatted")
    private String dppCentralGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-state-government-message")
    private String dppStateGovernmentMessage;
    
    @JsonProperty("dummy-promoters-preference-state-government-percentage-formatted")
    private String dppStateGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-government-companies-message")
    private String dppGovernmentCompaniesMessage;
    
    @JsonProperty("dummy-promoters-preference-government-companies-percentage-formatted")
    private String dppGovernmentCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-insurance-companies-percentage-formatted")
    private String dppInsuranceCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-banks-message")
    private String dppBanksMessage;
    
    @JsonProperty("dummy-promoters-preference-banks-companies-percentage-formatted")
    private String dppBanksCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-financial-institutions-message")
    private String dppFinancialInstitutionsMessage;
    
    @JsonProperty("dummy-promoters-preference-financial-institutions-companies-percentage-formatted")
    private String dppFinancialInstitutionsCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-foreign-institutional-investors-message")
    private String dppForeignInstitutionalInvestorsMessage;
    
    @JsonProperty("dummy-promoters-preference-foreign-institutional-investors-percentage-formatted")
    private String dppForeignInstitutionalInvestorsPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-mutual-funds-message")
    private String dppMutualFundsMessage;
    
    @JsonProperty("dummy-promoters-preference-mutual-funds-percentage-formatted")
    private String dppMutualFundsPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-venture-capital-message")
    private String dppVentureCapitalMessage;
    
    @JsonProperty("dummy-promoters-preference-venture-capital-percentage-formatted")
    private String dppVentureCapitalPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-body-corporate-message")
    private String dppBodyCorporateMessage;
    
    @JsonProperty("dummy-promoters-preference-body-corporate-percentage-formatted")
    private String dppBodyCorporatePercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-others-message")
    private String dppOthersMessage;
    
    @JsonProperty("dummy-promoters-preference-others-percentage-formatted")
    private String dppOthersPercentageFormatted;
    
    @JsonProperty("dummy-promoters-preference-total-message")
    private String dppTotalMessage;
    
    @JsonProperty("dummy-promoters-preference-total-percentage-formatted")
    private String dppTotalPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-indian-message")
    private String dotpeIndianMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-indian-percentage-formatted")
    private String dotpeIndianPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-nri-message")
    private String dotpeNriMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-nri-percentage-formatted")
    private String dotpeNriPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-foreign-national-(other-than-nri)-message")
    private String dotpeForeignNationalOtnMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-foreign-national-(other-than-nri)-percentage-formatted")
    private String dotpeForeignNationalOtnPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-central-government-message")
    private String dotpeCentralGovernmentMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-central-government-percentage-formatted")
    private String dotpeCentralGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-state-government-message")
    private String dotpeStateGovernmentMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-state-government-percentage-formatted")
    private String dotpeStateGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-government-companies-message")
    private String dotpeGovernmentCompaniesMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-government-companies-percentage-formatted")
    private String dotpeGovernmentCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-insurance-companies-percentage-formatted")
    private String dotpeInsuranceCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-banks-message")
    private String dotpeBanksMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-banks-companies-percentage-formatted")
    private String dotpeBanksCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-financial-institutions-message")
    private String dotpeFinancialInstitutionsMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-financial-institutions-companies-percentage-formatted")
    private String dotpeFinancialInstitutionsCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-foreign-institutional-investors-message")
    private String dotpeForeignInstitutionalInvestorsMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-foreign-institutional-investors-percentage-formatted")
    private String dotpeForeignInstitutionalInvestorsPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-mutual-funds-message")
    private String dotpeMutualFundsMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-mutual-funds-percentage-formatted")
    private String dotpeMutualFundsPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-venture-capital-message")
    private String dotpeVentureCapitalMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-venture-capital-percentage-formatted")
    private String dotpeVentureCapitalPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-body-corporate-message")
    private String dotpeBodyCorporateMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-body-corporate-percentage-formatted")
    private String dotpeBodyCorporatePercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-others-message")
    private String dotpeOthersMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-others-percentage-formatted")
    private String dotpeOthersPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-equity-total-message")
    private String dotpeTotalMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-total-percentage-formatted")
    private String dotpeTotalPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-indian-message")
    private String dotppIndianMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-indian-percentage-formatted")
    private String dotppIndianPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-nri-message")
    private String dotppNriMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-nri-percentage-formatted")
    private String dotppNriPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-foreign-national-(other-than-nri)-message")
    private String dotppForeignNationalOtnMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-foreign-national-(other-than-nri)-percentage-formatted")
    private String dotppForeignNationalOtnPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-central-government-message")
    private String dotppCentralGovernmentMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-central-government-percentage-formatted")
    private String dotppCentralGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-state-government-message")
    private String dotppStateGovernmentMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-state-government-percentage-formatted")
    private String dotppStateGovernmentPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-government-companies-message")
    private String dotppGovernmentCompaniesMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-government-companies-percentage-formatted")
    private String dotppGovernmentCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-insurance-companies-percentage-formatted")
    private String dotppInsuranceCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-banks-message")
    private String dotppBanksMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-banks-companies-percentage-formatted")
    private String dotppBanksCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-financial-institutions-message")
    private String dotppFinancialInstitutionsMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-financial-institutions-companies-percentage-formatted")
    private String dotppFinancialInstitutionsCompaniesPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-foreign-institutional-investors-message")
    private String dotppForeignInstitutionalInvestorsMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-foreign-institutional-investors-percentage-formatted")
    private String dotppForeignInstitutionalInvestorsPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-mutual-funds-message")
    private String dotppMutualFundsMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-mutual-funds-percentage-formatted")
    private String dotppMutualFundsPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-venture-capital-message")
    private String dotppVentureCapitalMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-venture-capital-percentage-formatted")
    private String dotherTppvcPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-body-corporate-message")
    private String dotppBodyCorporateMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-body-corporate-percentage-formatted")
    private String dotppBodyCorporatePercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-others-message")
    private String dotppOthersMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-others-percentage-formatted")
    private String dotppOthersPercentageFormatted;
    
    @JsonProperty("dummy-other-than-promoters-preference-total-message")
    private String dotppTotalMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-total-percentage-formatted")
    private String dotppTotalPercentageFormatted;
    
    @JsonProperty("dummy-unclassified-equity-shares-percentage-formatted")
    private String dummyUesPercentageFormatted;
    
    @JsonProperty("dummy-unclassified-preference-shares-percentage-formatted")
    private String dummyUpspFormatted;
    
    @JsonProperty("dummy-promoters-equity-insurance-companies-message")
    private String dpeInsuranceCompaniesMessage;
    
    @JsonProperty("dummy-promoters-preference-insurance-companies-message")
    private String dppInsuranceCompaniesMessage;
    
    @JsonProperty("dummy-other-than-promoters-equity-insurance-companies-message")
    private String dotpeInsuranceCompaniesMessage;
    
    @JsonProperty("dummy-other-than-promoters-preference-insurance-companies-message")
    private String dotppInsuranceCompaniesMessage;
    
    @JsonProperty("dummy-equity-chart-title")
    private String dummyEquityChartTitle;
    
    @JsonProperty("dummy-preference-chart-title")
    private String dpreferenceChartTitle;
    
    @JsonProperty("dummy-promoter-title")
    private String dummyPromoterTitle;
    
    @JsonProperty("dummy-other-than-promoter-title")
    private String dotherThanPromoterTitle;
    
    @JsonProperty("dummy-unclassified-title")
    private String dummyUnclassifiedTitle;
    
    @JsonProperty("dummy-shareholding-title")
    private String dummyShareholdingTitle;
    
    @JsonProperty("dummy-shares-title")
    private String dummySharesTitle;
    
    @JsonProperty("dummy-percentage-title")
    private String dpercentageTitle;
    
    @JsonProperty("dummy-individual-huf-title")
    private String dummyIndividualHufTitle;
    
    @JsonProperty("dummy-government-title")
    private String dummyGovernmentTitle;
     
    @JsonProperty("dummy-equity-shares-without-differential-rights-title")
    private String dummyEquitySharesWithoutDifferentialRightsTitle;
    
    @JsonProperty("dummy-preference-shares-without-differential-rights-title")
    private String dpswdifferentialRightsTitle;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getAsOnDate() {
		return asOnDate;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public String getPeIndianNoOfShares() {
		return peIndianNoOfShares;
	}

	public String getPeIndianPercentage() {
		return peIndianPercentage;
	}

	public String getPeNriNoOfShares() {
		return peNriNoOfShares;
	}

	public String getPeNriPercentage() {
		return peNriPercentage;
	}

	public String getPeForeignNationalOtnNoOfShares() {
		return peForeignNationalOtnNoOfShares;
	}

	public String getPe_foreign_national_otn_percentage() {
		return pe_foreign_national_otn_percentage;
	}

	public String getPe_central_government_no_of_shares() {
		return pe_central_government_no_of_shares;
	}

	public String getPeCentralGovernmentPercentage() {
		return peCentralGovernmentPercentage;
	}

	public String getPeStateGovernmentNoOfShares() {
		return peStateGovernmentNoOfShares;
	}

	public String getPeStateGovernmentPercentage() {
		return peStateGovernmentPercentage;
	}

	public String getPeGovernmentCompaniesNoOfShares() {
		return peGovernmentCompaniesNoOfShares;
	}

	public String getPeGovernmentCompaniesPercentage() {
		return peGovernmentCompaniesPercentage;
	}

	public String getPeInsuranceNoOfShares() {
		return peInsuranceNoOfShares;
	}

	public String getPeInsuranceCompaniesPercentage() {
		return peInsuranceCompaniesPercentage;
	}

	public String getPeBanksNoOfShares() {
		return peBanksNoOfShares;
	}

	public String getPeBanksCompaniesPercentage() {
		return peBanksCompaniesPercentage;
	}

	public String getPeFinancialInstitutionsNoOfShares() {
		return peFinancialInstitutionsNoOfShares;
	}

	public String getPeFinancialInstitutionsCompaniesPercentage() {
		return peFinancialInstitutionsCompaniesPercentage;
	}

	public String getPeForeignInstitutionalInvestorsNoOfShares() {
		return peForeignInstitutionalInvestorsNoOfShares;
	}

	public String getPeForeignInstitutionalInvestorsPercentage() {
		return peForeignInstitutionalInvestorsPercentage;
	}

	public String getPeMutualFundsNoOfShares() {
		return peMutualFundsNoOfShares;
	}

	public String getPeMutualFundsPercentage() {
		return peMutualFundsPercentage;
	}

	public String getPeVentureCapitalNoOfShares() {
		return peVentureCapitalNoOfShares;
	}

	public String getPeVentureCapitalPercentage() {
		return peVentureCapitalPercentage;
	}

	public String getPeBodyCorporateNoOfShares() {
		return peBodyCorporateNoOfShares;
	}

	public String getPeBodyCorporatePercentage() {
		return peBodyCorporatePercentage;
	}

	public String getPeOthers() {
		return peOthers;
	}

	public String getPeOthersNoOfShares() {
		return peOthersNoOfShares;
	}

	public String getPeOthersPercentage() {
		return peOthersPercentage;
	}

	public String getPeTotalNoOfShares() {
		return peTotalNoOfShares;
	}

	public String getPeTotalPercentage() {
		return peTotalPercentage;
	}

	public String getPpIndianNoOfShares() {
		return ppIndianNoOfShares;
	}

	public String getPpIndianPercentage() {
		return ppIndianPercentage;
	}

	public String getPpNriNoOfShares() {
		return ppNriNoOfShares;
	}

	public String getPpNriPercentage() {
		return ppNriPercentage;
	}

	public String getPpForeignNationalOtnNoOfShares() {
		return ppForeignNationalOtnNoOfShares;
	}

	public String getPpForeignNationalOtnPercentage() {
		return ppForeignNationalOtnPercentage;
	}

	public String getPpCentralGovernmentNoOfShares() {
		return ppCentralGovernmentNoOfShares;
	}

	public String getPpCentralGovernmentPercentage() {
		return ppCentralGovernmentPercentage;
	}

	public String getPpStateGovernmentNoOfShares() {
		return ppStateGovernmentNoOfShares;
	}

	public String getPpStateGovernmentPercentage() {
		return ppStateGovernmentPercentage;
	}

	public String getPpGovernmentCompaniesNoOfShares() {
		return ppGovernmentCompaniesNoOfShares;
	}

	public String getPpGovernmentCompaniesPercentage() {
		return ppGovernmentCompaniesPercentage;
	}

	public String getPpInsuranceNoOfShares() {
		return ppInsuranceNoOfShares;
	}

	public String getPpInsuranceCompaniesPercentage() {
		return ppInsuranceCompaniesPercentage;
	}

	public String getPpBanksNoOfShares() {
		return ppBanksNoOfShares;
	}

	public String getPpBanksCompaniesPercentage() {
		return ppBanksCompaniesPercentage;
	}

	public String getPpFinancialInstitutionsNoOfShares() {
		return ppFinancialInstitutionsNoOfShares;
	}

	public String getPpFinancialInstitutionsCompaniesPercentage() {
		return ppFinancialInstitutionsCompaniesPercentage;
	}

	public String getPpForeignInstitutionalInvestorsNoOfShares() {
		return ppForeignInstitutionalInvestorsNoOfShares;
	}

	public String getPpForeignInstitutionalInvestorsPercentage() {
		return ppForeignInstitutionalInvestorsPercentage;
	}

	public String getPpMutualFundsNoOfShares() {
		return ppMutualFundsNoOfShares;
	}

	public String getPpMutualFundsPercentage() {
		return ppMutualFundsPercentage;
	}

	public String getPpVentureCapitalNoOfShares() {
		return ppVentureCapitalNoOfShares;
	}

	public String getPpVentureCapitalPercentage() {
		return ppVentureCapitalPercentage;
	}

	public String getPpBodyCorporateNoOfShares() {
		return ppBodyCorporateNoOfShares;
	}

	public String getPpBodyCorporatePercentage() {
		return ppBodyCorporatePercentage;
	}

	public String getPpOthers() {
		return ppOthers;
	}

	public String getPpOthersNoOfShares() {
		return ppOthersNoOfShares;
	}

	public String getPpOthersPercentage() {
		return ppOthersPercentage;
	}

	public String getPpTotalNoOfShares() {
		return ppTotalNoOfShares;
	}

	public String getPpTotalPercentage() {
		return ppTotalPercentage;
	}

	public String getOtpeIndianNoOfShares() {
		return otpeIndianNoOfShares;
	}

	public String getOtpeIndianPercentage() {
		return otpeIndianPercentage;
	}

	public String getOtpeNriNoOfShares() {
		return otpeNriNoOfShares;
	}

	public String getOtpeNriPercentage() {
		return otpeNriPercentage;
	}

	public String getOtpeForeignNationalOtnNoOfShares() {
		return otpeForeignNationalOtnNoOfShares;
	}

	public String getOtpeForeignNationalOtnPercentage() {
		return otpeForeignNationalOtnPercentage;
	}

	public String getOtpeCentralGovernmentNoOfShares() {
		return otpeCentralGovernmentNoOfShares;
	}

	public String getOtpeCentralGovernmentPercentage() {
		return otpeCentralGovernmentPercentage;
	}

	public String getOtStateGovernmentNoOfShares() {
		return otStateGovernmentNoOfShares;
	}

	public String getOtpetateGovernmentPercentage() {
		return otpetateGovernmentPercentage;
	}

	public String getOtpeGovernmentCompaniesNoOfShares() {
		return otpeGovernmentCompaniesNoOfShares;
	}

	public String getOtpeGovernmentCompaniesPercentage() {
		return otpeGovernmentCompaniesPercentage;
	}

	public String getOtpeInsuranceNoOfShares() {
		return otpeInsuranceNoOfShares;
	}

	public String getOtpeInsuranceCompaniesPercentage() {
		return otpeInsuranceCompaniesPercentage;
	}

	public String getOtpeBanksNoOfShares() {
		return otpeBanksNoOfShares;
	}

	public String getOtpeBanksCompaniesPercentage() {
		return otpeBanksCompaniesPercentage;
	}

	public String getOtpeFiNoOfShares() {
		return otpeFiNoOfShares;
	}

	public String getOtpeFiCompaniesPercentage() {
		return otpeFiCompaniesPercentage;
	}

	public String getOtpeForeignInstitutionalInvestorsNoOfShares() {
		return otpeForeignInstitutionalInvestorsNoOfShares;
	}

	public String getOtpeForeignInstitutionalInvestorsPercentage() {
		return otpeForeignInstitutionalInvestorsPercentage;
	}

	public String getOtpeMutualFundsNoOfShares() {
		return otpeMutualFundsNoOfShares;
	}

	public String getOtpeMutualFundsPercentage() {
		return otpeMutualFundsPercentage;
	}

	public String getOtpeVentureCapitalNoOfShares() {
		return otpeVentureCapitalNoOfShares;
	}

	public String getOtpeVentureCapitalPercentage() {
		return otpeVentureCapitalPercentage;
	}

	public String getOtpeBodyCorporateNoOfShares() {
		return otpeBodyCorporateNoOfShares;
	}

	public String getOtpeBodyCorporatePercentage() {
		return otpeBodyCorporatePercentage;
	}

	public String getOtpeOthers() {
		return otpeOthers;
	}

	public String getOtpeOthersNoOfShares() {
		return otpeOthersNoOfShares;
	}

	public String getOtpeOthersPercentage() {
		return otpeOthersPercentage;
	}

	public String getOtpeTotalNoOfShares() {
		return otpeTotalNoOfShares;
	}

	public String getOtpeTotalPercentage() {
		return otpeTotalPercentage;
	}

	public String getOtppIndianNoOfShares() {
		return otppIndianNoOfShares;
	}

	public String getOtppIndianPercentage() {
		return otppIndianPercentage;
	}

	public String getOtppNriNoOfShares() {
		return otppNriNoOfShares;
	}

	public String getOtppNriPercentage() {
		return otppNriPercentage;
	}

	public String getOtpp_foreign_national_otn_no_of_shares() {
		return otpp_foreign_national_otn_no_of_shares;
	}

	public String getOtpp_foreign_national_otn_percentage() {
		return otpp_foreign_national_otn_percentage;
	}

	public String getOtppCentralGovernmentNoOfShares() {
		return otppCentralGovernmentNoOfShares;
	}

	public String getOtppCentralGovernmentPercentage() {
		return otppCentralGovernmentPercentage;
	}

	public String getOtppStateGovernmentNoOfShares() {
		return otppStateGovernmentNoOfShares;
	}

	public String getOtppStateGovernmentPercentage() {
		return otppStateGovernmentPercentage;
	}

	public String getOtppGovernmentCompaniesNoOfShares() {
		return otppGovernmentCompaniesNoOfShares;
	}

	public String getOtppGovernmentCompaniesPercentage() {
		return otppGovernmentCompaniesPercentage;
	}

	public String getOtppInsuranceNoOfShares() {
		return otppInsuranceNoOfShares;
	}

	public String getOtppInsuranceCompaniesPercentage() {
		return otppInsuranceCompaniesPercentage;
	}

	public String getOtppBanksNoOfShares() {
		return otppBanksNoOfShares;
	}

	public String getOtppBanksCompaniesPercentage() {
		return otppBanksCompaniesPercentage;
	}

	public String getOtppFinancialInstitutionsNoOfShares() {
		return otppFinancialInstitutionsNoOfShares;
	}

	public String getOtppFinancialInstitutionsCompaniesPercentage() {
		return otppFinancialInstitutionsCompaniesPercentage;
	}

	public String getOtppForeignInstitutionalInvestorsNoOfShares() {
		return otppForeignInstitutionalInvestorsNoOfShares;
	}

	public String getOtppForeignInstitutionalInvestorsPercentage() {
		return otppForeignInstitutionalInvestorsPercentage;
	}

	public String getOtppMutualFundsNoOfShares() {
		return otppMutualFundsNoOfShares;
	}

	public String getOtppMutualFundsPercentage() {
		return otppMutualFundsPercentage;
	}

	public String getOtppVentureCapitalNoOfShares() {
		return otppVentureCapitalNoOfShares;
	}

	public String getOtppVentureCapitalPercentage() {
		return otppVentureCapitalPercentage;
	}

	public String getOtppBodyCorporateNoOfShares() {
		return otppBodyCorporateNoOfShares;
	}

	public String getOtppBodyCorporatePercentage() {
		return otppBodyCorporatePercentage;
	}

	public String getOtppOthers() {
		return otppOthers;
	}

	public String getOtppOthersNoOfShares() {
		return otppOthersNoOfShares;
	}

	public String getOtppOthersPercentage() {
		return otppOthersPercentage;
	}

	public String getOtppTotalNoOfShares() {
		return otppTotalNoOfShares;
	}

	public String getOtppTotalPercentage() {
		return otppTotalPercentage;
	}

	public String getUnclassifiedEquityNoOfShares() {
		return unclassifiedEquityNoOfShares;
	}

	public String getUnclassifiedEquitySharesPercentage() {
		return unclassifiedEquitySharesPercentage;
	}

	public List<Object> getEquitySharesWithoutDifferentialRights() {
		return equitySharesWithoutDifferentialRights;
	}

	public String getUnclassifiedPreferenceNoOfShares() {
		return unclassifiedPreferenceNoOfShares;
	}

	public String getUnclassifiedPreferenceSharesPercentage() {
		return unclassifiedPreferenceSharesPercentage;
	}

	public List<Object> getPreferenceSharesWithoutDifferentialRights() {
		return preferenceSharesWithoutDifferentialRights;
	}

	public String getDpeIndianMessage() {
		return dpeIndianMessage;
	}

	public String getDpeIndianPercentageFormatted() {
		return dpeIndianPercentageFormatted;
	}

	public String getDpeNriMessage() {
		return dpeNriMessage;
	}

	public String getDpeNriPercentageFormatted() {
		return dpeNriPercentageFormatted;
	}

	public String getDpeForeignNationalOtnMessage() {
		return dpeForeignNationalOtnMessage;
	}

	public String getDpeForeignNationalOtnPercentageFormatted() {
		return dpeForeignNationalOtnPercentageFormatted;
	}

	public String getDpeCentralGovernmentMessage() {
		return dpeCentralGovernmentMessage;
	}

	public String getDpeCentralGovernmentPercentageFormatted() {
		return dpeCentralGovernmentPercentageFormatted;
	}

	public String getDpeStateGovernmentMessage() {
		return dpeStateGovernmentMessage;
	}

	public String getDpeStateGovernmentPercentageFormatted() {
		return dpeStateGovernmentPercentageFormatted;
	}

	public String getDpeGovernmentCompaniesMessage() {
		return dpeGovernmentCompaniesMessage;
	}

	public String getDpeGovernmentCompaniesPercentageFormatted() {
		return dpeGovernmentCompaniesPercentageFormatted;
	}

	public String getDpeInsuranceCompaniesPercentageFormatted() {
		return dpeInsuranceCompaniesPercentageFormatted;
	}

	public String getDpeBanksMessage() {
		return dpeBanksMessage;
	}

	public String getDpeBanksCompaniesPercentageFormatted() {
		return dpeBanksCompaniesPercentageFormatted;
	}

	public String getDpeFinancialInstitutionsMessage() {
		return dpeFinancialInstitutionsMessage;
	}

	public String getDpeFinancialInstitutionsCompaniesPercentageFormatted() {
		return dpeFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public String getDpeForeignInstitutionalInvestorsMessage() {
		return dpeForeignInstitutionalInvestorsMessage;
	}

	public String getDpeForeignInstitutionalInvestorsPercentageFormatted() {
		return dpeForeignInstitutionalInvestorsPercentageFormatted;
	}

	public String getDpeMutualFundsMessage() {
		return dpeMutualFundsMessage;
	}

	public String getDpeMutualFundsPercentageFormatted() {
		return dpeMutualFundsPercentageFormatted;
	}

	public String getDpeVentureCapitalMessage() {
		return dpeVentureCapitalMessage;
	}

	public String getDpeVentureCapitalPercentageFormatted() {
		return dpeVentureCapitalPercentageFormatted;
	}

	public String getDpeBodyCorporateMessage() {
		return dpeBodyCorporateMessage;
	}

	public String getDpeBodyCorporatePercentageFormatted() {
		return dpeBodyCorporatePercentageFormatted;
	}

	public String getDpeOthersMessage() {
		return dpeOthersMessage;
	}

	public String getDpeOthersPercentageFormatted() {
		return dpeOthersPercentageFormatted;
	}

	public String getDpeTotalMessage() {
		return dpeTotalMessage;
	}

	public String getDpeTotalPercentageFormatted() {
		return dpeTotalPercentageFormatted;
	}

	public String getDppIndianMessage() {
		return dppIndianMessage;
	}

	public String getDppIndianPercentageFormatted() {
		return dppIndianPercentageFormatted;
	}

	public String getDppNriMessage() {
		return dppNriMessage;
	}

	public String getDppNriPercentageFormatted() {
		return dppNriPercentageFormatted;
	}

	public String getDppForeignNationalOtnMessage() {
		return dppForeignNationalOtnMessage;
	}

	public String getDppForeignNationalOtnPercentageFormatted() {
		return dppForeignNationalOtnPercentageFormatted;
	}

	public String getDppCentralGovernmentMessage() {
		return dppCentralGovernmentMessage;
	}

	public String getDppCentralGovernmentPercentageFormatted() {
		return dppCentralGovernmentPercentageFormatted;
	}

	public String getDppStateGovernmentMessage() {
		return dppStateGovernmentMessage;
	}

	public String getDppStateGovernmentPercentageFormatted() {
		return dppStateGovernmentPercentageFormatted;
	}

	public String getDppGovernmentCompaniesMessage() {
		return dppGovernmentCompaniesMessage;
	}

	public String getDppGovernmentCompaniesPercentageFormatted() {
		return dppGovernmentCompaniesPercentageFormatted;
	}

	public String getDppInsuranceCompaniesPercentageFormatted() {
		return dppInsuranceCompaniesPercentageFormatted;
	}

	public String getDppBanksMessage() {
		return dppBanksMessage;
	}

	public String getDppBanksCompaniesPercentageFormatted() {
		return dppBanksCompaniesPercentageFormatted;
	}

	public String getDppFinancialInstitutionsMessage() {
		return dppFinancialInstitutionsMessage;
	}

	public String getDppFinancialInstitutionsCompaniesPercentageFormatted() {
		return dppFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public String getDppForeignInstitutionalInvestorsMessage() {
		return dppForeignInstitutionalInvestorsMessage;
	}

	public String getDppForeignInstitutionalInvestorsPercentageFormatted() {
		return dppForeignInstitutionalInvestorsPercentageFormatted;
	}

	public String getDppMutualFundsMessage() {
		return dppMutualFundsMessage;
	}

	public String getDppMutualFundsPercentageFormatted() {
		return dppMutualFundsPercentageFormatted;
	}

	public String getDppVentureCapitalMessage() {
		return dppVentureCapitalMessage;
	}

	public String getDppVentureCapitalPercentageFormatted() {
		return dppVentureCapitalPercentageFormatted;
	}

	public String getDppBodyCorporateMessage() {
		return dppBodyCorporateMessage;
	}

	public String getDppBodyCorporatePercentageFormatted() {
		return dppBodyCorporatePercentageFormatted;
	}

	public String getDppOthersMessage() {
		return dppOthersMessage;
	}

	public String getDppOthersPercentageFormatted() {
		return dppOthersPercentageFormatted;
	}

	public String getDppTotalMessage() {
		return dppTotalMessage;
	}

	public String getDppTotalPercentageFormatted() {
		return dppTotalPercentageFormatted;
	}

	public String getDotpeIndianMessage() {
		return dotpeIndianMessage;
	}

	public String getDotpeIndianPercentageFormatted() {
		return dotpeIndianPercentageFormatted;
	}

	public String getDotpeNriMessage() {
		return dotpeNriMessage;
	}

	public String getDotpeNriPercentageFormatted() {
		return dotpeNriPercentageFormatted;
	}

	public String getDotpeForeignNationalOtnMessage() {
		return dotpeForeignNationalOtnMessage;
	}

	public String getDotpeForeignNationalOtnPercentageFormatted() {
		return dotpeForeignNationalOtnPercentageFormatted;
	}

	public String getDotpeCentralGovernmentMessage() {
		return dotpeCentralGovernmentMessage;
	}

	public String getDotpeCentralGovernmentPercentageFormatted() {
		return dotpeCentralGovernmentPercentageFormatted;
	}

	public String getDotpeStateGovernmentMessage() {
		return dotpeStateGovernmentMessage;
	}

	public String getDotpeStateGovernmentPercentageFormatted() {
		return dotpeStateGovernmentPercentageFormatted;
	}

	public String getDotpeGovernmentCompaniesMessage() {
		return dotpeGovernmentCompaniesMessage;
	}

	public String getDotpeGovernmentCompaniesPercentageFormatted() {
		return dotpeGovernmentCompaniesPercentageFormatted;
	}

	public String getDotpeInsuranceCompaniesPercentageFormatted() {
		return dotpeInsuranceCompaniesPercentageFormatted;
	}

	public String getDotpeBanksMessage() {
		return dotpeBanksMessage;
	}

	public String getDotpeBanksCompaniesPercentageFormatted() {
		return dotpeBanksCompaniesPercentageFormatted;
	}

	public String getDotpeFinancialInstitutionsMessage() {
		return dotpeFinancialInstitutionsMessage;
	}

	public String getDotpeFinancialInstitutionsCompaniesPercentageFormatted() {
		return dotpeFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public String getDotpeForeignInstitutionalInvestorsMessage() {
		return dotpeForeignInstitutionalInvestorsMessage;
	}

	public String getDotpeForeignInstitutionalInvestorsPercentageFormatted() {
		return dotpeForeignInstitutionalInvestorsPercentageFormatted;
	}

	public String getDotpeMutualFundsMessage() {
		return dotpeMutualFundsMessage;
	}

	public String getDotpeMutualFundsPercentageFormatted() {
		return dotpeMutualFundsPercentageFormatted;
	}

	public String getDotpeVentureCapitalMessage() {
		return dotpeVentureCapitalMessage;
	}

	public String getDotpeVentureCapitalPercentageFormatted() {
		return dotpeVentureCapitalPercentageFormatted;
	}

	public String getDotpeBodyCorporateMessage() {
		return dotpeBodyCorporateMessage;
	}

	public String getDotpeBodyCorporatePercentageFormatted() {
		return dotpeBodyCorporatePercentageFormatted;
	}

	public String getDotpeOthersMessage() {
		return dotpeOthersMessage;
	}

	public String getDotpeOthersPercentageFormatted() {
		return dotpeOthersPercentageFormatted;
	}

	public String getDotpeTotalMessage() {
		return dotpeTotalMessage;
	}

	public String getDotpeTotalPercentageFormatted() {
		return dotpeTotalPercentageFormatted;
	}

	public String getDotppIndianMessage() {
		return dotppIndianMessage;
	}

	public String getDotppIndianPercentageFormatted() {
		return dotppIndianPercentageFormatted;
	}

	public String getDotppNriMessage() {
		return dotppNriMessage;
	}

	public String getDotppNriPercentageFormatted() {
		return dotppNriPercentageFormatted;
	}

	public String getDotppForeignNationalOtnMessage() {
		return dotppForeignNationalOtnMessage;
	}

	public String getDotppForeignNationalOtnPercentageFormatted() {
		return dotppForeignNationalOtnPercentageFormatted;
	}

	public String getDotppCentralGovernmentMessage() {
		return dotppCentralGovernmentMessage;
	}

	public String getDotppCentralGovernmentPercentageFormatted() {
		return dotppCentralGovernmentPercentageFormatted;
	}

	public String getDotppStateGovernmentMessage() {
		return dotppStateGovernmentMessage;
	}

	public String getDotppStateGovernmentPercentageFormatted() {
		return dotppStateGovernmentPercentageFormatted;
	}

	public String getDotppGovernmentCompaniesMessage() {
		return dotppGovernmentCompaniesMessage;
	}

	public String getDotppGovernmentCompaniesPercentageFormatted() {
		return dotppGovernmentCompaniesPercentageFormatted;
	}

	public String getDotppInsuranceCompaniesPercentageFormatted() {
		return dotppInsuranceCompaniesPercentageFormatted;
	}

	public String getDotppBanksMessage() {
		return dotppBanksMessage;
	}

	public String getDotppBanksCompaniesPercentageFormatted() {
		return dotppBanksCompaniesPercentageFormatted;
	}

	public String getDotppFinancialInstitutionsMessage() {
		return dotppFinancialInstitutionsMessage;
	}

	public String getDotppFinancialInstitutionsCompaniesPercentageFormatted() {
		return dotppFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public String getDotppForeignInstitutionalInvestorsMessage() {
		return dotppForeignInstitutionalInvestorsMessage;
	}

	public String getDotppForeignInstitutionalInvestorsPercentageFormatted() {
		return dotppForeignInstitutionalInvestorsPercentageFormatted;
	}

	public String getDotppMutualFundsMessage() {
		return dotppMutualFundsMessage;
	}

	public String getDotppMutualFundsPercentageFormatted() {
		return dotppMutualFundsPercentageFormatted;
	}

	public String getDotppVentureCapitalMessage() {
		return dotppVentureCapitalMessage;
	}

	public String getDotherTppvcPercentageFormatted() {
		return dotherTppvcPercentageFormatted;
	}

	public String getDotppBodyCorporateMessage() {
		return dotppBodyCorporateMessage;
	}

	public String getDotppBodyCorporatePercentageFormatted() {
		return dotppBodyCorporatePercentageFormatted;
	}

	public String getDotppOthersMessage() {
		return dotppOthersMessage;
	}

	public String getDotppOthersPercentageFormatted() {
		return dotppOthersPercentageFormatted;
	}

	public String getDotppTotalMessage() {
		return dotppTotalMessage;
	}

	public String getDotppTotalPercentageFormatted() {
		return dotppTotalPercentageFormatted;
	}

	public String getDummyUesPercentageFormatted() {
		return dummyUesPercentageFormatted;
	}

	public String getDummyUpspFormatted() {
		return dummyUpspFormatted;
	}

	public String getDpeInsuranceCompaniesMessage() {
		return dpeInsuranceCompaniesMessage;
	}

	public String getDppInsuranceCompaniesMessage() {
		return dppInsuranceCompaniesMessage;
	}

	public String getDotpeInsuranceCompaniesMessage() {
		return dotpeInsuranceCompaniesMessage;
	}

	public String getDotppInsuranceCompaniesMessage() {
		return dotppInsuranceCompaniesMessage;
	}

	public String getDummyEquityChartTitle() {
		return dummyEquityChartTitle;
	}

	public String getDpreferenceChartTitle() {
		return dpreferenceChartTitle;
	}

	public String getDummyPromoterTitle() {
		return dummyPromoterTitle;
	}

	public String getDotherThanPromoterTitle() {
		return dotherThanPromoterTitle;
	}

	public String getDummyUnclassifiedTitle() {
		return dummyUnclassifiedTitle;
	}

	public String getDummyShareholdingTitle() {
		return dummyShareholdingTitle;
	}

	public String getDummySharesTitle() {
		return dummySharesTitle;
	}

	public String getDpercentageTitle() {
		return dpercentageTitle;
	}

	public String getDummyIndividualHufTitle() {
		return dummyIndividualHufTitle;
	}

	public String getDummyGovernmentTitle() {
		return dummyGovernmentTitle;
	}

	public String getDummyEquitySharesWithoutDifferentialRightsTitle() {
		return dummyEquitySharesWithoutDifferentialRightsTitle;
	}

	public String getDpswdifferentialRightsTitle() {
		return dpswdifferentialRightsTitle;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public void setPeIndianNoOfShares(String peIndianNoOfShares) {
		this.peIndianNoOfShares = peIndianNoOfShares;
	}

	public void setPeIndianPercentage(String peIndianPercentage) {
		this.peIndianPercentage = peIndianPercentage;
	}

	public void setPeNriNoOfShares(String peNriNoOfShares) {
		this.peNriNoOfShares = peNriNoOfShares;
	}

	public void setPeNriPercentage(String peNriPercentage) {
		this.peNriPercentage = peNriPercentage;
	}

	public void setPeForeignNationalOtnNoOfShares(String peForeignNationalOtnNoOfShares) {
		this.peForeignNationalOtnNoOfShares = peForeignNationalOtnNoOfShares;
	}

	public void setPe_foreign_national_otn_percentage(String pe_foreign_national_otn_percentage) {
		this.pe_foreign_national_otn_percentage = pe_foreign_national_otn_percentage;
	}

	public void setPe_central_government_no_of_shares(String pe_central_government_no_of_shares) {
		this.pe_central_government_no_of_shares = pe_central_government_no_of_shares;
	}

	public void setPeCentralGovernmentPercentage(String peCentralGovernmentPercentage) {
		this.peCentralGovernmentPercentage = peCentralGovernmentPercentage;
	}

	public void setPeStateGovernmentNoOfShares(String peStateGovernmentNoOfShares) {
		this.peStateGovernmentNoOfShares = peStateGovernmentNoOfShares;
	}

	public void setPeStateGovernmentPercentage(String peStateGovernmentPercentage) {
		this.peStateGovernmentPercentage = peStateGovernmentPercentage;
	}

	public void setPeGovernmentCompaniesNoOfShares(String peGovernmentCompaniesNoOfShares) {
		this.peGovernmentCompaniesNoOfShares = peGovernmentCompaniesNoOfShares;
	}

	public void setPeGovernmentCompaniesPercentage(String peGovernmentCompaniesPercentage) {
		this.peGovernmentCompaniesPercentage = peGovernmentCompaniesPercentage;
	}

	public void setPeInsuranceNoOfShares(String peInsuranceNoOfShares) {
		this.peInsuranceNoOfShares = peInsuranceNoOfShares;
	}

	public void setPeInsuranceCompaniesPercentage(String peInsuranceCompaniesPercentage) {
		this.peInsuranceCompaniesPercentage = peInsuranceCompaniesPercentage;
	}

	public void setPeBanksNoOfShares(String peBanksNoOfShares) {
		this.peBanksNoOfShares = peBanksNoOfShares;
	}

	public void setPeBanksCompaniesPercentage(String peBanksCompaniesPercentage) {
		this.peBanksCompaniesPercentage = peBanksCompaniesPercentage;
	}

	public void setPeFinancialInstitutionsNoOfShares(String peFinancialInstitutionsNoOfShares) {
		this.peFinancialInstitutionsNoOfShares = peFinancialInstitutionsNoOfShares;
	}

	public void setPeFinancialInstitutionsCompaniesPercentage(String peFinancialInstitutionsCompaniesPercentage) {
		this.peFinancialInstitutionsCompaniesPercentage = peFinancialInstitutionsCompaniesPercentage;
	}

	public void setPeForeignInstitutionalInvestorsNoOfShares(String peForeignInstitutionalInvestorsNoOfShares) {
		this.peForeignInstitutionalInvestorsNoOfShares = peForeignInstitutionalInvestorsNoOfShares;
	}

	public void setPeForeignInstitutionalInvestorsPercentage(String peForeignInstitutionalInvestorsPercentage) {
		this.peForeignInstitutionalInvestorsPercentage = peForeignInstitutionalInvestorsPercentage;
	}

	public void setPeMutualFundsNoOfShares(String peMutualFundsNoOfShares) {
		this.peMutualFundsNoOfShares = peMutualFundsNoOfShares;
	}

	public void setPeMutualFundsPercentage(String peMutualFundsPercentage) {
		this.peMutualFundsPercentage = peMutualFundsPercentage;
	}

	public void setPeVentureCapitalNoOfShares(String peVentureCapitalNoOfShares) {
		this.peVentureCapitalNoOfShares = peVentureCapitalNoOfShares;
	}

	public void setPeVentureCapitalPercentage(String peVentureCapitalPercentage) {
		this.peVentureCapitalPercentage = peVentureCapitalPercentage;
	}

	public void setPeBodyCorporateNoOfShares(String peBodyCorporateNoOfShares) {
		this.peBodyCorporateNoOfShares = peBodyCorporateNoOfShares;
	}

	public void setPeBodyCorporatePercentage(String peBodyCorporatePercentage) {
		this.peBodyCorporatePercentage = peBodyCorporatePercentage;
	}

	public void setPeOthers(String peOthers) {
		this.peOthers = peOthers;
	}

	public void setPeOthersNoOfShares(String peOthersNoOfShares) {
		this.peOthersNoOfShares = peOthersNoOfShares;
	}

	public void setPeOthersPercentage(String peOthersPercentage) {
		this.peOthersPercentage = peOthersPercentage;
	}

	public void setPeTotalNoOfShares(String peTotalNoOfShares) {
		this.peTotalNoOfShares = peTotalNoOfShares;
	}

	public void setPeTotalPercentage(String peTotalPercentage) {
		this.peTotalPercentage = peTotalPercentage;
	}

	public void setPpIndianNoOfShares(String ppIndianNoOfShares) {
		this.ppIndianNoOfShares = ppIndianNoOfShares;
	}

	public void setPpIndianPercentage(String ppIndianPercentage) {
		this.ppIndianPercentage = ppIndianPercentage;
	}

	public void setPpNriNoOfShares(String ppNriNoOfShares) {
		this.ppNriNoOfShares = ppNriNoOfShares;
	}

	public void setPpNriPercentage(String ppNriPercentage) {
		this.ppNriPercentage = ppNriPercentage;
	}

	public void setPpForeignNationalOtnNoOfShares(String ppForeignNationalOtnNoOfShares) {
		this.ppForeignNationalOtnNoOfShares = ppForeignNationalOtnNoOfShares;
	}

	public void setPpForeignNationalOtnPercentage(String ppForeignNationalOtnPercentage) {
		this.ppForeignNationalOtnPercentage = ppForeignNationalOtnPercentage;
	}

	public void setPpCentralGovernmentNoOfShares(String ppCentralGovernmentNoOfShares) {
		this.ppCentralGovernmentNoOfShares = ppCentralGovernmentNoOfShares;
	}

	public void setPpCentralGovernmentPercentage(String ppCentralGovernmentPercentage) {
		this.ppCentralGovernmentPercentage = ppCentralGovernmentPercentage;
	}

	public void setPpStateGovernmentNoOfShares(String ppStateGovernmentNoOfShares) {
		this.ppStateGovernmentNoOfShares = ppStateGovernmentNoOfShares;
	}

	public void setPpStateGovernmentPercentage(String ppStateGovernmentPercentage) {
		this.ppStateGovernmentPercentage = ppStateGovernmentPercentage;
	}

	public void setPpGovernmentCompaniesNoOfShares(String ppGovernmentCompaniesNoOfShares) {
		this.ppGovernmentCompaniesNoOfShares = ppGovernmentCompaniesNoOfShares;
	}

	public void setPpGovernmentCompaniesPercentage(String ppGovernmentCompaniesPercentage) {
		this.ppGovernmentCompaniesPercentage = ppGovernmentCompaniesPercentage;
	}

	public void setPpInsuranceNoOfShares(String ppInsuranceNoOfShares) {
		this.ppInsuranceNoOfShares = ppInsuranceNoOfShares;
	}

	public void setPpInsuranceCompaniesPercentage(String ppInsuranceCompaniesPercentage) {
		this.ppInsuranceCompaniesPercentage = ppInsuranceCompaniesPercentage;
	}

	public void setPpBanksNoOfShares(String ppBanksNoOfShares) {
		this.ppBanksNoOfShares = ppBanksNoOfShares;
	}

	public void setPpBanksCompaniesPercentage(String ppBanksCompaniesPercentage) {
		this.ppBanksCompaniesPercentage = ppBanksCompaniesPercentage;
	}

	public void setPpFinancialInstitutionsNoOfShares(String ppFinancialInstitutionsNoOfShares) {
		this.ppFinancialInstitutionsNoOfShares = ppFinancialInstitutionsNoOfShares;
	}

	public void setPpFinancialInstitutionsCompaniesPercentage(String ppFinancialInstitutionsCompaniesPercentage) {
		this.ppFinancialInstitutionsCompaniesPercentage = ppFinancialInstitutionsCompaniesPercentage;
	}

	public void setPpForeignInstitutionalInvestorsNoOfShares(String ppForeignInstitutionalInvestorsNoOfShares) {
		this.ppForeignInstitutionalInvestorsNoOfShares = ppForeignInstitutionalInvestorsNoOfShares;
	}

	public void setPpForeignInstitutionalInvestorsPercentage(String ppForeignInstitutionalInvestorsPercentage) {
		this.ppForeignInstitutionalInvestorsPercentage = ppForeignInstitutionalInvestorsPercentage;
	}

	public void setPpMutualFundsNoOfShares(String ppMutualFundsNoOfShares) {
		this.ppMutualFundsNoOfShares = ppMutualFundsNoOfShares;
	}

	public void setPpMutualFundsPercentage(String ppMutualFundsPercentage) {
		this.ppMutualFundsPercentage = ppMutualFundsPercentage;
	}

	public void setPpVentureCapitalNoOfShares(String ppVentureCapitalNoOfShares) {
		this.ppVentureCapitalNoOfShares = ppVentureCapitalNoOfShares;
	}

	public void setPpVentureCapitalPercentage(String ppVentureCapitalPercentage) {
		this.ppVentureCapitalPercentage = ppVentureCapitalPercentage;
	}

	public void setPpBodyCorporateNoOfShares(String ppBodyCorporateNoOfShares) {
		this.ppBodyCorporateNoOfShares = ppBodyCorporateNoOfShares;
	}

	public void setPpBodyCorporatePercentage(String ppBodyCorporatePercentage) {
		this.ppBodyCorporatePercentage = ppBodyCorporatePercentage;
	}

	public void setPpOthers(String ppOthers) {
		this.ppOthers = ppOthers;
	}

	public void setPpOthersNoOfShares(String ppOthersNoOfShares) {
		this.ppOthersNoOfShares = ppOthersNoOfShares;
	}

	public void setPpOthersPercentage(String ppOthersPercentage) {
		this.ppOthersPercentage = ppOthersPercentage;
	}

	public void setPpTotalNoOfShares(String ppTotalNoOfShares) {
		this.ppTotalNoOfShares = ppTotalNoOfShares;
	}

	public void setPpTotalPercentage(String ppTotalPercentage) {
		this.ppTotalPercentage = ppTotalPercentage;
	}

	public void setOtpeIndianNoOfShares(String otpeIndianNoOfShares) {
		this.otpeIndianNoOfShares = otpeIndianNoOfShares;
	}

	public void setOtpeIndianPercentage(String otpeIndianPercentage) {
		this.otpeIndianPercentage = otpeIndianPercentage;
	}

	public void setOtpeNriNoOfShares(String otpeNriNoOfShares) {
		this.otpeNriNoOfShares = otpeNriNoOfShares;
	}

	public void setOtpeNriPercentage(String otpeNriPercentage) {
		this.otpeNriPercentage = otpeNriPercentage;
	}

	public void setOtpeForeignNationalOtnNoOfShares(String otpeForeignNationalOtnNoOfShares) {
		this.otpeForeignNationalOtnNoOfShares = otpeForeignNationalOtnNoOfShares;
	}

	public void setOtpeForeignNationalOtnPercentage(String otpeForeignNationalOtnPercentage) {
		this.otpeForeignNationalOtnPercentage = otpeForeignNationalOtnPercentage;
	}

	public void setOtpeCentralGovernmentNoOfShares(String otpeCentralGovernmentNoOfShares) {
		this.otpeCentralGovernmentNoOfShares = otpeCentralGovernmentNoOfShares;
	}

	public void setOtpeCentralGovernmentPercentage(String otpeCentralGovernmentPercentage) {
		this.otpeCentralGovernmentPercentage = otpeCentralGovernmentPercentage;
	}

	public void setOtStateGovernmentNoOfShares(String otStateGovernmentNoOfShares) {
		this.otStateGovernmentNoOfShares = otStateGovernmentNoOfShares;
	}

	public void setOtpetateGovernmentPercentage(String otpetateGovernmentPercentage) {
		this.otpetateGovernmentPercentage = otpetateGovernmentPercentage;
	}

	public void setOtpeGovernmentCompaniesNoOfShares(String otpeGovernmentCompaniesNoOfShares) {
		this.otpeGovernmentCompaniesNoOfShares = otpeGovernmentCompaniesNoOfShares;
	}

	public void setOtpeGovernmentCompaniesPercentage(String otpeGovernmentCompaniesPercentage) {
		this.otpeGovernmentCompaniesPercentage = otpeGovernmentCompaniesPercentage;
	}

	public void setOtpeInsuranceNoOfShares(String otpeInsuranceNoOfShares) {
		this.otpeInsuranceNoOfShares = otpeInsuranceNoOfShares;
	}

	public void setOtpeInsuranceCompaniesPercentage(String otpeInsuranceCompaniesPercentage) {
		this.otpeInsuranceCompaniesPercentage = otpeInsuranceCompaniesPercentage;
	}

	public void setOtpeBanksNoOfShares(String otpeBanksNoOfShares) {
		this.otpeBanksNoOfShares = otpeBanksNoOfShares;
	}

	public void setOtpeBanksCompaniesPercentage(String otpeBanksCompaniesPercentage) {
		this.otpeBanksCompaniesPercentage = otpeBanksCompaniesPercentage;
	}

	public void setOtpeFiNoOfShares(String otpeFiNoOfShares) {
		this.otpeFiNoOfShares = otpeFiNoOfShares;
	}

	public void setOtpeFiCompaniesPercentage(String otpeFiCompaniesPercentage) {
		this.otpeFiCompaniesPercentage = otpeFiCompaniesPercentage;
	}

	public void setOtpeForeignInstitutionalInvestorsNoOfShares(String otpeForeignInstitutionalInvestorsNoOfShares) {
		this.otpeForeignInstitutionalInvestorsNoOfShares = otpeForeignInstitutionalInvestorsNoOfShares;
	}

	public void setOtpeForeignInstitutionalInvestorsPercentage(String otpeForeignInstitutionalInvestorsPercentage) {
		this.otpeForeignInstitutionalInvestorsPercentage = otpeForeignInstitutionalInvestorsPercentage;
	}

	public void setOtpeMutualFundsNoOfShares(String otpeMutualFundsNoOfShares) {
		this.otpeMutualFundsNoOfShares = otpeMutualFundsNoOfShares;
	}

	public void setOtpeMutualFundsPercentage(String otpeMutualFundsPercentage) {
		this.otpeMutualFundsPercentage = otpeMutualFundsPercentage;
	}

	public void setOtpeVentureCapitalNoOfShares(String otpeVentureCapitalNoOfShares) {
		this.otpeVentureCapitalNoOfShares = otpeVentureCapitalNoOfShares;
	}

	public void setOtpeVentureCapitalPercentage(String otpeVentureCapitalPercentage) {
		this.otpeVentureCapitalPercentage = otpeVentureCapitalPercentage;
	}

	public void setOtpeBodyCorporateNoOfShares(String otpeBodyCorporateNoOfShares) {
		this.otpeBodyCorporateNoOfShares = otpeBodyCorporateNoOfShares;
	}

	public void setOtpeBodyCorporatePercentage(String otpeBodyCorporatePercentage) {
		this.otpeBodyCorporatePercentage = otpeBodyCorporatePercentage;
	}

	public void setOtpeOthers(String otpeOthers) {
		this.otpeOthers = otpeOthers;
	}

	public void setOtpeOthersNoOfShares(String otpeOthersNoOfShares) {
		this.otpeOthersNoOfShares = otpeOthersNoOfShares;
	}

	public void setOtpeOthersPercentage(String otpeOthersPercentage) {
		this.otpeOthersPercentage = otpeOthersPercentage;
	}

	public void setOtpeTotalNoOfShares(String otpeTotalNoOfShares) {
		this.otpeTotalNoOfShares = otpeTotalNoOfShares;
	}

	public void setOtpeTotalPercentage(String otpeTotalPercentage) {
		this.otpeTotalPercentage = otpeTotalPercentage;
	}

	public void setOtppIndianNoOfShares(String otppIndianNoOfShares) {
		this.otppIndianNoOfShares = otppIndianNoOfShares;
	}

	public void setOtppIndianPercentage(String otppIndianPercentage) {
		this.otppIndianPercentage = otppIndianPercentage;
	}

	public void setOtppNriNoOfShares(String otppNriNoOfShares) {
		this.otppNriNoOfShares = otppNriNoOfShares;
	}

	public void setOtppNriPercentage(String otppNriPercentage) {
		this.otppNriPercentage = otppNriPercentage;
	}

	public void setOtpp_foreign_national_otn_no_of_shares(String otpp_foreign_national_otn_no_of_shares) {
		this.otpp_foreign_national_otn_no_of_shares = otpp_foreign_national_otn_no_of_shares;
	}

	public void setOtpp_foreign_national_otn_percentage(String otpp_foreign_national_otn_percentage) {
		this.otpp_foreign_national_otn_percentage = otpp_foreign_national_otn_percentage;
	}

	public void setOtppCentralGovernmentNoOfShares(String otppCentralGovernmentNoOfShares) {
		this.otppCentralGovernmentNoOfShares = otppCentralGovernmentNoOfShares;
	}

	public void setOtppCentralGovernmentPercentage(String otppCentralGovernmentPercentage) {
		this.otppCentralGovernmentPercentage = otppCentralGovernmentPercentage;
	}

	public void setOtppStateGovernmentNoOfShares(String otppStateGovernmentNoOfShares) {
		this.otppStateGovernmentNoOfShares = otppStateGovernmentNoOfShares;
	}

	public void setOtppStateGovernmentPercentage(String otppStateGovernmentPercentage) {
		this.otppStateGovernmentPercentage = otppStateGovernmentPercentage;
	}

	public void setOtppGovernmentCompaniesNoOfShares(String otppGovernmentCompaniesNoOfShares) {
		this.otppGovernmentCompaniesNoOfShares = otppGovernmentCompaniesNoOfShares;
	}

	public void setOtppGovernmentCompaniesPercentage(String otppGovernmentCompaniesPercentage) {
		this.otppGovernmentCompaniesPercentage = otppGovernmentCompaniesPercentage;
	}

	public void setOtppInsuranceNoOfShares(String otppInsuranceNoOfShares) {
		this.otppInsuranceNoOfShares = otppInsuranceNoOfShares;
	}

	public void setOtppInsuranceCompaniesPercentage(String otppInsuranceCompaniesPercentage) {
		this.otppInsuranceCompaniesPercentage = otppInsuranceCompaniesPercentage;
	}

	public void setOtppBanksNoOfShares(String otppBanksNoOfShares) {
		this.otppBanksNoOfShares = otppBanksNoOfShares;
	}

	public void setOtppBanksCompaniesPercentage(String otppBanksCompaniesPercentage) {
		this.otppBanksCompaniesPercentage = otppBanksCompaniesPercentage;
	}

	public void setOtppFinancialInstitutionsNoOfShares(String otppFinancialInstitutionsNoOfShares) {
		this.otppFinancialInstitutionsNoOfShares = otppFinancialInstitutionsNoOfShares;
	}

	public void setOtppFinancialInstitutionsCompaniesPercentage(String otppFinancialInstitutionsCompaniesPercentage) {
		this.otppFinancialInstitutionsCompaniesPercentage = otppFinancialInstitutionsCompaniesPercentage;
	}

	public void setOtppForeignInstitutionalInvestorsNoOfShares(String otppForeignInstitutionalInvestorsNoOfShares) {
		this.otppForeignInstitutionalInvestorsNoOfShares = otppForeignInstitutionalInvestorsNoOfShares;
	}

	public void setOtppForeignInstitutionalInvestorsPercentage(String otppForeignInstitutionalInvestorsPercentage) {
		this.otppForeignInstitutionalInvestorsPercentage = otppForeignInstitutionalInvestorsPercentage;
	}

	public void setOtppMutualFundsNoOfShares(String otppMutualFundsNoOfShares) {
		this.otppMutualFundsNoOfShares = otppMutualFundsNoOfShares;
	}

	public void setOtppMutualFundsPercentage(String otppMutualFundsPercentage) {
		this.otppMutualFundsPercentage = otppMutualFundsPercentage;
	}

	public void setOtppVentureCapitalNoOfShares(String otppVentureCapitalNoOfShares) {
		this.otppVentureCapitalNoOfShares = otppVentureCapitalNoOfShares;
	}

	public void setOtppVentureCapitalPercentage(String otppVentureCapitalPercentage) {
		this.otppVentureCapitalPercentage = otppVentureCapitalPercentage;
	}

	public void setOtppBodyCorporateNoOfShares(String otppBodyCorporateNoOfShares) {
		this.otppBodyCorporateNoOfShares = otppBodyCorporateNoOfShares;
	}

	public void setOtppBodyCorporatePercentage(String otppBodyCorporatePercentage) {
		this.otppBodyCorporatePercentage = otppBodyCorporatePercentage;
	}

	public void setOtppOthers(String otppOthers) {
		this.otppOthers = otppOthers;
	}

	public void setOtppOthersNoOfShares(String otppOthersNoOfShares) {
		this.otppOthersNoOfShares = otppOthersNoOfShares;
	}

	public void setOtppOthersPercentage(String otppOthersPercentage) {
		this.otppOthersPercentage = otppOthersPercentage;
	}

	public void setOtppTotalNoOfShares(String otppTotalNoOfShares) {
		this.otppTotalNoOfShares = otppTotalNoOfShares;
	}

	public void setOtppTotalPercentage(String otppTotalPercentage) {
		this.otppTotalPercentage = otppTotalPercentage;
	}

	public void setUnclassifiedEquityNoOfShares(String unclassifiedEquityNoOfShares) {
		this.unclassifiedEquityNoOfShares = unclassifiedEquityNoOfShares;
	}

	public void setUnclassifiedEquitySharesPercentage(String unclassifiedEquitySharesPercentage) {
		this.unclassifiedEquitySharesPercentage = unclassifiedEquitySharesPercentage;
	}

	public void setEquitySharesWithoutDifferentialRights(List<Object> equitySharesWithoutDifferentialRights) {
		this.equitySharesWithoutDifferentialRights = equitySharesWithoutDifferentialRights;
	}

	public void setUnclassifiedPreferenceNoOfShares(String unclassifiedPreferenceNoOfShares) {
		this.unclassifiedPreferenceNoOfShares = unclassifiedPreferenceNoOfShares;
	}

	public void setUnclassifiedPreferenceSharesPercentage(String unclassifiedPreferenceSharesPercentage) {
		this.unclassifiedPreferenceSharesPercentage = unclassifiedPreferenceSharesPercentage;
	}

	public void setPreferenceSharesWithoutDifferentialRights(List<Object> preferenceSharesWithoutDifferentialRights) {
		this.preferenceSharesWithoutDifferentialRights = preferenceSharesWithoutDifferentialRights;
	}

	public void setDpeIndianMessage(String dpeIndianMessage) {
		this.dpeIndianMessage = dpeIndianMessage;
	}

	public void setDpeIndianPercentageFormatted(String dpeIndianPercentageFormatted) {
		this.dpeIndianPercentageFormatted = dpeIndianPercentageFormatted;
	}

	public void setDpeNriMessage(String dpeNriMessage) {
		this.dpeNriMessage = dpeNriMessage;
	}

	public void setDpeNriPercentageFormatted(String dpeNriPercentageFormatted) {
		this.dpeNriPercentageFormatted = dpeNriPercentageFormatted;
	}

	public void setDpeForeignNationalOtnMessage(String dpeForeignNationalOtnMessage) {
		this.dpeForeignNationalOtnMessage = dpeForeignNationalOtnMessage;
	}

	public void setDpeForeignNationalOtnPercentageFormatted(String dpeForeignNationalOtnPercentageFormatted) {
		this.dpeForeignNationalOtnPercentageFormatted = dpeForeignNationalOtnPercentageFormatted;
	}

	public void setDpeCentralGovernmentMessage(String dpeCentralGovernmentMessage) {
		this.dpeCentralGovernmentMessage = dpeCentralGovernmentMessage;
	}

	public void setDpeCentralGovernmentPercentageFormatted(String dpeCentralGovernmentPercentageFormatted) {
		this.dpeCentralGovernmentPercentageFormatted = dpeCentralGovernmentPercentageFormatted;
	}

	public void setDpeStateGovernmentMessage(String dpeStateGovernmentMessage) {
		this.dpeStateGovernmentMessage = dpeStateGovernmentMessage;
	}

	public void setDpeStateGovernmentPercentageFormatted(String dpeStateGovernmentPercentageFormatted) {
		this.dpeStateGovernmentPercentageFormatted = dpeStateGovernmentPercentageFormatted;
	}

	public void setDpeGovernmentCompaniesMessage(String dpeGovernmentCompaniesMessage) {
		this.dpeGovernmentCompaniesMessage = dpeGovernmentCompaniesMessage;
	}

	public void setDpeGovernmentCompaniesPercentageFormatted(String dpeGovernmentCompaniesPercentageFormatted) {
		this.dpeGovernmentCompaniesPercentageFormatted = dpeGovernmentCompaniesPercentageFormatted;
	}

	public void setDpeInsuranceCompaniesPercentageFormatted(String dpeInsuranceCompaniesPercentageFormatted) {
		this.dpeInsuranceCompaniesPercentageFormatted = dpeInsuranceCompaniesPercentageFormatted;
	}

	public void setDpeBanksMessage(String dpeBanksMessage) {
		this.dpeBanksMessage = dpeBanksMessage;
	}

	public void setDpeBanksCompaniesPercentageFormatted(String dpeBanksCompaniesPercentageFormatted) {
		this.dpeBanksCompaniesPercentageFormatted = dpeBanksCompaniesPercentageFormatted;
	}

	public void setDpeFinancialInstitutionsMessage(String dpeFinancialInstitutionsMessage) {
		this.dpeFinancialInstitutionsMessage = dpeFinancialInstitutionsMessage;
	}

	public void setDpeFinancialInstitutionsCompaniesPercentageFormatted(
			String dpeFinancialInstitutionsCompaniesPercentageFormatted) {
		this.dpeFinancialInstitutionsCompaniesPercentageFormatted = dpeFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public void setDpeForeignInstitutionalInvestorsMessage(String dpeForeignInstitutionalInvestorsMessage) {
		this.dpeForeignInstitutionalInvestorsMessage = dpeForeignInstitutionalInvestorsMessage;
	}

	public void setDpeForeignInstitutionalInvestorsPercentageFormatted(
			String dpeForeignInstitutionalInvestorsPercentageFormatted) {
		this.dpeForeignInstitutionalInvestorsPercentageFormatted = dpeForeignInstitutionalInvestorsPercentageFormatted;
	}

	public void setDpeMutualFundsMessage(String dpeMutualFundsMessage) {
		this.dpeMutualFundsMessage = dpeMutualFundsMessage;
	}

	public void setDpeMutualFundsPercentageFormatted(String dpeMutualFundsPercentageFormatted) {
		this.dpeMutualFundsPercentageFormatted = dpeMutualFundsPercentageFormatted;
	}

	public void setDpeVentureCapitalMessage(String dpeVentureCapitalMessage) {
		this.dpeVentureCapitalMessage = dpeVentureCapitalMessage;
	}

	public void setDpeVentureCapitalPercentageFormatted(String dpeVentureCapitalPercentageFormatted) {
		this.dpeVentureCapitalPercentageFormatted = dpeVentureCapitalPercentageFormatted;
	}

	public void setDpeBodyCorporateMessage(String dpeBodyCorporateMessage) {
		this.dpeBodyCorporateMessage = dpeBodyCorporateMessage;
	}

	public void setDpeBodyCorporatePercentageFormatted(String dpeBodyCorporatePercentageFormatted) {
		this.dpeBodyCorporatePercentageFormatted = dpeBodyCorporatePercentageFormatted;
	}

	public void setDpeOthersMessage(String dpeOthersMessage) {
		this.dpeOthersMessage = dpeOthersMessage;
	}

	public void setDpeOthersPercentageFormatted(String dpeOthersPercentageFormatted) {
		this.dpeOthersPercentageFormatted = dpeOthersPercentageFormatted;
	}

	public void setDpeTotalMessage(String dpeTotalMessage) {
		this.dpeTotalMessage = dpeTotalMessage;
	}

	public void setDpeTotalPercentageFormatted(String dpeTotalPercentageFormatted) {
		this.dpeTotalPercentageFormatted = dpeTotalPercentageFormatted;
	}

	public void setDppIndianMessage(String dppIndianMessage) {
		this.dppIndianMessage = dppIndianMessage;
	}

	public void setDppIndianPercentageFormatted(String dppIndianPercentageFormatted) {
		this.dppIndianPercentageFormatted = dppIndianPercentageFormatted;
	}

	public void setDppNriMessage(String dppNriMessage) {
		this.dppNriMessage = dppNriMessage;
	}

	public void setDppNriPercentageFormatted(String dppNriPercentageFormatted) {
		this.dppNriPercentageFormatted = dppNriPercentageFormatted;
	}

	public void setDppForeignNationalOtnMessage(String dppForeignNationalOtnMessage) {
		this.dppForeignNationalOtnMessage = dppForeignNationalOtnMessage;
	}

	public void setDppForeignNationalOtnPercentageFormatted(String dppForeignNationalOtnPercentageFormatted) {
		this.dppForeignNationalOtnPercentageFormatted = dppForeignNationalOtnPercentageFormatted;
	}

	public void setDppCentralGovernmentMessage(String dppCentralGovernmentMessage) {
		this.dppCentralGovernmentMessage = dppCentralGovernmentMessage;
	}

	public void setDppCentralGovernmentPercentageFormatted(String dppCentralGovernmentPercentageFormatted) {
		this.dppCentralGovernmentPercentageFormatted = dppCentralGovernmentPercentageFormatted;
	}

	public void setDppStateGovernmentMessage(String dppStateGovernmentMessage) {
		this.dppStateGovernmentMessage = dppStateGovernmentMessage;
	}

	public void setDppStateGovernmentPercentageFormatted(String dppStateGovernmentPercentageFormatted) {
		this.dppStateGovernmentPercentageFormatted = dppStateGovernmentPercentageFormatted;
	}

	public void setDppGovernmentCompaniesMessage(String dppGovernmentCompaniesMessage) {
		this.dppGovernmentCompaniesMessage = dppGovernmentCompaniesMessage;
	}

	public void setDppGovernmentCompaniesPercentageFormatted(String dppGovernmentCompaniesPercentageFormatted) {
		this.dppGovernmentCompaniesPercentageFormatted = dppGovernmentCompaniesPercentageFormatted;
	}

	public void setDppInsuranceCompaniesPercentageFormatted(String dppInsuranceCompaniesPercentageFormatted) {
		this.dppInsuranceCompaniesPercentageFormatted = dppInsuranceCompaniesPercentageFormatted;
	}

	public void setDppBanksMessage(String dppBanksMessage) {
		this.dppBanksMessage = dppBanksMessage;
	}

	public void setDppBanksCompaniesPercentageFormatted(String dppBanksCompaniesPercentageFormatted) {
		this.dppBanksCompaniesPercentageFormatted = dppBanksCompaniesPercentageFormatted;
	}

	public void setDppFinancialInstitutionsMessage(String dppFinancialInstitutionsMessage) {
		this.dppFinancialInstitutionsMessage = dppFinancialInstitutionsMessage;
	}

	public void setDppFinancialInstitutionsCompaniesPercentageFormatted(
			String dppFinancialInstitutionsCompaniesPercentageFormatted) {
		this.dppFinancialInstitutionsCompaniesPercentageFormatted = dppFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public void setDppForeignInstitutionalInvestorsMessage(String dppForeignInstitutionalInvestorsMessage) {
		this.dppForeignInstitutionalInvestorsMessage = dppForeignInstitutionalInvestorsMessage;
	}

	public void setDppForeignInstitutionalInvestorsPercentageFormatted(
			String dppForeignInstitutionalInvestorsPercentageFormatted) {
		this.dppForeignInstitutionalInvestorsPercentageFormatted = dppForeignInstitutionalInvestorsPercentageFormatted;
	}

	public void setDppMutualFundsMessage(String dppMutualFundsMessage) {
		this.dppMutualFundsMessage = dppMutualFundsMessage;
	}

	public void setDppMutualFundsPercentageFormatted(String dppMutualFundsPercentageFormatted) {
		this.dppMutualFundsPercentageFormatted = dppMutualFundsPercentageFormatted;
	}

	public void setDppVentureCapitalMessage(String dppVentureCapitalMessage) {
		this.dppVentureCapitalMessage = dppVentureCapitalMessage;
	}

	public void setDppVentureCapitalPercentageFormatted(String dppVentureCapitalPercentageFormatted) {
		this.dppVentureCapitalPercentageFormatted = dppVentureCapitalPercentageFormatted;
	}

	public void setDppBodyCorporateMessage(String dppBodyCorporateMessage) {
		this.dppBodyCorporateMessage = dppBodyCorporateMessage;
	}

	public void setDppBodyCorporatePercentageFormatted(String dppBodyCorporatePercentageFormatted) {
		this.dppBodyCorporatePercentageFormatted = dppBodyCorporatePercentageFormatted;
	}

	public void setDppOthersMessage(String dppOthersMessage) {
		this.dppOthersMessage = dppOthersMessage;
	}

	public void setDppOthersPercentageFormatted(String dppOthersPercentageFormatted) {
		this.dppOthersPercentageFormatted = dppOthersPercentageFormatted;
	}

	public void setDppTotalMessage(String dppTotalMessage) {
		this.dppTotalMessage = dppTotalMessage;
	}

	public void setDppTotalPercentageFormatted(String dppTotalPercentageFormatted) {
		this.dppTotalPercentageFormatted = dppTotalPercentageFormatted;
	}

	public void setDotpeIndianMessage(String dotpeIndianMessage) {
		this.dotpeIndianMessage = dotpeIndianMessage;
	}

	public void setDotpeIndianPercentageFormatted(String dotpeIndianPercentageFormatted) {
		this.dotpeIndianPercentageFormatted = dotpeIndianPercentageFormatted;
	}

	public void setDotpeNriMessage(String dotpeNriMessage) {
		this.dotpeNriMessage = dotpeNriMessage;
	}

	public void setDotpeNriPercentageFormatted(String dotpeNriPercentageFormatted) {
		this.dotpeNriPercentageFormatted = dotpeNriPercentageFormatted;
	}

	public void setDotpeForeignNationalOtnMessage(String dotpeForeignNationalOtnMessage) {
		this.dotpeForeignNationalOtnMessage = dotpeForeignNationalOtnMessage;
	}

	public void setDotpeForeignNationalOtnPercentageFormatted(String dotpeForeignNationalOtnPercentageFormatted) {
		this.dotpeForeignNationalOtnPercentageFormatted = dotpeForeignNationalOtnPercentageFormatted;
	}

	public void setDotpeCentralGovernmentMessage(String dotpeCentralGovernmentMessage) {
		this.dotpeCentralGovernmentMessage = dotpeCentralGovernmentMessage;
	}

	public void setDotpeCentralGovernmentPercentageFormatted(String dotpeCentralGovernmentPercentageFormatted) {
		this.dotpeCentralGovernmentPercentageFormatted = dotpeCentralGovernmentPercentageFormatted;
	}

	public void setDotpeStateGovernmentMessage(String dotpeStateGovernmentMessage) {
		this.dotpeStateGovernmentMessage = dotpeStateGovernmentMessage;
	}

	public void setDotpeStateGovernmentPercentageFormatted(String dotpeStateGovernmentPercentageFormatted) {
		this.dotpeStateGovernmentPercentageFormatted = dotpeStateGovernmentPercentageFormatted;
	}

	public void setDotpeGovernmentCompaniesMessage(String dotpeGovernmentCompaniesMessage) {
		this.dotpeGovernmentCompaniesMessage = dotpeGovernmentCompaniesMessage;
	}

	public void setDotpeGovernmentCompaniesPercentageFormatted(String dotpeGovernmentCompaniesPercentageFormatted) {
		this.dotpeGovernmentCompaniesPercentageFormatted = dotpeGovernmentCompaniesPercentageFormatted;
	}

	public void setDotpeInsuranceCompaniesPercentageFormatted(String dotpeInsuranceCompaniesPercentageFormatted) {
		this.dotpeInsuranceCompaniesPercentageFormatted = dotpeInsuranceCompaniesPercentageFormatted;
	}

	public void setDotpeBanksMessage(String dotpeBanksMessage) {
		this.dotpeBanksMessage = dotpeBanksMessage;
	}

	public void setDotpeBanksCompaniesPercentageFormatted(String dotpeBanksCompaniesPercentageFormatted) {
		this.dotpeBanksCompaniesPercentageFormatted = dotpeBanksCompaniesPercentageFormatted;
	}

	public void setDotpeFinancialInstitutionsMessage(String dotpeFinancialInstitutionsMessage) {
		this.dotpeFinancialInstitutionsMessage = dotpeFinancialInstitutionsMessage;
	}

	public void setDotpeFinancialInstitutionsCompaniesPercentageFormatted(
			String dotpeFinancialInstitutionsCompaniesPercentageFormatted) {
		this.dotpeFinancialInstitutionsCompaniesPercentageFormatted = dotpeFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public void setDotpeForeignInstitutionalInvestorsMessage(String dotpeForeignInstitutionalInvestorsMessage) {
		this.dotpeForeignInstitutionalInvestorsMessage = dotpeForeignInstitutionalInvestorsMessage;
	}

	public void setDotpeForeignInstitutionalInvestorsPercentageFormatted(
			String dotpeForeignInstitutionalInvestorsPercentageFormatted) {
		this.dotpeForeignInstitutionalInvestorsPercentageFormatted = dotpeForeignInstitutionalInvestorsPercentageFormatted;
	}

	public void setDotpeMutualFundsMessage(String dotpeMutualFundsMessage) {
		this.dotpeMutualFundsMessage = dotpeMutualFundsMessage;
	}

	public void setDotpeMutualFundsPercentageFormatted(String dotpeMutualFundsPercentageFormatted) {
		this.dotpeMutualFundsPercentageFormatted = dotpeMutualFundsPercentageFormatted;
	}

	public void setDotpeVentureCapitalMessage(String dotpeVentureCapitalMessage) {
		this.dotpeVentureCapitalMessage = dotpeVentureCapitalMessage;
	}

	public void setDotpeVentureCapitalPercentageFormatted(String dotpeVentureCapitalPercentageFormatted) {
		this.dotpeVentureCapitalPercentageFormatted = dotpeVentureCapitalPercentageFormatted;
	}

	public void setDotpeBodyCorporateMessage(String dotpeBodyCorporateMessage) {
		this.dotpeBodyCorporateMessage = dotpeBodyCorporateMessage;
	}

	public void setDotpeBodyCorporatePercentageFormatted(String dotpeBodyCorporatePercentageFormatted) {
		this.dotpeBodyCorporatePercentageFormatted = dotpeBodyCorporatePercentageFormatted;
	}

	public void setDotpeOthersMessage(String dotpeOthersMessage) {
		this.dotpeOthersMessage = dotpeOthersMessage;
	}

	public void setDotpeOthersPercentageFormatted(String dotpeOthersPercentageFormatted) {
		this.dotpeOthersPercentageFormatted = dotpeOthersPercentageFormatted;
	}

	public void setDotpeTotalMessage(String dotpeTotalMessage) {
		this.dotpeTotalMessage = dotpeTotalMessage;
	}

	public void setDotpeTotalPercentageFormatted(String dotpeTotalPercentageFormatted) {
		this.dotpeTotalPercentageFormatted = dotpeTotalPercentageFormatted;
	}

	public void setDotppIndianMessage(String dotppIndianMessage) {
		this.dotppIndianMessage = dotppIndianMessage;
	}

	public void setDotppIndianPercentageFormatted(String dotppIndianPercentageFormatted) {
		this.dotppIndianPercentageFormatted = dotppIndianPercentageFormatted;
	}

	public void setDotppNriMessage(String dotppNriMessage) {
		this.dotppNriMessage = dotppNriMessage;
	}

	public void setDotppNriPercentageFormatted(String dotppNriPercentageFormatted) {
		this.dotppNriPercentageFormatted = dotppNriPercentageFormatted;
	}

	public void setDotppForeignNationalOtnMessage(String dotppForeignNationalOtnMessage) {
		this.dotppForeignNationalOtnMessage = dotppForeignNationalOtnMessage;
	}

	public void setDotppForeignNationalOtnPercentageFormatted(String dotppForeignNationalOtnPercentageFormatted) {
		this.dotppForeignNationalOtnPercentageFormatted = dotppForeignNationalOtnPercentageFormatted;
	}

	public void setDotppCentralGovernmentMessage(String dotppCentralGovernmentMessage) {
		this.dotppCentralGovernmentMessage = dotppCentralGovernmentMessage;
	}

	public void setDotppCentralGovernmentPercentageFormatted(String dotppCentralGovernmentPercentageFormatted) {
		this.dotppCentralGovernmentPercentageFormatted = dotppCentralGovernmentPercentageFormatted;
	}

	public void setDotppStateGovernmentMessage(String dotppStateGovernmentMessage) {
		this.dotppStateGovernmentMessage = dotppStateGovernmentMessage;
	}

	public void setDotppStateGovernmentPercentageFormatted(String dotppStateGovernmentPercentageFormatted) {
		this.dotppStateGovernmentPercentageFormatted = dotppStateGovernmentPercentageFormatted;
	}

	public void setDotppGovernmentCompaniesMessage(String dotppGovernmentCompaniesMessage) {
		this.dotppGovernmentCompaniesMessage = dotppGovernmentCompaniesMessage;
	}

	public void setDotppGovernmentCompaniesPercentageFormatted(String dotppGovernmentCompaniesPercentageFormatted) {
		this.dotppGovernmentCompaniesPercentageFormatted = dotppGovernmentCompaniesPercentageFormatted;
	}

	public void setDotppInsuranceCompaniesPercentageFormatted(String dotppInsuranceCompaniesPercentageFormatted) {
		this.dotppInsuranceCompaniesPercentageFormatted = dotppInsuranceCompaniesPercentageFormatted;
	}

	public void setDotppBanksMessage(String dotppBanksMessage) {
		this.dotppBanksMessage = dotppBanksMessage;
	}

	public void setDotppBanksCompaniesPercentageFormatted(String dotppBanksCompaniesPercentageFormatted) {
		this.dotppBanksCompaniesPercentageFormatted = dotppBanksCompaniesPercentageFormatted;
	}

	public void setDotppFinancialInstitutionsMessage(String dotppFinancialInstitutionsMessage) {
		this.dotppFinancialInstitutionsMessage = dotppFinancialInstitutionsMessage;
	}

	public void setDotppFinancialInstitutionsCompaniesPercentageFormatted(
			String dotppFinancialInstitutionsCompaniesPercentageFormatted) {
		this.dotppFinancialInstitutionsCompaniesPercentageFormatted = dotppFinancialInstitutionsCompaniesPercentageFormatted;
	}

	public void setDotppForeignInstitutionalInvestorsMessage(String dotppForeignInstitutionalInvestorsMessage) {
		this.dotppForeignInstitutionalInvestorsMessage = dotppForeignInstitutionalInvestorsMessage;
	}

	public void setDotppForeignInstitutionalInvestorsPercentageFormatted(
			String dotppForeignInstitutionalInvestorsPercentageFormatted) {
		this.dotppForeignInstitutionalInvestorsPercentageFormatted = dotppForeignInstitutionalInvestorsPercentageFormatted;
	}

	public void setDotppMutualFundsMessage(String dotppMutualFundsMessage) {
		this.dotppMutualFundsMessage = dotppMutualFundsMessage;
	}

	public void setDotppMutualFundsPercentageFormatted(String dotppMutualFundsPercentageFormatted) {
		this.dotppMutualFundsPercentageFormatted = dotppMutualFundsPercentageFormatted;
	}

	public void setDotppVentureCapitalMessage(String dotppVentureCapitalMessage) {
		this.dotppVentureCapitalMessage = dotppVentureCapitalMessage;
	}

	public void setDotherTppvcPercentageFormatted(String dotherTppvcPercentageFormatted) {
		this.dotherTppvcPercentageFormatted = dotherTppvcPercentageFormatted;
	}

	public void setDotppBodyCorporateMessage(String dotppBodyCorporateMessage) {
		this.dotppBodyCorporateMessage = dotppBodyCorporateMessage;
	}

	public void setDotppBodyCorporatePercentageFormatted(String dotppBodyCorporatePercentageFormatted) {
		this.dotppBodyCorporatePercentageFormatted = dotppBodyCorporatePercentageFormatted;
	}

	public void setDotppOthersMessage(String dotppOthersMessage) {
		this.dotppOthersMessage = dotppOthersMessage;
	}

	public void setDotppOthersPercentageFormatted(String dotppOthersPercentageFormatted) {
		this.dotppOthersPercentageFormatted = dotppOthersPercentageFormatted;
	}

	public void setDotppTotalMessage(String dotppTotalMessage) {
		this.dotppTotalMessage = dotppTotalMessage;
	}

	public void setDotppTotalPercentageFormatted(String dotppTotalPercentageFormatted) {
		this.dotppTotalPercentageFormatted = dotppTotalPercentageFormatted;
	}

	public void setDummyUesPercentageFormatted(String dummyUesPercentageFormatted) {
		this.dummyUesPercentageFormatted = dummyUesPercentageFormatted;
	}

	public void setDummyUpspFormatted(String dummyUpspFormatted) {
		this.dummyUpspFormatted = dummyUpspFormatted;
	}

	public void setDpeInsuranceCompaniesMessage(String dpeInsuranceCompaniesMessage) {
		this.dpeInsuranceCompaniesMessage = dpeInsuranceCompaniesMessage;
	}

	public void setDppInsuranceCompaniesMessage(String dppInsuranceCompaniesMessage) {
		this.dppInsuranceCompaniesMessage = dppInsuranceCompaniesMessage;
	}

	public void setDotpeInsuranceCompaniesMessage(String dotpeInsuranceCompaniesMessage) {
		this.dotpeInsuranceCompaniesMessage = dotpeInsuranceCompaniesMessage;
	}

	public void setDotppInsuranceCompaniesMessage(String dotppInsuranceCompaniesMessage) {
		this.dotppInsuranceCompaniesMessage = dotppInsuranceCompaniesMessage;
	}

	public void setDummyEquityChartTitle(String dummyEquityChartTitle) {
		this.dummyEquityChartTitle = dummyEquityChartTitle;
	}

	public void setDpreferenceChartTitle(String dpreferenceChartTitle) {
		this.dpreferenceChartTitle = dpreferenceChartTitle;
	}

	public void setDummyPromoterTitle(String dummyPromoterTitle) {
		this.dummyPromoterTitle = dummyPromoterTitle;
	}

	public void setDotherThanPromoterTitle(String dotherThanPromoterTitle) {
		this.dotherThanPromoterTitle = dotherThanPromoterTitle;
	}

	public void setDummyUnclassifiedTitle(String dummyUnclassifiedTitle) {
		this.dummyUnclassifiedTitle = dummyUnclassifiedTitle;
	}

	public void setDummyShareholdingTitle(String dummyShareholdingTitle) {
		this.dummyShareholdingTitle = dummyShareholdingTitle;
	}

	public void setDummySharesTitle(String dummySharesTitle) {
		this.dummySharesTitle = dummySharesTitle;
	}

	public void setDpercentageTitle(String dpercentageTitle) {
		this.dpercentageTitle = dpercentageTitle;
	}

	public void setDummyIndividualHufTitle(String dummyIndividualHufTitle) {
		this.dummyIndividualHufTitle = dummyIndividualHufTitle;
	}

	public void setDummyGovernmentTitle(String dummyGovernmentTitle) {
		this.dummyGovernmentTitle = dummyGovernmentTitle;
	}

	public void setDummyEquitySharesWithoutDifferentialRightsTitle(String dummyEquitySharesWithoutDifferentialRightsTitle) {
		this.dummyEquitySharesWithoutDifferentialRightsTitle = dummyEquitySharesWithoutDifferentialRightsTitle;
	}

	public void setDpswdifferentialRightsTitle(String dpswdifferentialRightsTitle) {
		this.dpswdifferentialRightsTitle = dpswdifferentialRightsTitle;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
    
    

}
