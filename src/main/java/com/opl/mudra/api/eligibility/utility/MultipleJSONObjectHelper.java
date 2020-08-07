package com.opl.mudra.api.eligibility.utility;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by win7 on 11/16/2016.
 */
public class MultipleJSONObjectHelper implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(MultipleJSONObjectHelper.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleJSONObjectHelper() {
		// Do nothing because of X and Y.
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(String data, String key, Class<?> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (key == null) {
			return (T) mapper.convertValue(data, clazz);
		} else {
			JsonNode node = mapper.readTree(data);
			return (T) mapper.convertValue(node.get(key), clazz);
		}
	}

	public static List<?> getListOfObjects(String data, String key, Class<?> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (key != null) {
			JsonNode node = mapper.readTree(data);
			String jsonString = node.get(key).toString();
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
		if (!EligibilityUtils.isListNullOrEmpty(list)) {
			final StringWriter sw = new StringWriter();
			new ObjectMapper().writeValue(sw, list);
			return sw.toString();
		} else {
			return "[]";
		}
	}
	

	public static <T> T convertJSONToObject(String response, Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		  try {
				mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
				mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   return (T)mapper.readValue(response, clazz);
		  } catch (Exception e) {
			  logger.error(EligibilityUtils.EXCEPTION + " convertJSONToObject :: " ,e);
		   return null;
	}
	}
}
