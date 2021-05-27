package io.lemon.tree.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class SignatureUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encodeSHA256ToHexString(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        byte[] b = md.digest(data);
        return new String(Hex.encode(b));
    }

    public static String encodeSHA256ToHexString(byte[] data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] b = md.digest(data);
        return new String(Hex.encode(b));
    }

    public static byte[] encodeSHA256(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        return md.digest(data);
    }

    public static byte[] encodeSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(data);
    }

    public static String encodeMD5ToHexString(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        byte[] b = md.digest(data);
        return new String(Hex.encode(b));
    }

    public static String encodeMD5ToHexString(byte[] data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(data);
        return new String(Hex.encode(b));
    }

    public static byte[] encodeMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        return md.digest(data);
    }

    public static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    public static byte[] encodeMD5Stream(InputStream in) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(in, md);
        //TODO 这种方式智能督导一个字节
        int read = 0;
        while (-1 != read) {
            read = dis.read();
        }
        byte[] result = dis.getMessageDigest().digest();
        dis.close();
        return result;
    }

    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    public static byte[] getSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String str = "lining";
        byte[] data = str.getBytes();
        InputStream in = new ByteInputStream(data, data.length);
        System.out.println(new String(Hex.encode(encodeMD5Stream(in))));
        System.out.println(encodeMD5ToHexString(str));
    }

}
