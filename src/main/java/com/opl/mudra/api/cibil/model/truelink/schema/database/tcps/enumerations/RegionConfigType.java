
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegionConfigType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RegionConfigType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DEFAULT_LOCALE"/>
 *     &lt;enumeration value="REQ_FETCH_FID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RegionConfigType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum RegionConfigType {

    DEFAULT_LOCALE,
    REQ_FETCH_FID;

    public String value() {
        return name();
    }

    public static RegionConfigType fromValue(String v) {
        return valueOf(v);
    }

}
