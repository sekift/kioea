package com.kioea.www.encryptutil;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.kioea.www.urlutil.HttpUtil;


/**
 * @ClassName: AEStest
 * @Description: AES加密测试
 * 
 */
public class AESCoder {
        /** 
         * 密钥算法 
        */  
        private static final String KEY_ALGORITHM = "AES";  
          
        private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//"算法/模式/补码方式"  
          
        /** 
         * 初始化密钥 
         *  
         * @return byte[] 密钥  
         * @throws Exception 
         */  
        public static byte[] initSecretKey(String keyStr) {  
            //返回生成指定算法的秘密密钥的 KeyGenerator 对象  
            KeyGenerator kg = null;
            SecureRandom sr = null;
            try {  
                kg = KeyGenerator.getInstance(KEY_ALGORITHM);
                sr = SecureRandom.getInstance("SHA1PRNG");
                sr.setSeed(keyStr.getBytes());
            } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
                return new byte[0];  
            }  
            //初始化此密钥生成器，使其具有确定的密钥大小  
            //AES 要求密钥长度为 128
            kg.init(128,sr);  
            //生成一个密钥  
            SecretKey  secretKey = kg.generateKey();  
            return secretKey.getEncoded();  
        }  
          
        /** 
         * 转换密钥 
         *  
         * @param key   二进制密钥 
         * @return 密钥 
         */  
        private static Key toKey(byte[] key){  
            //生成密钥  
            return new SecretKeySpec(key, KEY_ALGORITHM);  
        }  
          
        /**
         * 解密 
         * 
         * @param data 待解密数据
         * @param keyStr 字符串秘钥
         * @return byte[]   解密数据 
         * @throws Exception
         */
        public static byte[] decrypt(byte[] data,String keyStr) throws Exception{
        	byte[] key = initSecretKey(keyStr);
        	return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM); 
        }
          
        /** 
         * 解密 
         *  
         * @param data  待解密数据 
         * @param key   二进制密钥 
         * @return byte[]   解密数据 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,byte[] key) throws Exception{  
            return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);  
        }  
          
        /** 
         * 解密 
         *  
         * @param data  待解密数据 
         * @param key   密钥 
         * @return byte[]   解密数据 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,Key key) throws Exception{  
            return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);  
        }  
          
        /** 
         * 解密 
         *  
         * @param data  待解密数据 
         * @param key   二进制密钥 
         * @param cipherAlgorithm   加密算法/工作模式/填充方式 
         * @return byte[]   解密数据 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{  
            //还原密钥  
            Key k = toKey(key);  
            return decrypt(data, k, cipherAlgorithm);  
        }  
      
        /** 
         * 解密 
         *  
         * @param data  待解密数据 
         * @param key   密钥 
         * @param cipherAlgorithm   加密算法/工作模式/填充方式 
         * @return byte[]   解密数据 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{  
            //实例化  
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);  
            //使用密钥初始化，设置为解密模式  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            //执行操作  
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
          
        private static String  showByteArray(byte[] data){  
            if(null == data){  
                return null;  
            }  
            StringBuilder sb = new StringBuilder("{");  
            for(byte b:data){  
                sb.append(b).append(",");  
            }  
            sb.deleteCharAt(sb.length()-1);  
            sb.append("}");  
            return sb.toString();  
        }  
          
        public static void main(String[] args) throws Exception {
        	//加密
        	String keyStr = "mK7fp1G2";
        	
            byte[] key = initSecretKey(keyStr); 
            Key k = toKey(key);
            
            //解密
            String token2 = "61D585BB2B675FA3A44CAC846B386C24CB1190DE3A1B3138CB76F0D210DC6252";  
            byte[] encryptData2 = parseHexStr2Byte(token2);
            byte[] decryptData = decrypt(encryptData2, k);  
            System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));  
            System.out.println("解密后数据: string:"+new String(decryptData));  
            
            String str=HttpUtil.get("http://open.tianya.cn/v2/opt/verifyToken.do?tokenStr=tysdk_87407354_019883", null, 5000, 5000, "utf-8");
            System.out.println(str);
        }  
    }  
