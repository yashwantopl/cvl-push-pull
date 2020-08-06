
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChaseVendorDispositionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChaseVendorDispositionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RVENR"/>
 *     &lt;enumeration value="RVINEL"/>
 *     &lt;enumeration value="RVALEN"/>
 *     &lt;enumeration value="RCANCL"/>
 *     &lt;enumeration value="RVCAN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChaseVendorDispositionCodeType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ChaseVendorDispositionCodeType {

    RVENR,
    RVINEL,
    RVALEN,
    RCANCL,
    RVCAN;

    public String value() {
        return name();
    }

    public static ChaseVendorDispositionCodeType fromValue(String v) {
        return valueOf(v);
    }

}
