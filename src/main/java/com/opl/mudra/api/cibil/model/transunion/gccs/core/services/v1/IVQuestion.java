
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IVQuestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IVQuestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LastChanceQuestion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FullQuestionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnswerChoice" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AnswerChoiceText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Range" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="UpperLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="LowerLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="AnswerChoiceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skipEligible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="resendEligible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="selectMultiple" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVQuestion", propOrder = {
    "lastChanceQuestion",
    "fullQuestionText",
    "answerChoice",
    "key",
    "skipEligible",
    "resendEligible",
    "selectMultiple"
})
public class IVQuestion {

    @XmlElement(name = "LastChanceQuestion")
    protected boolean lastChanceQuestion;
    @XmlElement(name = "FullQuestionText", required = true)
    protected String fullQuestionText;
    @XmlElement(name = "AnswerChoice", required = true)
    protected List<AnswerChoice> answerChoice;
    @XmlElement(name = "Key")
    protected String key;
    protected Boolean skipEligible;
    protected Boolean resendEligible;
    protected Boolean selectMultiple;

    /**
     * Gets the value of the lastChanceQuestion property.
     *
     */
    public boolean isLastChanceQuestion() {
        return lastChanceQuestion;
    }

    /**
     * Sets the value of the lastChanceQuestion property.
     *
     */
    public void setLastChanceQuestion(boolean value) {
        this.lastChanceQuestion = value;
    }

    /**
     * Gets the value of the fullQuestionText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFullQuestionText() {
        return fullQuestionText;
    }

    /**
     * Sets the value of the fullQuestionText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFullQuestionText(String value) {
        this.fullQuestionText = value;
    }

    /**
     * Gets the value of the answerChoice property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the answerChoice property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnswerChoice().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IVQuestion.AnswerChoice }
     *
     *
     */
    public List<AnswerChoice> getAnswerChoice() {
        if (answerChoice == null) {
            answerChoice = new ArrayList<AnswerChoice>();
        }
        return this.answerChoice;
    }

    /**
     * Gets the value of the key property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the skipEligible property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSkipEligible() {
        return skipEligible;
    }

    /**
     * Sets the value of the skipEligible property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSkipEligible(Boolean value) {
        this.skipEligible = value;
    }

    /**
     * Gets the value of the resendEligible property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isResendEligible() {
        return resendEligible;
    }

    /**
     * Sets the value of the resendEligible property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setResendEligible(Boolean value) {
        this.resendEligible = value;
    }

    /**
     * Gets the value of the selectMultiple property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSelectMultiple() {
        return selectMultiple;
    }

    /**
     * Sets the value of the selectMultiple property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSelectMultiple(Boolean value) {
        this.selectMultiple = value;
    }


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
     *         &lt;element name="AnswerChoiceText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Range" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="UpperLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="LowerLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="AnswerChoiceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "answerChoiceText",
        "range",
        "key",
        "answerChoiceId"
    })
    public static class AnswerChoice {

        @XmlElement(name = "AnswerChoiceText")
        protected String answerChoiceText;
        @XmlElement(name = "Range")
        protected IVQuestion.AnswerChoice.Range range;
        @XmlElement(name = "Key")
        protected String key;
        @XmlElement(name = "AnswerChoiceId")
        protected long answerChoiceId;

        /**
         * Gets the value of the answerChoiceText property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAnswerChoiceText() {
            return answerChoiceText;
        }

        /**
         * Sets the value of the answerChoiceText property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAnswerChoiceText(String value) {
            this.answerChoiceText = value;
        }

        /**
         * Gets the value of the range property.
         *
         * @return
         *     possible object is
         *     {@link IVQuestion.AnswerChoice.Range }
         *
         */
        public IVQuestion.AnswerChoice.Range getRange() {
            return range;
        }

        /**
         * Sets the value of the range property.
         *
         * @param value
         *     allowed object is
         *     {@link IVQuestion.AnswerChoice.Range }
         *
         */
        public void setRange(IVQuestion.AnswerChoice.Range value) {
            this.range = value;
        }

        /**
         * Gets the value of the key property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets the value of the key property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKey(String value) {
            this.key = value;
        }

        /**
         * Gets the value of the answerChoiceId property.
         * 
         */
        public long getAnswerChoiceId() {
            return answerChoiceId;
        }

        /**
         * Sets the value of the answerChoiceId property.
         * 
         */
        public void setAnswerChoiceId(long value) {
            this.answerChoiceId = value;
        }


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
         *         &lt;element name="UpperLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="LowerLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
            "upperLimit",
            "lowerLimit"
        })
        public static class Range {

            @XmlElement(name = "UpperLimit")
            protected long upperLimit;
            @XmlElement(name = "LowerLimit")
            protected long lowerLimit;

            /**
             * Gets the value of the upperLimit property.
             * 
             */
            public long getUpperLimit() {
                return upperLimit;
            }

            /**
             * Sets the value of the upperLimit property.
             * 
             */
            public void setUpperLimit(long value) {
                this.upperLimit = value;
            }

            /**
             * Gets the value of the lowerLimit property.
             * 
             */
            public long getLowerLimit() {
                return lowerLimit;
            }

            /**
             * Sets the value of the lowerLimit property.
             * 
             */
            public void setLowerLimit(long value) {
                this.lowerLimit = value;
            }

        }

    }

}
