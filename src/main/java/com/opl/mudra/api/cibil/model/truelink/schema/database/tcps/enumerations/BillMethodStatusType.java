
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillMethodStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillMethodStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="NotApplicable"/>
 *     &lt;enumeration value="ResponsePending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BillMethodStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BillMethodStatusType {

    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("NotApplicable")
    NOT_APPLICABLE("NotApplicable"),
    @XmlEnumValue("ResponsePending")
    RESPONSE_PENDING("ResponsePending");
    private final String value;

    BillMethodStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BillMethodStatusType fromValue(String v) {
        for (BillMethodStatusType c: BillMethodStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
