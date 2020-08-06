
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceItemType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvoiceItemType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Bundle"/>
 *     &lt;enumeration value="Discount"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvoiceItemType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum InvoiceItemType {

    @XmlEnumValue("Bundle")
    BUNDLE("Bundle"),
    @XmlEnumValue("Discount")
    DISCOUNT("Discount");
    private final String value;

    InvoiceItemType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvoiceItemType fromValue(String v) {
        for (InvoiceItemType c: InvoiceItemType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
