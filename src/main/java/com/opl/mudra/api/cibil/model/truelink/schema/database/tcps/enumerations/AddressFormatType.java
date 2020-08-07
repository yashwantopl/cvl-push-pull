
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressFormatType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AddressFormatType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Parsed"/>
 *     &lt;enumeration value="Unparsed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AddressFormatType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AddressFormatType {

    @XmlEnumValue("Parsed")
    PARSED("Parsed"),
    @XmlEnumValue("Unparsed")
    UNPARSED("Unparsed");
    private final String value;

    AddressFormatType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AddressFormatType fromValue(String v) {
        for (AddressFormatType c: AddressFormatType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
