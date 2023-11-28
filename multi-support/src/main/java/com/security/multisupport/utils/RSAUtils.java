package com.security.multisupport.utils;

//import org.slf4j.Logger;
//import org.slf4j.////loggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author zeng
 * @version v 1.0
 * @File RSAUtils.java
 * @Desc 非对称式加密解密
 * @DateTime 2022-08-30 15:51
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2022 All Rights Reserved.
 */
public class RSAUtils {

    // region 静态常量声明
//    private final static Logger logger = LoggerFactory.getLogger(RSAUtils.class);
    /**
     * 加密算法
     */
    private final static String ALGORITHM_RSA = "RSA";

    /**
     * RSA默认密钥长度
     */
    private final static int DEFAULT_KEY_SIZE = 1024;

    /**
     * RSA公钥索引
     */
    public final static int PUBLIC_KEY_INDEX = 0;

    /**
     * RSA密钥索引
     */
    public final static int PRIVATE_KEY_INDEX = 1;

    /**
     * RSA公钥参数配置名称
     */
    private final static String PUBLIC_KEY_PARA_NAME = "rsaPublicKey";

    /**
     * RSA密钥参数配置名称
     */
    private final static String PRIVATE_KEY_PARA_NAME = "rsaPrivateKey";

    /**
     * 默认密钥字符串对
     */
    private static List<String> DEFAULT_KEY = new ArrayList<String>(2) {{
        this.add("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUXk2nflX4uhPd39JTM8kz/Xbh8WDFLo3H6Q/JLsh1U5MsLmr3b8EWuK5NR8u6ap8PgnMdZpI/U0mAaIuKPZeivur/hqbp1oBocX1XZa1rEYSrFpOIBaHVNlZVZPJE3ZBE0wiUVAqwCLqvHgLxxP9Au5AcnGu7LUTlJ/qDeeWiHQIDAQAB");
        this.add("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJReTad+Vfi6E93f0lMzyTP9duHxYMUujcfpD8kuyHVTkywuavdvwRa4rk1Hy7pqnw+Ccx1mkj9TSYBoi4o9l6K+6v+GpunWgGhxfVdlrWsRhKsWk4gFodU2VlVk8kTdkETTCJRUCrAIuq8eAvHE/0C7kByca7stROUn+oN55aIdAgMBAAECgYEAh1zCXF3W8eZS2cqqiV5l4xkMrXcbYsrmb80iqdoU6XYmf9iZnsZG1AQKgldOd/VuySeq/tfl9gB2vsCjDqzC+kPzjCJzihnqRh7fMy+PkoKR0XK5Mf7ckYPBbwDE2iaPqu+E+KxAUYFAmvhSMWR9A4rau2Z9Z65QDsp3aGtTyAECQQDQFEMMq4agBjjCd1coaTBgocFPdmt/8k34Ex94MtXGl4TgQvClpawnfRILboaM5gjKHNyIb154/Xjk4GN7Pz+9AkEAtomrMBFBUGvv74gU3UfIWS665ska6YCZ8YZ4S78c+Q/UTCubYxHt4h9IS3NKK+leRZSnTv+ofPaIKrAi/0hh4QJAD7w4hdDC0X8m863KrYem3Ofekn/GCVrtgoEPtCtD90ymA7+MKzqoFiRjJPIqwqTyrTYZh0fEwSmJ5uUBIOTbkQJAG/VeKRCbhmvh5jhbqcDy6OYFbo/i9VzjN2E3T7LIB0XraHdNZ5E5dGcCNbxE4hDuhY3NSoZSEkHYfWFZGuzFQQJAS2HXe8DJrbDn5EiLMg3X6QnNKxwpJs6GsaSyIBdKxt/UnkidW2REG20Qfqn5uD4ylg6mKPv3iTWTkorSZHXIxQ==");
    }};

    /**
     * 默认密钥对象对
     */
    private static List<Key> DEFAULT_KEY_OBJECT;

    // endregion 静态常量声明

    // region 获取默认密钥

    /**
     * 获取默认公钥字符串
     *
     * @return 默认公钥字符串
     */
    public static String getDefaultPublicKeyStr() {
        initDefaultKey();
        return DEFAULT_KEY.get(PUBLIC_KEY_INDEX);
    }

    /**
     * 获取默认私钥字符串
     *
     * @return 默认私钥字符串
     */
    public static String getDefaultPrivateKeyStr() {
        initDefaultKey();
        return DEFAULT_KEY.get(PRIVATE_KEY_INDEX);
    }

    /**
     * 初始化默认公钥私钥
     */
    private static void initDefaultKey() {
        try {
            if (DEFAULT_KEY == null || DEFAULT_KEY.size() <= 0) {
                DEFAULT_KEY = getRSAKeyString(DEFAULT_KEY_SIZE);
            }
            DEFAULT_KEY_OBJECT = new ArrayList<>(DEFAULT_KEY.size());
            DEFAULT_KEY_OBJECT.add(getPublicKey(DEFAULT_KEY.get(PUBLIC_KEY_INDEX)));
            DEFAULT_KEY_OBJECT.add(getPrivateKey(DEFAULT_KEY.get(PRIVATE_KEY_INDEX)));
        } catch (NoSuchAlgorithmException e) {
//            logger.error("RSA密钥初始化失败,未找到加密算法",e);
        } catch (Exception e) {
//            logger.error("RSA密钥初始化失败,未知错误",e);
        }
    }

    // endregion 获取默认密钥

    // region 密钥对生成

    /**
     * 直接生成公钥、私钥对象
     *
     * @throws NoSuchAlgorithmException
     */
    public static List<Key> getRSAKeyObject() throws NoSuchAlgorithmException {
        return getRSAKeyObject(DEFAULT_KEY_SIZE);
    }

    /**
     * 直接生成公钥、私钥对象
     *
     * @param modulus 初始化长度 512以上
     * @throws NoSuchAlgorithmException
     */
    public static List<Key> getRSAKeyObject(int modulus) throws NoSuchAlgorithmException {
        List<Key> keyList = new ArrayList<>(2);
        // 创建RSA密钥生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        // 设置密钥的大小，此处是RSA算法的模长 = 最大加密数据的大小
        keyPairGen.initialize(modulus);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // keyPair.getPublic() 生成的是RSAPublic的是咧
        keyList.add(keyPair.getPublic());
        // keyPair.getPrivate() 生成的是RSAPrivateKey的实例
        keyList.add(keyPair.getPrivate());
        return keyList;
    }

    /**
     * 生成公钥、私钥的字符串
     * 方便传输
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static List<String> getRSAKeyString() throws NoSuchAlgorithmException {
        return getRSAKeyString(DEFAULT_KEY_SIZE);
    }

    /**
     * 生成公钥、私钥的字符串
     * 方便传输
     *
     * @param modulus 模长 512以上
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static List<String> getRSAKeyString(int modulus) throws NoSuchAlgorithmException {
        List<String> keyList = new ArrayList<>(2);
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGen.initialize(modulus);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        keyList.add(publicKey);
        keyList.add(privateKey);
        return keyList;
    }

    /**
     * 获取公钥对象
     * Java中RSAPublicKeySpec、X509EncodedKeySpec支持生成RSA公钥
     * 此处使用X509EncodedKeySpec生成
     *
     * @param publicKey 公钥文本
     * @return 公钥对象
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }


    /**
     * 获取私钥对象
     * Java中只有RSAPrivateKeySpec、PKCS8EncodedKeySpec支持生成RSA私钥
     * 此处使用PKCS8EncodedKeySpec生成
     *
     * @param privateKey 私钥文本
     * @return 私钥对象
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }

    // endregion 密钥对生成

    // region 加密

    /**
     * 公钥加密
     *
     * @param data 原始文本
     * @return 密文文本
     * @throws Exception
     */
    public static String encryptByPublicKey(String data)
            throws Exception {
        return encryptByPublicKey(data, getDefaultPublicKeyStr());
    }

    /**
     * 公钥加密
     *
     * @param data      原始文本
     * @param publicKey 公钥文本
     * @return 密文文本
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKey)
            throws Exception {
        return encryptByPublicKey(data, getPublicKey(publicKey));
    }

    /**
     * 公钥加密
     *
     * @param data      原始文本
     * @param publicKey 公钥对象
     * @return 密文文本
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 模长n转换成字节数
        int modulusSize = publicKey.getModulus().bitLength() / 8;
        // PKCS Padding长度为11字节，所以实际要加密的数据不能要 - 11byte
        int maxSingleSize = modulusSize - 11;
        // 切分字节数组，每段不大于maxSingleSize
        byte[][] dataArray = splitArray(data.getBytes(), maxSingleSize);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 分组加密，并将加密后的内容写入输出字节流
        for (byte[] s : dataArray) {
            out.write(cipher.doFinal(s));
        }
        // 使用Base64将字节数组转换String类型
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }
    // endregion 加密

    // region 解密

    /**
     * 私钥解密
     *
     * @param data 密文文本
     * @return 原文文本
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data)
            throws Exception {
        return decryptByPrivateKey(data, getDefaultPrivateKeyStr());
    }

    /**
     * 私钥解密
     *
     * @param data       密文文本
     * @param privateKey 私钥文本
     * @return 原文文本
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String privateKey)
            throws Exception {
        return decryptByPrivateKey(data, getPrivateKey(privateKey));
    }

    /**
     * 私钥解密
     *
     * @param data       密文文本
     * @param privateKey 私钥对象
     * @return 原文文本
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // RSA加密算法的模长 n
        int modulusSize = privateKey.getModulus().bitLength() / 8;
        byte[] dataBytes = data.getBytes();
        // 之前加密的时候做了转码，此处需要使用Base64进行解码
        byte[] decodeData = Base64.getDecoder().decode(dataBytes);
        // 切分字节数组，每段不大于modulusSize
        byte[][] splitArrays = splitArray(decodeData, modulusSize);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (byte[] arr : splitArrays) {
            out.write(cipher.doFinal(arr));
        }
        return new String(out.toByteArray());
    }

    // endregion 解密

    // region 切分字节

    /**
     * 按指定长度切分数组
     *
     * @param data
     * @param len  单个字节数组长度
     * @return
     */
    private static byte[][] splitArray(byte[] data, int len) {
        int dataLen = data.length;
        if (dataLen <= len) {
            return new byte[][]{data};
        }
        byte[][] result = new byte[(dataLen - 1) / len + 1][];
        int resultLen = result.length;
        for (int i = 0; i < resultLen; i++) {
            if (i == resultLen - 1) {
                int splitLen = dataLen - len * i;
                byte[] single = new byte[splitLen];
                System.arraycopy(data, len * i, single, 0, splitLen);
                result[i] = single;
                break;
            }
            byte[] single = new byte[len];
            System.arraycopy(data, len * i, single, 0, len);
            result[i] = single;
        }
        return result;
    }

    // endregion 切分字节

}
