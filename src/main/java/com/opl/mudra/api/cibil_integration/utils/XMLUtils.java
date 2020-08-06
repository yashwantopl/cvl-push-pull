package com.opl.mudra.api.cibil_integration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author Akshay
 *
 */
public class XMLUtils {
	private XMLUtils (){
		// nothing.
	}

	private static final Logger logger = LoggerFactory.getLogger(XMLUtils.class);
	private static final String ERROR_WHILE_CONVERTING_STRING_XML_TO_OBJECT = "Error While Converting String XML to Object";

	public static String toXML(Object object, Class<?> clazz) throws JAXBException {
		boolean trueflag = true;
		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, trueflag);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, trueflag);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(object, writer);
		return writer.toString();
	}
	
	public static String toXMLWithoutFormat(Object object, Class<?> clazz) throws JAXBException {
		boolean trueflag = true;
		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, trueflag);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(object, writer);
		return writer.toString();
	}
	

	@SuppressWarnings("unchecked")
	public static <T> T toObject(String xmlString, Class<?> clazz) throws JAXBException {
		try {
			// JAXBContext jaxbContext = JAXBContext.newInstance("com.transunion"); //using
			// package is Remain to Test once the first phase gets live then will test.
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller marshaller = jaxbContext.createUnmarshaller();
			SOAPMessage message = MessageFactory.newInstance().createMessage(null,
					new ByteArrayInputStream(xmlString.getBytes()));
			return (T) JAXBIntrospector
					.getValue(marshaller.unmarshal(message.getSOAPBody().extractContentAsDocument()));
		} catch (Exception e) {
			logger.error(CibilUtils.EXCEPTION+e.getMessage(), e);
			logger.info(ERROR_WHILE_CONVERTING_STRING_XML_TO_OBJECT);
		}
		return null;
	}

	public static String getString(String xmlString,String node) throws JAXBException {
		try {
			SOAPMessage message = MessageFactory.newInstance().createMessage(null,
					new ByteArrayInputStream(xmlString.getBytes()));
			NodeList nodeList = message.getSOAPBody().extractContentAsDocument().getElementsByTagName(node);
			StringBuffer finalBuffer = new StringBuffer();
			for (int i = 0; i < nodeList.getLength(); i++) {
				StringWriter buf = new StringWriter();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
				Transformer xform = transformerFactory.newTransformer();
				xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				xform.setOutputProperty(OutputKeys.INDENT, "yes");
				xform.transform(new DOMSource(nodeList.item(i)), new StreamResult(buf));
				finalBuffer.append(buf.toString());
			}
			return finalBuffer.toString();
		} catch (Exception e) {
			logger.error(CibilUtils.EXCEPTION+e.getMessage(), e);
			logger.info(ERROR_WHILE_CONVERTING_STRING_XML_TO_OBJECT);
		}
		return null;
	}
	
	public static String getStringFromIndividual(String xmlString,String node) throws JAXBException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nodeList = doc.getElementsByTagName(node);
			StringBuffer finalBuffer = new StringBuffer();
			for (int i = 0; i < nodeList.getLength(); i++) {
				StringWriter buf = new StringWriter();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
				Transformer xform = transformerFactory.newTransformer();
				xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				xform.setOutputProperty(OutputKeys.INDENT, "yes");
				xform.transform(new DOMSource(nodeList.item(i)), new StreamResult(buf));
				finalBuffer.append(buf.toString());
			}
			return finalBuffer.toString();
		} catch (Exception e) {
			logger.error(CibilUtils.EXCEPTION+e.getMessage(), e);
			logger.info(ERROR_WHILE_CONVERTING_STRING_XML_TO_OBJECT);
		}
		return null;
	}

//	public static String getStringForIndividual(SOAPMessage message) throws JAXBException {
//		try {
//			NodeList nodeList = message.getSOAPBody().extractContentAsDocument().getElementsByTagName("DCRequest");
//			StringBuffer finalBuffer = new StringBuffer();
//			for (int i = 0; i < nodeList.getLength(); i++) {
//				StringWriter buf = new StringWriter();
//				Transformer xform = TransformerFactory.newInstance().newTransformer();
//				xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//				xform.setOutputProperty(OutputKeys.INDENT, "yes");
//				xform.transform(new DOMSource(nodeList.item(i)), new StreamResult(buf));
//				finalBuffer.append(buf.toString());
//			}
//			return finalBuffer.toString();
//		} catch (Exception e) {
//			logger.error(CibilUtils.EXCEPTION+e.getMessage(), e);
//			logger.info(ERROR_WHILE_CONVERTING_STRING_XML_TO_OBJECT);
//			logger.info("Error While Convrting String XML to Object");
//		}
//		return null;
//	}

	@SuppressWarnings("unchecked")
	public static <T> T toObject(String xmlString, String node, Class<?> clazz, boolean isList) throws JAXBException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller marshaller = jaxbContext.createUnmarshaller();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(xmlString));
			Document doc = builder.parse(src);
			if (isList) {
				// nothing.

			} else {
				return (T) marshaller.unmarshal(doc.getElementsByTagName(node).item(0), clazz);
			}

		} catch (Exception e) {
			logger.error(CibilUtils.EXCEPTION+e.getMessage(), e);
			logger.info(ERROR_WHILE_CONVERTING_STRING_XML_TO_OBJECT);
		}
		return null;
	}

	public static <T> Object toObjectForIndividual(String xml, Class<?> clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller marshaller = jaxbContext.createUnmarshaller();
		return marshaller.unmarshal(new StringReader(xml));
	}
}
