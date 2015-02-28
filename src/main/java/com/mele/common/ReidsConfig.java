package com.mele.common;

import org.apache.commons.lang.math.NumberUtils;

import com.mele.utils.PropertyManager;

public class ReidsConfig {
	/**
	 * 分页默认每页大小
	 */
	public static final int DEFAULT_PAGE_SIZE = NumberUtils.toInt(PropertyManager.getString("default.page.size"));

	/**
	 * 分页默认页码
	 */
	public static final int DEFAULT_PAGE_NO = NumberUtils.toInt(PropertyManager.getString("default.page.no"));
	
	
}
