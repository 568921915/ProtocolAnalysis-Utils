package com.gjh.Utils.Gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString("ISO-8859-1"); // UTF-8 ISO-8859-1
	}

	// 解压缩
	public static String uncompress(String str)   {
		try{
			if (str == null || str.length() == 0) {
				return str;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			// toString()使用平台默认编码，也可以显式的指定如toString("GBK")
			return out.toString();
		}catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	//解压缩之后返回字符串
	public static String uncompress(byte[] input_byte) {
		if (input_byte.length == 0) {
			return null;
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(input_byte);
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			out.close();
			return out.toString("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//解压缩之后返回byte[]
	public static byte[] uncompress2byte(byte[] input_byte) {
		if (input_byte.length == 0) {
			return null;
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(input_byte);
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			out.close();
			return out.toString("utf-8").getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
