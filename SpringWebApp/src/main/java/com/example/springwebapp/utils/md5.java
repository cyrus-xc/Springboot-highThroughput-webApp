package com.example.springwebapp.utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class md5 {
    public static String getMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    private static final String salt = "1a2b3c4d"; // server side salt

    public static String inputPassToSaltedPass(String inputPass) { // create pass with server side salt
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return getMD5(str);
    }

    public static String saltedPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return getMD5(str);
    }

    public static String inputPassToDBPass(String input, String saltDB) { // saltDB is the salt in the database
        String formPass = inputPassToSaltedPass(input);
        return saltedPassToDBPass(formPass, saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToSaltedPass("123456")); // d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(saltedPassToDBPass(inputPassToSaltedPass("123456"), "1a2b3c4d")); // 1897a69ef451f0991bb85c6e7c35aa31
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
