
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CountryCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CountryCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="us"/>
 *     &lt;enumeration value="ca"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CountryCodeType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CountryCodeType {

    @XmlEnumValue("us")
    US("us"),
    @XmlEnumValue("ca")
    CA("ca");
    private final String value;

    CountryCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CountryCodeType fromValue(String v) {
        for (CountryCodeType c: CountryCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
