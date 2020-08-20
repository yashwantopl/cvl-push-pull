package com.opl.service.loans.domain.fundseeker.mfi;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Harsukh Ghumaliya
 */
@Entity
@Table(name = "tutorial_view_audit")
public class MFITutorialsViewAudits implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;
	@Column(name = "role_id")
    private Long roleId;
	@Column(name = "tutorial_id")
    private Long tutorialId;
	@Column(name = "loan_type")
    private Integer loanType;
	@Column(name = "view_date")
    private Date viewDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getTutorialId() {
		return tutorialId;
	}
	public void setTutorialId(Long tutorialId) {
		this.tutorialId = tutorialId;
	}
	public Integer getLoanType() {
		return loanType;
	}
	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}
	
	
	
	
	
}
