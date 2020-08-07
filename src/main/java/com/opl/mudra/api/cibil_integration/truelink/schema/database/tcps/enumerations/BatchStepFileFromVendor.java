
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchStepFileFromVendor.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BatchStepFileFromVendor">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GetFile"/>
 *     &lt;enumeration value="DecryptFile"/>
 *     &lt;enumeration value="DecompressFile"/>
 *     &lt;enumeration value="ProcessFileAndArchive"/>
 *     &lt;enumeration value="AllSteps"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BatchStepFileFromVendor", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BatchStepFileFromVendor {

    @XmlEnumValue("GetFile")
    GET_FILE("GetFile"),
    @XmlEnumValue("DecryptFile")
    DECRYPT_FILE("DecryptFile"),
    @XmlEnumValue("DecompressFile")
    DECOMPRESS_FILE("DecompressFile"),
    @XmlEnumValue("ProcessFileAndArchive")
    PROCESS_FILE_AND_ARCHIVE("ProcessFileAndArchive"),
    @XmlEnumValue("AllSteps")
    ALL_STEPS("AllSteps");
    private final String value;

    BatchStepFileFromVendor(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchStepFileFromVendor fromValue(String v) {
        for (BatchStepFileFromVendor c: BatchStepFileFromVendor.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
