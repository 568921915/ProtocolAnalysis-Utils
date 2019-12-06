package com.gjh.Utils.Des;

import java.security.Key;
//import java.awt.RenderingHints.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.gjh.Utils.FileUtils;
import com.gjh.Utils.Md5.Md5Utils;
import com.gjh.Utils.base64.Base64Utils;

public class DesTest {

	
	private static final char[] f = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	
	public static byte[] m_iv= new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
	public static String m_key = "xwtec$35";
	
	
	private static byte[] a(byte[] arg9) {
        byte[] v0_3;
        Object v0;
        int v4 = 8926;
       
        try {
            IvParameterSpec v0_2 = new IvParameterSpec(m_iv);
            SecretKeySpec v1 = new SecretKeySpec(m_key.getBytes(), "DES");
            Cipher v2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
            v2.init(1, (Key) ((Key)v1), ((AlgorithmParameterSpec)v0_2));
            v0_3 = v2.doFinal(arg9);
        }
        catch(Exception v0_1) {
            v0_1.printStackTrace();
            v0_3 = null;
        }
		return v0_3;

       // return ((byte[])v0);
    }
	
	
	public static String Base64(String inputData)
	{
		return Base64Utils.encodeData(inputData);
	}
	
	public static String Base64(byte[] arg9) {
        String v0_2;
        Object v1 = null;
        int v4 = 8802;
      
      
            int v3 = arg9.length;
            StringBuffer v4_1 = new StringBuffer(arg9.length * 3 / 2);
            int v5 = v3 - 3;
            int v1_1 = 0;
            int v0_1;
            int v2 = 0;
            for(v0_1 = 0; v0_1 <= v5; v0_1 = v2) {
                 v2 = (arg9[v0_1] & 255) << 16 | (arg9[v0_1 + 1] & 255) << 8 | arg9[v0_1 + 2] & 255;
                v4_1.append(f[v2 >> 18 & 63]);
                v4_1.append(f[v2 >> 12 & 63]);
                v4_1.append(f[v2 >> 6 & 63]);
                v4_1.append(f[v2 & 63]);
                v2 = v0_1 + 3;
                v0_1 = v1_1 + 1;
                if(v1_1 >= 14) {
                    v4_1.append(" ");
                    v0_1 = 0;
                }

                v1_1 = v0_1;
            }

            if(v0_1 == v3 - 2) {
                v0_1 = (arg9[v0_1 + 1] & 255) << 8 | (arg9[v0_1] & 255) << 16;
                v4_1.append(f[v0_1 >> 18 & 63]);
                v4_1.append(f[v0_1 >> 12 & 63]);
                v4_1.append(f[v0_1 >> 6 & 63]);
                v4_1.append("=");
            }
            else if(v0_1 == v3 - 1) {
                v0_1 = (arg9[v0_1] & 255) << 16;
                v4_1.append(f[v0_1 >> 18 & 63]);
                v4_1.append(f[v0_1 >> 12 & 63]);
                v4_1.append("==");
            }

            v0_2 = v4_1.toString();
       

        return v0_2;
    }
	
	
	
	
	
	public static void md51() {
		String str =FileUtils.ReadStringFile("d:/test.txt");
		System.out.println(str);
		byte[] rst_des = a(str.getBytes());
		
		String rst_base64 = Base64(rst_des);					//e0100f7f9ded5bd06d82a2a517852543
		//String rst_base64 = Base64Utils.encodeData(rst_des);  //e0100f7f9ded5bd06d82a2a517852543
		String str1 = rst_base64.replaceAll(" ", "");
		System.out.println(str1);
		
		StringBuffer sb = new StringBuffer();
		String str0 = "AdFxHa!3qc#AdA!Rcw&45";
		String devicesID = "DC2A4DD5301C6275C37099C36C32037D";
		String date = "20180619";
		sb.append(str0).append(devicesID).append(date).append(str1);
		
		
		str0 = sb.toString();
		
		System.out.println( "str0 = " + str0 );
		String rst = Md5Utils.getMD5String(str0);
		
		System.out.println(rst);
		System.out.println(rst.toUpperCase());
		
	}
	
	public static void main(String[] args) {
		md51();
	}
	
}
