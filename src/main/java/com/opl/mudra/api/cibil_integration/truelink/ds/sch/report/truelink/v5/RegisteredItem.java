
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Security" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateMatures" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="originalBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "security"
})
@XmlRootElement(name = "RegisteredItem")
public class RegisteredItem {

    @XmlElement(name = "Security")
    protected List<CodeRef> security;
    @XmlAttribute(name = "dateMatures")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateMatures;
    @XmlAttribute(name = "originalBalance")
    protected BigDecimal originalBalance;

    /**
     * Gets the value of the security property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the security property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeRef }
     * 
     * 
     */
    public List<CodeRef> getSecurity() {
        if (security == null) {
            security = new ArrayList<CodeRef>();
        }
        return this.security;
    }

    /**
     * Gets the value of the dateMatures property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateMatures() {
        return dateMatures;
    }

    /**
     * Sets the value of the dateMatures property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateMatures(XMLGregorianCalendar value) {
        this.dateMatures = value;
    }

    /**
     * Gets the value of the originalBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOriginalBalance() {
        return originalBalance;
    }

    /**
     * Sets the value of the originalBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOriginalBalance(BigDecimal value) {
        this.originalBalance = value;
    }

}
