
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SurveyQuestionsAndAnswersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SurveyQuestionsAndAnswersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SurveyType" type="{com/truelink/schema/database/tcps/enumerations}SurveyType"/>
 *         &lt;element name="SurveyQuestionAndAnswer" type="{com/truelink/schema/msp}SurveyQuestionAndAnswerType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurveyQuestionsAndAnswersType", propOrder = {
    "surveyType",
    "surveyQuestionAndAnswer"
})
public class SurveyQuestionsAndAnswersType {

    @XmlElement(name = "SurveyType", required = true)
    @XmlSchemaType(name = "string")
    protected SurveyType surveyType;
    @XmlElement(name = "SurveyQuestionAndAnswer", required = true)
    protected List<SurveyQuestionAndAnswerType> surveyQuestionAndAnswer;

    /**
     * Gets the value of the surveyType property.
     * 
     * @return
     *     possible object is
     *     {@link SurveyType }
     *     
     */
    public SurveyType getSurveyType() {
        return surveyType;
    }

    /**
     * Sets the value of the surveyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurveyType }
     *     
     */
    public void setSurveyType(SurveyType value) {
        this.surveyType = value;
    }

    /**
     * Gets the value of the surveyQuestionAndAnswer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surveyQuestionAndAnswer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurveyQuestionAndAnswer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyQuestionAndAnswerType }
     * 
     * 
     */
    public List<SurveyQuestionAndAnswerType> getSurveyQuestionAndAnswer() {
        if (surveyQuestionAndAnswer == null) {
            surveyQuestionAndAnswer = new ArrayList<SurveyQuestionAndAnswerType>();
        }
        return this.surveyQuestionAndAnswer;
    }

}
