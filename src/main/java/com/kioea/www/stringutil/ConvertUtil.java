package com.kioea.www.stringutil;

import java.math.BigInteger;

import com.kioea.www.Constant;
import com.kioea.www.regexutil.PatternUtil;

/**
 * 将value转换类型,将String的再包装后转换
 * 
 * @author: sekift
 * @time: 2015-3-4 上午10:44:14，3-25
 * @version:
 */
public class ConvertUtil {

	/**
	 * 将value对象转成long
	 * 
	 * @param value
	 *            -- 数字类型的对象
	 * @return -- long值
	 */
	public static long toLong(Object value) {

		long val = 0;
		if (Long.class.isInstance(value)) {
			val = (Long) value;
		} else if (Integer.class.isInstance(value)) {
			Integer i = (Integer) value;
			val = i.longValue();
		} else if (BigInteger.class.isInstance(value)) {
			BigInteger bi = (BigInteger) value;
			val = bi.longValue();
		} else if (Double.class.isInstance(value)) {
			Double d = (Double) value;
			val = d.longValue();
		} else if (Short.class.isInstance(value)) {
			Short s = (Short) value;
			val = s.longValue();
		} else if (value instanceof String) {
			String strValue = (String) value;
			try {
				if (StringUtil.isNullOrBlank(strValue)) {
					return val;
				}
				if (!PatternUtil.patternAll(strValue, Constant.regex_ID.INTEGER)
						&& !PatternUtil.patternAll(strValue, Constant.regex_ID.FLOAT)) {
					return val;
				}
				val = Long.valueOf(strValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 * 将value对象转成int
	 * 
	 * @param value
	 *            -- 数字类型的对象
	 * @return -- int值
	 */
	public static int toInt(Object value) {

		int val = 0;
		if (Long.class.isInstance(value)) {
			Long l = (Long) value;
			val = l.intValue();
		} else if (Integer.class.isInstance(value)) {
			Integer i = (Integer) value;
			val = i.intValue();
		} else if (BigInteger.class.isInstance(value)) {
			BigInteger bi = (BigInteger) value;
			val = bi.intValue();
		} else if (Double.class.isInstance(value)) {
			Double d = (Double) value;
			val = d.intValue();
		} else if (Short.class.isInstance(value)) {
			Short s = (Short) value;
			val = s.intValue();
		} else if (value instanceof String) {
			String strValue = (String) value;
			try {
				if (StringUtil.isNullOrBlank(strValue)) {
					return val;
				}
				if (!PatternUtil.patternAll(strValue, Constant.regex_ID.INTEGER)
						&& !PatternUtil.patternAll(strValue, Constant.regex_ID.FLOAT)) {
					return val;
				}
				val = Integer.valueOf(strValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 * 将value对象转成float
	 * 
	 * @param value
	 *            -- 数字类型的对象
	 * @return -- float值
	 */
	public static float toFloat(Object value) {

		float val = 0;
		if (Long.class.isInstance(value)) {
			Long l = (Long) value;
			val = l.floatValue();
		} else if (Integer.class.isInstance(value)) {
			Integer i = (Integer) value;
			val = i.floatValue();
		} else if (BigInteger.class.isInstance(value)) {
			BigInteger bi = (BigInteger) value;
			val = bi.floatValue();
		} else if (Double.class.isInstance(value)) {
			Double d = (Double) value;
			val = d.floatValue();
		} else if (Short.class.isInstance(value)) {
			Short s = (Short) value;
			val = s.floatValue();
		} else if (value instanceof String) {
			String strValue = (String) value;
			try {
				if (StringUtil.isNullOrBlank(strValue)) {
					return val;
				}
				if (!PatternUtil.patternAll(strValue, Constant.regex_ID.INTEGER)
						&& !PatternUtil.patternAll(strValue, Constant.regex_ID.FLOAT)) {
					return val;
				}
				val = Float.valueOf(strValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 * 将value对象转成short
	 * 
	 * @param value
	 *            -- 数字类型的对象
	 * @return -- short值
	 */
	public static short toShort(Object value) {

		short val = 0;
		if (Long.class.isInstance(value)) {
			Long l = (Long) value;
			val = l.shortValue();
		} else if (Integer.class.isInstance(value)) {
			Integer i = (Integer) value;
			val = i.shortValue();
		} else if (BigInteger.class.isInstance(value)) {
			BigInteger bi = (BigInteger) value;
			val = bi.shortValue();
		} else if (Double.class.isInstance(value)) {
			Double d = (Double) value;
			val = d.shortValue();
		} else if (Short.class.isInstance(value)) {
			Short s = (Short) value;
			val = s.shortValue();
		} else if (value instanceof String) {
			String strValue = (String) value;
			try {
				if (StringUtil.isNullOrBlank(strValue)) {
					return val;
				}
				if (!PatternUtil.patternAll(strValue, Constant.regex_ID.INTEGER)
						&& !PatternUtil.patternAll(strValue, Constant.regex_ID.FLOAT)) {
					return val;
				}
				val = Short.valueOf(strValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 * 将value对象转成double
	 * 
	 * @param value
	 *            -- 数字类型的对象
	 * @return -- double值
	 */
	public static double toDouble(Object value) {

		double val = 0;
		if (Long.class.isInstance(value)) {
			Long l = (Long) value;
			val = l.doubleValue();
		} else if (Integer.class.isInstance(value)) {
			Integer i = (Integer) value;
			val = i.doubleValue();
		} else if (BigInteger.class.isInstance(value)) {
			BigInteger bi = (BigInteger) value;
			val = bi.doubleValue();
		} else if (Double.class.isInstance(value)) {
			Double d = (Double) value;
			val = d.doubleValue();
		} else if (Short.class.isInstance(value)) {
			Short s = (Short) value;
			val = s.doubleValue();
		} else if (value instanceof String) {
			String strValue = (String) value;
			try {
				if (StringUtil.isNullOrBlank(strValue)) {
					return val;
				}
				if (!PatternUtil.patternAll(strValue, Constant.regex_ID.INTEGER)
						&& !PatternUtil.patternAll(strValue, Constant.regex_ID.FLOAT)) {
					return val;
				}
				val = Double.valueOf(strValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 * 将value对象转成boolean
	 * 
	 * @param value
	 *            -- Boolean类型的对象
	 * @return -- boolean值
	 */
	public static boolean toBoolean(Object value) {

		if (null == value) {
			return false;
		} else if (Boolean.class.isInstance(value)) {
			Boolean b = (Boolean) value;
			return b.booleanValue();
		} else {
			String s = value.toString();
			return !"false".equals(s) && !"0".equals(s);
		}
	}

}
