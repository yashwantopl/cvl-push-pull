
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SurveyQuestionType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for SurveyQuestionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SurveyQuestionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QuestionName" type="{com/truelink/schema/database/tcps/enumerations}SurveyQuestionType"/>
 *         &lt;element name="QuestionDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Answer" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurveyQuestionsType", propOrder = {
    "questionName",
    "questionDescription",
    "answer"
})
public class SurveyQuestionsType {

    @XmlElement(name = "QuestionName", required = true)
    @XmlSchemaType(name = "string")
    protected SurveyQuestionType questionName;
    @XmlElement(name = "QuestionDescription", required = true)
    protected String questionDescription;
    @XmlElement(name = "Answer", required = true)
    protected List<Answer> answer;

    /**
     * Gets the value of the questionName property.
     *
     * @return
     *     possible object is
     *     {@link SurveyQuestionType }
     *
     */
    public SurveyQuestionType getQuestionName() {
        return questionName;
    }

    /**
     * Sets the value of the questionName property.
     *
     * @param value
     *     allowed object is
     *     {@link SurveyQuestionType }
     *
     */
    public void setQuestionName(SurveyQuestionType value) {
        this.questionName = value;
    }

    /**
     * Gets the value of the questionDescription property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getQuestionDescription() {
        return questionDescription;
    }

    /**
     * Sets the value of the questionDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setQuestionDescription(String value) {
        this.questionDescription = value;
    }

    /**
     * Gets the value of the answer property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the answer property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnswer().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyQuestionsType.Answer }
     *
     *
     */
    public List<Answer> getAnswer() {
        if (answer == null) {
            answer = new ArrayList<Answer>();
        }
        return this.answer;
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
     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "name",
        "description"
    })
    public static class Answer {

        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Description", required = true)
        protected String description;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

    }

}
