package com.switchvov.test;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Created by jap on 2017/7/18.
 */
public class Base64Test2 {
    /**
     * 创建日期2011-4-25上午10:12:38
     * 修改日期
     * 作者：dh *TODO 使用Base64加密算法加密字符串
     * return
     */
    public static String encodeStr(String plainText) {
        byte[] b = plainText.getBytes();
        Base64 base64 = new Base64();
        b = base64.encode(b);
        String s = new String(b);
        return s;
    }

    /**
     * 创建日期2011-4-25上午10:15:11
     * 修改日期
     * 作者：dh     *TODO 使用Base64加密
     * return
     */
    public static String decodeStr(String encodeStr) {
        byte[] b = encodeStr.getBytes();
        Base64 base64 = new Base64();
        b = base64.decode(b);
        String s = new String(b);
        return s;
    }

    @Test
    public void test() {
        String s = encodeStr("15644564564654561");
        System.out.println(s);
        String s1 = decodeStr(s);
        System.out.println(s1);
    }
}
