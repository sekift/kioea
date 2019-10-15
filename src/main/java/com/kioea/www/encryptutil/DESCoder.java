package com.kioea.www.encryptutil;

import java.security.Key;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * 
 * @author:sekift
 * @time:2017-4-27 下午03:58:46
 * @version:
 */
public abstract class DESCoder {
	
	public static final String KEY_ALGORITHM = "DESede";
	
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
	
	private static Key toKey(byte[] key)throws Exception{
		DESedeKeySpec dks = new DESedeKeySpec(key);
		SecretKeyFactory keyFacotry = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		return keyFacotry.generateSecret(dks);
	}
	
	

}
