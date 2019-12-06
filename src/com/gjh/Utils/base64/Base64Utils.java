package com.gjh.Utils.base64;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
public class Base64Utils {
	/*
	final static Base64.Decoder decoder = Base64.getDecoder();
	final static Base64.Encoder encoder = Base64.getEncoder();
	
	
	//解码
	public static byte[] base64Decode(String inputStr){
		if(inputStr == null || inputStr.length() == 0)
		{
			System.out.println("error");
			return null;
		}
		byte[] rst = decoder.decode(inputStr);
		return rst;
	}
	
	 * 
	 */
	public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

	
	
	 private static final String UTF_8 = "UTF-8";

	    /**
	     * 对给定的字符串进行base64解码操作
	     */
	    public static String decodeData2Str(String inputData) {
	        try {
	            if (null == inputData) {
	                return null;
	            }
	            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
	        } catch (UnsupportedEncodingException e) {
	            //logger.error(inputData, e);
	        }

	        return null;
	    }
	    public static byte[] decodeData2Byte(String inputData) {
	    	try {
	            if (null == inputData) {
	            	System.out.println("input null");
	                return null;
	            }
	            return Base64.decodeBase64(inputData.getBytes(UTF_8));
	        } catch (UnsupportedEncodingException e) {
	            System.out.println( e);
	        }

	        return null;
	    }
	    
	    
	    public static byte[] decodeByte2Byte(byte[] inputData) {
	            if (null == inputData) {
	                return null;
	            }
	            return Base64.decodeBase64(inputData);
	    }
	    /**
	     * 对给定的字符串进行base64加密操作
	     */
	    public static String encodeData(String inputData) {
	        try {
	            if (null == inputData) {
	                return null;
	            }
	            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
	        } catch (UnsupportedEncodingException e) {
	            //logger.error(inputData, e);
	        }

	        return null;
	    }
	    public static String encodeData(byte[] inputData) {
	        if (null == inputData) {
			    return null;
			}
			return new String(Base64.encodeBase64(inputData));

	        //return null;
	    }


}
