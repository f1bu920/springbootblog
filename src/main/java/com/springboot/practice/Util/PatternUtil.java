package com.springboot.practice.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    /**
     * 匹配邮箱正则
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * 验证只包含中英文和数字的字符串
     *
     * @param keyWord
     * @return
     */
    public static Boolean validKeyword(String keyWord) {
        String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(keyWord);
        return matcher.matches();
    }

    /**
     * 判断是否是邮箱
     * @param eamilStr
     * @return
     */
    public static boolean isEmail(String eamilStr){
        return VALID_EMAIL_ADDRESS_REGEX.matcher(eamilStr).find();
    }

    /**
     * 判断是否是网址
     * @param urlStr
     * @return
     */
    public static boolean isURL(String urlStr){
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(urlStr).matches();
    }
}
