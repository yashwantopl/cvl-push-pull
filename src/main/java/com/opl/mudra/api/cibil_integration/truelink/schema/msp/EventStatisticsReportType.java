
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CommEventMediumType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.CsptIssueType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for EventStatisticsReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventStatisticsReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Contact" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CommEventMedium" type="{com/truelink/schema/database/tcps/enumerations}CommEventMediumType"/>
 *                   &lt;element name="CallType" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CsptIssue" type="{com/truelink/schema/database/tcps/enumerations}CsptIssueType"/>
 *                             &lt;element name="CallTypeTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="Reason" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CsptReason" type="{com/truelink/schema/database/tcps/enumerations}CsptReasonType" minOccurs="0"/>
 *                                       &lt;element name="ReasonTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                       &lt;element name="Action" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CsptAction" type="{com/truelink/schema/database/tcps/enumerations}CsptActionType"/>
 *                                                 &lt;element name="ActionTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
@XmlType(name = "EventStatisticsReportType", propOrder = {
    "contact"
})
public class EventStatisticsReportType {

    @XmlElement(name = "Contact", required = true)
    protected List<Contact> contact;

    /**
     * Gets the value of the contact property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contact property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContact().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventStatisticsReportType.Contact }
     *
     *
     */
    public List<Contact> getContact() {
        if (contact == null) {
            contact = new ArrayList<Contact>();
        }
        return this.contact;
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
     *         &lt;element name="CommEventMedium" type="{com/truelink/schema/database/tcps/enumerations}CommEventMediumType"/>
     *         &lt;element name="CallType" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CsptIssue" type="{com/truelink/schema/database/tcps/enumerations}CsptIssueType"/>
     *                   &lt;element name="CallTypeTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="Reason" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CsptReason" type="{com/truelink/schema/database/tcps/enumerations}CsptReasonType" minOccurs="0"/>
     *                             &lt;element name="ReasonTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                             &lt;element name="Action" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="CsptAction" type="{com/truelink/schema/database/tcps/enumerations}CsptActionType"/>
     *                                       &lt;element name="ActionTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
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
    @XmlType(name = "", propOrder = {
        "commEventMedium",
        "callType"
    })
    public static class Contact {

        @XmlElement(name = "CommEventMedium", required = true)
        @XmlSchemaType(name = "string")
        protected CommEventMediumType commEventMedium;
        @XmlElement(name = "CallType", required = true)
        protected List<CallType> callType;

        /**
         * Gets the value of the commEventMedium property.
         *
         * @return
         *     possible object is
         *     {@link CommEventMediumType }
         *
         */
        public CommEventMediumType getCommEventMedium() {
            return commEventMedium;
        }

        /**
         * Sets the value of the commEventMedium property.
         *
         * @param value
         *     allowed object is
         *     {@link CommEventMediumType }
         *
         */
        public void setCommEventMedium(CommEventMediumType value) {
            this.commEventMedium = value;
        }

        /**
         * Gets the value of the callType property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the callType property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCallType().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EventStatisticsReportType.Contact.CallType }
         *
         *
         */
        public List<CallType> getCallType() {
            if (callType == null) {
                callType = new ArrayList<CallType>();
            }
            return this.callType;
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
         *         &lt;element name="CsptIssue" type="{com/truelink/schema/database/tcps/enumerations}CsptIssueType"/>
         *         &lt;element name="CallTypeTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="Reason" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="CsptReason" type="{com/truelink/schema/database/tcps/enumerations}CsptReasonType" minOccurs="0"/>
         *                   &lt;element name="ReasonTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *                   &lt;element name="Action" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="CsptAction" type="{com/truelink/schema/database/tcps/enumerations}CsptActionType"/>
         *                             &lt;element name="ActionTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
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
        @XmlType(name = "", propOrder = {
            "csptIssue",
            "callTypeTotal",
            "reason"
        })
        public static class CallType {

            @XmlElement(name = "CsptIssue", required = true)
            @XmlSchemaType(name = "string")
            protected CsptIssueType csptIssue;
            @XmlElement(name = "CallTypeTotal")
            protected int callTypeTotal;
            @XmlElement(name = "Reason", required = true)
            protected List<Reason> reason;

            /**
             * Gets the value of the csptIssue property.
             *
             * @return
             *     possible object is
             *     {@link CsptIssueType }
             *
             */
            public CsptIssueType getCsptIssue() {
                return csptIssue;
            }

            /**
             * Sets the value of the csptIssue property.
             *
             * @param value
             *     allowed object is
             *     {@link CsptIssueType }
             *
             */
            public void setCsptIssue(CsptIssueType value) {
                this.csptIssue = value;
            }

            /**
             * Gets the value of the callTypeTotal property.
             *
             */
            public int getCallTypeTotal() {
                return callTypeTotal;
            }

            /**
             * Sets the value of the callTypeTotal property.
             *
             */
            public void setCallTypeTotal(int value) {
                this.callTypeTotal = value;
            }

            /**
             * Gets the value of the reason property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the reason property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getReason().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link EventStatisticsReportType.Contact.CallType.Reason }
             *
             *
             */
            public List<Reason> getReason() {
                if (reason == null) {
                    reason = new ArrayList<Reason>();
                }
                return this.reason;
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
             *         &lt;element name="CsptReason" type="{com/truelink/schema/database/tcps/enumerations}CsptReasonType" minOccurs="0"/>
             *         &lt;element name="ReasonTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
             *         &lt;element name="Action" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="CsptAction" type="{com/truelink/schema/database/tcps/enumerations}CsptActionType"/>
             *                   &lt;element name="ActionTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
            @XmlType(name = "", propOrder = {
                "csptReason",
                "reasonTotal",
                "action"
            })
            public static class Reason {

                @XmlElement(name = "CsptReason")
                @XmlSchemaType(name = "string")
                protected CsptReasonType csptReason;
                @XmlElement(name = "ReasonTotal")
                protected int reasonTotal;
                @XmlElement(name = "Action", required = true)
                protected List<Action> action;

                /**
                 * Gets the value of the csptReason property.
                 *
                 * @return
                 *     possible object is
                 *     {@link CsptReasonType }
                 *
                 */
                public CsptReasonType getCsptReason() {
                    return csptReason;
                }

                /**
                 * Sets the value of the csptReason property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link CsptReasonType }
                 *
                 */
                public void setCsptReason(CsptReasonType value) {
                    this.csptReason = value;
                }

                /**
                 * Gets the value of the reasonTotal property.
                 *
                 */
                public int getReasonTotal() {
                    return reasonTotal;
                }

                /**
                 * Sets the value of the reasonTotal property.
                 *
                 */
                public void setReasonTotal(int value) {
                    this.reasonTotal = value;
                }

                /**
                 * Gets the value of the action property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the action property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getAction().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link EventStatisticsReportType.Contact.CallType.Reason.Action }
                 *
                 *
                 */
                public List<Action> getAction() {
                    if (action == null) {
                        action = new ArrayList<Action>();
                    }
                    return this.action;
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
                 *         &lt;element name="CsptAction" type="{com/truelink/schema/database/tcps/enumerations}CsptActionType"/>
                 *         &lt;element name="ActionTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
                    "csptAction",
                    "actionTotal"
                })
                public static class Action {

                    @XmlElement(name = "CsptAction", required = true)
                    protected String csptAction;
                    @XmlElement(name = "ActionTotal")
                    protected int actionTotal;

                    /**
                     * Gets the value of the csptAction property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCsptAction() {
                        return csptAction;
                    }

                    /**
                     * Sets the value of the csptAction property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCsptAction(String value) {
                        this.csptAction = value;
                    }

                    /**
                     * Gets the value of the actionTotal property.
                     * 
                     */
                    public int getActionTotal() {
                        return actionTotal;
                    }

                    /**
                     * Sets the value of the actionTotal property.
                     * 
                     */
                    public void setActionTotal(int value) {
                        this.actionTotal = value;
                    }

                }

            }

        }

    }

}
