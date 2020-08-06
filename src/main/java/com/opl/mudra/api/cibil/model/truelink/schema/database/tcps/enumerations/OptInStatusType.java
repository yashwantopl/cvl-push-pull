
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OptInStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OptInStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="In"/>
 *     &lt;enumeration value="Out"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OptInStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum OptInStatusType {

    @XmlEnumValue("In")
    IN("In"),
    @XmlEnumValue("Out")
    OUT("Out");
    private final String value;

    OptInStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OptInStatusType fromValue(String v) {
        for (OptInStatusType c: OptInStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
