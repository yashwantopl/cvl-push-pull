package com.opl.mudra.api.cibil.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by win7 on 11/16/2016.
 */
public class MultipleJSONObjectHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MultipleJSONObjectHelper.class);

	public MultipleJSONObjectHelper() {// Do nothing because of X and Y.
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(String data, String key, Class<?> clazz,String type) throws IOException {
		if("xml".equalsIgnoreCase(type)) {
			data = XML.toJSONObject(data).toString();
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (key == null) {
			return (T) mapper.convertValue(data, clazz);
		} else {
			JsonNode node = mapper.readTree(data);
			JsonNode jsonNode = node.get(key);
			return (T) mapper.convertValue(jsonNode, clazz);
		}
	}

	public static List<?> getListOfObjects(String data, String key, Class<?> clazz) throws IOException {
		if(CibilUtils.isObjectNullOrEmpty(data)){
			return Collections.emptyList();
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (key != null) {
			JsonNode node = mapper.readTree(data);
			JsonNode jsonNode = node.get(key);
			if(CibilUtils.isObjectNullOrEmpty(jsonNode)) {
				return null;
			}
			String jsonString = jsonNode.toString();
			return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} else {
			return mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObjectFromMap(Map<?, ?> map, Class<?> clazz) throws IOException {
		final ObjectMapper mapper = new ObjectMapper(); // jackson's
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return (T) mapper.convertValue(map, clazz);
	}

	public static String getStringfromObject(Object object) throws IOException {
		if (object != null) {
			return new ObjectMapper().writeValueAsString(object);
		} else {
			return "{}";
		}
	}

	public static String getStringfromListOfObject(List<?> list) throws IOException {
		if (!CibilUtils.isListNullOrEmpty(list)) {
			final StringWriter sw = new StringWriter();
			new ObjectMapper().writeValue(sw, list);
			return sw.toString();
		} else {
			return "[]";
		}
	}

	public static String XmlToJson(String xmlString) throws IOException {
		if (!CibilUtils.isObjectNullOrEmpty(xmlString)) {
			JSONObject jObject = XML.toJSONObject(xmlString);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Object json = mapper.readValue(jObject.toString(), Object.class);
			return mapper.writeValueAsString(json);
			// XmlMapper xmlMapper = new XmlMapper();
			// JsonNode node = xmlMapper.readTree(xmlString.getBytes());
			// ObjectMapper jsonMapper = new ObjectMapper();
			// jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
			// return jsonMapper.writeValueAsString(node);
		} else {
			return "{}";
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObjectExtraConfig(String data, String key, Class<?> clazz,String type) throws IOException {
		if("xml".equalsIgnoreCase(type)) {
			data = XML.toJSONObject(data).toString();
			logger.info("data after converting XML to JSON==>" + data);
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (key == null) {
			return (T) mapper.readValue(data, clazz);
		} else {
			JsonNode node = mapper.readTree(data);
			JsonNode jsonNode = node.get(key);
			return (T) mapper.convertValue(jsonNode, clazz);
		}
	}
	
//	public static List<?> getListOfObjectsExtraConfig(String data, String key, Class<?> clazz) throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//		if (key != null) {
//			JsonNode node = mapper.readTree(data);
//			JsonNode jsonNode = node.get(key);
//			if(CibilUtils.isObjectNullOrEmpty(jsonNode)) {
//				return null;
//			}
//			String jsonString = jsonNode.toString();
//			return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
//		} else {
//			return mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObjectByReadValue(String data, String key, Class<?> clazz,String type) throws IOException {
		if("xml".equalsIgnoreCase(type)) {
			data = XML.toJSONObject(data).toString();
			logger.info("data after converting XML to JSON==>" + data);
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (key == null) {
			return (T) mapper.readValue(data, clazz);
		} else {
			JsonNode node = mapper.readTree(data);
			JsonNode jsonNode = node.get(key);
			return (T) mapper.convertValue(jsonNode, clazz);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getMapFromString(String value) throws IOException {
		if (value != null) {
			return new ObjectMapper().readValue(value,Map.class);
		} else {
			return null;
		}
	}
}
