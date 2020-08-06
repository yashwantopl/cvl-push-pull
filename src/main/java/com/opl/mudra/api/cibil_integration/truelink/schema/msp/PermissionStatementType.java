
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PermissionStatementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PermissionStatementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PermissionStatementId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PermissionStatement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PermissionStatementType", propOrder = {
    "permissionStatementId",
    "permissionStatement"
})
public class PermissionStatementType {

    @XmlElement(name = "PermissionStatementId")
    protected long permissionStatementId;
    @XmlElement(name = "PermissionStatement", required = true)
    protected String permissionStatement;

    /**
     * Gets the value of the permissionStatementId property.
     * 
     */
    public long getPermissionStatementId() {
        return permissionStatementId;
    }

    /**
     * Sets the value of the permissionStatementId property.
     * 
     */
    public void setPermissionStatementId(long value) {
        this.permissionStatementId = value;
    }

    /**
     * Gets the value of the permissionStatement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionStatement() {
        return permissionStatement;
    }

    /**
     * Sets the value of the permissionStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionStatement(String value) {
        this.permissionStatement = value;
    }

}
