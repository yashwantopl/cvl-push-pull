package com.opl.mudra.api.loans.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by win7 on 11/16/2016.
 */
public class MultipleJSONObjectHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleJSONObjectHelper() {
		// Do nothing because of X and Y.
	}

	public static <T> T getObject(String data, String key, Class<?> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(data);
		return (T) mapper.convertValue(node.get(key), clazz);
	}

	public static List getListOfObjects(String data, String key, Class<?> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		if(key != null){
			JsonNode node = mapper.readTree(data);
			return mapper.readValue(node.get(key).toString(), mapper.getTypeFactory().constructCollectionType(List.class, clazz));			
		}else{
			return mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		}
	}
	
	public static <T> T getObjectFromString(String data, Class<?> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T)mapper.readValue(data, clazz);
    }
	
	public static <T> T getObjectFromMap(Map map, Class<?> clazz) throws IOException {
		final ObjectMapper mapper = new ObjectMapper(); // jackson's
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
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
	/*@SuppressWarnings("unchecked")
	public static <T extends List<?>> T getListFromObject(Object obj) {
	    return (T) obj;
	}*/

//	public static <T> T getListFromMap(List<Map<String, Object>> map, Class<?> clazz) throws IOException {
//		final ObjectMapper mapper = new ObjectMapper(); // jackson's
//		return mapper.readValue(map, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
//	}

}
