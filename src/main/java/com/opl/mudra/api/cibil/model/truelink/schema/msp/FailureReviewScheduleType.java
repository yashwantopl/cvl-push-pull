
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.FailureReviewType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.ScheduleStatusType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.ScheduleType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.TimePeriodUnitType;


/**
 * <p>Java class for FailureReviewScheduleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FailureReviewScheduleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FailureReviewScheduleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}FailureReview"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}ScheduleStatus"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}Schedule"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}TimePeriodUnit"/>
 *         &lt;element name="NextActionTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActionCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="BtxRefKey" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FailureReviewScheduleType", propOrder = {
    "failureReviewScheduleId",
    "customerId",
    "failureReview",
    "scheduleStatus",
    "schedule",
    "timePeriodUnit",
    "nextActionTime",
    "actionCount",
    "btxRefKey"
})
public class FailureReviewScheduleType {

    @XmlElement(name = "FailureReviewScheduleId")
    protected long failureReviewScheduleId;
    @XmlElement(name = "CustomerId")
    protected long customerId;
    @XmlElement(name = "FailureReview", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected FailureReviewType failureReview;
    @XmlElement(name = "ScheduleStatus", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected ScheduleStatusType scheduleStatus;
    @XmlElement(name = "Schedule", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected ScheduleType schedule;
    @XmlElement(name = "TimePeriodUnit", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected TimePeriodUnitType timePeriodUnit;
    @XmlElement(name = "NextActionTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nextActionTime;
    @XmlElement(name = "ActionCount")
    protected long actionCount;
    @XmlElement(name = "BtxRefKey")
    protected BigDecimal btxRefKey;

    /**
     * Gets the value of the failureReviewScheduleId property.
     * 
     */
    public long getFailureReviewScheduleId() {
        return failureReviewScheduleId;
    }

    /**
     * Sets the value of the failureReviewScheduleId property.
     * 
     */
    public void setFailureReviewScheduleId(long value) {
        this.failureReviewScheduleId = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     */
    public void setCustomerId(long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the failureReview property.
     * 
     * @return
     *     possible object is
     *     {@link FailureReviewType }
     *     
     */
    public FailureReviewType getFailureReview() {
        return failureReview;
    }

    /**
     * Sets the value of the failureReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link FailureReviewType }
     *     
     */
    public void setFailureReview(FailureReviewType value) {
        this.failureReview = value;
    }

    /**
     * Gets the value of the scheduleStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleStatusType }
     *     
     */
    public ScheduleStatusType getScheduleStatus() {
        return scheduleStatus;
    }

    /**
     * Sets the value of the scheduleStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleStatusType }
     *     
     */
    public void setScheduleStatus(ScheduleStatusType value) {
        this.scheduleStatus = value;
    }

    /**
     * Gets the value of the schedule property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleType }
     *     
     */
    public ScheduleType getSchedule() {
        return schedule;
    }

    /**
     * Sets the value of the schedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleType }
     *     
     */
    public void setSchedule(ScheduleType value) {
        this.schedule = value;
    }

    /**
     * Gets the value of the timePeriodUnit property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodUnitType }
     *     
     */
    public TimePeriodUnitType getTimePeriodUnit() {
        return timePeriodUnit;
    }

    /**
     * Sets the value of the timePeriodUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodUnitType }
     *     
     */
    public void setTimePeriodUnit(TimePeriodUnitType value) {
        this.timePeriodUnit = value;
    }

    /**
     * Gets the value of the nextActionTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextActionTime() {
        return nextActionTime;
    }

    /**
     * Sets the value of the nextActionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextActionTime(XMLGregorianCalendar value) {
        this.nextActionTime = value;
    }

    /**
     * Gets the value of the actionCount property.
     * 
     */
    public long getActionCount() {
        return actionCount;
    }

    /**
     * Sets the value of the actionCount property.
     * 
     */
    public void setActionCount(long value) {
        this.actionCount = value;
    }

    /**
     * Gets the value of the btxRefKey property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBtxRefKey() {
        return btxRefKey;
    }

    /**
     * Sets the value of the btxRefKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBtxRefKey(BigDecimal value) {
        this.btxRefKey = value;
    }

}
