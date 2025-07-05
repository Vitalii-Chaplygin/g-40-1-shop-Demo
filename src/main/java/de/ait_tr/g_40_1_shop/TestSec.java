package de.ait_tr.g_40_1_shop;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestSec {
    public static void main(String[] args) {
        //получить зашифрованный пароль
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "111";
        String encodePassword = encoder.encode(password);
        System.out.println(encodePassword);
    }
}
