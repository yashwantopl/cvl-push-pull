package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

/**
 * @author sanket
 *
 */
public class NotificationPageRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pageIndex;
	
	private Integer size;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getSize() {
		return size;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	

}
