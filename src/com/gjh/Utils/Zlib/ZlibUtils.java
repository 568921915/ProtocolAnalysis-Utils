package com.gjh.Utils.Zlib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.gjh.Utils.FileUtils;
import com.gjh.Utils.base64.Base64Utils;

public class ZlibUtils {
	
	/** 
     * ѹ�� 
     *  
     * @param data 
     *            ��ѹ������ 
     * @return byte[] ѹ��������� 
     */  
	public static byte[] compress(byte[] data) {  
        byte[] output = new byte[0];  
  
        Deflater compresser = new Deflater();  
  
        compresser.reset();  
        compresser.setInput(data);  
        compresser.finish();  
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);  
        try {  
            byte[] buf = new byte[1024];  
            while (!compresser.finished()) {  
                int i = compresser.deflate(buf);  
                bos.write(buf, 0, i);  
            }  
            output = bos.toByteArray();  
        } catch (Exception e) {  
            output = data;  
            e.printStackTrace();  
        } finally {  
            try {  
                bos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        compresser.end();  
        return output;  
    }  
	
	
	public static byte[] decompress(byte[] data) {  
        byte[] output = new byte[0];  
  
        Inflater decompresser = new Inflater();  
        decompresser.reset();  
        decompresser.setInput(data);  
  
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);  
        try {  
            byte[] buf = new byte[1024];  
            while (!decompresser.finished()) {  
                int i = decompresser.inflate(buf);  
                o.write(buf, 0, i);  
            }  
            output = o.toByteArray();  
        } catch (Exception e) {  
            output = data;  
            e.printStackTrace();  
        } finally {  
            try {  
                o.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
  
        decompresser.end();  
        return output;  
    }  
	
	public static void main(String[] args) {
		
		byte[] a = FileUtils.ReadBinaryFile("e:/shenzhouzhuanche.bin");
		 byte []rst = decompress(a);
		 FileUtils.SaveByteToFile("e:/3-shenzhouzuche.bin", rst);
			try {
				System.out.println(new String(rst,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
		String a = FileUtils.ReadStringFile("e:/in.txt");
		System.out.println(a);
		byte[] bt = Base64Utils.decodeByte2Byte(a.getBytes());
		FileUtils.SaveByteToFile("e:/base64.bin", bt);
		*/
	}
  

}
