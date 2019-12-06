package com.gjh.Utils.RSA;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RsaUtils {

	// 将base64编码后的公钥字符串转成PublicKey实例
	public static PublicKey getPublicKey(String publicKey) {
		byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 将base64编码后的私钥字符串转成PrivateKey实例
	public static PrivateKey getPrivateKey(String privateKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 公钥加密
	public static byte[] encrypt(byte[] content, String base64_PubKey) {
		try {
			PublicKey publicKey = getPublicKey(base64_PubKey);
			Cipher cipher = Cipher.getInstance("RSA");// java默认"RSA"="RSA/ECB/PKCS1Padding"
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 私钥解密
	public static byte[] decrypt(byte[] content, String base64_PriKey) {

		try {
			PrivateKey privateKey = getPrivateKey(base64_PriKey);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
