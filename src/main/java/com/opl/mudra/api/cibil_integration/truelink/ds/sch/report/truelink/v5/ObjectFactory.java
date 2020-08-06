
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.opl.mudra.api.cibil_integration.truelink.ds.sch.pii.v1.AddressType;
import com.opl.mudra.api.cibil_integration.truelink.ds.sch.pii.v1.FullNameType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.truelink.ds.sch.report.truelink.v5 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreditScoreModel_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "CreditScoreModel");
    private final static QName _CreditAddress_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "CreditAddress");
    private final static QName _OccupationCode_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "OccupationCode");
    private final static QName _TrueLinkCreditReport_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "TrueLinkCreditReport");
    private final static QName _NameType_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "NameType");
    private final static QName _StatementType_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "StatementType");
    private final static QName _AccountCondition_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "AccountCondition");
    private final static QName _Dwelling_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "Dwelling");
    private final static QName _IndustryCode_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "IndustryCode");
    private final static QName _IncomeFreqIndicator_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "IncomeFreqIndicator");
    private final static QName _CreditorType_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "CreditorType");
    private final static QName _Name_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "Name");
    private final static QName _AccountDesignator_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "AccountDesignator");
    private final static QName _NetGrossIndicator_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "NetGrossIndicator");
    private final static QName _VerificationIndicator_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "VerificationIndicator");
    private final static QName _CreditScore_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "CreditScore");
    private final static QName _PayStatus_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "PayStatus");
    private final static QName _NoScoreReason_QNAME = new QName("com/truelink/ds/sch/report/truelink/v5", "NoScoreReason");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.truelink.ds.sch.report.truelink.v5
     * 
     */
    public ObjectFactory() {// Do nothing because of X and Y.
    }

    /**
     * Create an instance of {@link DisputeRemarks }
     * 
     */
    public DisputeRemarks createDisputeRemarks() {
        return new DisputeRemarks();
    }

    /**
     * Create an instance of {@link BorrowerComparison }
     * 
     */
    public BorrowerComparison createBorrowerComparison() {
        return new BorrowerComparison();
    }

    /**
     * Create an instance of {@link SummaryComparison }
     * 
     */
    public SummaryComparison createSummaryComparison() {
        return new SummaryComparison();
    }

    /**
     * Create an instance of {@link Borrower }
     * 
     */
    public Borrower createBorrower() {
        return new Borrower();
    }

    /**
     * Create an instance of {@link Summary }
     * 
     */
    public Summary createSummary() {
        return new Summary();
    }

    /**
     * Create an instance of {@link ChangeReportType }
     * 
     */
    public ChangeReportType createChangeReportType() {
        return new ChangeReportType();
    }

    /**
     * Create an instance of {@link ChangeReportType.Sources }
     * 
     */
    public ChangeReportType.Sources createChangeReportTypeSources() {
        return new ChangeReportType.Sources();
    }

    /**
     * Create an instance of {@link Summary.PublicRecordSummary }
     * 
     */
    public Summary.PublicRecordSummary createSummaryPublicRecordSummary() {
        return new Summary.PublicRecordSummary();
    }

    /**
     * Create an instance of {@link Summary.InquirySummary }
     * 
     */
    public Summary.InquirySummary createSummaryInquirySummary() {
        return new Summary.InquirySummary();
    }

    /**
     * Create an instance of {@link Summary.TradelineSummary }
     * 
     */
    public Summary.TradelineSummary createSummaryTradelineSummary() {
        return new Summary.TradelineSummary();
    }

    /**
     * Create an instance of {@link SummaryComparison.PublicRecordSummary }
     * 
     */
    public SummaryComparison.PublicRecordSummary createSummaryComparisonPublicRecordSummary() {
        return new SummaryComparison.PublicRecordSummary();
    }

    /**
     * Create an instance of {@link SummaryComparison.InquirySummary }
     * 
     */
    public SummaryComparison.InquirySummary createSummaryComparisonInquirySummary() {
        return new SummaryComparison.InquirySummary();
    }

    /**
     * Create an instance of {@link SummaryComparison.TradelineSummary }
     * 
     */
    public SummaryComparison.TradelineSummary createSummaryComparisonTradelineSummary() {
        return new SummaryComparison.TradelineSummary();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType }
     * 
     */
    public TrueLinkCreditReportType createTrueLinkCreditReportType() {
        return new TrueLinkCreditReportType();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.Sources }
     * 
     */
    public TrueLinkCreditReportType.Sources createTrueLinkCreditReportTypeSources() {
        return new TrueLinkCreditReportType.Sources();
    }

    /**
     * Create an instance of {@link BorrowerAddress }
     * 
     */
    public BorrowerAddress createBorrowerAddress() {
        return new BorrowerAddress();
    }

    /**
     * Create an instance of {@link CodeRef }
     * 
     */
    public CodeRef createCodeRef() {
        return new CodeRef();
    }

    /**
     * Create an instance of {@link com.opl.msme.api.model.cibil_integration.truelink.ds.sch.report.truelink.v5.Source }
     * 
     */
    public com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5.Source createSource() {
        return new com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5.Source();
    }

    /**
     * Create an instance of {@link MiscPublicRecordComparison }
     * 
     */
    public MiscPublicRecordComparison createMiscPublicRecordComparison() {
        return new MiscPublicRecordComparison();
    }

    /**
     * Create an instance of {@link MiscPublicRecord }
     * 
     */
    public MiscPublicRecord createMiscPublicRecord() {
        return new MiscPublicRecord();
    }

    /**
     * Create an instance of {@link FinancingStatement }
     * 
     */
    public FinancingStatement createFinancingStatement() {
        return new FinancingStatement();
    }

    /**
     * Create an instance of {@link Foreclosure }
     * 
     */
    public Foreclosure createForeclosure() {
        return new Foreclosure();
    }

    /**
     * Create an instance of {@link RegisteredItem }
     * 
     */
    public RegisteredItem createRegisteredItem() {
        return new RegisteredItem();
    }

    /**
     * Create an instance of {@link MonthlyPayStatus }
     * 
     */
    public MonthlyPayStatus createMonthlyPayStatus() {
        return new MonthlyPayStatus();
    }

    /**
     * Create an instance of {@link CreditScoreFactor }
     * 
     */
    public CreditScoreFactor createCreditScoreFactor() {
        return new CreditScoreFactor();
    }

    /**
     * Create an instance of {@link Remark }
     * 
     */
    public Remark createRemark() {
        return new Remark();
    }

    /**
     * Create an instance of {@link PreviousAddress }
     * 
     */
    public PreviousAddress createPreviousAddress() {
        return new PreviousAddress();
    }

    /**
     * Create an instance of {@link Identifier }
     * 
     */
    public Identifier createIdentifier() {
        return new Identifier();
    }

    /**
     * Create an instance of {@link Subscriber }
     * 
     */
    public Subscriber createSubscriber() {
        return new Subscriber();
    }

    /**
     * Create an instance of {@link CollectionTradeComparison }
     * 
     */
    public CollectionTradeComparison createCollectionTradeComparison() {
        return new CollectionTradeComparison();
    }

    /**
     * Create an instance of {@link SubscriberComparison }
     * 
     */
    public SubscriberComparison createSubscriberComparison() {
        return new SubscriberComparison();
    }

    /**
     * Create an instance of {@link LegalItemComparison }
     * 
     */
    public LegalItemComparison createLegalItemComparison() {
        return new LegalItemComparison();
    }

    /**
     * Create an instance of {@link MaritalItemComparison }
     * 
     */
    public MaritalItemComparison createMaritalItemComparison() {
        return new MaritalItemComparison();
    }

    /**
     * Create an instance of {@link FinancingStatementComparsion }
     * 
     */
    public FinancingStatementComparsion createFinancingStatementComparsion() {
        return new FinancingStatementComparsion();
    }

    /**
     * Create an instance of {@link DisputeRemarks.ErrorCode }
     * 
     */
    public DisputeRemarks.ErrorCode createDisputeRemarksErrorCode() {
        return new DisputeRemarks.ErrorCode();
    }

    /**
     * Create an instance of {@link DisputeRemarks.RemarksCode }
     * 
     */
    public DisputeRemarks.RemarksCode createDisputeRemarksRemarksCode() {
        return new DisputeRemarks.RemarksCode();
    }

    /**
     * Create an instance of {@link Bankruptcy }
     * 
     */
    public Bankruptcy createBankruptcy() {
        return new Bankruptcy();
    }

    /**
     * Create an instance of {@link Employer }
     * 
     */
    public Employer createEmployer() {
        return new Employer();
    }

    /**
     * Create an instance of {@link GrantedTrade }
     * 
     */
    public GrantedTrade createGrantedTrade() {
        return new GrantedTrade();
    }

    /**
     * Create an instance of {@link PayStatusHistory }
     * 
     */
    public PayStatusHistory createPayStatusHistory() {
        return new PayStatusHistory();
    }

    /**
     * Create an instance of {@link GrantedTradeComparison }
     * 
     */
    public GrantedTradeComparison createGrantedTradeComparison() {
        return new GrantedTradeComparison();
    }

    /**
     * Create an instance of {@link LegalItem }
     * 
     */
    public LegalItem createLegalItem() {
        return new LegalItem();
    }

    /**
     * Create an instance of {@link FinancialCounseling }
     * 
     */
    public FinancialCounseling createFinancialCounseling() {
        return new FinancialCounseling();
    }

    /**
     * Create an instance of {@link PublicRecord }
     * 
     */
    public PublicRecord createPublicRecord() {
        return new PublicRecord();
    }

    /**
     * Create an instance of {@link Garnishment }
     * 
     */
    public Garnishment createGarnishment() {
        return new Garnishment();
    }

    /**
     * Create an instance of {@link MaritalItem }
     * 
     */
    public MaritalItem createMaritalItem() {
        return new MaritalItem();
    }

    /**
     * Create an instance of {@link TaxLien }
     * 
     */
    public TaxLien createTaxLien() {
        return new TaxLien();
    }

    /**
     * Create an instance of {@link TaxLienComparison }
     * 
     */
    public TaxLienComparison createTaxLienComparison() {
        return new TaxLienComparison();
    }

    /**
     * Create an instance of {@link WatchTrade }
     * 
     */
    public WatchTrade createWatchTrade() {
        return new WatchTrade();
    }

    /**
     * Create an instance of {@link FinancialCounselingComparison }
     * 
     */
    public FinancialCounselingComparison createFinancialCounselingComparison() {
        return new FinancialCounselingComparison();
    }

    /**
     * Create an instance of {@link PublicRecordComparison }
     * 
     */
    public PublicRecordComparison createPublicRecordComparison() {
        return new PublicRecordComparison();
    }

    /**
     * Create an instance of {@link BankruptcyComparison }
     * 
     */
    public BankruptcyComparison createBankruptcyComparison() {
        return new BankruptcyComparison();
    }

    /**
     * Create an instance of {@link ForeclosureComparison }
     * 
     */
    public ForeclosureComparison createForeclosureComparison() {
        return new ForeclosureComparison();
    }

    /**
     * Create an instance of {@link GarnishmentComparison }
     * 
     */
    public GarnishmentComparison createGarnishmentComparison() {
        return new GarnishmentComparison();
    }

    /**
     * Create an instance of {@link BorrowerName }
     * 
     */
    public BorrowerName createBorrowerName() {
        return new BorrowerName();
    }

    /**
     * Create an instance of {@link BorrowerComparison.AddressPartition }
     * 
     */
    public BorrowerComparison.AddressPartition createBorrowerComparisonAddressPartition() {
        return new BorrowerComparison.AddressPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.PreviousAddressPartition }
     * 
     */
    public BorrowerComparison.PreviousAddressPartition createBorrowerComparisonPreviousAddressPartition() {
        return new BorrowerComparison.PreviousAddressPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.EmailPartition }
     * 
     */
    public BorrowerComparison.EmailPartition createBorrowerComparisonEmailPartition() {
        return new BorrowerComparison.EmailPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.BirthPartition }
     * 
     */
    public BorrowerComparison.BirthPartition createBorrowerComparisonBirthPartition() {
        return new BorrowerComparison.BirthPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.GenderPartition }
     * 
     */
    public BorrowerComparison.GenderPartition createBorrowerComparisonGenderPartition() {
        return new BorrowerComparison.GenderPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.BorrowerNamePartition }
     * 
     */
    public BorrowerComparison.BorrowerNamePartition createBorrowerComparisonBorrowerNamePartition() {
        return new BorrowerComparison.BorrowerNamePartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.CreditStatementPartition }
     * 
     */
    public BorrowerComparison.CreditStatementPartition createBorrowerComparisonCreditStatementPartition() {
        return new BorrowerComparison.CreditStatementPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.EmployerPartition }
     * 
     */
    public BorrowerComparison.EmployerPartition createBorrowerComparisonEmployerPartition() {
        return new BorrowerComparison.EmployerPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.IdentifierPartition }
     * 
     */
    public BorrowerComparison.IdentifierPartition createBorrowerComparisonIdentifierPartition() {
        return new BorrowerComparison.IdentifierPartition();
    }

    /**
     * Create an instance of {@link BorrowerComparison.CreditScorePartition }
     * 
     */
    public BorrowerComparison.CreditScorePartition createBorrowerComparisonCreditScorePartition() {
        return new BorrowerComparison.CreditScorePartition();
    }

    /**
     * Create an instance of {@link TradelineComparison }
     * 
     */
    public TradelineComparison createTradelineComparison() {
        return new TradelineComparison();
    }

    /**
     * Create an instance of {@link WatchTradeComparison }
     * 
     */
    public WatchTradeComparison createWatchTradeComparison() {
        return new WatchTradeComparison();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link Birth }
     * 
     */
    public Birth createBirth() {
        return new Birth();
    }

    /**
     * Create an instance of {@link Date }
     * 
     */
    public Date createDate() {
        return new Date();
    }

    /**
     * Create an instance of {@link BirthComparison }
     * 
     */
    public BirthComparison createBirthComparison() {
        return new BirthComparison();
    }

    /**
     * Create an instance of {@link EmployerComparison }
     * 
     */
    public EmployerComparison createEmployerComparison() {
        return new EmployerComparison();
    }

    /**
     * Create an instance of {@link CreditStatement }
     * 
     */
    public CreditStatement createCreditStatement() {
        return new CreditStatement();
    }

    /**
     * Create an instance of {@link InquiryComparison }
     * 
     */
    public InquiryComparison createInquiryComparison() {
        return new InquiryComparison();
    }

    /**
     * Create an instance of {@link BorrowerTelephone }
     * 
     */
    public BorrowerTelephone createBorrowerTelephone() {
        return new BorrowerTelephone();
    }

    /**
     * Create an instance of {@link CreditStatementComparison }
     * 
     */
    public CreditStatementComparison createCreditStatementComparison() {
        return new CreditStatementComparison();
    }

    /**
     * Create an instance of {@link BankingRecord }
     * 
     */
    public BankingRecord createBankingRecord() {
        return new BankingRecord();
    }

    /**
     * Create an instance of {@link BorrowerNameComparison }
     * 
     */
    public BorrowerNameComparison createBorrowerNameComparison() {
        return new BorrowerNameComparison();
    }

    /**
     * Create an instance of {@link CreditScoreType }
     * 
     */
    public CreditScoreType createCreditScoreType() {
        return new CreditScoreType();
    }

    /**
     * Create an instance of {@link Tradeline }
     * 
     */
    public Tradeline createTradeline() {
        return new Tradeline();
    }

    /**
     * Create an instance of {@link CollectionTrade }
     * 
     */
    public CollectionTrade createCollectionTrade() {
        return new CollectionTrade();
    }

    /**
     * Create an instance of {@link EmailAddressType }
     * 
     */
    public EmailAddressType createEmailAddressType() {
        return new EmailAddressType();
    }

    /**
     * Create an instance of {@link Borrower.IdentifierPartition }
     * 
     */
    public Borrower.IdentifierPartition createBorrowerIdentifierPartition() {
        return new Borrower.IdentifierPartition();
    }

    /**
     * Create an instance of {@link Inquiry }
     * 
     */
    public Inquiry createInquiry() {
        return new Inquiry();
    }

    /**
     * Create an instance of {@link BorrowerAddressComparison }
     * 
     */
    public BorrowerAddressComparison createBorrowerAddressComparison() {
        return new BorrowerAddressComparison();
    }

    /**
     * Create an instance of {@link MessageComparison }
     * 
     */
    public MessageComparison createMessageComparison() {
        return new MessageComparison();
    }

    /**
     * Create an instance of {@link PublicRecordSummaryInfoComparison }
     * 
     */
    public PublicRecordSummaryInfoComparison createPublicRecordSummaryInfoComparison() {
        return new PublicRecordSummaryInfoComparison();
    }

    /**
     * Create an instance of {@link TradelineSummaryInfo }
     * 
     */
    public TradelineSummaryInfo createTradelineSummaryInfo() {
        return new TradelineSummaryInfo();
    }

    /**
     * Create an instance of {@link TradelineSummaryInfoComparison }
     * 
     */
    public TradelineSummaryInfoComparison createTradelineSummaryInfoComparison() {
        return new TradelineSummaryInfoComparison();
    }

    /**
     * Create an instance of {@link InquirySummaryInfoComparison }
     * 
     */
    public InquirySummaryInfoComparison createInquirySummaryInfoComparison() {
        return new InquirySummaryInfoComparison();
    }

    /**
     * Create an instance of {@link InquirySummaryInfo }
     * 
     */
    public InquirySummaryInfo createInquirySummaryInfo() {
        return new InquirySummaryInfo();
    }

    /**
     * Create an instance of {@link PublicRecordSummaryInfo }
     * 
     */
    public PublicRecordSummaryInfo createPublicRecordSummaryInfo() {
        return new PublicRecordSummaryInfo();
    }

    /**
     * Create an instance of {@link ChangeReportType.SB168Frozen }
     * 
     */
    public ChangeReportType.SB168Frozen createChangeReportTypeSB168Frozen() {
        return new ChangeReportType.SB168Frozen();
    }

    /**
     * Create an instance of {@link ChangeReportType.TradeLinePartition }
     * 
     */
    public ChangeReportType.TradeLinePartition createChangeReportTypeTradeLinePartition() {
        return new ChangeReportType.TradeLinePartition();
    }

    /**
     * Create an instance of {@link ChangeReportType.InquiryPartition }
     * 
     */
    public ChangeReportType.InquiryPartition createChangeReportTypeInquiryPartition() {
        return new ChangeReportType.InquiryPartition();
    }

    /**
     * Create an instance of {@link ChangeReportType.PublicRecordPartition }
     * 
     */
    public ChangeReportType.PublicRecordPartition createChangeReportTypePublicRecordPartition() {
        return new ChangeReportType.PublicRecordPartition();
    }

    /**
     * Create an instance of {@link ChangeReportType.SubscriberPartition }
     * 
     */
    public ChangeReportType.SubscriberPartition createChangeReportTypeSubscriberPartition() {
        return new ChangeReportType.SubscriberPartition();
    }

    /**
     * Create an instance of {@link ChangeReportType.MessagePartition }
     * 
     */
    public ChangeReportType.MessagePartition createChangeReportTypeMessagePartition() {
        return new ChangeReportType.MessagePartition();
    }

    /**
     * Create an instance of {@link ChangeReportType.SummaryPartition }
     * 
     */
    public ChangeReportType.SummaryPartition createChangeReportTypeSummaryPartition() {
        return new ChangeReportType.SummaryPartition();
    }

    /**
     * Create an instance of {@link ChangeReportType.Sources.Source }
     * 
     */
    public ChangeReportType.Sources.Source createChangeReportTypeSourcesSource() {
        return new ChangeReportType.Sources.Source();
    }

    /**
     * Create an instance of {@link Summary.PublicRecordSummary.Bureau }
     * 
     */
    public Summary.PublicRecordSummary.Bureau createSummaryPublicRecordSummaryBureau() {
        return new Summary.PublicRecordSummary.Bureau();
    }

    /**
     * Create an instance of {@link Summary.InquirySummary.Bureau }
     * 
     */
    public Summary.InquirySummary.Bureau createSummaryInquirySummaryBureau() {
        return new Summary.InquirySummary.Bureau();
    }

    /**
     * Create an instance of {@link Summary.TradelineSummary.Bureau }
     * 
     */
    public Summary.TradelineSummary.Bureau createSummaryTradelineSummaryBureau() {
        return new Summary.TradelineSummary.Bureau();
    }

    /**
     * Create an instance of {@link SummaryComparison.PublicRecordSummary.Bureau }
     * 
     */
    public SummaryComparison.PublicRecordSummary.Bureau createSummaryComparisonPublicRecordSummaryBureau() {
        return new SummaryComparison.PublicRecordSummary.Bureau();
    }

    /**
     * Create an instance of {@link SummaryComparison.InquirySummary.Bureau }
     * 
     */
    public SummaryComparison.InquirySummary.Bureau createSummaryComparisonInquirySummaryBureau() {
        return new SummaryComparison.InquirySummary.Bureau();
    }

    /**
     * Create an instance of {@link SummaryComparison.TradelineSummary.Bureau }
     * 
     */
    public SummaryComparison.TradelineSummary.Bureau createSummaryComparisonTradelineSummaryBureau() {
        return new SummaryComparison.TradelineSummary.Bureau();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.Frozen }
     * 
     */
    public TrueLinkCreditReportType.Frozen createTrueLinkCreditReportTypeFrozen() {
        return new TrueLinkCreditReportType.Frozen();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.TradeLinePartition }
     * 
     */
    public TrueLinkCreditReportType.TradeLinePartition createTrueLinkCreditReportTypeTradeLinePartition() {
        return new TrueLinkCreditReportType.TradeLinePartition();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.InquiryPartition }
     * 
     */
    public TrueLinkCreditReportType.InquiryPartition createTrueLinkCreditReportTypeInquiryPartition() {
        return new TrueLinkCreditReportType.InquiryPartition();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.BankingRecordPartition }
     * 
     */
    public TrueLinkCreditReportType.BankingRecordPartition createTrueLinkCreditReportTypeBankingRecordPartition() {
        return new TrueLinkCreditReportType.BankingRecordPartition();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.PublicRecordPartition }
     * 
     */
    public TrueLinkCreditReportType.PublicRecordPartition createTrueLinkCreditReportTypePublicRecordPartition() {
        return new TrueLinkCreditReportType.PublicRecordPartition();
    }

    /**
     * Create an instance of {@link TrueLinkCreditReportType.Sources.Source }
     * 
     */
    public TrueLinkCreditReportType.Sources.Source createTrueLinkCreditReportTypeSourcesSource() {
        return new TrueLinkCreditReportType.Sources.Source();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "CreditScoreModel")
    public JAXBElement<CodeRef> createCreditScoreModel(CodeRef value) {
        return new JAXBElement<CodeRef>(_CreditScoreModel_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "CreditAddress")
    public JAXBElement<AddressType> createCreditAddress(AddressType value) {
        return new JAXBElement<AddressType>(_CreditAddress_QNAME, AddressType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "OccupationCode")
    public JAXBElement<CodeRef> createOccupationCode(CodeRef value) {
        return new JAXBElement<CodeRef>(_OccupationCode_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrueLinkCreditReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "TrueLinkCreditReport")
    public JAXBElement<TrueLinkCreditReportType> createTrueLinkCreditReport(TrueLinkCreditReportType value) {
        return new JAXBElement<TrueLinkCreditReportType>(_TrueLinkCreditReport_QNAME, TrueLinkCreditReportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "NameType")
    public JAXBElement<CodeRef> createNameType(CodeRef value) {
        return new JAXBElement<CodeRef>(_NameType_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "StatementType")
    public JAXBElement<CodeRef> createStatementType(CodeRef value) {
        return new JAXBElement<CodeRef>(_StatementType_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "AccountCondition")
    public JAXBElement<CodeRef> createAccountCondition(CodeRef value) {
        return new JAXBElement<CodeRef>(_AccountCondition_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "Dwelling")
    public JAXBElement<CodeRef> createDwelling(CodeRef value) {
        return new JAXBElement<CodeRef>(_Dwelling_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "IndustryCode")
    public JAXBElement<CodeRef> createIndustryCode(CodeRef value) {
        return new JAXBElement<CodeRef>(_IndustryCode_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "IncomeFreqIndicator")
    public JAXBElement<CodeRef> createIncomeFreqIndicator(CodeRef value) {
        return new JAXBElement<CodeRef>(_IncomeFreqIndicator_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "CreditorType")
    public JAXBElement<CodeRef> createCreditorType(CodeRef value) {
        return new JAXBElement<CodeRef>(_CreditorType_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FullNameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "Name")
    public JAXBElement<FullNameType> createName(FullNameType value) {
        return new JAXBElement<FullNameType>(_Name_QNAME, FullNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "AccountDesignator")
    public JAXBElement<CodeRef> createAccountDesignator(CodeRef value) {
        return new JAXBElement<CodeRef>(_AccountDesignator_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "NetGrossIndicator")
    public JAXBElement<CodeRef> createNetGrossIndicator(CodeRef value) {
        return new JAXBElement<CodeRef>(_NetGrossIndicator_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "VerificationIndicator")
    public JAXBElement<CodeRef> createVerificationIndicator(CodeRef value) {
        return new JAXBElement<CodeRef>(_VerificationIndicator_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditScoreType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "CreditScore")
    public JAXBElement<CreditScoreType> createCreditScore(CreditScoreType value) {
        return new JAXBElement<CreditScoreType>(_CreditScore_QNAME, CreditScoreType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "PayStatus")
    public JAXBElement<CodeRef> createPayStatus(CodeRef value) {
        return new JAXBElement<CodeRef>(_PayStatus_QNAME, CodeRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/truelink/ds/sch/report/truelink/v5", name = "NoScoreReason")
    public JAXBElement<CodeRef> createNoScoreReason(CodeRef value) {
        return new JAXBElement<CodeRef>(_NoScoreReason_QNAME, CodeRef.class, null, value);
    }

}
