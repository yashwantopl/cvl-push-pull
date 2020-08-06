
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5.TrueLinkCreditReportType;
import com.opl.mudra.api.cibil.model.truelink.schema.msp.FailureResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.transunion.gccs.core.services.v1 package. 
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

    private final static QName _Failure_QNAME = new QName("com/transunion/gccs/core/services/v1", "Failure");
    private final static QName _AssetDataTypeTrueLinkCreditReport_QNAME = new QName("com/transunion/gccs/core/services/v1", "TrueLinkCreditReport");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.transunion.gccs.core.services.v1
     * 
     */
    public ObjectFactory() {// Do nothing because of X and Y.
    }

    /**
     * Create an instance of {@link FulfillOfferRequest }
     * 
     */
    public FulfillOfferRequest createFulfillOfferRequest() {
        return new FulfillOfferRequest();
    }

    /**
     * Create an instance of {@link IVQuestion }
     * 
     */
    public IVQuestion createIVQuestion() {
        return new IVQuestion();
    }

    /**
     * Create an instance of {@link IVQuestion.AnswerChoice }
     * 
     */
    public IVQuestion.AnswerChoice createIVQuestionAnswerChoice() {
        return new IVQuestion.AnswerChoice();
    }

    /**
     * Create an instance of {@link PingRequest }
     * 
     */
    public PingRequest createPingRequest() {
        return new PingRequest();
    }

    /**
     * Create an instance of {@link BaseRequestType }
     * 
     */
    public BaseRequestType createBaseRequestType() {
        return new BaseRequestType();
    }

    /**
     * Create an instance of {@link VerifyAuthenticationAnswersSuccess }
     * 
     */
    public VerifyAuthenticationAnswersSuccess createVerifyAuthenticationAnswersSuccess() {
        return new VerifyAuthenticationAnswersSuccess();
    }

    /**
     * Create an instance of {@link UpdateCustomerIVStatusRequest }
     * 
     */
    public UpdateCustomerIVStatusRequest createUpdateCustomerIVStatusRequest() {
        return new UpdateCustomerIVStatusRequest();
    }

    /**
     * Create an instance of {@link GetAuthenticationQuestionsResponse }
     * 
     */
    public GetAuthenticationQuestionsResponse createGetAuthenticationQuestionsResponse() {
        return new GetAuthenticationQuestionsResponse();
    }

    /**
     * Create an instance of {@link BaseResponseType }
     * 
     */
    public BaseResponseType createBaseResponseType() {
        return new BaseResponseType();
    }

    /**
     * Create an instance of {@link GetAuthenticationQuestionsSuccess }
     * 
     */
    public GetAuthenticationQuestionsSuccess createGetAuthenticationQuestionsSuccess() {
        return new GetAuthenticationQuestionsSuccess();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link UpdateCustomerIVStatusSuccess }
     * 
     */
    public UpdateCustomerIVStatusSuccess createUpdateCustomerIVStatusSuccess() {
        return new UpdateCustomerIVStatusSuccess();
    }

    /**
     * Create an instance of {@link FulfillOfferSuccess }
     * 
     */
    public FulfillOfferSuccess createFulfillOfferSuccess() {
        return new FulfillOfferSuccess();
    }

    /**
     * Create an instance of {@link FulfillOfferRequest.CustomerInfo }
     * 
     */
    public FulfillOfferRequest.CustomerInfo createFulfillOfferRequestCustomerInfo() {
        return new FulfillOfferRequest.CustomerInfo();
    }

    /**
     * Create an instance of {@link UpdateCustomerIVStatusResponse }
     * 
     */
    public UpdateCustomerIVStatusResponse createUpdateCustomerIVStatusResponse() {
        return new UpdateCustomerIVStatusResponse();
    }

    /**
     * Create an instance of {@link FulfillOfferResponse }
     * 
     */
    public FulfillOfferResponse createFulfillOfferResponse() {
        return new FulfillOfferResponse();
    }

    /**
     * Create an instance of {@link GetCustomerAssetsRequest }
     * 
     */
    public GetCustomerAssetsRequest createGetCustomerAssetsRequest() {
        return new GetCustomerAssetsRequest();
    }

    /**
     * Create an instance of {@link GetCustomerAssetsResponse }
     * 
     */
    public GetCustomerAssetsResponse createGetCustomerAssetsResponse() {
        return new GetCustomerAssetsResponse();
    }

    /**
     * Create an instance of {@link GetCustomerAssetsSuccess }
     * 
     */
    public GetCustomerAssetsSuccess createGetCustomerAssetsSuccess() {
        return new GetCustomerAssetsSuccess();
    }

    /**
     * Create an instance of {@link AssetDataType }
     * 
     */
    public AssetDataType createAssetDataType() {
        return new AssetDataType();
    }

    /**
     * Create an instance of {@link VerifyAuthenticationAnswersResponse }
     * 
     */
    public VerifyAuthenticationAnswersResponse createVerifyAuthenticationAnswersResponse() {
        return new VerifyAuthenticationAnswersResponse();
    }

    /**
     * Create an instance of {@link VerifyAuthenticationAnswersRequest }
     * 
     */
    public VerifyAuthenticationAnswersRequest createVerifyAuthenticationAnswersRequest() {
        return new VerifyAuthenticationAnswersRequest();
    }

    /**
     * Create an instance of {@link IVAnswer }
     * 
     */
    public IVAnswer createIVAnswer() {
        return new IVAnswer();
    }

    /**
     * Create an instance of {@link GetAuthenticationQuestionsRequest }
     * 
     */
    public GetAuthenticationQuestionsRequest createGetAuthenticationQuestionsRequest() {
        return new GetAuthenticationQuestionsRequest();
    }

    /**
     * Create an instance of {@link CustomerInfoType }
     * 
     */
    public CustomerInfoType createCustomerInfoType() {
        return new CustomerInfoType();
    }

    /**
     * Create an instance of {@link IVQuestion.AnswerChoice.Range }
     * 
     */
    public IVQuestion.AnswerChoice.Range createIVQuestionAnswerChoiceRange() {
        return new IVQuestion.AnswerChoice.Range();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FailureResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/transunion/gccs/core/services/v1", name = "Failure")
    public JAXBElement<FailureResponseType> createFailure(FailureResponseType value) {
        return new JAXBElement<FailureResponseType>(_Failure_QNAME, FailureResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrueLinkCreditReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com/transunion/gccs/core/services/v1", name = "TrueLinkCreditReport", scope = AssetDataType.class)
    public JAXBElement<TrueLinkCreditReportType> createAssetDataTypeTrueLinkCreditReport(TrueLinkCreditReportType value) {
        return new JAXBElement<TrueLinkCreditReportType>(_AssetDataTypeTrueLinkCreditReport_QNAME, TrueLinkCreditReportType.class, AssetDataType.class, value);
    }

}
