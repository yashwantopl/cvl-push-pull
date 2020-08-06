
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChaseFulfillmentStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChaseFulfillmentStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SCHEDULED"/>
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="NA"/>
 *     &lt;enumeration value="NO_HIT"/>
 *     &lt;enumeration value="ADDRESS_MISMATCH"/>
 *     &lt;enumeration value="FRAUD"/>
 *     &lt;enumeration value="PRINTING_FAILED"/>
 *     &lt;enumeration value="WATCH_FAILED"/>
 *     &lt;enumeration value="NO_CUSTOMER_FOUND"/>
 *     &lt;enumeration value="NO_ORDER_FOUND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChaseFulfillmentStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ChaseFulfillmentStatusType {

    SCHEDULED,
    OK,
    NA,
    NO_HIT,
    ADDRESS_MISMATCH,
    FRAUD,
    PRINTING_FAILED,
    WATCH_FAILED,
    NO_CUSTOMER_FOUND,
    NO_ORDER_FOUND;

    public String value() {
        return name();
    }

    public static ChaseFulfillmentStatusType fromValue(String v) {
        return valueOf(v);
    }

}
