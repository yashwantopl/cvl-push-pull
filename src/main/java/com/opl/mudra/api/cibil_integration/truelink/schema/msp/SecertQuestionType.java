
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SecretQuestionType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for SecertQuestionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecertQuestionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}SecretQuestion"/>
 *         &lt;element name="SercretAnswer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecertQuestionType", propOrder = {
    "secretQuestion",
    "sercretAnswer"
})
public class SecertQuestionType {

    @XmlElement(name = "SecretQuestion", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected SecretQuestionType secretQuestion;
    @XmlElement(name = "SercretAnswer", required = true)
    protected String sercretAnswer;

    /**
     * Gets the value of the secretQuestion property.
     * 
     * @return
     *     possible object is
     *     {@link SecretQuestionType }
     *     
     */
    public SecretQuestionType getSecretQuestion() {
        return secretQuestion;
    }

    /**
     * Sets the value of the secretQuestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecretQuestionType }
     *     
     */
    public void setSecretQuestion(SecretQuestionType value) {
        this.secretQuestion = value;
    }

    /**
     * Gets the value of the sercretAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSercretAnswer() {
        return sercretAnswer;
    }

    /**
     * Sets the value of the sercretAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSercretAnswer(String value) {
        this.sercretAnswer = value;
    }

}
