
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.BillingStatusType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.LockReasonType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.MemberStatusType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.OrderFulfillStatusType;


/**
 * <p>Java class for CustomerStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="CustomerState" use="required" type="{com/truelink/schema/database/tcps/enumerations}CustomerStatusType" />
 *       &lt;attribute name="CustomerStateDisplay" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OrderBillingState" use="required" type="{com/truelink/schema/database/tcps/enumerations}BillingStatusType" />
 *       &lt;attribute name="OrderBillingStateDisplay" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OrderFulfillmentState" use="required" type="{com/truelink/schema/database/tcps/enumerations}OrderFulfillStatusType" />
 *       &lt;attribute name="OrderFulfillmentStateDisplay" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MemberStatusType" use="required" type="{com/truelink/schema/database/tcps/enumerations}MemberStatusType" />
 *       &lt;attribute name="ActiveOrderId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="LockReason" type="{com/truelink/schema/database/tcps/enumerations}LockReasonType" />
 *       &lt;attribute name="IVRefCode" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="IVStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isCustomerReAuthenticated" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerStatusType")
public class CustomerStatusType {

    @XmlAttribute(name = "CustomerState", required = true)
    protected com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CustomerStatusType customerState;
    @XmlAttribute(name = "CustomerStateDisplay", required = true)
    protected String customerStateDisplay;
    @XmlAttribute(name = "OrderBillingState", required = true)
    protected BillingStatusType orderBillingState;
    @XmlAttribute(name = "OrderBillingStateDisplay", required = true)
    protected String orderBillingStateDisplay;
    @XmlAttribute(name = "OrderFulfillmentState", required = true)
    protected OrderFulfillStatusType orderFulfillmentState;
    @XmlAttribute(name = "OrderFulfillmentStateDisplay", required = true)
    protected String orderFulfillmentStateDisplay;
    @XmlAttribute(name = "MemberStatusType", required = true)
    protected MemberStatusType memberStatusType;
    @XmlAttribute(name = "ActiveOrderId")
    protected Long activeOrderId;
    @XmlAttribute(name = "LockReason")
    protected LockReasonType lockReason;
    @XmlAttribute(name = "IVRefCode")
    protected Integer ivRefCode;
    @XmlAttribute(name = "IVStatus")
    protected String ivStatus;
    @XmlAttribute(name = "isCustomerReAuthenticated")
    protected Boolean isCustomerReAuthenticated;

    /**
     * Gets the value of the customerState property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CustomerStatusType }
     *     
     */
    public com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CustomerStatusType getCustomerState() {
        return customerState;
    }

    /**
     * Sets the value of the customerState property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CustomerStatusType }
     *     
     */
    public void setCustomerState(com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CustomerStatusType value) {
        this.customerState = value;
    }

    /**
     * Gets the value of the customerStateDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerStateDisplay() {
        return customerStateDisplay;
    }

    /**
     * Sets the value of the customerStateDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerStateDisplay(String value) {
        this.customerStateDisplay = value;
    }

    /**
     * Gets the value of the orderBillingState property.
     * 
     * @return
     *     possible object is
     *     {@link BillingStatusType }
     *     
     */
    public BillingStatusType getOrderBillingState() {
        return orderBillingState;
    }

    /**
     * Sets the value of the orderBillingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillingStatusType }
     *     
     */
    public void setOrderBillingState(BillingStatusType value) {
        this.orderBillingState = value;
    }

    /**
     * Gets the value of the orderBillingStateDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBillingStateDisplay() {
        return orderBillingStateDisplay;
    }

    /**
     * Sets the value of the orderBillingStateDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBillingStateDisplay(String value) {
        this.orderBillingStateDisplay = value;
    }

    /**
     * Gets the value of the orderFulfillmentState property.
     * 
     * @return
     *     possible object is
     *     {@link OrderFulfillStatusType }
     *     
     */
    public OrderFulfillStatusType getOrderFulfillmentState() {
        return orderFulfillmentState;
    }

    /**
     * Sets the value of the orderFulfillmentState property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderFulfillStatusType }
     *     
     */
    public void setOrderFulfillmentState(OrderFulfillStatusType value) {
        this.orderFulfillmentState = value;
    }

    /**
     * Gets the value of the orderFulfillmentStateDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderFulfillmentStateDisplay() {
        return orderFulfillmentStateDisplay;
    }

    /**
     * Sets the value of the orderFulfillmentStateDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderFulfillmentStateDisplay(String value) {
        this.orderFulfillmentStateDisplay = value;
    }

    /**
     * Gets the value of the memberStatusType property.
     * 
     * @return
     *     possible object is
     *     {@link MemberStatusType }
     *     
     */
    public MemberStatusType getMemberStatusType() {
        return memberStatusType;
    }

    /**
     * Sets the value of the memberStatusType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberStatusType }
     *     
     */
    public void setMemberStatusType(MemberStatusType value) {
        this.memberStatusType = value;
    }

    /**
     * Gets the value of the activeOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActiveOrderId() {
        return activeOrderId;
    }

    /**
     * Sets the value of the activeOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActiveOrderId(Long value) {
        this.activeOrderId = value;
    }

    /**
     * Gets the value of the lockReason property.
     * 
     * @return
     *     possible object is
     *     {@link LockReasonType }
     *     
     */
    public LockReasonType getLockReason() {
        return lockReason;
    }

    /**
     * Sets the value of the lockReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link LockReasonType }
     *     
     */
    public void setLockReason(LockReasonType value) {
        this.lockReason = value;
    }

    /**
     * Gets the value of the ivRefCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIVRefCode() {
        return ivRefCode;
    }

    /**
     * Sets the value of the ivRefCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIVRefCode(Integer value) {
        this.ivRefCode = value;
    }

    /**
     * Gets the value of the ivStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIVStatus() {
        return ivStatus;
    }

    /**
     * Sets the value of the ivStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIVStatus(String value) {
        this.ivStatus = value;
    }

    /**
     * Gets the value of the isCustomerReAuthenticated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCustomerReAuthenticated() {
        return isCustomerReAuthenticated;
    }

    /**
     * Sets the value of the isCustomerReAuthenticated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCustomerReAuthenticated(Boolean value) {
        this.isCustomerReAuthenticated = value;
    }

}
