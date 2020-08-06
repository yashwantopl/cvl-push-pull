
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllianceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AllianceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ActivationCode"/>
 *     &lt;enumeration value="Affinion"/>
 *     &lt;enumeration value="CPP"/>
 *     &lt;enumeration value="Chase"/>
 *     &lt;enumeration value="Credentity"/>
 *     &lt;enumeration value="CreditKarma"/>
 *     &lt;enumeration value="CreditView"/>
 *     &lt;enumeration value="EuropAssistance"/>
 *     &lt;enumeration value="IDWatchdog"/>
 *     &lt;enumeration value="LifeLock"/>
 *     &lt;enumeration value="OneTech"/>
 *     &lt;enumeration value="ProjectStar"/>
 *     &lt;enumeration value="TCMobile"/>
 *     &lt;enumeration value="TUAutomation"/>
 *     &lt;enumeration value="TransUnion"/>
 *     &lt;enumeration value="USBank"/>
 *     &lt;enumeration value="Vertrue"/>
 *     &lt;enumeration value="Bluestem"/>
 *     &lt;enumeration value="TDBank"/>
 *     &lt;enumeration value="ChaseBankNA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AllianceType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AllianceType {

    @XmlEnumValue("ActivationCode")
    ACTIVATION_CODE("ActivationCode"),
    @XmlEnumValue("Affinion")
    AFFINION("Affinion"),
    CPP("CPP"),
    @XmlEnumValue("Chase")
    CHASE("Chase"),
    @XmlEnumValue("Credentity")
    CREDENTITY("Credentity"),
    @XmlEnumValue("CreditKarma")
    CREDIT_KARMA("CreditKarma"),
    @XmlEnumValue("CreditView")
    CREDIT_VIEW("CreditView"),
    @XmlEnumValue("EuropAssistance")
    EUROP_ASSISTANCE("EuropAssistance"),
    @XmlEnumValue("IDWatchdog")
    ID_WATCHDOG("IDWatchdog"),
    @XmlEnumValue("LifeLock")
    LIFE_LOCK("LifeLock"),
    @XmlEnumValue("OneTech")
    ONE_TECH("OneTech"),
    @XmlEnumValue("ProjectStar")
    PROJECT_STAR("ProjectStar"),
    @XmlEnumValue("TCMobile")
    TC_MOBILE("TCMobile"),
    @XmlEnumValue("TUAutomation")
    TU_AUTOMATION("TUAutomation"),
    @XmlEnumValue("TransUnion")
    TRANS_UNION("TransUnion"),
    @XmlEnumValue("USBank")
    US_BANK("USBank"),
    @XmlEnumValue("Vertrue")
    VERTRUE("Vertrue"),
    @XmlEnumValue("Bluestem")
    BLUESTEM("Bluestem"),
    @XmlEnumValue("TDBank")
    TD_BANK("TDBank"),
    @XmlEnumValue("ChaseBankNA")
    CHASE_BANK_NA("ChaseBankNA");
    private final String value;

    AllianceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AllianceType fromValue(String v) {
        for (AllianceType c: AllianceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
