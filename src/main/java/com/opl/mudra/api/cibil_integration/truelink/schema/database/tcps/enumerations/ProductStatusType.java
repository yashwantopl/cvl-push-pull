
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProductStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Current"/>
 *     &lt;enumeration value="AssetRenewal"/>
 *     &lt;enumeration value="AssetUnavailable"/>
 *     &lt;enumeration value="ReorderProduct"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProductStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ProductStatusType {

    @XmlEnumValue("Current")
    CURRENT("Current"),
    @XmlEnumValue("AssetRenewal")
    ASSET_RENEWAL("AssetRenewal"),
    @XmlEnumValue("AssetUnavailable")
    ASSET_UNAVAILABLE("AssetUnavailable"),
    @XmlEnumValue("ReorderProduct")
    REORDER_PRODUCT("ReorderProduct");
    private final String value;

    ProductStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProductStatusType fromValue(String v) {
        for (ProductStatusType c: ProductStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
