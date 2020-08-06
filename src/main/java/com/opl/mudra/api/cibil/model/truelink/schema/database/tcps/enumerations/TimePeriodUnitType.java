
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimePeriodUnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TimePeriodUnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AdHoc"/>
 *     &lt;enumeration value="Annual"/>
 *     &lt;enumeration value="Day"/>
 *     &lt;enumeration value="Decade"/>
 *     &lt;enumeration value="Infinite"/>
 *     &lt;enumeration value="Month"/>
 *     &lt;enumeration value="Once"/>
 *     &lt;enumeration value="Quarter"/>
 *     &lt;enumeration value="Week"/>
 *     &lt;enumeration value="Year"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TimePeriodUnitType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum TimePeriodUnitType {

    @XmlEnumValue("AdHoc")
    AD_HOC("AdHoc"),
    @XmlEnumValue("Annual")
    ANNUAL("Annual"),
    @XmlEnumValue("Day")
    DAY("Day"),
    @XmlEnumValue("Decade")
    DECADE("Decade"),
    @XmlEnumValue("Infinite")
    INFINITE("Infinite"),
    @XmlEnumValue("Month")
    MONTH("Month"),
    @XmlEnumValue("Once")
    ONCE("Once"),
    @XmlEnumValue("Quarter")
    QUARTER("Quarter"),
    @XmlEnumValue("Week")
    WEEK("Week"),
    @XmlEnumValue("Year")
    YEAR("Year");
    private final String value;

    TimePeriodUnitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimePeriodUnitType fromValue(String v) {
        for (TimePeriodUnitType c: TimePeriodUnitType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
