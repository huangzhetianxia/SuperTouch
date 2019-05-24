package com.supertouch.loginregister.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式检查
 */
public class FormatCheckUtils {

    /**
     * 校验是否为手机号码
     * @param value
     * @return
     */
    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    /**
     * 密码是否为6-16位数
     * @param value
     * @return
     */
    public static boolean isPasswordFormat(String value){
        if(value.length()>=8&&value.length()<=16){
            return true;
        }else{
            return false;
        }
    }
}
