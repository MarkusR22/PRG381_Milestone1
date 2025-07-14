package com.bc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtil {

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes());

            byte[] shortHash = new byte[16];
            System.arraycopy(hash, 0, shortHash, 0, 16);

            return Base64.getEncoder().encodeToString(shortHash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found" ,e);
        }
    }
}
