package com.opl.mudra.api.user.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown =  true)
public class BranchUserResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String isActive;
	private String userRole;
	private String email;
	private String mobile;
	
	private String firstName;
	private String lastName;

}