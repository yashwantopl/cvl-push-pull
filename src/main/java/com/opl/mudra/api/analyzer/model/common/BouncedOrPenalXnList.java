package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "BouncedOrPenalXns")
@XmlAccessorType(XmlAccessType.FIELD)
public class BouncedOrPenalXnList {
	@XmlElement(name = "BouncedOrPenalXn")
	private List<BouncedOrPenalXn> bouncedOrPenalXns;

	public List<BouncedOrPenalXn> getBouncedOrPenalXns() {
		return bouncedOrPenalXns;
	}

	public void setBouncedOrPenalXns(List<BouncedOrPenalXn> bouncedOrPenalXns) {
		this.bouncedOrPenalXns = bouncedOrPenalXns;
	}

	public BouncedOrPenalXnList(List<BouncedOrPenalXn> bouncedOrPenalXns) {
		this.bouncedOrPenalXns = bouncedOrPenalXns;
	}

	public BouncedOrPenalXnList() {
		super();
	}

	
	
	
	
	
}
