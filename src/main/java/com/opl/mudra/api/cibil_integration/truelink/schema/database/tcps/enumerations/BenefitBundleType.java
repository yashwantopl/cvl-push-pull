
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BenefitBundleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BenefitBundleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AVCIDTheft"/>
 *     &lt;enumeration value="AVCNone"/>
 *     &lt;enumeration value="CKNone"/>
 *     &lt;enumeration value="ChaseBreachBenefit"/>
 *     &lt;enumeration value="IDTheftBenefit"/>
 *     &lt;enumeration value="NoBenefits"/>
 *     &lt;enumeration value="OTNone"/>
 *     &lt;enumeration value="StandardBenefit"/>
 *     &lt;enumeration value="StandardBenefitPlusID"/>
 *     &lt;enumeration value="TUBenefit"/>
 *     &lt;enumeration value="TUBreach"/>
 *     &lt;enumeration value="TUOCRNone"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BenefitBundleType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BenefitBundleType {

    @XmlEnumValue("AVCIDTheft")
    AVCID_THEFT("AVCIDTheft"),
    @XmlEnumValue("AVCNone")
    AVC_NONE("AVCNone"),
    @XmlEnumValue("CKNone")
    CK_NONE("CKNone"),
    @XmlEnumValue("ChaseBreachBenefit")
    CHASE_BREACH_BENEFIT("ChaseBreachBenefit"),
    @XmlEnumValue("IDTheftBenefit")
    ID_THEFT_BENEFIT("IDTheftBenefit"),
    @XmlEnumValue("NoBenefits")
    NO_BENEFITS("NoBenefits"),
    @XmlEnumValue("OTNone")
    OT_NONE("OTNone"),
    @XmlEnumValue("StandardBenefit")
    STANDARD_BENEFIT("StandardBenefit"),
    @XmlEnumValue("StandardBenefitPlusID")
    STANDARD_BENEFIT_PLUS_ID("StandardBenefitPlusID"),
    @XmlEnumValue("TUBenefit")
    TU_BENEFIT("TUBenefit"),
    @XmlEnumValue("TUBreach")
    TU_BREACH("TUBreach"),
    @XmlEnumValue("TUOCRNone")
    TUOCR_NONE("TUOCRNone");
    private final String value;

    BenefitBundleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BenefitBundleType fromValue(String v) {
        for (BenefitBundleType c: BenefitBundleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
