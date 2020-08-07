
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerStatusTypeOld.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CustomerStatusTypeOld">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="CANCELED"/>
 *     &lt;enumeration value="FAILED"/>
 *     &lt;enumeration value="EXCEPTION_PENDING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CustomerStatusTypeOld", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CustomerStatusTypeOld {

    ACTIVE,
    CANCELED,
    FAILED,
    EXCEPTION_PENDING;

    public String value() {
        return name();
    }

    public static CustomerStatusTypeOld fromValue(String v) {
        return valueOf(v);
    }

}
