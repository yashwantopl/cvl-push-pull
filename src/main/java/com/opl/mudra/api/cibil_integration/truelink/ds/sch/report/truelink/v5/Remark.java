
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="RemarkCode" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *       &lt;/sequence>
 *       &lt;attribute name="customRemark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "remarkCode"
})
@XmlRootElement(name = "Remark")
public class Remark {

    @XmlElement(name = "RemarkCode", required = true)
    protected CodeRef remarkCode;
    @XmlAttribute(name = "customRemark")
    protected String customRemark;

    /**
     * Gets the value of the remarkCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getRemarkCode() {
        return remarkCode;
    }

    /**
     * Sets the value of the remarkCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setRemarkCode(CodeRef value) {
        this.remarkCode = value;
    }

    /**
     * Gets the value of the customRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomRemark() {
        return customRemark;
    }

    /**
     * Sets the value of the customRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomRemark(String value) {
        this.customRemark = value;
    }

}
