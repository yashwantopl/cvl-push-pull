
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommEventType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommEventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IssFailure"/>
 *     &lt;enumeration value="Newsletter"/>
 *     &lt;enumeration value="PasswordReset"/>
 *     &lt;enumeration value="PaymentProblem"/>
 *     &lt;enumeration value="ProductExpirationWarning"/>
 *     &lt;enumeration value="Receipt"/>
 *     &lt;enumeration value="Security"/>
 *     &lt;enumeration value="Support"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommEventType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CommEventType {

    @XmlEnumValue("IssFailure")
    ISS_FAILURE("IssFailure"),
    @XmlEnumValue("Newsletter")
    NEWSLETTER("Newsletter"),
    @XmlEnumValue("PasswordReset")
    PASSWORD_RESET("PasswordReset"),
    @XmlEnumValue("PaymentProblem")
    PAYMENT_PROBLEM("PaymentProblem"),
    @XmlEnumValue("ProductExpirationWarning")
    PRODUCT_EXPIRATION_WARNING("ProductExpirationWarning"),
    @XmlEnumValue("Receipt")
    RECEIPT("Receipt"),
    @XmlEnumValue("Security")
    SECURITY("Security"),
    @XmlEnumValue("Support")
    SUPPORT("Support");
    private final String value;

    CommEventType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommEventType fromValue(String v) {
        for (CommEventType c: CommEventType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
