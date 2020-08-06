
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.pii.v1;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.truelink.ds.sch.pii.v1 package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.truelink.ds.sch.pii.v1
     * 
     */
    public ObjectFactory() {// Do nothing because of X and Y.
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link FullNameType }
     * 
     */
    public FullNameType createFullNameType() {
        return new FullNameType();
    }

    /**
     * Create an instance of {@link IdentifierType }
     * 
     */
    public IdentifierType createIdentifierType() {
        return new IdentifierType();
    }

    /**
     * Create an instance of {@link PhoneNumberType }
     * 
     */
    public PhoneNumberType createPhoneNumberType() {
        return new PhoneNumberType();
    }

    /**
     * Create an instance of {@link SocialNumberType }
     * 
     */
    public SocialNumberType createSocialNumberType() {
        return new SocialNumberType();
    }

    /**
     * Create an instance of {@link CustomerInformationType }
     * 
     */
    public CustomerInformationType createCustomerInformationType() {
        return new CustomerInformationType();
    }

}
