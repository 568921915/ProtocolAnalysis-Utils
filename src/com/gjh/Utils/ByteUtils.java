package com.gjh.Utils;

public class ByteUtils {
	/**
	 * 将16进制的byte[]数组转化为字符串形式
	 * @param byteArray
	 * @return
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
	
	/**
	 * 将字符串形式的16进制转化为byte数组的16进制
	 * @param input
	 * @return
	 */
	public static byte[] HexStrToByteArray(String input) {

	        int scale = 16;		//转换为16进制
	        int v2 = input.length();
	        
	        if(v2 % 2 != 0) {
	            throw new IllegalArgumentException("len % 2 != 0");
	        }
	
	        byte[] rst = new byte[v2 / 2];
	        int v1;
	        for(v1 = 0; v1 < v2; v1 += 2) {
	            rst[v1 / 2] = ((byte)((Character.digit(input.charAt(v1), scale) << 4) + Character.digit(input.charAt(v1 + 1), scale)));
	        }

	        return rst;
	    
		
	}

}
