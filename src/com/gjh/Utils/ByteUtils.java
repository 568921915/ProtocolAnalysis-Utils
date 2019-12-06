package com.gjh.Utils;

public class ByteUtils {
	/**
	 * ��16���Ƶ�byte[]����ת��Ϊ�ַ�����ʽ
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
	 * ���ַ�����ʽ��16����ת��Ϊbyte�����16����
	 * @param input
	 * @return
	 */
	public static byte[] HexStrToByteArray(String input) {

	        int scale = 16;		//ת��Ϊ16����
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
