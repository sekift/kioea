package com.kioea.www.encryptutil;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * IDEA对称加密算法，java6不支持这个算法的实现，bouncycastle支持IDEA对称加密算法
 * 这是一款对称分组密码。是目前比较常用的电子邮件加密算法之一
 * 我们可以参照这个算法的实现来完成其他算法的实现：Rijndael,Serpent,Twofish等
 * 
 * @author kongqz
 * */
public class IDEACoder {
	/**
	 * 密钥算法
	 * */
	public static final String KEY_ALGORITHM = "IDEA";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * */
	public static final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

	/**
	 * 
	 * 生成密钥，只有bouncycastle支持
	 * 
	 * @return byte[] 二进制密钥
	 * */
	public static byte[] initkey() throws Exception {
		// 加入bouncyCastle支持
		Security.addProvider(new BouncyCastleProvider());

		// 实例化密钥生成器
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥生成器，IDEA要求密钥长度为128位
		kg.init(128);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		// 获取二进制密钥编码形式
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return Key 密钥
	 * */
	public static Key toKey(byte[] key) throws Exception {
		// 实例化DES密钥
		// 生成密钥
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密后的数据
	 * */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 加入bouncyCastle支持
		Security.addProvider(new BouncyCastleProvider());
		// 还原密钥
		Key k = toKey(key);
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 加入bouncyCastle支持
		Security.addProvider(new BouncyCastleProvider());
		// 还原密钥
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}
	
    /**将二进制转换成16进制 
     * @param buf 
     * @return 
     */  
    public static String parseByte2HexStr(byte buf[]) {  
    	StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
                String hex = Integer.toHexString(buf[i] & 0xFF);  
                if (hex.length() == 1) {  
                        hex = '0' + hex;  
                }  
                sb.append(hex.toUpperCase());  
        }  
        return sb.toString();     
    }
    
    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */  
    public static byte[] parseHexStr2Byte(String hexStr) {  
    	if (hexStr.length() < 1)  
            return null;  
    	byte[] result = new byte[hexStr.length()/2];  
    	for (int i = 0;i< hexStr.length()/2; i++) {  
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
            result[i] = (byte) (high * 16 + low);  
    	}  
    	return result;      
    }

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String str = "2052ef43e2f4e9a6dc99f604ebd5d652";
		System.out.println("原文：" + str);
		// 初始化密钥
		byte[] key = "a".getBytes();//{'a','a','b','c','2'};//IDEACoder.initkey();
//		System.out.println("密钥：" + Base64.encodeBase64String(key));
		System.out.println("密钥：" + parseByte2HexStr(initkey()));
		// 加密数据
		byte[] data = IDEACoder.encrypt(str.getBytes(), key);
//		System.out.println("加密后：" + Base64.encodeBase64String(data));
		System.out.println("加密后：" + parseByte2HexStr(data));
		// 解密数据
		data = IDEACoder.decrypt(data, key);
		System.out.println("解密后：" + new String(data));
	}
}