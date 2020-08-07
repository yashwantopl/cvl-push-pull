
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MigrationEnrStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MigrationEnrStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AddCoreRequestError"/>
 *     &lt;enumeration value="AddCoreRequestFailure"/>
 *     &lt;enumeration value="AlertsMigrationFailure"/>
 *     &lt;enumeration value="AlertsMigrationPending"/>
 *     &lt;enumeration value="AlertsMigrationRequired"/>
 *     &lt;enumeration value="AlertsMigrationSuccess"/>
 *     &lt;enumeration value="DataValidationFailure"/>
 *     &lt;enumeration value="EnrollmentFailure"/>
 *     &lt;enumeration value="EnrollmentPending"/>
 *     &lt;enumeration value="MigrationSuccess"/>
 *     &lt;enumeration value="New"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MigrationEnrStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MigrationEnrStatusType {

    @XmlEnumValue("AddCoreRequestError")
    ADD_CORE_REQUEST_ERROR("AddCoreRequestError"),
    @XmlEnumValue("AddCoreRequestFailure")
    ADD_CORE_REQUEST_FAILURE("AddCoreRequestFailure"),
    @XmlEnumValue("AlertsMigrationFailure")
    ALERTS_MIGRATION_FAILURE("AlertsMigrationFailure"),
    @XmlEnumValue("AlertsMigrationPending")
    ALERTS_MIGRATION_PENDING("AlertsMigrationPending"),
    @XmlEnumValue("AlertsMigrationRequired")
    ALERTS_MIGRATION_REQUIRED("AlertsMigrationRequired"),
    @XmlEnumValue("AlertsMigrationSuccess")
    ALERTS_MIGRATION_SUCCESS("AlertsMigrationSuccess"),
    @XmlEnumValue("DataValidationFailure")
    DATA_VALIDATION_FAILURE("DataValidationFailure"),
    @XmlEnumValue("EnrollmentFailure")
    ENROLLMENT_FAILURE("EnrollmentFailure"),
    @XmlEnumValue("EnrollmentPending")
    ENROLLMENT_PENDING("EnrollmentPending"),
    @XmlEnumValue("MigrationSuccess")
    MIGRATION_SUCCESS("MigrationSuccess"),
    @XmlEnumValue("New")
    NEW("New");
    private final String value;

    MigrationEnrStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MigrationEnrStatusType fromValue(String v) {
        for (MigrationEnrStatusType c: MigrationEnrStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
