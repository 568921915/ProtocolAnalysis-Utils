package com.gjh.Utils;

import java.io.UnsupportedEncodingException;

import com.gjh.Utils.base64.Base64Utils;

public class Test {
	public static void main(String[] args) {
		String a = "34629E0263C5FA522B1FF8C378BD811202E80EBE2387058358FD8AB22C4586A0B6E814B4DC891D5AE0FF5E1B03EB6EFB3FFF13F06C8C8CC5774A55E124F5C7FB9B94727E26A5A7F9EF3DDA33E1BB988E1F84AF4EA83D5A36B07B08B8AC1A871BD39E879081852953790BEFA9CC5775308AC4BD16C493548F68017345DC81B7A6";
		FileUtils.SaveByteToFile("e:/javaHook.bin", ByteUtils.HexStrToByteArray(a));
		try {
			System.out.println(new String(ByteUtils.HexStrToByteArray(a), "utf-8"));
			System.out.println(new String(ByteUtils.HexStrToByteArray(a), "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
