
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConcurrentProcessType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConcurrentProcessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartDataId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="EndDataId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="RecordCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ConcurrentGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConcurrentProcessType", propOrder = {
    "startDataId",
    "endDataId",
    "recordCount",
    "concurrentGroupId"
})
public class ConcurrentProcessType {

    @XmlElement(name = "StartDataId")
    protected long startDataId;
    @XmlElement(name = "EndDataId")
    protected long endDataId;
    @XmlElement(name = "RecordCount")
    protected long recordCount;
    @XmlElement(name = "ConcurrentGroupId")
    protected long concurrentGroupId;

    /**
     * Gets the value of the startDataId property.
     * 
     */
    public long getStartDataId() {
        return startDataId;
    }

    /**
     * Sets the value of the startDataId property.
     * 
     */
    public void setStartDataId(long value) {
        this.startDataId = value;
    }

    /**
     * Gets the value of the endDataId property.
     * 
     */
    public long getEndDataId() {
        return endDataId;
    }

    /**
     * Sets the value of the endDataId property.
     * 
     */
    public void setEndDataId(long value) {
        this.endDataId = value;
    }

    /**
     * Gets the value of the recordCount property.
     * 
     */
    public long getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the value of the recordCount property.
     * 
     */
    public void setRecordCount(long value) {
        this.recordCount = value;
    }

    /**
     * Gets the value of the concurrentGroupId property.
     * 
     */
    public long getConcurrentGroupId() {
        return concurrentGroupId;
    }

    /**
     * Sets the value of the concurrentGroupId property.
     * 
     */
    public void setConcurrentGroupId(long value) {
        this.concurrentGroupId = value;
    }

}
