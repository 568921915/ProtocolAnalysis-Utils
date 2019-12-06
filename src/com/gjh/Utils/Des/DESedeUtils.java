package com.gjh.Utils.Des;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import com.gjh.Utils.FileUtils;
import com.gjh.Utils.Gzip.GZipUtils;
import com.gjh.Utils.base64.Base64Utils;

public class DESedeUtils {

	public static byte[] decrypt(byte[] bKey, byte[] bContent) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(bKey, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(2, ((Key) secretKey));
			byte[] bAfterEncrypt = cipher.doFinal(bContent);
			return bAfterEncrypt;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	

}
