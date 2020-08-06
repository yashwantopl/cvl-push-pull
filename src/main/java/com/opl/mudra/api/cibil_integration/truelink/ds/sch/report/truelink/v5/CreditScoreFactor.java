
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
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
 *         &lt;element name="Factor" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="FactorText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="bureauCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FactorType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "factor",
    "factorText"
})
@XmlRootElement(name = "CreditScoreFactor")
public class CreditScoreFactor {

    @XmlElement(name = "Factor", required = true)
    protected CodeRef factor;
    @XmlElement(name = "FactorText")
    protected List<String> factorText;
    @XmlAttribute(name = "bureauCode")
    protected String bureauCode;
    @XmlAttribute(name = "FactorType")
    protected String factorType;

    /**
     * Gets the value of the factor property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getFactor() {
        return factor;
    }

    /**
     * Sets the value of the factor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setFactor(CodeRef value) {
        this.factor = value;
    }

    /**
     * Gets the value of the factorText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factorText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactorText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFactorText() {
        if (factorText == null) {
            factorText = new ArrayList<String>();
        }
        return this.factorText;
    }

    /**
     * Gets the value of the bureauCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauCode() {
        return bureauCode;
    }

    /**
     * Sets the value of the bureauCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauCode(String value) {
        this.bureauCode = value;
    }

    /**
     * Gets the value of the factorType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactorType() {
        return factorType;
    }

    /**
     * Sets the value of the factorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactorType(String value) {
        this.factorType = value;
    }

}
