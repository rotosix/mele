package com.mele.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class ItvJsonUtil {
	public static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 将json字符串反序列号成对象
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T readValue(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

   public static <T> T readValue(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

	/**
	 * 将对象序列号json字符串
	 * @param obj
	 * @return
	 */
	public static String writeValue(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static String RESPONSE(String code, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		return writeValue(map);
	}

	
	/**
	 * 返回分页信息时用到
	 * */
	public static String RESPONSEPAGED(String code, Object data,Integer start,Integer total,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		map.put("total", total);//总量
		map.put("limit", pageSize);//每页显示个数
		map.put("start", start);//limit 起始位置
		return writeValue(map);
	}
	public static String RESPONSERANGE(String code, Object data,Integer start,Integer total,Integer end){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		map.put("total", total);//总量
		map.put("end", end);//每页显示个数
		map.put("start", start);//limit 起始位置
		return writeValue(map);
	}
	public static String RESPONSEPAGED(String chnId,String code, Object data,Integer start,Integer total,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chnId", chnId);
		map.put("code", code);
		map.put("data", data);
		map.put("total", total);//总量
		map.put("limit", pageSize);//每页显示个数
		map.put("start", start);//limit 起始位置
		return writeValue(map);
	}
}
