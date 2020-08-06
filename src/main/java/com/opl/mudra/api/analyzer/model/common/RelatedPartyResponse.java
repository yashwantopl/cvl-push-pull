/**
 * 
 */
package com.opl.mudra.api.analyzer.model.common;

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
		this.topTrans=new ArrayList<Map<String,Object>>();
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
