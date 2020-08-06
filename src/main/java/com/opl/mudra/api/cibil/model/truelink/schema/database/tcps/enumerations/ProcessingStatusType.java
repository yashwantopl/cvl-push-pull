
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcessingStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProcessingStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="Loading"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Processing"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProcessingStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ProcessingStatusType {

    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Loading")
    LOADING("Loading"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Processing")
    PROCESSING("Processing"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    ProcessingStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProcessingStatusType fromValue(String v) {
        for (ProcessingStatusType c: ProcessingStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
