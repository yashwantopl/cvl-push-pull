/**
 * 
 */
package com.opl.mudra.api.thirdparty.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author sanket
 *
 */
public class XMLUtils {
	private XMLUtils()
	{
// Do nothing because of X and Y.
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
