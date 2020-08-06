
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrintEntryStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrintEntryStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Successful"/>
 *     &lt;enumeration value="Transit"/>
 *     &lt;enumeration value="VendorDown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrintEntryStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PrintEntryStatusType {

    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Successful")
    SUCCESSFUL("Successful"),
    @XmlEnumValue("Transit")
    TRANSIT("Transit"),
    @XmlEnumValue("VendorDown")
    VENDOR_DOWN("VendorDown");
    private final String value;

    PrintEntryStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrintEntryStatusType fromValue(String v) {
        for (PrintEntryStatusType c: PrintEntryStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
