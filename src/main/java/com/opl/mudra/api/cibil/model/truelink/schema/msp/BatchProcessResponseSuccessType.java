
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchProcessResponseSuccessType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchProcessResponseSuccessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConcurrentProcess" type="{com/truelink/schema/msp}ConcurrentProcessType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchProcessResponseSuccessType", propOrder = {
    "concurrentProcess"
})
public class BatchProcessResponseSuccessType {

    @XmlElement(name = "ConcurrentProcess")
    protected List<ConcurrentProcessType> concurrentProcess;

    /**
     * Gets the value of the concurrentProcess property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the concurrentProcess property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConcurrentProcess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConcurrentProcessType }
     * 
     * 
     */
    public List<ConcurrentProcessType> getConcurrentProcess() {
        if (concurrentProcess == null) {
            concurrentProcess = new ArrayList<ConcurrentProcessType>();
        }
        return this.concurrentProcess;
    }

}
