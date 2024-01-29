package com.zp.parking.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RsaHelper {

    // =========================================
    //          Encrypt & Decrypt
    // =========================================
    /**
     * encrypt given plain text
     *
     * @param plain the plain text to be encrypted
     * @param publicKey base64 encoded public key
     * @return base64 encoded cipher text
     *
     *
     */
    public static String encryptToString(String plain, String publicKey) {
        return Base64.getEncoder().encodeToString(encrypt(plain.getBytes(StandardCharsets.UTF_8), publicKey));
    }

    /**
     * encrypt given plain text
     *
     * @param plain the plain text to be encrypted
     * @param publicKey public key
     * @return base64 encoded cipher text
     */
    public static String encryptToString(String plain, byte[] publicKey) {
        return Base64.getEncoder().encodeToString(encrypt(plain.getBytes(StandardCharsets.UTF_8), publicKey));
    }

    /**
     * encrypt given plain text
     *
     * @param plain the plain text to be encrypted
     * @param publicKey base64 encoded public key
     * @return cipher text
     */
    public static byte[] encrypt(byte[] plain, String publicKey) {
        return encrypt(plain, Base64.getDecoder().decode(publicKey));
    }

    /**
     * encrypt given plain text
     *
     * @param plain the text to be encrypted
     * @param publicKey public key
     * @return cipher text
     */
    public static byte[] encrypt(byte[] plain, byte[] publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, bytesToPublicKey(publicKey));
            return cipher.doFinal(plain);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeySpecException e) {
            // all those exceptions should be handled during dev phase, and never let it carry out into prod
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * decrypt
     *
     * @param ciphered  the cipher text,which is base64 encoded
     * @param privateKey base64 encoded private key
     * @return plain text
     */
    public static String decrypt(String ciphered, String privateKey) {
        return new String(decrypt(Base64.getDecoder().decode(ciphered), Base64.getDecoder().decode(privateKey)));
    }

    /**
     * decrypt
     *
     * @param ciphered  the cipher text
     * @param privateKey base64 encoded private key
     * @return plain text
     */
    public static byte[] decrypt(byte[] ciphered, String privateKey) {
        return decrypt(ciphered, Base64.getDecoder().decode(privateKey));
    }

    /**
     * decrypt
     *
     * @param ciphered  base64 encoded the cipher text
     * @param privateKey  private key
     * @return plain text
     */
    public static String decrypt(String ciphered, byte[] privateKey) {
        return new String(decrypt(Base64.getDecoder().decode(ciphered), privateKey));
    }

    /**
     * decrypt
     *
     * @param ciphered  the cipher text
     * @param privateKey private key
     * @return plain text
     */
    public static byte[] decrypt(byte[] ciphered, byte[] privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, bytesToPrivateKey(privateKey));
            return cipher.doFinal(ciphered);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeySpecException e) {
            // all those exceptions should be handled during dev phase, and never let it carry out into prod
            throw new RuntimeException(e.getMessage());
        }
    }

    // =========================================
    //          Sign & Verify
    // =========================================

    /**
     * sign given data
     * @param data the raw data to be signed
     * @param privateKey base64 encoded private key
     * @return base64 encoded signature
     */
    public static String sign(String data, String privateKey) {
        return sign(data, Base64.getDecoder().decode(privateKey));
    }

    /**
     * sign given data
     * @param data the raw data to be signed
     * @param privateKey private key
     * @return base64 encoded signature
     */
    public static String sign(String data, byte[] privateKey) {
        return Base64.getEncoder().encodeToString(
            sign(data.getBytes(StandardCharsets.UTF_8), privateKey)
        );
    }

    /**
     * sign given data
     * @param data the raw data to be signed
     * @param privateKey private key
     * @return signature
     */
    public static byte[] sign(byte[] data, byte[] privateKey) {
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(bytesToPrivateKey(privateKey));
            sign.update(data);
            return sign.sign();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            // all those exceptions should be handled during dev phase, and never let it carry out into prod
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * verify signature
     *
     * @param data the raw data whose signature will be verified
     * @param signature the given/told signature, which is base64 encoded
     * @param publicKey base64 encoded public key
     * @return true if verification passes, false otherwise
     */
    public static boolean verify(String data, String signature, String publicKey) {
        return verify(data, signature, Base64.getDecoder().decode(publicKey));
    }

    /**
     * verify signature
     *
     * @param data the raw data whose signature will be verified
     * @param signature the given/told signature, which is base64 encoded
     * @param publicKey public key
     * @return true if verification passes, false otherwise
     */
    public static boolean verify(String data, String signature, byte[] publicKey) {
        return verify(data.getBytes(StandardCharsets.UTF_8), Base64.getDecoder().decode(signature), publicKey);
    }

    /**
     * verify signature
     *
     * @param data the raw data whose signature will be verified
     * @param signature the given/told signature
     * @param publicKey public key
     * @return true if verification passes, false otherwise
     */
    public static boolean verify(byte[] data, byte[] signature, byte[] publicKey) {
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(bytesToPublicKey(publicKey));
            sign.update(data);
            return sign.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            // all those exceptions should be handled during dev phase, and never let it carry out into prod
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * generate public and private key pair
     *
     * Example usage:
     *   KeyPair pair = RsaHelper.genKeyPair();
     *   System.out.println(Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));
     *   System.out.println(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));
     *
     * @return key pair
     */
    public static KeyPair genKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024/* key size */, new SecureRandom());
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            // ignore, since RSA is known to be supported
        }

        throw new UnsupportedOperationException("RSA is not supported");
    }

    // convert private key in byte array to object
    private static PrivateKey bytesToPrivateKey(byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // https://stackoverflow.com/questions/2411096/how-to-recover-a-rsa-public-key-from-a-byte-array
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKey));
    }

    // convert public key in byte array to object
    private static PublicKey bytesToPublicKey(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // https://stackoverflow.com/questions/2411096/how-to-recover-a-rsa-public-key-from-a-byte-array
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey));
    }
}
