package io.github.foodsearcher.model.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by young on 18-3-11.
 */
public class SecurityHelper {

    public static String encryptPassword(String plainPassword, String salt) {
        String algorithmName = "md5";
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, plainPassword, salt, hashIterations);
       return hash.toHex();
    }
}
