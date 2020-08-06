
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeIndicatorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChangeIndicatorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADDED"/>
 *     &lt;enumeration value="COMPARED"/>
 *     &lt;enumeration value="REMOVED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChangeIndicatorType")
@XmlEnum
public enum ChangeIndicatorType {

    ADDED,
    COMPARED,
    REMOVED;

    public String value() {
        return name();
    }

    public static ChangeIndicatorType fromValue(String v) {
        return valueOf(v);
    }

}
