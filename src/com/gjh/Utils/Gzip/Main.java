package com.gjh.Utils.Gzip;

import java.io.UnsupportedEncodingException;

import com.gjh.Utils.FileUtils;

public class Main {
	public static void a(String[] args) {
		byte[] a = FileUtils.ReadBinaryFile("e:/gzip_rst.bin");
		String a1;
		try {
			a1 = new String(a, "utf-8");
			System.out.println(a1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		
		byte [] input = FileUtils.ReadBinaryFile("e:/Pandora.bin");
		byte[] rst = GZipUtils.uncompress2byte(input);
		try {
			System.out.println(new String(rst, "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileUtils.SaveByteToFile("e:/gzip_rst.bin", rst);
		//String str = GZipUtils.uncompress(input);
	}

}
