
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReviewActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReviewActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CancelEnrollment"/>
 *     &lt;enumeration value="ContinueReview"/>
 *     &lt;enumeration value="RetireReview"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReviewActionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ReviewActionType {

    @XmlEnumValue("CancelEnrollment")
    CANCEL_ENROLLMENT("CancelEnrollment"),
    @XmlEnumValue("ContinueReview")
    CONTINUE_REVIEW("ContinueReview"),
    @XmlEnumValue("RetireReview")
    RETIRE_REVIEW("RetireReview");
    private final String value;

    ReviewActionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReviewActionType fromValue(String v) {
        for (ReviewActionType c: ReviewActionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
