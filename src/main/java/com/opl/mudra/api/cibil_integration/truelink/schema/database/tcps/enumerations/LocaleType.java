
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocaleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LocaleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="arSA"/>
 *     &lt;enumeration value="deDE"/>
 *     &lt;enumeration value="enCA"/>
 *     &lt;enumeration value="enIN"/>
 *     &lt;enumeration value="enUS"/>
 *     &lt;enumeration value="esUS"/>
 *     &lt;enumeration value="frCA"/>
 *     &lt;enumeration value="frFR"/>
 *     &lt;enumeration value="itIT"/>
 *     &lt;enumeration value="jaJP"/>
 *     &lt;enumeration value="koKR"/>
 *     &lt;enumeration value="ptBR"/>
 *     &lt;enumeration value="ruRU"/>
 *     &lt;enumeration value="viVN"/>
 *     &lt;enumeration value="zhCH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LocaleType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LocaleType {

    @XmlEnumValue("arSA")
    AR_SA("arSA"),
    @XmlEnumValue("deDE")
    DE_DE("deDE"),
    @XmlEnumValue("enCA")
    EN_CA("enCA"),
    @XmlEnumValue("enIN")
    EN_IN("enIN"),
    @XmlEnumValue("enUS")
    EN_US("enUS"),
    @XmlEnumValue("esUS")
    ES_US("esUS"),
    @XmlEnumValue("frCA")
    FR_CA("frCA"),
    @XmlEnumValue("frFR")
    FR_FR("frFR"),
    @XmlEnumValue("itIT")
    IT_IT("itIT"),
    @XmlEnumValue("jaJP")
    JA_JP("jaJP"),
    @XmlEnumValue("koKR")
    KO_KR("koKR"),
    @XmlEnumValue("ptBR")
    PT_BR("ptBR"),
    @XmlEnumValue("ruRU")
    RU_RU("ruRU"),
    @XmlEnumValue("viVN")
    VI_VN("viVN"),
    @XmlEnumValue("zhCH")
    ZH_CH("zhCH");
    private final String value;

    LocaleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocaleType fromValue(String v) {
        for (LocaleType c: LocaleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
