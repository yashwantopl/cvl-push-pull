package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Top5FundsReceived")
@XmlAccessorType(XmlAccessType.FIELD)
public class Top5FundReceivedList {
	
	@XmlElement(name = "Item")
	private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Top5FundReceivedList() {
		super();
	}

	public Top5FundReceivedList(List<Item> item) {
		super();
		this.item = item;
	}
	
	
	
	
}
