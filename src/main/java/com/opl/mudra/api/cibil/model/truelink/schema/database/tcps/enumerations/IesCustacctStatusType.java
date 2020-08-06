
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IesCustacctStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IesCustacctStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Created"/>
 *     &lt;enumeration value="Found"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="Started"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IesCustacctStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum IesCustacctStatusType {

    @XmlEnumValue("Created")
    CREATED("Created"),
    @XmlEnumValue("Found")
    FOUND("Found"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Started")
    STARTED("Started");
    private final String value;

    IesCustacctStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IesCustacctStatusType fromValue(String v) {
        for (IesCustacctStatusType c: IesCustacctStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
