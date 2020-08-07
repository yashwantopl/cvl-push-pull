
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrintCommConfigType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrintCommConfigType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DantomIn"/>
 *     &lt;enumeration value="DantomOut"/>
 *     &lt;enumeration value="LasonIn"/>
 *     &lt;enumeration value="LasonOut"/>
 *     &lt;enumeration value="MetroGroupIn"/>
 *     &lt;enumeration value="MetroGroupOut"/>
 *     &lt;enumeration value="TUAuto_Site1In"/>
 *     &lt;enumeration value="TUAuto_Site1Out"/>
 *     &lt;enumeration value="TUAuto_Site2In"/>
 *     &lt;enumeration value="TUAuto_Site2Out"/>
 *     &lt;enumeration value="TUAuto_Site3In"/>
 *     &lt;enumeration value="TUAuto_Site3Out"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrintCommConfigType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PrintCommConfigType {

    @XmlEnumValue("DantomIn")
    DANTOM_IN("DantomIn"),
    @XmlEnumValue("DantomOut")
    DANTOM_OUT("DantomOut"),
    @XmlEnumValue("LasonIn")
    LASON_IN("LasonIn"),
    @XmlEnumValue("LasonOut")
    LASON_OUT("LasonOut"),
    @XmlEnumValue("MetroGroupIn")
    METRO_GROUP_IN("MetroGroupIn"),
    @XmlEnumValue("MetroGroupOut")
    METRO_GROUP_OUT("MetroGroupOut"),
    @XmlEnumValue("TUAuto_Site1In")
    TU_AUTO_SITE_1_IN("TUAuto_Site1In"),
    @XmlEnumValue("TUAuto_Site1Out")
    TU_AUTO_SITE_1_OUT("TUAuto_Site1Out"),
    @XmlEnumValue("TUAuto_Site2In")
    TU_AUTO_SITE_2_IN("TUAuto_Site2In"),
    @XmlEnumValue("TUAuto_Site2Out")
    TU_AUTO_SITE_2_OUT("TUAuto_Site2Out"),
    @XmlEnumValue("TUAuto_Site3In")
    TU_AUTO_SITE_3_IN("TUAuto_Site3In"),
    @XmlEnumValue("TUAuto_Site3Out")
    TU_AUTO_SITE_3_OUT("TUAuto_Site3Out");
    private final String value;

    PrintCommConfigType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrintCommConfigType fromValue(String v) {
        for (PrintCommConfigType c: PrintCommConfigType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
