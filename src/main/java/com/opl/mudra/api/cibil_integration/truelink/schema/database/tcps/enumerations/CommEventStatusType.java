
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommEventStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommEventStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="InProgress"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Succeeded"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommEventStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CommEventStatusType {

    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("InProgress")
    IN_PROGRESS("InProgress"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Succeeded")
    SUCCEEDED("Succeeded");
    private final String value;

    CommEventStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommEventStatusType fromValue(String v) {
        for (CommEventStatusType c: CommEventStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
