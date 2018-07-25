package fr.perrine.essaiallodisney.Helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassHelper {

    public static String hashPass(String password) {
        String passwordHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwordHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passwordHash;
    }
}
