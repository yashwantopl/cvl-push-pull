package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author sanket
 *
 */
public class MobileFrameRequest extends MobileLoanRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Map<String, Object>> dataList;
	
	

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	
	

}
