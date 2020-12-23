package com.kevenpotter.student.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * 盐值工具类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-23 13:33:56
 */
public class SaltUtils {

    /*编码格式:默认使用uft-8*/
    public static String CHARSET = "UTF-8";
    /*加密方式:DES*/
    public static final String DES = "DES";
    /*DES*/
    public static int KEY_SIZE_DES = 0;

    /**
     * 返回MD5加密时需要使用的盐值
     *
     * @return 返回MD5加密时需要使用的盐值
     * @author KevenPotter
     * @date 2020-12-23 13:49:28
     */
    public static String getSaltValue() {
        return DESDecode("976C0FD9866D7679C4C94E16936706DC", "Keven");
    }

    /**
     * 使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错
     *
     * @param res       加密的原文
     * @param algorithm 加密使用的算法名称
     * @param key       加密的秘钥
     * @param keysize
     * @param isEncode
     * @return
     */


    private static String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                byte[] keyBytes = CHARSET == null ? key.getBytes() : key.getBytes(CHARSET);
                kg.init(new SecureRandom(keyBytes));

            } else if (key == null) {
                kg.init(keysize);

            } else {
                byte[] keyBytes = CHARSET == null ? key.getBytes() : key.getBytes(CHARSET);
                kg.init(keysize, new SecureRandom(keyBytes));

            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = CHARSET == null ? res.getBytes() : res.getBytes(CHARSET);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(res))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 196      * 使用DES加密算法进行加密（可逆）
     * 197      * @param res 需要加密的原文
     * 198      * @param key 秘钥
     * 199      * @return
     * 200
     */
    public static String DESEncode(String res, String key) {
        return keyGeneratorES(res, DES, key, KEY_SIZE_DES, true);
    }

    /**
     * 对使用DES加密算法的密文进行解密（可逆）
     *
     * @param res 需要解密的密文
     * @param key 秘钥
     * @return
     */
    public static String DESDecode(String res, String key) {
        return keyGeneratorES(res, DES, key, KEY_SIZE_DES, false);
    }
}