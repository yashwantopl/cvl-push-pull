
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BTXTargetAppEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BTXTargetAppEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CHASE_DATA_EXCHANGE"/>
 *     &lt;enumeration value="AG_DATA_EXCHANGE"/>
 *     &lt;enumeration value="PRINT_AND_MAIL"/>
 *     &lt;enumeration value="PAYMENT"/>
 *     &lt;enumeration value="EMAIL"/>
 *     &lt;enumeration value="CORE_MSP"/>
 *     &lt;enumeration value="OT_MSP"/>
 *     &lt;enumeration value="AVC_MSP"/>
 *     &lt;enumeration value="PSI_MSP"/>
 *     &lt;enumeration value="AG_MSP"/>
 *     &lt;enumeration value="TCMobile_MSP"/>
 *     &lt;enumeration value="MIGRATION"/>
 *     &lt;enumeration value="TxGenerator"/>
 *     &lt;enumeration value="CORE_CREDITVIEW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BTXTargetAppEnum", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BTXTargetAppEnum {

    CHASE_DATA_EXCHANGE("CHASE_DATA_EXCHANGE"),
    AG_DATA_EXCHANGE("AG_DATA_EXCHANGE"),
    PRINT_AND_MAIL("PRINT_AND_MAIL"),
    PAYMENT("PAYMENT"),
    EMAIL("EMAIL"),
    CORE_MSP("CORE_MSP"),
    OT_MSP("OT_MSP"),
    AVC_MSP("AVC_MSP"),
    PSI_MSP("PSI_MSP"),
    AG_MSP("AG_MSP"),
    @XmlEnumValue("TCMobile_MSP")
    TC_MOBILE_MSP("TCMobile_MSP"),
    MIGRATION("MIGRATION"),
    @XmlEnumValue("TxGenerator")
    TX_GENERATOR("TxGenerator"),
    CORE_CREDITVIEW("CORE_CREDITVIEW");
    private final String value;

    BTXTargetAppEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BTXTargetAppEnum fromValue(String v) {
        for (BTXTargetAppEnum c: BTXTargetAppEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
