package com.baize.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EnCodePassword {
    /**
     * 静态的加密方法
     * @return
     */
    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 为了跑起来得到密码"123"的密文
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(encode("123"));
    }
}
