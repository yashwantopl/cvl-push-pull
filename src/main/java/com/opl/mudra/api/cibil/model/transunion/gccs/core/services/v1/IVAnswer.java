
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IVAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IVAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="questionKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="answerKey" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="UserInputAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resendOTP" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="skipQuestion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVAnswer", propOrder = {
    "questionKey",
    "answerKey",
    "userInputAnswer",
    "resendOTP",
    "skipQuestion"
})
public class IVAnswer {

    @XmlElement(required = true)
    protected String questionKey;
    @XmlElement(required = true)
    protected List<String> answerKey;
    @XmlElement(name = "UserInputAnswer")
    protected String userInputAnswer;
    protected Boolean resendOTP;
    protected Boolean skipQuestion;

    /**
     * Gets the value of the questionKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionKey() {
        return questionKey;
    }

    /**
     * Sets the value of the questionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionKey(String value) {
        this.questionKey = value;
    }

    /**
     * Gets the value of the answerKey property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the answerKey property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnswerKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAnswerKey() {
        if (answerKey == null) {
            answerKey = new ArrayList<String>();
        }
        return this.answerKey;
    }

    /**
     * Gets the value of the userInputAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserInputAnswer() {
        return userInputAnswer;
    }

    /**
     * Sets the value of the userInputAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserInputAnswer(String value) {
        this.userInputAnswer = value;
    }

    /**
     * Gets the value of the resendOTP property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResendOTP() {
        return resendOTP;
    }

    /**
     * Sets the value of the resendOTP property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResendOTP(Boolean value) {
        this.resendOTP = value;
    }

    /**
     * Gets the value of the skipQuestion property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSkipQuestion() {
        return skipQuestion;
    }

    /**
     * Sets the value of the skipQuestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSkipQuestion(Boolean value) {
        this.skipQuestion = value;
    }

}
