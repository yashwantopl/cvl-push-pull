
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EncryptionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EncryptionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AES256ConstantIV"/>
 *     &lt;enumeration value="Apr2004"/>
 *     &lt;enumeration value="BlkList1"/>
 *     &lt;enumeration value="CNCR1"/>
 *     &lt;enumeration value="GlobalAES256HK"/>
 *     &lt;enumeration value="GlobalAES256HKConstantIV"/>
 *     &lt;enumeration value="GlobalAES256INDIA"/>
 *     &lt;enumeration value="GlobalAES256INDIAConstantIV"/>
 *     &lt;enumeration value="Mar2009"/>
 *     &lt;enumeration value="May2005"/>
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="TPS1"/>
 *     &lt;enumeration value="TSE1"/>
 *     &lt;enumeration value="TUMS1"/>
 *     &lt;enumeration value="WestAES"/>
 *     &lt;enumeration value="WestDES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EncryptionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EncryptionType {

    @XmlEnumValue("AES256ConstantIV")
    AES_256_CONSTANT_IV("AES256ConstantIV"),
    @XmlEnumValue("Apr2004")
    APR_2004("Apr2004"),
    @XmlEnumValue("BlkList1")
    BLK_LIST_1("BlkList1"),
    @XmlEnumValue("CNCR1")
    CNCR_1("CNCR1"),
    @XmlEnumValue("GlobalAES256HK")
    GLOBAL_AES_256_HK("GlobalAES256HK"),
    @XmlEnumValue("GlobalAES256HKConstantIV")
    GLOBAL_AES_256_HK_CONSTANT_IV("GlobalAES256HKConstantIV"),
    @XmlEnumValue("GlobalAES256INDIA")
    GLOBAL_AES_256_INDIA("GlobalAES256INDIA"),
    @XmlEnumValue("GlobalAES256INDIAConstantIV")
    GLOBAL_AES_256_INDIA_CONSTANT_IV("GlobalAES256INDIAConstantIV"),
    @XmlEnumValue("Mar2009")
    MAR_2009("Mar2009"),
    @XmlEnumValue("May2005")
    MAY_2005("May2005"),
    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("TPS1")
    TPS_1("TPS1"),
    @XmlEnumValue("TSE1")
    TSE_1("TSE1"),
    @XmlEnumValue("TUMS1")
    TUMS_1("TUMS1"),
    @XmlEnumValue("WestAES")
    WEST_AES("WestAES"),
    @XmlEnumValue("WestDES")
    WEST_DES("WestDES");
    private final String value;

    EncryptionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EncryptionType fromValue(String v) {
        for (EncryptionType c: EncryptionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
