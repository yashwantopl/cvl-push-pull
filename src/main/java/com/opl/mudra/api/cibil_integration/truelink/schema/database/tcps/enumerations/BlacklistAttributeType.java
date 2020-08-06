
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlacklistAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BlacklistAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BankAccountNumber"/>
 *     &lt;enumeration value="CreditCardNumber"/>
 *     &lt;enumeration value="EmailAddress"/>
 *     &lt;enumeration value="SocialSecurityNumber"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlacklistAttributeType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BlacklistAttributeType {

    @XmlEnumValue("BankAccountNumber")
    BANK_ACCOUNT_NUMBER("BankAccountNumber"),
    @XmlEnumValue("CreditCardNumber")
    CREDIT_CARD_NUMBER("CreditCardNumber"),
    @XmlEnumValue("EmailAddress")
    EMAIL_ADDRESS("EmailAddress"),
    @XmlEnumValue("SocialSecurityNumber")
    SOCIAL_SECURITY_NUMBER("SocialSecurityNumber");
    private final String value;

    BlacklistAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BlacklistAttributeType fromValue(String v) {
        for (BlacklistAttributeType c: BlacklistAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
