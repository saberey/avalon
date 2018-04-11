package com.avalon.ms.common.util;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	    /**
	     * 小写
	     */
	    public static final StringFormatConstants LOWER_CASE = StringFormatConstants.LOWER_CASE;

	    /**
	     * 大写
	     */
	    public static final StringFormatConstants UPPER_CASE = StringFormatConstants.UPPER_CASE;

	    /**
	     * 字符串处理常量枚举
	     * 
	     * 
	     */
	    private enum StringFormatConstants {

	        /**
	         * 小写
	         */
	        LOWER_CASE,

	        /**
	         * 大写
	         */
	        UPPER_CASE;
	    }

	    /**
	     * 字符串格式枚举
	     * 
	     * 
	     */
	    public enum StringFormatType {

	        /**
	         * 空值
	         */
	        NULL("#,##0.00"),

	        /**
	         * 日期格式：2009年01月01日->2009/01/01 00:00:00
	         */
	        DATE_FORMAT_DATETIME("yyyy/MM/dd HH:mm:ss"),
	        /**
	         * 日期格式：2009年01月01日 00:00:00->00:00:00
	         */
	        DATE_FORMAT_TIME("HH:mm:ss"),
	        /**
	         * 日期格式：2009年01月01日->2009年01月01日
	         */
	        DATE_FORMAT_YEAR_MON_DAY("yyyy年MM月dd日"),

	        /**
	         * 日期格式：2009年01月01日->2009年01月01日
	         */
	        DATE_FORMAT_YEAR_MON_DAY_SINGLE("yyyy年M月d日"),

	        /**
	         * 日期格式：2009年01月01日->01月01日
	         */
	        DATE_FORMAT_MON_DAY("MM月dd日"),
	        /**
	         * 日期格式：2009年01月01日->09年01月
	         */
	        DATE_FORMAT_YEAR_MON("yy年MM月"),
	        /**
	         * 日期格式：2009年01月01日->2009年01月
	         */
	        DATE_FORMAT_FYEAR_MON("yyyy年MM月"),
	        /**
	         * 日期格式：2009年01月01日->09年01月
	         */
	        DATE_FORMAT_TYEAR_MON("yy年MM月"),
	        /**
	         * 日期格式：2009年01月01日->09/01/01
	         */
	        DATE_FORMAT_YY_MM_DD("yy/MM/dd"),

	        /**
	         * 日期格式：2009年01月01日->09/1/1
	         */
	        DATE_FORMAT_YY_M_D("yy/M/d"),

	        /**
	         * 日期格式：2009年01月01日->2009/01/01
	         */
	        DATE_FORMAT_YYYY_MM_DD("yyyy/MM/dd"),

	        /**
	         * 日期格式：2009年01月01日->2009/1/1
	         */
	        DATE_FORMAT_YYYY_M_D("yyyy/M/d"),

	        /**
	         * 日期格式：2009年01月01日->09/01/01
	         */
	        DATE_FORMAT_YY__MM__DD("yy-MM-dd"),

	        /**
	         * 日期格式：2009年01月01日->09/1/1
	         */
	        DATE_FORMAT_YY__M__D("yy-M-d"),

	        /**
	         * 日期格式：2009年01月01日->2009-01-01
	         */
	        DATE_FORMAT_YYYY__MM__DD("yyyy-MM-dd"),

	        /**
	         * 日期格式：2009年01月01日->2009/1/1
	         */
	        DATE_FORMAT_YYYY__M__D("yyyy-M-d"),

	        /**
	         * 日期格式：2009年01月01日->2009/01/01 00:00:00
	         */
	        DATE_FORMAT_YYYY__MM__DD__HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

	        /**
	         * 日期格式：2009年01月01日->20090101000000
	         */
	        DATE_FORMAT_YYYY__MM__DD__HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),

	        /**
	         * 日期格式：2009年01月01日->20090101
	         */
	        DATE_FORMAT_YYYYMMDD("yyyyMMdd"),

	        /**
	         * 日期格式：2009年01月01日->200901
	         */
	        DATE_FORMAT_YYYYMM("yyyyMM"),

	        /**
	         * 日期格式：2009年01月01日->20090101000000
	         */
	        DATE_FORMAT_YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

	        /**
	         * 日期格式：2009年01月01日->20090101000000
	         */
	        DATE_FORMAT_YYYYMMDDHHMMSSS("yyyyMMddHHmmsss"),

	        /**
	         * 日期格式：2009年01月01日->200901010000000000
	         */
	        DATE_FORMAT_YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS"),

	        /**
	         * 日期格式：2009年01月->2009/01
	         */
	        DATE_FORMAT_YYYY_MM("yyyy/MM"),

	        /**
	         * 日期格式：2009年01月01日->2009/01/01 00:00
	         */
	        DATE_FORMAT_HH_MM("yyyy/MM/dd HH:mm"),

	        /**
	         * 日期格式：2009年01月/01日->01/01
	         */
	        DATE_FORMAT_MM_DD("MM/dd"),

	        /**
	         * 日期格式：2009年01月->0901
	         */
	        DATE_FORMAT_YYMM("yyMM"),

	        /**
	         * 日期格式：2009年01月02日->0102
	         */
	        DATE_FORMAT_MMDD("MMdd"),

	        /**
	         * 日期格式：2009年01月02日->2009-01-01 00:00
	         */
	        DATE_FORMAT_yyyyMMdd_HHmm("yyyy-MM-dd HH:mm"),

	        /**
	         * 日期格式：2009年01月02日->200901010000
	         */
	        DATE_FORMAT_yyyyMMddHHmm("yyyyMMddHHmm"),

	        /**
	         * 数字格式：1234567->1,234,567
	         */
	        NUMBER_FORMAT_INTEGER_NUMBER("#,##0"),

	        /**
	         * 金额格式：1234567.89->1,234,567.89
	         */
	        NUMBER_FORMAT_MONEY("#,##0.00"),

	        /**
	         * 金额格式：1234567.89->1,234,567
	         */
	        NUMBER_FORMAT_MONEY_JPY("#,##0"),

	        /**
	         * 金额格式：1234567.89->1,234,567.89000000000000
	         */
	        NUMBER_FORMAT_FOURTEEN_MONEY("#,##0.00############"),

	        /**
	         * 金额格式：1234567.8->1,234,567.8
	         */
	        NUMBER_FORMAT_MONEY_NUMBER("#,##0.0"),

	        /**
	         * 数字格式：1234567.89->1234567.89
	         */
	        NUMBER_FORMAT_NUMBER("0.00"),

	        /**
	         * 数字格式：1234567.89->1234567.899
	         */
	        NUMBER_FORMAT_PER_NUMBER("0.000"),

	        /**
	         * 数字格式：0.0000
	         */
	        NUMBER_FORMAT_FOUR_NUMBER("0.0000"),

	        /**
	         * 数字格式：0.00000
	         */
	        NUMBER_FORMAT_FIVE_NUMBER("0.00000"),
	        /**
	         * 数字格式：0.000000
	         */
	        NUMBER_FORMAT_SIX_NUMBER("0.000000"),
	        /**
	         * 数字格式：0.0000000
	         */
	        NUMBER_FORMAT_SEVEN_NUMBER("0.0000000"),
	        /**
	         * 数字格式：0.00000000
	         */
	        NUMBER_FORMAT_EIGHT_NUMBER("0.00000000"),

	        /**
	         * 数字格式：0.00000000000000
	         */
	        NUMBER_FORMAT_FOURTEEN_NUMBER("0.00############"),

	        /**
	         * 数字格式：0.00#############
	         */
	        NUMBER_FORMAT_FIFTEEN_NUMBER("0.00#############"),

	        /**
	         * 数字格式：1234567.89->1234567.89
	         */
	        NUMBER_FORMAT_NORMAL_NUMBER("0"),

	        /**
	         * 数字格式：9->09
	         */
	        NUMBER_FORMAT_00_NUMBER("00");

	        /**
	         * 字符串格式
	         */
	        private String formatString;

	        /**
	         * 构造函数
	         * 
	         * @param formatString
	         *            字符串格式
	         */
	        StringFormatType(String formatString) {
	            this.formatString = formatString;
	        }

	        /**
	         * 返回字符串格式
	         * 
	         * @return 字符串格式
	         */
	        public String format() {
	            return formatString;
	        }

	    }

	    /**
	     * 空字符串
	     */
	    private static final String       EMPTY_STRING        = "";
	    /**
	     * 单个半角空格字符串
	     */
	    public static final String        HALF_SPACE_STRING   = " ";

	    /**
	     * 全角字符
	     */
	    private static final String       WIDECHARS           = "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ"
	                                                            + "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ１２３４５６７８９０";
	    /**
	     * 半角字符
	     */
	    private static final String       CHARS               = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                                            + "abcdefghijklmnopqrstuvwxyz1234567890";

	    /**
	     * 全角空格
	     */
	    private static final char         FULL_SPACE          = 12288;

	    /**
	     * 半角空格
	     */
	    private static final char         HALF_SPACE          = 32;

	    /**
	     * 括号
	     */
	    private static final String       PUNC_BRACKET        = "[";

	    /**
	     * 转义括号
	     */
	    private static final String       ESCAPE_BRACKET      = "[[]";

	    /**
	     * 下划线
	     */
	    private static final String       PUNC_UNDERLINE      = "_";

	    /**
	     * 转义下划线
	     */
	    private static final String       ESCAPE_UNDERLINE    = "[_]";

	    /**
	     * 百分号
	     */
	    private static final String       PUNC_PERCENT        = "%";

	    /**
	     * 转义百分号
	     */
	    private static final String       ESCAPE_PERCENT      = "[%]";

	    /**
	     * 单引号
	     */
	    private static final String       PUNC_SINGLE_QUOTE   = "'";

	    /**
	     * 转义单引号
	     */
	    private static final String       ESCAPE_SINGLE_QUOTE = "''";

	    /**
	     * 数字
	     */
	    private static final String       NUMBER_REGEX        = "[0-9]*";
	    
	    
	    /**
	     * 竖线
	     */
	    private static final String       VERTICAL_LINE       = "[|]";

	    /***
	     * mail
	     */
	    private static final String       MAIL_REGEX          = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	    public static final String        HHMM_REGEX          = "^(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})$";

	    private static final String       SPACE_REGEX         = ".*\\s.*";
	    /**
	     * 日期格式
	     */
	    private static StringFormatType[] dateParsePattern;

	    /**
	     * 金额格式
	     */
	    private static StringFormatType[] numberParsePattern;

	    static {
	        dateParsePattern = new StringFormatType[] { StringFormatType.DATE_FORMAT_DATETIME,
	            StringFormatType.DATE_FORMAT_TIME, StringFormatType.DATE_FORMAT_YEAR_MON_DAY,
	            StringFormatType.DATE_FORMAT_YEAR_MON_DAY_SINGLE, StringFormatType.DATE_FORMAT_MON_DAY,
	            StringFormatType.DATE_FORMAT_YEAR_MON, StringFormatType.DATE_FORMAT_FYEAR_MON,
	            StringFormatType.DATE_FORMAT_TYEAR_MON, StringFormatType.DATE_FORMAT_YY_MM_DD,
	            StringFormatType.DATE_FORMAT_YY_M_D, StringFormatType.DATE_FORMAT_YYYY_MM_DD,
	            StringFormatType.DATE_FORMAT_YYYY_M_D, StringFormatType.DATE_FORMAT_YY__MM__DD,
	            StringFormatType.DATE_FORMAT_YY__M__D, StringFormatType.DATE_FORMAT_YYYY__MM__DD,
	            StringFormatType.DATE_FORMAT_YYYY__M__D,
	            StringFormatType.DATE_FORMAT_YYYY__MM__DD__HH_MM_SS,
	            StringFormatType.DATE_FORMAT_YYYY__MM__DD__HH_MM_SS_SSS,
	            StringFormatType.DATE_FORMAT_YYYYMMDD, StringFormatType.DATE_FORMAT_YYYYMM,
	            StringFormatType.DATE_FORMAT_YYYYMMDDHHMMSS,
	            StringFormatType.DATE_FORMAT_YYYYMMDDHHMMSSS,
	            StringFormatType.DATE_FORMAT_YYYYMMDDHHMMSSSSS, StringFormatType.DATE_FORMAT_YYYY_MM,
	            StringFormatType.DATE_FORMAT_HH_MM, StringFormatType.DATE_FORMAT_MM_DD,
	            StringFormatType.DATE_FORMAT_YYMM, StringFormatType.DATE_FORMAT_MMDD,
	            StringFormatType.DATE_FORMAT_yyyyMMdd_HHmm, StringFormatType.DATE_FORMAT_yyyyMMddHHmm };

	        numberParsePattern = new StringFormatType[] {
	            StringFormatType.NUMBER_FORMAT_INTEGER_NUMBER, StringFormatType.NUMBER_FORMAT_MONEY,
	            StringFormatType.NUMBER_FORMAT_NUMBER, StringFormatType.NUMBER_FORMAT_NORMAL_NUMBER,
	            StringFormatType.NUMBER_FORMAT_PER_NUMBER, StringFormatType.NUMBER_FORMAT_00_NUMBER };
	    }

	    /**
	     * 在头尾追加重复字符串
	     * 
	     * @param str
	     *            字符串
	     * @param c
	     *            重复字符
	     * @param len
	     *            重复次数
	     * @return 追加重复字符后的字符串
	     */
	    public static String append(String str, char c, int len) {
	        return rappend(lappend(str, c, len), c, len);
	    }

	    /**
	     * 将驼峰模式的字符串转换为下划线连接的字符串
	     * 
	     * @param obj
	     *            字符串变量
	     * @return 下划线连接的字符串
	     */
	    public static String camelToUnderline(String obj) {

	        assertNotNull(obj);

	        // 获取按驼峰模式分割的子字符串组
	        String[] subStrings = obj.split("[A-Z]");

	        // 返回变量
	        String retval = "";

	        // 格式化为下划线连接的字符串并连接每个子字符串
	        for (int i = 0; i < subStrings.length - 1; i++) {
	            int idx = obj.indexOf(subStrings[i], retval.length() - i) + subStrings[i].length();
	            retval += subStrings[i].toUpperCase() + PUNC_UNDERLINE + obj.substring(idx, idx + 1);
	        }

	        retval += subStrings[subStrings.length - 1].toUpperCase();

	        // 去掉字符串首的下划线
	        if ((0 == retval.indexOf(PUNC_UNDERLINE)) && (1 < retval.length())) {
	            retval = retval.substring(1);
	        }

	        return retval;
	    }
	    
	    public static String[] splitBySpecify(String src,String regex){
	    	return src.split(regex);
	    }
	    
	    public static String getFieldsplitBySpecity(String src,String regex,int index){
	    	String[] content = splitBySpecify(src, regex);
	    	if(index>content.length){
	    		return null;
	    	}
	    	return content[index-1];
	    }
	    
	    public static void main(String[] args) {
			String line = "2018-01-15 |001 |103458144456 |0007 |103 |103100000026 |103100000026 |  |  |  |4510 |4581 |中国农业银行安丘市支行安丘办事处 |  |1100000000 |0002 |0002 |0001 |  |  |0536-4223425 |  |@ |1 |2014-06-27 21:20:37.865988 |2014-08-15 17:32:43.910966 |2014-08-16 |2999-12-31";
			System.out.println(getFieldsplitBySpecity(line, "[|]", 13));
			String[] strings = splitBySpecify(line, "[|]");
			for (String string : strings) {
				System.out.println(string);
			}
	    }
	    
	    /**
	     * 处理字符串变量的首字母
	     * 
	     * @param obj
	     *            字符串变量
	     * @param type
	     *            处理类型
	     * @return 处理后的字符串变量
	     */
	    public static String firstLetterFormat(String obj, StringFormatConstants type) {

	        assertNotNull(type);

	        String firstLetter = null;

	        // 如果字符串变量为空，则直接返回
	        if (isEmpty(obj)) {
	            return obj;
	        }

	        if (StringFormatConstants.UPPER_CASE.equals(type)) {
	            // 获取字符串变量的首字母的大写字母
	            firstLetter = obj.substring(0, 1).toUpperCase();
	        } else if (StringFormatConstants.LOWER_CASE.equals(type)) {
	            // 获取字符串变量的首字母的小写字母
	            firstLetter = obj.substring(0, 1).toLowerCase();
	        }

	        // 如果字符串变量长度大于1，则返回首字母处理后的字符串变量，
	        // 否则返回处理后的首字母
	        if (1 < obj.length()) {
	            return firstLetter + obj.substring(1);
	        } else {
	            return firstLetter;
	        }
	    }

	    /**
	     * 格式化日期
	     * 
	     * @param dt
	     *            日期
	     * @param formatType
	     *            日期格式
	     * @return 格式化后的日期
	     */
	    public static String formatDate(Date dt, StringFormatType formatType) {

	        assertNotNull(formatType);

	        if (null == dt) {
	            return null;
	        }

	        SimpleDateFormat formatter = new SimpleDateFormat(formatType.format());
	        return formatter.format(dt);
	    }

	    /**
	     * 格式化数字
	     * 
	     * @param bd
	     *            数字
	     * @param formatType
	     *            格式类型
	     * @return 格式化后的数字
	     */
	    public static String formatNumber(BigDecimal bd, StringFormatType formatType) {

	        assertNotNull(formatType);

	        if (null == bd) {
	            return null;
	        }

	        DecimalFormat formatter = new DecimalFormat(formatType.format());
	        return formatter.format(bd);
	    }

	    /**
	     * 判断字符串变量是否为空
	     * 
	     * @param obj
	     *            字符串变量
	     * @return true时字符串为空
	     */
	    public static boolean isEmpty(String obj) {
	        if (null == obj) {
	            return true;
	        } else {
	            return EMPTY_STRING.equals(obj.trim());
	        }
	    }

	    /**
	     * 字符串左对齐
	     * 
	     * @param str
	     *            字符串
	     * @param c
	     *            填充字符
	     * @param len
	     *            长度
	     * @return 左对齐后的字符串
	     */
	    public static String lalign(String str, char c, int len) {

	        assertNotNull(str);

	        if (str.length() >= len) {
	            return str;
	        }

	        return str + repeat(c, len - str.length());
	    }

	    /**
	     * 字符串左对齐，填充空格
	     * 
	     * @param str
	     *            字符串
	     * @param len
	     *            长度
	     * @return 左对齐后的字符串
	     */
	    public static String lalign(String str, int len) {
	        return lalign(str, HALF_SPACE, len);
	    }

	    /**
	     * 在头部追加重复字符串
	     * 
	     * @param str
	     *            字符串
	     * @param c
	     *            重复字符
	     * @param len
	     *            重复次数
	     * @return 追加重复字符后的字符串
	     */
	    public static String lappend(String str, char c, int len) {

	        assertNotNull(str);
	        assertNotNegative(len);

	        return repeat(c, len) + str;
	    }

	    /**
	     * 去除头部空格
	     * 
	     * @param str
	     *            对象字符串
	     * @return 去除空格后的字符串
	     */
	    public static String ltrim(String str) {

	        assertNotNull(str);

	        for (int i = 0; i < str.length(); i++) {
	            if (HALF_SPACE != str.charAt(i) && FULL_SPACE != str.charAt(i)) {
	                return str.substring(i);
	            }
	        }
	        return "";
	    }

	    /**
	     * 去除头部重复字符
	     * 
	     * @param str
	     *            对象字符串
	     * @param c
	     *            重复字符
	     * @return 去除重复字符后的字符串
	     */
	    public static String ltrim(String str, char c) {

	        assertNotNull(str);

	        for (int i = 0; i < str.length(); i++) {
	            if (str.charAt(i) != c) {
	                return str.substring(i);
	            }
	        }
	        return "";
	    }

	    /**
	     * 将字符串转换为日期
	     * 
	     * @param dateString
	     *            日期字符串
	     * @return 日期
	     */
	    public static Date parseDate(String dateString) {

	        assertNotNull(dateString);

	        SimpleDateFormat parser = new SimpleDateFormat();

	        Date retval = null;

	        boolean chkResult = false;

	        // 尝试按已知的格式转换
	        for (StringFormatType pattern : dateParsePattern) {
	            try {
	                parser.applyPattern(pattern.format());
	                retval = parser.parse(dateString);
	                // 如果转换成功，则验证其结果正确性
	                if (dateString.equals(StringUtils.formatDate(retval, pattern))) {
	                    chkResult = true;
	                    break;
	                }
	            } catch (ParseException pe) {
	            }
	        }

	        // 如果验证失败，则返回null
	        if (!chkResult) {
	            retval = null;
	        }

	        return retval;
	    }

	    /**
	     * 将字符串转换为数字
	     * 
	     * @param numberString
	     *            数字字符串
	     * @return 数字
	     */
	    public static BigDecimal parseNumber(String numberString) {

	        assertNotNull(numberString);

	        DecimalFormat parser = new DecimalFormat();

	        // 设置返回BigDecimal
	        parser.setParseBigDecimal(true);

	        BigDecimal retval = null;

	        boolean chkResult = false;

	        // 尝试按已知的格式转换
	        for (StringFormatType pattern : numberParsePattern) {
	            try {
	                parser.applyPattern(pattern.format());
	                retval = (BigDecimal) parser.parse(numberString);
	                if (numberString.equals(StringUtils.formatNumber(retval, pattern))) {
	                    chkResult = true;
	                    break;
	                }
	            } catch (ParseException pe) {
	            }
	        }

	        // 如果验证失败，则返回null
	        if (!chkResult) {
	            retval = null;
	        }

	        return retval;
	    }

	    /**
	     * 字符串右对齐
	     * 
	     * @param str
	     *            字符串
	     * @param c
	     *            填充字符
	     * @param len
	     *            长度
	     * @return 右对齐后的字符串
	     */
	    public static String ralign(String str, char c, int len) {

	        assertNotNull(str);
	        assertNotNegative(len);

	        if (str.length() >= len) {
	            return str;
	        }

	        return repeat(c, len - str.length()) + str;
	    }

	    /**
	     * 字符串右对齐，填充空格
	     * 
	     * @param str
	     *            字符串
	     * @param len
	     *            长度
	     * @return 右对齐后的字符串
	     */
	    public static String ralign(String str, int len) {
	        return ralign(str, HALF_SPACE, len);
	    }

	    /**
	     * 在尾部追加重复字符串
	     * 
	     * @param str
	     *            字符串
	     * @param c
	     *            重复字符
	     * @param len
	     *            重复次数
	     * @return 追加重复字符后的字符串
	     */
	    public static String rappend(String str, char c, int len) {

	        assertNotNull(str);
	        assertNotNegative(len);

	        return str + repeat(c, len);
	    }

	    /**
	     * 重复字符串
	     * 
	     * @param c
	     *            重复字符
	     * @param len
	     *            重复次数
	     * @return 重复字符串
	     */
	    public static String repeat(char c, int len) {

	        assertNotNegative(len);

	        char[] carr = new char[len];
	        for (int i = 0; i < len; i++) {
	            carr[i] = c;
	        }
	        return new String(carr);
	    }

	    /**
	     * 去除尾部空格
	     * 
	     * @param str
	     *            对象字符串
	     * @return 去除空格后的字符串
	     */
	    public static String rtrim(String str) {

	        assertNotNull(str);

	        for (int i = str.length() - 1; i >= 0; i--) {
	            if (HALF_SPACE != str.charAt(i) && FULL_SPACE != str.charAt(i)) {
	                return str.substring(0, i + 1);
	            }
	        }
	        return "";
	    }

	    /**
	     * 去除尾部重复字符
	     * 
	     * @param str
	     *            对象字符串
	     * @param c
	     *            重复字符
	     * @return 去除重复字符后的字符串
	     */
	    public static String rtrim(String str, char c) {

	        assertNotNull(str);

	        for (int i = str.length() - 1; i >= 0; i--) {
	            if (str.charAt(i) != c) {
	                return str.substring(0, i + 1);
	            }
	        }
	        return "";
	    }

	    /**
	     * 转义SQL字符串
	     * 
	     * @param str
	     *            SQL字符串
	     * @return 转义后的SQL字符串
	     */
	    public static String sqlEscape(String str) {

	        assertNotNull(str);

	        return str.replace(PUNC_BRACKET, ESCAPE_BRACKET).replace(PUNC_PERCENT, ESCAPE_PERCENT)
	            .replace(PUNC_UNDERLINE, ESCAPE_UNDERLINE)
	            .replace(PUNC_SINGLE_QUOTE, ESCAPE_SINGLE_QUOTE);
	    }

	    /**
	     * 逆转义SQL字符串
	     * 
	     * @param str
	     *            SQL字符串
	     * @return 逆转义后的SQL字符串
	     */
	    public static String sqlUnescape(String str) {

	        assertNotNull(str);

	        return str.replace(ESCAPE_SINGLE_QUOTE, PUNC_SINGLE_QUOTE)
	            .replace(ESCAPE_UNDERLINE, PUNC_UNDERLINE).replace(ESCAPE_PERCENT, PUNC_PERCENT)
	            .replace(ESCAPE_BRACKET, PUNC_BRACKET);
	    }

	    /**
	     * 去除头尾空格
	     * 
	     * @param str
	     *            对象字符串
	     * @return 去除空格后的字符串
	     */
	    public static String trim(String str) {
	        return ltrim(rtrim(str));
	    }

	    /**
	     * 去除头尾重复字符
	     * 
	     * @param str
	     *            对象字符串
	     * @param c
	     *            重复字符
	     * @return 去除重复字符后的字符串
	     */
	    public static String trim(String str, char c) {
	        return rtrim(ltrim(str, c), c);
	    }

	    /**
	     * 将下划线连接的字符串转换为驼峰表示的字符串
	     * 
	     * @param obj
	     *            字符串变量
	     * @return 驼峰表示的字符串
	     */
	    public static String underlineToCamel(String obj) {

	        assertNotNull(obj);

	        // 获取按下划线分割的子字符串组
	        String[] subStrings = obj.split(PUNC_UNDERLINE);

	        // 返回变量
	        String retval = "";

	        // 格式化为驼峰模式并连接每个子字符串
	        for (String subString : subStrings) {
	            retval += firstLetterFormat(subString.toLowerCase(), StringFormatConstants.UPPER_CASE);
	        }

	        return retval;
	    }

	    /**
	     * 全角英数字转半角
	     * 
	     * @param input
	     *            字符串变量
	     * @return 半角字符串
	     */
	    public static String replaceWideChars(String input) {
	        // バッファの初期化
	        StringBuffer output = new StringBuffer(input.length());
	        for (int i = 0; i < input.length(); i++) {
	            int idx = WIDECHARS.indexOf(input.charAt(i));
	            if (idx != -1) {
	                output.append(CHARS.charAt(idx));
	            } else {
	                output.append(input.charAt(i));
	            }
	        }
	        return output.toString();
	    }

	    /**
	     * 半角英数字转全角
	     * 
	     * @param input
	     *            字符串变量
	     * @return 半角字符串
	     */
	    public static String replaceHalfChars(String input) {
	        // バッファの初期化
	        StringBuffer output = new StringBuffer(input.length());
	        for (int i = 0; i < input.length(); i++) {
	            int idx = CHARS.indexOf(input.charAt(i));
	            if (idx != -1) {
	                output.append(WIDECHARS.charAt(idx));
	            } else {
	                output.append(input.charAt(i));
	            }
	        }
	        return output.toString();
	    }

	    /**
	     * 移除前后百分号
	     * 
	     * @param input
	     *            字符串变量
	     * @return 字符串
	     */
	    public static String removePercent(String input) {
	        // バッファの初期化
	        String strObj = null;
	        if (!StringUtils.isEmpty(input) && input.startsWith(PUNC_PERCENT)
	            && input.endsWith(PUNC_PERCENT)) {
	            strObj = input.substring(1);
	            strObj = strObj.substring(0, strObj.length() - 1);
	        }
	        return strObj;
	    }

	    /**
	     * Null 转换
	     * 
	     * @param str
	     *            字符
	     * @return 字符
	     */
	    public static String nullToSp(String str) {
	        if (StringUtils.isEmpty(str)) {
	            return "";
	        }
	        return str;
	    }

	    /**
	     * 判断字符串是否是数字
	     * 
	     * @param str
	     *            字符
	     * @return 判断结果
	     */
	    public static boolean isNumeirc(String str) {
	        Pattern pattern = Pattern.compile(NUMBER_REGEX);
	        return pattern.matcher(str).matches();
	    }

	    public static boolean isFormatNormal(String target, String regex) {
	        Pattern pattern = Pattern.compile(regex);
	        return pattern.matcher(target).matches();
	    }

	    // public static void main(String[] args) {
	    // //
	    // if(isFormatNormal("15:10","^(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})$")){
	    // // System.out.println("have");
	    // // }else {
	    // // System.out.println("don't have");
	    // // };
	    // System.out.println(Integer.parseInt("0123"));;
	    // }
	    /**
	     * 判断字符串是否符合邮箱
	     * 
	     * @param str
	     *            字符
	     * @return 判断结果
	     */
	    public static boolean isSimpleMailFormat(String str) {
	        Pattern pattern = Pattern.compile(MAIL_REGEX);
	        return pattern.matcher(str).matches();
	    }

	    /**
	     * 判断字符串是否含有空格
	     * 
	     * @param str
	     *            字符
	     * @return 判断结果
	     */
	    public static boolean isContainSpace(String str) {
	        Pattern pattern = Pattern.compile(SPACE_REGEX);
	        return pattern.matcher(str).matches();
	    }

	    /**
	     * Null值判断
	     * 
	     * @param obj
	     *            对象
	     */
	    private static void assertNotNull(Object obj) {
	        if (null == obj) {
	            throw new InvalidParameterException();
	        }
	    }

	    /**
	     * 负值判断
	     * 
	     * @param num
	     *            数字
	     */
	    private static void assertNotNegative(int num) {
	        if (0 > num) {
	            throw new InvalidParameterException();
	        }
	    }

	    /**
	     * 判断一个字符串不是空字符串
	     * 
	     * @param str
	     *            字符串
	     * @return 空返回false 非空返回true
	     */
	    public static boolean isNotEmpty(String str) {
	        return !isEmpty(str.trim());
	    }

	    /**
	     * 判断二个字符串是否相等
	     * 
	     * @param str
	     *            字符串
	     * @param str1
	     *            字符串
	     * @return 不相等false 相等true
	     */
	    public static boolean equals(String str, String str1) {
	        if (isNotEmpty(str) && isNotEmpty(str1)) {
	            return str.equals(str1);
	        }
	        return false;
	    }
	    
	    /**
	     * 把字符串中的|换成成，
	     * @param str
	     * @return
	     */
	    public static String replaceAll(String str){
	    	char[] s = str.toCharArray();
			String string = "";
			for(int i=0;i<s.length;i++){
				if(s[i]=='|'){
					string += ",";
				}else{
					string += s[i];
				}
			}
			return string;
	    }
	    /**
	     * 从尾部开始截取字符串指定的长度
	     * @param str
	     * @param length
	     * @return
	     */
	    public static String strEndSubstr(String str,int length){
	    	return  strSubstr(str,-length);
	    }
	    
	    /**
	     * 从头部开始截取字符串指定的长度
	     * @param str
	     * @param length
	     * @return
	     */
	    public static String strStartSubstr(String str,int length){
	    	return  strSubstr(str,length);
	    }
	    
	    /**
	     * 截取字符串指定的长度
	     * @param str
	     * @param length
	     * @return
	     */
	    public static String strSubstr(String str,int length){
	    	if(str==null)
	    		return "";
	    	if(str.length()<Math.abs(length))
	    		return str;
	    	return length<0?str.substring(str.length()-Math.abs(length), str.length()) : str.substring(0, Math.abs(length));
	    }
	    
	    /**
	     * 从指定位置，截取字符串指定的长度
	     * @param str
	     * @param start
	     * @param length <0向前截取，>0向后截取
	     * @return
	     */
	    public static String strSubstr(String str,int start, int length){
	    	if(str==null || str.length()<Math.abs(start) || length==0)
	    		return "";
	    	
	    	return length<0?str.substring(start-Math.abs(length), start) : str.substring(start, Math.abs(length));
	    }
	    
		/**
		 * 通过正则表达式获取内容
		 * 
		 * @param regex		正则表达式
		 * @param from		原字符串
		 * @return
		 */
		public static String[] regex(String regex, String from){
			Pattern pattern = Pattern.compile(regex); 
			Matcher matcher = pattern.matcher(from);
			List<String> results = new ArrayList<String>();
			while(matcher.find()){
				for (int i = 0; i < matcher.groupCount(); i++) {
					results.add(matcher.group(i+1));
				}
			}
			return results.toArray(new String[]{});
		}


}
