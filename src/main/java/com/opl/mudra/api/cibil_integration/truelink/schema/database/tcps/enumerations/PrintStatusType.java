
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrintStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrintStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandon"/>
 *     &lt;enumeration value="Fail"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Retry"/>
 *     &lt;enumeration value="RetryPending"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrintStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PrintStatusType {

    @XmlEnumValue("Abandon")
    ABANDON("Abandon"),
    @XmlEnumValue("Fail")
    FAIL("Fail"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Retry")
    RETRY("Retry"),
    @XmlEnumValue("RetryPending")
    RETRY_PENDING("RetryPending"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    PrintStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrintStatusType fromValue(String v) {
        for (PrintStatusType c: PrintStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
