
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderHistoryInvoiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderHistoryInvoiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="InvoiceInfo" type="{com/truelink/schema/msp}InvoiceBasicType"/>
 *         &lt;element name="PaymentTransaction" type="{com/truelink/schema/msp}PaymentTransactionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Latest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderHistoryInvoiceType", propOrder = {
    "invoiceId",
    "invoiceInfo",
    "paymentTransaction",
    "latest"
})
public class OrderHistoryInvoiceType {

    @XmlElement(name = "InvoiceId")
    protected long invoiceId;
    @XmlElement(name = "InvoiceInfo", required = true)
    protected InvoiceBasicType invoiceInfo;
    @XmlElement(name = "PaymentTransaction")
    protected List<PaymentTransactionType> paymentTransaction;
    @XmlElement(name = "Latest")
    protected Boolean latest;

    /**
     * Gets the value of the invoiceId property.
     * 
     */
    public long getInvoiceId() {
        return invoiceId;
    }

    /**
     * Sets the value of the invoiceId property.
     * 
     */
    public void setInvoiceId(long value) {
        this.invoiceId = value;
    }

    /**
     * Gets the value of the invoiceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceBasicType }
     *     
     */
    public InvoiceBasicType getInvoiceInfo() {
        return invoiceInfo;
    }

    /**
     * Sets the value of the invoiceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceBasicType }
     *     
     */
    public void setInvoiceInfo(InvoiceBasicType value) {
        this.invoiceInfo = value;
    }

    /**
     * Gets the value of the paymentTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentTransactionType }
     * 
     * 
     */
    public List<PaymentTransactionType> getPaymentTransaction() {
        if (paymentTransaction == null) {
            paymentTransaction = new ArrayList<PaymentTransactionType>();
        }
        return this.paymentTransaction;
    }

    /**
     * Gets the value of the latest property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLatest() {
        return latest;
    }

    /**
     * Sets the value of the latest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLatest(Boolean value) {
        this.latest = value;
    }

}
