
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for SurveyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SurveyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SurveyType" type="{com/truelink/schema/database/tcps/enumerations}SurveyType"/>
 *         &lt;element name="SurveyQuestions" type="{com/truelink/schema/msp}SurveyQuestionsType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurveyType", propOrder = {
    "surveyType",
    "surveyQuestions"
})
public class SurveyType {

    @XmlElement(name = "SurveyType", required = true)
    @XmlSchemaType(name = "string")
    protected com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SurveyType surveyType;
    @XmlElement(name = "SurveyQuestions", required = true)
    protected List<SurveyQuestionsType> surveyQuestions;

    /**
     * Gets the value of the surveyType property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SurveyType }
     *     
     */
    public com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SurveyType getSurveyType() {
        return surveyType;
    }

    /**
     * Sets the value of the surveyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SurveyType }
     *     
     */
    public void setSurveyType(com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SurveyType value) {
        this.surveyType = value;
    }

    /**
     * Gets the value of the surveyQuestions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surveyQuestions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurveyQuestions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyQuestionsType }
     * 
     * 
     */
    public List<SurveyQuestionsType> getSurveyQuestions() {
        if (surveyQuestions == null) {
            surveyQuestions = new ArrayList<SurveyQuestionsType>();
        }
        return this.surveyQuestions;
    }

}
