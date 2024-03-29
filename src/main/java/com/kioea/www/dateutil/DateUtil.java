package com.kioea.www.dateutil;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 提供跟日期相关的一些通用方法。
 *
 * @author:sekift
 * @time:2015-3-2 上午10:36:23
 * @version:
 */
public class DateUtil {
    /**
     * 时间格式
     */
    public final static String TIME_FORMAT = "HH:mm:ss:SS";

    /**
     * 缺省短日期格式
     */
    public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省短日期格式
     */
    public final static String DEFAULT_SHORT_DATE_FORMAT_ZH = "yyyy年M月d日";

    /**
     * 缺省长日期格式
     */
    public final static String DEFAULT_LONG_DATE_FORMAT =
            DEFAULT_SHORT_DATE_FORMAT + " " + TIME_FORMAT;

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd）。
     */
    public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd HH:mm:ss:SS）。
     */
    public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";

    /**
     * Java能支持的最小的Timestamp。
     */
    public final static Timestamp JAVA_MIN_TIMESTAMP = convertStrToTimestamp(JAVA_MIN_LONG_DATE_STR);
    /**
     * 默认的时间段显示格式
     */
    public final static String DEFAULT_PERIOD_FORMAT = "{0}天{1}小时{2}分钟";

    /**
     * Java能支持的最大日期字符串（yyyy-MM-dd）。
     */
    public final static String JAVA_MAX_SHORT_DATE_STR = "9999-12-31";

    /**
     * 把日期对象加减年、月、日后得到新的日期对象
     *
     * @param datepart yy-年、MM-月、dd-日
     * @param number   加减因子
     * @param date     需要加减年、月、日的日期对象
     * @return Date 新的日期对象
     */
    public static Date addDate(String datepart, int number, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if ("yy".equals(datepart)) {
            cal.add(Calendar.YEAR, number);
        } else if ("MM".equals(datepart)) {
            cal.add(Calendar.MONTH, number);
        } else if ("dd".equals(datepart)) {
            cal.add(Calendar.DATE, number);
        } else {
            throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值：" +
                    datepart);
        }

        return cal.getTime();
    }

    /**
     * 比较时间大小 默认和当前时间比较
     *
     * @param time1
     * @return
     */
    public static boolean compareTime(String time1) {
        return compareTime(time1, getCurrDateStr(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 比较时间大小
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compareTime(String time1, String time2) {
        return compareTime(time1, time2, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 比较时间大小
     *
     * @param time1
     * @param time2
     * @param dateFormat
     * @return 如果time1小于time2返回true, 否则返回false
     */
    public static boolean compareTime(String time1, String time2, String dateFormat) {
        SimpleDateFormat t1 = new SimpleDateFormat(dateFormat);
        SimpleDateFormat t2 = new SimpleDateFormat(dateFormat);
        try {
            Date d1 = t1.parse(time1);
            Date d2 = t2.parse(time2);
            return d1.before(d2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将日期类型转换成指定格式的日期字符串
     *
     * @param date       待转换的日期
     * @param dateFormat 日期格式字符串
     * @return String
     */
    public static String convertDateToStr(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 将指定格式的字符串转换成日期类型
     *
     * @param dateStr    待转换的日期字符串
     * @param dateFormat 日期格式字符串
     * @return Date
     */
    public static Date convertStrToDate(String dateStr, String dateFormat) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("DateUtil.convertStrToDate():" +
                    e.getMessage());
        }
    }

    /**
     * 将指定格式的字符串转换成日期类型
     *
     * @param dateStr    待转换的日期字符串
     * @param dateFormat 日期格式字符串
     * @param locale     本地化对象
     * @return Date
     */
    public static Date convertStrToDate(String dateStr, String dateFormat, Locale locale) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("DateUtil.convertStrToDate():" +
                    e.getMessage());
        }
    }

    /**
     * 把字符串转换为Timestamp类型，对于短日期格式，自动把时间设为系统当前时间。
     *
     * @return Timestamp
     * @see #convertStrToTimestamp(String, boolean)
     */
    public static Timestamp convertStrToTimestamp(String dateStr) {
        return convertStrToTimestamp(dateStr, false);
    }

    /**
     * 把字符串转换为Timestamp类型。
     *
     * @param dateStr     - 日期字符串，只支持"yyyy-MM-dd"和"yyyy-MM-dd HH:mm:ss:SS"两种格式。
     *                    如果为"yyyy-MM-dd"，系统会自动取得当前时间补上。
     * @param addZeroTime - 当日期字符串为"yyyy-MM-dd"这样的格式时，addZeroTime为true表示
     *                    用0来设置HH:mm:ss:SS，否则用当前Time来设置。
     * @return Timestamp
     */
    private static Timestamp convertStrToTimestamp(String dateStr,
                                                   boolean addZeroTime) {
        if (dateStr == null) {
            return null;
        }

        String dStr = dateStr.trim();
        if (dStr.indexOf(" ") == -1) {
            if (addZeroTime) {
                dStr = dStr + " 00:00:00:00";
            } else {
                dStr = dStr + " " + getCurrDateStr(DateUtil.TIME_FORMAT);
            }
        }

        Date utilDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                DEFAULT_LONG_DATE_FORMAT);

        try {
            utilDate = simpleDateFormat.parse(dStr);
        } catch (Exception ex) {
            throw new RuntimeException("DateUtil.convertStrToTimestamp(): "
                    + ex.getMessage());
        }

        return new Timestamp(utilDate.getTime());
    }

    /**
     * 把字符串转换为Timestamp类型，对于短日期格式，自动把时间设为0。
     *
     * @return Timestamp
     * @see #convertStrToTimestamp(String, boolean)
     */
    public static Timestamp convertStrToTimestampZero(String dateStr) {
        return convertStrToTimestamp(dateStr, true);
    }

    /**
     * 把输入的时间转换成时间段显示
     *
     * @param period 单位为妙
     * @return 默认格式为"d天h小时m分钟"
     */
    public static String convertToPeriod(long period) {
        long dayUnit = 24 * 60 * 60L;
        long hourUnit = 60 * 60L;
        long minUnit = 60L;
        String result = MessageFormat.format(DEFAULT_PERIOD_FORMAT, (period / dayUnit), (period % dayUnit / hourUnit), (period % hourUnit / minUnit));
        return result;
    }

    /**
     * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是
     * 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
     *
     * @param datepart  两位的格式字符串，yy表示年，MM表示月，dd表示日
     * @param startdate 开始日期
     * @param enddate   结束日期
     * @return double 如果enddate>startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
     */
    public static double dateDiff(String datepart, Date startdate, Date enddate) {
        if (datepart == null || "".equals(datepart)) {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" +
                    datepart);
        }

        double days = (double) (enddate.getTime() - startdate.getTime()) /
                (60 * 60 * 24 * 1000);

        if ("yy".equals(datepart)) {
            days = days / 365;
        } else if ("MM".equals(datepart)) {
            days = days / 30;
        } else if ("dd".equals(datepart)) {
            return days;
        } else {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" +
                    datepart);
        }
        return days;
    }

    /**
     * 返回当前时间，默认格式为yyyy-MM-dd HH:mm:ss,
     *
     * @return
     */
    public static String getCurrDateStr() {
        return getCurrDateStr("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * <p>
     * 取得当前日期，并将其转换成格式为"dateFormat"的字符串
     * 例子：假如当前日期是 2003-09-24 9:19:10，则：
     * <pre>
     * getCurrDateStr("yyyyMMdd")="20030924"
     * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
     * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
     * </pre>
     * </p>
     *
     * @param dateFormat String 日期格式字符串
     * @return String
     */
    public static String getCurrDateStr(String dateFormat) {
        return convertDateToStr(new Date(), dateFormat);
    }

    /**
     * 得到系统当前时间的Timestamp对象
     *
     * @return 系统当前时间的Timestamp对象
     */
    public static Timestamp getCurrTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 转为开始时间
     *
     * @param fieldValue
     * @return 格式如：1970-01-01 00:00:00
     */
    public static String toBeginDate(String fieldValue) {
        if (fieldValue == null || "".equals(fieldValue)) {
            return "";
        }
        String result = "";
        fieldValue += " 00:00:00";
        result = fieldValue;
        return result;
    }

    /**
     * 转为结束时间
     *
     * @param fieldValue
     * @return 格式如:1970-01-01 23:59:59
     */
    public static String toEndDate(String fieldValue) {
        if (fieldValue == null || "".equals(fieldValue)) {
            return "";
        }
        String result = "";
        fieldValue += " 23:59:59";
        result = fieldValue;
        return result;
    }

    /**
     * 获取标准格式的日期时间显示字符串
     *
     * @param timestampStr JDBC Timestamp格式字符串 格式:yyyy-MM-dd HH:mm:ss.SSS
     * @return 标准格式的日期时间显示字符串，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getStandardDatetimeStr(String timestampStr) {
        if (timestampStr == null || "".equals(timestampStr.trim())) {
            return "";
        }
        String result = "";
        try {
            Timestamp timestamp = Timestamp.valueOf(timestampStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = sdf.format(timestamp);
        } catch (Exception e) {
            throw new RuntimeException("DateUtil.getStandardDatetimeStr()失败:"
                    + e.getMessage());
        }
        return result;
    }

    /**
     * 获取时间戳字符串的毫秒表示，字符串格式 “yyyy-MM-dd HH:mm:ss”
     *
     * @param dateStr 时间戳字符串的毫秒表示，
     */
    public static long getTimeMillis(String dateStr) {
        return convertStrToDate(dateStr, "yyyy-MM-dd HH:mm:ss").getTime();
    }
}

