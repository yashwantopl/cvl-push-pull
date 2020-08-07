
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MigrationUpdStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MigrationUpdStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AddCoreRequestError"/>
 *     &lt;enumeration value="AddCoreRequestFailure"/>
 *     &lt;enumeration value="CustomerUpdateFailure"/>
 *     &lt;enumeration value="CustomerUpdatePending"/>
 *     &lt;enumeration value="CustomerUpdateSuccess"/>
 *     &lt;enumeration value="DataValidationFailure"/>
 *     &lt;enumeration value="New"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MigrationUpdStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MigrationUpdStatusType {

    @XmlEnumValue("AddCoreRequestError")
    ADD_CORE_REQUEST_ERROR("AddCoreRequestError"),
    @XmlEnumValue("AddCoreRequestFailure")
    ADD_CORE_REQUEST_FAILURE("AddCoreRequestFailure"),
    @XmlEnumValue("CustomerUpdateFailure")
    CUSTOMER_UPDATE_FAILURE("CustomerUpdateFailure"),
    @XmlEnumValue("CustomerUpdatePending")
    CUSTOMER_UPDATE_PENDING("CustomerUpdatePending"),
    @XmlEnumValue("CustomerUpdateSuccess")
    CUSTOMER_UPDATE_SUCCESS("CustomerUpdateSuccess"),
    @XmlEnumValue("DataValidationFailure")
    DATA_VALIDATION_FAILURE("DataValidationFailure"),
    @XmlEnumValue("New")
    NEW("New");
    private final String value;

    MigrationUpdStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MigrationUpdStatusType fromValue(String v) {
        for (MigrationUpdStatusType c: MigrationUpdStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
