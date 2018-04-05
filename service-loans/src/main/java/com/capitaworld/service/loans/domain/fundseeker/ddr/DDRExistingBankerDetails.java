package com.capitaworld.service.loans.domain.fundseeker.ddr;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="fs_ddr_existing_banker_details")
@NamedQuery(name="DDRExistingBankerDetails.findAll", query="SELECT a FROM DDRExistingBankerDetails a")
public class DDRExistingBankerDetails  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fs_ddr_form_id")
    private Long ddrFormId;

    @Column(name="financial_institution_name")
    private String financialInstitutionName;

    @Column(name="address")
    private String address;

    @Column(name="relationship_since")
    private Integer relationshipSince;

    @Column(name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Column(name = "modify_by")
    private Long modifyBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "is_active")
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDdrFormId() {
        return ddrFormId;
    }

    public void setDdrFormId(Long ddrFormId) {
        this.ddrFormId = ddrFormId;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRelationshipSince() {
        return relationshipSince;
    }

    public void setRelationshipSince(Integer relationshipSince) {
        this.relationshipSince = relationshipSince;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
