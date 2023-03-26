package com.ppc.ffs.web.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class CheckUtil {

    public static boolean isPhoneNumber(String phoneNumber) {
        if(StringUtils.isEmpty(phoneNumber)) return false;

        String pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
        if (!Pattern.matches(pattern, phoneNumber)) {
            return false;
        }

        return true;
    }
}
