
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="question" type="{com/transunion/gccs/core/services/v1}IVQuestion" maxOccurs="unbounded"/>
 *         &lt;element name="ChallengeConfigGUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QueueName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "question",
    "challengeConfigGUID",
    "queueName"
})
@XmlRootElement(name = "GetAuthenticationQuestionsSuccess")
public class GetAuthenticationQuestionsSuccess {

    @XmlElement(required = true)
    protected List<IVQuestion> question;
    @XmlElement(name = "ChallengeConfigGUID", required = true)
    protected String challengeConfigGUID;
    @XmlElement(name = "QueueName")
    protected String queueName;

    /**
     * Gets the value of the question property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the question property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IVQuestion }
     * 
     * 
     */
    public List<IVQuestion> getQuestion() {
        if (question == null) {
            question = new ArrayList<IVQuestion>();
        }
        return this.question;
    }

    /**
     * Gets the value of the challengeConfigGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChallengeConfigGUID() {
        return challengeConfigGUID;
    }

    /**
     * Sets the value of the challengeConfigGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChallengeConfigGUID(String value) {
        this.challengeConfigGUID = value;
    }

    /**
     * Gets the value of the queueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * Sets the value of the queueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueName(String value) {
        this.queueName = value;
    }

}
