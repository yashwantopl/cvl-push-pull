package com.opl.mudra.api.mca.verifyApi.directorDetail;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
  * @auther : Maaz Shaikh
  * @Time : 02-Aug-2019 - 6:33:13 PM
  */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorDetailPara {
	
	@JsonProperty("api_auth_token")
	private String apiAuthTokan;
	
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("din")
	private List<String> din;

	public DirectorDetailPara() {
		super();
	}
	
	public DirectorDetailPara(String apiAuthTokan, Long userId,List<String> din) {
		super();
		this.apiAuthTokan = apiAuthTokan;
		this.userId = userId;
		this.din = din;
	}

	public String getApiAuthTokan() {
		return apiAuthTokan;
	}

	public void setApiAuthTokan(String apiAuthTokan) {
		this.apiAuthTokan = apiAuthTokan;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getDin() {
		return din;
	}

	public void setDin(List<String> din) {
		this.din = din;
	}
	
	public String addDin(String din) {
		getDin().add(din);
		return din;
	}

	@Override
	public String toString() {
		return "DirectorDetailPara [apiAuthTokan=" + apiAuthTokan + ", userId="
				+ userId + ", din=" + din + "]";
	}
	

}
