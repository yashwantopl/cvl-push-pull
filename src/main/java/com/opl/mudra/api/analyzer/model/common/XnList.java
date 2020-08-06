package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Xns")
@XmlAccessorType(XmlAccessType.FIELD)
public class XnList {
	@XmlElement(name = "Xn")
	private List<Xn> xn;

	public List<Xn> getXns() {
		return xn;
	}

	public void setXns(List<Xn> xns) {
		this.xn = xns;
	}
	
	

		
	
}
