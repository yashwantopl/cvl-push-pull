
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankAcctType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BankAcctType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Checking"/>
 *     &lt;enumeration value="PartnerEncryptedChecking"/>
 *     &lt;enumeration value="PartnerEncryptedSavings"/>
 *     &lt;enumeration value="Savings"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BankAcctType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BankAcctType {

    @XmlEnumValue("Checking")
    CHECKING("Checking"),
    @XmlEnumValue("PartnerEncryptedChecking")
    PARTNER_ENCRYPTED_CHECKING("PartnerEncryptedChecking"),
    @XmlEnumValue("PartnerEncryptedSavings")
    PARTNER_ENCRYPTED_SAVINGS("PartnerEncryptedSavings"),
    @XmlEnumValue("Savings")
    SAVINGS("Savings");
    private final String value;

    BankAcctType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BankAcctType fromValue(String v) {
        for (BankAcctType c: BankAcctType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
