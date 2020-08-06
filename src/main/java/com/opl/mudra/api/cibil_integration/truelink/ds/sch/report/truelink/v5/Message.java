
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
 *         &lt;element name="Code" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Type" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *       &lt;/sequence>
 *       &lt;attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "code",
    "type"
})
@XmlRootElement(name = "Message")
public class Message {

    @XmlElement(name = "Code", required = true)
    protected CodeRef code;
    @XmlElement(name = "Type", required = true)
    protected CodeRef type;
    @XmlAttribute(name = "text")
    protected String text;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCode(CodeRef value) {
        this.code = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setType(CodeRef value) {
        this.type = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

}
