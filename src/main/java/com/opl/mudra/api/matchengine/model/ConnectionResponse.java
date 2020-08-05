package com.opl.mudra.api.matchengine.model;

import java.math.BigInteger;
import java.util.List;

public class ConnectionResponse {

	List<BigInteger> suggetionByMatchesList;
	
	List<BigInteger> suggetionList;

	public List<BigInteger> getSuggetionByMatchesList() {
		return suggetionByMatchesList;
	}

	public void setSuggetionByMatchesList(List<BigInteger> suggetionByMatchesList) {
		this.suggetionByMatchesList = suggetionByMatchesList;
	}

	public List<BigInteger> getSuggetionList() {
		return suggetionList;
	}

	public void setSuggetionList(List<BigInteger> suggetionList) {
		this.suggetionList = suggetionList;
	}

	
		
	
	
}
