
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchStepFileToVendor.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BatchStepFileToVendor">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EnqueueSuccessRecords"/>
 *     &lt;enumeration value="GenerateFile"/>
 *     &lt;enumeration value="MergeFile"/>
 *     &lt;enumeration value="CompressFile"/>
 *     &lt;enumeration value="EncryptFile"/>
 *     &lt;enumeration value="ArchiveAndSendFile"/>
 *     &lt;enumeration value="AllSteps"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BatchStepFileToVendor", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BatchStepFileToVendor {

    @XmlEnumValue("EnqueueSuccessRecords")
    ENQUEUE_SUCCESS_RECORDS("EnqueueSuccessRecords"),
    @XmlEnumValue("GenerateFile")
    GENERATE_FILE("GenerateFile"),
    @XmlEnumValue("MergeFile")
    MERGE_FILE("MergeFile"),
    @XmlEnumValue("CompressFile")
    COMPRESS_FILE("CompressFile"),
    @XmlEnumValue("EncryptFile")
    ENCRYPT_FILE("EncryptFile"),
    @XmlEnumValue("ArchiveAndSendFile")
    ARCHIVE_AND_SEND_FILE("ArchiveAndSendFile"),
    @XmlEnumValue("AllSteps")
    ALL_STEPS("AllSteps");
    private final String value;

    BatchStepFileToVendor(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchStepFileToVendor fromValue(String v) {
        for (BatchStepFileToVendor c: BatchStepFileToVendor.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
