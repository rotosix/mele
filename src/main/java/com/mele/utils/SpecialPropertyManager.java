package com.mele.utils;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SpecialPropertyManager {
	private static final String CONFIG = "specialconfig";
	private static final ResourceBundle RESOURCE_BUNDLE =ResourceBundle.getBundle(CONFIG);
	/**
	 * 读取默认的specialconfig.properties文件
	 * @param key 
	 * @return value
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			throw new RuntimeException( "! config : "+ key + '!');
		}
	}
	
	public static Integer getInt(String key) {
		try {
			String val = RESOURCE_BUNDLE.getString(key);
			return Integer.valueOf(val);
		} catch (MissingResourceException e) {
			throw new RuntimeException( "! config : "+ key + '!');
		}
	}

	/**
	 * 
	 * @Title: getLong 
	 * 
	 * @param key
	 * @return
	 * @return: Long
	 */
    public static Long getLong(String key) {
        try {
            String val = RESOURCE_BUNDLE.getString(key);
            return Long.valueOf(val);
        } catch (MissingResourceException e) {
            throw new RuntimeException( "! config : "+ key + '!');
        }
    }

    /**
	 * 
	 * @author: James.wu
	 * @date: 2013-12-31 上午11:55:21
	 * @Title: getFloat 
	 * 
	 * @param key
	 * @return
	 * @return: Float
	 */
    public static Float getFloat(String key) {
        try {
            String val = RESOURCE_BUNDLE.getString(key);
            return Float.valueOf(val);
        } catch (MissingResourceException e) {
            throw new RuntimeException( "! config : "+ key + '!');
        }
    }	

    /**
     * 
     * @author: James.wu
     * @date: 2013-12-31 上午11:55:21
     * @Title: getDouble 
     * 
     * @param key
     * @return
     * @return: Double
     */
    public static Double getDouble(String key) {
        try {
            String val = RESOURCE_BUNDLE.getString(key);
            return Double.valueOf(val);
        } catch (MissingResourceException e) {
            throw new RuntimeException( "! config : "+ key + '!');
        }
    }

    /**
     * 
     * @Title: getBoolean 
     * 
     * @param key
     * @return
     * @return: Boolean
     */
    public static Boolean getBoolean(String key) {
        try {
            String val = RESOURCE_BUNDLE.getString(key);
            return Boolean.valueOf(val);
        } catch (MissingResourceException e) {
            throw new RuntimeException( "! config : "+ key + '!');
        }
    }

    /**
	 * 读取指定为rb.properties文件
	 * @param rb property 文件，文件名为 rb.properties
	 * @param key 
	 * @return value
	 */
	public static String getRB(String rb, String key){
		try {
			String val = ResourceBundle.getBundle(rb).getString(key);
			return val;
		} catch (MissingResourceException e) {
			throw new RuntimeException( '!' + rb + ":" + key + '!');
		}
	}
	
	public static Map<String, String> getStringMap(String baseName) {
		Map<String, String> m = new HashMap<String, String>();
		ResourceBundle rb = ResourceBundle.getBundle(baseName);
		Enumeration<String> keys = rb.getKeys();
		String temp;
		while (keys.hasMoreElements()) {
			temp = keys.nextElement();
			m.put(temp, rb.getString(temp));
		}
		ResourceBundle.clearCache();
		return m;
	}
}
