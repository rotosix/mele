package com.mele.base.redis.utils;

import java.math.BigInteger;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class RedisDataUtil {

	public static Integer getIntVal(String val) {

	    if (StringUtils.isNotBlank(val)) {
			if (val.equalsIgnoreCase("null")) {
				return null;
			} else {
				return Integer.valueOf(val);
			}
		} else {
			return null;
		}
	}

	public static BigInteger getBigIntVal(String val) {

	    if (StringUtils.isNotBlank(val)) {
			if (val.equalsIgnoreCase("null")) {
				return null;
			} else {
				return new BigInteger(val);
			}
		} else {
			return null;
		}
	}

	public static String getStrVal(String val) {

	    if (StringUtils.isNotBlank(val)) {
			if (val.equalsIgnoreCase("null")) {
				return null;
			} else {
				return val;
			}
		} else {
			return null;
		}
	}

	public static Double getDoubleVal(String val) {

	    if (StringUtils.isNotBlank(val)) {
			if (val.equalsIgnoreCase("null")) {
				return null;
			} else {
				return Double.valueOf(val);
			}
		} else {
			return null;
		}
	}

	public static Boolean isExpired(int timeStamp, int days) {

	    Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, days * -1);

		if ((Double.valueOf(cal.getTimeInMillis()/1000d)).intValue() > timeStamp) {
			return true;
		} else {
			return false;
		}
	}

	public static String generateContactSource(String fid,String source) {
	    return  String.format("%s%02d", fid, Integer.parseInt(source));
	}
}
