
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrintProcessorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrintProcessorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Dantom"/>
 *     &lt;enumeration value="Lason"/>
 *     &lt;enumeration value="MetroGroup"/>
 *     &lt;enumeration value="TUAuto_DataServices"/>
 *     &lt;enumeration value="TUAuto_Springton"/>
 *     &lt;enumeration value="TUAuto_WhiteChapel"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrintProcessorType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PrintProcessorType {

    @XmlEnumValue("Dantom")
    DANTOM("Dantom"),
    @XmlEnumValue("Lason")
    LASON("Lason"),
    @XmlEnumValue("MetroGroup")
    METRO_GROUP("MetroGroup"),
    @XmlEnumValue("TUAuto_DataServices")
    TU_AUTO_DATA_SERVICES("TUAuto_DataServices"),
    @XmlEnumValue("TUAuto_Springton")
    TU_AUTO_SPRINGTON("TUAuto_Springton"),
    @XmlEnumValue("TUAuto_WhiteChapel")
    TU_AUTO_WHITE_CHAPEL("TUAuto_WhiteChapel");
    private final String value;

    PrintProcessorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrintProcessorType fromValue(String v) {
        for (PrintProcessorType c: PrintProcessorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
