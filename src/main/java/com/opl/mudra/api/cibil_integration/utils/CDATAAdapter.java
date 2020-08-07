package com.opl.mudra.api.cibil_integration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class CDATAAdapter extends XmlAdapter<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(CDATAAdapter.class);
	
	@Override
    public String marshal(String v) throws Exception {
		return "<![CDATA[" + v + "]]>";
    }
 
    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }
}
