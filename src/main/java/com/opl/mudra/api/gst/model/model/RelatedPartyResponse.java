/**
 * 
 */
package com.opl.mudra.api.gst.model.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author vijay.chauhan
 *
 */
public class RelatedPartyResponse {

	private List<Map<String, Object>> topTrans;
	
	public RelatedPartyResponse(){
		this.topTrans=new ArrayList<>();
	}

	/**
	 * @return the topTrans
	 */
	public List<Map<String, Object>> getTopTrans() {
		return topTrans;
	}

	/**
	 * @param topTrans the topTrans to set
	 */
	public void setTopTrans(List<Map<String, Object>> topTrans) {
		this.topTrans = topTrans;
	}

	
	
}
