package com.kioea.www.sqlutil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 数据库使用的类
 * 
 * 防注入
 * 
 * @author:sekift
 * @time:2015-3-26 下午02:15:26
 * @version:
 */
public class SqlUtil {

	/**
	 * data format
	 */
	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static ThreadLocal threadlocal = new ThreadLocal() {
		@Override
		protected Object initialValue() {
			return new SimpleDateFormat(DATE_FORMAT);
		}
	};

	/**
	 * 设置date format
	 * 
	 * @param format
	 *            -- date format
	 */
	public static void setDateFormat(String format) {
		DATE_FORMAT = format;
	}

	/**
	 * 返回当前线程date format。
	 * 
	 * @return
	 */
	public static DateFormat getDateFormat() {
		return (DateFormat) threadlocal.get();
	}

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串
	 */
	private static String sqlValue(String value) {
		if (null == value) {
			return "''";
		}
		String v = value.trim();
		int vs = v.length();
		StringBuilder sb = new StringBuilder(2 + vs * 2);
		char c = 0;
		sb.append('\'');
		for (int i = 0; i < vs; i++) {
			c = v.charAt(i);
			// 防止sql注入处理，替换'为'',替换\为\\
			if ('\'' == c) {
				sb.append('\'');
				sb.append('\'');
			} else if ('\\' == c) {
				sb.append('\\');
				sb.append('\\');
			} else {
				sb.append(c);
			}
		}
		sb.append('\'');
		return sb.toString();
	}

	/**
	 * 拼接SQL语法的字段字符串值，默认日期格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串
	 */
	private static String sqlValue(Date value) {
		return "'" + getDateFormat().format(value) + "'";
	}

	public static Date parseObject(String value) {
		try {
			return (Date) getDateFormat().parseObject(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 * @param simpleDateFormat
	 *            -- 自定义日期格式
	 * @return -- SQL片段字符串
	 */
	public static String sqlValue(Date value, SimpleDateFormat simpleDateFormat) {
		return "'" + simpleDateFormat.format(value) + "'";
	}

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串
	 */
	private static String sqlValue(Timestamp value) {
		return "'" + value + "'";
	}

	/**
	 * 拼接SQL语法的字段字符串值，适用于基本数据类型
	 * 
	 * @param value
	 * @return
	 */
	private static <T> String sqlValuePrimitive(T value) {
		return value.toString();
	}

	/**
	 * 拼接SQL语法的字段字符串值，适用于数组类型
	 * 
	 * @param value
	 * @return
	 */
	private static <T> String sqlValueArray(T[] value) {
		if (null == value) {
			return "''";
		}
		StringBuilder sql = new StringBuilder(64 + value.length * 32);
		for (int i = 0; i < value.length; i++) {
			sql.append(sqlValue(value[i]));
			if (i < value.length - 1) {
				sql.append(",");
			}
		}
		return sql.toString();
	}

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 *            <p>
	 *            注意：如果字段为datetime类型，object
	 *            value不能为null，因为通过jdbc访问mysql时，datetime类型值为''时，会抛出异常
	 * @return -- SQL片段字符串，如果value为null，返回字符串：''
	 */
	public static String sqlValue(Object value) {
		if (null == value) {
			return "''";
		} else if (value instanceof String) {
			return sqlValue((String) value);
		} else if (value instanceof Date) {
			return sqlValue((Date) value);
		} else if (value instanceof Timestamp) {
			return sqlValue((Timestamp) value);
		} else if (value instanceof Integer || value instanceof Long || value instanceof Short
				|| value instanceof Float || value instanceof Double) {
			// 基本数字类型
			return sqlValuePrimitive(value);
		} else if (value instanceof List) {
			return sqlValueArray(((List) value).toArray());
		} else if (value.getClass().isArray()) {
			// 数组类型，（基本数据类型无法进行autoboxing,需要进行额外的处理）
			Class ct = value.getClass().getComponentType();
			if (ct == String.class) {
				return sqlValueArray(String[].class.cast(value));
			} else if (ct == int.class) {
				return sqlValueArray(boxedPrimitiveArray((int[]) value));
			} else if (ct == long.class) {
				return sqlValueArray(boxedPrimitiveArray((long[]) value));
			} else if (ct == short.class) {
				return sqlValueArray(boxedPrimitiveArray((short[]) value));
			} else if (ct == float.class) {
				return sqlValueArray(boxedPrimitiveArray((float[]) value));
			} else if (ct == double.class) {
				return sqlValueArray(boxedPrimitiveArray((double[]) value));
			}
			// 默认转成Object对象数组
			return sqlValueArray((Object[]) value);
		} else {
			return "'" + value.toString() + "'";
		}

	}

	/**
	 * boxed int array
	 * 
	 * @param array
	 * @return
	 */
	private static Integer[] boxedPrimitiveArray(int[] array) {
		Integer[] result = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	/**
	 * boxed long array
	 * 
	 * @param array
	 * @return
	 */
	private static Long[] boxedPrimitiveArray(long[] array) {
		Long[] result = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	/**
	 * boxed short array
	 * 
	 * @param array
	 * @return
	 */
	private static Short[] boxedPrimitiveArray(short[] array) {
		Short[] result = new Short[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	/**
	 * boxed float array
	 * 
	 * @param array
	 * @return
	 */
	private static Float[] boxedPrimitiveArray(float[] array) {
		Float[] result = new Float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	/**
	 * boxed double array
	 * 
	 * @param array
	 * @return
	 */
	private static Double[] boxedPrimitiveArray(double[] array) {
		Double[] result = new Double[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	public static void main(String args[]) {
		String str = "中国\\\\'''";
		System.out.println(sqlValue(str));

	}
}
