package com.gjh.Utils.Certificate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.gjh.Utils.base64.Base64Utils;

public class PublicKeyUtils {
	public static PublicKey GetPublicKeyFromBase64( String base64Str ) {
		byte[] bCer = Base64Utils.decodeData2Byte(base64Str);
		PublicKey v1_1;
		try {
			v1_1 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bCer));
		
		
        System.out.println(v1_1.toString());
		String rsagy = ((RSAKey)(v1_1)).getModulus().toString(16);
        String rsazs = ((RSAPublicKey)(v1_1)).getPublicExponent().toString(16);
        
        
        System.out.printf("RSA len:%d\n",rsagy.length()/2*8);
        System.out.printf("RSA:%s\n",rsagy);
        System.out.printf("RSA eponent:%s\n",rsazs);
        
        
        return v1_1;
        
        
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	
	public static PublicKey GetPublicKeyFromBase64Certificate( String base64Str ) {
		PublicKey v0 = null;
		Certificate certi = null;
		byte[] bCer = Base64Utils.decodeData2Byte(base64Str);
		ByteArrayInputStream v2 = new ByteArrayInputStream(bCer);
		CertificateFactory v1_2;
		try {
			v1_2 = CertificateFactory.getInstance("X.509");
			KeyFactory v3 = KeyFactory.getInstance("RSA");
			certi = v1_2.generateCertificate(((InputStream)v2));
			
			v0 = v3.generatePublic(new X509EncodedKeySpec(certi.getPublicKey().getEncoded()));
			 
			
			 
			System.out.println(v0.toString());
			  
		    String rsagy = ((RSAKey)(v0)).getModulus().toString(16);
		    String rsazs = ((RSAPublicKey)(v0)).getPublicExponent().toString(16);
		        
		        
		    System.out.printf("RSA len:%d\n",rsagy.length()/2*8);
		    System.out.printf("RSA:%s\n",rsagy);
		    System.out.printf("RSA eponent:%s\n",rsazs);
			 
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v0;
	}

}
