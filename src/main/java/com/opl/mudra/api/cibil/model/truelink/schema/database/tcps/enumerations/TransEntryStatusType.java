
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransEntryStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransEntryStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="LateFailureNotification"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Rejected"/>
 *     &lt;enumeration value="Successful"/>
 *     &lt;enumeration value="Transit"/>
 *     &lt;enumeration value="VendorDown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransEntryStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum TransEntryStatusType {

    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("LateFailureNotification")
    LATE_FAILURE_NOTIFICATION("LateFailureNotification"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Rejected")
    REJECTED("Rejected"),
    @XmlEnumValue("Successful")
    SUCCESSFUL("Successful"),
    @XmlEnumValue("Transit")
    TRANSIT("Transit"),
    @XmlEnumValue("VendorDown")
    VENDOR_DOWN("VendorDown");
    private final String value;

    TransEntryStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransEntryStatusType fromValue(String v) {
        for (TransEntryStatusType c: TransEntryStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
