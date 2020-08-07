/**
 * 
 */
package com.opl.mudra.api.fraudanalytics.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author sanket
 *
 */

public class XMLUtils {
	private XMLUtils(){
		// nothing.
	}

	public static String toXML(Object object,Class<?> clazz) throws JAXBException{
		StringWriter writer  = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(object, writer);
		return writer.toString();	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String xmlString, Class<?> clazz) throws JAXBException{

		StringReader reader  = new StringReader(xmlString);
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller marshaller = jaxbContext.createUnmarshaller();
		return (T)marshaller.unmarshal(reader);
	}

}
