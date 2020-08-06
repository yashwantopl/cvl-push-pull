
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AuthorizationFailure"/>
 *     &lt;enumeration value="OfflineToOnlineFailure"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ActionType {

    @XmlEnumValue("AuthorizationFailure")
    AUTHORIZATION_FAILURE("AuthorizationFailure"),
    @XmlEnumValue("OfflineToOnlineFailure")
    OFFLINE_TO_ONLINE_FAILURE("OfflineToOnlineFailure");
    private final String value;

    ActionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActionType fromValue(String v) {
        for (ActionType c: ActionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
