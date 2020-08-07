
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SiteType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ActivationCode"/>
 *     &lt;enumeration value="Affinion"/>
 *     &lt;enumeration value="CPP"/>
 *     &lt;enumeration value="CPPCharterOne"/>
 *     &lt;enumeration value="CPPCitizensBank"/>
 *     &lt;enumeration value="ChaseIP"/>
 *     &lt;enumeration value="Credentity"/>
 *     &lt;enumeration value="CreditKarma"/>
 *     &lt;enumeration value="EAGeico"/>
 *     &lt;enumeration value="EuropAssist"/>
 *     &lt;enumeration value="IDWatchdog"/>
 *     &lt;enumeration value="IDWatchdog_Site10"/>
 *     &lt;enumeration value="IDWatchdog_Site11"/>
 *     &lt;enumeration value="IDWatchdog_Site12"/>
 *     &lt;enumeration value="IDWatchdog_Site13"/>
 *     &lt;enumeration value="IDWatchdog_Site2"/>
 *     &lt;enumeration value="IDWatchdog_Site3"/>
 *     &lt;enumeration value="IDWatchdog_Site4"/>
 *     &lt;enumeration value="IDWatchdog_Site5"/>
 *     &lt;enumeration value="IDWatchdog_Site6"/>
 *     &lt;enumeration value="IDWatchdog_Site7"/>
 *     &lt;enumeration value="IDWatchdog_Site8"/>
 *     &lt;enumeration value="IDWatchdog_Site9"/>
 *     &lt;enumeration value="LifeLock"/>
 *     &lt;enumeration value="OneTech"/>
 *     &lt;enumeration value="ProjectStar"/>
 *     &lt;enumeration value="TCMobile"/>
 *     &lt;enumeration value="TU1"/>
 *     &lt;enumeration value="TU2"/>
 *     &lt;enumeration value="Fingerhut"/>
 *     &lt;enumeration value="TUAuto_Site1"/>
 *     &lt;enumeration value="TUAuto_Site2"/>
 *     &lt;enumeration value="TUAuto_Site3"/>
 *     &lt;enumeration value="TUCI1"/>
 *     &lt;enumeration value="promoca"/>
 *     &lt;enumeration value="TransUnionBreachServices"/>
 *     &lt;enumeration value="TransUnionCross-Sell"/>
 *     &lt;enumeration value="Vertrue24Protect"/>
 *     &lt;enumeration value="Vertrue24ProtectPlus"/>
 *     &lt;enumeration value="VertrueAtHomeRewards"/>
 *     &lt;enumeration value="VertrueAtHomeRewardsPlus"/>
 *     &lt;enumeration value="VertrueBusinessMax"/>
 *     &lt;enumeration value="VertrueCSComplete"/>
 *     &lt;enumeration value="VertrueCVP"/>
 *     &lt;enumeration value="VertrueClickMyCredit"/>
 *     &lt;enumeration value="VertrueCoverdellMyPrivacyMatters"/>
 *     &lt;enumeration value="VertrueCreditScoreExpress"/>
 *     &lt;enumeration value="VertrueFreeScore"/>
 *     &lt;enumeration value="VertrueIDHawk"/>
 *     &lt;enumeration value="VertrueNatCityIdentityProtect"/>
 *     &lt;enumeration value="VertruePrivacyMatters"/>
 *     &lt;enumeration value="VertruePrivacyMatters123"/>
 *     &lt;enumeration value="VertruePrivacyPlus"/>
 *     &lt;enumeration value="promo"/>
 *     &lt;enumeration value="CreditViewDemo"/>
 *     &lt;enumeration value="uwcu"/>
 *     &lt;enumeration value="iPreCheck"/>
 *     &lt;enumeration value="TDBank"/>
 *     &lt;enumeration value="ChaseBankNA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SiteType {

    @XmlEnumValue("ActivationCode")
    ACTIVATION_CODE("ActivationCode"),
    @XmlEnumValue("Affinion")
    AFFINION("Affinion"),
    CPP("CPP"),
    @XmlEnumValue("CPPCharterOne")
    CPP_CHARTER_ONE("CPPCharterOne"),
    @XmlEnumValue("CPPCitizensBank")
    CPP_CITIZENS_BANK("CPPCitizensBank"),
    @XmlEnumValue("ChaseIP")
    CHASE_IP("ChaseIP"),
    @XmlEnumValue("Credentity")
    CREDENTITY("Credentity"),
    @XmlEnumValue("CreditKarma")
    CREDIT_KARMA("CreditKarma"),
    @XmlEnumValue("EAGeico")
    EA_GEICO("EAGeico"),
    @XmlEnumValue("EuropAssist")
    EUROP_ASSIST("EuropAssist"),
    @XmlEnumValue("IDWatchdog")
    ID_WATCHDOG("IDWatchdog"),
    @XmlEnumValue("IDWatchdog_Site10")
    ID_WATCHDOG_SITE_10("IDWatchdog_Site10"),
    @XmlEnumValue("IDWatchdog_Site11")
    ID_WATCHDOG_SITE_11("IDWatchdog_Site11"),
    @XmlEnumValue("IDWatchdog_Site12")
    ID_WATCHDOG_SITE_12("IDWatchdog_Site12"),
    @XmlEnumValue("IDWatchdog_Site13")
    ID_WATCHDOG_SITE_13("IDWatchdog_Site13"),
    @XmlEnumValue("IDWatchdog_Site2")
    ID_WATCHDOG_SITE_2("IDWatchdog_Site2"),
    @XmlEnumValue("IDWatchdog_Site3")
    ID_WATCHDOG_SITE_3("IDWatchdog_Site3"),
    @XmlEnumValue("IDWatchdog_Site4")
    ID_WATCHDOG_SITE_4("IDWatchdog_Site4"),
    @XmlEnumValue("IDWatchdog_Site5")
    ID_WATCHDOG_SITE_5("IDWatchdog_Site5"),
    @XmlEnumValue("IDWatchdog_Site6")
    ID_WATCHDOG_SITE_6("IDWatchdog_Site6"),
    @XmlEnumValue("IDWatchdog_Site7")
    ID_WATCHDOG_SITE_7("IDWatchdog_Site7"),
    @XmlEnumValue("IDWatchdog_Site8")
    ID_WATCHDOG_SITE_8("IDWatchdog_Site8"),
    @XmlEnumValue("IDWatchdog_Site9")
    ID_WATCHDOG_SITE_9("IDWatchdog_Site9"),
    @XmlEnumValue("LifeLock")
    LIFE_LOCK("LifeLock"),
    @XmlEnumValue("OneTech")
    ONE_TECH("OneTech"),
    @XmlEnumValue("ProjectStar")
    PROJECT_STAR("ProjectStar"),
    @XmlEnumValue("TCMobile")
    TC_MOBILE("TCMobile"),
    @XmlEnumValue("TU1")
    TU_1("TU1"),
    @XmlEnumValue("TU2")
    TU_2("TU2"),
    @XmlEnumValue("Fingerhut")
    FINGERHUT("Fingerhut"),
    @XmlEnumValue("TUAuto_Site1")
    TU_AUTO_SITE_1("TUAuto_Site1"),
    @XmlEnumValue("TUAuto_Site2")
    TU_AUTO_SITE_2("TUAuto_Site2"),
    @XmlEnumValue("TUAuto_Site3")
    TU_AUTO_SITE_3("TUAuto_Site3"),
    @XmlEnumValue("TUCI1")
    TUCI_1("TUCI1"),
    @XmlEnumValue("promoca")
    PROMOCA("promoca"),
    @XmlEnumValue("TransUnionBreachServices")
    TRANS_UNION_BREACH_SERVICES("TransUnionBreachServices"),
    @XmlEnumValue("TransUnionCross-Sell")
    TRANS_UNION_CROSS_SELL("TransUnionCross-Sell"),
    @XmlEnumValue("Vertrue24Protect")
    VERTRUE_24_PROTECT("Vertrue24Protect"),
    @XmlEnumValue("Vertrue24ProtectPlus")
    VERTRUE_24_PROTECT_PLUS("Vertrue24ProtectPlus"),
    @XmlEnumValue("VertrueAtHomeRewards")
    VERTRUE_AT_HOME_REWARDS("VertrueAtHomeRewards"),
    @XmlEnumValue("VertrueAtHomeRewardsPlus")
    VERTRUE_AT_HOME_REWARDS_PLUS("VertrueAtHomeRewardsPlus"),
    @XmlEnumValue("VertrueBusinessMax")
    VERTRUE_BUSINESS_MAX("VertrueBusinessMax"),
    @XmlEnumValue("VertrueCSComplete")
    VERTRUE_CS_COMPLETE("VertrueCSComplete"),
    @XmlEnumValue("VertrueCVP")
    VERTRUE_CVP("VertrueCVP"),
    @XmlEnumValue("VertrueClickMyCredit")
    VERTRUE_CLICK_MY_CREDIT("VertrueClickMyCredit"),
    @XmlEnumValue("VertrueCoverdellMyPrivacyMatters")
    VERTRUE_COVERDELL_MY_PRIVACY_MATTERS("VertrueCoverdellMyPrivacyMatters"),
    @XmlEnumValue("VertrueCreditScoreExpress")
    VERTRUE_CREDIT_SCORE_EXPRESS("VertrueCreditScoreExpress"),
    @XmlEnumValue("VertrueFreeScore")
    VERTRUE_FREE_SCORE("VertrueFreeScore"),
    @XmlEnumValue("VertrueIDHawk")
    VERTRUE_ID_HAWK("VertrueIDHawk"),
    @XmlEnumValue("VertrueNatCityIdentityProtect")
    VERTRUE_NAT_CITY_IDENTITY_PROTECT("VertrueNatCityIdentityProtect"),
    @XmlEnumValue("VertruePrivacyMatters")
    VERTRUE_PRIVACY_MATTERS("VertruePrivacyMatters"),
    @XmlEnumValue("VertruePrivacyMatters123")
    VERTRUE_PRIVACY_MATTERS_123("VertruePrivacyMatters123"),
    @XmlEnumValue("VertruePrivacyPlus")
    VERTRUE_PRIVACY_PLUS("VertruePrivacyPlus"),
    @XmlEnumValue("promo")
    PROMO("promo"),
    @XmlEnumValue("CreditViewDemo")
    CREDIT_VIEW_DEMO("CreditViewDemo"),
    @XmlEnumValue("uwcu")
    UWCU("uwcu"),
    @XmlEnumValue("iPreCheck")
    I_PRE_CHECK("iPreCheck"),
    @XmlEnumValue("TDBank")
    TD_BANK("TDBank"),
    @XmlEnumValue("ChaseBankNA")
    CHASE_BANK_NA("ChaseBankNA");
    private final String value;

    SiteType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteType fromValue(String v) {
        for (SiteType c: SiteType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
