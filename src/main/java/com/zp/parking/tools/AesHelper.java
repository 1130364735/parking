package com.zp.parking.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AesHelper {

    /**
     * encrypt with 'AES/ECB/PKCS5Padding' as cipher transformation
     *
     * @param plain the plain text to be ciphered
     * @param key cipher key (AES by definition takes 16, 24 or 32 bytes as key, and nothing else)
     * @return base64 encoded cipher text
     */
    public static String encryptToString(String plain, String key) {
        return Base64.getEncoder().encodeToString(encrypt(plain, key.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * encrypt with 'AES/ECB/PKCS5Padding' as cipher transformation
     *
     * @param plain the plain text to be ciphered
     * @param key cipher key (AES by definition takes 16, 24 or 32 bytes as key, and nothing else)
     * @return cipher text
     */
    public static byte[] encrypt(String plain, byte[] key) {
        // https://stackoverflow.com/questions/10193567/java-security-nosuchalgorithmexceptioncannot-find-any-provider-supporting-aes-e
        // PKCS7Padding is the same as PKCS5Padding. But PKCS7Padding is not supported by JDK, while PKCS5Padding is.
        return encrypt(plain.getBytes(StandardCharsets.UTF_8), key, "AES/ECB/PKCS5Padding");
    }

    /**
     * encrypt given plain text with given cipher key and transformation
     *
     * @param plain the plain text to be ciphered
     * @param key cipher key (AES by definition takes 16, 24 or 32 bytes as key, and nothing else)
     * @param transformation cipher transformation, 'AES/ECB/PKCS5Padding' for example
     * @return cipher text
     */
    public static byte[] encrypt(byte[] plain, byte[] key, String transformation) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));

            return cipher.doFinal(plain);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            // all those exceptions should be handled during dev phase, and never let it carry out into prod
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * decrypt with 'AES/ECB/PKCS5Padding' as cipher transformation
     *
     * @param ciphered the cipher text
     * @param key cipher key (AES by definition takes 16, 24 or 32 bytes as key, and nothing else)
     * @return plain text (Note: it's not base64 encoded)
     */
    public static String decryptToString(String ciphered, String key) {
        return new String(decrypt(ciphered, key));
    }

    /**
     * decrypt with 'AES/ECB/PKCS5Padding' as cipher transformation
     *
     * @param ciphered the cipher text
     * @param key cipher key, should be multiples of 16 in length
     * @return plain text
     */
    public static byte[] decrypt(String ciphered, String key) {
        return decrypt(Base64.getDecoder().decode(ciphered), key);
    }

    /**
     * decrypt with 'AES/ECB/PKCS5Padding' as cipher transformation
     *
     * @param ciphered the cipher text
     * @param key cipher key (AES by definition takes 16, 24 or 32 bytes as key, and nothing else)
     * @return plain text
     */
    public static byte[] decrypt(byte[] ciphered, String key) {
        // https://stackoverflow.com/questions/10193567/java-security-nosuchalgorithmexceptioncannot-find-any-provider-supporting-aes-e
        // PKCS7Padding is the same as PKCS5Padding. But PKCS7Padding is not supported by JDK, while PKCS5Padding is.
        return decrypt(ciphered, key, "AES/ECB/PKCS5Padding");
    }

    /**
     * decrypt given cipher text with given key and transformation
     *
     * @param ciphered the cipher text
     * @param key cipher key (AES by definition takes 16, 24 or 32 bytes as key, and nothing else)
     * @return plain text
     */
    public static byte[] decrypt(byte[] ciphered, String key, String transformation) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));

            return cipher.doFinal(ciphered);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
               IllegalBlockSizeException | BadPaddingException e) {
            // all those exceptions should be handled during dev phase, and never let it carry out into prod
            throw new RuntimeException(e.getMessage());
        }
    }
}