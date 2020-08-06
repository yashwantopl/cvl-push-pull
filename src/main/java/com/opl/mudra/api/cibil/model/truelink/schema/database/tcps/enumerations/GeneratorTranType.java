
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GeneratorTranType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GeneratorTranType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Cancel"/>
 *     &lt;enumeration value="ProductReplacement"/>
 *     &lt;enumeration value="Refund"/>
 *     &lt;enumeration value="ResendNotification"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GeneratorTranType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum GeneratorTranType {

    @XmlEnumValue("Cancel")
    CANCEL("Cancel"),
    @XmlEnumValue("ProductReplacement")
    PRODUCT_REPLACEMENT("ProductReplacement"),
    @XmlEnumValue("Refund")
    REFUND("Refund"),
    @XmlEnumValue("ResendNotification")
    RESEND_NOTIFICATION("ResendNotification");
    private final String value;

    GeneratorTranType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GeneratorTranType fromValue(String v) {
        for (GeneratorTranType c: GeneratorTranType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
