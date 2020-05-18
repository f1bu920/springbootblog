package com.springboot.practice.Util;

import java.security.MessageDigest;

public class MD5Util {
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byteToHexString(b[i]));
        }
        return sb.toString();
    }

    public static String MD5Encode(String origin, String charset) {
        String result = null;
        try {
            result = new String(origin);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            if (charset == null || "".equals(charset)) {
                result = byteArrayToHexString(md5.digest(result.getBytes()));
            } else {
                result = byteArrayToHexString(md5.digest(result.getBytes(charset)));
            }
        } catch (Exception e) {
        }
        return result;
    }
}
