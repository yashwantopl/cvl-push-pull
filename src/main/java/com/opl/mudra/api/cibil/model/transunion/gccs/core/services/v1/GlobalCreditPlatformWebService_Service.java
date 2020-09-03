
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
///apps/service/servicce-cibil/GlobalCreditPlatformWebService_1.wsdl
@WebServiceClient(name = "GlobalCreditPlatformWebService", targetNamespace = "com/transunion/gccs/core/services/v1", wsdlLocation = "https://gccs-live.sd.demo.truelink.com/GCCS-ws/GlobalCreditPlatformWebService")
public class GlobalCreditPlatformWebService_Service
    extends Service
{

    private final static URL GLOBALCREDITPLATFORMWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException GLOBALCREDITPLATFORMWEBSERVICE_EXCEPTION;
    private final static QName GLOBALCREDITPLATFORMWEBSERVICE_QNAME = new QName("com/transunion/gccs/core/services/v1", "GlobalCreditPlatformWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://gccs-live.sd.demo.truelink.com/GCCS-ws/GlobalCreditPlatformWebService");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GLOBALCREDITPLATFORMWEBSERVICE_WSDL_LOCATION = url;
        GLOBALCREDITPLATFORMWEBSERVICE_EXCEPTION = e;
    }

    public GlobalCreditPlatformWebService_Service() {
        super(__getWsdlLocation(), GLOBALCREDITPLATFORMWEBSERVICE_QNAME);
    }

    public GlobalCreditPlatformWebService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), GLOBALCREDITPLATFORMWEBSERVICE_QNAME, features);
    }

    public GlobalCreditPlatformWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, GLOBALCREDITPLATFORMWEBSERVICE_QNAME);
    }

    public GlobalCreditPlatformWebService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GLOBALCREDITPLATFORMWEBSERVICE_QNAME, features);
    }

    public GlobalCreditPlatformWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GlobalCreditPlatformWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GlobalCreditPlatformWebService
     */
    @WebEndpoint(name = "GlobalCreditPlatformWebServicePort")
    public GlobalCreditPlatformWebService getGlobalCreditPlatformWebServicePort() {
        return super.getPort(new QName("com/transunion/gccs/core/services/v1", "GlobalCreditPlatformWebServicePort"), GlobalCreditPlatformWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GlobalCreditPlatformWebService
     */
    @WebEndpoint(name = "GlobalCreditPlatformWebServicePort")
    public GlobalCreditPlatformWebService getGlobalCreditPlatformWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("com/transunion/gccs/core/services/v1", "GlobalCreditPlatformWebServicePort"), GlobalCreditPlatformWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GLOBALCREDITPLATFORMWEBSERVICE_EXCEPTION!= null) {
            throw GLOBALCREDITPLATFORMWEBSERVICE_EXCEPTION;
        }
        return GLOBALCREDITPLATFORMWEBSERVICE_WSDL_LOCATION;
    }

}