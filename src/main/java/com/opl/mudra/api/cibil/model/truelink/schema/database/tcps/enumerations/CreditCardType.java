
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreditCardType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CreditCardType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Amex"/>
 *     &lt;enumeration value="B1PartnerEncrypted"/>
 *     &lt;enumeration value="Discover"/>
 *     &lt;enumeration value="MasterCard"/>
 *     &lt;enumeration value="PartnerEncryptedCard"/>
 *     &lt;enumeration value="Visa"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CreditCardType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CreditCardType {

    @XmlEnumValue("Amex")
    AMEX("Amex"),
    @XmlEnumValue("B1PartnerEncrypted")
    B_1_PARTNER_ENCRYPTED("B1PartnerEncrypted"),
    @XmlEnumValue("Discover")
    DISCOVER("Discover"),
    @XmlEnumValue("MasterCard")
    MASTER_CARD("MasterCard"),
    @XmlEnumValue("PartnerEncryptedCard")
    PARTNER_ENCRYPTED_CARD("PartnerEncryptedCard"),
    @XmlEnumValue("Visa")
    VISA("Visa");
    private final String value;

    CreditCardType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CreditCardType fromValue(String v) {
        for (CreditCardType c: CreditCardType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
