package com.opl.mudra.api.user.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by win7 on 11/16/2016.
 */
@SuppressWarnings({"unchecked","rawtypes"})
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
		if(key != null){
			JsonNode node = mapper.readTree(data);
			return mapper.readValue(node.get(key).asText(), mapper.getTypeFactory().constructCollectionType(List.class, clazz));			
		}else{
			return mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		}
	}

	public static <T> T getObjectFromMap(Map map, Class<?> clazz) throws IOException {
		final ObjectMapper mapper = new ObjectMapper(); // jackson's
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return (T) mapper.convertValue(map, clazz);
	}
	
	/*@SuppressWarnings("unchecked")
	public static <T extends List<?>> T getListFromObject(Object obj) {
	    return (T) obj;
	}*/

//	public static <T> T getListFromMap(List<Map<String, Object>> map, Class<?> clazz) throws IOException {
//		final ObjectMapper mapper = new ObjectMapper(); // jackson's
//		return mapper.readValue(map, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
//	}
	
	public static <T> T convertJSONToObject(String response, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            return  mapper.readValue(response, clazz);
        } catch (Exception e) {
            return null;
        }
    }


    public static String convertObjectToString(Object value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }
}
