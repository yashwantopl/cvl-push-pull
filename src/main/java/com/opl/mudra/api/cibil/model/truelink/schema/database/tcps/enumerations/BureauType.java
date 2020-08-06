
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BureauType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BureauType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EQF"/>
 *     &lt;enumeration value="EXP"/>
 *     &lt;enumeration value="TU"/>
 *     &lt;enumeration value="CSID"/>
 *     &lt;enumeration value="TU_TRG"/>
 *     &lt;enumeration value="TCA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BureauType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BureauType {

    EQF,
    EXP,
    TU,
    CSID,
    TU_TRG,
    TCA;

    public String value() {
        return name();
    }

    public static BureauType fromValue(String v) {
        return valueOf(v);
    }

}
