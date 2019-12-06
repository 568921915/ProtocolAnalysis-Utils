package com.gjh.Utils.AES;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.gjh.Utils.StringUtils;




/**
 *AES���ܽ��ܹ�����
 *@author M-Y
 */
public class AESUtils {
      //private static final Logger logger = Logger.getLogger(AESUtil.class);
      private static final String defaultCharset = "UTF-8";
      private static final String KEY_AES = "AES";
      private static final String KEY = "123456";
      
      
      
      
      
      //decryptString();
      //mod:"AES/CBC/PKCS5Padding"
      public static byte[] encryptByte(byte[] key, byte[] iv, String mod, byte[] content)
      {
    	  try{  
              KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
              keyGenerator.init(128, new SecureRandom(key));  
              SecretKey seckey=keyGenerator.generateKey();  
              Cipher cipher=Cipher.getInstance(mod);  
              cipher.init(Cipher.ENCRYPT_MODE, seckey, new IvParameterSpec(iv));  
              byte[] result=cipher.doFinal(content);  
              return result;  
    	   }catch (Exception e) {  
               System.out.println("exception:"+e.toString());  
           }   
           return null; 
      }
      
      
      // mod:  "AES/CBC/PKCS5Padding"
      public static byte[] decryptByte(byte[] keyBytes, byte[] iv, String mod, byte[] content){  
          
          try{  
              KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
              keyGenerator.init(128, new SecureRandom(keyBytes));//key������Ϊ128��192��256λ������ֻ����Ϊ128  
              SecretKey key=keyGenerator.generateKey();  
              Cipher cipher=Cipher.getInstance(mod);  
              cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));  
              byte[] result=cipher.doFinal(content);  
              return result;  
          }catch (Exception e) {  
              // TODO Auto-generated catch block  
              e.printStackTrace();
        	  //System.out.println("exception:"+e.toString());  
          }   
          return null;  
      }  
      
      
      
      public static byte[] AES_CBC_Encrypt(byte[] content, byte[] keyBytes, byte[] iv){  
          
          try{  
              KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
              keyGenerator.init(128, new SecureRandom(keyBytes));  
              SecretKey key=keyGenerator.generateKey();  
              Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");  
              cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));  
              byte[] result=cipher.doFinal(content);  
              return result;  
          }catch (Exception e) {  
              // TODO Auto-generated catch block  
              System.out.println("exception:"+e.toString());  
          }   
          return null;  
      }  
      
      
      
    /**
     * ����
     *
     * @param data ��Ҫ���ܵ�����
     * @param key ��������
     * @return
     */
    public static String encrypt(String data, String key) {
        return doAesString(data, key, Cipher.ENCRYPT_MODE);
    }
 
    /**
     * ����
     *
     * @param data ����������
     * @param key ������Կ
     * @return
     */
    public static String decrypt(String data, String key) {
        return doAesString(data, key, Cipher.DECRYPT_MODE);
    }
    
    public static byte[] AES128Decode1(byte[] arg8, byte[] arg9) throws Throwable {
        if(arg8 != null) {
            if(arg9 == null) {
            }
            else {
                byte[] v1 = new byte[16];
                System.arraycopy(arg8, 0, v1, 0, Math.min(arg8.length, 16));
                SecretKeySpec v8 = new SecretKeySpec(v1, "AES");
                Cipher v0 = Cipher.getInstance("AES/CBC/PKCS5Padding");
                v0.init(2, ((Key)v8));
                arg8 = new byte[v0.getOutputSize(arg9.length)];
                v0.doFinal(arg8, v0.update(arg9, 0, arg9.length, arg8, 0));
                return arg8;
            }
        }

        return null;
    }
    
    public static byte[] AES128Decode(byte[] arg6, byte[] arg7) {
        byte[] v4 = {};
        int v3 = 16;
        if(arg6 == null || arg7 == null) {
            v4 = null;
        }
        else {
            byte[] v0 = new byte[v3];
            System.arraycopy(arg6, 0, v0, 0, Math.min(arg6.length, v3));
            SecretKeySpec v1 = new SecretKeySpec(v0, "AES");
            Cipher v0_1;
			try {
				//v0_1 = Cipher.getInstance("AES/ECB/NoPadding", "BC");
				v0_1 = Cipher.getInstance("AES/ECB/NoPadding");
		
            v0_1.init(2, (Key) ((Key)v1));
            v4 = new byte[v0_1.getOutputSize(arg7.length)];
            v0_1.doFinal(v4, v0_1.update(arg7, 0, arg7.length, v4, 0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return v4;
    }
    
    public static String doAesByte( byte[] data, String key, int mode)
    {
    	try {
            if (data.length == 0 || StringUtils.isBlank(key)) {
                return null;
            }
            //�ж��Ǽ��ܻ��ǽ���
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content = data;
            //true �������� false ��������
            /*
            if (encrypt) {
                content = data;
            } else {
                content = parseHexStr2Byte(data);
            }
            */
            //1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            //2.����ecnodeRules�����ʼ����Կ������
            //����һ��128λ�����Դ,���ݴ�����ֽ�����
            kgen.init(128, new SecureRandom(key.getBytes()));
            //3.����ԭʼ�Գ���Կ
            SecretKey secretKey = kgen.generateKey();
            //4.���ԭʼ�Գ���Կ���ֽ�����
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.�����ֽ���������AES��Կ
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.����ָ���㷨AES�Գ�������
            Cipher cipher = Cipher.getInstance(KEY_AES);// ����������
            //7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽��ܽ���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
            cipher.init(mode, keySpec);// ��ʼ��
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                //��������ת����16����
                return parseByte2HexStr(result);
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
        	
            System.out.println("AES ���Ĵ����쳣 : " + e);
            e.printStackTrace();
        }
        return null;
    	
    	
    	
    }
 
    /**
     * �ӽ���
     *
     * @param data ����������
     * @param password  ��Կ
     * @param mode �ӽ���mode
     * @return
     */
    private static String doAesString(String data, String key, int mode) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }
            //�ж��Ǽ��ܻ��ǽ���
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true �������� false ��������
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = parseHexStr2Byte(data);
            }
            //1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            //2.����ecnodeRules�����ʼ����Կ������
            //����һ��128λ�����Դ,���ݴ�����ֽ�����
            kgen.init(128, new SecureRandom(key.getBytes()));
            //3.����ԭʼ�Գ���Կ
            SecretKey secretKey = kgen.generateKey();
            //4.���ԭʼ�Գ���Կ���ֽ�����
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.�����ֽ���������AES��Կ
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.����ָ���㷨AES�Գ�������
            Cipher cipher = Cipher.getInstance(KEY_AES);// ����������
            //7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽��ܽ���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
            cipher.init(mode, keySpec);// ��ʼ��
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                //��������ת����16����
                return parseByte2HexStr(result);
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
            System.out.println("AES ���Ĵ����쳣" + e);
        }
        return null;
    }
    /**
     * ��������ת����16����
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * ��16����ת��Ϊ������
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    /*
    public static void main(String[] args) throws Exception {  
        String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";  
        System.out.println("����ǰ��" + content);  
        System.out.println("������Կ�ͽ�����Կ��" + KEY);  
        String encrypt = encrypt(content, KEY);  
        System.out.println("���ܺ�" + encrypt);  
        String decrypt = decrypt(encrypt, KEY);  
        System.out.println("���ܺ�" + decrypt);  
    }  */
}

