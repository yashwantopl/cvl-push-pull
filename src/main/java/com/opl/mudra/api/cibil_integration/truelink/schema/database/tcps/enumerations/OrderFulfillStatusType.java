
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderFulfillStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderFulfillStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="AssetAvailable"/>
 *     &lt;enumeration value="AssetUnavailable"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="FulfillmentFailed"/>
 *     &lt;enumeration value="FulfillmentPending"/>
 *     &lt;enumeration value="TUCI1_Group"/>
 *     &lt;enumeration value="promoca_Group"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="NotInitiated"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderFulfillStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum OrderFulfillStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("AssetAvailable")
    ASSET_AVAILABLE("AssetAvailable"),
    @XmlEnumValue("AssetUnavailable")
    ASSET_UNAVAILABLE("AssetUnavailable"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("FulfillmentFailed")
    FULFILLMENT_FAILED("FulfillmentFailed"),
    @XmlEnumValue("FulfillmentPending")
    FULFILLMENT_PENDING("FulfillmentPending"),
    @XmlEnumValue("TUCI1_Group")
    TUCI_1_GROUP("TUCI1_Group"),
    @XmlEnumValue("promoca_Group")
    PROMOCA_GROUP("promoca_Group"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("NotInitiated")
    NOT_INITIATED("NotInitiated");
    private final String value;

    OrderFulfillStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrderFulfillStatusType fromValue(String v) {
        for (OrderFulfillStatusType c: OrderFulfillStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
