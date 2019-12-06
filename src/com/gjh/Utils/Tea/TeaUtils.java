package com.gjh.Utils.Tea;

import java.io.File;

import com.gjh.Utils.ByteUtils;
import com.gjh.Utils.FileUtils;

/**
 * Tea�㷨
 * ÿ�β������Դ���8���ֽ�����
 * KEYΪ16�ֽ�,ӦΪ����4��int������int[]��һ��intΪ4���ֽ�
 * ���ܽ�������ӦΪ8�ı������Ƽ���������Ϊ64��
 * */
public class TeaUtils {
	/*
    private final static int[] KEY = new int[]{//���ܽ������õ�KEY
        0x789f5645, 0xf68bd5a4,
        0x81963ffa, 0x458fac58
    };
    */
    private final static int[] KEY = new int[]{//���ܽ������õ�KEY
    		0x73596b36, 0x426f4942,
    		0x51496a69, 0x45336c46
    };
    //����
    public static byte[] encrypt(byte[] content, int offset, int[] key, int times){//timesΪ��������
        int[] tempInt = byteToInt(content, offset);
        int y = tempInt[0], z = tempInt[1], sum = 0, i;
        int delta=0x9e3779b9; //�����㷨��׼����ֵ
        int a = key[0], b = key[1], c = key[2], d = key[3]; 

        for (i = 0; i < times; i++) {   
            
            sum += delta;
            y += ((z<<4) + a) ^ (z + sum) ^ ((z>>5) + b);
            z += ((y<<4) + c) ^ (y + sum) ^ ((y>>5) + d);
        }
        tempInt[0]=y;
        tempInt[1]=z; 
        return intToByte(tempInt, 0);
    }
    //����
    public static byte[] decrypt(byte[] encryptContent, int offset, int[] key, int times){
        int[] tempInt = byteToInt(encryptContent, offset);
        if(tempInt == null)
        	return null;
        int y = tempInt[0], z = tempInt[1], sum = 0, i;
        int delta=0x9e3779b9; //�����㷨��׼����ֵ
        int a = key[0], b = key[1], c = key[2], d = key[3];
        if (times == 32)
            sum = 0xC6EF3720; /* delta << 5*/
        else if (times == 16)
            sum = 0xE3779B90; /* delta << 4*/
        else
            sum = delta * times;

        for(i = 0; i < times; i++) { 
            z -= ((y<<4) + c) ^ (y + sum) ^ ((y>>5) + d);
            y -= ((z<<4) + a) ^ (z + sum) ^ ((z>>5) + b);
            sum -= delta; 
        }
        tempInt[0] = y;
        tempInt[1] = z;

        return intToByte(tempInt, 0);
    }
    //byte[]������ת��int[]������
    private static int[] byteToInt(byte[] content, int offset){

    	int inputLen = content.length;
    	int rstLen = inputLen >> 2;
        System.out.println(inputLen);
        System.out.println(rstLen);
        
		int[] result = new int[rstLen];
		for (int i = 0, j = offset; i < rstLen && j < inputLen; i++, j += 4) {
			try{
			result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8 | transform(content[j + 1]) << 16
					| (int) content[j] << 24;
			}catch(Exception e)
			{
				System.out.println("i = " + i);
				//e.printStackTrace();
				return null;

			}
		}
		return result;
        
        
        
        /*
        int[] result = new int[content.length >> 2];//����2��n�η� == ����nλ �� content.length / 4 == content.length >> 2
        for(int i = 0, j = offset; j < content.length; i++, j += 4){
            result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8 |
            transform(content[j + 1]) << 16 | (int)content[j] << 24;
        }
        return result;
        */
        
    }
    //int[]������ת��byte[]������
    private static byte[] intToByte(int[] content, int offset){
        byte[] result = new byte[content.length << 2];//����2��n�η� == ����nλ �� content.length * 4 == content.length << 2
        for(int i = 0, j = offset; j < result.length; i++, j += 4){
            result[j + 3] = (byte)(content[i] & 0xff);
            result[j + 2] = (byte)((content[i] >> 8) & 0xff);
            result[j + 1] = (byte)((content[i] >> 16) & 0xff);
            result[j] = (byte)((content[i] >> 24) & 0xff);
        }
        return result;
    }
    //��ĳ�ֽ�Ϊ�������轫��ת���޷�������
    private static int transform(byte temp){
        int tempInt = (int)temp;
        if(tempInt < 0){
            tempInt += 256;
        }
        return tempInt;
    }
    
    //ͨ��TEA�㷨������Ϣ
    public static byte[] encryptByTea(String info){
        byte[] temp = info.getBytes();
        int n = 8 - temp.length % 8;//��temp��λ������8�ı���,��Ҫ����λ��
        byte[] encryptStr = new byte[temp.length + n];
        encryptStr[0] = (byte)n;
        System.arraycopy(temp, 0, encryptStr, n, temp.length);
        byte[] result = new byte[encryptStr.length];
        for(int offset = 0; offset < result.length; offset += 8){
            byte[] tempEncrpt = TeaUtils.encrypt(encryptStr, offset, KEY, 32);
            System.arraycopy(tempEncrpt, 0, result, offset, 8);
        }
        return result;
    }
    //ͨ��TEA�㷨������Ϣ
    public static String decryptByTea(byte[] secretInfo){
        byte[] decryptStr = null;
        byte[] tempDecrypt = new byte[secretInfo.length];
        for(int offset = 0; offset < secretInfo.length; offset += 8){
            //decryptStr = TeaUtils.decrypt(secretInfo, offset, KEY, 32);
            decryptStr = TeaUtils.decrypt(secretInfo, offset, KEY, 16);
            if(decryptStr == null)
            {
            	return null;
            }else
            {
            	//FileUtils.AppendByteToFile("e:/189rst.bin", decryptStr);
            }
            System.arraycopy(decryptStr, 0, tempDecrypt, offset, 8);
        }
        
        int n = tempDecrypt[0];
        FileUtils.SaveByteToFile("e:/189_rst.bin", tempDecrypt);
        return new String(tempDecrypt, n, decryptStr.length - n);
        
    }
    
    public static void main(String[] args){
    	
    	
    	String sInput = FileUtils.ReadStringFile("e:/189.txt");
    	byte[] bInput = ByteUtils.HexStrToByteArray(sInput);
    	
    	String decryptInfo = decryptByTea(bInput);
    	System.out.println(decryptInfo);
    	
    	/*
        String info =  "123456";
        System.out.println("ԭ���ݣ�" + info);
        byte[] encryptInfo = encryptByTea(info);
        System.out.println("���ܺ�����ݣ�");
        System.out.println(ByteUtils.byteArrayToHexStr(encryptInfo));
        System.out.println("*********************");
        String decryptInfo = decryptByTea(encryptInfo);
        System.out.println("���ܺ�����ݣ�");
        System.out.println(decryptInfo);
        */
        
    }
    
}
